package com.snapgramfx.resumeforgefx.servlet;

import com.snapgramfx.resumeforgefx.dao.ResumeDAO;
import com.snapgramfx.resumeforgefx.dao.impl.ResumeDAOImpl;
import com.snapgramfx.resumeforgefx.model.Resume;
import com.snapgramfx.resumeforgefx.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet for handling resume deletion.
 */
@WebServlet(name = "deleteResumeServlet", value = "/resume/delete")
public class DeleteResumeServlet extends HttpServlet {
    
    private ResumeDAO resumeDAO;
    
    @Override
    public void init() throws ServletException {
        resumeDAO = new ResumeDAOImpl();
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
            
            // Delete the resume
            boolean deleted = resumeDAO.delete(resumeId);
            
            if (deleted) {
                // Set success message in session
                session.setAttribute("message", "Resume deleted successfully.");
            } else {
                // Set error message in session
                session.setAttribute("error", "Failed to delete resume. Please try again.");
            }
            
            // Redirect to the dashboard
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
        }
    }
}
