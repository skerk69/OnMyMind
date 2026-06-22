<%@page import="java.util.ArrayList"%>
<%@page import="model.Categoria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Collezione</title>
<link rel="stylesheet" href="css/collection.css">

</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<%
@SuppressWarnings("unchecked")
ArrayList<Categoria> listCat = (ArrayList<Categoria>) request.getAttribute("listCat");
%>

    <div class="catalogo">

        <div id="filtri">
    <h3>Cerca nella Collezione</h3>
    
    <div class="gruppo-filtro">
        <label for="input-ricerca">Nome Prodotto:</label>
        <input type="text" id="input-ricerca" placeholder="Cerca un cappello...">
    </div>

    <hr> <h3>Filtri</h3>
    
			<div class="gruppo-filtro">
                <label for="input-categoria">Categoria:</label>
                <input type="text" id="input-categoria" list="suggerimenti-categorie" placeholder="Es. Berretti, Snapback...">
                
                <datalist id="suggerimenti-categorie">
                    <% if(listCat != null && !listCat.isEmpty()){ 
                        for(Categoria c : listCat){ %>
                        <option value="<%= c.getNomeCategoria() %>"></option>
                    <% } } %>
                </datalist>
            </div> 
    
    
    <div class="gruppo-filtro">
    	<label for="input-colore">Colore:</label>
    	<input type="text" id="input-colore" list="suggerimenti-colori" placeholder="Es. Nero, Blu...">
    
		<datalist id="suggerimenti-colori"></datalist>
	</div>

			<div class="gruppo-filtro">
                <label for="input-taglia">Taglia:</label>
                <select id="input-taglia">
                    <option value="">Tutte le taglie</option>
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                    <option value="Unica">Taglia Unica</option>
                </select>
            </div>

    <div class="gruppo-filtro">
        <label>Prezzo ($):</label>
        <input type="number" id="input-prezzo-min" step="0.01" placeholder="Min" min="0">
        <input type="number" id="input-prezzo-max" step="0.01" placeholder="Max" min="0">
    </div>
</div>

        <main id="contenitore">
            <p>Caricamento della collezione in corso...</p>
        </main>

    </div>
<!--  soldout se quantita = 0 -->
<script>
        const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/js/catalogo_ajax.js" defer></script>

</body>
</html>