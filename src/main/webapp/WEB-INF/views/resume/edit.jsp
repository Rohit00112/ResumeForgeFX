<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="row mb-4">
    <div class="col-md-8">
        <h2>Edit Resume</h2>
        <p class="lead">Update your resume information.</p>
    </div>
    <div class="col-md-4 text-md-end">
        <div class="btn-group">
            <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-outline-secondary">
                <i class="fas fa-arrow-left me-2"></i>Back to Dashboard
            </a>
            <a href="${pageContext.request.contextPath}/resume/view?id=${resume.id}" class="btn btn-outline-primary">
                <i class="fas fa-eye me-2"></i>Preview
            </a>
        </div>
    </div>
</div>

<c:if test="${not empty success}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        ${success}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        ${error}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<div class="row">
    <div class="col-md-12">
        <form action="${pageContext.request.contextPath}/resume/edit" method="post" id="resumeForm">
            <input type="hidden" name="id" value="${resume.id}">
            
            <!-- Basic Information Card -->
            <div class="card shadow mb-4">
                <div class="card-header bg-light">
                    <h5 class="mb-0">Basic Information</h5>
                </div>
                <div class="card-body">
                    <div class="mb-3">
                        <label for="title" class="form-label">Resume Title</label>
                        <input type="text" class="form-control" id="title" name="title" required
                               value="${resume.title}">
                    </div>
                    
                    <div class="mb-3">
                        <label for="summary" class="form-label">Professional Summary</label>
                        <textarea class="form-control" id="summary" name="summary" rows="4">${resume.summary}</textarea>
                    </div>
                </div>
            </div>
            
            <!-- Contact Information Card -->
            <div class="card shadow mb-4">
                <div class="card-header bg-light">
                    <h5 class="mb-0">Contact Information</h5>
                </div>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required
                                   value="${resume.contactInfo.email}">
                        </div>
                        <div class="col-md-6">
                            <label for="phone" class="form-label">Phone</label>
                            <input type="tel" class="form-control" id="phone" name="phone" 
                                   value="${resume.contactInfo.phone}">
                        </div>
                    </div>
                    
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" name="address" 
                               value="${resume.contactInfo.address}">
                    </div>
                    
                    <div class="row mb-3">
                        <div class="col-md-4">
                            <label for="city" class="form-label">City</label>
                            <input type="text" class="form-control" id="city" name="city" 
                                   value="${resume.contactInfo.city}">
                        </div>
                        <div class="col-md-4">
                            <label for="state" class="form-label">State/Province</label>
                            <input type="text" class="form-control" id="state" name="state" 
                                   value="${resume.contactInfo.state}">
                        </div>
                        <div class="col-md-4">
                            <label for="zipCode" class="form-label">Zip/Postal Code</label>
                            <input type="text" class="form-control" id="zipCode" name="zipCode" 
                                   value="${resume.contactInfo.zipCode}">
                        </div>
                    </div>
                    
                    <div class="mb-3">
                        <label for="country" class="form-label">Country</label>
                        <input type="text" class="form-control" id="country" name="country" 
                               value="${resume.contactInfo.country}">
                    </div>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="linkedin" class="form-label">LinkedIn Profile</label>
                            <input type="url" class="form-control" id="linkedin" name="linkedin" 
                                   value="${resume.contactInfo.linkedIn}">
                        </div>
                        <div class="col-md-6">
                            <label for="website" class="form-label">Personal Website</label>
                            <input type="url" class="form-control" id="website" name="website" 
                                   value="${resume.contactInfo.website}">
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- Education Card -->
            <div class="card shadow mb-4">
                <div class="card-header bg-light d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Education</h5>
                    <button type="button" class="btn btn-sm btn-primary" id="addEducation">
                        <i class="fas fa-plus me-1"></i>Add Education
                    </button>
                </div>
                <div class="card-body">
                    <div id="educationContainer">
                        <c:forEach var="education" items="${resume.educations}" varStatus="status">
                            <div class="education-item mb-4 border-bottom pb-4">
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <h6 class="mb-0">Education #${status.index + 1}</h6>
                                    <button type="button" class="btn btn-sm btn-outline-danger remove-education">
                                        <i class="fas fa-trash me-1"></i>Remove
                                    </button>
                                </div>
                                
                                <div class="mb-3">
                                    <label class="form-label">Institution</label>
                                    <input type="text" class="form-control" name="institution" required
                                           value="${education.institution}">
                                </div>
                                
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Degree</label>
                                        <input type="text" class="form-control" name="degree" required
                                               value="${education.degree}">
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Field of Study</label>
                                        <input type="text" class="form-control" name="fieldOfStudy"
                                               value="${education.fieldOfStudy}">
                                    </div>
                                </div>
                                
                                <div class="row mb-3">
                                    <div class="col-md-5">
                                        <label class="form-label">Start Date</label>
                                        <input type="date" class="form-control" name="eduStartDate"
                                               value="<fmt:formatDate value="${education.startDate}" pattern="yyyy-MM-dd" />">
                                    </div>
                                    <div class="col-md-5">
                                        <label class="form-label">End Date</label>
                                        <input type="date" class="form-control" name="eduEndDate"
                                               value="<fmt:formatDate value="${education.endDate}" pattern="yyyy-MM-dd" />"
                                               ${education.current ? 'disabled' : ''}>
                                    </div>
                                    <div class="col-md-2 d-flex align-items-end">
                                        <div class="form-check">
                                            <input class="form-check-input current-checkbox" type="checkbox" name="eduCurrent"
                                                   ${education.current ? 'checked' : ''}>
                                            <label class="form-check-label">Current</label>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="mb-3">
                                    <label class="form-label">Description</label>
                                    <textarea class="form-control" name="eduDescription" rows="3">${education.description}</textarea>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    
                    <c:if test="${empty resume.educations}">
                        <div class="text-center py-4">
                            <p class="text-muted">No education entries yet. Click the "Add Education" button to add your educational background.</p>
                        </div>
                    </c:if>
                </div>
            </div>
            
            <!-- Experience Card -->
            <div class="card shadow mb-4">
                <div class="card-header bg-light d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Work Experience</h5>
                    <button type="button" class="btn btn-sm btn-primary" id="addExperience">
                        <i class="fas fa-plus me-1"></i>Add Experience
                    </button>
                </div>
                <div class="card-body">
                    <div id="experienceContainer">
                        <c:forEach var="experience" items="${resume.experiences}" varStatus="status">
                            <div class="experience-item mb-4 border-bottom pb-4">
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <h6 class="mb-0">Experience #${status.index + 1}</h6>
                                    <button type="button" class="btn btn-sm btn-outline-danger remove-experience">
                                        <i class="fas fa-trash me-1"></i>Remove
                                    </button>
                                </div>
                                
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Company</label>
                                        <input type="text" class="form-control" name="company" required
                                               value="${experience.company}">
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Position</label>
                                        <input type="text" class="form-control" name="position" required
                                               value="${experience.position}">
                                    </div>
                                </div>
                                
                                <div class="mb-3">
                                    <label class="form-label">Location</label>
                                    <input type="text" class="form-control" name="location"
                                           value="${experience.location}">
                                </div>
                                
                                <div class="row mb-3">
                                    <div class="col-md-5">
                                        <label class="form-label">Start Date</label>
                                        <input type="date" class="form-control" name="expStartDate"
                                               value="<fmt:formatDate value="${experience.startDate}" pattern="yyyy-MM-dd" />">
                                    </div>
                                    <div class="col-md-5">
                                        <label class="form-label">End Date</label>
                                        <input type="date" class="form-control" name="expEndDate"
                                               value="<fmt:formatDate value="${experience.endDate}" pattern="yyyy-MM-dd" />"
                                               ${experience.current ? 'disabled' : ''}>
                                    </div>
                                    <div class="col-md-2 d-flex align-items-end">
                                        <div class="form-check">
                                            <input class="form-check-input current-checkbox" type="checkbox" name="expCurrent"
                                                   ${experience.current ? 'checked' : ''}>
                                            <label class="form-check-label">Current</label>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="mb-3">
                                    <label class="form-label">Description</label>
                                    <textarea class="form-control" name="expDescription" rows="3">${experience.description}</textarea>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    
                    <c:if test="${empty resume.experiences}">
                        <div class="text-center py-4">
                            <p class="text-muted">No experience entries yet. Click the "Add Experience" button to add your work history.</p>
                        </div>
                    </c:if>
                </div>
            </div>
            
            <!-- Skills Card -->
            <div class="card shadow mb-4">
                <div class="card-header bg-light d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Skills</h5>
                    <button type="button" class="btn btn-sm btn-primary" id="addSkill">
                        <i class="fas fa-plus me-1"></i>Add Skill
                    </button>
                </div>
                <div class="card-body">
                    <div id="skillsContainer">
                        <c:forEach var="skill" items="${resume.skills}" varStatus="status">
                            <div class="skill-item mb-3 row align-items-center">
                                <div class="col-md-5">
                                    <input type="text" class="form-control" name="skillName" placeholder="Skill name" required
                                           value="${skill.name}">
                                </div>
                                <div class="col-md-5">
                                    <select class="form-select" name="skillLevel">
                                        <option value="1" ${skill.proficiencyLevel == 1 ? 'selected' : ''}>Beginner</option>
                                        <option value="2" ${skill.proficiencyLevel == 2 ? 'selected' : ''}>Elementary</option>
                                        <option value="3" ${skill.proficiencyLevel == 3 ? 'selected' : ''}>Intermediate</option>
                                        <option value="4" ${skill.proficiencyLevel == 4 ? 'selected' : ''}>Advanced</option>
                                        <option value="5" ${skill.proficiencyLevel == 5 ? 'selected' : ''}>Expert</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <button type="button" class="btn btn-sm btn-outline-danger remove-skill">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    
                    <c:if test="${empty resume.skills}">
                        <div class="text-center py-4" id="noSkillsMessage">
                            <p class="text-muted">No skills added yet. Click the "Add Skill" button to add your skills.</p>
                        </div>
                    </c:if>
                </div>
            </div>
            
            <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-5">
                <button type="submit" class="btn btn-primary btn-lg">
                    <i class="fas fa-save me-2"></i>Save Resume
                </button>
            </div>
        </form>
    </div>
