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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dunke
 */
@Entity
@Table(name = "involucrados_actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvolucradosActividad.findAll", query = "SELECT i FROM InvolucradosActividad i")
    , @NamedQuery(name = "InvolucradosActividad.findByActividadId", query = "SELECT i FROM InvolucradosActividad i WHERE i.actividadId = :actividadId")
    , @NamedQuery(name = "InvolucradosActividad.findByCantDocTC", query = "SELECT i FROM InvolucradosActividad i WHERE i.cantDocTC = :cantDocTC")
    , @NamedQuery(name = "InvolucradosActividad.findByCantDocC", query = "SELECT i FROM InvolucradosActividad i WHERE i.cantDocC = :cantDocC")
    , @NamedQuery(name = "InvolucradosActividad.findByCantDocCTP", query = "SELECT i FROM InvolucradosActividad i WHERE i.cantDocCTP = :cantDocCTP")
    , @NamedQuery(name = "InvolucradosActividad.findByCantEst", query = "SELECT i FROM InvolucradosActividad i WHERE i.cantEst = :cantEst")})
public class InvolucradosActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "actividad_id")
    private Integer actividadId;
    @Column(name = "cantDocTC")
    private Integer cantDocTC;
    @Column(name = "cantDocC")
    private Integer cantDocC;
    @Column(name = "cantDocCTP")
    private Integer cantDocCTP;
    @Column(name = "cantEst")
    private Integer cantEst;
    @JoinColumn(name = "actividad_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Actividad actividad;

    public InvolucradosActividad() {
    }

    public InvolucradosActividad(Integer actividadId) {
        this.actividadId = actividadId;
    }

    public Integer getActividadId() {
        return actividadId;
    }

    public void setActividadId(Integer actividadId) {
        this.actividadId = actividadId;
    }

    public Integer getCantDocTC() {
        return cantDocTC;
    }

    public void setCantDocTC(Integer cantDocTC) {
        this.cantDocTC = cantDocTC;
    }

    public Integer getCantDocC() {
        return cantDocC;
    }

    public void setCantDocC(Integer cantDocC) {
        this.cantDocC = cantDocC;
    }

    public Integer getCantDocCTP() {
        return cantDocCTP;
    }

    public void setCantDocCTP(Integer cantDocCTP) {
        this.cantDocCTP = cantDocCTP;
    }

    public Integer getCantEst() {
        return cantEst;
    }

    public void setCantEst(Integer cantEst) {
        this.cantEst = cantEst;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actividadId != null ? actividadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvolucradosActividad)) {
            return false;
        }
        InvolucradosActividad other = (InvolucradosActividad) object;
        if ((this.actividadId == null && other.actividadId != null) || (this.actividadId != null && !this.actividadId.equals(other.actividadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.InvolucradosActividad[ actividadId=" + actividadId + " ]";
    }
    
}
