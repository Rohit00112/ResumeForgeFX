package com.snapgramfx.resumeforgefx.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents an education entry in a resume.
 */
public class Education implements Serializable {
    private Long id;
    private Long resumeId;
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private Date startDate;
    private Date endDate;
    private String description;
    private boolean current;
    
    // Default constructor
    public Education() {
    }
    
    // Parameterized constructor
    public Education(String institution, String degree, String fieldOfStudy, 
                    Date startDate, Date endDate, String description) {
        this.institution = institution;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
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
    
    public String getInstitution() {
        return institution;
    }
    
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    
    public String getDegree() {
        return degree;
    }
    
    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    public String getFieldOfStudy() {
        return fieldOfStudy;
    }
    
    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
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
        return "Education{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", institution='" + institution + '\'' +
                ", degree='" + degree + '\'' +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", current=" + current +
                '}';
    }
}
