/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ufps.edu.co.negocio.AdministrarConvenio;

/**
 *
 * @author dunke
 */
@WebServlet(name = "ControlConvenio", urlPatterns = {"/ControlConvenio"})
public class ControlConvenio extends HttpServlet {

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
        switch(request.getParameter("q")){
            case "list": this.list(request, response);
            break;
            case "edit": this.edit(request, response);
            break;
        }
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
            case "reg": this.registrar(request, response);
            break;
        }
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdministrarConvenio ac = new AdministrarConvenio();
        request.getSession().setAttribute("cs", ac.list());
        request.getSession().setAttribute("tps", ac.listTypes());
        response.sendRedirect("registroConvenio.jsp");
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new AdministrarConvenio().registrarConvenio(request);
        response.sendRedirect("registroConvenio.jsp");
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
