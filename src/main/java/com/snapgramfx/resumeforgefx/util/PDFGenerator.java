package com.snapgramfx.resumeforgefx.util;

import com.snapgramfx.resumeforgefx.model.*;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Utility class for generating PDF resumes.
 *
 * Note: This is a placeholder implementation. In a real application, you would use a PDF library
 * like iText, Apache PDFBox, or Flying Saucer with HTML to PDF conversion.
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
        // This is a placeholder implementation
        // In a real application, you would use a PDF library to generate the PDF

        // For now, we'll just return a simple byte array
        // In a real implementation, you would:
        // 1. Create a PDF document
        // 2. Add content based on the resume and template
        // 3. Apply customization options (colors, fonts, etc.)
        // 4. Return the PDF as a byte array

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Placeholder for PDF generation
        // In a real implementation, you would use a PDF library here

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
}
