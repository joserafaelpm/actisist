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
@Table(name = "tipo_docente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocente.findAll", query = "SELECT t FROM TipoDocente t")
    , @NamedQuery(name = "TipoDocente.findById", query = "SELECT t FROM TipoDocente t WHERE t.id = :id")
    , @NamedQuery(name = "TipoDocente.findByTipo", query = "SELECT t FROM TipoDocente t WHERE t.tipo = :tipo")})
public class TipoDocente implements Serializable, Tipo {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocente")
    private List<Docente> docenteList;

    public TipoDocente() {
    }

    public TipoDocente(Integer id) {
        this.id = id;
    }

    public TipoDocente(Integer id, String tipo) {
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
    public List<Docente> getDocenteList() {
        return docenteList;
    }

    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
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
        if (!(object instanceof TipoDocente)) {
            return false;
        }
        TipoDocente other = (TipoDocente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.TipoDocente[ id=" + id + " ]";
    }
    
}
