package org.forkjoin.jdbckit.core.impi;

import org.forkjoin.apikit.core.PageResult;
import org.forkjoin.jdbckit.core.*;
import org.forkjoin.jdbckit.core.identifier.Field;
import org.forkjoin.jdbckit.core.select.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BaseDaoImpi<T extends EntityObject, K extends KeyObject>
        extends AbstractBaseDao<T, K> {

    protected static final Logger log = LoggerFactory.getLogger(BaseDaoImpi.class);

    private ReadOnlyDaoImpi<T, K> readOnlyDaoImpi;

    public BaseDaoImpi(EntityMeta<T, K> entityMeta) {
        super(entityMeta);
        readOnlyDaoImpi = new ReadOnlyDaoImpi<>(entityMeta);
    }

    @Override
    protected void initTemplateConfig() {
        super.initTemplateConfig();
        readOnlyDaoImpi.setJdbcTemplate(getJdbcTemplate());
    }

    @Override
    public boolean replace(final T t) {
        return getJdbcTemplate().execute(new ConnectionCallback<Boolean>() {
            @Override
            public Boolean doInConnection(Connection con) throws SQLException, DataAccessException {
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    String sql = entityMeta.getReplaceSql();
                    if (log.isDebugEnabled()) {
                        log.debug("replace: {}[object:{}]", sql, t);
                    }
                    ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                    entityMeta.setAllPreparedStatement(t, ps, 1);
                    int updateCount = ps.executeUpdate();

                    if (entityMeta.isHasAutoIncrement()) {
                        rs = ps.getGeneratedKeys();
                        if (rs.next()) {
                            entityMeta.setKey(t, rs);
                        }
                    }
                    return updateCount > 0;
                } catch (Exception e) {
                    log.error("sql错误{}", t, e);
                    throw e;
                } finally {
                    JdbcUtils.closeResultSet(rs);
                    JdbcUtils.closeStatement(ps);
                }
            }
        });
    }

    @Override
    public void insert(final T t) {
        getJdbcTemplate().execute(new ConnectionCallback<Void>() {
            @Override
            public Void doInConnection(Connection con) throws SQLException, DataAccessException {
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    String sql = entityMeta.getInsertSql();
                    if (log.isDebugEnabled()) {
                        log.debug("insert: {}[object:{}]", sql, t);
                    }
                    ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                    entityMeta.setPreparedStatement(t, ps, 1, true);
                    ps.execute();
                    if (entityMeta.isHasAutoIncrement()) {
                        rs = ps.getGeneratedKeys();
                        if (rs.next()) {
                            entityMeta.setKey(t, rs);
                        }
                    }
                    return null;
                } catch (Exception e) {
                    log.error("sql错误{}", t, e);
                    throw e;
                } finally {
                    JdbcUtils.closeResultSet(rs);
                    JdbcUtils.closeStatement(ps);
                }
            }
        });
    }

    @Override
    public int update(final T t) {
        return getJdbcTemplate().execute(new ConnectionCallback<Integer>() {
            @Override
            public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
                PreparedStatement ps = null;
                try {
                    if (log.isDebugEnabled()) {
                        log.debug("update: {}[nums:{}]", entityMeta.getUpdateSql(), t.toString());
                    }
                    ps = con.prepareStatement(entityMeta.getUpdateSql());

                    int i = 1;
                    i = entityMeta.setPreparedStatement(t, ps, i, true);
                    //设置 WHERE
                    entityMeta.setPreparedStatementKeys(t, ps, i);
                    return ps.executeUpdate();
                } catch (Exception e) {
                    log.error("sql错误", e);
                    throw e;
                } finally {
                    JdbcUtils.closeStatement(ps);
                }
            }
        });
    }

    @Override
    public int del(final K key) {
        return getJdbcTemplate().execute(new ConnectionCallback<Integer>() {
            @Override
            public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
                PreparedStatement ps = null;
                try {
                    if (log.isDebugEnabled()) {
                        log.debug("del: {}[map:{}]", entityMeta.getKeyDeleteSql(), key);
                    }
                    ps = con.prepareStatement(entityMeta.getKeyDeleteSql());
                    entityMeta.setKeyPreparedStatement(key, ps, 1);
                    return ps.executeUpdate();
                } catch (Exception e) {
                    log.error("sql错误", e);
                    throw e;
                } finally {
                    JdbcUtils.closeStatement(ps);
                }
            }
        });
    }

    @Override
    public int del(final Field key0, final Object value0) {
        return getJdbcTemplate().execute(new ConnectionCallback<Integer>() {
            @Override
            public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
                PreparedStatement ps = null;
                try {
                    String tableName = entityMeta.getDbTableName().toString();
                    String sql = "DELETE FROM `" + tableName + "` WHERE `" + SqlUtils.nameFilter(key0.toString()) + "` = ?";

                    if (log.isDebugEnabled()) {
                        log.debug("del by {}: {}[map:{}]", tableName, sql, value0);
                    }
                    ps = con.prepareStatement(sql);
                    ps.setObject(1, value0);

                    return ps.executeUpdate();
                } catch (Exception e) {
                    log.error("sql错误", e);
                    throw e;
                } finally {
                    JdbcUtils.closeStatement(ps);
                }
            }
        });
    }

    @Override
    public int insert(final List<T> list) {
        return getJdbcTemplate().execute(new ConnectionCallback<Integer>() {
            @Override
            public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
                PreparedStatement ps = null;
                try {
                    if (!list.isEmpty()) {
//                        T f = list.get(0);
                        StringBuilder sb = new StringBuilder(entityMeta.getFastInsertPrefixSql());

                        for (int i = 0; i < list.size(); i++) {
                            if (i != 0) {
                                sb.append(",");
                            }
                            sb.append(entityMeta.getFastInsertValueItemsSql());
                        }
                        if (log.isDebugEnabled()) {
                            log.debug("fastInsert: {}; nums:{}", sb.toString(), list.size());
                        }
                        ps = con.prepareStatement(sb.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

                        int i = 1;
                        for (T t : list) {
                            i = entityMeta.setPreparedStatement(t, ps, i, true);
                        }
                        return ps.executeUpdate();
                    }
                    return 0;
                } catch (Exception e) {
                    log.error("sql错误", e);
                    throw e;
                } finally {
                    JdbcUtils.closeStatement(ps);
                }
            }
        });
    }

    @Override
    public int incrementUpdatePartial(final Map<Field, Object> m, final K key) {
        StringBuilder sb = new StringBuilder();

        sb.append(entityMeta.getKeyUpdatePartialPrefixSql());
        ArrayList<Object> args = BaseDaoImpi.this.toIncrementSqlSet(sb, m);
        sb.append(entityMeta.getKeyWhereByKeySql());
        final String sql = sb.toString();


        Collections.addAll(args, key.getQueryParams());
        return updatePartial(sql, args);
    }

    @Override
    public int updatePartial(final Map<Field, Object> m, final K key) {
        StringBuilder sb = new StringBuilder();

        sb.append(entityMeta.getKeyUpdatePartialPrefixSql());
        ArrayList<Object> args = BaseDaoImpi.this.toSqlSet(sb, m);
        sb.append(entityMeta.getKeyWhereByKeySql());
        final String sql = sb.toString();


        Collections.addAll(args, key.getQueryParams());
        return updatePartial(sql, args);
    }

    private int updatePartial(final String sql, final ArrayList<Object> args) {
        return getJdbcTemplate().execute(new ConnectionCallback<Integer>() {
            @Override
            public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
                PreparedStatement ps = null;
                try {
                    if (log.isDebugEnabled()) {
                        log.debug("updatePartial: {}; args:{}", sql, args);
                    }
                    ps = con.prepareStatement(sql);

                    int i = 1;

                    for (Object arg : args) {
                        ps.setObject(i, arg);
                        i++;
                    }
                    return ps.executeUpdate();
                } catch (Exception e) {
                    log.error("sql错误", e);
                    throw e;
                } finally {
                    JdbcUtils.closeStatement(ps);
                }
            }
        });
    }

    /**
     * 转换map成为UPDATE 一句里面的SET短
     * 例如:`content`='11',`type`=1
     */
    private ArrayList<Object> toIncrementSqlSet(StringBuilder sb, Map<Field, Object> m) {
        boolean isAdd = false;
        ArrayList<Object> list = new ArrayList<>();
        for (Map.Entry<Field, Object> e : m.entrySet()) {
            String name = SqlUtils.nameFilter(e.getKey().toString());
            list.add(e.getValue());

            if (isAdd) {
                sb.append(',');
            } else {
                isAdd = true;
            }
            sb.append('`');
            sb.append(name);
            sb.append("` = `");
            sb.append(name);
            sb.append("` + ?");

        }
        return list;
    }


    private ArrayList<Object> toSqlSet(StringBuilder sb, Map<Field, Object> m) {
        boolean isAdd = false;
        ArrayList<Object> list = new ArrayList<>();
        for (Map.Entry<Field, Object> e : m.entrySet()) {
            String name = SqlUtils.nameFilter(e.getKey().toString());
            list.add(e.getValue());

            if (isAdd) {
                sb.append(',');
            } else {
                isAdd = true;
            }
            sb.append('`');
            sb.append(name);
            sb.append("` = ?");
        }
        return list;
    }


    @Override
    public T get(K k) {
        return readOnlyDaoImpi.get(k);
    }

    @Override
    public long getCount() {
        return readOnlyDaoImpi.getCount();
    }

    @Override
    public long getCount(QueryParams params) {
        return readOnlyDaoImpi.getCount(params);
    }

    @Override
    public long getCount(String sql, Object[] args) {
        return readOnlyDaoImpi.getCount(sql, args);
    }

    @Override
    public long getCount(Select select) {
        return readOnlyDaoImpi.getCount(select);
    }

    @Override
    public T findObject(Select select) {
        return readOnlyDaoImpi.findObject(select);
    }

    @Override
    public PageResult<T> findPage(Select select, int page, int pageSize) {
        return readOnlyDaoImpi.findPage(select, page, pageSize);
    }

    @Override
    public <C> PageResult<C> findPage(Select select, RowMapper<C> rowMapper, int page, int pageSize) {
        return readOnlyDaoImpi.findPage(select, rowMapper, page, pageSize);
    }

    @Override
    public List<T> find(Select select) {
        return readOnlyDaoImpi.find(select);
    }

    @Override
    public <C> List<C> find(Select select, RowMapper<C> rowMapper) {
        return readOnlyDaoImpi.find(select, rowMapper);
    }

    @Override
    protected PageResult<T> innerFindPage(QueryParams params, Order order, int page, int pageSize) {
        return readOnlyDaoImpi.innerFindPage(params, order, page, pageSize);
    }

    @Override
    protected List<T> innerFind(QueryParams params, Order order, int max) {
        return readOnlyDaoImpi.innerFind(params, order, max);
    }
}