package org.forkjoin.apikit.example.model;


import org.forkjoin.apikit.core.Message;

import java.util.Date;
import java.util.List;

/**
 * @author zuoge85 on 15/6/17.
 */
@Message
public class TestObjectList<T> {
    private String id;
    /**
     * 签名
     *
     * @see java.lang
     * @see java.lang
     */
    private boolean booleanValue;
    private int intValue;
    private long longValue;
    private float floatValue;
    private double doubleValue;


    private String stringValue;
    private List<Byte> bytesValue;

    private  Date regDate;


    private List<Boolean> booleanValueArray;
    private List<Integer> intValueArray;
    private List<Long> longValueArray;
    private List<Float> floatValueArray;
    private List<Double> doubleValueArray;


    private List<String> stringValueArray;

    private List<Date> regDateArray;

    private User user;
    private List<User> users;

    private List<T> generics;

    private List<TestObjectList<T>> genericObjs;

    private List<TestObjectList<User>> genericUsers;

    private TestObjectList<T> genericObj;

    private T generic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public void setLongValue(long longValue) {
        this.longValue = longValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public List<Byte> getBytesValue() {
        return bytesValue;
    }

    public void setBytesValue(List<Byte> bytesValue) {
        this.bytesValue = bytesValue;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public List<Boolean> getBooleanValueArray() {
        return booleanValueArray;
    }

    public void setBooleanValueArray(List<Boolean> booleanValueArray) {
        this.booleanValueArray = booleanValueArray;
    }

    public List<Integer> getIntValueArray() {
        return intValueArray;
    }

    public void setIntValueArray(List<Integer> intValueArray) {
        this.intValueArray = intValueArray;
    }

    public List<Long> getLongValueArray() {
        return longValueArray;
    }

    public void setLongValueArray(List<Long> longValueArray) {
        this.longValueArray = longValueArray;
    }

    public List<Float> getFloatValueArray() {
        return floatValueArray;
    }

    public void setFloatValueArray(List<Float> floatValueArray) {
        this.floatValueArray = floatValueArray;
    }

    public List<Double> getDoubleValueArray() {
        return doubleValueArray;
    }

    public void setDoubleValueArray(List<Double> doubleValueArray) {
        this.doubleValueArray = doubleValueArray;
    }

    public List<String> getStringValueArray() {
        return stringValueArray;
    }

    public void setStringValueArray(List<String> stringValueArray) {
        this.stringValueArray = stringValueArray;
    }

    public List<Date> getRegDateArray() {
        return regDateArray;
    }

    public void setRegDateArray(List<Date> regDateArray) {
        this.regDateArray = regDateArray;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<T> getGenerics() {
        return generics;
    }

    public void setGenerics(List<T> generics) {
        this.generics = generics;
    }

    public List<TestObjectList<T>> getGenericObjs() {
        return genericObjs;
    }

    public void setGenericObjs(List<TestObjectList<T>> genericObjs) {
        this.genericObjs = genericObjs;
    }

    public List<TestObjectList<User>> getGenericUsers() {
        return genericUsers;
    }

    public void setGenericUsers(List<TestObjectList<User>> genericUsers) {
        this.genericUsers = genericUsers;
    }

    public TestObjectList<T> getGenericObj() {
        return genericObj;
    }

    public void setGenericObj(TestObjectList<T> genericObj) {
        this.genericObj = genericObj;
    }

    public T getGeneric() {
        return generic;
    }

    public void setGeneric(T generic) {
        this.generic = generic;
    }

    @Override
    public String toString() {
        return "TestObjectList{" +
                "id='" + id + '\'' +
                ", booleanValue=" + booleanValue +
                ", intValue=" + intValue +
                ", longValue=" + longValue +
                ", floatValue=" + floatValue +
                ", doubleValue=" + doubleValue +
                ", stringValue='" + stringValue + '\'' +
                ", bytesValue=" + bytesValue +
                ", regDate=" + regDate +
                ", booleanValueArray=" + booleanValueArray +
                ", intValueArray=" + intValueArray +
                ", longValueArray=" + longValueArray +
                ", floatValueArray=" + floatValueArray +
                ", doubleValueArray=" + doubleValueArray +
                ", stringValueArray=" + stringValueArray +
                ", regDateArray=" + regDateArray +
                ", user=" + user +
                ", users=" + users +
                ", generics=" + generics +
                ", genericObjs=" + genericObjs +
                ", genericUsers=" + genericUsers +
                ", genericObj=" + genericObj +
                ", generic=" + generic +
                '}';
    }
}
