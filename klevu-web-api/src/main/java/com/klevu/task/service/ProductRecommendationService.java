package com.klevu.task.service;

import com.klevu.task.client.RecommendationClient;
import com.klevu.task.client.EtlClient;
import com.klevu.task.model.web.Products;
import com.klevu.task.model.web.Result;
import io.micronaut.retry.annotation.CircuitBreaker;
import io.micronaut.retry.annotation.Fallback;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ProductRecommendationService {

    @Inject RecommendationClient recommendationClient;
    @Inject EtlClient etlClient;

    @CircuitBreaker
    public Result<Products> getRecommendationsByProduct(String productId) {
        return recommendationClient.getRecommendations(productId);
    }

    @Fallback
    public Result<Products> getRecommendationsByProduct(String productId, Exception e) {
        return new Result<>();
    }

    public Result<Products> extractData() {
        return etlClient.extractData();
    }
}
