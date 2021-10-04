package com.klevu.task.service;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

@Singleton
public class DataSourceService implements DataSourceServiceInterface {

    @Value("${data.file}")
    public String SELECTED_DATA_FILE_PATH;

    private static final Logger log = LoggerFactory.getLogger(DataSourceService.class);

    @Override
    public URI getResource() {
        try {
            return DataSourceService.class.getClassLoader().getResource(SELECTED_DATA_FILE_PATH).toURI();
        } catch (URISyntaxException e) {
            log.error("Unable to get resource", e);
        }
        return null;
    }

}
