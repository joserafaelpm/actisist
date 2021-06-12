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
@Table(name = "proyecto_entregable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProyectoEntregable.findAll", query = "SELECT p FROM ProyectoEntregable p")
    , @NamedQuery(name = "ProyectoEntregable.findById", query = "SELECT p FROM ProyectoEntregable p WHERE p.id = :id")
    , @NamedQuery(name = "ProyectoEntregable.findByEntregable", query = "SELECT p FROM ProyectoEntregable p WHERE p.entregable = :entregable")})
public class ProyectoEntregable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "entregable")
    private String entregable;
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proyecto proyectoId;

    public ProyectoEntregable() {
    }

    public ProyectoEntregable(Integer id) {
        this.id = id;
    }

    public ProyectoEntregable(Integer id, String entregable) {
        this.id = id;
        this.entregable = entregable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntregable() {
        return entregable;
    }

    public void setEntregable(String entregable) {
        this.entregable = entregable;
    }

    public Proyecto getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Proyecto proyectoId) {
        this.proyectoId = proyectoId;
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
        if (!(object instanceof ProyectoEntregable)) {
            return false;
        }
        ProyectoEntregable other = (ProyectoEntregable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.ProyectoEntregable[ id=" + id + " ]";
    }
    
}
