<%@page import="model.Indirizzo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/order" method="post">

<select name="id" id="sceltaIndirizzo">
<%
ArrayList<Indirizzo> listAddress = (ArrayList<Indirizzo>) request.getAttribute("indirizzi");
if(!listAddress.isEmpty()){
for(Indirizzo i : listAddress){
%>
<option value="<%= i.getId_indirizzo() %>"><%= "Via " + i.getVia() + ", " + i.getCitta() + ", " + i.getProvincia() + ", " + i.getPaese() %></option>
<% }} %>
<option value="0">Inserisci nuovo indirizzo...</option>
</select>

<span id="nuovo">
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
	<button type="submit">Inserisci</button>
</span>

<select>
<option>Carta di Credito</option>
<option>Paypal</option>
<option>Altro</option> <!-- fare finto form che cambia in base all'opzione selezionata -->
</select>

<button type="submit" id="orderButton">Procedi all'acquisto</button>
</form>

<script src="${pageContext.request.contextPath}/js/checkout_script.js" defer></script>

</body>
</html>