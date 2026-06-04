<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Management Page</title>
</head>
<body>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Cappello" %>
<%@ page import="model.Categoria" %>
<h2>Aggiungi Cappello</h2>

<form action="${pageContext.request.contextPath}/product" method="post" enctype="multipart/form-data">
    Categoria: <select name="categoria">
    
    <%
    ArrayList<Categoria> listCat = (ArrayList<Categoria>) request.getAttribute("categorie");
    for(Categoria cat: listCat)
    {
    %>
    <option value=<%= cat.getId_categoria() %>><%= cat.getNomeCategoria() %></option>
    <%
    }
    %>
    <option value="0"> Nuova </option>
    
    </select><br>
    <!-- far apparire e scomparire i due input sotto se la select è su "Nuova" -->
    <input type="text" name="nomeCategoria" id="nomeCategoria" placeholder="Inserisci nome categoria"><br>
    <input type="text" name="descCategoria" id="descCategoria" placeholder="Inserisci descrizione categoria"><br>
    
    Nome: <input type="text" name="nome"><br>
    Descrizione: <input type="text" name="descrizione"><br>
    Prezzo: <input type="text" name="prezzo"><br>
    Taglia: <input type="text" name="taglia"><br>
    Colore: <input type="text" name="colore"><br>
    Materiale: <input type="text" name="materiale"><br>
    Quantità: <input type="text" name="quantita"><br>
    Immagine: <input type="file" name="immagine"><br>

    <input type="hidden" name="action" value="insert">

    <button type="submit">Aggiungi</button>
</form>

<h2>Lista Categorie</h2>

<%
for (Categoria c : listCat) {
%>

    <p>
        <%= c.getNomeCategoria() %><br>
        <%= c.getDescrizione() %>

        <form action="${pageContext.request.contextPath}/category" method="post">
            <input type="hidden" name="id" value="<%= c.getId_categoria() %>">
            <button type="submit" name="action" onclick=showModify()>Modifica</button>
            <!-- fare funzione showModify() con javascript e inserire button con invia e value="modify" -->
            <button type="submit" name="action" value="delete">Elimina</button>
        </form>
   

<%
}
%>

<h2>Lista Cappelli</h2>

<%
ArrayList<Cappello> listCap = (ArrayList<Cappello>) request.getAttribute("cappelli");

for (Cappello c : listCap) {
%>

    <p>
        <%= c.getNome() %> - <%= c.getPrezzo() + "$" %>

        <form action="${pageContext.request.contextPath}/product" method="post">
            <input type="hidden" name="id" value="<%= c.getId_cappello() %>">
                Nome: <input type="text" name="nome<%= c.getId_cappello() %>"><br>
 			    Descrizione: <input type="text" name="descrizione<%= c.getId_cappello() %>"><br>
 			    Prezzo: <input type="text" name="prezzo<%= c.getId_cappello() %>"><br>
    			Taglia: <input type="text" name="taglia<%= c.getId_cappello() %>"><br>
    			Colore: <input type="text" name="colore<%= c.getId_cappello() %>"><br>
    			Materiale: <input type="text" name="materiale<%= c.getId_cappello() %>"><br>
    			Quantità: <input type="text" name="quantita<%= c.getId_cappello() %>"><br>
    			Immagine: <input type="file" name="immagine<%= c.getId_cappello() %>"><br>
            <button type="submit" name="action" onclick=showModify()>Modifica</button>
            <!-- fare funzione showModify() con javascript e inserire button con invia e value="modify" -->
            <button type="submit" name="action" value="delete">Elimina</button>
        </form>
   

<%
}
%>


</body>
</html>