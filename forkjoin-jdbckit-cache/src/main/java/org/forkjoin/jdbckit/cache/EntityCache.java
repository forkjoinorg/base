package org.forkjoin.jdbckit.cache;

import org.apache.commons.lang3.StringUtils;
import org.forkjoin.jdbckit.core.*;
import org.forkjoin.jdbckit.core.identifier.Field;
import org.forkjoin.jdbckit.core.identifier.Key;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * 所有操作应该注意原子问题
 * redis 操作应该在redis 事务内执行
 * 数据库炒作应该放在最后!
 */
public class EntityCache<K extends KeyObject, V extends EntityObject> extends Cache<String, V> {
    public static final int MAX_SECONDS = 10;
    public static final int MAX_NUMS = 30;
    public static final int REDIS_CAS_MAX = 10;

    protected Class<V> cls;

    protected BaseDao<V, K> dbDao;
    protected EntityMeta<V, K> entityMeta;

    public EntityCache(Class<V> cls, EntityMeta<V, K> entityMeta) {
        this.cls = cls;
        this.entityMeta = entityMeta;
    }

    protected void init(int maxSeconds, int maxNums, BaseDao<V, K> dbDao) {
        super.init(maxSeconds, maxNums);
        this.dbDao = dbDao;
    }

    protected void init(BaseDao<V, K> dbDao) {
        super.init(MAX_SECONDS, MAX_NUMS);
        this.dbDao = dbDao;
    }

    /**
     * 注意如果相关唯一建 之内的发生了改变那么.最好先 清空缓存在执行这个函数
     */
    public void replace(final V v) {
        dbDao.replace(v);
        setCache(v);
    }


    /**
     * 先删除缓存,在更新,在重新缓存
     *
     * @param v
     */
    public void update(V v) {
        @SuppressWarnings("unchecked")
        V old = get((K) v.getKey());
        if (old != null) {
            removeCache(v);
        }
        dbDao.update(v);
        setCache(v);
    }

    public void insert(final V v) {
        dbDao.insert(v);
        setCache(v);
    }

    public int del(V v) {
        removeCache(v);
        @SuppressWarnings("unchecked")
        K key = (K) v.getKey();
        return dbDao.del(key);
    }

