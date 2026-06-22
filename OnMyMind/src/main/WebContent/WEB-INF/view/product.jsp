<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.Utente"%>
<%@page import="model.Recensione"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Categoria"%>
<%@page import="model.Cappello"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>On My Mind - <%= ((Cappello)request.getAttribute("cappello")).getNome() %></title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/product.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<%
Cappello cap = (Cappello) request.getAttribute("cappello"); 
Categoria cat = (Categoria) request.getAttribute("categoria");
@SuppressWarnings("unchecked")
ArrayList<Recensione> rec = (ArrayList<Recensione>) request.getAttribute("recensioni");
Utente u = (Utente) session.getAttribute("utente");
boolean comprato = (boolean) request.getAttribute("comprato"); 
boolean recensito = (boolean) request.getAttribute("recensito");
int id = 0;
if(u != null){
	id = u.getId_utente();
}
%>

<main class="product-page-container">
    <div class="product-main-wrapper">
        
        <div class="product-gallery">
            <div class="image-box">
                <img src="${pageContext.request.contextPath}/images/<%= cap.getImmagine() %>" alt="<%= cap.getNome() %>">
                <% if(cap.getQuantitaMagazzino() <= 3 && cap.getQuantitaMagazzino() > 0) { %>
                    <span class="stock-badge warning">Solo <%= cap.getQuantitaMagazzino() %> rimasti</span>
                <% } else if(cap.getQuantitaMagazzino() == 0) { %>
                    <span class="stock-badge alert">Sold Out</span>
                <% } %>
            </div>
        </div>

        <div class="product-info-panel">
            <span class="product-category"><%= cat.getNomeCategoria() %></span>
            <h1 class="product-title"><%= cap.getNome() %></h1>
            
            <div class="product-price-row">
                <span class="prezzo"><%= cap.getPrezzo() %></span><span class="currency">$</span>
            </div>

            <div class="product-specs">
                <div class="spec-item"><span>Taglia:</span> <strong><%= cap.getTaglia() %></strong></div>
                <div class="spec-item"><span>Colore:</span> <strong><%= cap.getColore() %></strong></div>
                <div class="spec-item"><span>Materiale:</span> <strong><%= cap.getMateriale() %></strong></div>
                <div class="spec-item"><span>Disponibilità:</span> <strong><%= cap.getQuantitaMagazzino() %> pz</strong></div>
            </div>

            <p class="product-description"><%= cap.getDescrizione() %></p>

            <% if(cap.getQuantitaMagazzino() > 0) { %>
                <form action="${pageContext.request.contextPath}/addcart" method="post" class="cart-action-form">
                    <input type="hidden" name="id" value="<%= cap.getId_cappello() %>">
                    <div class="qty-selector">
                        <label for="quantita">Quantità</label>
                        <input type="number" id="quantita" name="quantita" value="1" min="1" max="<%= cap.getQuantitaMagazzino() %>">
                    </div>
                    <button type="submit" class="btn-add-cart">Aggiungi al Carrello</button>
                </form>
            <% } %>

            <% if(comprato && !recensito){ %>
                <div class="write-review-box">
                    <h3 class="review-box-title">Lascia una recensione</h3>
                    <form action="${pageContext.request.contextPath}/addreview" method="post" class="review-form">
                        <input type="hidden" name="id" value="<%= cap.getId_cappello() %>">
                        
                        <div class="form-group">
                            <label for="voto">Valutazione (1-5 stelle)</label>
                            <input type="number" id="voto" name="voto" min="1" max="5" value="5" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="descrizione">Il tuo commento</label>
                            <textarea id="descrizione" name="descrizione" rows="3" placeholder="Raccontaci la tua esperienza con questo prodotto..." required></textarea>
                        </div>
                        
                        <button type="submit" class="btn-submit-review">Pubblica Recensione</button>
                    </form>
                </div>
            <% } %>
        </div>
    </div>

    <section class="reviews-section">
        <h2 class="section-title">Recensioni della Community</h2>
        
        <% if(rec != null && !rec.isEmpty()){ %>
            <div class="reviews-grid">
                <% for(Recensione r : rec){ %>
                    <div class="review-card <%= (r.getUtente().getId_utente() == id) ? "own-review" : "" %>">
                        <div class="review-header">
                            <div class="review-user">
                                <span class="user-avatar"><%= r.getUtente().getNome().substring(0,1) %></span>
                                <div class="user-meta">
                                    <h4><%= r.getUtente().getNome() + " " + r.getUtente().getCognome() %></h4>
                                    <span class="review-date"><%= r.getData_recensione().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) %></span>
                                </div>
                            </div>
                            <div class="review-rating">
                                <% for(int i=0; i<5; i++) { %>
                                    <span class="star <%= (i < r.getVoto()) ? "active" : "" %>">★</span>
                                <% } %>
                            </div>
                        </div>
                        
                        <p class="review-body"><%= r.getCommento() %></p>
                        
                        <% if(r.getUtente().getId_utente() == id && u != null){ %>
                            <div class="review-actions">
                                <form action="${pageContext.request.contextPath}/removereview" method="post">
                                    <input type="hidden" name="id_rec" value="<%= r.getId_recensione() %>">
                                    <input type="hidden" name="id_cap" value="<%= cap.getId_cappello() %>">
                                    <button type="submit" class="btn-delete-review">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path></svg>
                                        Elimina recensione
                                    </button>
                                </form>
                            </div>
                        <% } %>
                    </div>
                <% } %>
            </div>
        <% } else { %>
            <div class="no-reviews">
                <p>Non ci sono ancora recensioni per questo articolo. Sii il primo a condividerne una!</p>
            </div>
        <% } %>
    </section>
</main>

<script src="${pageContext.request.contextPath}/js/priceformat_script.js" defer></script>
</body>
</html>