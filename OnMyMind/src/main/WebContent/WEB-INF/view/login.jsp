<%@page import="model.Utente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>On My Mind - Accesso</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<main class="login-page-container">
    <div class="auth-card">
        <h1 class="auth-title">Area Riservata</h1>
        <p class="auth-subtitle">Accedi al tuo account On My Mind o creane uno nuovo</p>
        
        <form action="${pageContext.request.contextPath}/login" method="post" class="auth-form">
            <div class="input-group">
                <label for="email">Indirizzo Email</label>
                <input type="email" id="email" name="email" required autocomplete="email" placeholder="esempio@dominio.com">
            </div>
            
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required placeholder="••••••••">
            </div>
            
            <div class="auth-actions">
                <button type="submit" class="btn-primary">Accedi</button>
            </div>
        </form>

        <div class="registration-zone">
            <div class="auth-divider">
                <span>Nuovo cliente?</span>
            </div>
            
            <a href="${pageContext.request.contextPath}/registration" class="btn-secondary">Crea un account</a>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/layout/footer.jsp"/>

</body>
</html>