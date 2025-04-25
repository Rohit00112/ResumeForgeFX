<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="resume-template executive-template">
    <div class="resume-header">
        <div class="header-top">
            <h1 class="resume-name">${sessionScope.user.firstName} ${sessionScope.user.lastName}</h1>
            <div class="resume-title">${resume.title}</div>
        </div>
        
        <div class="header-bottom">
            <div class="contact-info">
                <c:if test="${not empty resume.contactInfo.email}">
                    <div class="contact-item">
                        <i class="fas fa-envelope"></i> ${resume.contactInfo.email}
                    </div>
                </c:if>
                
                <c:if test="${not empty resume.contactInfo.phone}">
                    <div class="contact-item">
                        <i class="fas fa-phone"></i> ${resume.contactInfo.phone}
                    </div>
                </c:if>
                
                <c:if test="${not empty resume.contactInfo.address || not empty resume.contactInfo.city || not empty resume.contactInfo.state || not empty resume.contactInfo.zipCode}">
                    <div class="contact-item">
                        <i class="fas fa-map-marker-alt"></i> 
                        <c:if test="${not empty resume.contactInfo.city}">${resume.contactInfo.city}, </c:if>
                        <c:if test="${not empty resume.contactInfo.state}">${resume.contactInfo.state}</c:if>
                    </div>
                </c:if>
                
                <c:if test="${not empty resume.contactInfo.linkedIn}">
                    <div class="contact-item">
                        <i class="fab fa-linkedin"></i> 
                        <a href="${resume.contactInfo.linkedIn}" class="contact-link">LinkedIn Profile</a>
                    </div>
                </c:if>
                
                <c:if test="${not empty resume.contactInfo.website}">
                    <div class="contact-item">
                        <i class="fas fa-globe"></i> 
                        <a href="${resume.contactInfo.website}" class="contact-link">Personal Website</a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    
    <c:if test="${not empty resume.summary}">
        <div class="resume-section summary-section">
            <h2 class="section-title">Executive Summary</h2>
            <div class="section-content">
                <p class="summary-text">${resume.summary}</p>
            </div>
        </div>
    </c:if>
    
    <c:if test="${not empty resume.experiences}">
        <div class="resume-section experience-section">
            <h2 class="section-title">Professional Experience</h2>
            <div class="section-content">
                <c:forEach var="experience" items="${resume.experiences}">
                    <div class="experience-item">
                        <div class="experience-header">
                            <div class="experience-title-company">
                                <h3 class="experience-title">${experience.position}</h3>
                                <div class="experience-company">${experience.company}<c:if test="${not empty experience.location}">, ${experience.location}</c:if></div>
                            </div>
                            <div class="experience-date">
                                <c:if test="${not empty experience.startDate}">
                                    <fmt:formatDate value="${experience.startDate}" pattern="MMM yyyy" /> - 
                                    <c:choose>
                                        <c:when test="${experience.current}">Present</c:when>
                                        <c:when test="${not empty experience.endDate}"><fmt:formatDate value="${experience.endDate}" pattern="MMM yyyy" /></c:when>
                                    </c:choose>
                                </c:if>
                            </div>
                        </div>
                        <c:if test="${not empty experience.description}">
                            <div class="experience-description">
                                <p>${experience.description}</p>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>
    
    <c:if test="${not empty resume.educations}">
        <div class="resume-section education-section">
            <h2 class="section-title">Education</h2>
            <div class="section-content">
                <div class="education-grid">
                    <c:forEach var="education" items="${resume.educations}">
                        <div class="education-item">
                            <div class="education-details">
                                <h3 class="education-degree">${education.degree}<c:if test="${not empty education.fieldOfStudy}">, ${education.fieldOfStudy}</c:if></h3>
                                <div class="education-institution">${education.institution}</div>
                                <div class="education-date">
                                    <c:if test="${not empty education.startDate}">
                                        <fmt:formatDate value="${education.startDate}" pattern="yyyy" /> - 
                                        <c:choose>
                                            <c:when test="${education.current}">Present</c:when>
                                            <c:when test="${not empty education.endDate}"><fmt:formatDate value="${education.endDate}" pattern="yyyy" /></c:when>
                                        </c:choose>
                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${not empty education.description}">
                                <div class="education-description">
                                    <p>${education.description}</p>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
    
    <c:if test="${not empty resume.skills}">
        <div class="resume-section skills-section">
            <h2 class="section-title">Core Competencies</h2>
            <div class="section-content">
                <div class="skills-grid">
                    <c:forEach var="skill" items="${resume.skills}">
                        <div class="skill-item">
                            <div class="skill-name">${skill.name}</div>
                            <div class="skill-rating">
                                <c:forEach begin="1" end="5" var="i">
                                    <span class="rating-star ${i <= skill.proficiencyLevel ? 'filled' : ''}">
                                        <i class="fas fa-star"></i>
                                    </span>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
    
    <div class="resume-footer">
        <div class="footer-line"></div>
        <div class="footer-text">
            ${sessionScope.user.firstName} ${sessionScope.user.lastName} | 
            <c:if test="${not empty resume.contactInfo.email}">${resume.contactInfo.email} | </c:if>
            <c:if test="${not empty resume.contactInfo.phone}">${resume.contactInfo.phone}</c:if>
        </div>
    </div>
