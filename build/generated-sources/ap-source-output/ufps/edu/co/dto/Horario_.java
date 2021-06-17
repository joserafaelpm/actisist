package ufps.edu.co.dto;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.ActividadAcademica;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:03")
@StaticMetamodel(Horario.class)
public class Horario_ { 

    public static volatile SingularAttribute<Horario, Date> horaFin;
    public static volatile SingularAttribute<Horario, Date> fecha;
    public static volatile SingularAttribute<Horario, ActividadAcademica> actividadAcademicaId;
    public static volatile SingularAttribute<Horario, Integer> id;
    public static volatile SingularAttribute<Horario, Date> horaInicio;

}