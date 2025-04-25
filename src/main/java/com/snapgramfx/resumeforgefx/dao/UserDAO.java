package com.snapgramfx.resumeforgefx.dao;

import com.snapgramfx.resumeforgefx.model.User;
import java.util.List;

/**
 * Data Access Object interface for User entity.
 */
public interface UserDAO {
    
    /**
     * Save a new user to the database.
     * 
     * @param user The user to save
     * @return The saved user with generated ID
     */
    User save(User user);
    
    /**
     * Find a user by their ID.
     * 
     * @param id The user ID
     * @return The user if found, null otherwise
     */
    User findById(Long id);
    
    /**
     * Find a user by their username.
     * 
     * @param username The username to search for
     * @return The user if found, null otherwise
     */
    User findByUsername(String username);
    
    /**
     * Find a user by their email.
     * 
     * @param email The email to search for
     * @return The user if found, null otherwise
     */
    User findByEmail(String email);
    
    /**
     * Get all users from the database.
     * 
     * @return List of all users
     */
    List<User> findAll();
    
    /**
     * Update an existing user.
     * 
     * @param user The user to update
     * @return The updated user
     */
    User update(User user);
    
    /**
     * Delete a user by their ID.
     * 
     * @param id The ID of the user to delete
     * @return true if deleted successfully, false otherwise
     */
    boolean delete(Long id);
}
