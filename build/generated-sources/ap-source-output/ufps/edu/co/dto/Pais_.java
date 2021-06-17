package ufps.edu.co.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.Conferencista;
import ufps.edu.co.dto.PaisInstitucion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:02")
@StaticMetamodel(Pais.class)
public class Pais_ { 

    public static volatile SingularAttribute<Pais, String> iso;
    public static volatile ListAttribute<Pais, Conferencista> conferencistaList;
    public static volatile SingularAttribute<Pais, Integer> id;
    public static volatile ListAttribute<Pais, PaisInstitucion> paisInstitucionList;
    public static volatile SingularAttribute<Pais, String> nombre;

}