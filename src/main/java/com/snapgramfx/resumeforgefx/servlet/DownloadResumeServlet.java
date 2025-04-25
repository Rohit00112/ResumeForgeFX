package com.snapgramfx.resumeforgefx.servlet;

import com.snapgramfx.resumeforgefx.dao.ResumeDAO;
import com.snapgramfx.resumeforgefx.dao.impl.ResumeDAOImpl;
import com.snapgramfx.resumeforgefx.model.Resume;
import com.snapgramfx.resumeforgefx.model.User;
import com.snapgramfx.resumeforgefx.util.PDFGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Servlet for handling resume downloads.
 */
@WebServlet(name = "downloadResumeServlet", value = "/resume/download")
public class DownloadResumeServlet extends HttpServlet {

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

        // Get the format parameter (default to "pdf" if not specified)
        String format = request.getParameter("format");
        if (format == null || format.trim().isEmpty()) {
            format = "pdf";
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

            // Get customization parameters
            String primaryColor = request.getParameter("primaryColor");
            String secondaryColor = request.getParameter("secondaryColor");
            String accentColor = request.getParameter("accentColor");
            String fontFamily = request.getParameter("fontFamily");
            String fontSize = request.getParameter("fontSize");

            // Generate and download the resume based on the format
            if (format.equalsIgnoreCase("pdf")) {
                downloadPDF(response, resume, template, primaryColor, secondaryColor, accentColor, fontFamily, fontSize);
            } else if (format.equalsIgnoreCase("html")) {
                downloadHTML(request, response, resume, template, primaryColor, secondaryColor, accentColor, fontFamily, fontSize);
            } else {
                // Unsupported format, redirect to dashboard
                response.sendRedirect(request.getContextPath() + "/dashboard");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/dashboard");
        }
    }

    /**
     * Generate and download a PDF version of the resume.
     *
     * @param response the HttpServletResponse
     * @param resume the Resume object
     * @param template the template to use
     * @param primaryColor the primary color for customization
     * @param secondaryColor the secondary color for customization
     * @param accentColor the accent color for customization
     * @param fontFamily the font family for customization
     * @param fontSize the font size for customization
     * @throws Exception if an error occurs
     */
    private void downloadPDF(HttpServletResponse response, Resume resume, String template,
                            String primaryColor, String secondaryColor, String accentColor,
                            String fontFamily, String fontSize) throws Exception {
        // Set response headers
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + resume.getTitle().replaceAll("\\s+", "_") + ".pdf\"");

        // Generate PDF
        byte[] pdfBytes = PDFGenerator.generatePDF(resume, template, primaryColor, secondaryColor, accentColor, fontFamily, fontSize);

        // Write PDF to response
        OutputStream out = response.getOutputStream();
        out.write(pdfBytes);
        out.flush();
    }

    /**
     * Generate and download an HTML version of the resume.
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @param resume the Resume object
     * @param template the template to use
     * @param primaryColor the primary color for customization
     * @param secondaryColor the secondary color for customization
     * @param accentColor the accent color for customization
     * @param fontFamily the font family for customization
     * @param fontSize the font size for customization
     * @throws Exception if an error occurs
     */
    private void downloadHTML(HttpServletRequest request, HttpServletResponse response, Resume resume, String template,
                             String primaryColor, String secondaryColor, String accentColor,
                             String fontFamily, String fontSize) throws Exception {
        // Set response headers
        response.setContentType("text/html");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + resume.getTitle().replaceAll("\\s+", "_") + ".html\"");

        // Set resume and template as request attributes
        request.setAttribute("resume", resume);
        request.setAttribute("template", template);

        // Set customization attributes if provided
        if (primaryColor != null && !primaryColor.trim().isEmpty()) {
            request.setAttribute("primaryColor", primaryColor);
        }

        if (secondaryColor != null && !secondaryColor.trim().isEmpty()) {
            request.setAttribute("secondaryColor", secondaryColor);
        }

        if (accentColor != null && !accentColor.trim().isEmpty()) {
            request.setAttribute("accentColor", accentColor);
        }

        if (fontFamily != null && !fontFamily.trim().isEmpty()) {
            request.setAttribute("fontFamily", fontFamily);
        }

        if (fontSize != null && !fontSize.trim().isEmpty()) {
            request.setAttribute("fontSize", fontSize);
        }

        // Forward to the resume view page (which will be downloaded as HTML)
        request.getRequestDispatcher("/WEB-INF/views/resume/templates/" + template + ".jsp").forward(request, response);
    }
}
