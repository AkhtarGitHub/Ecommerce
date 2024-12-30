package com.ecommerce.model;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private int sellerId;

    
    // Default Constructor
    public Product() {}

    // Constructor for adding a new product
    public Product(String name, BigDecimal price, int quantity, int sellerId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerId = sellerId;
    }

    // Constructor for updating a product
    public Product(int id, String name, BigDecimal price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    // Override toString for better display of Product info
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price +
            ", quantity=" + quantity + ", sellerId=" + sellerId + "]";
    }
}
