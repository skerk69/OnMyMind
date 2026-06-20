package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DettaglioOrdine;
import model.Indirizzo;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;

import dao.IndirizzoDAO;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ArrayList<DettaglioOrdine> cart = (ArrayList<DettaglioOrdine>) session.getAttribute("carrello");
		Utente u = (Utente) session.getAttribute("utente");
		
		if(u == null || cart.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/home");
		}else {
			
			IndirizzoDAO idao = new IndirizzoDAO();
			
			ArrayList<Indirizzo> i = idao.getByUtente(u.getId_utente());
			
			request.setAttribute("indirizzi", i);
			
			request.getRequestDispatcher("/WEB-INF/view/checkout.jsp")
				.forward(request, response);
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Utente u = (Utente) session.getAttribute("utente");
		ArrayList<Indirizzo> listaAddress = u.getIndirizzi();
		IndirizzoDAO idao = new IndirizzoDAO();
		
		String paese;
		String provincia;
		String cap;
		String citta;
		String via;
										
		paese = request.getParameter("paese");
		provincia = request.getParameter("provincia");
		cap = request.getParameter("cap");
		citta = request.getParameter("citta");
		via = request.getParameter("via");
			
		if(paese != null && !paese.isBlank() && provincia != null && !provincia.isBlank() && cap != null && !cap.isBlank() && citta != null && !citta.isBlank() && via != null && !via.isBlank() ) {
			
				Indirizzo indirizzo= new Indirizzo();	
				
				indirizzo.setPaese(paese);
				indirizzo.setProvincia(provincia);
				indirizzo.setCap(cap);
				indirizzo.setCitta(citta);
				indirizzo.setVia(via);
				indirizzo.setUtente(u);
				
				listaAddress.add(indirizzo);
				idao.insert(indirizzo);
		}
		
		doGet(request, response);
	}

}
