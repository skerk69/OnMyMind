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
ArrayList<Ordine> olist = (ArrayList<Ordine>) request.getAttribute("ordini");
ArrayList<Cappello> clist = (ArrayList<Cappello>) request.getAttribute("cappelli");
ArrayList<DettaglioOrdine> dlist;
Cappello c;
if(olist != null && !olist.isEmpty()){
	for(Ordine o : olist){
%>
		Ordine fatto il  <%= o.getData_ordine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) %><br>
		<% 
		dlist = o.getDettagliordini();
		for(DettaglioOrdine d : dlist){
		%>
			<%= d.getCappello().getNome() + " x" + d.getQuantita()%><br>
			
<% } %>
<span class="prezzo"><%= o.getTotale() %></span>$
<br><br>
<% } } %>

<script src="${pageContext.request.contextPath}/js/priceformat_script.js" defer></script>

</body>
</html>