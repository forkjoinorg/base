package org.forkjoin.apikit.example.model;

import org.forkjoin.apikit.core.Message;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
