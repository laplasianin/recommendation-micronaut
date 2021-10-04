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
public class EtlController {

    @Inject ProductRecommendationService productRecommendationService;

    @Get("extract-data")
    @Operation(summary = "Extact data from from test source",
            description = "Dislaimer: one time operation, coud not be completed twice"
    )
    public void extractData() {

        productRecommendationService.extractData();

    }


}
