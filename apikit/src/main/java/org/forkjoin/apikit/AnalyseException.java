package org.forkjoin.apikit;

/**
 * @author zuoge85 on 15/12/8.
 */
public class AnalyseException extends  RuntimeException{
    public AnalyseException() {
    }

    public AnalyseException(String message) {
        super(message);
    }

    public AnalyseException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnalyseException(Throwable cause) {
        super(cause);
    }

    public AnalyseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
