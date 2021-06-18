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
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.ConferencistaActividad;
import ufps.edu.co.dto.Convenio;
import ufps.edu.co.dto.ConvenioActividad;
import ufps.edu.co.dto.Usuario;
import ufps.edu.co.util.Conexion;

/**
 *
 * @author dunke
 */
public class AdministrarActividad {
    
    public void registrar(Actividad act, String conv, String conf){
        EntityManagerFactory em = Conexion.getConexion().getBd();
        ActividadJpaController ajpa = new ActividadJpaController(em);
        ConvenioActividadJpaController cjpa = new ConvenioActividadJpaController(em);
        ConferencistaActividadJpaController cojpa = new ConferencistaActividadJpaController(em);
        ajpa.create(act);
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
    
    public void editar(Actividad act, String conv, String conf) throws Exception {
        EntityManagerFactory em = Conexion.getConexion().getBd();
        Actividad get = this.getActividad(act);
        if(get.getInvolucradosActividadList().isEmpty()){
            new InvolucradosActividadJpaController(em).create(act.getInvolucradosActividadList().get(0));
        }
        new ActividadJpaController(em).edit(act);
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
