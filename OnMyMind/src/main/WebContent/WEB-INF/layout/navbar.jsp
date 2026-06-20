
<%@page import="model.Utente.Ruolo"%>
<%
String ruolo = (String) session.getAttribute("ruolo");
%>

<nav>
<ul>
<li id="logo"><img src="${pageContext.request.contextPath}/images/OnMyMindLogo.png" width=100></li>
<li id="home"><a href="${pageContext.request.contextPath}/home">HOME</a></li>
<li id="collezione"><a href="${pageContext.request.contextPath}/collection">COLLEZIONE</a></li>
<li id="cerca">CERCA</li>
<% if(ruolo == null){ %>
<li id="login"><a href="${pageContext.request.contextPath}/loginpage">LOGIN</a></li> 
<% } %>
<% if(ruolo != null){ %>
<li id="profile"><a href="${pageContext.request.contextPath}/profile">PROFILO</a></li> 
<% } %>
<% if(ruolo != null && ruolo.equals(Ruolo.ADMIN.getDbValue())){ %>
<li id="management"><a href="${pageContext.request.contextPath}/management">MANAGEMENT</a></li>
<% } %>
<li id="cart"><a href="${pageContext.request.contextPath}/cartpage">CARRELLO</a></li>
<% if(ruolo != null){ %>
<li id="orders"><a href="${pageContext.request.contextPath}/orders">ORDINI</a></li>
<% } %>
</ul>

</nav>
