package com.snapgramfx.resumeforgefx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a resume in the ResumeForgeFX application.
 */
public class Resume implements Serializable {
    private Long id;
    private Long userId;
    private String title;
    private String summary;
    private Date createdDate;
    private Date lastModifiedDate;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Skill> skills;
    private ContactInfo contactInfo;
    
    // Default constructor
    public Resume() {
        this.educations = new ArrayList<>();
        this.experiences = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.createdDate = new Date();
        this.lastModifiedDate = new Date();
    }
    
    // Parameterized constructor
    public Resume(Long userId, String title, String summary) {
        this();
        this.userId = userId;
        this.title = title;
        this.summary = summary;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
    
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    
    public List<Education> getEducations() {
        return educations;
    }
    
    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }
    
    public void addEducation(Education education) {
        this.educations.add(education);
    }
    
    public List<Experience> getExperiences() {
        return experiences;
    }
    
    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }
    
    public void addExperience(Experience experience) {
        this.experiences.add(experience);
    }
    
    public List<Skill> getSkills() {
        return skills;
    }
    
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
    
    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }
    
    public ContactInfo getContactInfo() {
        return contactInfo;
    }
    
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
    
    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", educations=" + educations +
                ", experiences=" + experiences +
                ", skills=" + skills +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
