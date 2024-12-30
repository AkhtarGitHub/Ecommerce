package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

public class ProductService {

    // Make ProductDAO final since it is not meant to change after initialization
    private final ProductDAO productDAO;

    // Constructor to initialize the ProductDAO dependency
    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    // Method to add a new product
    public boolean addProduct(Product product) {
        try {
            productDAO.addProduct(product);
            return true;
        } catch (Exception e) {
            System.err.println("Error adding product: " + e.getMessage());
            return false;
        }
    }

    // Method to retrieve all products
    public List<Product> getAllProducts() {
        try {
            return productDAO.getAllProducts();
        } catch (Exception e) {
            System.err.println("Error retrieving products: " + e.getMessage());
            return null;
        }
    }

    // Method to update an existing product
    public boolean updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
            return true;
        } catch (Exception e) {
            System.err.println("Error updating product: " + e.getMessage());
            return false;
        }
    }

    // Method to delete a product by ID
    public boolean deleteProduct(int productId) {
        try {
            productDAO.deleteProduct(productId);
            return true;
        } catch (Exception e) {
            System.err.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }

    // Method to get a product by ID
    public Product getProductById(int productId) {
        try {
            return productDAO.getProductById(productId);
        } catch (Exception e) {
            System.err.println("Error retrieving product by ID: " + e.getMessage());
            return null;
        }
    }
}
