<%@page import="model.Indirizzo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>On My Mind - Checkout</title>
    <link rel="stylesheet" href="css/checkout.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<main class="checkout-page-container">
    <h1 class="page-title">Finalizza Ordine</h1>

    <div class="checkout-card">
        <form action="${pageContext.request.contextPath}/order" method="post" class="checkout-form">
            
            <section class="checkout-section">
                <h2 class="section-title">1. Indirizzo di Spedizione</h2>
                
                <div class="input-group">
                    <label for="sceltaIndirizzo">Dove desideri ricevere il tuo ordine?</label>
                    <select name="id" id="sceltaIndirizzo" required>
                        <option value="-1" disabled selected>-- Scegli dove spedire --</option>
                        <%
                        @SuppressWarnings("unchecked")
                        ArrayList<Indirizzo> listAddress = (ArrayList<Indirizzo>) request.getAttribute("indirizzi");
                        if(listAddress != null && !listAddress.isEmpty()){
                            for(Indirizzo i : listAddress){
                        %>
                                <option value="<%= i.getId_indirizzo() %>">
                                    <%= "Via " + i.getVia() + ", " + i.getCitta() + " (" + i.getProvincia() + ") - " + i.getPaese() %>
                                </option>
                        <% 
                            }
                        } 
                        %>
                        <option value="0">Inserisci nuovo indirizzo...</option>
                    </select>
                </div>

                <div id="nuovo" class="new-address-box">
                    <div class="input-group">
                        <label>Via e Numero Civico</label>
                        <input type="text" name="via" placeholder="Es. Via Roma 12">
                    </div>
                    
                    <div class="input-grid-three">
                        <div class="input-group">
                            <label>Città</label>
                            <input type="text" name="citta" placeholder="Es. Milano">
                        </div>
                        <div class="input-group">
                            <label>Provincia</label>
                            <input type="text" name="provincia" placeholder="Es. MI" maxlength="2">
                        </div>
                        <div class="input-group">
                            <label>CAP</label>
                            <input type="text" name="cap" placeholder="Es. 20100" maxlength="5">
                        </div>
                    </div>
                    
                    <div class="input-group">
                        <label>Paese</label>
                        <input type="text" name="paese" placeholder="Es. Italia">
                    </div>
                    
                    <button type="submit" class="btn-sub-insert">Conferma Nuovo Indirizzo</button>
                </div>
            </section>

            <section class="checkout-section">
                <h2 class="section-title">2. Metodo di Pagamento</h2>
                
                <div class="input-group">
                    <label for="metodoPagamento">Seleziona un circuito di pagamento</label>
                    <select id="metodoPagamento">
                        <option>Carta di Credito</option>
                        <option>Paypal</option>
                        <option>Apple Pay</option>
                        <option>Google Pay</option>
                    </select>
                </div>
                
                <div class="payment-security-notice">
                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect><path d="M7 11V7a5 5 0 0 1 10 0v4"></path></svg>
                    <span>Pagamento sicuro e transazione crittografata SSL.</span>
                </div>
            </section>

            <div class="checkout-actions-zone">
                <button type="submit" id="orderButton" class="btn-primary-checkout">Procedi all'acquisto</button>
            </div>
        </form>
    </div>
</main>

<script src="${pageContext.request.contextPath}/js/checkout_script.js" defer></script>
</body>
</html>