document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.querySelector('section');
    document.querySelector('button');
    const emailInput = document.querySelector('input[type="email"]');
    const passwordInput = document.querySelector('input[type="password"]');
    const confirmPasswordInput = document.querySelector('input[name="confirmPassword"]');

    setTimeout(function () {
        signupForm.style.transition = 'opacity 1s ease-in-out';
        signupForm.style.opacity = 1;
    }, 500);

    const signupButton = document.querySelector('button');
    signupButton.addEventListener('click', function () {
        const emailInput = document.querySelector('input[type="email"]');
        const passwordInput = document.querySelector('input[type="password"]');
        const confirmPasswordInput = document.querySelector('input[name="confirmPassword"]');

        // Check for a valid email and password (you can add your validation logic here)
        const isValid = emailInput.checkValidity() && passwordInput.checkValidity() && confirmPasswordInput.checkValidity();

        if (isValid) {
            signupForm.classList.add('shake');

            setTimeout(function () {
                signupForm.classList.remove('shake');
            }, 1000);
        }
    });

    // Validação de senha
    if (confirmPasswordInput) {
        confirmPasswordInput.addEventListener('input', function() {
            if (passwordInput.value !== confirmPasswordInput.value) {
                confirmPasswordInput.setCustomValidity('As senhas não coincidem');
            } else {
                confirmPasswordInput.setCustomValidity('');
            }
        });
    }
});

