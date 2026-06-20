document.addEventListener("DOMContentLoaded", function() {
	
    document.querySelectorAll(".prezzo").forEach(function(elemento) {
		
        let valoreFormattato = elemento.textContent.replace(',', '.').trim();
        let numero = parseFloat(valoreFormattato);
        
        if (!isNaN(numero)) {
            elemento.textContent = numero.toFixed(2).replace('.', ',');
        }
    });
});