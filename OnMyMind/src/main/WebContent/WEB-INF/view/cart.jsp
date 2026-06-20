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
ArrayList<DettaglioOrdine> dolist = (ArrayList<DettaglioOrdine>) session.getAttribute("carrello"); 
ArrayList<Cappello> listcap = (ArrayList<Cappello>) request.getAttribute("cappelli");
if(dolist != null && !dolist.isEmpty()){
	for(DettaglioOrdine d : dolist){
%>
	<% Cappello cap = d.getCappello(); %>
	<img src="${pageContext.request.contextPath}/images/<%= cap.getImmagine() %>" width=100><br>
    <%= cap.getNome() %><br>
	<%= d.getQuantita() %>, <%= d.getPrezzo_unitario() + "$" %> <br>

	<form action="${pageContext.request.contextPath}/removecart" method="post"><!--  da fare -->
		<input type="hidden" name="id" value="<%= cap.getId_cappello() %>">
		<button type="submit">Rimuovi</button>
	</form>
	
<% }} %>

</body>
</html>