package com.turtorial.apidemo.Springboot.tutorial.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// POJO object
@Entity
public class Product {
    // This is "primary key"
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String productName;
    private int productYear;
    private double productPrice;
    private String productUrl;

    public Product(String productName, int year, double price, String url) {
        this.productName = productName;
        this.productYear = year;
        this.productPrice = price;
        this.productUrl = url;
    }

    public Product() {}

    @Override
    public String toString() {
        return "Product{" +
                "id = " + id +
                ", productName='" + productName + '\'' +
                ", year=" + productYear +
                ", price=" + productPrice +
                ", url='" + productUrl + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYear() {
        return productYear;
    }

    public void setYear(int year) {
        this.productYear = year;
    }

    public double getPrice() {
        return productPrice;
    }

    public void setPrice(double price) {
        this.productPrice = price;
    }

    public String getUrl() {
        return productUrl;
    }

    public void setUrl(String url) {
        this.productUrl = url;
    }
}
