<%@page import="model.Categoria"%>
<%@page import="model.Cappello"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OnMyMind</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<% 
ArrayList<Cappello> listCap = (ArrayList<Cappello>) request.getAttribute("cappelli");
ArrayList<Categoria> listCat = (ArrayList<Categoria>) request.getAttribute("categorie");

for(Cappello c : listCap){
%>

    	<img src="${pageContext.request.contextPath}/images/<%= c.getImmagine() %>" width=100><br>
        <%= c.getNome() %> - <%= c.getPrezzo() + "$" %><br>
    	<%  Categoria ct = c.getCategoria();
    	if(!listCat.isEmpty()){
    		for (Categoria cat : listCat) {  
    			if(cat.getId_categoria() == ct.getId_categoria()){
    			%>
    				Categoria: <%= cat.getNomeCategoria() %><br>
    	<% }}} %>
    	<%= c.getTaglia() %>, <%= c.getColore() %>, <%= c.getMateriale() %>, <%= c.getQuantitaMagazzino() %> <br>
        <%= c.getDescrizione() %><br>


<% } %>




<script>
const ruolo = request.getAttribute();


</script>



</body>
</html>