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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    
    // Recuperiamo i parametri di ricerca attuali per tenerli stampati negli input
    String dataInizioParam = request.getParameter("dataInizio") != null ? request.getParameter("dataInizio") : "";
    String dataFineParam = request.getParameter("dataFine") != null ? request.getParameter("dataFine") : "";
    String utenteQueryParam = request.getParameter("utenteQuery") != null ? request.getParameter("utenteQuery") : "";
    %>

<div class="filters-wrapper">
    <form action="" method="get" class="filters-form">
        
        <div class="filter-group">
            <label for="filter-start">Da data</label>
            <div class="input-icon-wrapper">
                <svg class="input-icon" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect><line x1="16" y1="2" x2="16" y2="6"></line><line x1="8" y1="2" x2="8" y2="6"></line><line x1="3" y1="10" x2="21" y2="10"></line></svg>
                <input type="date" id="filter-start" name="dataInizio" value="<%= dataInizioParam %>">
            </div>
        </div>
        
        <div class="filter-group">
            <label for="filter-end">A data</label>
            <div class="input-icon-wrapper">
                <svg class="input-icon" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect><line x1="16" y1="2" x2="16" y2="6"></line><line x1="8" y1="2" x2="8" y2="6"></line><line x1="3" y1="10" x2="21" y2="10"></line></svg>
                <input type="date" id="filter-end" name="dataFine" value="<%= dataFineParam %>">
            </div>
        </div>

        <% if("admin".equals(ruolo)){ %>
            <div class="filter-group filter-user-search">
                <label for="filter-user">Cerca Cliente</label>
                <div class="input-icon-wrapper">
                    <svg class="input-icon" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                    <input type="text" id="filter-user" name="utenteQuery" placeholder="Cerca..." value="<%= utenteQueryParam %>">
                </div>
            </div>
        <% } %>

        <div class="filter-actions">
            <button type="submit" class="btn-filter">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
                Filtra
            </button>
            <a href="?" class="btn-reset-filter" title="Resetta Filtri">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"><path d="M21.5 2v6h-6M21.34 15.57a10 10 0 1 1-.57-8.38l5.67-5.67"/></svg>
                Resetta
            </a>
        </div>
    </form>
</div>

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
                                <img src="${pageContext.request.contextPath}/images/<%= d.getImmagine() %>" alt="<%= d.getNome_cappello() %>" width="80" height="80">
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
            <p>Non è stato trovato alcun ordine in archivio per i filtri selezionati.</p>
        </div>
    <% } %>
</main>

<script src="${pageContext.request.contextPath}/js/priceformat_script.js" defer></script>
<script src="${pageContext.request.contextPath}/js/global-scroll.js" defer></script>

<jsp:include page="/WEB-INF/layout/footer.jsp"/>

</body>
</html>