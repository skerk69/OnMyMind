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
@SuppressWarnings("unchecked")
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

	
	<%
	@SuppressWarnings("unchecked")
	ArrayList<Indirizzo> listAddress = (ArrayList<Indirizzo>) request.getAttribute("indirizzi");
	if(!listAddress.isEmpty()){
	for(Indirizzo i : listAddress){
	 %>
	<form action="${pageContext.request.contextPath}/profile" method="post"> 
	<label>
	<input type="text" name="paese" value="<%= i.getPaese() %>">
	</label>
	<label>
	<input type="text" name="provincia" value="<%= i.getProvincia() %>">
	</label>
	<label>
	<input type="text" name="cap" value="<%= i.getCap() %>">
	</label>
	<label>
	<input type="text" name="citta" value="<%= i.getCitta() %>">
	</label>
	<label>
	<input type="text" name="via" value="<%= i.getVia() %>">
	</label>
	<input type="hidden" name="id_indirizzo" value="<%= i.getId_indirizzo() %>">
	<button type="submit" name="action" value="modifyAddress">Modifica</button>
	<button type="submit" name="action" value="delete">Elimina</button><br>
	</form>
	<% }} %>


<form action="${pageContext.request.contextPath}/profile" method="post">
	<label> Paese:
	<input type="text" name="paese">
	</label>
	<label> Provincia:
	<input type="text" name="provincia">
	</label>
	<label> CAP:
	<input type="text" name="cap">
	</label>
	<label> Città:
	<input type="text" name="citta">
	</label>
	<label> Via:
	<input type="text" name="via">
	</label>
	<button type="submit" name="action" value="insertAddress">Inserisci</button>

</form>
<br><br>
<!-- aggiungi controllo javascript che i campi non siano vuoti prima di premere pulsante, se lo sono lo metti disabled, e aggiungi pulsante modifica che apre il menù, come in management -->
<form action="${pageContext.request.contextPath}/logout" method="post">
<button type="submit">Logout</button>
</form>

</body>
</html>