package org.forkjoin.jdbckit.core.impi;

import com.google.common.collect.ImmutableMap;
import org.forkjoin.jdbckit.core.BaseDao;
import org.forkjoin.jdbckit.core.EntityMeta;
import org.forkjoin.jdbckit.core.EntityObject;
import org.forkjoin.jdbckit.core.KeyObject;
import org.forkjoin.jdbckit.core.identifier.Field;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author zuoge85 on 15/6/10.
 */
public abstract class AbstractBaseDao<T extends EntityObject, K extends KeyObject>
        extends AbstractReadOnlyDao<T, K>
        implements BaseDao<T, K> {

    protected final EntityMeta<T, K> entityMeta;

    public AbstractBaseDao(EntityMeta<T, K> entityMeta) {
        this.entityMeta = entityMeta;
    }

    @Override
    public int insert(List<T> list) {
        for (T t : list) {
            insert(t);
        }
        return list.size();
    }

    @Override
    public int incrementUpdatePartial(final Field name0, final Object value0, final K key) {
        return incrementUpdatePartial(Collections.singletonMap(name0, value0), key);
    }

    @Override
    public int incrementUpdatePartial(
            final Field name0, final Object value0,
            final Field name1, final Object value1,
            final K key
    ) {
        return incrementUpdatePartial(
                ImmutableMap.of(name0, value0, name1, value1),
                key
        );
    }

    @Override
    public int incrementUpdatePartial(
            final Field name0, final Object value0,
            final Field name1, final Object value1,
            final Field name2, final Object value2,
            final K key
    ) {
        return incrementUpdatePartial(
                ImmutableMap.of(name0, value0, name1, value1, name2, value2),
                key
        );
    }

    @Override
    public int incrementUpdatePartial(
            final Field name0, final Object value0,
            final Field name1, final Object value1,
            final Field name2, final Object value2,
            final Field name3, final Object value3,
            final K key
    ) {
        return incrementUpdatePartial(
                ImmutableMap.of(
                        name0, value0,
                        name1, value1,
                        name2, value2,
                        name3, value3
                ),
                key
        );
    }

    @Override
    public abstract int incrementUpdatePartial(final Map<Field, Object> m, final K key);

    @Override
    public int updatePartial(Field name, Object value, K key) {
        return updatePartial(Collections.singletonMap(name, value), key);
    }

    @Override
    public int updatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            K key
    ) {
        return updatePartial(
                ImmutableMap.of(name0, value0, name1, value1),
                key
        );
    }

    @Override
    public int updatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            Field name2, Object value2,
            K key
    ) {
        return updatePartial(
                ImmutableMap.of(
                        name0, value0,
                        name1, value1,
                        name2, value2
                ),
                key
        );
    }


    @Override
    public int updatePartial(
            Field name0, Object value0,
            Field name1, Object value1,
            Field name2, Object value2,
            Field name3, Object value3,
            K key
    ) {
        return updatePartial(
                ImmutableMap.of(
                        name0, value0,
                        name1, value1,
                        name2, value2,
                        name3, value3
                ),
                key
        );
    }

    @Override
    public abstract int updatePartial(Map<Field, Object> m, K key);

//    public boolean updatePartial(String sql, ArrayList<Object> args);
}
