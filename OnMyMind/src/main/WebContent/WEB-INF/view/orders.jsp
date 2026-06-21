<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.Cappello"%>
<%@page import="model.DettaglioOrdine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Ordine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordini Effettuati</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<% 
@SuppressWarnings("unchecked")
ArrayList<Ordine> olist = (ArrayList<Ordine>) request.getAttribute("ordini");
@SuppressWarnings("unchecked")
ArrayList<Cappello> clist = (ArrayList<Cappello>) request.getAttribute("cappelli");
ArrayList<DettaglioOrdine> dlist;
String ruolo = (String) session.getAttribute("ruolo");
Cappello c;
if(olist != null && !olist.isEmpty()){
	for(Ordine o : olist){
%>
		Ordine fatto il  <%= o.getData_ordine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) %>
		<% if(ruolo.equals("admin")){ %>
			da <%= o.getUtente().getNome() + " " + o.getUtente().getCognome() + ", " + o.getUtente().getEmail()%>
		<% } %>
		<br>
		<% 
		dlist = o.getDettagliordini();
		for(DettaglioOrdine d : dlist){
		%>
			<img src="${pageContext.request.contextPath}/images/<%= d.getImmagine() %>" width=100><br>
			<%= d.getNome_cappello() + " " + d.getTaglia() + " " + d.getColore() + " x" + d.getQuantita()%><br>
			
<% } %>
<span class="prezzo"><%= o.getTotale() %></span>$
<br><br>
<% } } %>

<script src="${pageContext.request.contextPath}/js/priceformat_script.js" defer></script>

</body>
</html>