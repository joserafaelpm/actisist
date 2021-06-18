/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.dto;

import ufps.edu.co.negocio.Tipo;
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
@Table(name = "tipo_actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoActividad.findAll", query = "SELECT t FROM TipoActividad t")
    , @NamedQuery(name = "TipoActividad.findById", query = "SELECT t FROM TipoActividad t WHERE t.id = :id")
    , @NamedQuery(name = "TipoActividad.findByTipo", query = "SELECT t FROM TipoActividad t WHERE t.tipo = :tipo")})
public class TipoActividad implements Serializable, Tipo {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoActividadId")
    private List<Actividad> actividadList;

    public TipoActividad() {
    }

    public TipoActividad(Integer id) {
        this.id = id;
    }

    public TipoActividad(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
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
        if (!(object instanceof TipoActividad)) {
            return false;
        }
        TipoActividad other = (TipoActividad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.TipoActividad[ id=" + id + " ]";
    }
    
}