</div>

<!-- Templates for dynamic form elements -->
<div id="templates" style="display: none;">
    <!-- Education Template -->
    <div id="educationTemplate">
        <div class="education-item mb-4 border-bottom pb-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h6 class="mb-0">New Education</h6>
                <button type="button" class="btn btn-sm btn-outline-danger remove-education">
                    <i class="fas fa-trash me-1"></i>Remove
                </button>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Institution</label>
                <input type="text" class="form-control" name="institution" required>
            </div>
            
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Degree</label>
                    <input type="text" class="form-control" name="degree" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Field of Study</label>
                    <input type="text" class="form-control" name="fieldOfStudy">
                </div>
            </div>
            
            <div class="row mb-3">
                <div class="col-md-5">
                    <label class="form-label">Start Date</label>
                    <input type="date" class="form-control" name="eduStartDate">
                </div>
                <div class="col-md-5">
                    <label class="form-label">End Date</label>
                    <input type="date" class="form-control" name="eduEndDate">
                </div>
                <div class="col-md-2 d-flex align-items-end">
                    <div class="form-check">
                        <input class="form-check-input current-checkbox" type="checkbox" name="eduCurrent">
                        <label class="form-check-label">Current</label>
                    </div>
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Description</label>
                <textarea class="form-control" name="eduDescription" rows="3"></textarea>
            </div>
        </div>
    </div>
    
    <!-- Experience Template -->
    <div id="experienceTemplate">
        <div class="experience-item mb-4 border-bottom pb-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h6 class="mb-0">New Experience</h6>
                <button type="button" class="btn btn-sm btn-outline-danger remove-experience">
                    <i class="fas fa-trash me-1"></i>Remove
                </button>
            </div>
            
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Company</label>
                    <input type="text" class="form-control" name="company" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Position</label>
                    <input type="text" class="form-control" name="position" required>
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Location</label>
                <input type="text" class="form-control" name="location">
            </div>
            
            <div class="row mb-3">
                <div class="col-md-5">
                    <label class="form-label">Start Date</label>
                    <input type="date" class="form-control" name="expStartDate">
                </div>
                <div class="col-md-5">
                    <label class="form-label">End Date</label>
                    <input type="date" class="form-control" name="expEndDate">
                </div>
                <div class="col-md-2 d-flex align-items-end">
                    <div class="form-check">
                        <input class="form-check-input current-checkbox" type="checkbox" name="expCurrent">
                        <label class="form-check-label">Current</label>
                    </div>
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Description</label>
                <textarea class="form-control" name="expDescription" rows="3"></textarea>
            </div>
        </div>
    </div>
    
    <!-- Skill Template -->
    <div id="skillTemplate">
        <div class="skill-item mb-3 row align-items-center">
            <div class="col-md-5">
                <input type="text" class="form-control" name="skillName" placeholder="Skill name" required>
            </div>
            <div class="col-md-5">
                <select class="form-select" name="skillLevel">
                    <option value="1">Beginner</option>
                    <option value="2">Elementary</option>
                    <option value="3" selected>Intermediate</option>
                    <option value="4">Advanced</option>
                    <option value="5">Expert</option>
                </select>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-sm btn-outline-danger remove-skill">
                    <i class="fas fa-trash"></i>
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Education section
        const educationContainer = document.getElementById('educationContainer');
        const addEducationBtn = document.getElementById('addEducation');
        const educationTemplate = document.getElementById('educationTemplate').innerHTML;
        
        addEducationBtn.addEventListener('click', function() {
            const div = document.createElement('div');
            div.innerHTML = educationTemplate;
            educationContainer.appendChild(div.firstElementChild);
            setupCurrentCheckboxes();
        });
        
        // Experience section
        const experienceContainer = document.getElementById('experienceContainer');
        const addExperienceBtn = document.getElementById('addExperience');
        const experienceTemplate = document.getElementById('experienceTemplate').innerHTML;
        
        addExperienceBtn.addEventListener('click', function() {
            const div = document.createElement('div');
            div.innerHTML = experienceTemplate;
            experienceContainer.appendChild(div.firstElementChild);
            setupCurrentCheckboxes();
        });
        
        // Skills section
        const skillsContainer = document.getElementById('skillsContainer');
        const addSkillBtn = document.getElementById('addSkill');
        const skillTemplate = document.getElementById('skillTemplate').innerHTML;
        const noSkillsMessage = document.getElementById('noSkillsMessage');
        
        addSkillBtn.addEventListener('click', function() {
            if (noSkillsMessage) {
                noSkillsMessage.style.display = 'none';
            }
            
            const div = document.createElement('div');
            div.innerHTML = skillTemplate;
            skillsContainer.appendChild(div.firstElementChild);
        });
        
        // Event delegation for remove buttons
        document.addEventListener('click', function(e) {
            // Remove education
            if (e.target.closest('.remove-education')) {
                e.target.closest('.education-item').remove();
            }
            
            // Remove experience
            if (e.target.closest('.remove-experience')) {
                e.target.closest('.experience-item').remove();
            }
            
            // Remove skill
            if (e.target.closest('.remove-skill')) {
                e.target.closest('.skill-item').remove();
                
                // Show message if no skills left
                if (skillsContainer.children.length === 0 && noSkillsMessage) {
                    noSkillsMessage.style.display = 'block';
                }
            }
        });
        
        // Setup current checkboxes
        function setupCurrentCheckboxes() {
            document.querySelectorAll('.current-checkbox').forEach(checkbox => {
                if (!checkbox.hasEventListener) {
                    checkbox.hasEventListener = true;
                    checkbox.addEventListener('change', function() {
                        const endDateInput = this.closest('.row').querySelector('input[type="date"]:nth-of-type(2)');
                        if (endDateInput) {
                            endDateInput.disabled = this.checked;
                            if (this.checked) {
                                endDateInput.value = '';
                            }
                        }
                    });
                }
            });
        }
        
        // Initial setup
        setupCurrentCheckboxes();
    });
</script>
