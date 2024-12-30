package com.ecommerce;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;
import com.ecommerce.util.DBConnection;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductDAOTest {

    private ProductDAO productDAO;

    @BeforeAll
    public void setUpDatabase() {
        // Initialize database connection for testing
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Clean up table and create a fresh one for testing
            stmt.execute("DROP TABLE IF EXISTS products");
            stmt.execute("CREATE TABLE products (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "price NUMERIC(10, 2), " +
                    "quantity INTEGER, " +
                    "seller_id INTEGER" +
                    ")");
            System.out.println("Test database setup completed.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to set up database for tests: " + e.getMessage(), e);
        }
    }

    @BeforeEach
    public void setUp() {
        productDAO = new ProductDAO();
    }

    @Test
    @DisplayName("Test Adding a Product")
    public void testAddProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(100.0)); // Fixed: Convert double to BigDecimal
        product.setQuantity(10);
        product.setSellerId(1);

        assertDoesNotThrow(() -> productDAO.addProduct(product));
        System.out.println("Add Product test passed.");
    }

    @Test
    @DisplayName("Test Retrieving All Products")
    public void testGetAllProducts() {
        List<Product> products = assertDoesNotThrow(() -> productDAO.getAllProducts());
        assertNotNull(products, "The product list should not be null.");
        assertFalse(products.isEmpty(), "The product list should not be empty.");
        System.out.println("Get All Products test passed.");
    }

    @Test
    @DisplayName("Test Updating a Product")
    public void testUpdateProduct() {
        Product product = new Product();
        product.setId(1); // Assuming the product ID 1 exists
        product.setName("Updated Product");
        product.setPrice(BigDecimal.valueOf(150.0)); // Fixed: Convert double to BigDecimal
        product.setQuantity(20);

        assertDoesNotThrow(() -> productDAO.updateProduct(product));
        System.out.println("Update Product test passed.");
    }

    @Test
    @DisplayName("Test Deleting a Product")
    public void testDeleteProduct() {
        int productId = 1; // Assuming the product ID 1 exists
        assertDoesNotThrow(() -> productDAO.deleteProduct(productId));
        System.out.println("Delete Product test passed.");
    }

    @AfterAll
    public void tearDownDatabase() {
        // Clean up the database after tests
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("DROP TABLE IF EXISTS products");
            System.out.println("Test database cleaned up.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to clean up database after tests: " + e.getMessage(), e);
        }
    }
}