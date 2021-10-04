package com.klevu.task.controller;

import com.klevu.task.model.Product;
import com.klevu.task.model.web.Error;
import com.klevu.task.model.web.Products;
import com.klevu.task.model.web.RecommendationResult;
import com.klevu.task.repository.ProductRepository;
import com.klevu.task.service.PreprocessedRecommendationEngine;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import jakarta.inject.Inject;

import java.util.Optional;

@Controller
public class RecommendationController {

    @Inject PreprocessedRecommendationEngine preprocessedRecommendationEngine;
    @Inject ProductRepository productRepository;

    @Get("recommendation/product/{productId}")
    public RecommendationResult data(@PathVariable String productId) {

        Optional<Product> product = productRepository.findById(productId);

        return product
                .map(value -> new RecommendationResult(new Products(preprocessedRecommendationEngine.getRecommendationsByProduct(value))))
                .orElseGet(() -> new RecommendationResult(new Error("PRODUCT_NOT_FOUND", "No product found with given ID")));

    }
}
