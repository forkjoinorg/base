package org.forkjoin.apikit.example.model;

import com.forkjoin.api.Message;

/**
 * 用户信息
 */
@Message
public class User {

    long id;

    /**
     * 名称允许重复
     */
    String name;

    /**
     * 座机电话
     */
    String telephone;
    /**
     * 手机电话
     */
    String mobile;

}
