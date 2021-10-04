package com.klevu.task.repository;

import com.klevu.task.model.Product;
import com.klevu.task.model.ProductPair;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jooq.DSLContext;
import org.jooq.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static nu.studer.sample.tables.PreprocessedRecommendationsByProduct.PREPROCESSED_RECOMMENDATIONS_BY_PRODUCT;
import static nu.studer.sample.tables.Product.PRODUCT;

@Singleton
public class DataRepository {

    @Inject DSLContext dsl;

    public void saveProcessedRecommendations(Map<ProductPair, Integer> data) {
        List<Query> queries = new ArrayList<>();

        data.forEach((k, v) ->
                queries.add(dsl.insertInto(PREPROCESSED_RECOMMENDATIONS_BY_PRODUCT)
                        .set(PREPROCESSED_RECOMMENDATIONS_BY_PRODUCT.PRODUCT_ID, k.getProductId())
                        .set(PREPROCESSED_RECOMMENDATIONS_BY_PRODUCT.BUY_WITH_PRODUCT_ID, k.getBuyWithProductId())
                        .set(PREPROCESSED_RECOMMENDATIONS_BY_PRODUCT.WEIGHT, v)));

        dsl.batch(queries).execute();
    }

    public void saveProducts(Set<Product> products) {
        List<Query> queries = new ArrayList<>();

        products.forEach(product ->
                queries.add(dsl.insertInto(PRODUCT)
                        .set(PRODUCT.ID, product.getId())
                        .set(PRODUCT.NAME, product.getName())));

        dsl.batch(queries).execute();
    }
}
