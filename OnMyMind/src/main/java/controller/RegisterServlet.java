package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String telefono = request.getParameter("telefono");
		
		doRegister(email, password, nome, cognome, telefono, request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	public void doRegister(String email, String password, String nome, String cognome, String telefono, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente u = user.login(email, password);
		
		if(u!=null) {
			request.getRequestDispatcher("/controller/login")
				.forward(request, response);
		}
		
		u = new Utente();
		
		u.setEmail(email);
		u.setPassword(hashPassword(password));
		u.setNome(nome);
		u.setCognome(cognome);
		u.setTelefono(telefono);
		u.setRuolo(Ruolo.UTENTE);
		
		user.insertUtente(u);
		
		
	}
	
	public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            byte[] hashBytes = digest.digest(password.getBytes());
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Errore: Algoritmo di hashing non trovato", e);
        }
    }
	
}
