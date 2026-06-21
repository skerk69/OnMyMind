<%@page import="model.Cappello"%>
<%@page import="model.DettaglioOrdine"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<%
@SuppressWarnings("unchecked")
ArrayList<DettaglioOrdine> dolist = (ArrayList<DettaglioOrdine>) session.getAttribute("carrello");
@SuppressWarnings("unchecked")
ArrayList<Cappello> listcap = (ArrayList<Cappello>) request.getAttribute("cappelli");
double tot = 0;
if(dolist != null && !dolist.isEmpty()){
	for(DettaglioOrdine d : dolist){
%>
	<img src="${pageContext.request.contextPath}/images/<%= d.getImmagine() %>" width=100><br>
    <%= d.getNome_cappello() %><br>
	Pezzi: <%= d.getQuantita() %>, 
	Prezzo: <span class="prezzo"><%= d.getPrezzo_unitario() * d.getQuantita() %> </span>$ <br>

	<form action="${pageContext.request.contextPath}/removecart" method="post"><!--  da fare -->
		<input type="hidden" name="id" value="<%= d.getId_cappello() %>">
		<button type="submit">Rimuovi</button>
	</form>
	<% tot+= d.getPrezzo_unitario()*d.getQuantita(); %>
<% }} %>
<br><br>
Totale: <span class="prezzo"><%= tot %>$</span>
<form action="${pageContext.request.contextPath}/checkout" method="post">
<button type="submit">Procedi all'acquisto</button>
</form>


<script src="${pageContext.request.contextPath}/js/priceformat_script.js" defer></script>
</body>
</html>