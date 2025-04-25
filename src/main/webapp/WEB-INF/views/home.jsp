<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Hero Section -->
<section class="py-5 text-center">
    <div class="row py-lg-5">
        <div class="col-lg-8 col-md-10 mx-auto">
            <h1 class="fw-bold">Create Professional Resumes in Minutes</h1>
            <p class="lead text-muted">
                ResumeForgeFX helps you create stunning, professional resumes that stand out from the crowd.
                Choose from multiple templates, customize your content, and download your resume in multiple formats.
            </p>
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                <a href="${pageContext.request.contextPath}/register" class="btn btn-primary btn-lg px-4 gap-3">Get Started</a>
                <a href="${pageContext.request.contextPath}/templates" class="btn btn-outline-secondary btn-lg px-4">View Templates</a>
            </div>
        </div>
    </div>
</section>

<!-- Features Section -->
<section class="py-5 bg-light">
    <div class="container">
        <h2 class="text-center mb-5">Why Choose ResumeForgeFX?</h2>
        <div class="row g-4">
            <div class="col-md-4">
                <div class="card h-100 border-0 shadow-sm">
                    <div class="card-body text-center p-4">
                        <div class="feature-icon bg-primary bg-gradient text-white rounded-circle mb-3">
                            <i class="fas fa-file-alt fa-2x p-3"></i>
                        </div>
                        <h5 class="card-title">Professional Templates</h5>
                        <p class="card-text">Choose from a variety of professionally designed templates that are tailored for different industries and career levels.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card h-100 border-0 shadow-sm">
                    <div class="card-body text-center p-4">
                        <div class="feature-icon bg-primary bg-gradient text-white rounded-circle mb-3">
                            <i class="fas fa-edit fa-2x p-3"></i>
                        </div>
                        <h5 class="card-title">Easy Customization</h5>
                        <p class="card-text">Our intuitive editor makes it easy to customize your resume with your personal information, experience, and skills.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card h-100 border-0 shadow-sm">
                    <div class="card-body text-center p-4">
                        <div class="feature-icon bg-primary bg-gradient text-white rounded-circle mb-3">
                            <i class="fas fa-download fa-2x p-3"></i>
                        </div>
                        <h5 class="card-title">Multiple Export Options</h5>
                        <p class="card-text">Download your resume in PDF, Word, or HTML formats to suit your application needs.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- How It Works Section -->
<section class="py-5">
    <div class="container">
        <h2 class="text-center mb-5">How It Works</h2>
        <div class="row">
            <div class="col-md-3">
                <div class="text-center">
                    <div class="step-circle bg-primary text-white mx-auto mb-3">1</div>
                    <h5>Create an Account</h5>
                    <p>Sign up for free and get access to all our features.</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="text-center">
                    <div class="step-circle bg-primary text-white mx-auto mb-3">2</div>
                    <h5>Choose a Template</h5>
                    <p>Select from our collection of professional templates.</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="text-center">
                    <div class="step-circle bg-primary text-white mx-auto mb-3">3</div>
                    <h5>Add Your Content</h5>
                    <p>Fill in your details, experience, education, and skills.</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="text-center">
                    <div class="step-circle bg-primary text-white mx-auto mb-3">4</div>
                    <h5>Download & Share</h5>
                    <p>Download your resume in your preferred format.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Testimonials Section -->
<section class="py-5 bg-light">
    <div class="container">
        <h2 class="text-center mb-5">What Our Users Say</h2>
        <div class="row">
            <div class="col-lg-4">
                <div class="card h-100 border-0 shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex mb-3">
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                        </div>
                        <p class="card-text">"ResumeForgeFX helped me create a professional resume that landed me my dream job. The templates are modern and the interface is so easy to use!"</p>
                        <div class="d-flex align-items-center mt-3">
                            <div class="flex-shrink-0">
                                <img src="https://via.placeholder.com/50" class="rounded-circle" alt="User">
                            </div>
                            <div class="flex-grow-1 ms-3">
                                <h6 class="mb-0">John Doe</h6>
                                <small class="text-muted">Software Engineer</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="card h-100 border-0 shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex mb-3">
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                        </div>
                        <p class="card-text">"I was struggling to create a resume that stood out. ResumeForgeFX made it simple with their beautiful templates and easy-to-use editor."</p>
                        <div class="d-flex align-items-center mt-3">
                            <div class="flex-shrink-0">
                                <img src="https://via.placeholder.com/50" class="rounded-circle" alt="User">
                            </div>
                            <div class="flex-grow-1 ms-3">
                                <h6 class="mb-0">Jane Smith</h6>
                                <small class="text-muted">Marketing Specialist</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="card h-100 border-0 shadow-sm">
                    <div class="card-body p-4">
                        <div class="d-flex mb-3">
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                            <i class="fas fa-star text-warning"></i>
                        </div>
                        <p class="card-text">"As a recent graduate, I needed a resume that would help me stand out. ResumeForgeFX provided exactly what I needed to start my career."</p>
                        <div class="d-flex align-items-center mt-3">
                            <div class="flex-shrink-0">
                                <img src="https://via.placeholder.com/50" class="rounded-circle" alt="User">
                            </div>
                            <div class="flex-grow-1 ms-3">
                                <h6 class="mb-0">Michael Johnson</h6>
                                <small class="text-muted">Recent Graduate</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- CTA Section -->
<section class="py-5 bg-primary text-white text-center">
    <div class="container">
        <h2 class="mb-4">Ready to Create Your Professional Resume?</h2>
        <p class="lead mb-4">Join thousands of job seekers who have successfully landed their dream jobs with ResumeForgeFX.</p>
        <a href="${pageContext.request.contextPath}/register" class="btn btn-light btn-lg px-4">Get Started Now</a>
    </div>
</section>

<style>
    .feature-icon {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        width: 4rem;
        height: 4rem;
    }
    
    .step-circle {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 3rem;
        height: 3rem;
        border-radius: 50%;
        font-size: 1.5rem;
        font-weight: bold;
    }
</style>
