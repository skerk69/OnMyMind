<%@page import="model.Categoria"%>
<%@page import="model.Cappello"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OnMyMind - Premium Headwear</title>
    <link rel="stylesheet" href="css/banner.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<section class="hero-banner">
    <div class="hero-overlay"></div>
    <div class="hero-content">
        <span class="hero-subtitle">Premium Headwear Collection</span>
        <h1 class="hero-title">On My Mind</h1>
        <p class="hero-description">
            Non è solo un accessorio, è lo specchio dei tuoi pensieri. Esplora una selezione esclusiva di cappelli pensati per chi definisce le proprie regole. Trova il pezzo perfetto da metterti in testa.
        </p>
        <div class="hero-actions">
            <a href="${pageContext.request.contextPath}/collection" class="btn-hero-primary">Scopri la Collezione</a>
        </div>
    </div>
</section>

<jsp:include page="/WEB-INF/layout/footer.jsp"/>
</body>
</html>