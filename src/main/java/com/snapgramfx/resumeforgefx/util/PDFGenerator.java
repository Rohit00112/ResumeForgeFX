package com.snapgramfx.resumeforgefx.util;

import com.snapgramfx.resumeforgefx.model.*;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

/**
 * Utility class for generating PDF resumes.
 */
public class PDFGenerator {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM yyyy");

    /**
     * Generate a PDF version of a resume.
     *
     * @param resume the Resume object
     * @param template the template to use
     * @return a byte array containing the PDF data
     * @throws Exception if an error occurs
     */
    public static byte[] generatePDF(Resume resume, String template) throws Exception {
        return generatePDF(resume, template, null, null, null, null, null);
    }

    /**
     * Generate a PDF version of a resume with customization options.
     *
     * @param resume the Resume object
     * @param template the template to use
     * @param primaryColor the primary color for customization
     * @param secondaryColor the secondary color for customization
     * @param accentColor the accent color for customization
     * @param fontFamily the font family for customization
     * @param fontSize the font size for customization
     * @return a byte array containing the PDF data
     * @throws Exception if an error occurs
     */
    public static byte[] generatePDF(Resume resume, String template,
                                    String primaryColor, String secondaryColor, String accentColor,
                                    String fontFamily, String fontSize) throws Exception {
        try {
            // Generate HTML content for the resume
            String htmlContent = generateResumeHtml(resume, template);

            // Generate custom CSS based on customization parameters
            String customCss = HtmlToPdfConverter.generateCustomCss(
                template, primaryColor, secondaryColor, accentColor, fontFamily, fontSize);

            // Convert HTML to PDF
            return HtmlToPdfConverter.convertHtmlToPdf(htmlContent, customCss);
        } catch (Exception e) {
            // If HTML to PDF conversion fails, fall back to the simple text-based PDF
            System.err.println("Error generating PDF: " + e.getMessage());
            e.printStackTrace();

            // Fallback to simple text-based PDF
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            StringBuilder content = new StringBuilder();
            content.append("Resume: ").append(resume.getTitle()).append("\n\n");

            // Add customization information
            content.append("Template: ").append(template).append("\n");
            if (primaryColor != null) content.append("Primary Color: ").append(primaryColor).append("\n");
            if (secondaryColor != null) content.append("Secondary Color: ").append(secondaryColor).append("\n");
            if (accentColor != null) content.append("Accent Color: ").append(accentColor).append("\n");
            if (fontFamily != null) content.append("Font Family: ").append(fontFamily).append("\n");
            if (fontSize != null) content.append("Font Size: ").append(fontSize).append("\n\n");

            // Add resume content
            content.append(generateResumeContent(resume));

            baos.write(content.toString().getBytes());

            return baos.toByteArray();
        }
    }

    /**
     * Generate HTML content for a resume.
     *
     * @param resume the Resume object
     * @param template the template to use
     * @return HTML content as a string
     */
    private static String generateResumeHtml(Resume resume, String template) {
        StringBuilder html = new StringBuilder();

        // Start HTML document
        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"en\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <title>").append(resume.getTitle()).append("</title>\n");

        // Add base styles based on the template
        html.append("    <style>\n");

        if (template.equals("classic")) {
            html.append(getClassicTemplateStyles());
        } else if (template.equals("modern")) {
            html.append(getModernTemplateStyles());
        } else if (template.equals("creative")) {
            html.append(getCreativeTemplateStyles());
        } else if (template.equals("executive")) {
            html.append(getExecutiveTemplateStyles());
        }

        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");

        // Generate the resume content based on the template
        if (template.equals("classic")) {
            html.append(generateClassicTemplate(resume));
        } else if (template.equals("modern")) {
            html.append(generateModernTemplate(resume));
        } else if (template.equals("creative")) {
            html.append(generateCreativeTemplate(resume));
        } else if (template.equals("executive")) {
            html.append(generateExecutiveTemplate(resume));
        }

        // End HTML document
        html.append("</body>\n");
        html.append("</html>");

