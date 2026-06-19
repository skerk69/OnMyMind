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

<form action="${pageContext.request.contextPath}/login" method="post">
	<label> Email:
	<input type="email" name="email">
	</label>
	<label> Password:
	<input type="password" name="password">
	</label><br>
	<button type="submit" name="action" value="login">Accedi</button>
	<button type="submit" name="action" value="register">Registrati</button>
</form>



<!-- da spostare successivamente -->
<a href="${pageContext.request.contextPath}/management">accedi alla lista prodotti</a>

</body>
</html>