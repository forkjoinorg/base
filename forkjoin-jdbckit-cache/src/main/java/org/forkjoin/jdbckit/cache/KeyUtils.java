package org.forkjoin.jdbckit.cache;

import java.lang.reflect.Method;

/**
 * @author zuoge85 on 15/6/13.
 */
public class KeyUtils {
    public static final String SEPARATOR = "$";
    public static final String PREFIX = "c";
    public static final String SPRING_CACHE_KEY_PREFIX = "s";

    public static String getKey(Class cls) {
        return PREFIX + cls.getSimpleName();
    }

    public static String getKey(Class cls, String key) {
        return PREFIX + cls.getSimpleName() + SEPARATOR + key;
    }

    public static String getKey(Class cls, String fragment0, String fragment1) {
        return PREFIX + cls.getSimpleName() + SEPARATOR + fragment0 + SEPARATOR + fragment1;
    }

    public static String getKey(Class cls, String fragment0, String fragment1, String fragment2) {
        return PREFIX + cls.getSimpleName() + SEPARATOR + fragment0 + SEPARATOR + fragment1 + SEPARATOR + fragment2;
    }

    public static String getKey(Class cls, String fragment0, String fragment1, String fragment2, String fragment3) {
        return PREFIX + cls.getSimpleName() + SEPARATOR + fragment0 + SEPARATOR + fragment1 + SEPARATOR + fragment2 + SEPARATOR + fragment3;
    }

    public static String getSpringCacheKey(Object target, Method method, Object[] params) {
        StringBuilder sb = new StringBuilder();

        sb.append(SPRING_CACHE_KEY_PREFIX);
        sb.append(SEPARATOR);
        sb.append(target.getClass().getName());
        sb.append(SEPARATOR);
        sb.append(method.getName());
        for (Object obj : params) {
            sb.append(SEPARATOR);
            sb.append(obj.toString());
        }
        return sb.toString();
    }

//    public static String getKey(Class cls,String ...fragments){
//
//
//        return cls.getSimpleName()  + "$" + fragment0 + "$" + fragment1;
//    }
}
