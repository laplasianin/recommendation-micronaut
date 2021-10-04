package com.klevu.task.model.web;

import java.util.ArrayList;
import java.util.List;

public class RecommendationResult {

    private final Products data;
    private final List<Error> errors;

    public RecommendationResult() {
        data = null;
        errors = new ArrayList<>();
    }

    public RecommendationResult(Products data) {
        this.data = data;
        errors = new ArrayList<>();
    }

    public RecommendationResult(Error error) {
        this.data = null;
        this.errors = List.of(error);
    }

    public RecommendationResult(Products data, List<Error> errors) {
        this.data = data;
        this.errors = errors;
    }

    public Products getData() {
        return data;
    }

    public List<Error> getErrors() {
        return errors;
    }

}