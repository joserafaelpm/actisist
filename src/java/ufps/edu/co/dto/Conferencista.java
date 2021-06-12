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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dunke
 */
@Entity
@Table(name = "conferencista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conferencista.findAll", query = "SELECT c FROM Conferencista c")
    , @NamedQuery(name = "Conferencista.findByUsuarioDni", query = "SELECT c FROM Conferencista c WHERE c.usuarioDni = :usuarioDni")})
public class Conferencista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "usuario_dni")
    private Long usuarioDni;
    @JoinColumn(name = "pais_origen", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pais paisOrigen;
    @JoinColumn(name = "institucion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Institucion institucionId;
    @JoinColumn(name = "usuario_dni", referencedColumnName = "dni", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "tipo_conferencista_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoConferencista tipoConferencistaId;

    public Conferencista() {
    }

    public Conferencista(Long usuarioDni) {
        this.usuarioDni = usuarioDni;
    }

    public Long getUsuarioDni() {
        return usuarioDni;
    }

    public void setUsuarioDni(Long usuarioDni) {
        this.usuarioDni = usuarioDni;
    }

    public Pais getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(Pais paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public Institucion getInstitucionId() {
        return institucionId;
    }

    public void setInstitucionId(Institucion institucionId) {
        this.institucionId = institucionId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoConferencista getTipoConferencistaId() {
        return tipoConferencistaId;
    }

    public void setTipoConferencistaId(TipoConferencista tipoConferencistaId) {
        this.tipoConferencistaId = tipoConferencistaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioDni != null ? usuarioDni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conferencista)) {
            return false;
        }
        Conferencista other = (Conferencista) object;
        if ((this.usuarioDni == null && other.usuarioDni != null) || (this.usuarioDni != null && !this.usuarioDni.equals(other.usuarioDni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.Conferencista[ usuarioDni=" + usuarioDni + " ]";
    }
    
}
