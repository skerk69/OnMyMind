package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cappello;
import dao.CappelloDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/search") 
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CappelloDAO cappelloDAO = new CappelloDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String nomeParam = request.getParameter("nome");
        String catParam = request.getParameter("categoria");
        String coloreParam = request.getParameter("colore");
        String tagliaParam = request.getParameter("taglia");
        String minParam = request.getParameter("prezzoMin");
        String maxParam = request.getParameter("prezzoMax");

        String nome = null;
        if (nomeParam != null && !nomeParam.trim().isEmpty()) {
            nome = nomeParam.trim();
        }

        Integer idCategoria = null;
        if (catParam != null && !catParam.trim().isEmpty()) {
            idCategoria = Integer.parseInt(catParam.trim());
        }

        String colore = null;
        if (coloreParam != null && !coloreParam.trim().isEmpty()) {
            colore = coloreParam.trim();
        }

        String taglia = null;
        if (tagliaParam != null && !tagliaParam.trim().isEmpty()) {
            taglia = tagliaParam.trim();
        }

        Double prezzoMin = null;
        if (minParam != null && !minParam.trim().isEmpty()) {
            prezzoMin = Double.parseDouble(minParam.trim());
        }

        Double prezzoMax = null;
        if (maxParam != null && !maxParam.trim().isEmpty()) {
            prezzoMax = Double.parseDouble(maxParam.trim());
        }

        ArrayList<Cappello> lista = cappelloDAO.search(nome, idCategoria, colore, taglia, prezzoMin, prezzoMax);

        StringBuilder json = new StringBuilder();
        json.append("[");
        
        for (int i = 0; i < lista.size(); i++) {
            Cappello c = lista.get(i);
            json.append("{");
            json.append("\"id\":").append(c.getId_cappello()).append(",");
            json.append("\"nome\":\"").append(c.getNome().replace("\"", "\\\"")).append("\",");
            json.append("\"colore\":\"").append(c.getColore() != null ? c.getColore().replace("\"", "\\\"") : "").append("\",");
            json.append("\"immagine\":\"").append(c.getImmagine()).append("\"");
            json.append("}");
            
            if (i < lista.size() - 1) {
                json.append(",");
            }
        }
        json.append("]"); 

        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}