package com.example.ecombackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EcomBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomBackendApplication.class, args);
    }

}
