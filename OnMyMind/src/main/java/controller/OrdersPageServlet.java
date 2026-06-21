package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Ordine;
import model.Utente;
import model.Utente.Ruolo;

import java.io.IOException;
import java.util.ArrayList;

import dao.DettaglioOrdineDAO;
import dao.OrdineDAO;

/**
 * Servlet implementation class OrdersPageServlet
 */
@WebServlet("/orders")
public class OrdersPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utente u = (Utente) session.getAttribute("utente");
		OrdineDAO odao = new OrdineDAO();
		DettaglioOrdineDAO ddao = new DettaglioOrdineDAO();
		ArrayList<Ordine> olist;
		
		if(u == null) {
			response.sendRedirect(request.getContextPath() + "/home");
		}
		
		if(u.getRuolo() == Ruolo.ADMIN) {
			olist = odao.getAll();
		} else {
			olist = odao.getByUtente(u.getId_utente());
		}
		
		
		for(Ordine o : olist) {
			o.setDettagliordini(ddao.getByOrdine(o.getId_ordine()));
		}
		
		request.setAttribute("ordini", olist);
		
		request.getRequestDispatcher("/WEB-INF/view/orders.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
