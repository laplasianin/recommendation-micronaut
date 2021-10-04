package com.klevu.task.service;


import com.klevu.task.model.Product;

import java.util.List;

public interface RecommendationEngine {

    List<Product> getRecommendationsByProduct(Product product);

    List<Product> getRecommendationsByProduct(Product product, Integer limit);
}
