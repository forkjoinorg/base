package org.forkjoin.jdbckit.core;

import org.forkjoin.apikit.core.PageResult;
import org.forkjoin.jdbckit.core.identifier.Field;
import org.forkjoin.jdbckit.core.select.Select;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * 页码都是 1 开始
 * @author zuoge85 on 15/6/9.
 */
public interface ReadOnlyDao<T extends EntityObject, K extends KeyObject> {
    T get(K k);

    long getCount();

    long getCount(QueryParams params);

    long getCount(String sql, Object[] args);

    PageResult<T> findPage(int page, int pageSize);

    PageResult<T> findPage(Order order, int page, int pageSize);

    PageResult<T> findPage(Field key, Object value, int page, int pageSize);

    PageResult<T> findPage(Field key, Object value, Order order, int page, int pageSize);

    PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            int page, int pageSize
    );

    PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            Order order,
            int page, int pageSize
    );


    PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            Field key2, Object value2,
            int page, int pageSize
    );

    PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            Field key2, Object value2,
            Order order,
            int page, int pageSize
    );

    PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            Field key2, Object value2,
            Field key3, Object value3,
            int page, int pageSize
    );

    PageResult<T> findPage(
            Field key0, Object value0,
            Field key1, Object value1,
            Field key2, Object value2,
            Field key3, Object value3,
            Order order,
            int page, int pageSize
    );

    PageResult<T> findPage(QueryParams params, int page, int pageSize);

    PageResult<T> findPage(QueryParams params, Order order, int page, int pageSize);



    List<T> find();

    List<T> find(Field key, Object value);

    List<T> find(Field key, Object value, Order order);

    List<T> find(Field key0, Object value0, Field key1, Object value1);

    List<T> find(Field key0, Object value0, Field key1, Object value1, Order order);

    List<T> find(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2);

    List<T> find(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2, Order order);

    List<T> find(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2, Field key3, Object value3);

    List<T> find(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2, Field key3, Object value3, Order order);

    List<T> find(QueryParams params);

    List<T> find(QueryParams params, Order order);

    List<T> find(QueryParams params, Order order, int max);


    T findObject();

    T findObject(Field key, Object value);

    T findObject(Field key0, Object value0, Field key1, Object value1);

    T findObject(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2);

    T findObject(Field key0, Object value0, Field key1, Object value1, Field key2, Object value2, Field key3, Object value3);

    T findObject(QueryParams params);



    long getCount(Select select);

    T findObject(Select select);

    PageResult<T> findPage(Select select, int page, int pageSize);

    <C> PageResult<C> findPage(Select select, final RowMapper<C> rowMapper, final int page, final int pageSize);

    List<T> find(Select select);

    <C> List<C> find(Select select, final RowMapper<C> rowMapper);
}
