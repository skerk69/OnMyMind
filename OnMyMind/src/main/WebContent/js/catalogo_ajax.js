function caricaProdotti() {

    const stringaRicerca = document.getElementById("input-ricerca").value;
    const colore = document.getElementById("input-colore").value;
    const taglia = document.getElementById("input-taglia").value;
    const prezzoMin = document.getElementById("input-prezzo-min").value;
    const prezzoMax = document.getElementById("input-prezzo-max").value;
    const categoria = document.getElementById("input-categoria").value;

    const urlParams = new URLSearchParams({
        categoria: categoria,
        nome: stringaRicerca,
        colore: colore,
        taglia: taglia,
        prezzoMin: prezzoMin,
        prezzoMax: prezzoMax
    });

    const url = contextPath + "/search?" + urlParams.toString(); 

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
    const minInput = document.getElementById("input-prezzo-min");
    const maxInput = document.getElementById("input-prezzo-max");

    if (minInput && maxInput) {
        if (minInput.value) maxInput.min = minInput.value;
        if (maxInput.value) minInput.max = maxInput.value;
    }

    caricaProdotti();

    document.getElementById("input-ricerca").addEventListener("input", caricaProdotti);
    document.getElementById("input-colore").addEventListener("input", caricaProdotti);
    document.getElementById("input-taglia").addEventListener("input", caricaProdotti);
    
    document.getElementById("input-categoria").addEventListener("change", caricaProdotti);

    if (minInput) {
        minInput.addEventListener("input", () => {
            const minVal = minInput.value;
            if (maxInput) {
                maxInput.min = minVal;
                if (maxInput.value && parseFloat(maxInput.value) < parseFloat(minVal)) {
                    maxInput.value = minVal;
                }
            }
            caricaProdotti(); 
        });
    }

    if (maxInput) {
        maxInput.addEventListener("input", () => {
            const maxVal = maxInput.value;
            if (minInput) {
                minInput.max = maxVal;
                if (minInput.value && parseFloat(minInput.value) > parseFloat(maxVal)) {
                    minInput.value = maxVal;
                }
            }
            caricaProdotti(); 
        });
    }
});

function aggiornaSuggerimentiColori(listaCappelli) {
    const datalist = document.getElementById("suggerimenti-colori");
    if (!datalist) return;
    
    if (document.activeElement === document.getElementById("input-colore")) {
        return; 
    }
    
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