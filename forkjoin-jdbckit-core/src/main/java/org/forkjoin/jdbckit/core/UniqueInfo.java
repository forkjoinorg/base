package org.forkjoin.jdbckit.core;

import org.forkjoin.jdbckit.core.identifier.Field;
import org.forkjoin.jdbckit.core.identifier.Key;

import java.util.Arrays;

/**
 * 唯一键信息
 */
public class UniqueInfo {
    private Key name;
    private Field[] fields;

    public UniqueInfo() {
    }

    public UniqueInfo(Key name, Field... fields) {
        this.name = name;
        this.fields = fields;
    }

    public Key getName() {
        return name;
    }

    public void setName(Key name) {
        this.name = name;
    }

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "UniqueInfo{" +
                "value='" + name + '\'' +
                ", fields=" + Arrays.toString(fields) +
                '}';
    }
}
