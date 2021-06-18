/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dunke
 */
@Entity
@Table(name = "institucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i")
    , @NamedQuery(name = "Institucion.findById", query = "SELECT i FROM Institucion i WHERE i.id = :id")
    , @NamedQuery(name = "Institucion.findByNombre", query = "SELECT i FROM Institucion i WHERE i.nombre = :nombre")})
public class Institucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "empresa")
    private List<Convenio> convenioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionId")
    private List<Conferencista> conferencistaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionId")
    private List<PaisInstitucion> paisInstitucionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionId")
    private List<ActividadInstitucion> actividadInstitucionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadId")
    private List<ActividadInstitucion> actividadInstitucionList1;

    public Institucion() {
    }

    public Institucion(Integer id) {
        this.id = id;
    }

    public Institucion(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Convenio> getConvenioList() {
        return convenioList;
    }

    public void setConvenioList(List<Convenio> convenioList) {
        this.convenioList = convenioList;
    }

    @XmlTransient
    public List<Conferencista> getConferencistaList() {
        return conferencistaList;
    }

    public void setConferencistaList(List<Conferencista> conferencistaList) {
        this.conferencistaList = conferencistaList;
    }

    @XmlTransient
    public List<PaisInstitucion> getPaisInstitucionList() {
        return paisInstitucionList;
    }

    public void setPaisInstitucionList(List<PaisInstitucion> paisInstitucionList) {
        this.paisInstitucionList = paisInstitucionList;
    }

    @XmlTransient
    public List<ActividadInstitucion> getActividadInstitucionList() {
        return actividadInstitucionList;
    }

    public void setActividadInstitucionList(List<ActividadInstitucion> actividadInstitucionList) {
        this.actividadInstitucionList = actividadInstitucionList;
    }

    @XmlTransient
    public List<ActividadInstitucion> getActividadInstitucionList1() {
        return actividadInstitucionList1;
    }

    public void setActividadInstitucionList1(List<ActividadInstitucion> actividadInstitucionList1) {
        this.actividadInstitucionList1 = actividadInstitucionList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.Institucion[ id=" + id + " ]";
    }
    
}
