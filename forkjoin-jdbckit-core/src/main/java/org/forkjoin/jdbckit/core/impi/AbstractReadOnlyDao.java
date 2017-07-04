package org.forkjoin.jdbckit.core.impi;

import org.forkjoin.apikit.core.PageResult;
import org.forkjoin.jdbckit.core.*;
import org.forkjoin.jdbckit.core.identifier.Field;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * @author zuoge85 on 15/6/9.
 */
public abstract class AbstractReadOnlyDao<T extends EntityObject, K extends KeyObject>
        extends AutowireNamedJdbcDaoSupport implements ReadOnlyDao<T, K> {


    @Override
    public PageResult<T> findPage(int page, int pageSize) {
        return innerFindPage(null, null, page, pageSize);
    }

    @Override
    public PageResult<T> findPage(Order order, int page, int pageSize) {
        return innerFindPage(null, order, page, pageSize);
    }

    @Override
    public PageResult<T> findPage(Field key, Object value, int page, int pageSize) {
        return innerFindPage(QueryParams.single(key, value), null, page, pageSize);
    }

    @Override
    public PageResult<T> findPage(Field key, Object value, Order order, int page, int pageSize) {
        return innerFindPage(QueryParams.single(key, value), order, page, pageSize);
    }

    @Override
    public PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            int page, int pageSize
    ) {
        return innerFindPage(
                QueryParams.create().add(key0, value0).add(key1, value1),
                null,
                page, pageSize
        );
    }

    @Override
    public PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            Order order,
            int page, int pageSize
    ) {
        return innerFindPage(
                QueryParams.create().add(key0, value0).add(key1, value1),
                order,
                page, pageSize
        );
    }

    @Override
    public PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            Field key2, Object value2,
            int page, int pageSize
    ) {
        return innerFindPage(
                QueryParams.create().add(key0, value0).add(key1, value1).add(key2, value2),
                null,
                page, pageSize
        );
    }

    @Override
    public PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            Field key2, Object value2,
            Order order,
            int page, int pageSize
    ) {
        return innerFindPage(
                QueryParams.create().add(key0, value0).add(key1, value1).add(key2, value2),
                order,
                page, pageSize
        );
    }

    @Override
    public PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            Field key2, Object value2,
            Field key3, Object value3,
            int page, int pageSize
    ) {
        return innerFindPage(
                QueryParams.create().add(key0, value0).add(key1, value1).add(key2, value2).add(key3, value3),
                null,
                page, pageSize
        );
    }

    @Override
    public PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            Field key2, Object value2,
            Field key3, Object value3,
            Order order,
            int page, int pageSize
    ) {
        return innerFindPage(
                QueryParams.create().add(key0, value0).add(key1, value1).add(key2, value2).add(key3, value3),
                order,
                page, pageSize
        );
    }

    @Override
    public PageResult<T> findPage(QueryParams params, int page, int pageSize) {
        return innerFindPage(params, null, page, pageSize);
    }

    @Override
    public PageResult<T> findPage(QueryParams params, Order order, int page, int pageSize) {
        return innerFindPage(params, order, page, pageSize);
    }

    protected abstract PageResult<T> innerFindPage(QueryParams params, Order order, int page, int pageSize);

    @Override
    public List<T> find() {
        return innerFind((QueryParams) null, null);
    }

    @Override
    public List<T> find(Field key, Object value) {
        return innerFind(QueryParams.single(key, value), null);
    }

    @Override
    public List<T> find(Field key, Object value, Order order) {
        return innerFind(QueryParams.single(key, value), order);
    }

    @Override
    public List<T> find(Field key0, Object value0, Field key1, Object value1) {
        return innerFind(QueryParams.create().add(key0, value0).add(key1, value1), null);
    }

    @Override
    public List<T> find(Field key0, Object value0, Field key1, Object value1, Order order) {
        return innerFind(QueryParams.create().add(key0, value0).add(key1, value1), order);
    }

    @Override
    public List<T> find(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2) {
        return innerFind(QueryParams.create().add(key0, value0).add(key1, value1).add(key2, value2), null);
    }

    @Override
    public List<T> find(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2, Order order) {
        return innerFind(QueryParams.create().add(key0, value0).add(key1, value1).add(key2, value2), order);
    }

    @Override
    public List<T> find(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2, Field key3, Object value3) {
        return innerFind(QueryParams.create().add(key0, value0).add(key1, value1).add(key2, value2).add(key3, value3), null);
    }

    @Override
    public List<T> find(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2, Field key3, Object value3, Order order) {
        return innerFind(QueryParams.create().add(key0, value0).add(key1, value1).add(key2, value2).add(key3, value3), order);
    }

    @Override
    public List<T> find(QueryParams params) {
        return innerFind(params, null);
    }

    @Override
    public List<T> find(QueryParams params, Order order) {
        return innerFind(params, order);
    }

    @Override
    public List<T> find(QueryParams params, Order order, int max) {
        return innerFind(params, order, max);
    }

    private List<T> innerFind(QueryParams params, Order order) {
        return innerFind(params, order, -1);
    }

    protected abstract List<T> innerFind(QueryParams params, Order order, int max);

    @Override
    public T findObject() {
        return innerFindObject((QueryParams) null);
    }

    @Override
    public T findObject(Field key, Object value) {
        return innerFindObject(QueryParams.single(key, value));
    }

    @Override
    public T findObject(Field key0, Object value0, Field key1, Object value1) {
        return innerFindObject(QueryParams.create().add(key0, value0).add(key1, value1));
    }

    @Override
    public T findObject(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2) {
        return innerFindObject(QueryParams.create().add(key0, value0).add(key1, value1).add(key2, value2));
    }

    @Override
    public T findObject(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2, Field key3, Object value3) {
        return innerFindObject(QueryParams.create().add(key0, value0).add(key1, value1).add(key2, value2).add(key3, value3));
    }

    @Override
    public T findObject(QueryParams params) {
        return innerFindObject(params);
    }

    private T innerFindObject(QueryParams params) {
        List<T> results = innerFind(params, null, 1);
        return DataAccessUtils.singleResult(results);
    }


}
