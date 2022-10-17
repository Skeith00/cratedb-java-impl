package com.blog.api.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationProperties(prefix = "application.resources")
@ConfigurationPropertiesScan
public class ResourcesProperties {

    private DBProperties database;

    public DBProperties getDatabase() {
        return database;
    }

    public void setDatabase(DBProperties database) {
        this.database = database;
    }
}
