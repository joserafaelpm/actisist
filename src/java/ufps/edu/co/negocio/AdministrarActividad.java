/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.negocio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.ActividadJpaController;
import ufps.edu.co.dao.ConferencistaActividadJpaController;
import ufps.edu.co.dao.ConvenioActividadJpaController;
import ufps.edu.co.dao.InvolucradosActividadJpaController;
import ufps.edu.co.dao.TipoActividadJpaController;
import ufps.edu.co.dao.TipoMovilidadJpaController;
import ufps.edu.co.dao.UsuarioJpaController;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.ConferencistaActividad;
import ufps.edu.co.dto.Convenio;
import ufps.edu.co.dto.ConvenioActividad;
import ufps.edu.co.dto.InvolucradosActividad;
import ufps.edu.co.dto.Usuario;
import ufps.edu.co.util.Conexion;

/**
 *
 * @author dunke
 */
public class AdministrarActividad {
    
    public void registrar(Actividad act, String conv, String conf){
        EntityManagerFactory em = Conexion.getConexion().getBd();
        new ActividadJpaController(em).create(act);
        this.createRel(act, em, conf, conv);
    }
    
    public void delete(Integer id) throws IllegalOrphanException, NonexistentEntityException{
        EntityManagerFactory em = Conexion.getConexion().getBd();
        new ActividadJpaController(em).destroy(id);
    }
    
    public void editar(Actividad act, InvolucradosActividad ia, String conv, String conf) throws Exception {
        EntityManagerFactory em = Conexion.getConexion().getBd();
        ActividadJpaController ajpa = new ActividadJpaController(em);
        Actividad get = this.getActividad(act);
        if(act.getImagen()==null) act.setImagen(get.getImagen());
        this.destroyAll(get, em);
        InvolucradosActividadJpaController iajpa = new InvolucradosActividadJpaController(em);
        if(get.getInvolucradosActividad()!=null) iajpa.destroy(get.getInvolucradosActividad().getActividadId());
        ajpa.destroy(act.getId());
        ajpa.create(act);
        ia.setActividad(act);
        ia.setActividadId(act.getId());
        iajpa.create(ia);
        this.createRel(act, em, conf, conv);
    }
    
    private void createRel(Actividad act, EntityManagerFactory em, String conf, String conv){
        ConvenioActividadJpaController cjpa = new ConvenioActividadJpaController(em);
        ConferencistaActividadJpaController cojpa = new ConferencistaActividadJpaController(em);
        if(conv != null && !conv.isEmpty()){
            String [] values = conv.split(",");
            for(String val: values){
                ConvenioActividad ca = new ConvenioActividad();
                ca.setActividadId(act);
                ca.setConvenioId(new Convenio(Integer.parseInt(val)));
                cjpa.create(ca);
            }
        }
        if(conf != null && !conf.isEmpty()){
            String [] values = conf.split(",");
            for(String val: values){
                ConferencistaActividad ca = new ConferencistaActividad();
                ca.setActividadId(act);
                ca.setUsuarioDni(new Usuario(Long.parseLong(val)));
                cojpa.create(ca);
            }
        }
    }
    
    private void destroyAll(Actividad a, EntityManagerFactory emf) throws NonexistentEntityException{
        ConferencistaActividadJpaController cajpa = new ConferencistaActividadJpaController(emf);
        for(ConferencistaActividad ca: a.getConferencistaActividadList()){
            cajpa.destroy(ca.getId());
        }
        
        ConvenioActividadJpaController cojpa = new ConvenioActividadJpaController(emf);
        for(ConvenioActividad ca: a.getConvenioActividadList()){
            cojpa.destroy(ca.getId());
        }
    }

    public Actividad getActividad(Actividad a){
        return new ActividadJpaController(Conexion.getConexion().getBd()).findActividad(a.getId());
    }
    
    public List<Actividad> listFor(Usuario u){
        return new UsuarioJpaController(Conexion.getConexion().getBd()).findUsuario(u.getDni()).getActividadList();
    }
    
    public List<Actividad> listAll(){
        return new ActividadJpaController(Conexion.getConexion().getBd()).findActividadEntities();
    }
    
    public Object listTypeAct(){
        return new TipoActividadJpaController(Conexion.getConexion().getBd()).findTipoActividadEntities();
    }
    
    public Object listTypeMov(){
        return new TipoMovilidadJpaController(Conexion.getConexion().getBd()).findTipoMovilidadEntities();
    }
}
