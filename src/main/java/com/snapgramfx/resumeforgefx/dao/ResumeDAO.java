package com.snapgramfx.resumeforgefx.dao;

import com.snapgramfx.resumeforgefx.model.Resume;
import java.util.List;

/**
 * Data Access Object interface for Resume entity.
 */
public interface ResumeDAO {
    
    /**
     * Save a new resume to the database.
     * 
     * @param resume The resume to save
     * @return The saved resume with generated ID
     */
    Resume save(Resume resume);
    
    /**
     * Find a resume by its ID.
     * 
     * @param id The resume ID
     * @return The resume if found, null otherwise
     */
    Resume findById(Long id);
    
    /**
     * Find all resumes for a specific user.
     * 
     * @param userId The user ID
     * @return List of resumes belonging to the user
     */
    List<Resume> findByUserId(Long userId);
    
    /**
     * Get all resumes from the database.
     * 
     * @return List of all resumes
     */
    List<Resume> findAll();
    
    /**
     * Update an existing resume.
     * 
     * @param resume The resume to update
     * @return The updated resume
     */
    Resume update(Resume resume);
    
    /**
     * Delete a resume by its ID.
     * 
     * @param id The ID of the resume to delete
     * @return true if deleted successfully, false otherwise
     */
    boolean delete(Long id);
}
