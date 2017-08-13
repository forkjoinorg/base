package org.forkjoin.apikit.impl.api.model;

import org.forkjoin.apikit.core.Message;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import java.util.ArrayList;

/**
 * 用户信息
 */
@Message
public class User {

    long id;

    /**
     * 名称允许重复
     */
    @Length(max = 255)
    String name;
    String[] names;
    ArrayList<String> nameList;

    /**
     * 年龄
     */
    @Min(14)
    int age;

    TestObject testObject;
}
