package org.forkjoin.jdbckit.cache;

import java.util.Map;

/**
 *
 */
public class CacheInfo {
    private String name;
    private int redisNums;
    private Map<String,LocalInfo> localMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRedisNums() {
        return redisNums;
    }

    public void setRedisNums(int redisNums) {
        this.redisNums = redisNums;
    }

    public Map<String, LocalInfo> getLocalMap() {
        return localMap;
    }

    public void setLocalMap(Map<String, LocalInfo> localMap) {
        this.localMap = localMap;
    }

    public static class LocalInfo{
        private String name;
        private long hitCount;
        private long missCount;
        private long loadSuccessCount;
        private long loadExceptionCount;
        private long totalLoadTime;
        private long evictionCount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getHitCount() {
            return hitCount;
        }

        public void setHitCount(long hitCount) {
            this.hitCount = hitCount;
        }

        public long getMissCount() {
            return missCount;
        }

        public void setMissCount(long missCount) {
            this.missCount = missCount;
        }

        public long getLoadSuccessCount() {
            return loadSuccessCount;
        }

        public void setLoadSuccessCount(long loadSuccessCount) {
            this.loadSuccessCount = loadSuccessCount;
        }

        public long getLoadExceptionCount() {
            return loadExceptionCount;
        }

        public void setLoadExceptionCount(long loadExceptionCount) {
            this.loadExceptionCount = loadExceptionCount;
        }

        public long getTotalLoadTime() {
            return totalLoadTime;
        }

        public void setTotalLoadTime(long totalLoadTime) {
            this.totalLoadTime = totalLoadTime;
        }

        public long getEvictionCount() {
            return evictionCount;
        }

        public void setEvictionCount(long evictionCount) {
            this.evictionCount = evictionCount;
        }
    }
}
