package com.example.universeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.universeapi.api.model")
public class UniVerseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniVerseApiApplication.class, args);
    }

}
