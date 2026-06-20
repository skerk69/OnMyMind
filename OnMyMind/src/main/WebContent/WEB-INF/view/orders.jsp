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
	int i=1;
	for(Ordine o : olist){
%>
		<h2>Ordine numero <%= i %></h2> fatto in data <%= o.getData_ordine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) %>
		dlist = o.getDettagliordini();
		for(DettaglioOrdine d : dlist){
%>
			<p><%= d.getCappello().getNome() %>
			
<% } i++;} } %>


</body>
</html>