package com.turtorial.apidemo.Springboot.tutorial.database;


import com.turtorial.apidemo.Springboot.tutorial.models.Product;
import com.turtorial.apidemo.Springboot.tutorial.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("Macbook Pro 16 inch", 2020, 2400.0, "abcxyz");
                Product productB = new Product("Ipad Air Green", 2021, 599.0, "abcxyz");
                productRepository.save(productA);
                productRepository.save(productB);
            }
        };
    }
}
