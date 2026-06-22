<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Cappello" %>
<%@ page import="model.Categoria" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>On My Mind - Dashboard Management</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/management.css">
</head>
<body>
<jsp:include page="/WEB-INF/layout/navbar.jsp"/>

<main class="management-container">
    <h1 class="page-title">Pannello di Gestione</h1>

    <div class="creation-grid">
        <section class="management-card">
            <h2 class="section-title">Aggiungi Nuovo Cappello</h2>
            <form action="${pageContext.request.contextPath}/product" method="post" enctype="multipart/form-data" class="admin-form">
                <div class="input-group">
                    <label>Categoria</label>
                    <select name="id_categoria" required>
                        <%
                        @SuppressWarnings("unchecked")
                        ArrayList<Categoria> listCat = (ArrayList<Categoria>) request.getAttribute("categorie");
                        if(listCat != null && !listCat.isEmpty()){
                            for(Categoria cat: listCat) {
                        %>
                                <option value="<%= cat.getId_categoria() %>"><%= cat.getNomeCategoria() %></option>
                        <%
                            }
                        }
                        %>
                    </select>
                </div>
                
                <div class="input-group">
                    <label>Nome Prodotto</label>
                    <input type="text" name="nome" required placeholder="Es. Snapback Classic">
                </div>

                <div class="form-row-grid">
                    <div class="input-group">
                        <label>Prezzo ($)</label>
                        <input type="text" name="prezzo" required placeholder="0.00">
                    </div>
                    <div class="input-group">
                        <label>Quantità</label>
                        <input type="text" name="quantita" required placeholder="10">
                    </div>
                </div>

                <div class="form-row-grid-three">
                    <div class="input-group">
                        <label>Taglia</label>
                        <select name="taglia" required>
                            <option value="Unica" selected>Unica</option>
                            <option value="S">S</option>
                            <option value="M">M</option>
                            <option value="L">L</option>
                            <option value="XL">XL</option>
                        </select>
                    </div>
                    <div class="input-group">
                        <label>Colore</label>
                        <input type="text" name="colore" required placeholder="Nero">
                    </div>
                    <div class="input-group">
                        <label>Materiale</label>
                        <input type="text" name="materiale" required placeholder="Cotone">
                    </div>
                </div>

                <div class="input-group">
                    <label>Immagine Prodotto</label>
                    <div class="file-upload-wrapper">
                        <input type="file" name="immagine" required>
                    </div>
                </div>

                <div class="input-group">
                    <label>Descrizione</label>
                    <textarea name="descrizione" rows="3" placeholder="Inserisci i dettagli del cappello..."></textarea>
                </div>
             
                <button type="submit" name="action" value="insert" class="btn-primary">Aggiungi Prodotto</button>
            </form>
        </section>

        <section class="management-card">
            <h2 class="section-title">Aggiungi Nuova Categoria</h2>
            <form action="${pageContext.request.contextPath}/category" method="post" class="admin-form">
                <div class="input-group">
                    <label>Nome Categoria</label>
                    <input type="text" name="nuovo_nome_categoria" required placeholder="Es. Berretti Invernali">
                </div>
                <div class="input-group">
                    <label>Descrizione Categoria</label>
                    <textarea name="nuova_descrizione_categoria" rows="4" placeholder="Descrivi la tipologia di prodotti..."></textarea>
                </div>
                <button type="submit" name="action" value="insert" class="btn-primary">Aggiungi Categoria</button>
            </form>
        </section>
    </div>

    <section class="list-section-wrapper">
        <h2 class="list-section-title">Lista delle Categorie</h2>
        <div class="items-management-list">
            <%
            if(listCat != null && !listCat.isEmpty()){
                for (Categoria c : listCat) {
            %>
                <div class="management-item-row">
                    <div class="item-static-info">
                        <h3><%= c.getNomeCategoria() %></h3>
                        <p><%= c.getDescrizione() %></p>
                    </div>

                    <form action="${pageContext.request.contextPath}/category" method="post" class="inline-edit-form">
                        <div class="form-row-grid class-toggle-group">
                            <div class="input-group classeCategoria">
                                <label class="classeCategoria">Nome Categoria</label>
                                <input type="text" name="nome_categoria<%= c.getId_categoria() %>" class="classeCategoria" value="<%= c.getNomeCategoria() %>">
                            </div>
                            <div class="input-group classeCategoria">
                                <label class="classeCategoria">Descrizione Categoria</label>
                                <textarea name="descrizione_categoria<%= c.getId_categoria() %>" class="classeCategoria" rows="2"><%= c.getDescrizione() %></textarea>
                            </div>
                        </div>
                        <input type="hidden" name="id_categoria" value="<%= c.getId_categoria() %>">
                        <div class="management-actions">
                            <button type="button" class="modificaCategoria btn-secondary" onclick="showModifyCategoria(this)">Modifica</button>
                            <button type="submit" name="action" value="modify" class="confermaCategoria btn-success">Conferma Modifiche</button>
                            <button type="submit" name="action" value="delete" class="btn-danger">Elimina</button>
                        </div>
                    </form>
                </div>
            <%
                }
            }
            %>
        </div>
    </section>

    <section class="list-section-wrapper">
        <h2 class="list-section-title">Lista dei Cappelli in Catalogo</h2>
        <div class="items-management-list">
            <%
            @SuppressWarnings("unchecked")
            ArrayList<Cappello> listCap = (ArrayList<Cappello>) request.getAttribute("cappelli");
            if(listCap != null && !listCap.isEmpty()){
                for (Cappello c : listCap) {
            %>
                <div class="management-product-card">
                    <div class="product-preview-zone">
                        <div class="img-container">
                            <img src="${pageContext.request.contextPath}/images/<%= c.getImmagine() %>" alt="<%= c.getNome() %>">
                        </div>
                        <div class="product-specs-summary">
                            <h3><%= c.getNome() %> <span class="prezzo-badge"><span class="prezzo"><%= c.getPrezzo()%></span>$</span></h3>
                            <div class="meta-tags">
                                <% 
                                Categoria ct = c.getCategoria();
                                if(!listCat.isEmpty()){
                                    for (Categoria cat : listCat) {  
                                        if(cat.getId_categoria() == ct.getId_categoria()){
                                %>
                                            <span class="tag">Cat: <%= cat.getNomeCategoria() %></span>
                                <% 
                                        }
                                    }
                                } 
                                %>
                                <span class="tag">Taglia: <%= c.getTaglia() %></span>
                                <span class="tag">Colore: <%= c.getColore() %></span>
                                <span class="tag">Stock: <%= c.getQuantitaMagazzino() %> pz</span>
                            </div>
                            <p class="desc-text"><%= c.getDescrizione() %></p>
                        </div>
                    </div>

                    <form action="${pageContext.request.contextPath}/product" method="post" enctype="multipart/form-data" class="product-edit-form">
                        <input type="hidden" name="id" value="<%= c.getId_cappello() %>">
                        
                        <div class="form-complex-grid class-toggle-group">
                            <div class="input-group classeCappello">
                                <label class="classeCappello">Categoria di riferimento</label>
                                <select name="id_categoria<%= c.getId_cappello() %>" class="classeCappello">
                                    <%
                                    if(!listCat.isEmpty()){
                                        for(Categoria cat: listCat) {
                                    %>
                                            <option value="<%= cat.getId_categoria() %>" <%= (cat.getId_categoria() == ct.getId_categoria()) ? "selected" : "" %>><%= cat.getNomeCategoria() %></option>
                                    <%
                                        }
                                    }
                                    %>
                                </select>
                            </div>

                            <div class="input-group classeCappello">
                                <label class="classeCappello">Nome Prodotto</label>
                                <input type="text" name="nome<%= c.getId_cappello() %>" class="classeCappello" value="<%= c.getNome() %>">
                            </div>

                            <div class="form-row-grid classeCappello">
                                <div class="input-group classeCappello">
                                    <label class="classeCappello">Prezzo ($)</label>
                                    <input type="text" name="prezzo<%= c.getId_cappello() %>" class="classeCappello" value="<%= c.getPrezzo() %>">
                                </div>
                                <div class="input-group classeCappello">
                                    <label class="classeCappello">Quantità Magazzino</label>
                                    <input type="text" name="quantita<%= c.getId_cappello() %>" class="classeCappello" value="<%= c.getQuantitaMagazzino() %>">
                                </div>
                            </div>

                            <div class="form-row-grid-three classeCappello">
                                <div class="input-group classeCappello">
                                    <label class="classeCappello">Taglia</label>
                                    <select name="taglia<%= c.getId_cappello() %>" class="classeCappello">
                                        <option value="Unica" <%= "Unica".equals(c.getTaglia()) ? "selected" : "" %>>Unica</option>
                                        <option value="S" <%= "S".equals(c.getTaglia()) ? "selected" : "" %>>S</option>
                                        <option value="M" <%= "M".equals(c.getTaglia()) ? "selected" : "" %>>M</option>
                                        <option value="L" <%= "L".equals(c.getTaglia()) ? "selected" : "" %>>L</option>
                                        <option value="XL" <%= "XL".equals(c.getTaglia()) ? "selected" : "" %>>XL</option>
                                    </select>
                                </div>
                                <div class="input-group classeCappello">
                                    <label class="classeCappello">Colore</label>
                                    <input type="text" name="colore<%= c.getId_cappello() %>" class="classeCappello" value="<%= c.getColore() %>">
                                </div>
                                <div class="input-group classeCappello">
                                    <label class="classeCappello">Materiale</label>
                                    <input type="text" name="materiale<%= c.getId_cappello() %>" class="classeCappello" value="<%= c.getMateriale() %>">
                                </div>
                            </div>

                            <div class="input-group classeCappello">
                                <label class="classeCappello">Sostituisci Immagine</label>
                                <input type="file" name="immagine<%= c.getId_cappello() %>" class="classeCappello">
                            </div>

                            <div class="input-group classeCappello">
                                <label class="classeCappello">Descrizione</label>
                                <textarea name="descrizione<%= c.getId_cappello() %>" class="classeCappello" rows="2"><%= c.getDescrizione() %></textarea>
                            </div>
                        </div>

                        <div class="management-actions product-buttons">
                            <button type="button" class="modificaCappello btn-secondary" onclick="showModifyCappello(this)">Modifica</button>
                            <button type="submit" name="action" class="confermaCappello btn-success" value="modify">Conferma Modifiche</button>
                            <button type="submit" name="action" value="delete" class="btn-danger">Elimina</button>
                        </div>
                    </form>
                </div>
            <%
                }
            }
            %>
        </div>
    </section>
</main>

<script src="${pageContext.request.contextPath}/js/management_script.js" defer></script>
<script src="${pageContext.request.contextPath}/js/priceformat_script.js" defer></script>
</body>
</html>