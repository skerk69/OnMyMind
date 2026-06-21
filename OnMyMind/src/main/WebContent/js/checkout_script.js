const scelta = document.getElementById("sceltaIndirizzo");

if(scelta){
scelta.addEventListener("input", function() {
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
scelta.dispatchEvent(new Event("input"));
}