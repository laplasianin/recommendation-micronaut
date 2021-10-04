package com.klevu.task.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PreprocessedRecommendationsByProduct {

    @Id
    private Long id;

    @OneToOne()
    private Product product;

    @OneToOne
    private Product buyWithProduct;

    public Integer weight;

    public Long getId() {
        return id;
    }

    public PreprocessedRecommendationsByProduct setId(Long id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public PreprocessedRecommendationsByProduct setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Product getBuyWithProduct() {
        return buyWithProduct;
    }

    public PreprocessedRecommendationsByProduct setBuyWithProduct(Product buyWithProduct) {
        this.buyWithProduct = buyWithProduct;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public PreprocessedRecommendationsByProduct setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }
}