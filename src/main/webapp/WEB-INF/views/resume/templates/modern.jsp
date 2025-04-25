<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="resume-template modern-template">
    <div class="resume-sidebar">
        <div class="sidebar-header">
            <div class="profile-image">
                <div class="profile-initials">${sessionScope.user.firstName.charAt(0)}${sessionScope.user.lastName.charAt(0)}</div>
            </div>
            <h1 class="resume-name">${sessionScope.user.firstName}<br>${sessionScope.user.lastName}</h1>
        </div>
        
        <div class="sidebar-section">
            <h2 class="sidebar-title">Contact</h2>
            <div class="contact-list">
                <c:if test="${not empty resume.contactInfo.email}">
                    <div class="contact-item">
                        <i class="fas fa-envelope"></i>
                        <span>${resume.contactInfo.email}</span>
                    </div>
                </c:if>
                
                <c:if test="${not empty resume.contactInfo.phone}">
                    <div class="contact-item">
                        <i class="fas fa-phone"></i>
                        <span>${resume.contactInfo.phone}</span>
                    </div>
                </c:if>
                
                <c:if test="${not empty resume.contactInfo.address || not empty resume.contactInfo.city || not empty resume.contactInfo.state || not empty resume.contactInfo.zipCode}">
                    <div class="contact-item">
                        <i class="fas fa-map-marker-alt"></i>
                        <span>
                            <c:if test="${not empty resume.contactInfo.address}">${resume.contactInfo.address},<br></c:if>
                            <c:if test="${not empty resume.contactInfo.city}">${resume.contactInfo.city}, </c:if>
                            <c:if test="${not empty resume.contactInfo.state}">${resume.contactInfo.state} </c:if>
                            <c:if test="${not empty resume.contactInfo.zipCode}">${resume.contactInfo.zipCode}</c:if>
                        </span>
                    </div>
                </c:if>
                
                <c:if test="${not empty resume.contactInfo.linkedIn}">
                    <div class="contact-item">
                        <i class="fab fa-linkedin"></i>
                        <span>${resume.contactInfo.linkedIn}</span>
                    </div>
                </c:if>
                
                <c:if test="${not empty resume.contactInfo.website}">
                    <div class="contact-item">
                        <i class="fas fa-globe"></i>
                        <span>${resume.contactInfo.website}</span>
                    </div>
                </c:if>
            </div>
        </div>
        
        <c:if test="${not empty resume.skills}">
            <div class="sidebar-section">
                <h2 class="sidebar-title">Skills</h2>
                <div class="skills-list">
                    <c:forEach var="skill" items="${resume.skills}">
                        <div class="skill-item">
                            <div class="skill-name">${skill.name}</div>
                            <div class="skill-bar">
                                <div class="skill-progress" style="width: ${skill.proficiencyLevel * 20}%"></div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </div>
    
    <div class="resume-main">
        <c:if test="${not empty resume.summary}">
            <div class="main-section">
                <h2 class="section-title">Professional Summary</h2>
                <div class="section-content">
                    <p>${resume.summary}</p>
                </div>
            </div>
        </c:if>
        
        <c:if test="${not empty resume.experiences}">
            <div class="main-section">
                <h2 class="section-title">Work Experience</h2>
                <div class="section-content">
                    <c:forEach var="experience" items="${resume.experiences}">
                        <div class="resume-item">
                            <div class="item-header">
                                <div class="item-title">
                                    <h3>${experience.position}</h3>
                                    <h4>${experience.company}<c:if test="${not empty experience.location}">, ${experience.location}</c:if></h4>
                                </div>
                                <div class="item-date">
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
                                <div class="item-description">
                                    <p>${experience.description}</p>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>
        
        <c:if test="${not empty resume.educations}">
            <div class="main-section">
                <h2 class="section-title">Education</h2>
                <div class="section-content">
                    <c:forEach var="education" items="${resume.educations}">
                        <div class="resume-item">
                            <div class="item-header">
                                <div class="item-title">
                                    <h3>${education.degree}<c:if test="${not empty education.fieldOfStudy}">, ${education.fieldOfStudy}</c:if></h3>
                                    <h4>${education.institution}</h4>
                                </div>
                                <div class="item-date">
                                    <c:if test="${not empty education.startDate}">
                                        <fmt:formatDate value="${education.startDate}" pattern="MMM yyyy" /> - 
                                        <c:choose>
                                            <c:when test="${education.current}">Present</c:when>
                                            <c:when test="${not empty education.endDate}"><fmt:formatDate value="${education.endDate}" pattern="MMM yyyy" /></c:when>
                                        </c:choose>
                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${not empty education.description}">
                                <div class="item-description">
                                    <p>${education.description}</p>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </div>
