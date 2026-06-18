<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>Management Page</title>
</head>
<body>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Cappello" %>
<%@ page import="model.Categoria" %>
<h2>Aggiungi Cappello</h2>

<form action="${pageContext.request.contextPath}/product" method="post" enctype="multipart/form-data">
    Categoria: <select name="id_categoria">
    
    <%
    ArrayList<Categoria> listCat = (ArrayList<Categoria>) request.getAttribute("categorie");
    if(!listCat.isEmpty()){
    for(Categoria cat: listCat)
    {
    %>
    <option value=<%= cat.getId_categoria() %>><%= cat.getNomeCategoria() %></option>
    <%
    }}
    %>
    
    </select><br>
    <label>Nome:
    <input type="text" name="nome"><br>
    </label>
    <label>Prezzo:
    <input type="text" name="prezzo"><br>
    </label>
    <label>Taglia:
    <input type="text" name="taglia"><br>
    </label>
    <label>Colore:
    <input type="text" name="colore"><br>
    </label>
    <label>Materiale:
    <input type="text" name="materiale"><br>
    </label>
    <label>Quantità:
    <input type="text" name="quantita"><br>
    </label>
    <label>Immagine:
    <input type="file" name="immagine"><br>
	</label>
	<label>Descrizione:
    <textarea name="descrizione"></textarea><br>
    </label>
 
    <button type="submit" name="action" value="insert">Aggiungi</button>
</form>

<h2>Aggiungi Categoria</h2>

	<form action="${pageContext.request.contextPath}/category" method="post">

 		<label> Nome Categoria:
 		<input type="text" name="nuovo_nome_categoria"><br>
  		</label>
  		<label> Descrizione:
  		<textarea name="nuova_descrizione_categoria"></textarea><br>
   		</label>
		<button type="submit" name="action" value="insert">Aggiungi</button>
		

	</form>

<h2>Lista Categorie</h2>

<%
if(!listCat.isEmpty()){
for (Categoria c : listCat) {
%>

    <p>
        <%= c.getNomeCategoria() %><br>
        <%= c.getDescrizione() %>

        <form action="${pageContext.request.contextPath}/category" method="post">
        
            <label class="classeCategoria"> Nome Categoria:
            <input type="text" name="nome_categoria<%= c.getId_categoria() %>" class="classeCategoria"><br class="classeCategoria">
			</label>
			<label class="classeCategoria"> Descrizione Categoria:
			<textarea name="descrizione_categoria<%= c.getId_categoria() %>" class="classeCategoria"></textarea><br class="classeCategoria">
        	</label>
            <input type="hidden" name="id_categoria" value="<%= c.getId_categoria() %>">
            <button type="button" class="modificaCategoria" onclick="showModifyCategoria(this)">Modifica</button>
            <button type="submit" name="action" value="modify" class="confermaCategoria">Conferma Modifiche</button>
            <button type="submit" name="action" value="delete">Elimina</button>
        </form>
   

<%
}}
%>

<h2>Lista Cappelli</h2>

<%
ArrayList<Cappello> listCap = (ArrayList<Cappello>) request.getAttribute("cappelli");
if(!listCap.isEmpty()){
for (Cappello c : listCap) {
%>

    <p>

    	<img src="${pageContext.request.contextPath}/images/<%= c.getImmagine() %>" width=100><br>
        <%= c.getNome() %> - <%= c.getPrezzo() + "$" %><br>
    	<%  Categoria ct = c.getCategoria();
    	if(!listCat.isEmpty()){
    		for (Categoria cat : listCat) {  
    			if(cat.getId_categoria() == ct.getId_categoria()){
    			%>
    				Categoria: <%= cat.getNomeCategoria() %><br>
    	<% }}} %>
    	<%= c.getTaglia() %>, <%= c.getColore() %>, <%= c.getMateriale() %>, <%= c.getQuantitaMagazzino() %> <br>
        <%= c.getDescrizione() %>

        <form action="${pageContext.request.contextPath}/product" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<%= c.getId_cappello() %>">
            
            <select name="id_categoria<%= c.getId_cappello() %>" class="classeCappello">
            	<%
   				if(!listCat.isEmpty()){
   				for(Categoria cat: listCat)
   				{
   				%>
   				<option value="<%= cat.getId_categoria() %>"><%= cat.getNomeCategoria() %></option>
   				<%
   				}}
    			%>
            </select><br>
                <label class="classeCappello"> Nome:
                <input type="text" name="nome<%= c.getId_cappello() %>" class="classeCappello"><br class="classeCappello">
                </label>	    
 			    <label class="classeCappello"> Prezzo:
 			    <input type="text" name="prezzo<%= c.getId_cappello() %>" class="classeCappello"><br class="classeCappello">
    			</label>
    			<label class="classeCappello"> Taglia:
    			<input type="text" name="taglia<%= c.getId_cappello() %>" class="classeCappello"><br class="classeCappello">
    			 </label>
    			<label class="classeCappello"> Colore:
    			<input type="text" name="colore<%= c.getId_cappello() %>" class="classeCappello"><br class="classeCappello">
    			 </label>
    			<label class="classeCappello"> Materiale:
    			<input type="text" name="materiale<%= c.getId_cappello() %>" class="classeCappello"><br class="classeCappello">
    			 </label>
    			<label class="classeCappello"> Quantità:
    			<input type="text" name="quantita<%= c.getId_cappello() %>" class="classeCappello"><br class="classeCappello">
    			</label>
    			<label class="classeCappello"> Immagine:
    			<input type="file" name="immagine<%= c.getId_cappello() %>" class="classeCappello"><br class="classeCappello">
             	</label>
             	<label class="classeCappello"> Descrizione:
				<textarea  name="descrizione<%= c.getId_cappello() %>" class="classeCappello"></textarea><br class="classeCappello">
				</label>
            <button type="button" class="modificaCappello" onclick="showModifyCappello(this)">Modifica</button>
             <button type="submit" name="action" class="confermaCappello" value="modify">Conferma Modifiche</button>
            <button type="submit" name="action" value="delete">Elimina</button>
        </form>
   

<%
}}
%>
<script src="${pageContext.request.contextPath}/js/management_script.js" defer></script>

</body>
</html>