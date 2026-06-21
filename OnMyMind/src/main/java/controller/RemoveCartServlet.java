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
 * Servlet implementation class RemoveCartServlet
 */
@WebServlet("/removecart")
public class RemoveCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCartServlet() {
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

		String idStr = request.getParameter("id");
		
		if(idStr != null && !idStr.isBlank()) {
			
			HttpSession session = request.getSession();
			
			@SuppressWarnings("unchecked")
			ArrayList<DettaglioOrdine> cart = (ArrayList<DettaglioOrdine>) session.getAttribute("carrello");
						
			int id = Integer.parseInt(idStr);
			
			cart.removeIf(d -> d.getCappello().getId_cappello() == id);
			
			session.setAttribute("carrello", cart);
			
		}
		
		response.sendRedirect(request.getContextPath() + "/removecart");
		
	}

}
