/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.negocio;

import java.util.List;
import ufps.edu.co.dao.PaisJpaController;
import ufps.edu.co.dto.Pais;
import ufps.edu.co.util.Conexion;

/**
 *
 * @author dunke
 */
public class AdministrarPaises {
    public List<Pais> list(){
        return new PaisJpaController(Conexion.getConexion().getBd()).findPaisEntities();
    }
}
