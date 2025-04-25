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
 * Servlet for handling resume creation.
 */
@WebServlet(name = "createResumeServlet", value = "/resume/create")
public class CreateResumeServlet extends HttpServlet {
    
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
        
        // Forward to the resume creation page
        request.getRequestDispatcher("/WEB-INF/views/resume/create.jsp").forward(request, response);
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
        
        try {
            // Create a new resume
            Resume resume = createResumeFromRequest(request, user.getId());
            
            // Save the resume
            Resume savedResume = resumeDAO.save(resume);
            
            if (savedResume != null) {
                // Redirect to the resume edit page
                response.sendRedirect(request.getContextPath() + "/resume/edit?id=" + savedResume.getId());
            } else {
                // Set error message and forward back to the create page
                request.setAttribute("error", "Failed to create resume. Please try again.");
                request.getRequestDispatcher("/WEB-INF/views/resume/create.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/resume/create.jsp").forward(request, response);
        }
    }
    
    /**
     * Create a Resume object from the request parameters.
     * 
     * @param request the HttpServletRequest
     * @param userId the ID of the user creating the resume
     * @return a Resume object
     * @throws ParseException if date parsing fails
     */
    private Resume createResumeFromRequest(HttpServletRequest request, Long userId) throws ParseException {
        // Get basic resume information
        String title = request.getParameter("title");
        String summary = request.getParameter("summary");
        
        // Create a new resume
        Resume resume = new Resume(userId, title, summary);
        
        // Create contact information
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipCode = request.getParameter("zipCode");
        String country = request.getParameter("country");
        String linkedin = request.getParameter("linkedin");
        String website = request.getParameter("website");
        
        ContactInfo contactInfo = new ContactInfo(email, phone);
        contactInfo.setAddress(address);
        contactInfo.setCity(city);
        contactInfo.setState(state);
        contactInfo.setZipCode(zipCode);
        contactInfo.setCountry(country);
        contactInfo.setLinkedIn(linkedin);
        contactInfo.setWebsite(website);
        
        resume.setContactInfo(contactInfo);
        
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
        
        return resume;
    }
}
