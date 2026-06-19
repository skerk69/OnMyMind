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
<!-- trasferire l'intero nav in un file a parte e linkarlo in tutte le pagine -->
<nav>
<ul>
<li id="logo"><img src="${pageContext.request.contextPath}/images/OnMyMindLogo.png" width=100></li>
<li id="home"><a href="${pageContext.request.contextPath}/home">HOME</a></li>
<li id="collezione">COLLEZIONE</li>
<li id="cerca">CERCA</li>
<li id="login"><a href="${pageContext.request.contextPath}/loginpage">LOGIN</a></li> <!-- accessibile solo se non loggato(ruolo==null), da fare con javascript -->
<li id="profile"><a href="${pageContext.request.contextPath}/profile1">PROFILO</a></li> <!-- accessibile solo se loggato(ruolo!=null), da fare con javascript -->
<li id="management"><a href="${pageContext.request.contextPath}/management">MANAGEMENT</a></li> <!-- accessiibile solo se admin(ruolo!=null && ruolo = Ruolo.ADMIN) -->
</ul>
</nav>


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