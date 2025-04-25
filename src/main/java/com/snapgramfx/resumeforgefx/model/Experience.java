package com.snapgramfx.resumeforgefx.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a work experience entry in a resume.
 */
public class Experience implements Serializable {
    private Long id;
    private Long resumeId;
    private String company;
    private String position;
    private String location;
    private Date startDate;
    private Date endDate;
    private String description;
    private boolean current;
    
    // Default constructor
    public Experience() {
    }
    
    // Parameterized constructor
    public Experience(String company, String position, String location, 
                     Date startDate, Date endDate, String description) {
        this.company = company;
        this.position = position;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.current = (endDate == null);
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
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        this.current = (endDate == null);
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isCurrent() {
        return current;
    }
    
    public void setCurrent(boolean current) {
        this.current = current;
        if (current) {
            this.endDate = null;
        }
    }
    
    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", location='" + location + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", current=" + current +
                '}';
    }
}
