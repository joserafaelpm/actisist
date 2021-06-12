/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ufps.edu.co.dao.InstitucionJpaController;
import ufps.edu.co.dao.PaisInstitucionJpaController;
import ufps.edu.co.dto.Institucion;
import ufps.edu.co.dto.Pais;
import ufps.edu.co.dto.PaisInstitucion;
import ufps.edu.co.util.Conexion;

/**
 *
 * @author dunke
 */
@WebServlet(name = "ControlInstitucion", urlPatterns = {"/ControlInstitucion"})
public class ControlInstitucion extends HttpServlet {

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
        switch (request.getParameter("q")) {
            case "reg":
                registrar(request, response);
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) {
        Institucion i = new Institucion();
        i.setNombre(request.getParameter("ins_non"));

        List<PaisInstitucion> ps = new ArrayList<>();
        PaisInstitucion pi = new PaisInstitucion();
        pi.setPaisId(new Pais(Integer.parseInt(request.getParameter("pai"))));
        ps.add(pi);

        EntityManagerFactory em = Conexion.getConexion().getBd();
        InstitucionJpaController ijpa = new InstitucionJpaController(em);
        PaisInstitucionJpaController pjpa = new PaisInstitucionJpaController(em);
        ijpa.create(i);
        ps.get(0).setInstitucionId(i);
        pjpa.create(ps.get(0));
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
        switch (request.getParameter("q")) {
            case "list":
                listar(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = new PrintWriter(response.getWriter());
        InstitucionJpaController ijpa = new InstitucionJpaController(Conexion.getConexion().getBd());
        List<Institucion> ins = ijpa.findInstitucionEntities();
        for (Institucion i : ins) {
            pw.println("<option value=" + i.getId() + ">" + i.getNombre() + "</option>");
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
