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


<form id="form-registrazione" action="${pageContext.request.contextPath}/registration" method="post">
    <label>Email:
        <input type="text" id="email" name="email" required>
        <span id="error-email" class="error"></span>
    </label><br>

    <label>Password:
        <input type="password" id="password" name="password" required>
        <span id="error-password" class="error"></span>
    </label><br>
    
    <label>Nome:
        <input type="text" id="nome" name="nome" required>
    </label><br>

    <label>Cognome:
        <input type="text" id="cognome" name="cognome" required>
    </label><br>

    <label>Telefono:
        <input type="tel" id="telefono" name="telefono" required>
        <span id="error-telefono" class="error"></span>
    </label><br>
    
    <button type="submit">Registrati</button><br>
	<span id="error-register"></span>
</form>

<script src="${pageContext.request.contextPath}/js/register_script.js"></script>

</body>
</html>