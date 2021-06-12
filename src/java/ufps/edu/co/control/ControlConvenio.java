/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.control;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ufps.edu.co.dao.ConvenioJpaController;
import ufps.edu.co.dao.TipoConvenioJpaController;
import ufps.edu.co.negocio.AdministrarConvenio;
import ufps.edu.co.util.Conexion;

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
            case "redi": this.redirigir(request, response);
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
    
    private void redirigir(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManagerFactory em = Conexion.getConexion().getBd();
        request.getSession().setAttribute("cs", new ConvenioJpaController(em).findConvenioEntities());
        request.getSession().setAttribute("tps", new TipoConvenioJpaController(em).findTipoConvenioEntities());
        response.sendRedirect("registroConvenio.jsp");
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
