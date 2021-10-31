/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.control;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.ConferencistaActividad;
import ufps.edu.co.dto.Convenio;
import ufps.edu.co.dto.ConvenioActividad;
import ufps.edu.co.dto.InvolucradosActividad;
import ufps.edu.co.dto.TipoActividad;
import ufps.edu.co.dto.TipoMovilidad;
import ufps.edu.co.dto.Usuario;
import ufps.edu.co.negocio.AdministrarActividad;
import ufps.edu.co.negocio.AdministrarConvenio;
import ufps.edu.co.negocio.AdministrarUsuario;
import ufps.edu.co.negocio.InformePDF;

/**
 *
 * @author dunke
 */
@WebServlet(name = "ControlActividad", urlPatterns = {"/ControlActividad"})
public class ControlActividad extends HttpServlet {

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
            throws ServletException, IOException, FileUploadException, ParseException, Exception {
        switch (request.getParameter("q")) {
            case "redi":
                this.redi_reg(request, response);
                break;
            case "reg":
                this.registrar(request, response);
                break;
            case "del":
                this.delete(request, response);
                break;
            case "show":
                this.show(request, response);
                break;
            case "showFor":
                this.showFor(request, response);
                break;
            case "edit":
                this.edit(request, response);
                break;
            case "editFor":
                this.editFor(request, response);
                break;
            case "listeConf":
                this.listeConf(request, response);
                break;
            case "listeConv":
                this.listeConv(request, response);
                break;
            case "info":
                this.informe(request, response);
                break;
        }
    }

    private void redi_reg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdministrarActividad a = new AdministrarActividad();
        request.getSession().setAttribute("typeAct", a.listTypeAct());
        request.getSession().setAttribute("typeMov", a.listTypeMov());
        response.sendRedirect("templates/docente/registrar-actividad.jsp");
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException, ParseException {
        Actividad act = new Actividad();
        String list[] = this.getActividad(request, act, null);
        new AdministrarActividad().registrar(act, list[1], list[0]);
        this.showFor(request, response);
    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("acts", new AdministrarActividad().listAll());
        response.sendRedirect("templates/administrador/ver-actividades.jsp");
    }

