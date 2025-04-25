<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="row mb-4">
    <div class="col-md-8">
        <h2>Create New Resume</h2>
        <p class="lead">Enter the basic information for your new resume.</p>
    </div>
    <div class="col-md-4 text-md-end">
        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-outline-secondary">
            <i class="fas fa-arrow-left me-2"></i>Back to Dashboard
        </a>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="card shadow">
            <div class="card-header bg-light">
                <h5 class="mb-0">Resume Information</h5>
            </div>
            <div class="card-body">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:if>
                
                <form action="${pageContext.request.contextPath}/resume/create" method="post">
                    <div class="mb-3">
                        <label for="title" class="form-label">Resume Title</label>
                        <input type="text" class="form-control" id="title" name="title" required
                               placeholder="e.g., Software Developer Resume, Marketing Professional Resume">
                        <div class="form-text">Give your resume a descriptive title to help you identify it later.</div>
                    </div>
                    
                    <div class="mb-3">
                        <label for="summary" class="form-label">Professional Summary</label>
                        <textarea class="form-control" id="summary" name="summary" rows="4"
                                  placeholder="A brief summary of your professional background and key qualifications..."></textarea>
                        <div class="form-text">A concise overview of your professional background, skills, and career goals.</div>
                    </div>
                    
                    <h5 class="mt-4 mb-3">Contact Information</h5>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required
                                   value="${sessionScope.user.email}">
                        </div>
                        <div class="col-md-6">
                            <label for="phone" class="form-label">Phone</label>
                            <input type="tel" class="form-control" id="phone" name="phone" 
                                   placeholder="e.g., (123) 456-7890">
                        </div>
                    </div>
                    
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" name="address" 
                               placeholder="e.g., 123 Main St">
                    </div>
                    
                    <div class="row mb-3">
                        <div class="col-md-4">
                            <label for="city" class="form-label">City</label>
                            <input type="text" class="form-control" id="city" name="city" 
                                   placeholder="e.g., New York">
                        </div>
                        <div class="col-md-4">
                            <label for="state" class="form-label">State/Province</label>
                            <input type="text" class="form-control" id="state" name="state" 
                                   placeholder="e.g., NY">
                        </div>
                        <div class="col-md-4">
                            <label for="zipCode" class="form-label">Zip/Postal Code</label>
                            <input type="text" class="form-control" id="zipCode" name="zipCode" 
                                   placeholder="e.g., 10001">
                        </div>
                    </div>
                    
                    <div class="mb-3">
                        <label for="country" class="form-label">Country</label>
                        <input type="text" class="form-control" id="country" name="country" 
                               placeholder="e.g., United States">
                    </div>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="linkedin" class="form-label">LinkedIn Profile</label>
                            <input type="url" class="form-control" id="linkedin" name="linkedin" 
                                   placeholder="e.g., https://www.linkedin.com/in/yourprofile">
                        </div>
                        <div class="col-md-6">
                            <label for="website" class="form-label">Personal Website</label>
                            <input type="url" class="form-control" id="website" name="website" 
                                   placeholder="e.g., https://www.yourwebsite.com">
                        </div>
                    </div>
                    
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save me-2"></i>Create Resume
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
