package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cappello;
import model.DettaglioOrdine;

import java.io.IOException;
import java.util.ArrayList;

import dao.CappelloDAO;

/**
 * Servlet implementation class CartPageServlet
 */
@WebServlet("/cartpage")
public class CartPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ArrayList<DettaglioOrdine> dolist = (ArrayList<DettaglioOrdine>) session.getAttribute("carrello"); 

		ArrayList<Cappello> cap = new ArrayList<Cappello>();
		
		CappelloDAO cdao = new CappelloDAO();
		
		if(dolist!= null && !dolist.isEmpty()) {
			for(DettaglioOrdine d : dolist){
				Cappello c = cdao.getById(d.getId_cappello());
				
				cap.add(c);
			}
		}
		
		request.setAttribute("cappelli", cap);
		
		request.getRequestDispatcher("/WEB-INF/view/cart.jsp")
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
