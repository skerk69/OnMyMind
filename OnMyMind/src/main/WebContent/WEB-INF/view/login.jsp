<%@page import="model.Utente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>

<% ArrayList<Utente> listUser = (ArrayList<Utente>) request.getAttribute("utenti"); %>

<form action="${pageContext.request.contextPath}/login" method="post">
	<input type="text" name="username">
	<input type="password" name="password">
</form>
<!-- da spostare successivamente -->
<a href="${pageContext.request.contextPath}/management">accedi alla lista prodotti</a>

</body>
</html>