package com.serverstatus.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages="com.serverstatus.domain")
@EnableMongoAuditing
public class MongoConfig extends AbstractMongoConfiguration{
    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost:27017");
    }

    @Override
    protected String getDatabaseName() {
        return "serverstatus";
    }
}