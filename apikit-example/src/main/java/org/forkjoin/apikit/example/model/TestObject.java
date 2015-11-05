package org.forkjoin.apikit.example.model;


import com.forkjoin.api.Message;

import java.util.Date;
import java.util.List;

/**
 * @author zuoge85 on 15/6/17.
 */
@Message
public class TestObject<T> {
    String id;
    /**
     * 签名
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


    List<Boolean> booleanValueArray;
    List<Integer> intValueArray;
    List<Long> longValueArray;
    List<Float> floatValueArray;
    List<Double> doubleValueArray;


    List<String> stringValueArray;

    List<Date> regDateArray;

    User user;
    List<User> users;

    List<T> generics;

    List<TestObject<T>> genericObjs;

    List<TestObject<User>> genericUsers;

    TestObject<T> genericObj;

    T generic;
}
