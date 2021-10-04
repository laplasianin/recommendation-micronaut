package com.klevu.task;

import com.klevu.task.model.PreprocessedRecommendationsByProduct;
import com.klevu.task.model.Product;
import com.klevu.task.model.web.RecommendationResult;
import com.klevu.task.repository.ProductRepository;
import com.klevu.task.service.DataService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest(transactional = false)
class KlevuDataRecommendationTest {

    @Inject
    EmbeddedApplication<?> application;

    @Client("/")
    @Inject HttpClient httpClient;

    @Inject DataService dataService;
    @Inject ProductRepository productRepository;

    @Test
    void testNotExistingProduct() {
        // WHEN:
        HttpResponse<RecommendationResult> result = httpClient.toBlocking().exchange("recommendation/product/unknown", RecommendationResult.class);

        // THEN:
        RecommendationResult body = result.body();

        // PRODUCT NOT FOUND ERROR
        Assertions.assertEquals(1, body.getErrors().size());
        Assertions.assertEquals("PRODUCT_NOT_FOUND", body.getErrors().get(0).getCode());

        // DATA IS EMPTY
        Assertions.assertNull(body.getData());
    }

    @Test
    void test3Recommendations() {
        // GIVEN:
        Product product1 = dataService.createProduct("1", "p1");
        Product product2 = dataService.createProduct("2", "p2");
        Product product3 = dataService.createProduct("3", "p3");
        Product product4 = dataService.createProduct("4", "p4");

        PreprocessedRecommendationsByProduct p1 = dataService.createPreprocessedRecommendation(100L, product1, product2, 3);
        PreprocessedRecommendationsByProduct p2 = dataService.createPreprocessedRecommendation(200L, product1, product3, 1);
        PreprocessedRecommendationsByProduct p3 = dataService.createPreprocessedRecommendation(300L, product1, product4, 5);
        PreprocessedRecommendationsByProduct p4 = dataService.createPreprocessedRecommendation(400L, product2, product3, 999);

        // WHEN:
        HttpResponse<RecommendationResult> result = httpClient.toBlocking().exchange("recommendation/product/1", RecommendationResult.class);

        // THEN:
        RecommendationResult body = result.body();

        // NO ERRORS
        Assertions.assertTrue(body.getErrors().isEmpty());

        // DATA CONTAINS 3 PRODUCTS
        List<Product> products = body.getData().getProducts();
        Assertions.assertEquals(3, products.size());

        Assertions.assertEquals("4", products.get(0).getId());
        Assertions.assertEquals("2", products.get(1).getId());
        Assertions.assertEquals("3", products.get(2).getId());
    }

    @Test
    void testMoreLimitedRecommendations() {
        // GIVEN:
        Product product1 = dataService.createProduct("1p", "p1");
        Product product2 = dataService.createProduct("2p", "p2");
        Product product3 = dataService.createProduct("3p", "p3");
        Product product4 = dataService.createProduct("4p", "p4");
        Product product5 = dataService.createProduct("5p", "p5");
        Product product6 = dataService.createProduct("6p", "p6");
        Product product7 = dataService.createProduct("7p", "p7");
        Product product8 = dataService.createProduct("8p", "p8");

        PreprocessedRecommendationsByProduct p1 = dataService.createPreprocessedRecommendation(10L, product1, product2, 3);
        PreprocessedRecommendationsByProduct p2 = dataService.createPreprocessedRecommendation(20L, product1, product3, 1);
        PreprocessedRecommendationsByProduct p3 = dataService.createPreprocessedRecommendation(30L, product1, product4, 5);
        PreprocessedRecommendationsByProduct p4 = dataService.createPreprocessedRecommendation(40L, product1, product5, 7);
        PreprocessedRecommendationsByProduct p5 = dataService.createPreprocessedRecommendation(50L, product1, product6, 9);
        PreprocessedRecommendationsByProduct p6 = dataService.createPreprocessedRecommendation(60L, product1, product7, 10);
        PreprocessedRecommendationsByProduct p7 = dataService.createPreprocessedRecommendation(70L, product2, product5, 999);

        // WHEN:
        HttpResponse<RecommendationResult> result = httpClient.toBlocking().exchange("recommendation/product/1p", RecommendationResult.class);

        // THEN:
        RecommendationResult body = result.body();

        // NO ERRORS
        Assertions.assertTrue(body.getErrors().isEmpty());

        // DATA CONTAINS 5 PRODUCTS WITH BIGGEST WEIGHT SORTED DESC
        List<Product> products = body.getData().getProducts();
        Assertions.assertEquals(5, products.size());

        Assertions.assertEquals("7p", products.get(0).getId());
        Assertions.assertEquals("6p", products.get(1).getId());
        Assertions.assertEquals("5p", products.get(2).getId());
        Assertions.assertEquals("4p", products.get(3).getId());
        Assertions.assertEquals("2p", products.get(4).getId());
    }


    @Test
    void testEmptyRecommendations() {
        // GIVEN:
        Product product1 = dataService.createProduct("1po", "p1");
        Product product2 = dataService.createProduct("2po", "p2");
        Product product3 = dataService.createProduct("3po", "p3");

        PreprocessedRecommendationsByProduct p1 = dataService.createPreprocessedRecommendation(1L, product1, product2, 3);

        // WHEN:
        HttpResponse<RecommendationResult> result = httpClient.toBlocking().exchange("recommendation/product/3po", RecommendationResult.class);

        // THEN:
        RecommendationResult body = result.body();

        // NO ERRORS
        Assertions.assertTrue(body.getErrors().isEmpty());

        // NO PRODUCTS
        Assertions.assertNull(body.getData().getProducts());
    }

}
