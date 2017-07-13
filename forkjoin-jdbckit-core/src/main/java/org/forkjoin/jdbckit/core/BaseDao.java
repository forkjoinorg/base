package org.forkjoin.jdbckit.core;

import org.forkjoin.jdbckit.core.identifier.Field;

import java.util.List;
import java.util.Map;

/**
 * @author zuoge85 on 15/6/9.
 */
public interface  BaseDao<T extends EntityObject, K extends KeyObject> extends ReadOnlyDao<T, K> {
    /**
     *
     */
    void insert(T t);

    /**
     * 返回是否替换成功
     */
    boolean replace(T t);

    /**
     * 返回更新数目
     */
    int update(T t);

    int insert(List<T> list);

    int del(K key);

    int del(Field key0, Object value0);

    int incrementUpdatePartial(Field name0, Object value0, K key);

    int incrementUpdatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            K key
    );

    int incrementUpdatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            Field name2, Object value2,
            K key
    );

    int incrementUpdatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            Field name2, Object value2,
            Field name3, Object value3,
            K key
    );

    int incrementUpdatePartial(Map<Field, Object> m, K key);

    int updatePartial(Field name, Object value, K key);

    int updatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            K key
    );

    int updatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            Field name2, Object value2,
            K key
    );

    int updatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            Field name2, Object value2,
            Field name3, Object value3,
            K key
    );

    int updatePartial(Map<Field, Object> m, K key);
}
