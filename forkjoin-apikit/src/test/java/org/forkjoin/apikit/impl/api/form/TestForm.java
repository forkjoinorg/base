package org.forkjoin.apikit.impl.api.form;

import org.forkjoin.apikit.core.Message;
import org.forkjoin.apikit.impl.api.model.User;
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
    List<Date> regDates;
    Date[] regDateArray;
    //
    List<T> childs;
    T generic;
    String id;

    /**
     * booleanValue
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

    List<User> users;


    boolean[] booleanValueArray;
    int[] intValueArray;
    long[] longValueArray;
    float[] floatValueArray;
    double[] doubleValueArray;

    String[] stringValueArray;
//

}
