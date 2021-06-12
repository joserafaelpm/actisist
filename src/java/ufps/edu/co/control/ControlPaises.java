/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ufps.edu.co.dao.PaisJpaController;
import ufps.edu.co.dto.Pais;
import ufps.edu.co.util.Conexion;

/**
 *
 * @author dunke
 */
@WebServlet(name = "ControlPaises", urlPatterns = {"/ControlPaises"})
public class ControlPaises extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch(request.getParameter("q")){
            case "list": listar(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = new PrintWriter(response.getWriter());
        PaisJpaController pjpa = new PaisJpaController(Conexion.getConexion().getBd());
        List<Pais> ps = pjpa.findPaisEntities();
        for(Pais p: ps){
           pw.println("<option value="+p.getId()+">"+p.getNombre()+"</option>"); 
        }
        pw.flush();
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
