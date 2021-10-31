/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import ufps.edu.co.dao.RolJpaController;
import ufps.edu.co.dto.Rol;
import ufps.edu.co.dto.SolicitudRegistro;
import ufps.edu.co.dto.TipoDocente;
import ufps.edu.co.dto.Usuario;
import ufps.edu.co.negocio.AdministrarUsuario;
import ufps.edu.co.util.Conexion;
import ufps.edu.co.util.EmailService;

/**
 *
 * @author dunke
 */
@WebServlet(name = "ControlUsuario", urlPatterns = {"/ControlUsuario"})
public class ControlUsuario extends HttpServlet {

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
        try {
            switch (request.getParameter("q")) {
                case "sign":
                    this.login(request, response);
                    break;
                case "log":
                    this.logout(request, response);
                    break;
                case "reg_sol":
                    this.registrarSolicitud(request, response);
                    break;
                case "reg":
                    this.registrarUsuario(request, response);
                    break;
                case "access_sol": ;
                    this.accesoSolicitud(request, response);
                    break;
                case "list":
                    this.listar(request, response);
                case "liste":
                    this.liste(request, response);
                case "perfil":
                    this.perfil(request, response);
                case "edit":
                    this.edit(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        processRequest(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long doc = Long.parseLong(request.getParameter("doc"));
        String cods = request.getParameter("cod");
        Long cod = Long.parseLong(cods.isEmpty() ? "0" : cods);
        String pw = request.getParameter("pw");
        Usuario u = new Usuario(doc, "", "");
        if((u = new AdministrarUsuario().login(u, cod, pw)) != null && (u.getDocente()==null || (u.getDocente()!=null && u.getDocente().getActivo()))){
            request.getSession().setAttribute("user", u);
            if(u.getIdRol().getId()==1)response.sendRedirect("templates/administrador/dashboard.jsp");
            else response.sendRedirect("ControlActividad?q=showFor");
        }else{
            this.logout(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("login.jsp");
    }

    private void registrarSolicitud(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SolicitudRegistro sr = new SolicitudRegistro();
        Integer type_us = Integer.parseInt(request.getParameter("docOrConf"));
        String email = request.getParameter("mail_us");
        sr.setEmail(email);
        sr.setTypeUs(new Rol(type_us));
        new AdministrarUsuario().registrarSolicitud(sr);
        new EmailService().sendEmail(sr);
        this.listar(request, response);
    }

    private void accesoSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            AdministrarUsuario admin = new AdministrarUsuario();
            SolicitudRegistro sr = new SolicitudRegistro(request.getParameter("token"));
            sr.setTypeUs(new Rol(Integer.parseInt(request.getParameter("type"))));
            sr = admin.accesoSolicitud(sr); 
            if (sr != null) {
                request.getSession().setAttribute("types", admin.getTypes(sr.getTypeUs().getId())); 
                request.getSession().setAttribute("sol", sr);
                response.sendRedirect("registro-doc-conf.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (NumberFormatException err) {
            response.sendRedirect("index.jsp");
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        new AdministrarUsuario().registrarUsuario(request);
        response.sendRedirect("index.jsp");
    }
    
    private void perfil(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("type", new AdministrarUsuario().getTypes(2));
        request.getSession().setAttribute("user", new AdministrarUsuario().getUser((Usuario) request.getSession().getAttribute("user")));
        response.sendRedirect("templates/docente/mi-perfil.jsp");
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException, ParseException, Exception {
        AdministrarUsuario admin = new AdministrarUsuario();
        Usuario u = new Usuario(((Usuario) request.getSession().getAttribute("user")).getDni());
        admin.edit(u, this.getUser(request, (Usuario) request.getSession().getAttribute("user")));
        request.getSession().setAttribute("user", admin.getUser(u));
        this.perfil(request, response);
    }
    
    private String getUser(HttpServletRequest request, Usuario u) throws FileUploadException, IOException, ParseException{
        String nombre = "", ape = "", titles = "";
        Integer typeDoc = 0, vmg = 0;
        InputStream imagen = null;
        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                nombre = item.getFieldName().equalsIgnoreCase("nombre") ? item.getString() : nombre;
                ape = item.getFieldName().equalsIgnoreCase("ape") ? item.getString() : ape;
                titles = item.getFieldName().equalsIgnoreCase("titles") ? item.getString() : titles;
                typeDoc = item.getFieldName().equalsIgnoreCase("type") ? Integer.parseInt(item.getString()) : typeDoc;
            } else {
                imagen = item.getInputStream();
            }
        }

        if(vmg != 0) u.getDocente().setImagenPerfil(IOUtils.toByteArray(imagen));
        u.setNombre(nombre);
        u.setApellido(ape);
        u.getDocente().setIdTipoDocente(new TipoDocente(typeDoc));
        
        return titles;
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

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory em = Conexion.getConexion().getBd();
        request.getSession().setAttribute("ujpa", new AdministrarUsuario().list());
        request.getSession().setAttribute("rjpa", new RolJpaController(em).findRolEntities());
        response.sendRedirect("templates/administrador/registrar-doc-conf.jsp");
    }
    
    public void liste(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter pw = response.getWriter();
        Usuario u = ((Usuario)request.getSession().getAttribute("user"));
        List<Usuario> users = new AdministrarUsuario().list();
        String rta = "";
        for(Usuario us: users){
            if(!us.getDni().equals(u.getDni())){
                rta += us.getNombre()+" "+us.getApellido()+":"+us.getDni()+",";
            }
        }
        if(rta.length()!=0)pw.print(rta.substring(0, rta.length()-1));
        pw.flush();
    }
}
