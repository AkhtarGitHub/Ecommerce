package com.ecommerce.model;

public class Seller extends User {
    public Seller(String username, String password, String email) {
        super(username, password, email, "seller");
    }
}
