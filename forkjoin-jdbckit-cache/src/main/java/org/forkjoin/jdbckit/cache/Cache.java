package org.forkjoin.jdbckit.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *
 */
public abstract class Cache<K, V> {
    @Autowired
    protected StringRedisTemplate template;

    protected com.google.common.cache.Cache<K, Optional<V>> localCache;

    private ConcurrentMap<String, com.google.common.cache.Cache> localCacheMap = new ConcurrentHashMap<>();

    protected void init(int maxSeconds, int maxNums) {
        localCache = newLocalCache("default", maxSeconds, maxNums);

        CacheManager.getInstance().reg(this);
    }

    protected <CK, CV> com.google.common.cache.Cache<CK, Optional<CV>> newLocalCache(String name, int maxSeconds, int maxNums) {
        com.google.common.cache.Cache<CK, Optional<CV>> cache = CacheBuilder.newBuilder().expireAfterWrite(
                maxSeconds, TimeUnit.SECONDS
        ).maximumSize(maxNums).build();

        localCacheMap.put(name, cache);

        return cache;
    }

    void clearLocalCache() {
        localCacheMap.forEach((k, v) -> {
            v.invalidateAll();
        });
    }

    CacheInfo dump() {
        CacheInfo info = new CacheInfo();
        info.setName(this.getClass().getSimpleName());


        info.setRedisNums(template.keys(KeyUtils.getKey(this.getClass())).size());

//        Map<String, CacheInfo.LocalInfo> localMap
////        info.setLocalMap();
//        localCacheMap.forEach((k,v)->{
//            v.stats().evictionCount();
//
//        });
        Map<String, CacheInfo.LocalInfo> localMap = localCacheMap.entrySet().parallelStream().map(e -> {
            CacheInfo.LocalInfo localInfo = new CacheInfo.LocalInfo();
            localInfo.setName(e.getKey());

            CacheStats stats = e.getValue().stats();
            localInfo.setEvictionCount(stats.evictionCount());
            localInfo.setHitCount(stats.hitCount());
            localInfo.setLoadExceptionCount(stats.loadExceptionCount());
            localInfo.setLoadSuccessCount(stats.loadSuccessCount());
            localInfo.setMissCount(stats.missCount());
            localInfo.setTotalLoadTime(stats.totalLoadTime());
            return localInfo;
        }).collect(Collectors.toMap(CacheInfo.LocalInfo::getName, v -> v));

        info.setLocalMap(localMap);
        return info;
    }

    public void clearClassKey() {
        innerClearClassKey(KeyUtils.getKey(this.getClass()));
    }

    public void clearClassKey(String fragment0) {
        innerClearClassKey(KeyUtils.getKey(this.getClass(), fragment0));
    }

    private void innerClearClassKey(String startKey) {
        template.execute(new SessionCallback<Void>() {
            @Override
            @SuppressWarnings("unchecked")
            public Void execute(RedisOperations oper) throws DataAccessException {
                Set keys = oper.keys(startKey + "*");
                oper.multi();
                oper.delete(keys);
                oper.exec();
                return null;
            }
        });
        if (localCache != null) {
            localCache.invalidateAll();
        }
    }


    protected String createKey(String value) {
        return KeyUtils.getKey(this.getClass(), value);
    }


    protected String createKey(String fragment0, String fragment1) {
        return KeyUtils.getKey(this.getClass(), fragment0, fragment1);
    }

    protected String createKey(String fragment0, String fragment1, String fragment2) {
        return KeyUtils.getKey(this.getClass(), fragment0, fragment1, fragment2);
    }

    protected String createKey(String fragment0, String fragment1, String fragment2, String fragment3) {
        return KeyUtils.getKey(this.getClass(), fragment0, fragment1, fragment2, fragment3);
    }
//
//    protected void setRedis(String key, byte[] data, int expires_in) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            Transaction multi = jedis.multi();
//            multi.set(SafeEncoder.encode(key), data);
//            multi.expire(key, expires_in);
//            multi.exec();
//        }
//    }
//
//    protected void setRedis(String key, String data, int expires_in) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            Transaction multi = jedis.multi();
//            multi.set(key, data);
//            multi.expire(key, expires_in);
//            multi.exec();
//        }
//    }
}