</div>

<style>
    .executive-template {
        font-family: 'Libre Baskerville', 'Times New Roman', serif;
        color: #333;
        line-height: 1.6;
        max-width: 800px;
        margin: 0 auto;
        padding: 2rem;
        background-color: #fff;
        border: 1px solid #e0e0e0;
    }
    
    .executive-template .resume-header {
        text-align: center;
        margin-bottom: 2rem;
        border-bottom: 2px solid #c9a050;
        padding-bottom: 1.5rem;
    }
    
    .executive-template .header-top {
        margin-bottom: 1rem;
    }
    
    .executive-template .resume-name {
        font-size: 2.5rem;
        font-weight: 700;
        margin: 0;
        color: #2c3e50;
        letter-spacing: 1px;
    }
    
    .executive-template .resume-title {
        font-size: 1.2rem;
        color: #7f8c8d;
        margin-top: 0.5rem;
        font-style: italic;
    }
    
    .executive-template .contact-info {
        display: flex;
        justify-content: center;
        flex-wrap: wrap;
        gap: 1.5rem;
        font-size: 0.9rem;
    }
    
    .executive-template .contact-item {
        display: flex;
        align-items: center;
        gap: 0.5rem;
    }
    
    .executive-template .contact-link {
        color: #c9a050;
        text-decoration: none;
    }
    
    .executive-template .contact-link:hover {
        text-decoration: underline;
    }
    
    .executive-template .section-title {
        font-size: 1.5rem;
        color: #2c3e50;
        margin-bottom: 1.5rem;
        position: relative;
        padding-bottom: 0.5rem;
        border-bottom: 1px solid #e0e0e0;
    }
    
    .executive-template .section-title::after {
        content: '';
        position: absolute;
        bottom: -1px;
        left: 0;
        width: 80px;
        height: 3px;
        background-color: #c9a050;
    }
    
    .executive-template .resume-section {
        margin-bottom: 2.5rem;
    }
    
    .executive-template .summary-text {
        font-size: 1.1rem;
        line-height: 1.8;
    }
    
    .executive-template .experience-item {
        margin-bottom: 2rem;
        padding-bottom: 2rem;
        border-bottom: 1px solid #f0f0f0;
    }
    
    .executive-template .experience-item:last-child {
        border-bottom: none;
        padding-bottom: 0;
    }
    
    .executive-template .experience-header {
        display: flex;
        justify-content: space-between;
        margin-bottom: 1rem;
    }
    
    .executive-template .experience-title {
        font-size: 1.3rem;
        font-weight: 700;
        margin: 0 0 0.25rem;
        color: #2c3e50;
    }
    
    .executive-template .experience-company {
        font-size: 1rem;
        color: #7f8c8d;
        font-style: italic;
    }
    
    .executive-template .experience-date {
        font-size: 0.9rem;
        color: #95a5a6;
        text-align: right;
        min-width: 120px;
    }
    
    .executive-template .experience-description {
        font-size: 1rem;
    }
    
    .executive-template .education-grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
        gap: 2rem;
    }
    
    .executive-template .education-item {
        border-left: 3px solid #c9a050;
        padding-left: 1rem;
    }
    
    .executive-template .education-degree {
        font-size: 1.1rem;
        font-weight: 700;
        margin: 0 0 0.25rem;
        color: #2c3e50;
    }
    
    .executive-template .education-institution {
        font-size: 1rem;
        color: #7f8c8d;
        margin-bottom: 0.25rem;
    }
    
    .executive-template .education-date {
        font-size: 0.9rem;
        color: #95a5a6;
        margin-bottom: 0.5rem;
    }
    
    .executive-template .skills-grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
        gap: 1.5rem;
    }
    
    .executive-template .skill-item {
        display: flex;
        flex-direction: column;
        gap: 0.5rem;
    }
    
    .executive-template .skill-name {
        font-size: 1rem;
        font-weight: 500;
    }
    
    .executive-template .skill-rating {
        display: flex;
        gap: 0.25rem;
    }
    
    .executive-template .rating-star {
        color: #e0e0e0;
        font-size: 0.9rem;
    }
    
    .executive-template .rating-star.filled {
        color: #c9a050;
    }
    
    .executive-template .resume-footer {
        margin-top: 3rem;
        text-align: center;
    }
    
    .executive-template .footer-line {
        height: 2px;
        background-color: #c9a050;
        margin-bottom: 1rem;
    }
    
    .executive-template .footer-text {
        font-size: 0.9rem;
        color: #7f8c8d;
    }
    
    @media (max-width: 768px) {
        .executive-template {
            padding: 1.5rem;
        }
        
        .executive-template .experience-header {
            flex-direction: column;
        }
        
        .executive-template .experience-date {
            text-align: left;
            margin-top: 0.5rem;
        }
        
        .executive-template .education-grid,
        .executive-template .skills-grid {
            grid-template-columns: 1fr;
        }
    }
</style>
