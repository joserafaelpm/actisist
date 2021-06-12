/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.dto;

import java.io.Serializable;
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
@Table(name = "convenio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Convenio.findAll", query = "SELECT c FROM Convenio c")
    , @NamedQuery(name = "Convenio.findById", query = "SELECT c FROM Convenio c WHERE c.id = :id")
    , @NamedQuery(name = "Convenio.findByNumero", query = "SELECT c FROM Convenio c WHERE c.numero = :numero")
    , @NamedQuery(name = "Convenio.findByFecha", query = "SELECT c FROM Convenio c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Convenio.findByVigencia", query = "SELECT c FROM Convenio c WHERE c.vigencia = :vigencia")
    , @NamedQuery(name = "Convenio.findByDescripcion", query = "SELECT c FROM Convenio c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Convenio.findByRazon", query = "SELECT c FROM Convenio c WHERE c.razon = :razon")})
public class Convenio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "vigencia")
    @Temporal(TemporalType.DATE)
    private Date vigencia;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "razon")
    private String razon;
    @JoinColumn(name = "empresa", referencedColumnName = "id")
    @ManyToOne
    private Institucion empresa;
    @JoinColumn(name = "tipo_convenio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoConvenio tipoConvenio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "convenioId")
    private List<ConvenioActividad> convenioActividadList;

    public Convenio() {
    }

    public Convenio(Integer id) {
        this.id = id;
    }

    public Convenio(Integer id, int numero) {
        this.id = id;
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public Institucion getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Institucion empresa) {
        this.empresa = empresa;
    }

    public TipoConvenio getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(TipoConvenio tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
    }

    @XmlTransient
    public List<ConvenioActividad> getConvenioActividadList() {
        return convenioActividadList;
    }

    public void setConvenioActividadList(List<ConvenioActividad> convenioActividadList) {
        this.convenioActividadList = convenioActividadList;
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
        if (!(object instanceof Convenio)) {
            return false;
        }
        Convenio other = (Convenio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.Convenio[ id=" + id + " ]";
    }
    
}
