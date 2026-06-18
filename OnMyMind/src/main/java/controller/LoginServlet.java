package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utente;
import model.Utente.Ruolo;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.mysql.cj.Session;

import dao.UtenteDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtenteDAO user = new UtenteDAO();
	
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		switch(action) {
		case "login": {
			doLogin(email, password, request, response);
		}
		break;
		case "register": {
			
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			
			request.getRequestDispatcher("/WEB-INF/view/registration.jsp")
					.forward(request, response);
		}
		break;
		default: request.getRequestDispatcher("/WEB-INF/view/login.jsp")
        			.forward(request, response);
		}
		
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	public void doLogin(String email, String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Ruolo ruolo;

		Utente u = user.login(email, password);	
		
		if(u == null) {
			ruolo = null;
		}else {
			ruolo = u.getRuolo();
		}
		
		HttpSession session = request.getSession();

		if(ruolo.equals(Ruolo.ADMIN)) {
			
			session.setAttribute("ruolo", ruolo);
			
	        request.getRequestDispatcher("/WEB-INF/admin/management.jsp")
	        .forward(request, response);
	        
		}else if(ruolo.equals(Ruolo.UTENTE)) {
			
			session.setAttribute("ruolo", ruolo);
			
	        request.getRequestDispatcher("/WEB-INF/view/index.jsp")
	        .forward(request, response);
		}else {
	        request.getRequestDispatcher("/WEB-INF/view/login.jsp")
	        .forward(request, response);
		}
	}
	

	
	
}












