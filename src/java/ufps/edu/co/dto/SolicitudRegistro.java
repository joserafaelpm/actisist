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
@Table(name = "solicitud_registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudRegistro.findAll", query = "SELECT s FROM SolicitudRegistro s")
    , @NamedQuery(name = "SolicitudRegistro.findByToken", query = "SELECT s FROM SolicitudRegistro s WHERE s.token = :token")
    , @NamedQuery(name = "SolicitudRegistro.findByEmail", query = "SELECT s FROM SolicitudRegistro s WHERE s.email = :email")})
public class SolicitudRegistro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "type_us", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rol typeUs;

    public SolicitudRegistro() {
    }

    public SolicitudRegistro(String token) {
        this.token = token;
    }

    public SolicitudRegistro(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getTypeUs() {
        return typeUs;
    }

    public void setTypeUs(Rol typeUs) {
        this.typeUs = typeUs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (token != null ? token.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudRegistro)) {
            return false;
        }
        SolicitudRegistro other = (SolicitudRegistro) object;
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ufps.edu.co.dto.SolicitudRegistro[ token=" + token + " ]";
    }
    
}
