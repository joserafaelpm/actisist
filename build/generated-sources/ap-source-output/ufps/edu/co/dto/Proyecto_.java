package ufps.edu.co.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.Contrato;
import ufps.edu.co.dto.ProyectoEntregable;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:03")
@StaticMetamodel(Proyecto.class)
public class Proyecto_ { 

    public static volatile SingularAttribute<Proyecto, String> proponente;
    public static volatile ListAttribute<Proyecto, ProyectoEntregable> proyectoEntregableList;
    public static volatile SingularAttribute<Proyecto, Integer> id;
    public static volatile SingularAttribute<Proyecto, String> ejecutor;
    public static volatile SingularAttribute<Proyecto, Contrato> contratoId;
    public static volatile SingularAttribute<Proyecto, Actividad> actividadId;

}