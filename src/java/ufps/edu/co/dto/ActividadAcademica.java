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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "actividad_academica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadAcademica.findAll", query = "SELECT a FROM ActividadAcademica a")
    , @NamedQuery(name = "ActividadAcademica.findById", query = "SELECT a FROM ActividadAcademica a WHERE a.id = :id")})
public class ActividadAcademica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadAcademicaId")
    private List<Horario> horarioList;
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Actividad actividadId;
    @JoinColumn(name = "tipo_movilidad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoMovilidad tipoMovilidadId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadAcademicaId")
    private List<ConvenioActividad> convenioActividadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadAcademicaId")
    private List<ConferencistaActividad> conferencistaActividadList;

    public ActividadAcademica() {
    }

    public ActividadAcademica(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    public Actividad getActividadId() {
        return actividadId;
    }

    public void setActividadId(Actividad actividadId) {
        this.actividadId = actividadId;
    }

    public TipoMovilidad getTipoMovilidadId() {
        return tipoMovilidadId;
    }

    public void setTipoMovilidadId(TipoMovilidad tipoMovilidadId) {
        this.tipoMovilidadId = tipoMovilidadId;
    }

    @XmlTransient
    public List<ConvenioActividad> getConvenioActividadList() {
        return convenioActividadList;
    }

    public void setConvenioActividadList(List<ConvenioActividad> convenioActividadList) {
        this.convenioActividadList = convenioActividadList;
    }

    @XmlTransient
    public List<ConferencistaActividad> getConferencistaActividadList() {
        return conferencistaActividadList;
    }

    public void setConferencistaActividadList(List<ConferencistaActividad> conferencistaActividadList) {
        this.conferencistaActividadList = conferencistaActividadList;
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
        if (!(object instanceof ActividadAcademica)) {
            return false;
        }
        ActividadAcademica other = (ActividadAcademica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.ActividadAcademica[ id=" + id + " ]";
    }
    
}
