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
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/addcart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect(request.getContextPath() + "/collection");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
	    int quantita = Integer.parseInt(request.getParameter("quantita"));
	    
	    HttpSession session = request.getSession();
	    
	    ArrayList<DettaglioOrdine> carrello = (ArrayList<DettaglioOrdine>) session.getAttribute("carrello");
	    if (carrello == null) {
	        carrello = new ArrayList<>();
	    }
	    
	    CappelloDAO cdao = new CappelloDAO();
	    Cappello c = cdao.getById(id);
	    
	    boolean presente = false;
	    for (DettaglioOrdine d : carrello) {
	        if (d.getCappello().getId_cappello() == id) {
	            d.setQuantita(d.getQuantita() + quantita);
	            presente = true;
	            break;
	        }
	    }
	    
	    if (!presente) {
	        DettaglioOrdine d = new DettaglioOrdine();
	        d.setCappello(c);
	        d.setQuantita(quantita);
	        d.setPrezzo_unitario(c.getPrezzo());
	        
	        carrello.add(d);
	    }
	    
	    session.setAttribute("carrello", carrello);
	    
	    response.sendRedirect(request.getContextPath() + "/collection");
		
		
	}

}
