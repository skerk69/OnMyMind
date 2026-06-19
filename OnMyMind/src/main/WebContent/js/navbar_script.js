document.addEventListener("DOMContentLoaded", function() {
	
	const ruoloElement = document.getElementById("ruolo");
	
	const login = document.getElementById("login");

	const profile = document.getElementById("profile");
		
	const management = document.getElementById("management");

	if(ruoloElement){
		
		const ruolo = ruoloElement.value;
		
		if(ruolo === "admin"){
			
			login.style.display = "none";
			
			management.style.display = "";
			
			profile.style.display = "";
			
		}else if(ruolo === "utente"){
			
			login.style.display = "none";
			
			management.style.display = "none";
			
			profile.style.display = "";
		}
	}	else{
			
				login.style.display = "";	
				
				management.style.display = "none";

				profile.style.display = "none";
				
			}
});
	