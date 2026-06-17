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
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoriaDAO cat = new CategoriaDAO();
	private CappelloDAO cap = new CappelloDAO();
	
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action= request.getParameter("action");
		
		
		switch(action) {
	
		case "modify": {
			doModify(request);
		}
		break;
		case "delete": {
			doDelete(request);
		}
		break;
		default: 
		}
		
		
		ArrayList<Categoria> listcat = cat.getAll();
	    request.setAttribute("categorie", listcat);
		
		ArrayList<Cappello> listcap = cap.getAll();
	    request.setAttribute("cappelli", listcap);
	    
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

	
	public void doModify(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("id_categoria"));
		
		Categoria c = cat.getById(id);
		
		c.setNomeCategoria(request.getParameter("nome_categoria"));
		c.setDescrizione(request.getParameter("descrizione_categoria"));
		
		cat.update(c);
		
	}
	
	public void doDelete(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("id_categoria"));
		
		cat.delete(id);
	}
	
}
