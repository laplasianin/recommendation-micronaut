package com.klevu.task.service;

import com.klevu.task.model.Product;
import com.klevu.task.repository.PreprocessedRecommendationsByProductRepository;
import io.micronaut.context.annotation.Value;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class PreprocessedRecommendationEngine implements RecommendationEngine {

    @Value(value = "${recommendation.limit}")
    private Integer recommendationLimit;

    @Inject
    private PreprocessedRecommendationsByProductRepository preprocessedRecommendationsByProductRepository;

    @Override
    public List<Product> getRecommendationsByProduct(Product product) {
        return getRecommendationsByProduct(product, recommendationLimit);
    }

    @Override
    public List<Product> getRecommendationsByProduct(Product product, Integer limit) {
        return preprocessedRecommendationsByProductRepository.findBuyWithProductByProductOrderByWeightDesc(
                product,
                Pageable.from(0, limit)
        );
    }
}
