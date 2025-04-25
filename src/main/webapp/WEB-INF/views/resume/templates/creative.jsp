<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="resume-template creative-template">
    <div class="resume-header">
        <div class="header-content">
            <div class="name-title">
                <h1 class="resume-name">${sessionScope.user.firstName} ${sessionScope.user.lastName}</h1>
                <div class="resume-title">${resume.title}</div>
            </div>
            <div class="header-right">
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
                </div>
                
                <div class="social-links">
                    <c:if test="${not empty resume.contactInfo.linkedIn}">
                        <a href="${resume.contactInfo.linkedIn}" class="social-link">
                            <i class="fab fa-linkedin"></i>
                        </a>
                    </c:if>
                    
                    <c:if test="${not empty resume.contactInfo.website}">
                        <a href="${resume.contactInfo.website}" class="social-link">
                            <i class="fas fa-globe"></i>
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    
    <div class="resume-body">
        <div class="resume-main">
            <c:if test="${not empty resume.summary}">
                <div class="resume-section summary-section">
                    <h2 class="section-title">About Me</h2>
                    <div class="section-content">
                        <p>${resume.summary}</p>
                    </div>
                </div>
            </c:if>
            
            <c:if test="${not empty resume.experiences}">
                <div class="resume-section experience-section">
                    <h2 class="section-title">Work Experience</h2>
                    <div class="section-content">
                        <div class="timeline">
                            <c:forEach var="experience" items="${resume.experiences}">
                                <div class="timeline-item">
                                    <div class="timeline-marker"></div>
                                    <div class="timeline-content">
                                        <div class="item-header">
                                            <h3 class="item-title">${experience.position}</h3>
                                            <div class="item-meta">
                                                <div class="meta-company">${experience.company}</div>
                                                <c:if test="${not empty experience.location}">
                                                    <div class="meta-location">${experience.location}</div>
                                                </c:if>
                                                <div class="meta-date">
                                                    <c:if test="${not empty experience.startDate}">
                                                        <fmt:formatDate value="${experience.startDate}" pattern="MMM yyyy" /> - 
                                                        <c:choose>
                                                            <c:when test="${experience.current}">Present</c:when>
                                                            <c:when test="${not empty experience.endDate}"><fmt:formatDate value="${experience.endDate}" pattern="MMM yyyy" /></c:when>
                                                        </c:choose>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${not empty experience.description}">
                                            <div class="item-description">
                                                <p>${experience.description}</p>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:if>
            
            <c:if test="${not empty resume.educations}">
                <div class="resume-section education-section">
                    <h2 class="section-title">Education</h2>
                    <div class="section-content">
                        <div class="timeline">
                            <c:forEach var="education" items="${resume.educations}">
                                <div class="timeline-item">
                                    <div class="timeline-marker"></div>
                                    <div class="timeline-content">
                                        <div class="item-header">
                                            <h3 class="item-title">${education.degree}<c:if test="${not empty education.fieldOfStudy}">, ${education.fieldOfStudy}</c:if></h3>
                                            <div class="item-meta">
                                                <div class="meta-institution">${education.institution}</div>
                                                <div class="meta-date">
                                                    <c:if test="${not empty education.startDate}">
                                                        <fmt:formatDate value="${education.startDate}" pattern="MMM yyyy" /> - 
                                                        <c:choose>
                                                            <c:when test="${education.current}">Present</c:when>
                                                            <c:when test="${not empty education.endDate}"><fmt:formatDate value="${education.endDate}" pattern="MMM yyyy" /></c:when>
                                                        </c:choose>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${not empty education.description}">
                                            <div class="item-description">
                                                <p>${education.description}</p>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
        
        <div class="resume-sidebar">
            <c:if test="${not empty resume.skills}">
                <div class="resume-section skills-section">
                    <h2 class="section-title">Skills</h2>
                    <div class="section-content">
                        <div class="skills-container">
                            <c:forEach var="skill" items="${resume.skills}">
                                <div class="skill-item">
                                    <div class="skill-name">${skill.name}</div>
                                    <div class="skill-level">
                                        <c:forEach begin="1" end="5" var="i">
                                            <div class="skill-bubble ${i <= skill.proficiencyLevel ? 'filled' : ''}"></div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:if>
            
            <div class="resume-section contact-section">
                <h2 class="section-title">Contact Details</h2>
                <div class="section-content">
                    <div class="contact-details">
                        <c:if test="${not empty resume.contactInfo.email}">
                            <div class="contact-detail-item">
                                <div class="detail-label">Email</div>
                                <div class="detail-value">${resume.contactInfo.email}</div>
                            </div>
                        </c:if>
                        
                        <c:if test="${not empty resume.contactInfo.phone}">
                            <div class="contact-detail-item">
                                <div class="detail-label">Phone</div>
                                <div class="detail-value">${resume.contactInfo.phone}</div>
                            </div>
                        </c:if>
                        
                        <c:if test="${not empty resume.contactInfo.address}">
                            <div class="contact-detail-item">
                                <div class="detail-label">Address</div>
                                <div class="detail-value">${resume.contactInfo.address}</div>
                            </div>
                        </c:if>
                        
                        <c:if test="${not empty resume.contactInfo.city || not empty resume.contactInfo.state || not empty resume.contactInfo.zipCode}">
                            <div class="contact-detail-item">
                                <div class="detail-label">Location</div>
                                <div class="detail-value">
                                    <c:if test="${not empty resume.contactInfo.city}">${resume.contactInfo.city}, </c:if>
                                    <c:if test="${not empty resume.contactInfo.state}">${resume.contactInfo.state} </c:if>
                                    <c:if test="${not empty resume.contactInfo.zipCode}">${resume.contactInfo.zipCode}</c:if>
                                </div>
                            </div>
                        </c:if>
                        
                        <c:if test="${not empty resume.contactInfo.linkedIn}">
                            <div class="contact-detail-item">
                                <div class="detail-label">LinkedIn</div>
                                <div class="detail-value">
                                    <a href="${resume.contactInfo.linkedIn}" class="detail-link">${resume.contactInfo.linkedIn}</a>
                                </div>
                            </div>
                        </c:if>
                        
                        <c:if test="${not empty resume.contactInfo.website}">
                            <div class="contact-detail-item">
                                <div class="detail-label">Website</div>
                                <div class="detail-value">
                                    <a href="${resume.contactInfo.website}" class="detail-link">${resume.contactInfo.website}</a>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
    .creative-template {
        font-family: 'Montserrat', sans-serif;
        color: #333;
        line-height: 1.6;
        max-width: 800px;
        margin: 0 auto;
        background-color: #fff;
    }
    
    .creative-template .resume-header {
        background-color: #ff6b6b;
        color: white;
        padding: 2rem;
        border-radius: 10px 10px 0 0;
    }
    
    .creative-template .header-content {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    
    .creative-template .resume-name {
        font-size: 2.5rem;
        font-weight: 700;
        margin: 0;
        line-height: 1.2;
    }
    
    .creative-template .resume-title {
        font-size: 1.2rem;
        opacity: 0.9;
        margin-top: 0.5rem;
    }
    
    .creative-template .header-right {
        text-align: right;
    }
    
    .creative-template .contact-info {
        margin-bottom: 1rem;
    }
    
    .creative-template .contact-item {
        margin-bottom: 0.25rem;
        font-size: 0.9rem;
    }
    
    .creative-template .social-links {
        display: flex;
        justify-content: flex-end;
        gap: 0.75rem;
    }
    
    .creative-template .social-link {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 32px;
        height: 32px;
        background-color: rgba(255, 255, 255, 0.2);
        border-radius: 50%;
        color: white;
        text-decoration: none;
        transition: background-color 0.3s;
    }
    
    .creative-template .social-link:hover {
        background-color: rgba(255, 255, 255, 0.3);
    }
    
    .creative-template .resume-body {
        display: flex;
        padding: 2rem;
        background-color: #fff;
        border-radius: 0 0 10px 10px;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    }
    
    .creative-template .resume-main {
        flex: 2;
        padding-right: 2rem;
    }
    
    .creative-template .resume-sidebar {
        flex: 1;
        border-left: 2px solid #f0f0f0;
        padding-left: 2rem;
    }
    
    .creative-template .resume-section {
        margin-bottom: 2.5rem;
    }
    
    .creative-template .section-title {
        font-size: 1.5rem;
        color: #ff6b6b;
        margin-bottom: 1.5rem;
        position: relative;
        display: inline-block;
    }
    
    .creative-template .section-title::after {
        content: '';
        position: absolute;
        bottom: -5px;
        left: 0;
        width: 100%;
        height: 2px;
        background-color: #ff6b6b;
    }
    
    .creative-template .timeline {
        position: relative;
    }
    
    .creative-template .timeline::before {
        content: '';
        position: absolute;
        top: 0;
        bottom: 0;
        left: 7px;
        width: 2px;
        background-color: #f0f0f0;
    }
    
    .creative-template .timeline-item {
        position: relative;
        padding-left: 30px;
        margin-bottom: 2rem;
    }
    
    .creative-template .timeline-marker {
        position: absolute;
        top: 5px;
        left: 0;
        width: 16px;
        height: 16px;
        border-radius: 50%;
        background-color: #ff6b6b;
        border: 2px solid #fff;
        box-shadow: 0 0 0 2px #ff6b6b;
    }
    
    .creative-template .item-title {
        font-size: 1.2rem;
        font-weight: 600;
        margin: 0 0 0.5rem;
        color: #333;
    }
    
    .creative-template .item-meta {
        font-size: 0.9rem;
        color: #777;
        margin-bottom: 0.75rem;
    }
    
    .creative-template .meta-company,
    .creative-template .meta-institution {
        font-weight: 500;
    }
    
    .creative-template .meta-location,
    .creative-template .meta-date {
        font-size: 0.85rem;
        opacity: 0.8;
    }
    
    .creative-template .item-description {
        font-size: 0.95rem;
        color: #555;
    }
    
    .creative-template .skills-container {
        display: flex;
        flex-direction: column;
        gap: 1rem;
    }
    
    .creative-template .skill-item {
        display: flex;
        flex-direction: column;
        gap: 0.5rem;
    }
    
    .creative-template .skill-name {
        font-weight: 500;
        font-size: 0.95rem;
    }
    
    .creative-template .skill-level {
        display: flex;
        gap: 0.25rem;
    }
    
    .creative-template .skill-bubble {
        width: 12px;
        height: 12px;
        border-radius: 50%;
        background-color: #f0f0f0;
    }
    
    .creative-template .skill-bubble.filled {
        background-color: #ff6b6b;
    }
    
    .creative-template .contact-details {
        display: flex;
        flex-direction: column;
        gap: 1rem;
    }
    
    .creative-template .contact-detail-item {
        display: flex;
        flex-direction: column;
        gap: 0.25rem;
    }
    
    .creative-template .detail-label {
        font-size: 0.8rem;
        text-transform: uppercase;
        letter-spacing: 1px;
        color: #999;
    }
    
    .creative-template .detail-value {
        font-size: 0.95rem;
    }
    
    .creative-template .detail-link {
        color: #ff6b6b;
        text-decoration: none;
    }
    
    .creative-template .detail-link:hover {
        text-decoration: underline;
    }
    
    @media (max-width: 768px) {
        .creative-template .header-content {
            flex-direction: column;
            text-align: center;
        }
        
        .creative-template .header-right {
            text-align: center;
            margin-top: 1.5rem;
        }
        
        .creative-template .social-links {
            justify-content: center;
        }
        
        .creative-template .resume-body {
            flex-direction: column;
        }
        
        .creative-template .resume-main {
            padding-right: 0;
            margin-bottom: 2rem;
        }
        
        .creative-template .resume-sidebar {
            border-left: none;
            border-top: 2px solid #f0f0f0;
            padding-left: 0;
            padding-top: 2rem;
        }
    }
</style>
