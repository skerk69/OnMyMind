<%@page import="model.Cappello"%>
<%@page import="model.DettaglioOrdine"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrello</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/cart.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<main class="cart-page-container">
    <h1 class="page-title">Il Tuo Carrello</h1>

    <%
    @SuppressWarnings("unchecked")
    ArrayList<DettaglioOrdine> dolist = (ArrayList<DettaglioOrdine>) session.getAttribute("carrello");
    @SuppressWarnings("unchecked")
    ArrayList<Cappello> listcap = (ArrayList<Cappello>) request.getAttribute("cappelli");
    double tot = 0;
    
    if(dolist != null && !dolist.isEmpty()){
    %>
        <div class="cart-wrapper">
            <div class="cart-items-section">
                <% 
                for(DettaglioOrdine d : dolist){ 
                    double subtotale = d.getPrezzo_unitario() * d.getQuantita();
                    tot += subtotale;
                %>
                    <div class="cart-item">
                        <div class="item-img-wrapper">
                            <img src="${pageContext.request.contextPath}/images/<%= d.getImmagine() %>" alt="<%= d.getNome_cappello() %>">
                        </div>
                        
                        <div class="item-details">
                            <h2 class="item-name"><%= d.getNome_cappello() %></h2>
                            <div class="item-meta">
                                <span class="item-quantity">Quantità: <strong><%= d.getQuantita() %></strong></span>
                            </div>
                        </div>
                        
                        <div class="item-actions-zone">
                            <div class="item-price-wrapper">
                                <span class="prezzo"><%= subtotale %></span><span class="currency">$</span>
                            </div>
                            
                            <form action="${pageContext.request.contextPath}/removecart" method="post" class="remove-form">
                                <input type="hidden" name="id" value="<%= d.getId_cappello() %>">
                                <button type="submit" class="btn-remove">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path></svg>
                                    Rimuovi
                                </button>
                            </form>
                        </div>
                    </div>
                <% } %>
            </div>

            <div class="cart-summary-section">
                <div class="summary-card">
                    <h2 class="summary-title">Riepilogo Ordine</h2>
                    
                    <div class="summary-row">
                        <span>Spedizione</span>
                        <span class="shipping-free">Gratuita</span>
                    </div>
                    
                    <div class="summary-divider"></div>
                    
                    <div class="summary-row total-row">
                        <span>Totale</span>
                        <div class="total-price-block">
                            <span class="prezzo"><%= tot %></span><span class="currency">$</span>
                        </div>
                    </div>
                    
                    <form action="${pageContext.request.contextPath}/checkout" method="post">
                        <button type="submit" class="btn-checkout">Procedi all'acquisto</button>
                    </form>
                </div>
            </div>
        </div>
    <% 
    } else { 
    %>
        <div class="empty-cart-card">
            <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round" class="empty-icon"><path d="M6 2L3 6v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V6l-3-4z"></path><line x1="3" y1="6" x2="21" y2="6"></line><path d="M16 10a4 4 0 0 1-8 0"></path></svg>
            <p>Il tuo carrello è attualmente vuoto</p>
            <a href="${pageContext.request.contextPath}/collection" class="btn-continue">Esplora la collezione</a>
        </div>
    <% } %>
</main>

<script src="${pageContext.request.contextPath}/js/priceformat_script.js" defer></script>
</body>
</html>