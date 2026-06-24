package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Cappello;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import dao.CappelloDAO;
import dao.CategoriaDAO;
import model.Categoria;
import model.Utente;
import model.Utente.Ruolo;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/product")
@MultipartConfig
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		
		Utente u = (Utente) session.getAttribute("utente");
		
		if(u == null || u.getRuolo() == Ruolo.UTENTE) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		CappelloDAO cap = new CappelloDAO();
		CategoriaDAO cat = new CategoriaDAO();
		
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
		default:;
		}
		
		response.sendRedirect(request.getContextPath() + "/product");
	}

	
	public void doInsert(HttpServletRequest request) {
	
		CappelloDAO cap = new CappelloDAO();
		CategoriaDAO cat = new CategoriaDAO();		
		
		int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
		String nome= request.getParameter("nome");
		String descrizione= request.getParameter("descrizione");
		double prezzo= Double.parseDouble(request.getParameter("prezzo"));
		String taglia= request.getParameter("taglia");
		String colore= request.getParameter("colore");
		String materiale= request.getParameter("materiale");
		int quantita= Integer.parseInt(request.getParameter("quantita"));
		Part immagine;
		String fileName="";
		try {
			immagine = request.getPart("immagine");
			fileName = immagine.getSubmittedFileName();
			String uploadPath = getServletContext().getRealPath("/images") + File.separator + fileName;
	        immagine.write(uploadPath);
		} catch (IOException | ServletException e) {
			return;
		}
		
		Cappello c = new Cappello();
	
		Categoria categoria = cat.getById(id_categoria);
		
		c.setCategoria(categoria);
		c.setNome(nome);
		c.setDescrizione(descrizione);
		c.setPrezzo(prezzo);
		c.setTaglia(taglia);
		c.setColore(colore);
		c.setMateriale(materiale);
		c.setQuantitaMagazzino(quantita);
		c.setImmagine(fileName);
		
		cap.insert(c);

	}
	
	public void doModify(HttpServletRequest request) {

		CappelloDAO cap = new CappelloDAO();
		CategoriaDAO cat = new CategoriaDAO();		
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Cappello c = cap.getById(id);
		
		String id_categoriaStr = request.getParameter("id_categoria" + c.getId_cappello());
		if(id_categoriaStr != null && !id_categoriaStr.isBlank()) {
			int id_categoria;
			try{
			id_categoria = Integer.parseInt(id_categoriaStr);
			}catch (Exception e) {
			id_categoria = c.getCategoria().getId_categoria();
			}
			Categoria categoria = cat.getById(id_categoria);
			c.setCategoria(categoria);
		}
			
		String nome = request.getParameter("nome"+ c.getId_cappello());
		if(nome != null && !nome.isBlank())
			c.setNome(nome);
		String descrizione = request.getParameter("descrizione"+ c.getId_cappello());
		if(descrizione != null && !descrizione.isBlank())
			c.setDescrizione(descrizione);
		
		String prezzoStr = request.getParameter("prezzo"+ c.getId_cappello());
		if(prezzoStr != null && !prezzoStr.isBlank()) {
			double prezzo;
			try{
			prezzo= Double.parseDouble(prezzoStr);
			}catch (Exception e) {
			prezzo=c.getPrezzo();	
			}
			c.setPrezzo(prezzo);
		}
		String taglia = request.getParameter("taglia"+ c.getId_cappello());
		if(taglia != null && !taglia.isBlank())	
			c.setTaglia(taglia);
		String colore = request.getParameter("colore"+ c.getId_cappello());
		if(colore != null && !colore.isBlank())
			c.setColore(colore);
		String materiale = request.getParameter("materiale"+ c.getId_cappello());
		if(materiale != null && !materiale.isBlank())
			c.setMateriale(materiale);

		String quantitaStr = request.getParameter("quantita"+ c.getId_cappello());
		if(quantitaStr != null && !quantitaStr.isBlank()) {
			int quantita;
			try{
			quantita= Integer.parseInt(quantitaStr);
			}catch (Exception e) {
			quantita=c.getQuantitaMagazzino();	
			}
			c.setQuantitaMagazzino(quantita);
		}

		try {
			Part immagine = request.getPart("immagine" + c.getId_cappello());

			if (immagine != null && immagine.getSize() > 0) {

				String fileName = immagine.getSubmittedFileName();
				String uploadPath = getServletContext().getRealPath("/images") + File.separator + fileName;
		
				String vecchiaImmagine = c.getImmagine();
			    if (vecchiaImmagine != null && !vecchiaImmagine.isEmpty()) {
			        File vecchioFile = new File(getServletContext().getRealPath("/images") + File.separator + vecchiaImmagine);
			        if (vecchioFile.exists()) {
			            vecchioFile.delete();
			        }
			    }
			    immagine.write(uploadPath); 
			    c.setImmagine(fileName);
			}
	        
		} catch (IOException | ServletException e) {
			return;
		}
		

		cap.update(c);
		
	}
	
	public void doDelete(HttpServletRequest request) {
		
		CappelloDAO cap = new CappelloDAO();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Cappello c = cap.getById(id);
		
		String img = c.getImmagine();
	    if (img != null && !img.isEmpty()) {
	        File imgFile = new File(getServletContext().getRealPath("/images") + File.separator + img);
	        if (imgFile.exists()) {
	            imgFile.delete();
	        }
	    }
		
		cap.delete(id);
		
	}
	
	
	
	
	
	
	
	
	
	
}
