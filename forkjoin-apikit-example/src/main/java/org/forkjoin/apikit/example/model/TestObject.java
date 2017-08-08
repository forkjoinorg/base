package org.forkjoin.apikit.example.model;


import org.forkjoin.apikit.core.Message;
import org.forkjoin.apikit.example.form.TestForm;

import java.util.Date;

/**
 * @author zuoge85 on 15/6/17.
 */
@Message
public class TestObject<T> {
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
    private byte[] bytesValue;

    private  Date regDate;


    private boolean[] booleanValueArray;
    private int[] intValueArray;
    private long[] longValueArray;
    private float[] floatValueArray;
    private double[] doubleValueArray;


    private String[] stringValueArray;

    private Date[] regDateArray;

    private User user;
    private User[] users;

    private T[] generics;

    private TestObject<T>[] genericObjs;

    private TestObject<User>[] genericUsers;

    private TestObject<T> genericObj;

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

    public byte[] getBytesValue() {
        return bytesValue;
    }

    public void setBytesValue(byte[] bytesValue) {
        this.bytesValue = bytesValue;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public boolean[] getBooleanValueArray() {
        return booleanValueArray;
    }

    public void setBooleanValueArray(boolean[] booleanValueArray) {
        this.booleanValueArray = booleanValueArray;
    }

    public int[] getIntValueArray() {
        return intValueArray;
    }

    public void setIntValueArray(int[] intValueArray) {
        this.intValueArray = intValueArray;
    }

    public long[] getLongValueArray() {
        return longValueArray;
    }

    public void setLongValueArray(long[] longValueArray) {
        this.longValueArray = longValueArray;
    }

    public float[] getFloatValueArray() {
        return floatValueArray;
    }

    public void setFloatValueArray(float[] floatValueArray) {
        this.floatValueArray = floatValueArray;
    }

    public double[] getDoubleValueArray() {
        return doubleValueArray;
    }

    public void setDoubleValueArray(double[] doubleValueArray) {
        this.doubleValueArray = doubleValueArray;
    }

    public String[] getStringValueArray() {
        return stringValueArray;
    }

    public void setStringValueArray(String[] stringValueArray) {
        this.stringValueArray = stringValueArray;
    }

    public Date[] getRegDateArray() {
        return regDateArray;
    }

    public void setRegDateArray(Date[] regDateArray) {
        this.regDateArray = regDateArray;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public T[] getGenerics() {
        return generics;
    }

    public void setGenerics(T[] generics) {
        this.generics = generics;
    }

    public TestObject<T>[] getGenericObjs() {
        return genericObjs;
    }

    public void setGenericObjs(TestObject<T>[] genericObjs) {
        this.genericObjs = genericObjs;
    }

    public TestObject<User>[] getGenericUsers() {
        return genericUsers;
    }

    public void setGenericUsers(TestObject<User>[] genericUsers) {
        this.genericUsers = genericUsers;
    }

    public TestObject<T> getGenericObj() {
        return genericObj;
    }

    public void setGenericObj(TestObject<T> genericObj) {
        this.genericObj = genericObj;
    }

    public T getGeneric() {
        return generic;
    }

    public void setGeneric(T generic) {
        this.generic = generic;
    }
}
