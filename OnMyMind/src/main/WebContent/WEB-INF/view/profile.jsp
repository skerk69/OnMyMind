<%@page import="model.Indirizzo"%>
<%@page import="model.Utente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>On My Mind - Il Mio Profilo</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/profile.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<% Utente u = (Utente) session.getAttribute("utente"); %>

<main class="profile-page-container">
    <h1 class="page-title">Il Mio Account</h1>

    <div class="profile-wrapper">
        <section class="profile-card">
            <h2 class="section-title">Dati Personali</h2>
            <form id="form-user" action="${pageContext.request.contextPath}/profile" method="post" class="auth-form">
                <div class="input-group">
                    <label>Nome</label>
                    <input type="text" name="nome" value="<%= u.getNome() %>" required>
                </div>
                <div class="input-group">
                    <label>Cognome</label>
                    <input type="text" name="cognome" value="<%= u.getCognome() %>" required>
                </div>
                <div class="input-group">
                    <label>Email</label>
                    <input type="email" name="email" value="<%= u.getEmail() %>" required>
                    <span id="error-email" class="error"></span>
                </div>
                <div class="input-group">
                    <label>Nuova Password (lascia vuoto per non modificare)</label>
                    <input type="password" name="password" placeholder="••••••••">
                    <span id="error-password" class="error"></span>
                </div>
                <div class="input-group">
                    <label>Telefono</label>
                    <input type="tel" name="telefono" value="<%= u.getTelefono() %>" required>
                    <span id="error-telefono" class="error"></span>
                </div>
                <button type="submit" name="action" value="modify" class="btn-primary">Aggiorna Profilo</button>
            </form>
        </section>

        <section class="profile-card">
            <h2 class="section-title">I Tuoi Indirizzi</h2>
            <div class="address-list">
                <%
                @SuppressWarnings("unchecked")
                ArrayList<Indirizzo> listAddress = (ArrayList<Indirizzo>) request.getAttribute("indirizzi");
                if(listAddress != null && !listAddress.isEmpty()){
                    for(Indirizzo i : listAddress){
                %>
                <div class="address-item">
                    <div class="address-display">
                        <p class="address-text"><%= i.getVia() %>, <%= i.getCitta() %> (<%= i.getProvincia() %>) - <%= i.getCap() %></p>
                        <button type="button" class="btn-toggle btn-secondary" data-target="form-edit-<%= i.getId_indirizzo() %>">Modifica</button>
                    </div>
                    
                    <div id="form-edit-<%= i.getId_indirizzo() %>" class="form-modifica" hidden>
                        <form action="${pageContext.request.contextPath}/profile" method="post" class="auth-form nested-form">
                            <div class="input-group">
                                <label>Via e Numero Civico</label>
                                <input type="text" name="via" value="<%= i.getVia() %>" required>
                            </div>
                            <div class="input-grid-three">
                                <div class="input-group">
                                    <label>Città</label>
                                    <input type="text" name="citta" value="<%= i.getCitta() %>" required>
                                </div>
                                <div class="input-group">
                                    <label>Prov.</label>
                                    <input type="text" name="provincia" value="<%= i.getProvincia() %>" maxlength="2" required>
                                </div>
                                <div class="input-group">
                                    <label>CAP</label>
                                    <input type="text" name="cap" value="<%= i.getCap() %>" maxlength="5" required>
                                </div>
                            </div>
                            <div class="input-group">
                                <label>Paese</label>
                                <input type="text" name="paese" value="<%= i.getPaese() %>" required>
                            </div>
                            
                            <input type="hidden" name="id_indirizzo" value="<%= i.getId_indirizzo() %>">
                            
                            <div class="action-buttons">
                                <button type="submit" name="action" value="modifyAddress" class="btn-primary btn-small">Salva</button>
                                <button type="submit" name="action" value="delete" class="btn-delete">Elimina</button>
                            </div>
                        </form>
                    </div>
                </div>
                <% }} else { %>
                    <p class="no-addresses">Nessun indirizzo salvato.</p>
                <% } %>
            </div>

            <h3 class="section-title sub-title">Aggiungi Nuovo Indirizzo</h3>
            <form action="${pageContext.request.contextPath}/profile" method="post" class="auth-form line-form">
                <div class="input-group">
                    <label>Via e Numero Civico</label>
                    <input type="text" name="via" placeholder="Es. Via Roma 12" required>
                </div>
                <div class="input-grid-three">
                    <div class="input-group">
                        <label>Città</label>
                        <input type="text" name="citta" placeholder="Es. Milano" required>
                    </div>
                    <div class="input-group">
                        <label>Provincia</label>
                        <input type="text" name="provincia" placeholder="Es. MI" maxlength="2" required>
                    </div>
                    <div class="input-group">
                        <label>CAP</label>
                        <input type="text" name="cap" placeholder="Es. 20100" maxlength="5" required>
                    </div>
                </div>
                <div class="input-group">
                    <label>Paese</label>
                    <input type="text" name="paese" placeholder="Es. Italia" required>
                </div>
                <button type="submit" name="action" value="insertAddress" class="btn-secondary-full">Inserisci Indirizzo</button>
            </form>
        </section>
    </div>

    <form action="${pageContext.request.contextPath}/logout" method="post" class="logout-zone">
        <button type="submit" class="btn-logout">Disconnettiti dal profilo</button>
    </form>
</main>

<script src="${pageContext.request.contextPath}/js/profile_script.js"></script>
</body>
</html>