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
                <div class="d-flex gap-2">
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
                    <button type="button" class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#customizeModal">
                        <i class="fas fa-palette me-1"></i>Customize
                    </button>
                </div>
            </div>
            <div class="card-body p-0">
                <div class="resume-preview">
                    <jsp:include page="/WEB-INF/views/resume/templates/${template}.jsp" />
                </div>

                <!-- Apply server-side customization if provided -->
                <c:if test="${not empty primaryColor or not empty secondaryColor or not empty accentColor or not empty fontFamily or not empty fontSize}">
                    <script>
                        document.addEventListener('DOMContentLoaded', function() {
                            // Create a style element
                            let styleElement = document.getElementById('server-custom-resume-style');
                            if (!styleElement) {
                                styleElement = document.createElement('style');
                                styleElement.id = 'server-custom-resume-style';
                                document.head.appendChild(styleElement);
                            }

                            // Set custom styles based on the template
                            const template = '${template}';
                            let customStyles = '';

                            if (template === 'classic') {
                                customStyles = `
                                    .classic-template {
                                        <c:if test="${not empty fontFamily}">font-family: ${fontFamily};</c:if>
                                        <c:if test="${not empty fontSize}">font-size: ${fontSize}px;</c:if>
                                    }
                                    <c:if test="${not empty primaryColor}">
                                    .classic-template .section-title {
                                        color: ${primaryColor};
                                        <c:if test="${not empty secondaryColor}">border-bottom-color: ${secondaryColor};</c:if>
                                    }
                                    .classic-template .item-title h3 {
                                        color: ${primaryColor};
                                    }
                                    </c:if>
                                    <c:if test="${not empty accentColor}">
                                    .classic-template .skill-dot.filled {
                                        background-color: ${accentColor};
                                        border-color: ${accentColor};
                                    }
                                    </c:if>
                                `;
                            } else if (template === 'modern') {
                                customStyles = `
                                    .modern-template {
                                        <c:if test="${not empty fontFamily}">font-family: ${fontFamily};</c:if>
                                        <c:if test="${not empty fontSize}">font-size: ${fontSize}px;</c:if>
                                    }
                                    <c:if test="${not empty primaryColor}">
                                    .modern-template .resume-sidebar {
                                        background-color: ${primaryColor};
                                    }
                                    </c:if>
                                    <c:if test="${not empty accentColor}">
                                    .modern-template .profile-image {
                                        background-color: ${accentColor};
                                    }
                                    .modern-template .sidebar-title::after,
                                    .modern-template .section-title::after {
                                        background-color: ${accentColor};
                                    }
                                    .modern-template .skill-progress {
                                        background-color: ${accentColor};
                                    }
                                    </c:if>
                                `;
                            } else if (template === 'creative') {
                                customStyles = `
                                    .creative-template {
                                        <c:if test="${not empty fontFamily}">font-family: ${fontFamily};</c:if>
                                        <c:if test="${not empty fontSize}">font-size: ${fontSize}px;</c:if>
                                    }
                                    <c:if test="${not empty primaryColor}">
                                    .creative-template .resume-header {
                                        background-color: ${primaryColor};
                                    }
                                    .creative-template .section-title {
                                        color: ${primaryColor};
                                    }
                                    .creative-template .section-title::after {
                                        background-color: ${primaryColor};
                                    }
                                    </c:if>
                                    <c:if test="${not empty accentColor}">
                                    .creative-template .timeline-marker {
                                        background-color: ${accentColor};
                                        box-shadow: 0 0 0 2px ${accentColor};
                                    }
                                    .creative-template .skill-bubble.filled {
                                        background-color: ${accentColor};
                                    }
                                    .creative-template .detail-link {
                                        color: ${accentColor};
                                    }
                                    </c:if>
                                `;
                            } else if (template === 'executive') {
                                customStyles = `
                                    .executive-template {
                                        <c:if test="${not empty fontFamily}">font-family: ${fontFamily};</c:if>
                                        <c:if test="${not empty fontSize}">font-size: ${fontSize}px;</c:if>
                                    }
                                    <c:if test="${not empty accentColor}">
                                    .executive-template .resume-header {
                                        border-bottom-color: ${accentColor};
                                    }
                                    .executive-template .section-title::after {
                                        background-color: ${accentColor};
                                    }
                                    .executive-template .contact-link {
                                        color: ${accentColor};
                                    }
                                    .executive-template .education-item {
                                        border-left-color: ${accentColor};
                                    }
                                    .executive-template .rating-star.filled {
                                        color: ${accentColor};
                                    }
                                    .executive-template .footer-line {
                                        background-color: ${accentColor};
                                    }
                                    </c:if>
                                `;
                            }

                            styleElement.textContent = customStyles;
                        });
                    </script>
                </c:if>
            </div>
        </div>
    </div>
