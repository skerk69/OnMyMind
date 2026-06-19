<%@page import="model.Recensione"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Categoria"%>
<%@page import="model.Cappello"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Singolo prodotto</title>
</head>
<body>

<%
Cappello cap = (Cappello) request.getAttribute("cappello"); 
Categoria cat = (Categoria) request.getAttribute("categoria");
ArrayList<Recensione> rec = (ArrayList<Recensione>) request.getAttribute("recensioni");
%>

    	<img src="${pageContext.request.contextPath}/images/<%= cap.getImmagine() %>" width=100><br>
        <%= cap.getNome() %> - <%= cap.getPrezzo() + "$" %><br>
    	<%= cat.getNomeCategoria() %><br>
    	<%= cap.getTaglia() %>, <%= cap.getColore() %>, <%= cap.getMateriale() %>, <%= cap.getQuantitaMagazzino() %> <br>
        <%= cap.getDescrizione() %><br>

		<form action="${pageContext.request.contextPath}/addcart" method="post">
    
  			<input type="hidden" name="id" value="<%= cap.getId_cappello() %>">
    
    		<label for="quantita">Quantità:</label>
    		<input type="number" id="quantita" name="quantita" value="1" min="1">
    
    		<button type="submit">Aggiungi al Carrello</button>
		</form>

		<!-- aggiungere recensioni -->




</body>
</html>