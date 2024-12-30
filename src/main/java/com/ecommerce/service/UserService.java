package com.ecommerce.service;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;
import com.ecommerce.util.BCryptUtil;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    /**
     * Register a new user by delegating to the UserDAO.
     * Ensures passwords are hashed and checks for duplicate usernames.
     *
     * @param user The user object to register.
     * @throws IllegalArgumentException if validation fails or username is already taken.
     */
    public void registerUser(User user) {
        // Input validation
        if (user.getUsername() == null || user.getUsername().isEmpty() ||
            user.getPassword() == null || user.getPassword().isEmpty() ||
            user.getEmail() == null || user.getEmail().isEmpty() ||
            user.getRole() == null || user.getRole().isEmpty()) {
            throw new IllegalArgumentException("User fields cannot be null or empty.");
        }

        // Check if username is already taken
        if (userDAO.isUsernameTaken(user.getUsername())) {
            throw new IllegalArgumentException("Username already taken.");
        }

        // Hash the password before saving
        String hashedPassword = BCryptUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        user.setPassword(BCryptUtil.hashPassword(user.getPassword()));
        userDAO.registerUser(user);

        // Delegate to UserDAO to persist the user
        userDAO.registerUser(user);
    }

    /**
     * Login a user by verifying the username and password.
     *
     * @param username The username of the user attempting to log in.
     * @param password The plaintext password to verify.
     * @return The authenticated User object if login is successful, null otherwise.
     */
    public User loginUser(String username, String password) {
        // Retrieve the user from the database
        User user = userDAO.findByUsername(username);

        // Check if the user exists and verify the password
        if (user != null && BCryptUtil.checkPassword(password, user.getPassword())) {
            return user;
        } else {
            return null; // Login failed
        }
    }

    /**
     * Delete a user by their username.
     * Only accessible to admins or authorized users.
     *
     * @param username The username of the user to delete.
     * @return True if the user was deleted successfully, false otherwise.
     */
    public boolean deleteUser(String username) {
        return userDAO.deleteUser(username);
    }

    /**
     * Retrieve a user by their username.
     *
     * @param username The username to search for.
     * @return The User object if found, null otherwise.
     */
    public User findUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}

