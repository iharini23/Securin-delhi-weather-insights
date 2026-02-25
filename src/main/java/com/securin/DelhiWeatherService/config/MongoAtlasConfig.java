package com.securin.DelhiWeatherService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoAtlasConfig {

    @Bean
    public MongoClient mongoClient(Environment env) {
        String uri = env.getProperty("spring.data.mongodb.uri");
        if (uri == null || uri.isBlank()) {
            throw new IllegalStateException("spring.data.mongodb.uri is missing");
        }
        return MongoClients.create(uri);
    }
}