package com.example.examination.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DbConfiguration {

    @ConfigurationProperties(prefix = "dbconfigs")
    @Bean
    public DBConfigMap dbConfigMap() {
        System.out.println("dbConfigMap");
        return new DBConfigMap();
    }
}
