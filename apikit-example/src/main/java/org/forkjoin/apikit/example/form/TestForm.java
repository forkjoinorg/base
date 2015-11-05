package org.forkjoin.apikit.example.form;

import com.forkjoin.api.Message;
import org.forkjoin.apikit.example.model.TestObject;
import org.forkjoin.apikit.example.model.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 好吧，测试表单
 * <p>
 * <p>
 * <h1>好呀</h1>
 *
 * @author zuoge85 on 15/4/18.
 * @see java.lang
 */
@Message
public class TestForm<T> {
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

    @Length(max = 255)
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
