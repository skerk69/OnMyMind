package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.RecensioneDAO;

/**
 * Servlet implementation class RemoveReviewServlet
 */
@WebServlet("/removereview")
public class RemoveReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("id");
		
		response.sendRedirect(request.getContextPath() + "/openproduct?id=" + idStr);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idRecStr = request.getParameter("id_rec");

		String idCapStr = request.getParameter("id_cap");
		
		if(idCapStr != null && !idCapStr.isBlank() && idRecStr != null && !idRecStr.isBlank()) {
							
				int idRec = Integer.parseInt(idRecStr);
				int idCap = Integer.parseInt(idCapStr);
				RecensioneDAO rdao = new RecensioneDAO();
				
				rdao.delete(idRec);
			
				response.sendRedirect(request.getContextPath() + "/removereview?id=" + idCapStr);
			
		} else {
		response.sendRedirect(request.getContextPath() + "/collection");
		}
		
		
	}

}
