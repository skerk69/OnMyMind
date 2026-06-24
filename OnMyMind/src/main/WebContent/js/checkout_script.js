(function() {

    const scelta = document.getElementById("sceltaIndirizzo");
    const spanNuovoIndirizzo = document.getElementById("nuovo");
    const btnOrdine = document.getElementById("orderButton");

    if (!scelta || !spanNuovoIndirizzo || !btnOrdine) {
        return;
    }

    function controllaInterfaccia() {
        const valore = scelta.value;
        console.log("Valore attuale della select:", valore);

        if (valore === "0") {
            spanNuovoIndirizzo.style.display = "";
            btnOrdine.disabled = true;
        } else if(valore === "-1"){
            spanNuovoIndirizzo.style.display = "none";
            btnOrdine.disabled = true;
        } else {
			spanNuovoIndirizzo.style.display = "none";
			btnOrdine.disabled = false;
		}
    }

    controllaInterfaccia();

    scelta.addEventListener("change", controllaInterfaccia);
    scelta.addEventListener("input", controllaInterfaccia);
})();