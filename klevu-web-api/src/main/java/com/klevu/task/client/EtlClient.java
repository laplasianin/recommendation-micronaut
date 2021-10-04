package com.klevu.task.client;

import com.klevu.task.model.web.Products;
import com.klevu.task.model.web.Result;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;


@Client("${services.etl}")
public interface EtlClient {

    @Get("/extract-data")
    Result<Products> extractData();

}
