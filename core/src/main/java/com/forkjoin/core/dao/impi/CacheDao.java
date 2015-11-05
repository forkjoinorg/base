package com.forkjoin.core.dao.impi;

import com.forkjoin.core.PageResult;
import com.forkjoin.core.dao.*;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.List;
import java.util.Map;

/**
 * 这个版本还未实现缓存
 *
 * @author zuoge85 on 15/6/9.
 */
public class CacheDao<T extends EntityObject, K extends KeyObject>
        extends AbstractBaseDao<T, K> {

    private LocalCache<T, K> localCache;
    private AbstractBaseDao<T, K> dbDao;



    @SuppressWarnings("unchecked")
    public CacheDao(Class<T> cls) throws Exception {
        this((TableInfo) FieldUtils.readStaticField(cls, "TABLE_INFO"));
        localCache = new LocalCache<>();
        dbDao = new BaseDaoImpi<>(tableInfo);
    }

    public CacheDao(TableInfo<T, K> tableInfo) {
        super(tableInfo);
        localCache = new LocalCache<>();
        dbDao = new BaseDaoImpi<>(tableInfo);
    }

    @Override
    protected void initTemplateConfig() {
        super.initTemplateConfig();
        dbDao.setJdbcTemplate(getJdbcTemplate());
    }

    @Override
    public long insert(T t) {
        return dbDao.insert(t);
    }

    @Override
    public long replace(T t) {
        return dbDao.replace(t);
    }

    @Override
    public boolean update(T t) {
        return dbDao.update(t);
    }

    @Override
    public boolean del(K key) {
        return dbDao.del(key);
    }

    @Override
    public boolean incrementUpdatePartial(Map<String, Object> m, K key) {
        return dbDao.incrementUpdatePartial(m, key);
    }

    @Override
    public boolean updatePartial(Map<String, Object> m, K key) {
        return dbDao.updatePartial(m, key);
    }

    @Override
    public T get(K k) {
        return dbDao.get(k);
    }

    @Override
    public long getCount() {
        return dbDao.getCount();
    }

    @Override
    public long getCount(QueryParams params) {
        return dbDao.getCount(params);
    }

    @Override
    public PageResult<T> findPage(QueryParams params, Order order, int page, int pageSize) {
        return dbDao.findPage(params, order, page, pageSize);
    }

    @Override
    public List<T> find(int max, QueryParams params, Order order) {
        return dbDao.find(max, params, order);
    }

    @Override
    public T findObject(QueryParams params) {
        return dbDao.findObject(params);
    }
}
