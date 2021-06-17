package ufps.edu.co.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.ActividadInstitucion;
import ufps.edu.co.dto.Conferencista;
import ufps.edu.co.dto.Convenio;
import ufps.edu.co.dto.PaisInstitucion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:03")
@StaticMetamodel(Institucion.class)
public class Institucion_ { 

    public static volatile ListAttribute<Institucion, Convenio> convenioList;
    public static volatile ListAttribute<Institucion, ActividadInstitucion> actividadInstitucionList;
    public static volatile ListAttribute<Institucion, Conferencista> conferencistaList;
    public static volatile SingularAttribute<Institucion, Integer> id;
    public static volatile ListAttribute<Institucion, PaisInstitucion> paisInstitucionList;
    public static volatile SingularAttribute<Institucion, String> nombre;

}