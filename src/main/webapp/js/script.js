/**
 * Main JavaScript file for ResumeForgeFX
 */

document.addEventListener('DOMContentLoaded', function() {
    // Initialize tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
    
    // Password strength meter
    const passwordInput = document.getElementById('password');
    const passwordStrength = document.getElementById('password-strength');
    
    if (passwordInput && passwordStrength) {
        passwordInput.addEventListener('input', function() {
            const password = passwordInput.value;
            let strength = 0;
            
            if (password.length >= 8) strength += 1;
            if (password.match(/[a-z]+/)) strength += 1;
            if (password.match(/[A-Z]+/)) strength += 1;
            if (password.match(/[0-9]+/)) strength += 1;
            if (password.match(/[^a-zA-Z0-9]+/)) strength += 1;
            
            switch (strength) {
                case 0:
                case 1:
                    passwordStrength.className = 'progress-bar bg-danger';
                    passwordStrength.style.width = '20%';
                    passwordStrength.textContent = 'Very Weak';
                    break;
                case 2:
                    passwordStrength.className = 'progress-bar bg-warning';
                    passwordStrength.style.width = '40%';
                    passwordStrength.textContent = 'Weak';
                    break;
                case 3:
                    passwordStrength.className = 'progress-bar bg-info';
                    passwordStrength.style.width = '60%';
                    passwordStrength.textContent = 'Medium';
                    break;
                case 4:
                    passwordStrength.className = 'progress-bar bg-primary';
                    passwordStrength.style.width = '80%';
                    passwordStrength.textContent = 'Strong';
                    break;
                case 5:
                    passwordStrength.className = 'progress-bar bg-success';
                    passwordStrength.style.width = '100%';
                    passwordStrength.textContent = 'Very Strong';
                    break;
            }
        });
    }
    
    // Template selection
    const templateCards = document.querySelectorAll('.template-card');
    const templateInput = document.getElementById('selectedTemplate');
    
    if (templateCards.length > 0 && templateInput) {
        templateCards.forEach(card => {
            card.addEventListener('click', function() {
                // Remove selected class from all cards
                templateCards.forEach(c => c.classList.remove('selected'));
                
                // Add selected class to clicked card
                this.classList.add('selected');
                
                // Update hidden input value
                templateInput.value = this.dataset.templateId;
            });
        });
    }
    
    // Dynamic form fields for education, experience, skills
    const addEducationBtn = document.getElementById('add-education');
    const educationContainer = document.getElementById('education-container');
    let educationCount = document.querySelectorAll('.education-item').length;
    
    if (addEducationBtn && educationContainer) {
        addEducationBtn.addEventListener('click', function() {
            const template = document.getElementById('education-template').innerHTML;
            const newItem = template.replace(/INDEX/g, educationCount);
            
            const div = document.createElement('div');
            div.className = 'education-item mb-4';
            div.innerHTML = newItem;
            
            educationContainer.appendChild(div);
            educationCount++;
        });
        
        // Event delegation for remove buttons
        educationContainer.addEventListener('click', function(e) {
            if (e.target && e.target.classList.contains('remove-education')) {
                e.target.closest('.education-item').remove();
            }
        });
    }
    
    // Similar functionality for experience and skills
    // (Code omitted for brevity but would follow the same pattern)
    
    // Form validation
    const forms = document.querySelectorAll('.needs-validation');
    
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            
            form.classList.add('was-validated');
        }, false);
    });
});
