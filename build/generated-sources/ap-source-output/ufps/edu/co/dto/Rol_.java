package ufps.edu.co.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.SolicitudRegistro;
import ufps.edu.co.dto.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:03")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile ListAttribute<Rol, Usuario> usuarioList;
    public static volatile SingularAttribute<Rol, Integer> id;
    public static volatile ListAttribute<Rol, SolicitudRegistro> solicitudRegistroList;
    public static volatile SingularAttribute<Rol, String> rol;

}