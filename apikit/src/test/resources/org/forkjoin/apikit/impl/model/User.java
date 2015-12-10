package org.forkjoin.apikit.example.model;

import org.forkjoin.api.Message;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

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

    /**
     * 年龄
     */
    @Min(14)
    int age;
}
