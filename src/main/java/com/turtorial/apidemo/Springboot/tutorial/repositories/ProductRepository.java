package com.turtorial.apidemo.Springboot.tutorial.repositories;

import com.turtorial.apidemo.Springboot.tutorial.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}
