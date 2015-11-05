package com.forkjoin.core.dao.impi;

import com.forkjoin.core.dao.*;
import com.google.common.collect.Lists;
import com.forkjoin.core.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ReadOnlyDaoImpi<T extends EntityObject, K extends KeyObject>
        extends AbstractReadOnlyDao<T, K> {

    protected static final Logger log = LoggerFactory.getLogger(ReadOnlyDaoImpi.class);

    public static final String STRING_COUNT = " count(1) ";

    protected final TableInfo<T, K> tableInfo;

    public ReadOnlyDaoImpi(TableInfo<T, K> tableInfo) {
        this.tableInfo = tableInfo;
    }

    @Override
    public T get(K k) {
        return querySingle(
                tableInfo.getSelectByKeySql(),
                tableInfo.getRowMapper(),
                k.getQueryParams()
        );
    }

    @Override
    public long getCount() {
        if (log.isDebugEnabled()) {
            log.debug("getCount: {};table:{}", tableInfo.getSelectCountSql(), tableInfo.getDbTableName());
        }
        return getJdbcTemplate().queryForObject(tableInfo.getSelectCountSql(), Long.class);
    }

    @Override
    public long getCount(QueryParams params) {
        StringBuilder sb = new StringBuilder(tableInfo.getSelectCountSql());
        params.toSql(sb);

        Object[] args = params.toParams();
        String sql = sb.toString();
        if (log.isDebugEnabled()) {
            log.debug("getCount: {}; params:{}; table:{}", sql, Arrays.toString(args), tableInfo.getDbTableName());
        }
        return getJdbcTemplate().queryForObject(sql, args, Long.class);
    }

    @Override
    public PageResult<T> findPage(QueryParams params, Order order, int page, int pageSize) {
        StringBuilder sb = new StringBuilder(tableInfo.getFormatSelectPrefixSql());
        if (params != null) {
            params.toSql(sb);
        }
        if (order == null) {
            sb.append(tableInfo.getOrderByIdDescSql());
        } else {
            order.toSql(sb);
        }

        if (params != null) {
            return fastQueryPage(sb.toString(), tableInfo.getRowMapper(), page, pageSize, params.toParams());
        } else {
            return fastQueryPage(sb.toString(), tableInfo.getRowMapper(), page, pageSize);
        }
    }

    @Override
    public List<T> find(int max, QueryParams params, Order order) {
        StringBuilder sb = new StringBuilder(tableInfo.getSelectPrefixSql());
        if (params != null) {
            params.toSql(sb);
        }
        if (order == null) {
            sb.append(tableInfo.getOrderByIdDescSql());
        } else {
            order.toSql(sb);
        }
        sb.append(" LIMIT ");
        sb.append(max);

        if (params != null) {
            return query(sb.toString(), tableInfo.getRowMapper(), params.toParams());
        } else {
            return query(sb.toString(), tableInfo.getRowMapper());
        }
    }

    @Override
    public T findObject(QueryParams params) {
        StringBuilder sb = new StringBuilder(tableInfo.getSelectPrefixSql());
        params.toSql(sb);
        sb.append(tableInfo.getOrderByIdDescSql());
        sb.append(" LIMIT ");
        sb.append(1);
        return querySingle(sb.toString(), tableInfo.getRowMapper(), params.toParams());
    }


    public <C extends T> List<C> query(String sql, RowMapper<C> rowMapper) throws DataAccessException {
        if (log.isDebugEnabled()) {
            log.debug("query: {},table:{}", sql, tableInfo.getDbTableName());
        }
        return getJdbcTemplate().query(sql, new RowMapperResultSetExtractor<>(rowMapper));
    }

    public <C extends T> List<C> query(String sql, RowMapper<C> rowMapper, Object... args) throws DataAccessException {
        if (log.isDebugEnabled()) {
            log.debug("query: {}; args:{}; table:{}", sql, Arrays.toString(args), tableInfo.getDbTableName());
        }
        return getJdbcTemplate().query(sql, new RowMapperResultSetExtractor<>(rowMapper), args);
    }

    public <C extends T> C querySingle(String sql, RowMapper<C> rowMapper, Object... args) {
        if (log.isDebugEnabled()) {
            log.debug("querySingle: {}; args:{}; table:{}", sql, Arrays.toString(args), tableInfo.getDbTableName());
        }
        List<C> results = getJdbcTemplate().query(sql, args, new RowMapperResultSetExtractor<>(rowMapper, 1));
        return DataAccessUtils.singleResult(results);
    }

    /**
     * sql语句类似这种
     * SELECT %s FROM `activity` WHERE id=?
     * 这个查询为了简单,但是比较耦合
     */
    public  <C extends T> PageResult<C> fastQueryPage(final String sql, final RowMapper<C> rowMapper, final int page, final int pageSize, final Object... args) {
        return getJdbcTemplate().execute(new ConnectionCallback<PageResult<C>>() {
            @Override
            public PageResult<C> doInConnection(Connection con) throws SQLException, DataAccessException {
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    ps = con.prepareStatement(String.format(sql, STRING_COUNT));
                    int i = 1;
                    for (Object o : args) {
                        ps.setObject(i++, o);
                    }
                    rs = ps.executeQuery();
                    int count;
                    if (rs.next()) {
                        count = Integer.valueOf(rs.getObject(1).toString());
                    } else {
                        throw new ResultPageDataAccessException("查询count失败!");
                    }
                    JdbcUtils.closeResultSet(rs);
                    JdbcUtils.closeStatement(ps);

                    PageResult<C> pageResult = PageResult.createPage(count, page, pageSize, null);

                    List<C> list = Lists.newArrayListWithCapacity(pageResult.getPageCount());
                    int start = pageResult.getStart();

                    String pageSql = String.format(sql, " * ") + " LIMIT " + start + "," + pageSize;
                    ps = con.prepareStatement(pageSql);
                    i = 1;
                    for (Object o : args) {
                        ps.setObject(i++, o);
                    }

                    if (log.isDebugEnabled()) {
                        log.debug("fastQueryPage: {}; params:{}; table:{}", pageSql, args, tableInfo.getDbTableName());
                    }
                    rs = ps.executeQuery();
                    int rowNum = 0;
                    while (rs.next()) {
                        list.add(rowMapper.mapRow(rs, rowNum++));
                    }
                    pageResult.setValue(list);
                    return pageResult;
                } finally {
                    JdbcUtils.closeResultSet(rs);
                    JdbcUtils.closeStatement(ps);
                }
            }
        });
    }
}