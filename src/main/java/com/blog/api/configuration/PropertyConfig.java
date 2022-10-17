package com.blog.api.configuration;

import com.blog.api.configuration.properties.DBProperties;
import com.blog.api.configuration.properties.ResourcesProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {

    @Bean
    public DBProperties dbProperties(ResourcesProperties properties) {
        return properties.getDatabase();
    }

}
