package ufps.edu.co.dto;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.ConvenioActividad;
import ufps.edu.co.dto.Institucion;
import ufps.edu.co.dto.TipoConvenio;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:03")
@StaticMetamodel(Convenio.class)
public class Convenio_ { 

    public static volatile SingularAttribute<Convenio, Date> vigencia;
    public static volatile SingularAttribute<Convenio, String> descripcion;
    public static volatile SingularAttribute<Convenio, Date> fecha;
    public static volatile ListAttribute<Convenio, ConvenioActividad> convenioActividadList;
    public static volatile SingularAttribute<Convenio, Integer> numero;
    public static volatile SingularAttribute<Convenio, TipoConvenio> tipoConvenio;
    public static volatile SingularAttribute<Convenio, Integer> id;
    public static volatile SingularAttribute<Convenio, Institucion> empresa;
    public static volatile SingularAttribute<Convenio, String> razon;

}