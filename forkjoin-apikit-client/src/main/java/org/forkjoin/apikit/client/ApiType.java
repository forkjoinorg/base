package org.forkjoin.apikit.client;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author zuoge85@gmail.com on 2016/11/23.
 */
public class ApiType implements ParameterizedType {
    private final Type[] types;
    private final Type raw;

    public ApiType(Type raw, Type... types) {
        this.raw = raw;
        this.types = types;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return types;
    }

    @Override
    public Type getRawType() {
        return raw;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
