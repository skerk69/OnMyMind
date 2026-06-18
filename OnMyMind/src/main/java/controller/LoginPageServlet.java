package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utente;
import model.Utente.Ruolo;

import java.io.IOException;
import java.util.ArrayList;

import dao.UtenteDAO;

/**
 * Servlet implementation class LoginPageServlet
 */
@WebServlet("/loginpage")
public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
    public LoginPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		String ruoloStr = request.getParameter("ruolo");
		
		Ruolo ruolo = null;
		if(ruoloStr != null) {

			if(ruoloStr.equals("admin")) {
				ruolo = Ruolo.ADMIN;
			}else if(ruoloStr.equals("utente")) {
				ruolo = Ruolo.UTENTE;
			}
		}
		*/
	    
			request.getRequestDispatcher("/WEB-INF/view/login.jsp") 
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