</div>

<!-- Customize Modal -->
<div class="modal fade" id="customizeModal" tabindex="-1" aria-labelledby="customizeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="customizeModalLabel">Customize Template</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="customizeForm">
                    <div class="mb-3">
                        <label for="primaryColor" class="form-label">Primary Color</label>
                        <div class="input-group">
                            <input type="color" class="form-control form-control-color" id="primaryColor" value="#4e73df">
                            <input type="text" class="form-control" id="primaryColorHex" value="#4e73df">
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="secondaryColor" class="form-label">Secondary Color</label>
                        <div class="input-group">
                            <input type="color" class="form-control form-control-color" id="secondaryColor" value="#858796">
                            <input type="text" class="form-control" id="secondaryColorHex" value="#858796">
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="accentColor" class="form-label">Accent Color</label>
                        <div class="input-group">
                            <input type="color" class="form-control form-control-color" id="accentColor" value="#1cc88a">
                            <input type="text" class="form-control" id="accentColorHex" value="#1cc88a">
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="fontFamily" class="form-label">Font Family</label>
                        <select class="form-select" id="fontFamily">
                            <option value="'Nunito', sans-serif">Nunito (Default)</option>
                            <option value="'Roboto', sans-serif">Roboto</option>
                            <option value="'Montserrat', sans-serif">Montserrat</option>
                            <option value="'Libre Baskerville', serif">Libre Baskerville</option>
                            <option value="'Georgia', serif">Georgia</option>
                            <option value="'Arial', sans-serif">Arial</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="fontSize" class="form-label">Base Font Size</label>
                        <div class="input-group">
                            <input type="range" class="form-range" id="fontSize" min="12" max="18" step="1" value="14">
                            <span class="input-group-text" id="fontSizeValue">14px</span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" id="applyCustomization">Apply Changes</button>
                <button type="button" class="btn btn-success" id="saveCustomization">Save Settings</button>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Color picker synchronization
        document.getElementById('primaryColor').addEventListener('input', function() {
            document.getElementById('primaryColorHex').value = this.value;
        });

        document.getElementById('primaryColorHex').addEventListener('input', function() {
            document.getElementById('primaryColor').value = this.value;
        });

        document.getElementById('secondaryColor').addEventListener('input', function() {
            document.getElementById('secondaryColorHex').value = this.value;
        });

        document.getElementById('secondaryColorHex').addEventListener('input', function() {
            document.getElementById('secondaryColor').value = this.value;
        });

        document.getElementById('accentColor').addEventListener('input', function() {
            document.getElementById('accentColorHex').value = this.value;
        });

        document.getElementById('accentColorHex').addEventListener('input', function() {
            document.getElementById('accentColorHex').value = this.value;
        });

        // Font size slider
        document.getElementById('fontSize').addEventListener('input', function() {
            document.getElementById('fontSizeValue').textContent = this.value + 'px';
        });

        // Apply customization
        document.getElementById('applyCustomization').addEventListener('click', function() {
            const primaryColor = document.getElementById('primaryColor').value;
            const secondaryColor = document.getElementById('secondaryColor').value;
            const accentColor = document.getElementById('accentColor').value;
            const fontFamily = document.getElementById('fontFamily').value;
            const fontSize = document.getElementById('fontSize').value;

            // Create a style element
            let styleElement = document.getElementById('custom-resume-style');
            if (!styleElement) {
                styleElement = document.createElement('style');
                styleElement.id = 'custom-resume-style';
                document.head.appendChild(styleElement);
            }

            // Set custom styles based on the template
            const template = '${template}';
            let customStyles = '';

            if (template === 'classic') {
                customStyles = `
                    .classic-template {
                        font-family: ${fontFamily};
                        font-size: ${fontSize}px;
                    }
                    .classic-template .section-title {
                        color: ${primaryColor};
                        border-bottom-color: ${secondaryColor};
                    }
                    .classic-template .item-title h3 {
                        color: ${primaryColor};
                    }
                    .classic-template .skill-dot.filled {
                        background-color: ${accentColor};
                        border-color: ${accentColor};
                    }
                `;
            } else if (template === 'modern') {
                customStyles = `
                    .modern-template {
                        font-family: ${fontFamily};
                        font-size: ${fontSize}px;
                    }
                    .modern-template .resume-sidebar {
                        background-color: ${primaryColor};
                    }
                    .modern-template .profile-image {
                        background-color: ${accentColor};
                    }
                    .modern-template .sidebar-title::after,
                    .modern-template .section-title::after {
                        background-color: ${accentColor};
                    }
                    .modern-template .skill-progress {
                        background-color: ${accentColor};
                    }
                `;
            } else if (template === 'creative') {
                customStyles = `
                    .creative-template {
                        font-family: ${fontFamily};
                        font-size: ${fontSize}px;
                    }
                    .creative-template .resume-header {
                        background-color: ${primaryColor};
                    }
                    .creative-template .section-title {
                        color: ${primaryColor};
                    }
                    .creative-template .section-title::after {
                        background-color: ${primaryColor};
                    }
                    .creative-template .timeline-marker {
                        background-color: ${accentColor};
                        box-shadow: 0 0 0 2px ${accentColor};
                    }
                    .creative-template .skill-bubble.filled {
                        background-color: ${accentColor};
                    }
                    .creative-template .detail-link {
                        color: ${accentColor};
                    }
                `;
            } else if (template === 'executive') {
                customStyles = `
                    .executive-template {
                        font-family: ${fontFamily};
                        font-size: ${fontSize}px;
                    }
                    .executive-template .resume-header {
                        border-bottom-color: ${accentColor};
                    }
                    .executive-template .section-title::after {
                        background-color: ${accentColor};
                    }
                    .executive-template .contact-link {
                        color: ${accentColor};
                    }
                    .executive-template .education-item {
                        border-left-color: ${accentColor};
                    }
                    .executive-template .rating-star.filled {
                        color: ${accentColor};
                    }
                    .executive-template .footer-line {
                        background-color: ${accentColor};
                    }
                `;
            }

            styleElement.textContent = customStyles;

            // Close the modal
            const modal = bootstrap.Modal.getInstance(document.getElementById('customizeModal'));
            modal.hide();
        });

        // Save customization settings
        document.getElementById('saveCustomization').addEventListener('click', function() {
            const primaryColor = document.getElementById('primaryColor').value;
            const secondaryColor = document.getElementById('secondaryColor').value;
            const accentColor = document.getElementById('accentColor').value;
            const fontFamily = document.getElementById('fontFamily').value;
            const fontSize = document.getElementById('fontSize').value;

            // Update download links with customization parameters
            const downloadLinks = document.querySelectorAll('a[href*="/resume/download"]');
            downloadLinks.forEach(link => {
                let url = new URL(link.href);
                url.searchParams.set('primaryColor', primaryColor);
                url.searchParams.set('secondaryColor', secondaryColor);
                url.searchParams.set('accentColor', accentColor);
                url.searchParams.set('fontFamily', fontFamily);
                url.searchParams.set('fontSize', fontSize);
                link.href = url.toString();
            });

            // Show success message
            const successAlert = document.createElement('div');
            successAlert.className = 'alert alert-success alert-dismissible fade show';
            successAlert.innerHTML = `
                <strong>Success!</strong> Your customization settings have been saved and will be applied to downloaded resumes.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            `;
            document.querySelector('.row.mb-4').insertAdjacentElement('afterend', successAlert);

            // Close the modal
            const modal = bootstrap.Modal.getInstance(document.getElementById('customizeModal'));
            modal.hide();
        });
    });
</script>