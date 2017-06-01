package org.forkjoin.jdbckit.core;

import org.forkjoin.jdbckit.core.identifier.Field;

import java.util.List;
import java.util.Map;

/**
 * @author zuoge85 on 15/6/9.
 */
public interface BaseDao<T extends EntityObject, K extends KeyObject> extends ReadOnlyDao<T, K> {
    long insert(T t);

    long replace(T t);

    boolean update(T t);

    int insert(List<T> list);

    boolean del(K key);

    boolean del(Field key0, Object value0);

    boolean incrementUpdatePartial(Field name0, Object value0, K key);

    boolean incrementUpdatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            K key
    );

    boolean incrementUpdatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            Field name2, Object value2,
            K key
    );

    boolean incrementUpdatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            Field name2, Object value2,
            Field name3, Object value3,
            K key
    );

    boolean incrementUpdatePartial(Map<Field, Object> m, K key);

    boolean updatePartial(Field name, Object value, K key);

    boolean updatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            K key
    );

    boolean updatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            Field name2, Object value2,
            K key
    );

    boolean updatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            Field name2, Object value2,
            Field name3, Object value3,
            K key
    );

    boolean updatePartial(Map<Field, Object> m, K key);
}
