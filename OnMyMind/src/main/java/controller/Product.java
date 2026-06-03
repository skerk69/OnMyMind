package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cappello;

import java.io.IOException;
import java.net.http.HttpRequest;

import com.sun.jmx.mbeanserver.DescriptorCache;

import dao.CappelloDAO;
import dao.CategoriaDAO;
import model.Categoria;

/**
 * Servlet implementation class Product
 */
@WebServlet("/product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private CappelloDAO cap = new CappelloDAO();
	private CategoriaDAO cat = new CategoriaDAO();
	private Categoria categoria;
	
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action= request.getParameter("action");
		
		switch(action) {
		case "insert":  {
			
			int id_categoria = Integer.parseInt(request.getParameter("categoria"));
			String nome= request.getParameter("nome");
			String descrizione= request.getParameter("descrizione");
			double prezzo= Double.parseDouble(request.getParameter("prezzo"));
			String taglia= request.getParameter("taglia");
			String colore= request.getParameter("colore");
			String materiale= request.getParameter("materiale");
			int quantita= Integer.parseInt(request.getParameter("quantita"));
			String immagine= request.getParameter("immagine");
	
			doInsert(id_categoria, nome, descrizione, prezzo, taglia, colore, materiale, quantita, immagine, request, response);
		}
		break;
		case "modify": {
		
			//lo farò quando farò la parte di modify
			
		}
		break;
		case "delete": {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			doDelete(id, request, response);
		}
		break;
		default:
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	public void doInsert(int id_categoria, String nome, String descrizione, double prezzo, String taglia, String colore, String materiale, int quantita, String immagine, HttpServletRequest request, HttpServletResponse response) {
		
		Cappello c = new Cappello();
		
		if(id_categoria == 0) {
			categoria = new Categoria();
			categoria.setNomeCategoria(request.getParameter("nomeCategoria"));
			categoria.setDescrizione(request.getParameter("descCategoria"));
			cat.insert(categoria);
		}else {
			categoria = cat.getById(id_categoria);
		}
		
		c.setCategoria(categoria);
		c.setNome(nome);
		c.setDescrizione(descrizione);
		c.setPrezzo(prezzo);
		c.setTaglia(taglia);
		c.setColore(colore);
		c.setMateriale(materiale);
		c.setQuantitaMagazzino(quantita);
		c.setImmagine(immagine);
		
		cap.insert(c);
		
		response.sendRedirect("/WEB-INF/admin/management.jsp");
	}
	
	public void doModify() {
		
	}
	
	public void doDelete(int id, HttpServletRequest request, HttpServletResponse response) {
		
		cap.delete(id);
		
		response.sendRedirect("/WEB-INF/admin/management.jsp");
		
	}
	
	
	
	
	
	
	
	
	
	
}
