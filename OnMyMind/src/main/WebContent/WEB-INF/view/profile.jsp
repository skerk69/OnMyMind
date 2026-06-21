<%@page import="model.Indirizzo"%>
<%@page import="model.Utente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profilo</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<%
Utente u = (Utente) session.getAttribute("utente");

%>

<%= u.getNome() %> <%= u.getCognome() %> <%= u.getEmail() %>

<form id="form-user" action="${pageContext.request.contextPath}/profile" method="post">
    <input type="text" name="nome" placeholder="Nuovo Nome" value="<%= u.getNome() %>" required><br>
    <input type="text" name="cognome" placeholder="Nuovo Cognome" value="<%= u.getCognome() %>" required><br>
    <input type="email" name="email" value="<%= u.getEmail() %>" required><span id="error-email"></span><br>
    <input type="password" name="password" placeholder="Nuova Password"><span id="error-password"></span><br>
    <input type="tel" name="telefono" value="<%= u.getTelefono() %>" required><span id="error-telefono"></span><br>
    <button type="submit" name="action" value="modify">Aggiorna Profilo</button><br>
</form>

	<h3>I Tuoi Indirizzi</h3>
	<%
	@SuppressWarnings("unchecked")
	ArrayList<Indirizzo> listAddress = (ArrayList<Indirizzo>) request.getAttribute("indirizzi");
	if(!listAddress.isEmpty()){
	for(Indirizzo i : listAddress){
	 %>
<p>Via <%= i.getVia() %>, <%= i.getCitta() %> (<%= i.getProvincia() %>) - <%= i.getCap() %></p>
    
<button type="button" class="btn-toggle" data-target="form-edit-<%= i.getId_indirizzo() %>">
    Modifica Indirizzo
</button>    
    <div id="form-edit-<%= i.getId_indirizzo() %>" class="form-modifica" hidden="true">
        <form action="${pageContext.request.contextPath}/profile" method="post"> 
            <input type="text" name="paese" placeholder="Paese" value="<%= i.getPaese() %>" required><br>
            <input type="text" name="provincia" placeholder="Provincia" value="<%= i.getProvincia() %>" maxlength="2" required><span id="error-provincia"></span><br>
            <input type="text" name="cap" placeholder="Cap" value="<%= i.getCap() %>" maxlength="5" required><span id="error-cap"></span><br>
            <input type="text" name="citta" placeholder="Città" value="<%= i.getCitta() %>" required><br>
            <input type="text" name="via" placeholder="Via" value="<%= i.getVia() %>" required><br>
            <input type="hidden" name="id_indirizzo" value="<%= i.getId_indirizzo() %>"><br>
            <button type="submit" name="action" value="modifyAddress">Salva Modifiche</button><br>
            <button type="submit" name="action" value="delete">Elimina</button><br>
        </form>
    </div>
	<% }} %>


<h3>Aggiungi Nuovo Indirizzo</h3>
<form action="${pageContext.request.contextPath}/profile" method="post">
    <input type="text" name="paese" placeholder="Paese" required><br>
    <input type="text" name="provincia" placeholder="Provincia" maxlength="2" required><span id="error-provincia"></span><br>
    <input type="text" name="cap" placeholder="CAP" maxlength="5" required><span id="error-cap"></span><br>
    <input type="text" name="citta" placeholder="Città" required><br>
    <input type="text" name="via" placeholder="Via" required><br>
    <button type="submit" name="action" value="insertAddress">Inserisci Indirizzo</button><br>
</form>
<br><br>
<!-- aggiungi controllo javascript che i campi non siano vuoti prima di premere pulsante, se lo sono lo metti disabled, e aggiungi pulsante modifica che apre il menù, come in management -->
<form action="${pageContext.request.contextPath}/logout" method="post">
<button type="submit">Logout</button>
</form>

<script src="${pageContext.request.contextPath}/js/profile_script.js"></script>

</body>
</html>