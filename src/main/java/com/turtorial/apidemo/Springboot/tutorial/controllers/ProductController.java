package com.turtorial.apidemo.Springboot.tutorial.controllers;

import com.turtorial.apidemo.Springboot.tutorial.models.Product;
import com.turtorial.apidemo.Springboot.tutorial.models.ResponseObject;
import com.turtorial.apidemo.Springboot.tutorial.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    // DI = Dependency Injection
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("")
    // this request is: http://localhost:8080/api/v1/Products
    List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    //Get detail product
    @GetMapping("/{id}")
    // Let's return an object with: data, message, status
    ResponseEntity<ResponseObject> getProductById(@PathVariable Long id){
        Optional<Product> foundProduct = productRepository.findById(id);

        return foundProduct.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query product successfully", foundProduct.get())
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Cannot find product with id =" + id, null)
            );
    }

    // Insert new Products with post method
    // Postman: Raw, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
        List<Product> searchProducts = productRepository.findByProductName(newProduct.getProductName().trim());
        if (searchProducts.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product already exists", null)
            );
        }
        // 2 products must not have the same name !!!
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert product successfully", productRepository.save(newProduct))
        );
    }


    // Update, upsert = update if found, otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@PathVariable Long id, @RequestBody Product newProduct){
        Product updatedProduct = productRepository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName().trim());
                    product.setPrice(newProduct.getPrice());
                    product.setYear(newProduct.getYear());
                    product.setUrl(newProduct.getUrl());
                    return productRepository.save(product);
                }).orElseGet(()->{
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update product successfully", updatedProduct)
        );
    }

    //Delete a Product => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        Boolean exist = productRepository.existsById(id);
        if (exist){
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete product successfully", null)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("false", "Cannot find product with id =" + id, null)
        );
    }


    // NOW WE WILL CONNECT TO MYSQL
}
