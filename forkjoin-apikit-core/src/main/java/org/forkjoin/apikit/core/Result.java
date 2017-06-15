package org.forkjoin.apikit.core;

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

    /**请求成功*/
    public static final int SUCCESS = 0;
    /**参数校验错误*/
    public static final int VALIDATOR = 1;
    /**服务器错误*/
    public static final int ERROR = 2;
    /**需要登录*/
    public static final int ACCOUNT_ERROR = -1;
    /**客户端异常*/
    public static final int CLIENT_ERROR = -2;
    /**客户取消*/
    public static final int CLIENT_CANCEL = -3;


    public static final String FIELD_STATUS = "status";

    public static final String FIELD_MSG = "msg";

    public static final String FIELD_DATA = "data";

    public static <T> Result<T> createSuccess() {
        return new Result<>();
    }

    public static <T> Result<T> createSuccess(T data) {
        return new Result<>(SUCCESS, null, data);
    }

    public static <T> Result<T> createError(Exception ex) {
        Result<T> result = new Result<>();
        result.setStatus(ERROR);
        result.setException(ex);
        return result;
    }

    public static <T> Result<T> createError(Exception ex, String msg) {
        Result<T> result = createError(ex);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> createError(int status, String msg) {
        return new Result<>(status, msg, null);
    }

    public static <T> Result<T> createError(String msg) {
        return new Result<>(ERROR, msg, null);
    }

    public static <T> Result<T> createCancel() {
        Result<T> result = new Result<>();
        result.setStatus(CLIENT_CANCEL);
        return result;
    }


    /**状态字段*/
    private int status;

    /**返回消息*/
    private String msg;
    /**返回数据*/
    private T data;
    /**返回消息map,一眼封装参数错误*/
    private Map<String,Object> msgMap;

    private transient Exception exception;

    public Result() {

    }

    public Result(int status) {
        this.status = status;
    }

    public Result(int status,T data) {
        this.status = status;
        this.data = data;
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


    @Transient
    public boolean isSuccess() {
        return status == SUCCESS;
    }

    @Transient
    public boolean isClientError() {
        return status == SUCCESS;
    }

    @Transient
    public Exception getException() {
        return exception;
    }

    @Transient
    public void setException(Exception exception) {
        this.exception = exception;
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
}
