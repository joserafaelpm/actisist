/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.dto;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dunke
 */
@Entity
@Table(name = "actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a")
    , @NamedQuery(name = "Actividad.findById", query = "SELECT a FROM Actividad a WHERE a.id = :id")
    , @NamedQuery(name = "Actividad.findByNombre", query = "SELECT a FROM Actividad a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Actividad.findByTematica", query = "SELECT a FROM Actividad a WHERE a.tematica = :tematica")
    , @NamedQuery(name = "Actividad.findByDescripcion", query = "SELECT a FROM Actividad a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Actividad.findBySemestre", query = "SELECT a FROM Actividad a WHERE a.semestre = :semestre")
    , @NamedQuery(name = "Actividad.findByLugar", query = "SELECT a FROM Actividad a WHERE a.lugar = :lugar")
    , @NamedQuery(name = "Actividad.findByFechaInicio", query = "SELECT a FROM Actividad a WHERE a.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Actividad.findByFechaFin", query = "SELECT a FROM Actividad a WHERE a.fechaFin = :fechaFin")})
public class Actividad implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "tematica")
    private String tematica;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "semestre")
    private int semestre;
    @Basic(optional = false)
    @Column(name = "lugar")
    private String lugar;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadId")
    private List<Horario> horarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadId")
    private List<ConvenioActividad> convenioActividadList;
    @JoinColumn(name = "usuario_dni", referencedColumnName = "dni")
    @ManyToOne(optional = false)
    private Usuario usuarioDni;
    @JoinColumn(name = "tipo_movilidad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoMovilidad tipoMovilidadId;
    @JoinColumn(name = "tipo_actividad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoActividad tipoActividadId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadId")
    private List<ActividadAnexos> actividadAnexosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadId")
    private List<ConferencistaActividad> conferencistaActividadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadId")
    private List<InvolucradosActividad> involucradosActividadList;

    public Actividad() {
    }

    public Actividad(Integer id) {
        this.id = id;
    }

    public Actividad(Integer id, String nombre, String tematica, String descripcion, int semestre, String lugar) {
        this.id = id;
        this.nombre = nombre;
        this.tematica = tematica;
        this.descripcion = descripcion;
        this.semestre = semestre;
        this.lugar = lugar;
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

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    @XmlTransient
    public List<ConvenioActividad> getConvenioActividadList() {
        return convenioActividadList;
    }

    public void setConvenioActividadList(List<ConvenioActividad> convenioActividadList) {
        this.convenioActividadList = convenioActividadList;
    }

    public Usuario getUsuarioDni() {
        return usuarioDni;
    }

    public void setUsuarioDni(Usuario usuarioDni) {
        this.usuarioDni = usuarioDni;
    }

    public TipoMovilidad getTipoMovilidadId() {
        return tipoMovilidadId;
    }

    public void setTipoMovilidadId(TipoMovilidad tipoMovilidadId) {
        this.tipoMovilidadId = tipoMovilidadId;
    }

    public TipoActividad getTipoActividadId() {
        return tipoActividadId;
    }

    public void setTipoActividadId(TipoActividad tipoActividadId) {
        this.tipoActividadId = tipoActividadId;
    }

    @XmlTransient
    public List<ActividadAnexos> getActividadAnexosList() {
        return actividadAnexosList;
    }

    public void setActividadAnexosList(List<ActividadAnexos> actividadAnexosList) {
        this.actividadAnexosList = actividadAnexosList;
    }

    @XmlTransient
    public List<ConferencistaActividad> getConferencistaActividadList() {
        return conferencistaActividadList;
    }

    public void setConferencistaActividadList(List<ConferencistaActividad> conferencistaActividadList) {
        this.conferencistaActividadList = conferencistaActividadList;
    }

    @XmlTransient
    public List<InvolucradosActividad> getInvolucradosActividadList() {
        return involucradosActividadList;
    }

    public void setInvolucradosActividadList(List<InvolucradosActividad> involucradosActividadList) {
        this.involucradosActividadList = involucradosActividadList;
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
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.Actividad[ id=" + id + " ]";
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    public String encodeImage(){
        String encodedImage=Base64.getEncoder().encodeToString(this.imagen);
        return "data:image/jpg;base64,"+encodedImage;
    }
}
