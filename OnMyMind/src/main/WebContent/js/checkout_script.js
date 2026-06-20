document.getElementById("sceltaIndirizzo").addEventListener("change", function() {
    let spanNuovoIndirizzo = document.getElementById("nuovo");
    
    if (this.value === "0") {
        spanNuovoIndirizzo.style.display = "";
    } else {
        spanNuovoIndirizzo.style.display = "none";
    }
});