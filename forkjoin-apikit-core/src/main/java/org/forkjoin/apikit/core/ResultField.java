package org.forkjoin.apikit.core;

public class ResultField {
    private String field;
    private String message;

    public ResultField() {
    }

    public ResultField(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultField{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
