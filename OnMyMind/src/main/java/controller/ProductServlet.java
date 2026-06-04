package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Cappello;

import java.io.IOException;
import java.util.ArrayList;

import dao.CappelloDAO;
import dao.CategoriaDAO;
import model.Categoria;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/product")
@MultipartConfig
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CappelloDAO cap = new CappelloDAO();
	private CategoriaDAO cat = new CategoriaDAO();
	private Categoria categoria = null;
	
    public ProductServlet() {
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
			doInsert(request);
		}
		break;
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

	
	public void doInsert(HttpServletRequest request) {
		
		
		int id_categoria = Integer.parseInt(request.getParameter("categoria"));
		String nome= request.getParameter("nome");
		String descrizione= request.getParameter("descrizione");
		double prezzo= Double.parseDouble(request.getParameter("prezzo"));
		String taglia= request.getParameter("taglia");
		String colore= request.getParameter("colore");
		String materiale= request.getParameter("materiale");
		int quantita= Integer.parseInt(request.getParameter("quantita"));
		String immagine= request.getParameter("immagine");

		
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

	}
	
	public void doModify(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));
		
		Cappello c = cap.getById(id);
		
		Categoria ct = cat.getById(Integer.parseInt(request.getParameter("id_categoria")));
		
		c.setId_cappello(id);
		c.setCategoria(ct);
		c.setNome(request.getParameter("nome"+ c.getId_cappello()));
		c.setDescrizione(request.getParameter("descrizione"+ c.getId_cappello()));
		c.setPrezzo(Double.parseDouble(request.getParameter("prezzo"+ c.getId_cappello())));
		c.setTaglia(request.getParameter("taglia"+ c.getId_cappello()));
		c.setColore(request.getParameter("colore"+ c.getId_cappello()));
		c.setMateriale(request.getParameter("materiale"+ c.getId_cappello()));
		c.setQuantitaMagazzino(Integer.parseInt(request.getParameter("quantita"+ c.getId_cappello())));
		c.setImmagine(request.getParameter("immagine"+ c.getId_cappello()));
		
		cap.update(c);
	}
	
	public void doDelete(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		cap.delete(id);
		
	}
	
	
	
	
	
	
	
	
	
	
}
