package com.klevu.task.controller;

import com.klevu.task.model.web.Error;
import com.klevu.task.model.web.Result;
import com.klevu.task.service.DataService;
import com.klevu.task.service.DataSourceService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

import java.net.URI;

@Controller
public class DataController {

    @Inject DataSourceService dataSourceService;
    @Inject DataService dataService;

    @Get("extract-data")
    public Result data() {

        URI uri = dataSourceService.getResource();

        if (uri == null) {
            return new Result(new Error("NOT_FOUND", "RESOURCE NOT FOUND"));
        }

        dataService.extractData(uri);

        return new Result();
    }
}
