package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cappello;
import model.Recensione;
import model.Utente;

import java.io.IOException;

import dao.RecensioneDAO;

/**
 * Servlet implementation class AddReviewServlet
 */
@WebServlet("/addreview")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		
		response.sendRedirect(request.getContextPath() + "/openproduct?id=" + id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("id");
		
		String votoStr = request.getParameter("voto");
		
		String descrizione = request.getParameter("descrizione");

		
		if(idStr != null && !idStr.isBlank() && votoStr != null && !votoStr.isBlank() && descrizione != null && !descrizione.isBlank()) {
			
			int id = Integer.parseInt(idStr);
			int voto = Integer.parseInt(votoStr);
			
			HttpSession session = request.getSession();
			
			Utente u = (Utente) session.getAttribute("utente");
			Cappello c = new Cappello();
			c.setId_cappello(id);
			
			Recensione r = new Recensione();
			
			r.setCappello(c);
			r.setUtente(u);
			r.setVoto(voto);
			r.setCommento(descrizione);
			
			RecensioneDAO rdao = new RecensioneDAO();
			
			rdao.insert(r);
			
			response.sendRedirect(request.getContextPath() + "/addreview?id=" + idStr);
			
		} else {		
			response.sendRedirect(request.getContextPath() + "/collection");
		}
	}

}
