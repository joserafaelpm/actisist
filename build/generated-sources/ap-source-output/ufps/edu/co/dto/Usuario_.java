package ufps.edu.co.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.Conferencista;
import ufps.edu.co.dto.ConferencistaActividad;
import ufps.edu.co.dto.Docente;
import ufps.edu.co.dto.Rol;
import ufps.edu.co.dto.Titulo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:03")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile ListAttribute<Usuario, Titulo> tituloList;
    public static volatile ListAttribute<Usuario, Actividad> actividadList;
    public static volatile SingularAttribute<Usuario, Rol> idRol;
    public static volatile SingularAttribute<Usuario, String> apellido;
    public static volatile SingularAttribute<Usuario, String> correo;
    public static volatile SingularAttribute<Usuario, Docente> docente;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile ListAttribute<Usuario, ConferencistaActividad> conferencistaActividadList;
    public static volatile SingularAttribute<Usuario, Long> dni;
    public static volatile SingularAttribute<Usuario, Conferencista> conferencista;
    public static volatile SingularAttribute<Usuario, String> contraseña;

}