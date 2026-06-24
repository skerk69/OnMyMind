<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Modello Non Trovato | On My Mind</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css">
</head>
<body>

    <jsp:include page="/WEB-INF/layout/navbar.jsp"/>

    <main class="error-container">
        <div class="error-visual">
            <span class="error-code">404</span>
            <div class="error-divider"></div>
        </div>
        
        <div class="error-content">
            <h1 class="error-title">Fuori Collezione</h1>
            <p class="error-message">
                Il modello o la pagina che stavi cercando non fa parte della nostra selezione attuale, oppure è stato rimosso.
            </p>
            
            <div class="error-actions">
                <a href="${pageContext.request.contextPath}/home" class="btn-error-primary">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>
                    Torna alla Home
                </a>
                <a href="${pageContext.request.contextPath}/collection" class="btn-error-secondary">
                    Esplora i modelli
                </a>
            </div>
        </div>
    </main>

    <jsp:include page="/WEB-INF/layout/footer.jsp"/>

</body>
</html>