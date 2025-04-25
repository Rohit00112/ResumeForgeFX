package com.snapgramfx.resumeforgefx.model;

import java.io.Serializable;

/**
 * Represents a skill entry in a resume.
 */
public class Skill implements Serializable {
    private Long id;
    private Long resumeId;
    private String name;
    private int proficiencyLevel; // 1-5 scale
    
    // Default constructor
    public Skill() {
    }
    
    // Parameterized constructor
    public Skill(String name, int proficiencyLevel) {
        this.name = name;
        this.proficiencyLevel = proficiencyLevel;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getResumeId() {
        return resumeId;
    }
    
    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getProficiencyLevel() {
        return proficiencyLevel;
    }
    
    public void setProficiencyLevel(int proficiencyLevel) {
        // Ensure proficiency level is between 1 and 5
        if (proficiencyLevel < 1) {
            this.proficiencyLevel = 1;
        } else if (proficiencyLevel > 5) {
            this.proficiencyLevel = 5;
        } else {
            this.proficiencyLevel = proficiencyLevel;
        }
    }
    
    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", name='" + name + '\'' +
                ", proficiencyLevel=" + proficiencyLevel +
                '}';
    }
}
