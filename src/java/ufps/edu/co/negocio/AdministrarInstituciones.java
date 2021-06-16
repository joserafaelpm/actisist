/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.negocio;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.InstitucionJpaController;
import ufps.edu.co.dao.PaisInstitucionJpaController;
import ufps.edu.co.dto.Institucion;
import ufps.edu.co.dto.PaisInstitucion;
import ufps.edu.co.util.Conexion;

/**
 *
 * @author dunke
 */
public class AdministrarInstituciones {

    public AdministrarInstituciones() {
    }
    
    public void registrarIns(Institucion i, List<PaisInstitucion> ps ){
        EntityManagerFactory em = Conexion.getConexion().getBd();
        new InstitucionJpaController(em).create(i);
        ps.get(0).setInstitucionId(i);
        new PaisInstitucionJpaController(em).create(ps.get(0));
    }
    
    public List<Institucion> listInstituciones(){
        return new InstitucionJpaController(Conexion.getConexion().getBd()).findInstitucionEntities();
    }
}
