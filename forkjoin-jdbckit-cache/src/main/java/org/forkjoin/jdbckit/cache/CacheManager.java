package org.forkjoin.jdbckit.cache;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * 所有缓存的管理容器
 * Created by zuoge85 on 16/3/9.
 */
public class CacheManager {
    private static final CacheManager instance = new CacheManager();

    private ConcurrentMap<String, Cache> map = new ConcurrentHashMap<>();

    public static CacheManager getInstance() {
        return instance;
    }

    public void reg(Cache kvCache) {
        if (map.put(kvCache.getClass().getSimpleName(), kvCache) != null) {
            throw new Error("不能有同名的缓存!name" + kvCache.getClass().getSimpleName() + ",class:" + kvCache.getClass().getName());
        }
    }

    public List<CacheInfo> dumpInfo(){
        return map.values().parallelStream().map(Cache::dump).collect(Collectors.toList());
    }

    public void clearLocalCache() {
        map.forEach((k, v) -> v.clearLocalCache());
    }

    public boolean clearLocalCache(String name) {
        Cache cache = map.get(name);
        if (cache != null) {
            cache.clearLocalCache();
            return true;
        } else {
            return false;
        }
    }

    public void clearClassCache() {
        map.forEach((k, v) -> v.clearClassKey());
    }

    public boolean clearClassCache(String name) {
        Cache cache = map.get(name);
        if (cache != null) {
            cache.clearClassKey();
            return true;
        } else {
            return false;
        }
    }

}
