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

import dao.UtenteDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registration")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtenteDAO user = new UtenteDAO();
	
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Utente u = (Utente) session.getAttribute("utente");
		
		if(u == null) {
		
		request.getRequestDispatcher("/WEB-INF/view/registration.jsp")
			.forward(request, response);
	
		} else {
		
		response.sendRedirect(request.getContextPath() + "/loginpage");	
			
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		
		if( user.checkEmailExists(email)) {
			response.sendRedirect(request.getContextPath() + "/login");
		}else {
			doRegister(email, request, response);
		}
		
		
	}

	
	public void doRegister(String email, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String telefono = request.getParameter("telefono");
		String password = request.getParameter("password");

		Utente u = new Utente();
		
		u.setEmail(email);
		u.setPassword(password);
		u.setNome(nome);
		u.setCognome(cognome);
		u.setTelefono(telefono);
		u.setRuolo(Ruolo.UTENTE);
		
		user.insertUtente(u);
		u = user.getById(u.getId_utente());
				
		HttpSession session = request.getSession();
		
		session.setAttribute("utente", u);
		
		session.setAttribute("ruolo", Ruolo.UTENTE.getDbValue());
		
        response.sendRedirect(request.getContextPath() + "/home");
		
	}
	
}
