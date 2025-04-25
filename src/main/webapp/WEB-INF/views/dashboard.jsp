<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="row mb-4">
    <div class="col-md-8">
        <h2>Welcome, ${sessionScope.user.firstName}!</h2>
        <p class="lead">Manage your resumes and create new ones.</p>
    </div>
    <div class="col-md-4 text-md-end">
        <a href="${pageContext.request.contextPath}/resume/create" class="btn btn-primary">
            <i class="fas fa-plus me-2"></i>Create New Resume
        </a>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="card shadow">
            <div class="card-header bg-light">
                <h5 class="mb-0">My Resumes</h5>
            </div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${empty resumes}">
                        <div class="text-center py-5">
                            <i class="fas fa-file-alt fa-4x text-muted mb-3"></i>
                            <h4>No Resumes Yet</h4>
                            <p class="text-muted">You haven't created any resumes yet. Click the button above to create your first resume.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Title</th>
                                        <th>Created</th>
                                        <th>Last Modified</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="resume" items="${resumes}">
                                        <tr>
                                            <td>${resume.title}</td>
                                            <td><fmt:formatDate value="${resume.createdDate}" pattern="MMM dd, yyyy" /></td>
                                            <td><fmt:formatDate value="${resume.lastModifiedDate}" pattern="MMM dd, yyyy" /></td>
                                            <td>
                                                <div class="btn-group btn-group-sm">
                                                    <a href="${pageContext.request.contextPath}/resume/view?id=${resume.id}" class="btn btn-outline-primary">
                                                        <i class="fas fa-eye"></i>
                                                    </a>
                                                    <a href="${pageContext.request.contextPath}/resume/edit?id=${resume.id}" class="btn btn-outline-secondary">
                                                        <i class="fas fa-edit"></i>
                                                    </a>
                                                    <a href="${pageContext.request.contextPath}/resume/download?id=${resume.id}" class="btn btn-outline-success">
                                                        <i class="fas fa-download"></i>
                                                    </a>
                                                    <button type="button" class="btn btn-outline-danger" 
                                                            onclick="confirmDelete(${resume.id}, '${resume.title}')">
                                                        <i class="fas fa-trash"></i>
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<!-- Delete Confirmation Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm Delete</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete the resume "<span id="resumeTitle"></span>"?</p>
                <p class="text-danger">This action cannot be undone.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <form id="deleteForm" action="${pageContext.request.contextPath}/resume/delete" method="post">
                    <input type="hidden" id="resumeId" name="id">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function confirmDelete(id, title) {
        document.getElementById('resumeId').value = id;
        document.getElementById('resumeTitle').textContent = title;
        new bootstrap.Modal(document.getElementById('deleteModal')).show();
    }
</script>
