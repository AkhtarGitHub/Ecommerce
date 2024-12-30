package com.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/ecommerce_db"; // Update to your DB name
    private static final String USER = "postgres"; // Replace with your PostgreSQL username
    private static final String PASSWORD = "3abuPostgres"; // Replace with your PostgreSQL password

    static {
        try {
            Class.forName("org.postgresql.Driver"); // Ensure PostgreSQL Driver is loaded
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL Driver not found!", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed: " + e.getMessage(), e);
        }
    }
}
