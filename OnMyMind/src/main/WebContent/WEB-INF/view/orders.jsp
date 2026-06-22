<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.Cappello"%>
<%@page import="model.DettaglioOrdine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Ordine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ordini Effettuati</title>
    <link rel="stylesheet" href="css/orders.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<main class="orders-container">
    <h1 class="page-title">
        <% if("admin".equals(session.getAttribute("ruolo"))){ %>
            Registro Ordini Clienti
        <% } else { %>
            I Tuoi Ordini
        <% } %>
    </h1>

    <% 
    @SuppressWarnings("unchecked")
    ArrayList<Ordine> olist = (ArrayList<Ordine>) request.getAttribute("ordini");
    @SuppressWarnings("unchecked")
    ArrayList<Cappello> clist = (ArrayList<Cappello>) request.getAttribute("cappelli");
    ArrayList<DettaglioOrdine> dlist;
    String ruolo = (String) session.getAttribute("ruolo");
    %>

    <% 
    if(olist != null && !olist.isEmpty()){
        for(Ordine o : olist){
    %>
            <div class="order-card">
                <div class="order-header">
                    <div class="order-info">
                        <span class="order-label">Effettuato il:</span>
                        <span class="order-date"><%= o.getData_ordine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) %></span>
                    </div>
                    <% if("admin".equals(ruolo)){ %>
                        <div class="admin-user-details">
                            <span class="order-label">Cliente:</span>
                            <span class="user-info"><%= o.getUtente().getNome() + " " + o.getUtente().getCognome() %> (<%= o.getUtente().getEmail() %>)</span>
                        </div>
                    <% } %>
                </div>

                <div class="order-items">
                    <% 
                    dlist = o.getDettagliordini();
                    for(DettaglioOrdine d : dlist){
                    %>
                        <div class="order-item">
                            <div class="item-img-wrapper">
                                <img src="${pageContext.request.contextPath}/images/<%= d.getImmagine() %>" alt="<%= d.getNome_cappello() %>">
                            </div>
                            <div class="item-details">
                                <h3 class="item-name"><%= d.getNome_cappello() %></h3>
                                <div class="item-meta">
                                    <span>Taglia: <strong><%= d.getTaglia() %></strong></span>
                                    <span>Colore: <strong><%= d.getColore() %></strong></span>
                                    <span>Quantità: <strong>x<%= d.getQuantita() %></strong></span>
                                </div>
                            </div>
                        </div>
                    <% } %>
                </div>

                <div class="order-footer">
                    <span class="total-label">Totale Ordine</span>
                    <div class="total-price-wrapper">
                        <span class="prezzo"><%= o.getTotale() %></span><span class="currency">$</span>
                    </div>
                </div>
            </div>
    <% 
        } 
    } else { 
    %>
        <div class="no-orders">
            <p>Non è stato trovato alcun ordine in archivio.</p>
        </div>
    <% } %>
</main>

<script src="${pageContext.request.contextPath}/js/priceformat_script.js" defer></script>

<jsp:include page="/WEB-INF/layout/footer.jsp"/>

</body>
</html>