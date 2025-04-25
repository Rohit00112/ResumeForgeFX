package com.snapgramfx.resumeforgefx.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class for password hashing and verification.
 */
public class PasswordUtil {
    
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH = 16;
    
    /**
     * Generate a random salt.
     * 
     * @return a random salt as a Base64 encoded string
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    
    /**
     * Hash a password with a given salt.
     * 
     * @param password the password to hash
     * @param salt the salt to use
     * @return the hashed password
     */
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    /**
     * Verify a password against a stored hash and salt.
     * 
     * @param password the password to verify
     * @param storedHash the stored hash
     * @param storedSalt the stored salt
     * @return true if the password matches, false otherwise
     */
    public static boolean verifyPassword(String password, String storedHash, String storedSalt) {
        String hashedPassword = hashPassword(password, storedSalt);
        return hashedPassword.equals(storedHash);
    }
    
    /**
     * Generate a secure password hash with a random salt.
     * 
     * @param password the password to hash
     * @return a string in the format "hash:salt"
     */
    public static String generateSecurePassword(String password) {
        String salt = generateSalt();
        String hash = hashPassword(password, salt);
        return hash + ":" + salt;
    }
    
    /**
     * Verify a password against a stored "hash:salt" string.
     * 
     * @param password the password to verify
     * @param storedHashAndSalt the stored "hash:salt" string
     * @return true if the password matches, false otherwise
     */
    public static boolean verifyPassword(String password, String storedHashAndSalt) {
        String[] parts = storedHashAndSalt.split(":");
        if (parts.length != 2) {
            return false;
        }
        
        String storedHash = parts[0];
        String storedSalt = parts[1];
        
        return verifyPassword(password, storedHash, storedSalt);
    }
}
