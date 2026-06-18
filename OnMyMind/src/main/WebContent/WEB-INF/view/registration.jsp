<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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

	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String telefono;
	private Ruolo ruolo;

</body>
</html>