        return html.toString();
    }

    /**
     * Generate a string representation of a resume.
     *
     * @param resume the Resume object
     * @return a string containing the resume content
     */
    private static String generateResumeContent(Resume resume) {
        StringBuilder sb = new StringBuilder();

        // Add basic information
        sb.append("Resume: ").append(resume.getTitle()).append("\n\n");

        // Add contact information
        ContactInfo contactInfo = resume.getContactInfo();
        if (contactInfo != null) {
            sb.append("Contact Information:\n");
            sb.append("Email: ").append(contactInfo.getEmail()).append("\n");
            sb.append("Phone: ").append(contactInfo.getPhone()).append("\n");

            if (contactInfo.getAddress() != null && !contactInfo.getAddress().isEmpty()) {
                sb.append("Address: ").append(contactInfo.getAddress()).append("\n");
            }

            if (contactInfo.getCity() != null && !contactInfo.getCity().isEmpty()) {
                sb.append("City: ").append(contactInfo.getCity()).append("\n");
            }

            if (contactInfo.getState() != null && !contactInfo.getState().isEmpty()) {
                sb.append("State: ").append(contactInfo.getState()).append("\n");
            }

            if (contactInfo.getZipCode() != null && !contactInfo.getZipCode().isEmpty()) {
                sb.append("Zip Code: ").append(contactInfo.getZipCode()).append("\n");
            }

            if (contactInfo.getCountry() != null && !contactInfo.getCountry().isEmpty()) {
                sb.append("Country: ").append(contactInfo.getCountry()).append("\n");
            }

            if (contactInfo.getLinkedIn() != null && !contactInfo.getLinkedIn().isEmpty()) {
                sb.append("LinkedIn: ").append(contactInfo.getLinkedIn()).append("\n");
            }

            if (contactInfo.getWebsite() != null && !contactInfo.getWebsite().isEmpty()) {
                sb.append("Website: ").append(contactInfo.getWebsite()).append("\n");
            }

            sb.append("\n");
        }

        // Add summary
        if (resume.getSummary() != null && !resume.getSummary().isEmpty()) {
            sb.append("Summary:\n");
            sb.append(resume.getSummary()).append("\n\n");
        }

        // Add education
        List<Education> educations = resume.getEducations();
        if (educations != null && !educations.isEmpty()) {
            sb.append("Education:\n");

            for (Education education : educations) {
                sb.append("- ").append(education.getInstitution()).append("\n");
                sb.append("  ").append(education.getDegree());

                if (education.getFieldOfStudy() != null && !education.getFieldOfStudy().isEmpty()) {
                    sb.append(", ").append(education.getFieldOfStudy());
                }

                sb.append("\n");

                if (education.getStartDate() != null) {
                    sb.append("  ").append(DATE_FORMAT.format(education.getStartDate()));

                    if (education.isCurrent()) {
                        sb.append(" - Present");
                    } else if (education.getEndDate() != null) {
                        sb.append(" - ").append(DATE_FORMAT.format(education.getEndDate()));
                    }

                    sb.append("\n");
                }

                if (education.getDescription() != null && !education.getDescription().isEmpty()) {
                    sb.append("  ").append(education.getDescription()).append("\n");
                }

                sb.append("\n");
            }
        }

        // Add experience
        List<Experience> experiences = resume.getExperiences();
        if (experiences != null && !experiences.isEmpty()) {
            sb.append("Experience:\n");

            for (Experience experience : experiences) {
                sb.append("- ").append(experience.getPosition()).append(", ").append(experience.getCompany()).append("\n");

                if (experience.getLocation() != null && !experience.getLocation().isEmpty()) {
                    sb.append("  ").append(experience.getLocation()).append("\n");
                }

                if (experience.getStartDate() != null) {
                    sb.append("  ").append(DATE_FORMAT.format(experience.getStartDate()));

                    if (experience.isCurrent()) {
                        sb.append(" - Present");
                    } else if (experience.getEndDate() != null) {
                        sb.append(" - ").append(DATE_FORMAT.format(experience.getEndDate()));
                    }

                    sb.append("\n");
                }

                if (experience.getDescription() != null && !experience.getDescription().isEmpty()) {
                    sb.append("  ").append(experience.getDescription()).append("\n");
                }

                sb.append("\n");
            }
        }

        // Add skills
        List<Skill> skills = resume.getSkills();
        if (skills != null && !skills.isEmpty()) {
            sb.append("Skills:\n");

            for (Skill skill : skills) {
                sb.append("- ").append(skill.getName()).append(" (").append(skill.getProficiencyLevel()).append("/5)\n");
            }
        }

        return sb.toString();
    }

    /**
     * Get CSS styles for the classic template.
     *
     * @return CSS styles as a string
     */
    private static String getClassicTemplateStyles() {
        return "body { font-family: 'Georgia', serif; line-height: 1.6; color: #333; max-width: 800px; margin: 0 auto; }\n" +
               ".classic-template { padding: 2rem; }\n" +
               ".resume-header { text-align: center; margin-bottom: 2rem; }\n" +
               ".resume-name { font-size: 2.5rem; margin-bottom: 0.5rem; }\n" +
               ".resume-contact { display: flex; justify-content: center; flex-wrap: wrap; gap: 1rem; }\n" +
               ".contact-item { display: flex; align-items: center; gap: 0.5rem; }\n" +
               ".section { margin-bottom: 2rem; }\n" +
               ".section-title { font-size: 1.5rem; color: #2c3e50; border-bottom: 2px solid #ddd; padding-bottom: 0.5rem; margin-bottom: 1rem; }\n" +
               ".resume-item { margin-bottom: 1.5rem; }\n" +
               ".item-title h3 { font-size: 1.2rem; color: #2c3e50; margin-bottom: 0.25rem; }\n" +
               ".item-title h4 { font-size: 1.1rem; font-weight: normal; margin-top: 0; margin-bottom: 0.25rem; }\n" +
               ".item-date { font-style: italic; color: #666; margin-bottom: 0.5rem; }\n" +
               ".item-description { margin-top: 0.5rem; }\n" +
               ".skills-list { display: flex; flex-wrap: wrap; gap: 1rem; }\n" +
               ".skill-item { display: flex; align-items: center; gap: 0.5rem; }\n" +
               ".skill-dots { display: flex; gap: 0.25rem; }\n" +
               ".skill-dot { width: 12px; height: 12px; border-radius: 50%; border: 1px solid #2c3e50; }\n" +
               ".skill-dot.filled { background-color: #2c3e50; }";
    }

    /**
     * Get CSS styles for the modern template.
     *
     * @return CSS styles as a string
     */
    private static String getModernTemplateStyles() {
        return "body { font-family: 'Roboto', sans-serif; line-height: 1.6; color: #333; }\n" +
               ".modern-template { display: flex; max-width: 800px; margin: 0 auto; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }\n" +
               ".resume-sidebar { width: 35%; background-color: #2c3e50; color: white; padding: 2rem; }\n" +
               ".resume-main { width: 65%; padding: 2rem; background-color: white; }\n" +
               ".sidebar-header { text-align: center; margin-bottom: 2rem; }\n" +
               ".profile-image { width: 120px; height: 120px; border-radius: 50%; background-color: #3498db; margin: 0 auto 1rem; display: flex; align-items: center; justify-content: center; }\n" +
               ".profile-initials { font-size: 2.5rem; font-weight: bold; }\n" +
               ".resume-name { font-size: 1.8rem; margin-bottom: 0.5rem; }\n" +
               ".sidebar-section { margin-bottom: 2rem; }\n" +
               ".sidebar-title { font-size: 1.3rem; margin-bottom: 1rem; position: relative; padding-bottom: 0.5rem; }\n" +
               ".sidebar-title::after { content: ''; position: absolute; bottom: 0; left: 0; width: 50px; height: 3px; background-color: #3498db; }\n" +
               ".contact-list { list-style: none; padding: 0; margin: 0; }\n" +
               ".contact-item { display: flex; align-items: center; gap: 0.5rem; margin-bottom: 0.5rem; }\n" +
               ".skills-list { margin-top: 1rem; }\n" +
               ".skill-item { margin-bottom: 1rem; }\n" +
               ".skill-name { margin-bottom: 0.25rem; }\n" +
               ".skill-bar { height: 8px; background-color: rgba(255, 255, 255, 0.2); border-radius: 4px; overflow: hidden; }\n" +
               ".skill-progress { height: 100%; background-color: #3498db; }\n" +
               ".main-section { margin-bottom: 2rem; }\n" +
               ".section-title { font-size: 1.5rem; color: #2c3e50; margin-bottom: 1rem; position: relative; padding-bottom: 0.5rem; }\n" +
               ".section-title::after { content: ''; position: absolute; bottom: 0; left: 0; width: 50px; height: 3px; background-color: #3498db; }\n" +
               ".resume-item { margin-bottom: 1.5rem; }\n" +
               ".item-header { margin-bottom: 0.5rem; }\n" +
               ".item-title h3 { font-size: 1.2rem; margin-bottom: 0.25rem; }\n" +
               ".item-title h4 { font-size: 1.1rem; font-weight: normal; margin-top: 0; color: #666; }\n" +
               ".item-date { font-style: italic; color: #666; font-size: 0.9rem; }";
    }

    /**
     * Get CSS styles for the creative template.
     *
     * @return CSS styles as a string
     */
    private static String getCreativeTemplateStyles() {
        return "body { font-family: 'Montserrat', sans-serif; line-height: 1.6; color: #333; background-color: #f9f9f9; }\n" +
               ".creative-template { max-width: 800px; margin: 0 auto; background-color: white; box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); }\n" +
               ".resume-header { background-color: #ff6b6b; color: white; padding: 2rem; }\n" +
               ".header-content { display: flex; justify-content: space-between; align-items: center; }\n" +
               ".resume-name { font-size: 2.5rem; margin-bottom: 0.5rem; }\n" +
               ".resume-title { font-size: 1.2rem; opacity: 0.9; }\n" +
               ".header-right { text-align: right; }\n" +
               ".contact-info { display: flex; flex-direction: column; gap: 0.5rem; }\n" +
               ".contact-item { display: flex; align-items: center; justify-content: flex-end; gap: 0.5rem; }\n" +
               ".resume-body { padding: 2rem; }\n" +
               ".resume-section { margin-bottom: 2.5rem; }\n" +
               ".section-title { font-size: 1.5rem; color: #ff6b6b; margin-bottom: 1.5rem; position: relative; display: inline-block; }\n" +
               ".section-title::after { content: ''; position: absolute; bottom: -5px; left: 0; width: 100%; height: 3px; background-color: #ff6b6b; }\n" +
               ".timeline { position: relative; }\n" +
               ".timeline::before { content: ''; position: absolute; top: 0; bottom: 0; left: 10px; width: 2px; background-color: #ddd; }\n" +
               ".timeline-item { position: relative; padding-left: 40px; margin-bottom: 2rem; }\n" +
               ".timeline-marker { position: absolute; top: 5px; left: 0; width: 20px; height: 20px; border-radius: 50%; background-color: #ff6b6b; box-shadow: 0 0 0 4px white, 0 0 0 5px #ff6b6b; }\n" +
               ".timeline-title { font-size: 1.2rem; margin-bottom: 0.25rem; }\n" +
               ".timeline-subtitle { font-size: 1rem; color: #666; margin-bottom: 0.5rem; }\n" +
               ".timeline-date { font-style: italic; color: #888; margin-bottom: 0.5rem; font-size: 0.9rem; }\n" +
               ".skills-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(120px, 1fr)); gap: 1rem; }\n" +
               ".skill-item { text-align: center; }\n" +
               ".skill-name { margin-bottom: 0.5rem; }\n" +
               ".skill-bubbles { display: flex; justify-content: center; gap: 0.25rem; }\n" +
               ".skill-bubble { width: 10px; height: 10px; border-radius: 50%; background-color: #ddd; }\n" +
               ".skill-bubble.filled { background-color: #ff6b6b; }\n" +
               ".detail-link { color: #ff6b6b; text-decoration: none; font-weight: bold; display: inline-block; margin-top: 0.5rem; }";
    }

    /**
     * Get CSS styles for the executive template.
     *
     * @return CSS styles as a string
     */
    private static String getExecutiveTemplateStyles() {
        return "body { font-family: 'Libre Baskerville', serif; line-height: 1.6; color: #333; background-color: #f9f9f9; }\n" +
               ".executive-template { max-width: 800px; margin: 0 auto; background-color: white; padding: 2rem; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }\n" +
               ".resume-header { text-align: center; margin-bottom: 2rem; border-bottom: 3px solid #8e44ad; padding-bottom: 1rem; }\n" +
               ".header-top { margin-bottom: 1rem; }\n" +
               ".resume-name { font-size: 2.5rem; margin-bottom: 0.5rem; }\n" +
               ".resume-title { font-size: 1.2rem; color: #666; }\n" +
               ".contact-info { display: flex; justify-content: center; flex-wrap: wrap; gap: 1.5rem; }\n" +
               ".contact-item { display: flex; align-items: center; gap: 0.5rem; }\n" +
               ".contact-link { color: #8e44ad; text-decoration: none; }\n" +
               ".resume-section { margin-bottom: 2.5rem; }\n" +
               ".section-title { font-size: 1.5rem; margin-bottom: 1.5rem; position: relative; display: inline-block; }\n" +
               ".section-title::after { content: ''; position: absolute; bottom: -5px; left: 0; width: 100%; height: 2px; background-color: #8e44ad; }\n" +
               ".resume-item { margin-bottom: 1.5rem; }\n" +
               ".item-header { margin-bottom: 0.5rem; }\n" +
               ".item-title { font-size: 1.2rem; margin-bottom: 0.25rem; }\n" +
               ".item-subtitle { font-size: 1.1rem; font-style: italic; color: #666; }\n" +
               ".item-date { color: #888; margin-bottom: 0.5rem; }\n" +
               ".education-list { display: flex; flex-wrap: wrap; gap: 2rem; }\n" +
               ".education-item { flex: 1 1 300px; padding-left: 1rem; border-left: 3px solid #8e44ad; }\n" +
               ".skills-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); gap: 1rem; }\n" +
               ".skill-item { display: flex; align-items: center; justify-content: space-between; }\n" +
               ".skill-rating { display: flex; gap: 0.25rem; }\n" +
               ".rating-star { color: #ddd; }\n" +
               ".rating-star.filled { color: #8e44ad; }\n" +
               ".resume-footer { margin-top: 3rem; text-align: center; }\n" +
               ".footer-line { height: 3px; background-color: #8e44ad; margin: 0 auto 1rem; width: 100px; }\n" +
               ".footer-text { font-style: italic; color: #666; }";
    }

    /**
     * Generate HTML for the classic template.
     *
     * @param resume the Resume object
     * @return HTML content as a string
     */
    private static String generateClassicTemplate(Resume resume) {
        StringBuilder html = new StringBuilder();

        html.append("<div class=\"classic-template\">\n");

        // Header section
        html.append("    <div class=\"resume-header\">\n");
        html.append("        <h1 class=\"resume-name\">").append(resume.getTitle()).append("</h1>\n");

        // Contact information
        ContactInfo contactInfo = resume.getContactInfo();
        if (contactInfo != null) {
            html.append("        <div class=\"resume-contact\">\n");

            if (contactInfo.getEmail() != null && !contactInfo.getEmail().isEmpty()) {
                html.append("            <div class=\"contact-item\">\n");
                html.append("                <i class=\"fas fa-envelope\"></i> ").append(contactInfo.getEmail()).append("\n");
                html.append("            </div>\n");
            }

            if (contactInfo.getPhone() != null && !contactInfo.getPhone().isEmpty()) {
                html.append("            <div class=\"contact-item\">\n");
                html.append("                <i class=\"fas fa-phone\"></i> ").append(contactInfo.getPhone()).append("\n");
                html.append("            </div>\n");
            }

            if (contactInfo.getAddress() != null && !contactInfo.getAddress().isEmpty()) {
                html.append("            <div class=\"contact-item\">\n");
                html.append("                <i class=\"fas fa-map-marker-alt\"></i> ").append(contactInfo.getAddress());

                if (contactInfo.getCity() != null && !contactInfo.getCity().isEmpty()) {
                    html.append(", ").append(contactInfo.getCity());
                }

                if (contactInfo.getState() != null && !contactInfo.getState().isEmpty()) {
                    html.append(", ").append(contactInfo.getState());
                }

                if (contactInfo.getZipCode() != null && !contactInfo.getZipCode().isEmpty()) {
                    html.append(" ").append(contactInfo.getZipCode());
                }

                html.append("\n");
                html.append("            </div>\n");
            }

            if (contactInfo.getLinkedIn() != null && !contactInfo.getLinkedIn().isEmpty()) {
                html.append("            <div class=\"contact-item\">\n");
                html.append("                <i class=\"fab fa-linkedin\"></i> ").append(contactInfo.getLinkedIn()).append("\n");
                html.append("            </div>\n");
            }

            if (contactInfo.getWebsite() != null && !contactInfo.getWebsite().isEmpty()) {
                html.append("            <div class=\"contact-item\">\n");
                html.append("                <i class=\"fas fa-globe\"></i> ").append(contactInfo.getWebsite()).append("\n");
                html.append("            </div>\n");
            }

            html.append("        </div>\n");
        }

        html.append("    </div>\n");

        // Summary section
        if (resume.getSummary() != null && !resume.getSummary().isEmpty()) {
            html.append("    <div class=\"section\">\n");
            html.append("        <h2 class=\"section-title\">Summary</h2>\n");
            html.append("        <p>").append(resume.getSummary()).append("</p>\n");
            html.append("    </div>\n");
        }

        // Experience section
        List<Experience> experiences = resume.getExperiences();
        if (experiences != null && !experiences.isEmpty()) {
            html.append("    <div class=\"section\">\n");
            html.append("        <h2 class=\"section-title\">Experience</h2>\n");

            for (Experience experience : experiences) {
                html.append("        <div class=\"resume-item\">\n");
                html.append("            <div class=\"item-title\">\n");
                html.append("                <h3>").append(experience.getPosition()).append("</h3>\n");
                html.append("                <h4>").append(experience.getCompany());

                if (experience.getLocation() != null && !experience.getLocation().isEmpty()) {
                    html.append(", ").append(experience.getLocation());
                }

                html.append("</h4>\n");
                html.append("            </div>\n");

                if (experience.getStartDate() != null) {
                    html.append("            <div class=\"item-date\">\n");
                    html.append("                ").append(DATE_FORMAT.format(experience.getStartDate()));

                    if (experience.isCurrent()) {
                        html.append(" - Present");
                    } else if (experience.getEndDate() != null) {
                        html.append(" - ").append(DATE_FORMAT.format(experience.getEndDate()));
                    }

                    html.append("\n");
                    html.append("            </div>\n");
                }

                if (experience.getDescription() != null && !experience.getDescription().isEmpty()) {
                    html.append("            <div class=\"item-description\">\n");
                    html.append("                <p>").append(experience.getDescription()).append("</p>\n");
                    html.append("            </div>\n");
                }

                html.append("        </div>\n");
            }

            html.append("    </div>\n");
        }

        // Education section
        List<Education> educations = resume.getEducations();
        if (educations != null && !educations.isEmpty()) {
            html.append("    <div class=\"section\">\n");
            html.append("        <h2 class=\"section-title\">Education</h2>\n");

            for (Education education : educations) {
                html.append("        <div class=\"resume-item\">\n");
                html.append("            <div class=\"item-title\">\n");
                html.append("                <h3>").append(education.getDegree());

                if (education.getFieldOfStudy() != null && !education.getFieldOfStudy().isEmpty()) {
                    html.append(", ").append(education.getFieldOfStudy());
                }

                html.append("</h3>\n");
                html.append("                <h4>").append(education.getInstitution()).append("</h4>\n");
                html.append("            </div>\n");

                if (education.getStartDate() != null) {
                    html.append("            <div class=\"item-date\">\n");
                    html.append("                ").append(DATE_FORMAT.format(education.getStartDate()));

                    if (education.isCurrent()) {
                        html.append(" - Present");
                    } else if (education.getEndDate() != null) {
                        html.append(" - ").append(DATE_FORMAT.format(education.getEndDate()));
                    }

                    html.append("\n");
                    html.append("            </div>\n");
                }

                if (education.getDescription() != null && !education.getDescription().isEmpty()) {
                    html.append("            <div class=\"item-description\">\n");
                    html.append("                <p>").append(education.getDescription()).append("</p>\n");
                    html.append("            </div>\n");
                }

                html.append("        </div>\n");
            }

            html.append("    </div>\n");
        }

        // Skills section
        List<Skill> skills = resume.getSkills();
        if (skills != null && !skills.isEmpty()) {
            html.append("    <div class=\"section\">\n");
            html.append("        <h2 class=\"section-title\">Skills</h2>\n");
            html.append("        <div class=\"skills-list\">\n");

            for (Skill skill : skills) {
                html.append("            <div class=\"skill-item\">\n");
                html.append("                <span class=\"skill-name\">").append(skill.getName()).append("</span>\n");
                html.append("                <div class=\"skill-dots\">\n");

                for (int i = 1; i <= 5; i++) {
                    if (i <= skill.getProficiencyLevel()) {
                        html.append("                    <div class=\"skill-dot filled\"></div>\n");
                    } else {
                        html.append("                    <div class=\"skill-dot\"></div>\n");
                    }
                }

                html.append("                </div>\n");
                html.append("            </div>\n");
            }

            html.append("        </div>\n");
            html.append("    </div>\n");
        }

        html.append("</div>");

        return html.toString();
    }

    /**
     * Generate HTML for the modern template.
     *
     * @param resume the Resume object
     * @return HTML content as a string
     */
    private static String generateModernTemplate(Resume resume) {
        StringBuilder html = new StringBuilder();

        html.append("<div class=\"modern-template\">\n");

        // Sidebar section
        html.append("    <div class=\"resume-sidebar\">\n");
        html.append("        <div class=\"sidebar-header\">\n");
        html.append("            <div class=\"profile-image\">\n");
        html.append("                <div class=\"profile-initials\">").append(resume.getTitle().substring(0, 1)).append("</div>\n");
        html.append("            </div>\n");
        html.append("            <h1 class=\"resume-name\">").append(resume.getTitle()).append("</h1>\n");
        html.append("        </div>\n");

        // Contact information
        ContactInfo contactInfo = resume.getContactInfo();
        if (contactInfo != null) {
            html.append("        <div class=\"sidebar-section\">\n");
            html.append("            <h2 class=\"sidebar-title\">Contact</h2>\n");
            html.append("            <ul class=\"contact-list\">\n");

            if (contactInfo.getEmail() != null && !contactInfo.getEmail().isEmpty()) {
                html.append("                <li class=\"contact-item\">\n");
                html.append("                    <i class=\"fas fa-envelope\"></i> ").append(contactInfo.getEmail()).append("\n");
                html.append("                </li>\n");
            }

            if (contactInfo.getPhone() != null && !contactInfo.getPhone().isEmpty()) {
                html.append("                <li class=\"contact-item\">\n");
                html.append("                    <i class=\"fas fa-phone\"></i> ").append(contactInfo.getPhone()).append("\n");
                html.append("                </li>\n");
            }

            if (contactInfo.getAddress() != null && !contactInfo.getAddress().isEmpty()) {
                html.append("                <li class=\"contact-item\">\n");
                html.append("                    <i class=\"fas fa-map-marker-alt\"></i> ").append(contactInfo.getAddress());

                if (contactInfo.getCity() != null && !contactInfo.getCity().isEmpty()) {
                    html.append(", ").append(contactInfo.getCity());
                }

                if (contactInfo.getState() != null && !contactInfo.getState().isEmpty()) {
                    html.append(", ").append(contactInfo.getState());
                }

                if (contactInfo.getZipCode() != null && !contactInfo.getZipCode().isEmpty()) {
                    html.append(" ").append(contactInfo.getZipCode());
                }

                html.append("\n");
                html.append("                </li>\n");
            }

            if (contactInfo.getLinkedIn() != null && !contactInfo.getLinkedIn().isEmpty()) {
                html.append("                <li class=\"contact-item\">\n");
                html.append("                    <i class=\"fab fa-linkedin\"></i> ").append(contactInfo.getLinkedIn()).append("\n");
                html.append("                </li>\n");
            }

            if (contactInfo.getWebsite() != null && !contactInfo.getWebsite().isEmpty()) {
                html.append("                <li class=\"contact-item\">\n");
                html.append("                    <i class=\"fas fa-globe\"></i> ").append(contactInfo.getWebsite()).append("\n");
                html.append("                </li>\n");
            }

            html.append("            </ul>\n");
            html.append("        </div>\n");
        }

        // Skills section in sidebar
        List<Skill> skills = resume.getSkills();
        if (skills != null && !skills.isEmpty()) {
            html.append("        <div class=\"sidebar-section\">\n");
            html.append("            <h2 class=\"sidebar-title\">Skills</h2>\n");
            html.append("            <div class=\"skills-list\">\n");

            for (Skill skill : skills) {
                html.append("                <div class=\"skill-item\">\n");
                html.append("                    <div class=\"skill-name\">").append(skill.getName()).append("</div>\n");
                html.append("                    <div class=\"skill-bar\">\n");
                html.append("                        <div class=\"skill-progress\" style=\"width: ").append(skill.getProficiencyLevel() * 20).append("%\"></div>\n");
                html.append("                    </div>\n");
                html.append("                </div>\n");
            }

            html.append("            </div>\n");
            html.append("        </div>\n");
        }

        html.append("    </div>\n");

        // Main content section
        html.append("    <div class=\"resume-main\">\n");

        // Summary section
        if (resume.getSummary() != null && !resume.getSummary().isEmpty()) {
            html.append("        <div class=\"main-section\">\n");
            html.append("            <h2 class=\"section-title\">Professional Summary</h2>\n");
            html.append("            <div class=\"section-content\">\n");
            html.append("                <p>").append(resume.getSummary()).append("</p>\n");
            html.append("            </div>\n");
            html.append("        </div>\n");
        }

        // Experience section
        List<Experience> experiences = resume.getExperiences();
        if (experiences != null && !experiences.isEmpty()) {
            html.append("        <div class=\"main-section\">\n");
            html.append("            <h2 class=\"section-title\">Work Experience</h2>\n");
            html.append("            <div class=\"section-content\">\n");

            for (Experience experience : experiences) {
                html.append("                <div class=\"resume-item\">\n");
                html.append("                    <div class=\"item-header\">\n");
                html.append("                        <div class=\"item-title\">\n");
                html.append("                            <h3>").append(experience.getPosition()).append("</h3>\n");
                html.append("                            <h4>").append(experience.getCompany());

                if (experience.getLocation() != null && !experience.getLocation().isEmpty()) {
                    html.append(", ").append(experience.getLocation());
                }

                html.append("</h4>\n");
                html.append("                        </div>\n");

                if (experience.getStartDate() != null) {
                    html.append("                        <div class=\"item-date\">\n");
                    html.append("                            ").append(DATE_FORMAT.format(experience.getStartDate()));

                    if (experience.isCurrent()) {
                        html.append(" - Present");
                    } else if (experience.getEndDate() != null) {
                        html.append(" - ").append(DATE_FORMAT.format(experience.getEndDate()));
                    }

                    html.append("\n");
                    html.append("                        </div>\n");
                }

                html.append("                    </div>\n");

                if (experience.getDescription() != null && !experience.getDescription().isEmpty()) {
                    html.append("                    <div class=\"item-description\">\n");
                    html.append("                        <p>").append(experience.getDescription()).append("</p>\n");
                    html.append("                    </div>\n");
                }

                html.append("                </div>\n");
            }

            html.append("            </div>\n");
            html.append("        </div>\n");
        }

        // Education section
        List<Education> educations = resume.getEducations();
        if (educations != null && !educations.isEmpty()) {
            html.append("        <div class=\"main-section\">\n");
            html.append("            <h2 class=\"section-title\">Education</h2>\n");
            html.append("            <div class=\"section-content\">\n");

            for (Education education : educations) {
                html.append("                <div class=\"resume-item\">\n");
                html.append("                    <div class=\"item-header\">\n");
                html.append("                        <div class=\"item-title\">\n");
                html.append("                            <h3>").append(education.getDegree());

                if (education.getFieldOfStudy() != null && !education.getFieldOfStudy().isEmpty()) {
                    html.append(", ").append(education.getFieldOfStudy());
                }

                html.append("</h3>\n");
                html.append("                            <h4>").append(education.getInstitution()).append("</h4>\n");
                html.append("                        </div>\n");

                if (education.getStartDate() != null) {
                    html.append("                        <div class=\"item-date\">\n");
                    html.append("                            ").append(DATE_FORMAT.format(education.getStartDate()));

                    if (education.isCurrent()) {
                        html.append(" - Present");
                    } else if (education.getEndDate() != null) {
                        html.append(" - ").append(DATE_FORMAT.format(education.getEndDate()));
                    }

                    html.append("\n");
                    html.append("                        </div>\n");
                }

                html.append("                    </div>\n");

                if (education.getDescription() != null && !education.getDescription().isEmpty()) {
                    html.append("                    <div class=\"item-description\">\n");
                    html.append("                        <p>").append(education.getDescription()).append("</p>\n");
                    html.append("                    </div>\n");
                }

                html.append("                </div>\n");
            }

            html.append("            </div>\n");
            html.append("        </div>\n");
        }

        html.append("    </div>\n");
        html.append("</div>");

        return html.toString();
    }
}
