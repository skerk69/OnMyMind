<%@page import="model.Utente.Ruolo"%>
<%
String ruolo = (String) session.getAttribute("ruolo");
%>

<link rel="stylesheet" href="css/style.css">

<nav>
    <ul>
        <li id="logo">
            <a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/images/OnMyMindLogo.png" width="100" alt="Logo"></a>
        </li>
        
        <li id="home"><a href="${pageContext.request.contextPath}/home">HOME</a></li>
        <li id="collezione"><a href="${pageContext.request.contextPath}/collection">COLLEZIONE</a></li>
        
		<li id="cart">
    		<a href="${pageContext.request.contextPath}/cartpage" aria-label="Carrello">
        		<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="nav-icon">
            		<circle cx="9" cy="21" r="1"></circle>
            		<circle cx="20" cy="21" r="1"></circle>
            		<path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
        		</svg>
    		</a>
		</li>
        
        <li class="dropdown" id="profile-dropdown">
            <button class="dropdown-toggle" id="btn-profilo" aria-label="Menu utente">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="nav-icon">
                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                    <circle cx="12" cy="7" r="4"></circle>
                </svg>
            </button>
            
            <div class="dropdown-menu" id="menu-profilo">
                <% if(ruolo == null) { %>
                    <a href="${pageContext.request.contextPath}/loginpage">Login</a>
                <% } else { %>
                    <a href="${pageContext.request.contextPath}/profile">Il mio Profilo</a>
                    <a href="${pageContext.request.contextPath}/orders">Ordini</a>
                    
                    <% if(ruolo.equals(Ruolo.ADMIN.getDbValue())) { %>
                        <a href="${pageContext.request.contextPath}/management" class="admin-link">Management</a>
                    <% } %>
                    
                    <div class="dropdown-divider"></div>
                    <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
                <% } %>
            </div>
        </li>
    </ul>
</nav>

<script>
document.addEventListener("DOMContentLoaded", function() {
    const btnProfilo = document.getElementById("btn-profilo");
    const menuProfilo = document.getElementById("menu-profilo");

    if (btnProfilo && menuProfilo) {
        btnProfilo.addEventListener("click", function(e) {
            e.stopPropagation();
            menuProfilo.classList.toggle("show");
        });

        document.addEventListener("click", function(e) {
            if (!menuProfilo.contains(e.target) && e.target !== btnProfilo) {
                menuProfilo.classList.remove("show");
            }
        });
    }
});
</script>