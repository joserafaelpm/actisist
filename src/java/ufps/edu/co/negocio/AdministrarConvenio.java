/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import ufps.edu.co.dao.ConvenioJpaController;
import ufps.edu.co.dao.TipoConvenioJpaController;
import ufps.edu.co.dto.Convenio;
import ufps.edu.co.dto.Institucion;
import ufps.edu.co.dto.Pais;
import ufps.edu.co.dto.PaisInstitucion;
import ufps.edu.co.dto.TipoConvenio;
import ufps.edu.co.util.Conexion;

/**
 *
 * @author dunke
 */
public class AdministrarConvenio {

    public AdministrarConvenio() {
    }
    
    public void registrarConvenio(HttpServletRequest request){
        Convenio c = new Convenio();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Integer numero= Integer.parseInt(request.getParameter("num"));
        String fe_ins = request.getParameter("fe_in"), fe_outs = request.getParameter("fe_out");
        Date fe_in = null, fe_out = null;
        try{
            if(!fe_ins.isEmpty())fe_in = sd.parse(fe_ins);
            if(!fe_outs.isEmpty()) fe_out = sd.parse(fe_outs);
        }catch(ParseException err){
            System.out.println(err.getMessage());
        }
        String rz = request.getParameter("razon");
        String desc = request.getParameter("descr");
        if(request.getParameter("ins_exis")!=null){
            c.setEmpresa(new Institucion(Integer.parseInt(request.getParameter("ins_exi"))));
        }else{
            Institucion i = new Institucion();
            i.setNombre(request.getParameter("ins_non"));

            List<PaisInstitucion> ps = new ArrayList<>();
            PaisInstitucion pi = new PaisInstitucion();
            pi.setPaisId(new Pais(Integer.parseInt(request.getParameter("pai"))));
            ps.add(pi);
            new AdministrarInstituciones().registrarIns(i, ps);
            c.setEmpresa(i);
        }
        c.setTipoConvenio(new TipoConvenio(Integer.parseInt(request.getParameter("tp_con"))));
        c.setNumero(numero);
        c.setDescripcion(desc);
        c.setFecha(fe_in);
        c.setVigencia(fe_out);
        c.setRazon(rz);
        new ConvenioJpaController(Conexion.getConexion().getBd()).create(c);
    }
    
    public List<Convenio> list(){
        return new ConvenioJpaController(Conexion.getConexion().getBd()).findConvenioEntities();
    }
    
    public List<TipoConvenio> listTypes(){
        return new TipoConvenioJpaController(Conexion.getConexion().getBd()).findTipoConvenioEntities();
    }
}
