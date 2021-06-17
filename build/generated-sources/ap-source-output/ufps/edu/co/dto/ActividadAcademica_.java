package ufps.edu.co.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.ConferencistaActividad;
import ufps.edu.co.dto.ConvenioActividad;
import ufps.edu.co.dto.Horario;
import ufps.edu.co.dto.TipoMovilidad;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:02")
@StaticMetamodel(ActividadAcademica.class)
public class ActividadAcademica_ { 

    public static volatile ListAttribute<ActividadAcademica, ConvenioActividad> convenioActividadList;
    public static volatile SingularAttribute<ActividadAcademica, Integer> id;
    public static volatile ListAttribute<ActividadAcademica, ConferencistaActividad> conferencistaActividadList;
    public static volatile SingularAttribute<ActividadAcademica, TipoMovilidad> tipoMovilidadId;
    public static volatile ListAttribute<ActividadAcademica, Horario> horarioList;
    public static volatile SingularAttribute<ActividadAcademica, Actividad> actividadId;

}