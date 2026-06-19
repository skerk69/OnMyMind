<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrati</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>


<form action="${pageContext.request.contextPath}/registration" method="post">
	<label> Email:
	<input type="text" name="email">
	</label>
	<label> Password:
	<input type="password" name="password">
	</label><br>
	<label> Nome:
	<input type="text" name="nome">
	</label>
	<label> Cognome:
	<input type="text" name="cognome">
	</label>
	<label> Telefono:
	<input type="tel" name="telefono">
	</label>	
	<button type="submit">Registrati</button>
</form>


</body>
</html>