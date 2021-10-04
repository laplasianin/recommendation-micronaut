package com.klevu.task;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import org.testcontainers.junit.jupiter.Testcontainers;

import static nu.studer.sample.tables.PreprocessedRecommendationsByProduct.PREPROCESSED_RECOMMENDATIONS_BY_PRODUCT;

@MicronautTest
@Testcontainers
class KlevuDataEtlTest {

    @Inject
    EmbeddedApplication<?> application;

    @Client("/")
    @Inject HttpClient httpClient;

    @Inject DSLContext dsl;

    @Test
    @Property(name = "data.file", value = "test-data.txt")
    void testItWorks() {

        HttpResponse<Object> exchange = httpClient.toBlocking().exchange(HttpRequest.GET("/extract-data"));

        Assertions.assertEquals(3, getRecommendationWeight("1", "3"));
        Assertions.assertEquals(3, getRecommendationWeight("3", "1"));
        Assertions.assertEquals(1, getRecommendationWeight("4", "3"));
        Assertions.assertEquals(2, getRecommendationWeight("1", "2"));
        Assertions.assertEquals(1, getRecommendationWeight("2", "3"));
        Assertions.assertEquals(1, getRecommendationWeight("3", "4"));
        Assertions.assertEquals(1, getRecommendationWeight("1", "4"));
        Assertions.assertEquals(1, getRecommendationWeight("4", "1"));

        Assertions.assertEquals(1, getRecommendationWeight("4", "1"));

    }

    private Integer getRecommendationWeight(String productId, String buyWithProductId) {
        return dsl.selectFrom(PREPROCESSED_RECOMMENDATIONS_BY_PRODUCT)
                .where(PREPROCESSED_RECOMMENDATIONS_BY_PRODUCT.PRODUCT_ID.eq(productId))
                .and(PREPROCESSED_RECOMMENDATIONS_BY_PRODUCT.BUY_WITH_PRODUCT_ID.eq(buyWithProductId))
                .fetchSingle(PREPROCESSED_RECOMMENDATIONS_BY_PRODUCT.WEIGHT);
    }

}
