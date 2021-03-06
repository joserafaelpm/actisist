/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ufps.edu.co.dto.Convenio;
import ufps.edu.co.dto.Institucion;
import ufps.edu.co.dto.TipoConvenio;
import ufps.edu.co.dto.Usuario;
import ufps.edu.co.negocio.AdministrarConvenio;
import ufps.edu.co.negocio.AdministrarUsuario;

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
            throws ServletException, IOException, Exception {
        switch(request.getParameter("q")){
            case "list": this.list(request, response);
            break;
            case "show": this.show(request, response);
            break;
            case "reg": this.registrar(request, response);
            break;
            case "edit": this.edit(request, response);
            break;
            case "liste": this.liste(request, response);
        }
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
        try{
            processRequest(request, response);
        }catch(Exception e){
            e.printStackTrace();
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
        try{
            processRequest(request, response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdministrarConvenio ac = new AdministrarConvenio();
        request.getSession().setAttribute("cs", ac.list());
        request.getSession().setAttribute("tps", ac.listTypes());
        response.sendRedirect("templates/administrador/registro-convenio.jsp");
    }
    
    private void show(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("convenio", new AdministrarConvenio().getConvenio(Integer.parseInt(request.getParameter("n_c"))));
        response.sendRedirect("templates/administrador/editar-convenio.jsp");
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario u = new Usuario(((Usuario)request.getSession().getAttribute("user")).getDni());
        new AdministrarConvenio().registrarConvenio(request);
        request.getSession().setAttribute("user", new AdministrarUsuario().getUser(u));
        this.list(request, response);
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ParseException, Exception {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Convenio c = (Convenio) request.getSession().getAttribute("convenio");
        c.setDescripcion(request.getParameter("descr"));
        c.setRazon(request.getParameter("razon"));
        c.setFecha(sd.parse(request.getParameter("fe_in")));
        c.setVigencia(sd.parse(request.getParameter("fe_out")));
        c.setEmpresa(new Institucion(Integer.parseInt(request.getParameter("ins_exi"))));
        c.setNumero(Integer.parseInt(request.getParameter("num")));
        c.setTipoConvenio(new TipoConvenio(Integer.parseInt(request.getParameter("tp_con"))));
        new AdministrarConvenio().edit(c);
        request.getSession().removeAttribute("convenio");
        this.list(request, response);
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

    private void liste(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        List<Convenio> convs = new AdministrarConvenio().list();
        String rta = "";
        for(Convenio c: convs){
            rta += c.getEmpresa().getNombre()+" No."+c.getNumero()+":"+c.getId()+",";
        }
        if(rta.length()!=0)pw.print(rta.substring(0, rta.length()-1));
        pw.flush();
    }
}
