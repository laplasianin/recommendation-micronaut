package com.klevu.task;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Klevu recommendation API",
                version = "0.0",
                description = "Klevu recommendation API"
        )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
