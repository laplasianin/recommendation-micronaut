package com.klevu.task.repository;

import com.klevu.task.model.PreprocessedRecommendationsByProduct;
import com.klevu.task.model.Product;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface PreprocessedRecommendationsByProductRepository extends CrudRepository<PreprocessedRecommendationsByProduct, Long> {

    List<Product> findBuyWithProductByProductOrderByWeightDesc(Product product, Pageable pageable);


}
