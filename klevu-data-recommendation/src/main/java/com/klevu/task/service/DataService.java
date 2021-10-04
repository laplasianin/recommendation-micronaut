package com.klevu.task.service;

import com.klevu.task.model.PreprocessedRecommendationsByProduct;
import com.klevu.task.model.Product;
import com.klevu.task.repository.PreprocessedRecommendationsByProductRepository;
import com.klevu.task.repository.ProductRepository;
import jakarta.inject.Inject;

import javax.transaction.Transactional;

public class DataService {

    @Inject ProductRepository productRepository;
    @Inject PreprocessedRecommendationsByProductRepository preprocessedRecommendationsByProductRepository;

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Product createProduct(String id, String name) {
        Product product = new Product()
                .setId(id)
                .setName(name);

        Product product1 = productRepository.save(product);
        return product1;
    }

    @Transactional
    public PreprocessedRecommendationsByProduct createPreprocessedRecommendation(Long id, Product product, Product buyWithProduct, Integer weight) {
        PreprocessedRecommendationsByProduct p1 = new PreprocessedRecommendationsByProduct()
                .setId(id)
                .setProduct(product)
                .setBuyWithProduct(buyWithProduct)
                .setWeight(weight);
        return preprocessedRecommendationsByProductRepository.save(p1);
    }
}
