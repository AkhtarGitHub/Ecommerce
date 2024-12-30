package com.ecommerce;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

public class App {
    private static final UserDAO userDAO = new UserDAO();
    private static final ProductDAO productDAO = new ProductDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- E-Commerce Platform ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Select role (buyer/seller/admin):");
        String role = scanner.nextLine().toLowerCase();

        User user = new User(username, password, email, role);
        try {
            userDAO.registerUser(user);
            System.out.println("User registered successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void loginUser() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        User user = userDAO.findByUsername(username);

        if (user != null) {
            System.out.println("Login successful. Welcome, " + user.getUsername() + "!");
            showMenuBasedOnRole(user);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void showMenuBasedOnRole(User user) {
        switch (user.getRole().toLowerCase()) {
            case "buyer" -> showBuyerMenu();
            case "seller" -> showSellerMenu();
            case "admin" -> showAdminMenu();
            default -> System.out.println("Unknown role.");
        }
    }

    private static void showBuyerMenu() {
        while (true) {
            System.out.println("\n--- Buyer Menu ---");
            System.out.println("1. View Products");
            System.out.println("2. Search Product");
            System.out.println("3. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> viewProducts();
                case 2 -> searchProduct();
                case 3 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void viewProducts() {
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            products.forEach(System.out::println);
        }
    }

    private static void searchProduct() {
        System.out.println("Enter product name to search:");
        String productName = scanner.nextLine();

        List<Product> products = productDAO.searchProductsByName(productName);
        if (products.isEmpty()) {
            System.out.println("No products found with the name: " + productName);
        } else {
            products.forEach(System.out::println);
        }
    }

    private static void showSellerMenu() {
        while (true) {
            System.out.println("\n--- Seller Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View My Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> viewMyProducts();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addProduct() {
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        System.out.println("Enter product quantity:");
        int quantity = scanner.nextInt();
        System.out.println("Enter your seller ID:");
        int sellerId = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        Product product = new Product(name, BigDecimal.valueOf(price), quantity, sellerId);
        productDAO.addProduct(product);
        System.out.println("Product added successfully!");
    }

    private static void viewMyProducts() {
        System.out.println("Enter your seller ID:");
        int sellerId = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        List<Product> products = productDAO.getProductsBySellerId(sellerId);
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            products.forEach(System.out::println);
        }
    }

    private static void updateProduct() {
        System.out.println("Enter product ID to update:");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        System.out.println("Enter new product name:");
        String name = scanner.nextLine();
        System.out.println("Enter new product price:");
        double price = scanner.nextDouble();
        System.out.println("Enter new product quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        Product product = new Product(productId, name, BigDecimal.valueOf(price), quantity);
        boolean updated = productDAO.updateProduct(product);
        if (updated) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Failed to update product.");
        }
    }

    private static void deleteProduct() {
        System.out.println("Enter product ID to delete:");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        boolean deleted = productDAO.deleteProduct(productId);
        if (deleted) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Failed to delete product.");
        }
    }

    private static void showAdminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Users");
            System.out.println("2. Delete User");
            System.out.println("3. View All Products");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> viewAllUsers();
                case 2 -> deleteUser();
                case 3 -> viewProducts();
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void viewAllUsers() {
        List<User> users = userDAO.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void deleteUser() {
        System.out.println("Enter username to delete:");
        String username = scanner.nextLine();

        boolean deleted = userDAO.deleteUser(username);
        if (deleted) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("Failed to delete user.");
        }
    }
}
