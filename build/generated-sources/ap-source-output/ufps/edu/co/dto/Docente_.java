package ufps.edu.co.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.TipoDocente;
import ufps.edu.co.dto.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:03")
@StaticMetamodel(Docente.class)
public class Docente_ { 

    public static volatile SingularAttribute<Docente, TipoDocente> idTipoDocente;
    public static volatile SingularAttribute<Docente, Long> codigo;
    public static volatile SingularAttribute<Docente, Long> usuarioDni;
    public static volatile SingularAttribute<Docente, byte[]> imagenPerfil;
    public static volatile SingularAttribute<Docente, Usuario> usuario;
    public static volatile SingularAttribute<Docente, Boolean> activo;

}