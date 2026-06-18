package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;

import dao.UtenteDAO;

/**
 * Servlet implementation class LoginPageServlet
 */
@WebServlet("/loginpage")
public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtenteDAO user = new UtenteDAO();
	
    public LoginPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Utente> listUser = user.getAll();
		
		request.setAttribute("utenti", listUser);
		
        request.getRequestDispatcher("/WEB-INF/view/login.jsp")
        .forward(request, response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
