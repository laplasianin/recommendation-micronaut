package com.klevu.task.model.web;

import java.util.ArrayList;
import java.util.List;

public class Result<T> {

    private final T data;
    private final List<Error> errors;

    public Result() {
        data = null;
        errors = new ArrayList<>();
    }

    public Result(T data) {
        this.data = data;
        errors = new ArrayList<>();
    }

    public Result(Error error) {
        this.data = null;
        this.errors = List.of(error);
    }

    public Result(T data, List<Error> errors) {
        this.data = data;
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public List<Error> getErrors() {
        return errors;
    }
}