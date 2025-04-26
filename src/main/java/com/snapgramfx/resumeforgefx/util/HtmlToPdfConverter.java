package com.snapgramfx.resumeforgefx.util;

import com.lowagie.text.DocumentException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Utility class for converting HTML to PDF using Flying Saucer and iText.
 */
public class HtmlToPdfConverter {

    /**
     * Convert HTML content to PDF.
     *
     * @param htmlContent the HTML content to convert
     * @return a byte array containing the PDF data
     * @throws IOException if an I/O error occurs
     * @throws DocumentException if a document error occurs
     */
    public static byte[] convertHtmlToPdf(String htmlContent) throws IOException, DocumentException {
        // Clean and prepare the HTML for PDF conversion
        Document document = Jsoup.parse(htmlContent);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

        // Add necessary CSS for PDF rendering
        Element head = document.head();

        // Add base styles for PDF
        head.appendElement("style").text(
            "@page { size: A4; margin: 2cm; }" +
            "body { font-family: 'Helvetica', 'Arial', sans-serif; }" +
            "a { text-decoration: none; }"
        );

        // Convert to XHTML
        String xhtml = document.html();

        // Create PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(xhtml);
        renderer.layout();
        renderer.createPDF(outputStream);

        return outputStream.toByteArray();
    }

    /**
     * Convert HTML content to PDF with custom CSS.
     *
     * @param htmlContent the HTML content to convert
     * @param customCss custom CSS to apply to the PDF
     * @return a byte array containing the PDF data
     * @throws IOException if an I/O error occurs
     * @throws DocumentException if a document error occurs
     */
    public static byte[] convertHtmlToPdf(String htmlContent, String customCss) throws IOException, DocumentException {
        // Clean and prepare the HTML for PDF conversion
        Document document = Jsoup.parse(htmlContent);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

        // Add necessary CSS for PDF rendering
        Element head = document.head();

        // Add base styles for PDF
        head.appendElement("style").text(
            "@page { size: A4; margin: 2cm; }" +
            "body { font-family: 'Helvetica', 'Arial', sans-serif; }" +
            "a { text-decoration: none; }"
        );

        // Add custom CSS
        if (customCss != null && !customCss.isEmpty()) {
            head.appendElement("style").text(customCss);
        }

        // Convert to XHTML
        String xhtml = document.html();

        // Create PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(xhtml);
        renderer.layout();
        renderer.createPDF(outputStream);

        return outputStream.toByteArray();
    }

