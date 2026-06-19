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
		
		request.getRequestDispatcher("/loginpage")
		.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			
			request.getRequestDispatcher("/registration")
					.forward(request, response);
		}
		break;
		default: { 
			request.getRequestDispatcher("/loginpage")
        			.forward(request, response);
		}
		}
	}

	
	public void doLogin(String email, String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Ruolo ruolo;

		Utente u = user.login(email, password);	
		
		if(u == null) {
			ruolo = null;
		}else {
			ruolo = u.getRuolo();
		}
		
		if(ruolo != null) {
		
			HttpSession session = request.getSession();

			session.setAttribute("ruolo", ruolo);
			
			u = user.getById(u.getId_utente());
			
			session.setAttribute("utente", u);
			
			if(ruolo.equals(Ruolo.ADMIN)) {
				request.getRequestDispatcher("/management")
				.forward(request, response);
				return;
			}else if(ruolo.equals(Ruolo.UTENTE)) {
				request.getRequestDispatcher("/home")
				.forward(request, response);
				return;
				}
			}
		
	        request.getRequestDispatcher("/loginpage")
	        .forward(request, response);
	}
	

	
	
}












