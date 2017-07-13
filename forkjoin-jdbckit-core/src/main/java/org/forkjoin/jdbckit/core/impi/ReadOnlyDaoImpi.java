package org.forkjoin.jdbckit.core.impi;

import com.google.common.collect.Lists;
import org.forkjoin.apikit.core.PageResult;
import org.forkjoin.jdbckit.core.*;
import org.forkjoin.jdbckit.core.select.Select;
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
        extends
        AbstractReadOnlyDao<T, K> {

    protected static final Logger log = LoggerFactory.getLogger(ReadOnlyDaoImpi.class);

    protected final EntityMeta<T, K> entityMeta;

    public ReadOnlyDaoImpi(EntityMeta<T, K> entityMeta) {
        this.entityMeta = entityMeta;
    }

    @Override
    public T get(K k) {
        List<T> query = getJdbcTemplate().query(entityMeta.getSelectByKeySql(), entityMeta.getRowMapper(), k.getQueryParams());
        return query == null || query.isEmpty() ? null : query.get(0);
    }


    @Override
    public long getCount() {
        if (log.isDebugEnabled()) {
            log.debug("getCount: {};table:{}", entityMeta.getSelectCountSql(), entityMeta.getDbTableName());
        }
        return getCount(entityMeta.getSelectCountSql(), null);
    }

    @Override
    public long getCount(QueryParams params) {
        StringBuilder sb = new StringBuilder(entityMeta.getSelectCountSql());
        params.toSql(sb);
        Object[] args = params.toParams();
        String sql = sb.toString();
        return getCount(sql, args);
    }

    @Override
    public long getCount(String sql, Object[] args) {
        if (log.isDebugEnabled()) {
            log.debug("getCount: {},args:{}", sql, Arrays.toString(args));
        }
        return getJdbcTemplate().queryForObject(sql, Long.class, args);
    }


    @Override
    protected PageResult<T> innerFindPage(QueryParams params, Order order, int page, int pageSize) {
        StringBuilder sb = new StringBuilder(entityMeta.getFormatSelectPrefixSql());
        if (params != null) {
            sb.append(params.toSql().replace("%", "%%"));
        }
        if (order == null) {
            sb.append(entityMeta.getOrderByIdDescSql());
        } else {
            order.toSql(sb);
        }

        if (params != null) {
            return fastQueryPage(sb.toString(), entityMeta.getRowMapper(), page, pageSize, params.toParams());
        } else {
            return fastQueryPage(sb.toString(), entityMeta.getRowMapper(), page, pageSize);
        }
    }

    @Override
    public List<T> innerFind(QueryParams params, Order order, int max) {
        StringBuilder sb = new StringBuilder(entityMeta.getSelectPrefixSql());
        if (params != null) {
            params.toSql(sb);
        }
        if (order == null) {
            sb.append(entityMeta.getOrderByIdDescSql());
        } else {
            order.toSql(sb);
        }

        return find(sb.toString(), params == null ? null : params.toParams(), entityMeta.getRowMapper(), max);
    }


    private <C> List<C> find(String sql, Object[] args, RowMapper<C> rowMapper, int max) {
        if (max != -1) {
            sql = sql + " LIMIT " + max;
        }


        if (log.isDebugEnabled()) {
            log.debug("query: {}; args:{}; table:{}", sql, Arrays.toString(args), entityMeta.getDbTableName());
        }
        if (args != null) {
            return getJdbcTemplate().query(sql, new RowMapperResultSetExtractor<>(rowMapper), args);
        } else {
            return getJdbcTemplate().query(sql, new RowMapperResultSetExtractor<>(rowMapper));
        }
    }


    /**
     * sql语句类似这种 SELECT %s FROM `activity` WHERE id=?
     * 这个查询为了简单,但是比较耦合
     */
    private <C extends T> PageResult<C> fastQueryPage(final String sql,
                                                      final RowMapper<C> rowMapper, final int page, final int pageSize,
                                                      final Object... args) {
        return fastQueryPage(String.format(sql, SqlUtils.STRING_COUNT), String.format(sql, " * "), rowMapper, page, pageSize, args);
    }

    private <C> PageResult<C> fastQueryPage(final String countSql,
                                            final String sql, final RowMapper<C> rowMapper, int curPage,
                                            int curPageSize, final Object[] args) {
        final int page = Math.max(curPage, 1);
        final int pageSize = Math.min(curPageSize, 200);
        return getJdbcTemplate()
                .execute(
                        new ConnectionCallback<PageResult<C>>() {
                            @Override
                            public PageResult<C> doInConnection(Connection con) throws SQLException, DataAccessException {
                                PreparedStatement ps = null;
                                ResultSet rs = null;
                                try {

                                    if (log.isDebugEnabled()) {
                                        log.debug(
                                                "fastQueryPage countSql: {}; params:{}; table:{}",
                                                countSql, args,
                                                entityMeta.getDbTableName());
                                    }
                                    ps = con.prepareStatement(countSql);
                                    int i = 1;
                                    for (Object o : args) {
                                        ps.setObject(i++, o);
                                    }
                                    rs = ps.executeQuery();
                                    int count;
                                    if (rs.next()) {
                                        count = Integer.valueOf(rs.getObject(1)
                                                .toString());
                                    } else {
                                        throw new ResultPageDataAccessException(
                                                "查询count失败!");
                                    }
                                    JdbcUtils.closeResultSet(rs);
                                    JdbcUtils.closeStatement(ps);

                                    PageResult<C> pageResult = PageResult
                                            .createPage(count, page, pageSize, null);

                                    List<C> list = Lists
                                            .newArrayListWithCapacity(pageResult
                                                    .getPageCount());
                                    int start = pageResult.getStart();

                                    String pageSql = sql + " LIMIT " + start + ","
                                            + pageSize;

                                    if (log.isDebugEnabled()) {
                                        log.debug(
                                                "fastQueryPage: {}; params:{}; table:{}",
                                                pageSql, args,
                                                entityMeta.getDbTableName());
                                    }

                                    ps = con.prepareStatement(pageSql);
                                    i = 1;
                                    for (Object o : args) {
                                        ps.setObject(i++, o);
                                    }

                                    rs = ps.executeQuery();
                                    int rowNum = 0;
                                    while (rs.next()) {
                                        list.add(rowMapper.mapRow(rs, rowNum++));
                                    }
                                    pageResult.setData(list);
                                    return pageResult;
                                } finally {
                                    JdbcUtils.closeResultSet(rs);
                                    JdbcUtils.closeStatement(ps);
                                }
                            }
                        });
    }


    @Override
    public long getCount(Select select) {
        return getCount(select.toSql(), select.toParams());
    }

    @Override
    public T findObject(Select select) {
        List<T> results = find(select);
        return DataAccessUtils.singleResult(results);
    }

    @Override
    public List<T> find(Select select) {
        return find(select, entityMeta.getRowMapper());
    }

    @Override
    public <C> List<C> find(Select select, final RowMapper<C> rowMapper) {
        return find(select.toSql(), select.toParams(), rowMapper, -1);
    }

    @Override
    public PageResult<T> findPage(Select select, int page, int pageSize) {
        return findPage(select, entityMeta.getRowMapper(), page, pageSize);
    }


    @Override
    public <C> PageResult<C> findPage(Select select, final RowMapper<C> rowMapper, final int page, final int pageSize) {
        StringBuilder sb = new StringBuilder();
        String sql = select.toSql(sb).toString();
        sb.setLength(0);
        String countSql = select.toCountSql(sb).toString();
        return fastQueryPage(countSql, sql, rowMapper, page, pageSize,
                select.toParams());
    }
}