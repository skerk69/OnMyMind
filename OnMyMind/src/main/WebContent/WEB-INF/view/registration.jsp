<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crea Account - On My Mind</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<main class="login-page-container">
    <div class="auth-card" style="max-width: 500px;">
        <h1 class="auth-title">Registrazione</h1>
        <p class="auth-subtitle">Inserisci i tuoi dati per creare un nuovo account</p>
        
        <form id="form-registrazione" action="${pageContext.request.contextPath}/registration" method="post" class="auth-form">
            <div class="input-group">
                <label for="email">Email</label>
                <input type="text" id="email" name="email" required>
                <span id="error-email" class="error"></span>
            </div>

            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
                <span id="error-password" class="error"></span>
            </div>
            
            <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 15px;">
                <div class="input-group">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" required>
                </div>
                <div class="input-group">
                    <label for="cognome">Cognome</label>
                    <input type="text" id="cognome" name="cognome" required>
                </div>
            </div>

            <div class="input-group">
                <label for="telefono">Telefono</label>
                <input type="tel" id="telefono" name="telefono" required>
                <span id="error-telefono" class="error"></span>
            </div>
            
            <button type="submit" class="btn-primary">Registrati</button>
            <span id="error-register" style="color: #ff6b6b; font-size: 0.8rem; text-align: center;"></span>
        </form>
    </div>
</main>

<script src="${pageContext.request.contextPath}/js/register_script.js"></script>
</body>
</html>