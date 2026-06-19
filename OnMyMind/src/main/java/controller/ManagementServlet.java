package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cappello;
import model.Categoria;

import java.io.IOException;
import java.util.ArrayList;

import dao.CappelloDAO;
import dao.CategoriaDAO;


/**
 * Servlet implementation class ManagementServlet
 */
@WebServlet("/management")
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CappelloDAO cap = new CappelloDAO(); 
		CategoriaDAO cat= new CategoriaDAO();
		
		ArrayList<Cappello> listCap = cap.getAll();
		ArrayList<Categoria> listCat = cat.getAll();
		
		request.setAttribute("cappelli", listCap);
		request.setAttribute("categorie", listCat);
		
		
		request.getRequestDispatcher("/WEB-INF/admin/management.jsp")
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
