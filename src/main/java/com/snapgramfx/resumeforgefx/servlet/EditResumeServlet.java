package com.snapgramfx.resumeforgefx.servlet;

import com.snapgramfx.resumeforgefx.dao.ResumeDAO;
import com.snapgramfx.resumeforgefx.dao.impl.ResumeDAOImpl;
import com.snapgramfx.resumeforgefx.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet for handling resume editing.
 */
@WebServlet(name = "editResumeServlet", value = "/resume/edit")
public class EditResumeServlet extends HttpServlet {
    
    private ResumeDAO resumeDAO;
    private SimpleDateFormat dateFormat;
    
    @Override
    public void init() throws ServletException {
        resumeDAO = new ResumeDAOImpl();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Redirect to login if not logged in
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Get the logged-in user
        User user = (User) session.getAttribute("user");
        
        // Get the resume ID from the request
        String resumeIdStr = request.getParameter("id");
        if (resumeIdStr == null || resumeIdStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }
        
        try {
            Long resumeId = Long.parseLong(resumeIdStr);
            
            // Get the resume
            Resume resume = resumeDAO.findById(resumeId);
            
            // Check if resume exists and belongs to the logged-in user
            if (resume == null || !resume.getUserId().equals(user.getId())) {
                response.sendRedirect(request.getContextPath() + "/dashboard");
                return;
            }
            
            // Set resume as a request attribute
            request.setAttribute("resume", resume);
            
            // Forward to the resume edit page
            request.getRequestDispatcher("/WEB-INF/views/resume/edit.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Redirect to login if not logged in
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Get the logged-in user
        User user = (User) session.getAttribute("user");
        
        // Get the resume ID from the request
        String resumeIdStr = request.getParameter("id");
        if (resumeIdStr == null || resumeIdStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }
        
        try {
            Long resumeId = Long.parseLong(resumeIdStr);
            
            // Get the resume
            Resume resume = resumeDAO.findById(resumeId);
            
            // Check if resume exists and belongs to the logged-in user
            if (resume == null || !resume.getUserId().equals(user.getId())) {
                response.sendRedirect(request.getContextPath() + "/dashboard");
                return;
            }
            
            // Update the resume with form data
            updateResumeFromRequest(request, resume);
            
            // Save the updated resume
            Resume updatedResume = resumeDAO.update(resume);
            
            if (updatedResume != null) {
                // Set success message
                request.setAttribute("success", "Resume updated successfully.");
            } else {
                // Set error message
                request.setAttribute("error", "Failed to update resume. Please try again.");
            }
            
            // Set resume as a request attribute
            request.setAttribute("resume", resume);
            
            // Forward back to the edit page
            request.getRequestDispatcher("/WEB-INF/views/resume/edit.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/dashboard");
        }
    }
    
    /**
     * Update a Resume object with data from the request parameters.
     * 
     * @param request the HttpServletRequest
     * @param resume the Resume object to update
     * @throws ParseException if date parsing fails
     */
    private void updateResumeFromRequest(HttpServletRequest request, Resume resume) throws ParseException {
        // Update basic resume information
        resume.setTitle(request.getParameter("title"));
        resume.setSummary(request.getParameter("summary"));
        
        // Update contact information
        ContactInfo contactInfo = resume.getContactInfo();
        if (contactInfo == null) {
            contactInfo = new ContactInfo();
            resume.setContactInfo(contactInfo);
        }
        
        contactInfo.setEmail(request.getParameter("email"));
        contactInfo.setPhone(request.getParameter("phone"));
        contactInfo.setAddress(request.getParameter("address"));
        contactInfo.setCity(request.getParameter("city"));
        contactInfo.setState(request.getParameter("state"));
        contactInfo.setZipCode(request.getParameter("zipCode"));
        contactInfo.setCountry(request.getParameter("country"));
        contactInfo.setLinkedIn(request.getParameter("linkedin"));
        contactInfo.setWebsite(request.getParameter("website"));
        
        // Clear existing education entries and add new ones
        resume.getEducations().clear();
        
        // Process education entries
        String[] institutions = request.getParameterValues("institution");
        String[] degrees = request.getParameterValues("degree");
        String[] fieldsOfStudy = request.getParameterValues("fieldOfStudy");
        String[] eduStartDates = request.getParameterValues("eduStartDate");
        String[] eduEndDates = request.getParameterValues("eduEndDate");
        String[] eduDescriptions = request.getParameterValues("eduDescription");
        String[] eduCurrentFlags = request.getParameterValues("eduCurrent");
        
        if (institutions != null) {
            for (int i = 0; i < institutions.length; i++) {
                if (institutions[i] != null && !institutions[i].trim().isEmpty()) {
                    Education education = new Education();
                    education.setInstitution(institutions[i]);
                    education.setDegree(degrees[i]);
                    education.setFieldOfStudy(fieldsOfStudy[i]);
                    
                    // Parse dates
                    if (eduStartDates[i] != null && !eduStartDates[i].isEmpty()) {
                        education.setStartDate(dateFormat.parse(eduStartDates[i]));
                    }
                    
                    // Check if current education
                    boolean isCurrent = eduCurrentFlags != null && 
                                       i < eduCurrentFlags.length && 
                                       eduCurrentFlags[i] != null && 
                                       eduCurrentFlags[i].equals("on");
                    
                    education.setCurrent(isCurrent);
                    
                    if (!isCurrent && eduEndDates[i] != null && !eduEndDates[i].isEmpty()) {
                        education.setEndDate(dateFormat.parse(eduEndDates[i]));
                    }
                    
                    education.setDescription(eduDescriptions[i]);
                    
                    resume.addEducation(education);
                }
            }
        }
        
        // Clear existing experience entries and add new ones
        resume.getExperiences().clear();
        
        // Process experience entries
        String[] companies = request.getParameterValues("company");
        String[] positions = request.getParameterValues("position");
        String[] locations = request.getParameterValues("location");
        String[] expStartDates = request.getParameterValues("expStartDate");
        String[] expEndDates = request.getParameterValues("expEndDate");
        String[] expDescriptions = request.getParameterValues("expDescription");
        String[] expCurrentFlags = request.getParameterValues("expCurrent");
        
        if (companies != null) {
            for (int i = 0; i < companies.length; i++) {
                if (companies[i] != null && !companies[i].trim().isEmpty()) {
                    Experience experience = new Experience();
                    experience.setCompany(companies[i]);
                    experience.setPosition(positions[i]);
                    experience.setLocation(locations[i]);
                    
                    // Parse dates
                    if (expStartDates[i] != null && !expStartDates[i].isEmpty()) {
                        experience.setStartDate(dateFormat.parse(expStartDates[i]));
                    }
                    
                    // Check if current job
                    boolean isCurrent = expCurrentFlags != null && 
                                       i < expCurrentFlags.length && 
                                       expCurrentFlags[i] != null && 
                                       expCurrentFlags[i].equals("on");
                    
                    experience.setCurrent(isCurrent);
                    
                    if (!isCurrent && expEndDates[i] != null && !expEndDates[i].isEmpty()) {
                        experience.setEndDate(dateFormat.parse(expEndDates[i]));
                    }
                    
                    experience.setDescription(expDescriptions[i]);
                    
                    resume.addExperience(experience);
                }
            }
        }
        
        // Clear existing skills and add new ones
        resume.getSkills().clear();
        
        // Process skills
        String[] skillNames = request.getParameterValues("skillName");
        String[] skillLevels = request.getParameterValues("skillLevel");
        
        if (skillNames != null) {
            for (int i = 0; i < skillNames.length; i++) {
                if (skillNames[i] != null && !skillNames[i].trim().isEmpty()) {
                    int level = 3; // Default level
                    if (skillLevels != null && i < skillLevels.length && skillLevels[i] != null && !skillLevels[i].isEmpty()) {
                        try {
                            level = Integer.parseInt(skillLevels[i]);
                        } catch (NumberFormatException e) {
                            // Use default level if parsing fails
                        }
                    }
                    
                    Skill skill = new Skill(skillNames[i], level);
                    resume.addSkill(skill);
                }
            }
        }
        
        // Update last modified date
        resume.setLastModifiedDate(new Date());
    }
}