    private void showFor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("acts", new AdministrarActividad().listFor(((Usuario) request.getSession().getAttribute("user"))));
        response.sendRedirect("templates/docente/mis-actividades.jsp");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdministrarActividad admin = new AdministrarActividad();
        Actividad a = new Actividad(Integer.parseInt(request.getParameter("id")));
        a = new AdministrarActividad().getActividad(a);
        request.getSession().setAttribute("act", a);
        request.getSession().setAttribute("typeAct", admin.listTypeAct());
        request.getSession().setAttribute("typeMov", admin.listTypeMov());
        response.sendRedirect("templates/docente/editar-actividad.jsp");
    }
    
    private void editFor(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException, ParseException, Exception {
        Actividad act = new Actividad(((Actividad)request.getSession().getAttribute("act")).getId());
        InvolucradosActividad ia = new InvolucradosActividad();
        String list[] = this.getActividad(request, act, ia);
        new AdministrarActividad().editar(act, ia, list[1], list[0]);
        showFor(request, response);
    }

    public void listeConf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        Usuario user = (Usuario)request.getSession().getAttribute("user");
        Actividad a = ((Actividad) request.getSession().getAttribute("act"));
        List<Usuario> us = new AdministrarUsuario().list();
        String rta = "";
        boolean found = false;
        for (Usuario u : us) {
            if(!user.getDni().equals(u.getDni())){
                for (ConferencistaActividad ca : a.getConferencistaActividadList()) {
                    if(u.getDni().equals(ca.getUsuarioDni().getDni())){
                        found = true;
                        break;
                    }
                }
                rta += u.getNombre() + " " + u.getApellido() + ":" + u.getDni() +":"+found+",";
                found = false;
            }
        }
        if (rta.length() != 0) {
            pw.print(rta.substring(0, rta.length() - 1));
        }
        pw.flush();
    }
    
    public void listeConv(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        Actividad a = ((Actividad) request.getSession().getAttribute("act"));
        List<Convenio> cs = new AdministrarConvenio().list();
        String rta = "";
        boolean found = false;
        for (Convenio c : cs) {
            for (ConvenioActividad ca : a.getConvenioActividadList()) {
                if(c.getId().equals(ca.getConvenioId().getId())){
                    found = true;
                    break;
                }
            }
            rta += c.getEmpresa().getNombre()+ " No." + c.getNumero() + ":" + c.getId()+":"+found+",";
            found = false;
        }
        if (rta.length() != 0) {
            pw.print(rta.substring(0, rta.length() - 1));
        }
        pw.flush();
    }
    
    private String[] getActividad(HttpServletRequest request, Actividad act, InvolucradosActividad inAct) throws FileUploadException, IOException, ParseException{
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        act.setUsuarioDni(new Usuario(((Usuario) request.getSession().getAttribute("user")).getDni()));
        String nombre = "", fe_in = "", fe_out = "", tema = "", desc = "", lugar = "", conv = "", conf = "";
        Integer typeAct = 0, typeMov = 0, sem = 0, cantC = 0, cantTC = 0, cantTCP = 0, cantE = 0, vmg = 0;
        InputStream imagen = null;
        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                nombre = item.getFieldName().equalsIgnoreCase("nombre") ? item.getString() : nombre;
                fe_in = item.getFieldName().equalsIgnoreCase("fe_in") ? item.getString() : fe_in;
                fe_out = item.getFieldName().equalsIgnoreCase("fe_out") ? item.getString() : fe_out;
                typeAct = item.getFieldName().equalsIgnoreCase("typeAct") ? Integer.parseInt(item.getString()) : typeAct;
                typeMov = item.getFieldName().equalsIgnoreCase("typeMov") ? Integer.parseInt(item.getString()) : typeMov;
                sem = item.getFieldName().equalsIgnoreCase("sem") ? Integer.parseInt(item.getString()) : sem;
                tema = item.getFieldName().equalsIgnoreCase("tema") ? item.getString() : tema;
                lugar = item.getFieldName().equalsIgnoreCase("lugar") ? item.getString() : lugar;
                desc = item.getFieldName().equalsIgnoreCase("desc") ? item.getString() : desc;
                conv = item.getFieldName().equalsIgnoreCase("conv") ? item.getString() : conv;
                conf = item.getFieldName().equalsIgnoreCase("conf") ? item.getString() : conf;
                cantC = item.getFieldName().equalsIgnoreCase("cantC") ? Integer.parseInt(item.getString()) : cantC;
                cantTC = item.getFieldName().equalsIgnoreCase("cantTC") ? Integer.parseInt(item.getString()) : cantTC;
                cantTCP = item.getFieldName().equalsIgnoreCase("cantTCP") ? Integer.parseInt(item.getString()) : cantTCP;
                cantE = item.getFieldName().equalsIgnoreCase("cantE") ? Integer.parseInt(item.getString()) : cantE;
                vmg = item.getFieldName().equalsIgnoreCase("vmg") ? Integer.parseInt(item.getString()) : vmg;
            } else {
                imagen = item.getInputStream();
            }
        }

        if(inAct!=null){
            inAct.setCantDocC(cantC);
            inAct.setCantDocTC(cantTC);
            inAct.setCantDocCTP(cantTCP);
            inAct.setCantEst(cantE);
            inAct.setActividad(act);
            inAct.setActividadId(act.getId());
        }
        
        act.setNombre(nombre);
        act.setDescripcion(desc);
        act.setLugar(lugar);
        act.setSemestre(sem);
        act.setTematica(tema);
        act.setFechaInicio(sd.parse(fe_in));
        act.setFechaFin(sd.parse(fe_out));
        if(vmg != 0) act.setImagen(IOUtils.toByteArray(imagen));
        act.setTipoActividadId(new TipoActividad(typeAct));
        act.setTipoMovilidadId(new TipoMovilidad(typeMov));
        
        return new String[]{conf, conv};
    }

    private void informe(HttpServletRequest request, HttpServletResponse response) throws DocumentException, BadElementException, IOException {
        AdministrarActividad admin = new AdministrarActividad();
        Usuario u = (Usuario)request.getSession().getAttribute("user");
        List<Actividad> acts = new ArrayList<>();
        String val[] = request.getParameterValues("act");
        for(String s: val){
            String sp[] = s.split("-");
            if(sp[1].equals("true")){
                acts.add(admin.getActividad(new Actividad(Integer.parseInt(sp[0]))));
            }
        }
        new InformePDF(request.getServletContext().getRealPath("/"), acts).createPDF();
        response.setContentType("application/pdf");
        File file = new File(request.getServletContext().getRealPath("/")).getParentFile().getParentFile();
        InputStream is = new FileInputStream(file.getAbsolutePath()+"\\temp\\InformeActividades.pdf");
        IOUtils.copy(is, response.getOutputStream());
        is.close();
        response.flushBuffer();
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IllegalOrphanException, NonexistentEntityException, IOException{
        AdministrarActividad admin = new AdministrarActividad();
        admin.delete(Integer.parseInt(request.getParameter("id")));
        this.showFor(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception e) {
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
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
