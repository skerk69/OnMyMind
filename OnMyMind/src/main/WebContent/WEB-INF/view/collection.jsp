<%@page import="model.Categoria"%>
<%@page import="model.Cappello"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Collezione</title>
<link rel="stylesheet" href="css/style.css">
</head>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>
<body>

<% 
@SuppressWarnings("unchecked")
ArrayList<Cappello> listCap = (ArrayList<Cappello>) request.getAttribute("cappelli");

for(Cappello c : listCap){
%>

		<a href="${pageContext.request.contextPath}/openproduct?id=<%= c.getId_cappello() %>">
    	<img src="${pageContext.request.contextPath}/images/<%= c.getImmagine() %>" width=100><br>
        <%= c.getNome() %><br>
    	</a>


<% } %>

</body>
</html>