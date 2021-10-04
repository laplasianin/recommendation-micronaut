package com.klevu.task.controller;

import com.klevu.task.model.web.Products;
import com.klevu.task.model.web.Result;
import com.klevu.task.service.ProductRecommendationService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.inject.Inject;

@Controller
public class RecommendationController {

    @Inject ProductRecommendationService productRecommendationService;

    @Get("recommendation/product/{productId}")
    @Operation(summary = "Get recommendation",
            description = "Get recommendation by a given product"
    )
    public Result<Products> data(@PathVariable String productId) {

        return productRecommendationService.getRecommendationsByProduct(productId);

    }


}
