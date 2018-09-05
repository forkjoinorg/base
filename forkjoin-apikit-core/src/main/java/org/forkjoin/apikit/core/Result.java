package org.forkjoin.apikit.core;

import java.beans.Transient;
import java.util.List;
import java.util.Map;

/**
 *
 */
public  class Result<T> extends AbstractResult<T,ResultField>{
    public Result() {
        super(SUCCESS);
    }

    public Result(int status) {
        super(status);
    }

    public Result(int status, T data) {
        super(status, data);
    }

    public Result(int status, String message, T data) {
        super(status, message, data);
    }

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
        result.setMessage(msg);
        return result;
    }

    public static <T> Result<T> createError(int status, String msg) {
        return new Result<>(status, msg, null);
    }

    public static <T> Result<T> createError(String msg) {
        return new Result<>(ERROR, msg, null);
    }

}
