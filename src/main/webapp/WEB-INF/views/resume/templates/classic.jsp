<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="resume-template classic-template">
    <div class="resume-header">
        <h1 class="resume-name">${sessionScope.user.firstName} ${sessionScope.user.lastName}</h1>
        
        <div class="resume-contact">
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
                    <c:if test="${not empty resume.contactInfo.address}">${resume.contactInfo.address}, </c:if>
                    <c:if test="${not empty resume.contactInfo.city}">${resume.contactInfo.city}, </c:if>
                    <c:if test="${not empty resume.contactInfo.state}">${resume.contactInfo.state} </c:if>
                    <c:if test="${not empty resume.contactInfo.zipCode}">${resume.contactInfo.zipCode}</c:if>
                </div>
            </c:if>
            
            <c:if test="${not empty resume.contactInfo.linkedIn}">
                <div class="contact-item">
                    <i class="fab fa-linkedin"></i> ${resume.contactInfo.linkedIn}
                </div>
            </c:if>
            
            <c:if test="${not empty resume.contactInfo.website}">
                <div class="contact-item">
                    <i class="fas fa-globe"></i> ${resume.contactInfo.website}
                </div>
            </c:if>
        </div>
    </div>
    
    <c:if test="${not empty resume.summary}">
        <div class="resume-section">
            <h2 class="section-title">Professional Summary</h2>
            <div class="section-content">
                <p>${resume.summary}</p>
            </div>
        </div>
    </c:if>
    
    <c:if test="${not empty resume.experiences}">
        <div class="resume-section">
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
        <div class="resume-section">
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
    
    <c:if test="${not empty resume.skills}">
        <div class="resume-section">
            <h2 class="section-title">Skills</h2>
            <div class="section-content">
                <div class="skills-list">
                    <c:forEach var="skill" items="${resume.skills}">
                        <div class="skill-item">
                            <span class="skill-name">${skill.name}</span>
                            <div class="skill-level">
                                <c:forEach begin="1" end="5" var="i">
                                    <div class="skill-dot ${i <= skill.proficiencyLevel ? 'filled' : ''}"></div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
</div>

<style>
    .resume-template {
        font-family: 'Georgia', serif;
        color: #333;
        line-height: 1.6;
        padding: 2rem;
        max-width: 800px;
        margin: 0 auto;
    }
    
    .classic-template .resume-header {
        text-align: center;
        margin-bottom: 2rem;
        border-bottom: 2px solid #333;
        padding-bottom: 1rem;
    }
    
    .classic-template .resume-name {
        font-size: 2.5rem;
        margin-bottom: 0.5rem;
        color: #333;
    }
    
    .classic-template .resume-contact {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 1rem;
        font-size: 0.9rem;
    }
    
    .classic-template .contact-item {
        display: flex;
        align-items: center;
        gap: 0.5rem;
    }
    
    .classic-template .section-title {
        font-size: 1.5rem;
        margin-bottom: 1rem;
        color: #333;
        border-bottom: 1px solid #ddd;
        padding-bottom: 0.5rem;
    }
    
    .classic-template .resume-section {
        margin-bottom: 2rem;
    }
    
    .classic-template .resume-item {
        margin-bottom: 1.5rem;
    }
    
    .classic-template .item-header {
        display: flex;
        justify-content: space-between;
        margin-bottom: 0.5rem;
    }
    
    .classic-template .item-title h3 {
        font-size: 1.2rem;
        margin: 0;
        color: #333;
    }
    
    .classic-template .item-title h4 {
        font-size: 1rem;
        margin: 0;
        font-weight: normal;
        color: #555;
    }
    
    .classic-template .item-date {
        font-size: 0.9rem;
        color: #777;
    }
    
    .classic-template .item-description {
        font-size: 0.95rem;
    }
    
    .classic-template .skills-list {
        display: flex;
        flex-wrap: wrap;
        gap: 1rem;
    }
    
    .classic-template .skill-item {
        width: calc(50% - 1rem);
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 0.5rem;
    }
    
    .classic-template .skill-name {
        font-size: 0.95rem;
    }
    
    .classic-template .skill-level {
        display: flex;
        gap: 0.25rem;
    }
    
    .classic-template .skill-dot {
        width: 10px;
        height: 10px;
        border-radius: 50%;
        background-color: #eee;
        border: 1px solid #ccc;
    }
    
    .classic-template .skill-dot.filled {
        background-color: #333;
        border-color: #333;
    }
    
    @media print {
        .resume-template {
            padding: 0;
        }
        
        body {
            background-color: white;
        }
    }
</style>
