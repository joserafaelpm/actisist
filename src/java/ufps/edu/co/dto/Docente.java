/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.dto;

import java.io.Serializable;
import java.util.Base64;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "docente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d")
    , @NamedQuery(name = "Docente.findByActivo", query = "SELECT d FROM Docente d WHERE d.activo = :activo")
    , @NamedQuery(name = "Docente.findByUsuarioDni", query = "SELECT d FROM Docente d WHERE d.usuarioDni = :usuarioDni")
    , @NamedQuery(name = "Docente.findByCodigo", query = "SELECT d FROM Docente d WHERE d.codigo = :codigo")})
public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @Id
    @Basic(optional = false)
    @Column(name = "usuario_dni")
    private Long usuarioDni;
    @Basic(optional = false)
    @Column(name = "codigo")
    private long codigo;
    @Basic(optional = false)
    @Lob
    @Column(name = "imagen_perfil")
    private byte[] imagenPerfil;
    @JoinColumn(name = "usuario_dni", referencedColumnName = "dni", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "id_tipo_docente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoDocente idTipoDocente;

    public Docente() {
    }

    public Docente(Long usuarioDni) {
        this.usuarioDni = usuarioDni;
    }

    public Docente(Long usuarioDni, boolean activo, long codigo, byte[] imagenPerfil) {
        this.usuarioDni = usuarioDni;
        this.activo = activo;
        this.codigo = codigo;
        this.imagenPerfil = imagenPerfil;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Long getUsuarioDni() {
        return usuarioDni;
    }

    public void setUsuarioDni(Long usuarioDni) {
        this.usuarioDni = usuarioDni;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public byte[] getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(byte[] imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoDocente getIdTipoDocente() {
        return idTipoDocente;
    }

    public void setIdTipoDocente(TipoDocente idTipoDocente) {
        this.idTipoDocente = idTipoDocente;
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
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.usuarioDni == null && other.usuarioDni != null) || (this.usuarioDni != null && !this.usuarioDni.equals(other.usuarioDni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.Docente[ usuarioDni=" + usuarioDni + " ]";
    }
    
    public String encodeImage(){
        String encodedImage=Base64.getEncoder().encodeToString(this.imagenPerfil);
        return "data:image/jpg;base64,"+encodedImage;
    }
}
