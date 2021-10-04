package com.klevu.task.client;

import com.klevu.task.model.web.Products;
import com.klevu.task.model.web.Result;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;

@Client("${services.recommendation}")
public interface RecommendationClient {

    @Get("/recommendation/product/{productId}")
    Result<Products> getRecommendations(@PathVariable String productId);

}
