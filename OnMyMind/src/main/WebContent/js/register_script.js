document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form-registrazione");
    
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    const telRegex = /^[0-9]{9,10}$/;
	
	const register = document.getElementById("error-register");
	
    function validateField(id, regex, errorId, message) {
        const value = document.getElementById(id).value;
        const errorElement = document.getElementById(errorId);
        if (!regex.test(value)) {
            errorElement.innerHTML = message;
            return false;
        } else {
            errorElement.innerHTML = "";
            return true;
        }
    }

    form.addEventListener("input", () => {
        validateField("email", emailRegex, "error-email", "Email non valida");
        validateField("password", passwordRegex, "error-password", "Min 8 caratteri, 1 lettera, 1 numero");
        validateField("telefono", telRegex, "error-telefono", "Numero non valido");
    });

    form.addEventListener("submit", (e) => {
        const isEmailValid = emailRegex.test(document.getElementById("email").value);
        const isPassValid = passwordRegex.test(document.getElementById("password").value);
        const isTelValid = telRegex.test(document.getElementById("telefono").value);

        if (!(isEmailValid && isPassValid && isTelValid)) {
            e.preventDefault();
			register.innerHTML = "Controlla le credenziali!";
        } else {
			register.innerHTML = "";
		}
    });
});