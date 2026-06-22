package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DettaglioOrdine;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet("/updatecart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect(request.getContextPath() + "/cartpage");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String quantitaStr = request.getParameter("quantita");
		String idStr = request.getParameter("id");
		
		if(quantitaStr != null && !quantitaStr.isBlank() && idStr != null && !idStr.isBlank()) {
			int quantita = Integer.parseInt(quantitaStr);
			int id = Integer.parseInt(idStr);
			
			HttpSession session = request.getSession();
			
			@SuppressWarnings("unchecked")
			ArrayList<DettaglioOrdine> cart = (ArrayList<DettaglioOrdine>) session.getAttribute("carrello");
			
			for(DettaglioOrdine d : cart) {
				if(d.getId_cappello() == id) {
					d.setQuantita(quantita);
					break;
				}
			}
			
			session.setAttribute("carrello", cart);
			
			response.sendRedirect(request.getContextPath() + "/cartpage");
			
		} else {
			doGet(request, response);
		}
		
		
	}

}
