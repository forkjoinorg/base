package com.forkjoin.spring;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.Transient;
import java.util.Map;

/**
 * Created by zuoge85 on 15/4/17.
 * <p>
 * {"status":1, "msg":"请求被拒绝", “data”:””}
 * status 0 表示成功 大于1 表示错误！
 * 错误 999表示必须重新登录！
 * msg 错误消息
 * data 协议特定字符串’
 */
public class Result<T> {

    public static final int SUCCESS = 0;
    public static final int VALIDATOR = 1;
    public static final int ERROR = 2;
    public static final int ACCOUNT_ERROR = -1;

    public static final String FIELD_STATUS = "status";
    public static final String FIELD_MSG = "msg";
    public static final String FIELD_DATA = "data";

    public static <T> Result<T> createSuccess() {
        return new Result<>();
    }

    public static <T> Result<T> createSuccess(T data) {
        return new Result<>(SUCCESS, null, data);
    }

    public static <T> Result<T> createError(int status, String msg) {
        return new Result<>(status, msg, null);
    }

    public static <T> Result<T> createError(String msg) {
        return new Result<>(ACCOUNT_ERROR, msg, null);
    }


    private int status;
    private String msg;
    private T data;
    private Map<String,Object> msgMap;

    public Result() {

    }


    public Result(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @JsonIgnore
    @Transient
    public boolean isSuccess() {
        return status == SUCCESS;
    }


    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", msgMap=" + msgMap +
                '}';
    }

    public Map<String, Object> getMsgMap() {
        return msgMap;
    }

    public void setMsgMap(Map<String, Object> msgMap) {
        this.msgMap = msgMap;
    }

    @SuppressWarnings("unchecked")
    public <R> Result<R> to() {
        if(this instanceof I18nResult){
            return (Result<R>) this;
        }
        return Result.createError(status, msg);
    }
}
