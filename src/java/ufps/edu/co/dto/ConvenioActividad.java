/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dunke
 */
@Entity
@Table(name = "convenio_actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConvenioActividad.findAll", query = "SELECT c FROM ConvenioActividad c")
    , @NamedQuery(name = "ConvenioActividad.findById", query = "SELECT c FROM ConvenioActividad c WHERE c.id = :id")})
public class ConvenioActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Actividad actividadId;
    @JoinColumn(name = "convenio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Convenio convenioId;

    public ConvenioActividad() {
    }

    public ConvenioActividad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Actividad getActividadId() {
        return actividadId;
    }

    public void setActividadId(Actividad actividadId) {
        this.actividadId = actividadId;
    }

    public Convenio getConvenioId() {
        return convenioId;
    }

    public void setConvenioId(Convenio convenioId) {
        this.convenioId = convenioId;
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
        if (!(object instanceof ConvenioActividad)) {
            return false;
        }
        ConvenioActividad other = (ConvenioActividad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.ConvenioActividad[ id=" + id + " ]";
    }
    
}
