package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DettaglioOrdine;
import model.Indirizzo;
import model.Ordine;
import model.Ordine.StatoOrdine;
import model.Utente;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;

import dao.DettaglioOrdineDAO;
import dao.IndirizzoDAO;
import dao.OrdineDAO;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect(request.getContextPath() + "/checkout");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		IndirizzoDAO idao = new IndirizzoDAO();

		int id = Integer.parseInt(request.getParameter("id"));
		
		Indirizzo i = idao.getById(id);
		
		if(i == null) {
			addAddress(request);
			response.sendRedirect(request.getContextPath() + "/checkout");
		}else {
			doOrder(request);
			response.sendRedirect(request.getContextPath() + "/orders");
		}
		
	}

	
	public void addAddress(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Utente u = (Utente) session.getAttribute("utente");
				
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
				IndirizzoDAO idao = new IndirizzoDAO();

				indirizzo.setPaese(paese);
				indirizzo.setProvincia(provincia);
				indirizzo.setCap(cap);
				indirizzo.setCitta(citta);
				indirizzo.setVia(via);
				indirizzo.setUtente(u);
				
				idao.insert(indirizzo);
		}
	}
	
	public void doOrder(HttpServletRequest request) {

		HttpSession session = request.getSession();
		
		Utente u = (Utente) session.getAttribute("utente");
		ArrayList<DettaglioOrdine> cart = (ArrayList<DettaglioOrdine>) session.getAttribute("carrello");

		Ordine o = new Ordine();
		o.setStato_ordine(StatoOrdine.IN_ATTESA);
		o.setDettagliordini(cart);
		o.setUtente(u);
		
		DettaglioOrdineDAO ddao = new DettaglioOrdineDAO();
		
		double tot=0;
		for(DettaglioOrdine d : cart) {
			tot+= d.getPrezzo_unitario()*d.getQuantita();
		}
		o.setTotale(tot);
		o.setStato_ordine(StatoOrdine.PAGATO);

		OrdineDAO odao = new OrdineDAO();
		o.setStato_ordine(StatoOrdine.CONSEGNATO);
		o.setId_ordine(odao.insert(o));
		
		for(DettaglioOrdine d : cart) {
			d.setId_ordine(o.getId_ordine());
			d.setOrdine(o);
			ddao.insert(d);
		}
		
		cart = new ArrayList<DettaglioOrdine>();
		
		session.setAttribute("carrello", cart);
		
	}
	
	
	
}
