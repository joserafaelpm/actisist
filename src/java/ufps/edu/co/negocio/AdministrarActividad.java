/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.negocio;

import ufps.edu.co.dao.TipoActividadJpaController;
import ufps.edu.co.dao.TipoMovilidadJpaController;
import ufps.edu.co.util.Conexion;

/**
 *
 * @author dunke
 */
public class AdministrarActividad {
    
    
    public Object listTypeAct(){
        return new TipoActividadJpaController(Conexion.getConexion().getBd()).findTipoActividadEntities();
    }
    
    public Object listTypeMov(){
        return new TipoMovilidadJpaController(Conexion.getConexion().getBd()).findTipoMovilidadEntities();
    }
}
