package ufps.edu.co.dto;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ufps.edu.co.dto.Proyecto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-16T21:47:02")
@StaticMetamodel(Contrato.class)
public class Contrato_ { 

    public static volatile SingularAttribute<Contrato, Date> fecha;
    public static volatile ListAttribute<Contrato, Proyecto> proyectoList;
    public static volatile SingularAttribute<Contrato, Integer> numero;
    public static volatile SingularAttribute<Contrato, Integer> id;

}