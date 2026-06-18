function resetAll() {
    const modificabtnCap = document.querySelectorAll(".modificaCappello");
    const confermabtnCap = document.querySelectorAll(".confermaCappello");
    const parametriCap = document.querySelectorAll(".classeCappello");

	const modificabtnCat = document.querySelectorAll(".modificaCategoria");
	const confermabtnCat = document.querySelectorAll(".confermaCategoria");
	const parametriCat = document.querySelectorAll(".classeCategoria");
	
    modificabtnCap.forEach(btn => btn.hidden = false);
    confermabtnCap.forEach(btn => btn.hidden = true);
    parametriCap.forEach(input => input.hidden = true);
	
	modificabtnCat.forEach(btn => btn.hidden = false);
	confermabtnCat.forEach(btn => btn.hidden = true);
	parametriCat.forEach(input => input.hidden = true);
}

document.addEventListener("DOMContentLoaded", resetAll);

function showModifyCappello(btn){
	
		resetAll();
	
		const formCorrente = btn.closest('form');
		
		const modificabtn = formCorrente.querySelector(".modificaCappello");
		const confermabtn = formCorrente.querySelector(".confermaCappello");
		const parametri = formCorrente.querySelectorAll(".classeCappello")
	
		modificabtn.hidden = true;
	    confermabtn.hidden = false;

	    parametri.forEach(input => {
	        input.hidden = false;
	    });
	    
	    if(parametri.length > 0) {
	        parametri[0].focus();
	    }
	
}

function showModifyCategoria(btn){

		resetAll();
	
		const formCorrente = btn.closest('form');
		
		const modificabtn = formCorrente.querySelector(".modificaCategoria");
		const confermabtn = formCorrente.querySelector(".confermaCategoria");
		const parametri = formCorrente.querySelectorAll(".classeCategoria")

		modificabtn.hidden = true;
	    confermabtn.hidden = false;
	    
		parametri.forEach(input => {
	        input.hidden = false; 
	    });
	    
	    if(parametri.length > 0) {
	        parametri[0].focus();
	    }
}

















