<nav>
<ul>
<li id="logo"><img src="${pageContext.request.contextPath}/images/OnMyMindLogo.png" width=100></li>
<li id="home"><a href="${pageContext.request.contextPath}/home">HOME</a></li>
<li id="collezione">COLLEZIONE</li>
<li id="cerca">CERCA</li>
<li id="login"><a href="${pageContext.request.contextPath}/loginpage">LOGIN</a></li> <!-- accessibile solo se non loggato(ruolo==null), da fare con javascript -->
<li id="profile"><a href="${pageContext.request.contextPath}/profile">PROFILO</a></li> <!-- accessibile solo se loggato(ruolo!=null), da fare con javascript -->
<li id="management"><a href="${pageContext.request.contextPath}/management">MANAGEMENT</a></li> <!-- accessiibile solo se admin(ruolo!=null && ruolo = Ruolo.ADMIN) -->
</ul>
</nav>


<script></script>