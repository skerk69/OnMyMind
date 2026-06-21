function caricaProdotti() {

    const stringaRicerca = document.getElementById("input-ricerca").value;
    
    const colore = document.getElementById("input-colore").value;
    const taglia = document.getElementById("input-taglia").value;
    const prezzoMin = document.getElementById("input-prezzo-min").value;
    const prezzoMax = document.getElementById("input-prezzo-max").value;

    const urlParams = new URLSearchParams({
        categoria: "", 
        nome: stringaRicerca,
        colore: colore,
        taglia: taglia,
        prezzoMin: prezzoMin,
        prezzoMax: prezzoMax
    });

    const url = contextPath + "/search?" + urlParams.toString(); 

    console.log("Chiamata AJAX a: ", url);

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("Errore nella risposta del server");
            }
            return response.json();
        })
        .then(listaCappelli => {
            const contenitore = document.getElementById("contenitore");
            contenitore.innerHTML = ""; 

			aggiornaSuggerimentiColori(listaCappelli);
			
            if (listaCappelli.length === 0) {
                contenitore.innerHTML = "<p>Nessun cappello corrisponde ai criteri di ricerca.</p>";
                return;
            }

            listaCappelli.forEach(cappello => {
                const cardHtml = `
                    <a href="openproduct?id=${cappello.id}">
                        <img src="images/${cappello.immagine}" width="100"><br>
                        ${cappello.nome}<br>
                    </a>
                `;
                contenitore.innerHTML += cardHtml;
            });
        })
        .catch(error => {
            console.error("Errore durante la chiamata AJAX:", error);
            document.getElementById("contenitore").innerHTML = "<p>Si è verificato un errore nel caricamento della collezione.</p>";
        });
}

document.addEventListener("DOMContentLoaded", () => {
    caricaProdotti();

    document.getElementById("input-ricerca").addEventListener("input", caricaProdotti);
    document.getElementById("input-colore").addEventListener("input", caricaProdotti);
    document.getElementById("input-taglia").addEventListener("input", caricaProdotti);
    document.getElementById("input-prezzo-min").addEventListener("input", caricaProdotti);
    document.getElementById("input-prezzo-max").addEventListener("input", caricaProdotti);
});

function aggiornaSuggerimentiColori(listaCappelli) {
    const datalist = document.getElementById("suggerimenti-colori");
    
    const coloriUnici = new Set();
    
    listaCappelli.forEach(cappello => {
        if (cappello.colore) {
            coloriUnici.add(cappello.colore.trim());
        }
    });

    datalist.innerHTML = "";
    coloriUnici.forEach(colore => {
        const option = document.createElement("option");
        option.value = colore;
        datalist.appendChild(option);
    });
}

