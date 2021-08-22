package com.borokali.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.borokali.persistence.mongo.*")
@Configuration
public class MongodbConfig {

}
