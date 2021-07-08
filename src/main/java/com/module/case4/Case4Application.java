package com.module.case4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class Case4Application {

    public static void main(String[] args) {
        SpringApplication.run(Case4Application.class, args);
    }

}
