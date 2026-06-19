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

import dao.UtenteDAO;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtenteDAO user = new UtenteDAO();

    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String action = request.getParameter("action");
			
			if(action != null) {
				
				switch(action) {
				case "insert": doInsert(request);
				break;
				case "modify": doModify(request);
				break;	
				default:;
				}
			}
			
			request.getRequestDispatcher("/WEB-INF/view/profile")
				.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@SuppressWarnings("null")
	public void doInsert(HttpServletRequest request) {
		
		int cont=0;
		
		HttpSession session = request.getSession();
		Utente u = (Utente) session.getAttribute("utente");
		ArrayList<Indirizzo> listAddress = u.getIndirizzi();
		
		String paese;
		String provincia;
		String cap;
		String citta;
		String via;
		Indirizzo indirizzo=null;
		
		boolean indirizzi = true;
		
		do{
			
			paese = request.getParameter("paese" + cont);
			provincia = request.getParameter("provincia" + cont);
			cap = request.getParameter("cap" + cont);
			citta = request.getParameter("citta" + cont);
			via = request.getParameter("via" + cont);
			
			indirizzo.setPaese(paese);
			indirizzo.setProvincia(provincia);
			indirizzo.setCap(cap);
			indirizzo.setCitta(citta);
			indirizzo.setVia(via);
			
			listAddress.add(indirizzo);
			
			cont++;
			
		}while(indirizzi);
		
	}
	
	public void doModify(HttpServletRequest request) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
