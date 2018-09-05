package org.forkjoin.apikit.core;

import java.beans.Transient;
import java.util.List;

/**
 *
 */
public abstract class AbstractResult<T, RF extends ResultField> {

    /**
     * 请求成功
     */
    public static final int SUCCESS = 200;
    /**
     * 参数校验错误
     * "Unprocessable Entity"
     */
    public static final int VALIDATOR = 422;
    /**
     * 服务器错误
     * "Internal Server Error"
     */
    public static final int ERROR = 500;
    /**
     * 需要登录
     * "Unauthorized"
     */
    public static final int ACCOUNT_ERROR = 401;
    /**
     * 客户端异常
     * "Request Timeout"
     */
    public static final int CLIENT_TIMEOUT = 408;


    public static final String FIELD_STATUS = "status";

    public static final String FIELD_MSG = "message";

    public static final String FIELD_DATA = "data";



    /**
     * 状态字段
     */
    private int status;

    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 返回消息map,一眼封装参数错误
     */
    private List<RF> errors;

    private transient Exception exception;

    public AbstractResult() {

    }

    public AbstractResult(int status) {
        this.status = status;
    }

    public AbstractResult(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public AbstractResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
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
                ", message='" + message + '\'' +
                ", data=" + data +
                ", message=" + message +
                '}';
    }

    public List<RF> getErrors() {
        return errors;
    }

    public void setErrors(List<RF> errors) {
        this.errors = errors;
    }
}
