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
</head>
<body>
<%
ArrayList<Utente> listUtenti = (ArrayList<Utente>) request.getAttribute("utenti");
Utente u = (Utente) session.getAttribute("utente");

%>

<%= u.getNome() %> <%= u.getCognome() %> <%= u.getEmail() %>

<form action="${pageContext.request.contextPath}/profile" method="post">
    <label>Nome:
    <input type="text" name="nome"><br>
    </label>
    <label>Cognome:
    <input type="text" name="cognome"><br>
    </label>
    <label>Email:
    <input type="email" name="email"><br>
    </label>
    <label>Password:
    <input type="text" name="password"><br>
    </label>
    <label>Telefono:
    <input type="tel" name="telefono"><br>
    </label>
    <button type="submit" name="action" value="modify">Modifica</button>
</form>

<form action="${pageContext.request.contextPath}/profile" method="post">
	
	<% ArrayList<Indirizzo> listAddress = (ArrayList<Indirizzo>) session.getAttribute("indirizzi");
	if(!listAddress.isEmpty()){
	for(Indirizzo i : listAddress){
	 %>
	<label>
	<input type="text" name="paese<%= i.getId_indirizzo() %>" value="<%= i.getPaese() %>">
	</label>
	<label>
	<input type="text" name="provincia<%= i.getId_indirizzo() %>" value="<%= i.getProvincia() %>">
	</label>
	<label>
	<input type="text" name="cap<%= i.getId_indirizzo() %>" value="<%= i.getCap() %>">
	</label>
	<label>
	<input type="text" name="citta<%= i.getId_indirizzo() %>" value="<%= i.getCitta() %>">
	</label>
	<label>
	<input type="text" name="via<%= i.getId_indirizzo() %>" value="<%= i.getVia() %>">
	</label>
	<input type="hidden" name="id_indirizzo" value="<%= i.getId_indirizzo() %>">
	<button type="submit" name="action" value="modifyAddress">Modifica</button>
	<button type="submit" name="action" value="delete">Elimina</button><br>
	<% }} %>
</form>

<form action="${pageContext.request.contextPath}/profile" method="post">
	<label> Paese:
	<input type="text" name="paese0">
	</label>
	<label> Provincia:
	<input type="text" name="provincia0">
	</label>
	<label> CAP:
	<input type="text" name="cap0">
	</label>
	<label> Città:
	<input type="text" name="citta0">
	</label>
	<label> Via:
	<input type="text" name="via0">
	</label>
	<button onclick="addAddress()">+</button> <!-- da fare -->
	<button type="submit" name="action" value="insertAddress">Inserisci</button> <!-- da fare -->

</form>

<!-- aggiungi controllo javascript che i campi non siano vuoti prima di premere pulsante, se lo sono lo metti disabled -->

<% session.removeAttribute("indirizzi"); %>

</body>
</html>