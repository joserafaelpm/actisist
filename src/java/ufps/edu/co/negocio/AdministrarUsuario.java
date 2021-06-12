/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.negocio;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import ufps.edu.co.dao.ConferencistaJpaController;
import ufps.edu.co.dao.DocenteJpaController;
import ufps.edu.co.dao.SolicitudRegistroJpaController;
import ufps.edu.co.dao.TipoConferencistaJpaController;
import ufps.edu.co.dao.TipoDocenteJpaController;
import ufps.edu.co.dao.UsuarioJpaController;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dao.exceptions.PreexistingEntityException;
import ufps.edu.co.dto.Conferencista;
import ufps.edu.co.dto.Docente;
import ufps.edu.co.dto.Institucion;
import ufps.edu.co.dto.Pais;
import ufps.edu.co.dto.SolicitudRegistro;
import ufps.edu.co.dto.TipoConferencista;
import ufps.edu.co.dto.TipoDocente;
import ufps.edu.co.dto.Usuario;
import ufps.edu.co.util.Conexion;
import ufps.edu.co.util.PasswordAuthentication;

/**
 *
 * @author dunke
 */
public class AdministrarUsuario {

    public AdministrarUsuario() {
    }

    public SolicitudRegistro accesoSolicitud(HttpServletRequest request, SolicitudRegistro sr){
        EntityManagerFactory em = Conexion.getConexion().getBd();
        Integer tp = sr.getTypeUs().getId();
        sr = new SolicitudRegistroJpaController(em).findSolicitudRegistro(request.getParameter("token"));
            if (sr != null && sr.getTypeUs().getId().equals(tp)) {
                if (sr.getTypeUs().getId() == 2) {
                    request.getSession().setAttribute("types", new TipoDocenteJpaController(em).findTipoDocenteEntities());
                } else {
                    request.getSession().setAttribute("types", new TipoConferencistaJpaController(em).findTipoConferencistaEntities());
                }
                return sr;
            }
        return null;
    }
    
    public Usuario login(Usuario u, Long cod, String pw){
        UsuarioJpaController ujpa = new UsuarioJpaController(Conexion.getConexion().getBd());
        u = ujpa.findUsuario(u.getDni());
        if (u != null && ((u.getDocente() != null && u.getDocente().getCodigo() == cod) || u.getIdRol().getId() == 1) && new PasswordAuthentication().authenticate(pw, u.getContraseña())) {
            return u;
        }
        return null;
    }
    
    public void registrarSolicitud(SolicitudRegistro sr)throws Exception {
        SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
        String token = "";
        for (int i = 1; i <= 10; i++) {
            token += i % 2 == 0 ? number.nextInt(21) : (char)(number.nextInt(91)+65);
        }
        sr.setToken(token);
        new SolicitudRegistroJpaController(Conexion.getConexion().getBd()).create(sr);
    }

    public void registrarUsuario(HttpServletRequest request) throws PreexistingEntityException, Exception {
        SolicitudRegistro s = ((SolicitudRegistro) request.getSession().getAttribute("sol"));
        InputStream imagen = null;
        String nombre = "", apellido = "", contraseña = "";
        Long documento = 0L, cod = 0L;
        Integer ins_exi = 0, pai = 0, type = 0;
        List<FileItem> items = null;
        items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                nombre = item.getFieldName().equalsIgnoreCase("nam") ? item.getString() : nombre;
                apellido = item.getFieldName().equalsIgnoreCase("ape") ? item.getString() : apellido;
                documento = item.getFieldName().equalsIgnoreCase("doc") ? Long.parseLong(item.getString()) : documento;
                type = item.getFieldName().equalsIgnoreCase("types") ? Integer.parseInt(item.getString()) : type;
                cod = item.getFieldName().equalsIgnoreCase("cod") ? Long.parseLong(item.getString()) : cod;
                ins_exi = item.getFieldName().equalsIgnoreCase("ins_exi") ? Integer.parseInt(item.getString()) : ins_exi;
                pai = item.getFieldName().equalsIgnoreCase("pai") ? Integer.parseInt(item.getString()) : pai;
                contraseña = item.getFieldName().equalsIgnoreCase("pw") ? item.getString() : contraseña;
            } else {
                imagen = item.getInputStream();
            }
        }
        Usuario u = new Usuario(documento, nombre, s.getEmail());
        u.setIdRol(s.getTypeUs());
        u.setApellido(apellido);
        u.setContraseña(new PasswordAuthentication().hash(contraseña));
        EntityManagerFactory em = Conexion.getConexion().getBd();
        new UsuarioJpaController(em).create(u);
        if (u.getIdRol().getId() == 2) {
            Docente d = new Docente(documento, true, cod, IOUtils.toByteArray(imagen));
            d.setActivo(true);
            d.setIdTipoDocente(new TipoDocente(type));
            this.registrarDocente(em, s, d);
        } else {
            Conferencista c = new Conferencista(documento);
            c.setInstitucionId(new Institucion(ins_exi));
            c.setPaisOrigen(new Pais(pai));
            c.setTipoConferencistaId(new TipoConferencista(type));
            this.registrarConferencista(em, s, c);
        }
    }

    public void registrarDocente(EntityManagerFactory em, SolicitudRegistro s, Docente d) throws PreexistingEntityException, Exception {
        new DocenteJpaController(em).create(d);
        this.delSol(em, s);
    }

    public void registrarConferencista(EntityManagerFactory em, SolicitudRegistro s, Conferencista c) throws PreexistingEntityException, Exception {
        new ConferencistaJpaController(em).create(c);
        this.delSol(em, s);
    }

    private void delSol(EntityManagerFactory em, SolicitudRegistro s) throws NonexistentEntityException {
        new SolicitudRegistroJpaController(em).destroy(s.getToken());
    }
    
    /**
     * Para insertar al admin por si se borra
     * @param args
     * @throws Exception 
     */
//    public static void main(String[] args) throws Exception {
//        Usuario u = new Usuario(1150000L, "Administrador", "email_use@ufps.edu.co");
//        u.setIdRol(new Rol(1));
//        u.setContraseña(new PasswordAuthentication().hash("sistema#115"));
//        EntityManagerFactory em = Conexion.getConexion().getBd();
//        new UsuarioJpaController(em).create(u);
//    }
}