    /**
     * 设置cache,注意不会写数据库
     */
    protected void setCache(final V v) {
        String stringKey = v.getKey().toStringKey();
        String key = createKey(stringKey);

        template.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations opt) throws DataAccessException {
                opt.multi();
                Map<String, String> strMap = EntityObjectUtils.encodeStringMap(v);
                opt.opsForHash().putAll(key, strMap);

                localCache.put(key, Optional.of(v));

                Map<Key, UniqueInfo> uniques = entityMeta.getUniques();
                for (UniqueInfo u : uniques.values()) {
                    Field[] fields = u.getFields();
                    Object[] objs = new Object[fields.length];
                    for (int i = 0; i < fields.length; i++) {
                        objs[i] = v.get(fields[i]);
                    }
                    String ukey = createUniqueKey(u.getName(), objs);
                    opt.opsForValue().set(ukey, stringKey);
                }
                opt.exec();
                return null;
            }
        });
    }

    public int del(final K k) {
        V v = get(k);
        if (v != null) {
            return del(v);
        }
        return 0;
    }

    protected boolean decrCount(final K k, Field field) {
        return incrCount(k, field, -1);
    }

    protected boolean incrCount(final K k, Field field) {
        return incrCount(k, field, 1);
    }

    protected boolean incrCount(final K k, Field field, int value) {
        int updateCount = dbDao.incrementUpdatePartial(field, value, k);
        if (updateCount > 0) {
            String key = createKey(k.toStringKey());

            localCache.invalidate(key);

            return template.execute(new SessionCallback<Boolean>() {
                @Override
                public Boolean execute(RedisOperations opt) throws DataAccessException {
                    int i = 0;
                    while (true) {
                        //监视key
                        opt.watch(key);
                        //获取数据

                        HashOperations hashOpt = opt.opsForHash();
                        Boolean hexists = hashOpt.hasKey(key, field.getNativeValue());

                        //根据之前获取数据,开始事务操作
                        if (hexists) {

                            opt.multi();
                            hashOpt.increment(key, field, value);
                            List<Object> exec = opt.exec();
                            if (exec != null && !exec.isEmpty()) {
                                //失败重试
                                break;
                            }
                        } else {
                            opt.unwatch();
                            break;
                        }
                        i++;
                        if (i > REDIS_CAS_MAX) {
                            throw new RuntimeException("更新redis 计数出现cas 重复次数超过 最大值:" + REDIS_CAS_MAX);
                        }
                    }
                    return true;
                }
            });
        }
        return false;
    }


    public V get(K k) {
        String key = createKey(k.toStringKey());
        return getByStringKey(key, () -> {
            V v = dbDao.get(k);
            if (v != null) {
                return v;
            } else {
                return null;
            }
        });
    }

    private V getByStringKey(String key, Callable<V> callable) {
        try {
            Callable<Optional<V>> optionalCallable = () -> template.execute(new SessionCallback<Optional<V>>() {
                @Override
                public Optional<V> execute(RedisOperations opt) throws DataAccessException {
                    try {
                        Map<String, String> stringStringMap = opt.opsForHash().entries(key);
                        if (stringStringMap != null && !stringStringMap.isEmpty()) {
                            V v = EntityObjectUtils.decodeStringMap(stringStringMap, newInstance(cls));
                            return Optional.of(v);
                        } else {
                            if (callable != null) {
                                V v = callable.call();
                                if (v != null) {
                                    setCache(v);
                                    return Optional.of(v);
                                }
                            }
                            return Optional.empty();
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            //TODO 隐患
            Optional<V> opt = localCache.get(key, optionalCallable);
            if (!opt.isPresent()) {
                localCache.invalidate(key);
            }
            return opt.orElse(null);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public V getByUnique(Key uniqueName, Object... values) {
        UniqueInfo uniques = entityMeta.getUniques(uniqueName);
        if (uniques == null) {
            throw new RuntimeException("错误的调用,没有对应 uniqueName");
        }
        if (values.length != uniques.getFields().length) {
            throw new RuntimeException("参数数量不对!");
        }
        String ukey = createUniqueKey(uniqueName, values);

        String key = template.opsForValue().get(ukey);
        if (key != null) {
            V v = getByStringKey(key, () -> {
                QueryParams queryParams = QueryParams.create();
                Field[] fields = uniques.getFields();
                for (int i = 0, fieldsLength = fields.length; i < fieldsLength; i++) {
                    Field filed = fields[i];
                    Object value = values[i];
                    queryParams.add(filed, value);
                }
                return dbDao.findObject(queryParams);
            });
            if (v != null) {
                return v;
            }
        }

        //数据库加载!
        QueryParams queryParams = QueryParams.create();
        Field[] fields = uniques.getFields();
        for (int i = 0, fieldsLength = fields.length; i < fieldsLength; i++) {
            Field filed = fields[i];
            Object value = values[i];
            queryParams.add(filed, value);
        }
        V object = dbDao.findObject(queryParams);
        if (object != null) {
            setCache(object);
        }
        return object;
    }

    /**
     * 返回true 表示删除成功
     * 返回false 表示删除失败,删除实体不存在
     */
    public boolean delByUnique(Key uniqueName, Object... values) {
        V v = getByUnique(uniqueName, values);
        return v != null && del(v) > 0;
    }

    public void removeCache(K k) {
        V v = get(k);
        if (v != null) {
            removeCache(v);
        }
    }

    /**
     * 从缓存清楚
     */
    public void removeCache(V v) {
        Map<Key, UniqueInfo> uniques = entityMeta.getUniques();

        String stringKey = v.getKey().toStringKey();
        String key = createKey(stringKey);
        String[] keys = new String[uniques.size() + 1];
        keys[0] = key;


        template.execute(new SessionCallback<Void>() {
            @Override
            @SuppressWarnings("unchecked")
            public Void execute(RedisOperations opt) throws DataAccessException {
                opt.multi();
                localCache.invalidate(key);
                int keyIndex = 0;
                for (UniqueInfo u : uniques.values()) {
                    Field[] fields = u.getFields();
                    Object[] objs = new Object[fields.length];
                    for (int i = 0; i < fields.length; i++) {
                        objs[i] = v.get(fields[i]);
                    }
                    String ukey = createUniqueKey(u.getName(), objs);
                    keys[++keyIndex] = ukey;
                }
                opt.delete(keys);
                opt.exec();
                return null;
            }
        });
    }

//    private String createKey(KeyObject key) {
//        return createKey(key.toStringKey());
//    }

    private String createUniqueKey(Key uniqueName, Object... value) {
        return createKey("#", uniqueName.getNativeValue(), StringUtils.join(value, "$"));
    }


    private V newInstance(Type type) throws IllegalAccessException, InstantiationException {
        if (type.getClass() == Class.class) {
            return ((Class<V>) type).newInstance();
        } else if (type instanceof ParameterizedType) {
            return newInstance(((ParameterizedType) type).getRawType());
        } else {
            throw new RuntimeException("错误的类型!" + type);
        }
    }
}
