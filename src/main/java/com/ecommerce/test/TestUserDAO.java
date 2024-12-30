package com.ecommerce.test;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.Buyer;
import com.ecommerce.model.Seller;
import com.ecommerce.model.User;

public class TestUserDAO {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // Register a new buyer
        User buyer = new Buyer("buyer123", "password123", "buyer@example.com");
        userDAO.registerUser(buyer);
        System.out.println("Buyer registered successfully!");

        // Register a new seller
        User seller = new Seller("seller123", "password123", "seller@example.com");
        userDAO.registerUser(seller);
        System.out.println("Seller registered successfully!");

        // Find a user by username
        User foundUser = userDAO.findByUsername("buyer123");
        if (foundUser != null) {
            System.out.println("Found user: " + foundUser.getUsername() + ", Role: " + foundUser.getRole());
        }

        // Delete a user
        boolean deleted = userDAO.deleteUser("buyer123");
        System.out.println("User deleted: " + deleted);
    }
}
