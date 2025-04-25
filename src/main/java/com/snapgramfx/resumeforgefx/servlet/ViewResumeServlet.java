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
 * Servlet for handling resume viewing.
 */
@WebServlet(name = "viewResumeServlet", value = "/resume/view")
public class ViewResumeServlet extends HttpServlet {
    
    private ResumeDAO resumeDAO;
    
    @Override
    public void init() throws ServletException {
        resumeDAO = new ResumeDAOImpl();
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
            
            // Get the template parameter (default to "classic" if not specified)
            String template = request.getParameter("template");
            if (template == null || template.trim().isEmpty()) {
                template = "classic";
            }
            
            // Set resume and template as request attributes
            request.setAttribute("resume", resume);
            request.setAttribute("template", template);
            
            // Forward to the resume view page
            request.getRequestDispatcher("/WEB-INF/views/resume/view.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
        }
    }
}
