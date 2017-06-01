package org.forkjoin.jdbckit.core;

import com.fasterxml.jackson.core.type.TypeReference;
import org.forkjoin.jdbckit.core.identifier.Field;
import org.forkjoin.jdbckit.core.identifier.Table;

/**
 *
 */
public class EntityProperty {
    private Field dbName;
    private String propertyName;
    private Class type;
    private TypeReference<?> valueTypeRef;

    public EntityProperty() {
    }

    public EntityProperty(Field dbName, String propertyName, Class type,TypeReference<?> valueTypeRef) {
        this.dbName = dbName;
        this.propertyName = propertyName;
        this.type = type;
        this.valueTypeRef = valueTypeRef;
    }

    public Field getDbName() {
        return dbName;
    }

    public void setDbName(Field dbName) {
        this.dbName = dbName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public TypeReference<?> getValueTypeRef() {
        return valueTypeRef;
    }

    public void setValueTypeRef(TypeReference<?> valueTypeRef) {
        this.valueTypeRef = valueTypeRef;
    }


    @Override
    public String toString() {
        return "EntityProperty{" +
                "dbName='" + dbName + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", type=" + type +
                ", valueTypeRef=" + valueTypeRef +
                '}';
    }
}
