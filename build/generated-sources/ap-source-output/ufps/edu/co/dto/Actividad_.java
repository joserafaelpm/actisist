package ufps.edu.co.dto;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.ActividadAcademica;
import ufps.edu.co.dto.ActividadAnexos;
import ufps.edu.co.dto.ActividadInstitucion;
import ufps.edu.co.dto.InvolucradosActividad;
import ufps.edu.co.dto.Proyecto;
import ufps.edu.co.dto.TipoActividad;
import ufps.edu.co.dto.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:03")
@StaticMetamodel(Actividad.class)
public class Actividad_ { 

    public static volatile SingularAttribute<Actividad, String> descripcion;
    public static volatile ListAttribute<Actividad, ActividadAcademica> actividadAcademicaList;
    public static volatile SingularAttribute<Actividad, String> lugar;
    public static volatile SingularAttribute<Actividad, Usuario> usuarioDni;
    public static volatile SingularAttribute<Actividad, TipoActividad> tipoActividadId;
    public static volatile SingularAttribute<Actividad, String> nombre;
    public static volatile SingularAttribute<Actividad, Date> fechaFin;
    public static volatile ListAttribute<Actividad, Proyecto> proyectoList;
    public static volatile SingularAttribute<Actividad, String> tematica;
    public static volatile SingularAttribute<Actividad, Date> fechaInicio;
    public static volatile ListAttribute<Actividad, ActividadInstitucion> actividadInstitucionList;
    public static volatile ListAttribute<Actividad, ActividadAnexos> actividadAnexosList;
    public static volatile SingularAttribute<Actividad, Integer> id;
    public static volatile SingularAttribute<Actividad, Integer> semestre;
    public static volatile ListAttribute<Actividad, InvolucradosActividad> involucradosActividadList;

}