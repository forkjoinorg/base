package org.forkjoin.apikit.impl.api.model;


import org.forkjoin.apikit.core.Message;

import java.util.Date;

/**
 * @author zuoge85 on 15/6/17.
 */
@Message
public class TestObject<T> {
    String id;
    /**
     * 签名
     *
     * @see java.lang
     * @see java.lang
     */
    boolean booleanValue;
    int intValue;
    long longValue;
    float floatValue;
    double doubleValue;


    String stringValue;
    byte[] bytesValue;

    Date regDate;


    boolean[] booleanValueArray;
    int[] intValueArray;
    long[] longValueArray;
    float[] floatValueArray;
    double[] doubleValueArray;


    String[] stringValueArray;

    Date[] regDateArray;

    User user;
    User[] users;

    T[] generics;

    TestObject<T>[] genericObjs;

    TestObject<User>[] genericUsers;

    TestObject<T> genericObj;

    T generic;
}
