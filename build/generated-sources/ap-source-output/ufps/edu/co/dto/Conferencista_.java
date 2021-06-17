package ufps.edu.co.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.Institucion;
import ufps.edu.co.dto.Pais;
import ufps.edu.co.dto.TipoConferencista;
import ufps.edu.co.dto.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:02")
@StaticMetamodel(Conferencista.class)
public class Conferencista_ { 

    public static volatile SingularAttribute<Conferencista, Institucion> institucionId;
    public static volatile SingularAttribute<Conferencista, TipoConferencista> tipoConferencistaId;
    public static volatile SingularAttribute<Conferencista, Long> usuarioDni;
    public static volatile SingularAttribute<Conferencista, Usuario> usuario;
    public static volatile SingularAttribute<Conferencista, Pais> paisOrigen;

}