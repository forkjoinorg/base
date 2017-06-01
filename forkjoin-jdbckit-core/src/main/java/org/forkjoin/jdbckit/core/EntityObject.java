package org.forkjoin.jdbckit.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.forkjoin.jdbckit.core.identifier.Field;

import java.beans.Transient;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现一系列函数,
 * dao通过这些函数让异步存储逻辑变成存储队列,这样可以避免脏数据保存到内存!
 *
 * @author zuoge85
 */
public abstract class EntityObject<T extends EntityObject, K extends KeyObject> {
    private static final long serialVersionUID = -8281724195431238638L;

    @JsonIgnore
    @Transient
    public abstract K getKey();

    @JsonIgnore
    @Transient
    public abstract EntityMeta<T, K> getEntityMeta();


    @JsonIgnore
    transient private AtomicInteger entityVersion = new AtomicInteger();


    @JsonIgnore
    @Transient
    public boolean isEntityChange(int version) {
        return entityVersion.get() != version;
    }

    @JsonIgnore
    @Transient
    protected void changeProperty(String name, Object o) {
        entityVersion.incrementAndGet();
    }

    @JsonIgnore
    @Transient
    public int getEntityVersion() {
        return entityVersion.get();
    }

    /**
     * 可选函数
     */
    public abstract void setKey(Object key);

    public abstract Object get(Field dbName);
    public abstract boolean set(Field dbName, Object obj);

    public abstract T newInstance();


}