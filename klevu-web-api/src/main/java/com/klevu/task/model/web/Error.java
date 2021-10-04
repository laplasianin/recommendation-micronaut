package com.klevu.task.model.web;

public class Error {

    private String code;
    private String message;

    public Error() {
    }

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Error setCode(String code) {
        this.code = code;
        return this;
    }

    public Error setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}