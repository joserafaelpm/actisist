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
@Table(name = "involucrados_actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvolucradosActividad.findAll", query = "SELECT i FROM InvolucradosActividad i")
    , @NamedQuery(name = "InvolucradosActividad.findById", query = "SELECT i FROM InvolucradosActividad i WHERE i.id = :id")
    , @NamedQuery(name = "InvolucradosActividad.findByCantDocTC", query = "SELECT i FROM InvolucradosActividad i WHERE i.cantDocTC = :cantDocTC")
    , @NamedQuery(name = "InvolucradosActividad.findByCantDocC", query = "SELECT i FROM InvolucradosActividad i WHERE i.cantDocC = :cantDocC")
    , @NamedQuery(name = "InvolucradosActividad.findByCantDocCTP", query = "SELECT i FROM InvolucradosActividad i WHERE i.cantDocCTP = :cantDocCTP")
    , @NamedQuery(name = "InvolucradosActividad.findByCantEst", query = "SELECT i FROM InvolucradosActividad i WHERE i.cantEst = :cantEst")})
public class InvolucradosActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cantDocTC")
    private Integer cantDocTC;
    @Column(name = "cantDocC")
    private Integer cantDocC;
    @Column(name = "cantDocCTP")
    private Integer cantDocCTP;
    @Column(name = "cantEst")
    private Integer cantEst;
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Actividad actividadId;

    public InvolucradosActividad() {
    }

    public InvolucradosActividad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Actividad getActividadId() {
        return actividadId;
    }

    public void setActividadId(Actividad actividadId) {
        this.actividadId = actividadId;
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
        if (!(object instanceof InvolucradosActividad)) {
            return false;
        }
        InvolucradosActividad other = (InvolucradosActividad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.InvolucradosActividad[ id=" + id + " ]";
    }
    
}
