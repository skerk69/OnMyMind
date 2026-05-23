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

<form action="CappelloServlet" method="post" enctype="multipart/form-data">
    Categoria: <select name=id_categoria>
    
    <%
    ArrayList<Categoria> listCat = (ArrayList<Categoria>) request.getAttribute("categorie");
    for(Categoria cat: listCat)
    {
    %>
    <option><%= cat.getNomeCategoria() %></option>
    <%
    }
    %>
    </select><br>
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

<hr>

<h2>Lista Cappelli</h2>

<%
ArrayList<Cappello> listCap = (ArrayList<Cappello>) request.getAttribute("cappelli");

for (Cappello c : listCap) {
%>

    <p>
        <%= c.getNome() %> - <%= c.getPrezzo() %>

        <form action="CappelloServlet" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= c.getId_cappello() %>">
            <button type="submit" value="modify">Modifica</button>
            <button type="submit" value="delete">Elimina</button>
        </form>
   

<%
}
%>


</body>
</html>