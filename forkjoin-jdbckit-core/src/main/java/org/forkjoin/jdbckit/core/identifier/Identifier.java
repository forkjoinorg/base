package org.forkjoin.jdbckit.core.identifier;

import org.forkjoin.jdbckit.core.SqlUtils;
import org.forkjoin.jdbckit.core.select.*;

/**
 * @author zuoge85@gmail.com on 2017/5/23.
 */
public class Identifier {
    /**
     * 处理后的值，直接可以进入sql 使用
     * 比如 `name` 或者 * 之内的
     */
    protected String value;

    /**
     * 标识符的真实值，不带 ``
     */
    protected String nativeValue;


    protected Identifier(String nativeValue) {
        this.value = "`" + SqlUtils.nameFilter(nativeValue) + "`";
        this.nativeValue = nativeValue;
    }

    protected Identifier(String value, String nativeValue) {
        this.value = value;
        this.nativeValue = nativeValue;
    }

    /**
     * 处理后的值，直接可以进入sql 使用
     * 比如 `name` 或者 * 之内的
     */
    public String getValue() {
        return value;
    }

    /**
     * 标识符的真实值，不带 ``
     */
    public String getNativeValue() {
        return nativeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identifier that = (Identifier) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    /**
     * 处理后的值，直接可以进入sql 使用
     * 比如 `name` 或者 * 之内的
     */
    @Override
    public String toString() {
        return value;
    }
}
