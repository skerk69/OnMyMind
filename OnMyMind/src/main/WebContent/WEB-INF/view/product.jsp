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
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

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

		<form action="${pageContext.request.contextPath}/addreview" method="post">
			<label> Voto:
			<input type="number" id="voto" name="voto">
			</label>
			<label>
			<textarea id="descrizione" name="descrizione"></textarea>
			</label>
			<input type="hidden" name="id" value="<%= cap.getId_cappello() %>">
		</form>

<%
if(rec != null && !rec.isEmpty()){
		for(Recensione r : rec){
%>
		<%= r.getUtente().getNome() + r.getUtente().getCognome() %><br>
		<%= r.getVoto() %><br>
		<%= r.getCommento() %><br>
		
<% }} %>


</body>
</html>