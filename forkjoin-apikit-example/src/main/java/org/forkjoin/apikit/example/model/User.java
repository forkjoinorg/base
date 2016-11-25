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

    private long id;

    /**
     * 名称允许重复
     */
    @Length(max = 255)
    private String name;

    /**
     * 年龄
     */
    @Min(14)
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
