package com.ecommerce.util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtil {

    /**
     * Hashes a plaintext password using BCrypt.
     *
     * @param password The plaintext password to hash.
     * @return The hashed password.
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verifies a plaintext password against a hashed password.
     *
     * @param plaintext The plaintext password.
     * @param hashed    The hashed password.
     * @return True if the passwords match, false otherwise.
     */
    public static boolean checkPassword(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }
}
