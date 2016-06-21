package org.forkjoin.apikit.spring;

/**
 * @author zuoge85 on 15/4/26.
 */
public class AccountRuntimeException extends RuntimeException{
    public AccountRuntimeException() {
    }

    public AccountRuntimeException(String message) {
        super(message);
    }

    public AccountRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountRuntimeException(Throwable cause) {
        super(cause);
    }

    public AccountRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
