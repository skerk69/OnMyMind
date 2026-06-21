package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cappello;
import model.Categoria;
import model.Recensione;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;

import dao.CappelloDAO;
import dao.CategoriaDAO;
import dao.OrdineDAO;
import dao.RecensioneDAO;
import dao.UtenteDAO;

/**
 * Servlet implementation class OpenProductServlet
 */
@WebServlet("/openproduct")
public class OpenProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idStr = request.getParameter("id");
		if(idStr != null && !idStr.isBlank()) {
			
			int id = Integer.parseInt(idStr);
			
			CappelloDAO capdao = new CappelloDAO();
			CategoriaDAO catdao = new CategoriaDAO();
			RecensioneDAO rdao = new RecensioneDAO();
			UtenteDAO udao = new UtenteDAO();
			
			Cappello cap = capdao.getById(id);
			Categoria cat = catdao.getById(cap.getCategoria().getId_categoria());
			ArrayList<Recensione> rec = rdao.getByCappello(id);
			
			for(Recensione r : rec) {
				r.setUtente(udao.getById(r.getUtente().getId_utente()));
			}
			
			OrdineDAO odao = new OrdineDAO();
			
			HttpSession session = request.getSession();			
			Utente u = (Utente) session.getAttribute("utente");
			boolean comprato;
			if(u != null) {
				comprato = odao.hasBought(u.getId_utente(), id);
			}else {
				comprato = false;
			}
			
			boolean recensito;
			if(u != null) {
				recensito = rdao.hasReviewed(u.getId_utente(), id);
			} else {
				recensito = false;
			}
			
			request.setAttribute("comprato", comprato);
			request.setAttribute("recensito", recensito);
			request.setAttribute("cappello", cap);
			request.setAttribute("categoria", cat);
			request.setAttribute("recensioni", rec);
			 
			request.getRequestDispatcher("/WEB-INF/view/product.jsp")
				.forward(request, response);
			
		}else {
			
		response.sendRedirect(request.getContextPath() + "/collection");
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
