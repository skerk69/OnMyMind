<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Collezione</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

    <div class="catalogo">

        <div id="filtri">
    <h3>Cerca nella Collezione</h3>
    
    <div class="gruppo-filtro">
        <label for="input-ricerca">Nome Prodotto:</label>
        <input type="text" id="input-ricerca" placeholder="Cerca un cappello...">
    </div>

    <hr> <h3>Filtri</h3>
    
    <div class="gruppo-filtro">
    	<label for="input-colore">Colore:</label>
    	<input type="text" id="input-colore" list="suggerimenti-colori" placeholder="Es. Nero, Blu...">
    
		<datalist id="suggerimenti-colori"></datalist>
	</div>

    <div class="gruppo-filtro">
        <label for="input-taglia">Taglia:</label>
        <input type="text" id="input-taglia" placeholder="Es. M, L...">
    </div>

    <div class="gruppo-filtro">
        <label>Prezzo ($):</label>
        <input type="number" id="input-prezzo-min" step="0.01" placeholder="Min">
        <input type="number" id="input-prezzo-max" step="0.01" placeholder="Max">
    </div>
</div>

        <main id="contenitore">
            <p>Caricamento della collezione in corso...</p>
        </main>

    </div>

<script>
        const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/js/catalogo_ajax.js" defer></script>

</body>
</html>