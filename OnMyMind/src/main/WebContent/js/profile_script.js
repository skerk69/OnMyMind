document.addEventListener("DOMContentLoaded", () => {
    

    const regex = {
        cap: /^\d{5}$/,
        provincia: /^[A-Z]{2}$/,
        telefono: /^[0-9]{9,10}$/,
        email: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
        password: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&\.])[A-Za-z\d@$!%*?&\.]{8,}$/
    };

    function validaForm(form) {
        let isFormValid = true;
        const inputs = form.querySelectorAll('input');

        inputs.forEach(input => {
            const fieldName = input.name;
            
            if (fieldName === 'password' && input.value === "") {
                return;
            }

            const errorElement = form.querySelector("#error-" + fieldName);
            
            if (regex[fieldName]) {
                if (!regex[fieldName].test(input.value)) {
                    if (errorElement) errorElement.innerHTML = "Formato " + fieldName + " non valido";
                    isFormValid = false;
                } else {
                    if (errorElement) errorElement.innerHTML = "";
                }
            }
        });
        return isFormValid;
    }

    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', (e) => {
            if (!validaForm(form)) {
                e.preventDefault();
            }
        });
    });

	    document.querySelectorAll('.btn-toggle').forEach(button => {
	        button.addEventListener('click', () => {
	            const targetId = button.getAttribute('data-target');
	            const targetEl = document.getElementById(targetId);
	            
	            document.querySelectorAll('.form-modifica').forEach(form => {
	                if (form.id !== targetId) {
	                    form.hidden = true;
	                }
	            });

	            if (targetEl) {
	                targetEl.hidden = !targetEl.hidden;
	            }
	        });
	    });

    document.querySelectorAll('input').forEach(input => {
        input.addEventListener('input', () => {
            validaForm(input.closest('form'));
        });
    });
});