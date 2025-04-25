<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="row mb-4">
    <div class="col-md-8">
        <h2>${resume.title}</h2>
        <p class="text-muted">
            Created: <fmt:formatDate value="${resume.createdDate}" pattern="MMM dd, yyyy" /> | 
            Last Updated: <fmt:formatDate value="${resume.lastModifiedDate}" pattern="MMM dd, yyyy" />
        </p>
    </div>
    <div class="col-md-4 text-md-end">
        <div class="btn-group">
            <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-outline-secondary">
                <i class="fas fa-arrow-left me-2"></i>Dashboard
            </a>
            <a href="${pageContext.request.contextPath}/resume/edit?id=${resume.id}" class="btn btn-outline-primary">
                <i class="fas fa-edit me-2"></i>Edit
            </a>
            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="fas fa-download me-2"></i>Download
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/resume/download?id=${resume.id}&format=pdf&template=${template}">PDF</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/resume/download?id=${resume.id}&format=html&template=${template}">HTML</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="row mb-4">
    <div class="col-md-12">
        <div class="card shadow">
            <div class="card-header bg-light d-flex justify-content-between align-items-center">
                <h5 class="mb-0">Resume Preview</h5>
                <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        Template: ${template.substring(0, 1).toUpperCase()}${template.substring(1)}
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item ${template == 'classic' ? 'active' : ''}" href="${pageContext.request.contextPath}/resume/view?id=${resume.id}&template=classic">Classic</a></li>
                        <li><a class="dropdown-item ${template == 'modern' ? 'active' : ''}" href="${pageContext.request.contextPath}/resume/view?id=${resume.id}&template=modern">Modern</a></li>
                        <li><a class="dropdown-item ${template == 'creative' ? 'active' : ''}" href="${pageContext.request.contextPath}/resume/view?id=${resume.id}&template=creative">Creative</a></li>
                        <li><a class="dropdown-item ${template == 'executive' ? 'active' : ''}" href="${pageContext.request.contextPath}/resume/view?id=${resume.id}&template=executive">Executive</a></li>
                    </ul>
                </div>
            </div>
            <div class="card-body p-0">
                <div class="resume-preview">
                    <jsp:include page="/WEB-INF/views/resume/templates/${template}.jsp" />
                </div>
            </div>
        </div>
    </div>
</div>
