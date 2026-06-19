package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Indirizzo;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;

import com.mysql.cj.Session;

import dao.IndirizzoDAO;
import dao.UtenteDAO;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			IndirizzoDAO idao = new IndirizzoDAO();
			
			HttpSession session = request.getSession();
			
			Utente u = (Utente) session.getAttribute("utente");
			
			ArrayList<Indirizzo> listAddress = idao.getByUtente(u.getId_utente());
			
			request.setAttribute("indirizzi", listAddress);
						
			request.getRequestDispatcher("/WEB-INF/view/profile.jsp")
				.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		if(action != null) {
			
			switch(action) {
			case "insertAddress": doInsertAddress(request);
			break;
			case "modify": doModify(request);
			break;
			case "modifyAddress": doModifyAddress(request);
			break;
			case "delete": doDelete(request);
			default:;
			}
		}
		
		
		response.sendRedirect(request.getContextPath() + "/profile");
	}

	public void doInsertAddress(HttpServletRequest request) {
		
		int cont=0;
		
		HttpSession session = request.getSession();
		Utente u = (Utente) session.getAttribute("utente");
		ArrayList<Indirizzo> listaAddress = u.getIndirizzi();
		IndirizzoDAO idao = new IndirizzoDAO();
		
		String paese;
		String provincia;
		String cap;
		String citta;
		String via;
		
		boolean indirizzi = true;
		
		while(indirizzi){
						
			paese = request.getParameter("paese" + cont);
			provincia = request.getParameter("provincia" + cont);
			cap = request.getParameter("cap" + cont);
			citta = request.getParameter("citta" + cont);
			via = request.getParameter("via" + cont);
			
			if(paese == null || provincia == null || cap == null || citta == null || via == null ) {
				indirizzi=false;
			} else {
			
				Indirizzo indirizzo= new Indirizzo();	
				
				indirizzo.setPaese(paese);
				indirizzo.setProvincia(provincia);
				indirizzo.setCap(cap);
				indirizzo.setCitta(citta);
				indirizzo.setVia(via);
				indirizzo.setUtente(u);
				
				listaAddress.add(indirizzo);
				idao.insert(indirizzo);
				
				cont++;
			}
		}
	}
	
	public void doModify(HttpServletRequest request) {
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String telefono = request.getParameter("telefono");
		
		HttpSession session = request.getSession();
		Utente u = (Utente) session.getAttribute("utente");
				
		if(nome != null && !nome.isBlank()) {
			u.setNome(nome);
		}
		if(cognome != null && !cognome.isBlank()) {
			u.setCognome(cognome);
		}		
		if(email != null && !email.isBlank()) {
			u.setEmail(email);
		}
		if(password != null && !password.isBlank()) {
			u.setPassword(password);
		}
		if(telefono != null && !telefono.isBlank()) {
			u.setTelefono(telefono);
		}

		UtenteDAO user = new UtenteDAO();

		user.updateUtente(u);
		
	}
	
	
	public void doModifyAddress(HttpServletRequest request) {
		
		int id_indirizzo = Integer.parseInt(request.getParameter("id_indirizzo"));
		
		String paese = request.getParameter("paese" + id_indirizzo);
		String provincia = request.getParameter("provincia" + id_indirizzo); 
		String cap = request.getParameter("cap" + id_indirizzo); 
		String citta = request.getParameter("citta" + id_indirizzo); 
		String via = request.getParameter("via" + id_indirizzo);
		
		HttpSession session = request.getSession();
		
		Utente u = (Utente) session.getAttribute("utente");
		IndirizzoDAO idao = new IndirizzoDAO();
		Indirizzo i = idao.getById(id_indirizzo);
		
		if(paese != null && !paese.isBlank()) {
			i.setPaese(paese);
		}
		if(provincia != null && !provincia.isBlank()) {
			i.setProvincia(provincia);
		}
		if(cap != null && !cap.isBlank()) {
			i.setCap(cap);
		}
		if(citta != null && !citta.isBlank()) {
			i.setCitta(citta);
		}
		if(via != null && !via.isBlank()) {
			i.setVia(via);
		}
		idao.update(i);
		ArrayList<Indirizzo> listaAddress = idao.getByUtente(u.getId_utente()); 
		u.setIndirizzi(listaAddress);
		session.setAttribute("utente", u);
	}
	public void doDelete(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("id_indirizzo"));
		
		IndirizzoDAO idao = new IndirizzoDAO();

		idao.delete(id);
		
		HttpSession session = request.getSession();
	    Utente u = (Utente) session.getAttribute("utente");
		
		ArrayList<Indirizzo> listaAddress = idao.getByUtente(u.getId_utente()); 

		u.setIndirizzi(listaAddress);

		session.setAttribute("utente", u);
	}
	
	
	
	
	
	
	
	
	
	
}
