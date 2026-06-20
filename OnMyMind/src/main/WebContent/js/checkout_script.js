document.getElementById("sceltaIndirizzo").addEventListener("change", function() {
    let spanNuovoIndirizzo = document.getElementById("nuovo");
    let btn = document.getElementById("order");
    if (this.value === "0") {
        spanNuovoIndirizzo.style.display = "";
		btn.disabled = true;
    } else {
        spanNuovoIndirizzo.style.display = "none";
		btn.disabled = false;
    }
});