    /**
     * Generate custom CSS for resume templates based on customization parameters.
     *
     * @param template the template name
     * @param primaryColor the primary color
     * @param secondaryColor the secondary color
     * @param accentColor the accent color
     * @param fontFamily the font family
     * @param fontSize the font size
     * @return custom CSS string
     */
    public static String generateCustomCss(String template, String primaryColor, String secondaryColor,
                                          String accentColor, String fontFamily, String fontSize) {
        StringBuilder css = new StringBuilder();

        // Add font imports if needed
        if (fontFamily != null && !fontFamily.isEmpty() && !fontFamily.contains("Arial") && !fontFamily.contains("Georgia")) {
            String fontName = fontFamily.split(",")[0].replace("'", "").trim();
            css.append("@import url('https://fonts.googleapis.com/css2?family=").append(fontName).append("&display=swap');\n");
        }

        if (template.equals("classic")) {
            css.append(".classic-template {");
            if (fontFamily != null && !fontFamily.isEmpty()) {
                css.append("font-family: ").append(fontFamily).append(";");
            }
            if (fontSize != null && !fontSize.isEmpty()) {
                css.append("font-size: ").append(fontSize).append("px;");
            }
            css.append("}\n");

            if (primaryColor != null && !primaryColor.isEmpty()) {
                css.append(".classic-template .section-title {");
                css.append("color: ").append(primaryColor).append(";");
                if (secondaryColor != null && !secondaryColor.isEmpty()) {
                    css.append("border-bottom-color: ").append(secondaryColor).append(";");
                }
                css.append("}\n");

                css.append(".classic-template .item-title h3 {");
                css.append("color: ").append(primaryColor).append(";");
                css.append("}\n");
            }

            if (accentColor != null && !accentColor.isEmpty()) {
                css.append(".classic-template .skill-dot.filled {");
                css.append("background-color: ").append(accentColor).append(";");
                css.append("border-color: ").append(accentColor).append(";");
                css.append("}\n");
            }
        } else if (template.equals("modern")) {
            css.append(".modern-template {");
            if (fontFamily != null && !fontFamily.isEmpty()) {
                css.append("font-family: ").append(fontFamily).append(";");
            }
            if (fontSize != null && !fontSize.isEmpty()) {
                css.append("font-size: ").append(fontSize).append("px;");
            }
            css.append("}\n");

            if (primaryColor != null && !primaryColor.isEmpty()) {
                css.append(".modern-template .resume-sidebar {");
                css.append("background-color: ").append(primaryColor).append(";");
                css.append("}\n");
            }

            if (accentColor != null && !accentColor.isEmpty()) {
                css.append(".modern-template .profile-image {");
                css.append("background-color: ").append(accentColor).append(";");
                css.append("}\n");

                css.append(".modern-template .sidebar-title::after,");
                css.append(".modern-template .section-title::after {");
                css.append("background-color: ").append(accentColor).append(";");
                css.append("}\n");

                css.append(".modern-template .skill-progress {");
                css.append("background-color: ").append(accentColor).append(";");
                css.append("}\n");
            }
        } else if (template.equals("creative")) {
            css.append(".creative-template {");
            if (fontFamily != null && !fontFamily.isEmpty()) {
                css.append("font-family: ").append(fontFamily).append(";");
            }
            if (fontSize != null && !fontSize.isEmpty()) {
                css.append("font-size: ").append(fontSize).append("px;");
            }
            css.append("}\n");

            if (primaryColor != null && !primaryColor.isEmpty()) {
                css.append(".creative-template .resume-header {");
                css.append("background-color: ").append(primaryColor).append(";");
                css.append("}\n");

                css.append(".creative-template .section-title {");
                css.append("color: ").append(primaryColor).append(";");
                css.append("}\n");

                css.append(".creative-template .section-title::after {");
                css.append("background-color: ").append(primaryColor).append(";");
                css.append("}\n");
            }

            if (accentColor != null && !accentColor.isEmpty()) {
                css.append(".creative-template .timeline-marker {");
                css.append("background-color: ").append(accentColor).append(";");
                css.append("box-shadow: 0 0 0 2px ").append(accentColor).append(";");
                css.append("}\n");

                css.append(".creative-template .skill-bubble.filled {");
                css.append("background-color: ").append(accentColor).append(";");
                css.append("}\n");

                css.append(".creative-template .detail-link {");
                css.append("color: ").append(accentColor).append(";");
                css.append("}\n");
            }
        } else if (template.equals("executive")) {
            css.append(".executive-template {");
            if (fontFamily != null && !fontFamily.isEmpty()) {
                css.append("font-family: ").append(fontFamily).append(";");
            }
            if (fontSize != null && !fontSize.isEmpty()) {
                css.append("font-size: ").append(fontSize).append("px;");
            }
            css.append("}\n");

            if (accentColor != null && !accentColor.isEmpty()) {
                css.append(".executive-template .resume-header {");
                css.append("border-bottom-color: ").append(accentColor).append(";");
                css.append("}\n");

                css.append(".executive-template .section-title::after {");
                css.append("background-color: ").append(accentColor).append(";");
                css.append("}\n");

                css.append(".executive-template .contact-link {");
                css.append("color: ").append(accentColor).append(";");
                css.append("}\n");

                css.append(".executive-template .education-item {");
                css.append("border-left-color: ").append(accentColor).append(";");
                css.append("}\n");

                css.append(".executive-template .rating-star.filled {");
                css.append("color: ").append(accentColor).append(";");
                css.append("}\n");

                css.append(".executive-template .footer-line {");
                css.append("background-color: ").append(accentColor).append(";");
                css.append("}\n");
            }
        }

        return css.toString();
    }
}