</div>

<style>
    .resume-template {
        font-family: 'Roboto', sans-serif;
        color: #333;
        line-height: 1.6;
        display: flex;
        max-width: 800px;
        margin: 0 auto;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    
    .modern-template .resume-sidebar {
        width: 35%;
        background-color: #2c3e50;
        color: white;
        padding: 2rem;
    }
    
    .modern-template .resume-main {
        width: 65%;
        padding: 2rem;
        background-color: white;
    }
    
    .modern-template .sidebar-header {
        text-align: center;
        margin-bottom: 2rem;
    }
    
    .modern-template .profile-image {
        width: 120px;
        height: 120px;
        border-radius: 50%;
        background-color: #3498db;
        margin: 0 auto 1rem;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    
    .modern-template .profile-initials {
        font-size: 2.5rem;
        font-weight: bold;
    }
    
    .modern-template .resume-name {
        font-size: 1.8rem;
        margin-bottom: 0.5rem;
        line-height: 1.2;
    }
    
    .modern-template .sidebar-title {
        font-size: 1.3rem;
        margin-bottom: 1rem;
        position: relative;
        padding-bottom: 0.5rem;
    }
    
    .modern-template .sidebar-title::after {
        content: '';
        position: absolute;
        bottom: 0;
        left: 0;
        width: 50px;
        height: 3px;
        background-color: #3498db;
    }
    
    .modern-template .sidebar-section {
        margin-bottom: 2rem;
    }
    
    .modern-template .contact-item {
        display: flex;
        align-items: flex-start;
        margin-bottom: 0.75rem;
        font-size: 0.9rem;
    }
    
    .modern-template .contact-item i {
        width: 20px;
        margin-right: 0.75rem;
        margin-top: 0.25rem;
    }
    
    .modern-template .skill-item {
        margin-bottom: 1rem;
    }
    
    .modern-template .skill-name {
        font-size: 0.9rem;
        margin-bottom: 0.25rem;
    }
    
    .modern-template .skill-bar {
        height: 6px;
        background-color: rgba(255, 255, 255, 0.2);
        border-radius: 3px;
        overflow: hidden;
    }
    
    .modern-template .skill-progress {
        height: 100%;
        background-color: #3498db;
    }
    
    .modern-template .section-title {
        font-size: 1.5rem;
        margin-bottom: 1.5rem;
        color: #2c3e50;
        position: relative;
        padding-bottom: 0.5rem;
    }
    
    .modern-template .section-title::after {
        content: '';
        position: absolute;
        bottom: 0;
        left: 0;
        width: 50px;
        height: 3px;
        background-color: #3498db;
    }
    
    .modern-template .main-section {
        margin-bottom: 2rem;
    }
    
    .modern-template .resume-item {
        margin-bottom: 1.5rem;
    }
    
    .modern-template .item-header {
        display: flex;
        justify-content: space-between;
        margin-bottom: 0.5rem;
    }
    
    .modern-template .item-title h3 {
        font-size: 1.1rem;
        margin: 0;
        color: #2c3e50;
    }
    
    .modern-template .item-title h4 {
        font-size: 0.9rem;
        margin: 0;
        font-weight: normal;
        color: #7f8c8d;
    }
    
    .modern-template .item-date {
        font-size: 0.85rem;
        color: #95a5a6;
    }
    
    .modern-template .item-description {
        font-size: 0.9rem;
    }
    
    @media print {
        .resume-template {
            box-shadow: none;
        }
    }
    
    @media (max-width: 768px) {
        .resume-template {
            flex-direction: column;
        }
        
        .modern-template .resume-sidebar,
        .modern-template .resume-main {
            width: 100%;
        }
    }
</style>
