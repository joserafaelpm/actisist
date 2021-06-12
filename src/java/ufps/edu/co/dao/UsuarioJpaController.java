/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ufps.edu.co.dto.Docente;
import ufps.edu.co.dto.Conferencista;
import ufps.edu.co.dto.Rol;
import ufps.edu.co.dto.Titulo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dao.exceptions.PreexistingEntityException;
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.ConferencistaActividad;
import ufps.edu.co.dto.Usuario;

/**
 *
 * @author dunke
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getTituloList() == null) {
            usuario.setTituloList(new ArrayList<Titulo>());
        }
        if (usuario.getActividadList() == null) {
            usuario.setActividadList(new ArrayList<Actividad>());
        }
        if (usuario.getConferencistaActividadList() == null) {
            usuario.setConferencistaActividadList(new ArrayList<ConferencistaActividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente docente = usuario.getDocente();
            if (docente != null) {
                docente = em.getReference(docente.getClass(), docente.getUsuarioDni());
                usuario.setDocente(docente);
            }
            Conferencista conferencista = usuario.getConferencista();
            if (conferencista != null) {
                conferencista = em.getReference(conferencista.getClass(), conferencista.getUsuarioDni());
                usuario.setConferencista(conferencista);
            }
            Rol idRol = usuario.getIdRol();
            if (idRol != null) {
                idRol = em.getReference(idRol.getClass(), idRol.getId());
                usuario.setIdRol(idRol);
            }
            List<Titulo> attachedTituloList = new ArrayList<Titulo>();
            for (Titulo tituloListTituloToAttach : usuario.getTituloList()) {
                tituloListTituloToAttach = em.getReference(tituloListTituloToAttach.getClass(), tituloListTituloToAttach.getId());
                attachedTituloList.add(tituloListTituloToAttach);
            }
            usuario.setTituloList(attachedTituloList);
            List<Actividad> attachedActividadList = new ArrayList<Actividad>();
            for (Actividad actividadListActividadToAttach : usuario.getActividadList()) {
                actividadListActividadToAttach = em.getReference(actividadListActividadToAttach.getClass(), actividadListActividadToAttach.getId());
                attachedActividadList.add(actividadListActividadToAttach);
            }
            usuario.setActividadList(attachedActividadList);
            List<ConferencistaActividad> attachedConferencistaActividadList = new ArrayList<ConferencistaActividad>();
            for (ConferencistaActividad conferencistaActividadListConferencistaActividadToAttach : usuario.getConferencistaActividadList()) {
                conferencistaActividadListConferencistaActividadToAttach = em.getReference(conferencistaActividadListConferencistaActividadToAttach.getClass(), conferencistaActividadListConferencistaActividadToAttach.getId());
                attachedConferencistaActividadList.add(conferencistaActividadListConferencistaActividadToAttach);
            }
            usuario.setConferencistaActividadList(attachedConferencistaActividadList);
            em.persist(usuario);
            if (docente != null) {
                Usuario oldUsuarioOfDocente = docente.getUsuario();
                if (oldUsuarioOfDocente != null) {
                    oldUsuarioOfDocente.setDocente(null);
                    oldUsuarioOfDocente = em.merge(oldUsuarioOfDocente);
                }
                docente.setUsuario(usuario);
                docente = em.merge(docente);
            }
            if (conferencista != null) {
                Usuario oldUsuarioOfConferencista = conferencista.getUsuario();
                if (oldUsuarioOfConferencista != null) {
                    oldUsuarioOfConferencista.setConferencista(null);
                    oldUsuarioOfConferencista = em.merge(oldUsuarioOfConferencista);
                }
                conferencista.setUsuario(usuario);
                conferencista = em.merge(conferencista);
            }
            if (idRol != null) {
                idRol.getUsuarioList().add(usuario);
                idRol = em.merge(idRol);
            }
            for (Titulo tituloListTitulo : usuario.getTituloList()) {
                Usuario oldUsuarioDniOfTituloListTitulo = tituloListTitulo.getUsuarioDni();
                tituloListTitulo.setUsuarioDni(usuario);
                tituloListTitulo = em.merge(tituloListTitulo);
                if (oldUsuarioDniOfTituloListTitulo != null) {
                    oldUsuarioDniOfTituloListTitulo.getTituloList().remove(tituloListTitulo);
                    oldUsuarioDniOfTituloListTitulo = em.merge(oldUsuarioDniOfTituloListTitulo);
                }
            }
            for (Actividad actividadListActividad : usuario.getActividadList()) {
                Usuario oldUsuarioDniOfActividadListActividad = actividadListActividad.getUsuarioDni();
                actividadListActividad.setUsuarioDni(usuario);
                actividadListActividad = em.merge(actividadListActividad);
                if (oldUsuarioDniOfActividadListActividad != null) {
                    oldUsuarioDniOfActividadListActividad.getActividadList().remove(actividadListActividad);
                    oldUsuarioDniOfActividadListActividad = em.merge(oldUsuarioDniOfActividadListActividad);
                }
            }
            for (ConferencistaActividad conferencistaActividadListConferencistaActividad : usuario.getConferencistaActividadList()) {
                Usuario oldUsuarioDniOfConferencistaActividadListConferencistaActividad = conferencistaActividadListConferencistaActividad.getUsuarioDni();
                conferencistaActividadListConferencistaActividad.setUsuarioDni(usuario);
                conferencistaActividadListConferencistaActividad = em.merge(conferencistaActividadListConferencistaActividad);
                if (oldUsuarioDniOfConferencistaActividadListConferencistaActividad != null) {
                    oldUsuarioDniOfConferencistaActividadListConferencistaActividad.getConferencistaActividadList().remove(conferencistaActividadListConferencistaActividad);
                    oldUsuarioDniOfConferencistaActividadListConferencistaActividad = em.merge(oldUsuarioDniOfConferencistaActividadListConferencistaActividad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getDni()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getDni());
            Docente docenteOld = persistentUsuario.getDocente();
            Docente docenteNew = usuario.getDocente();
            Conferencista conferencistaOld = persistentUsuario.getConferencista();
            Conferencista conferencistaNew = usuario.getConferencista();
            Rol idRolOld = persistentUsuario.getIdRol();
            Rol idRolNew = usuario.getIdRol();
            List<Titulo> tituloListOld = persistentUsuario.getTituloList();
            List<Titulo> tituloListNew = usuario.getTituloList();
            List<Actividad> actividadListOld = persistentUsuario.getActividadList();
            List<Actividad> actividadListNew = usuario.getActividadList();
            List<ConferencistaActividad> conferencistaActividadListOld = persistentUsuario.getConferencistaActividadList();
            List<ConferencistaActividad> conferencistaActividadListNew = usuario.getConferencistaActividadList();
            List<String> illegalOrphanMessages = null;
            if (docenteOld != null && !docenteOld.equals(docenteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Docente " + docenteOld + " since its usuario field is not nullable.");
            }
            if (conferencistaOld != null && !conferencistaOld.equals(conferencistaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Conferencista " + conferencistaOld + " since its usuario field is not nullable.");
            }
            for (Titulo tituloListOldTitulo : tituloListOld) {
                if (!tituloListNew.contains(tituloListOldTitulo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Titulo " + tituloListOldTitulo + " since its usuarioDni field is not nullable.");
                }
            }
            for (Actividad actividadListOldActividad : actividadListOld) {
                if (!actividadListNew.contains(actividadListOldActividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actividad " + actividadListOldActividad + " since its usuarioDni field is not nullable.");
                }
            }
            for (ConferencistaActividad conferencistaActividadListOldConferencistaActividad : conferencistaActividadListOld) {
                if (!conferencistaActividadListNew.contains(conferencistaActividadListOldConferencistaActividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ConferencistaActividad " + conferencistaActividadListOldConferencistaActividad + " since its usuarioDni field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (docenteNew != null) {
                docenteNew = em.getReference(docenteNew.getClass(), docenteNew.getUsuarioDni());
                usuario.setDocente(docenteNew);
            }
            if (conferencistaNew != null) {
                conferencistaNew = em.getReference(conferencistaNew.getClass(), conferencistaNew.getUsuarioDni());
                usuario.setConferencista(conferencistaNew);
            }
            if (idRolNew != null) {
                idRolNew = em.getReference(idRolNew.getClass(), idRolNew.getId());
                usuario.setIdRol(idRolNew);
            }
            List<Titulo> attachedTituloListNew = new ArrayList<Titulo>();
            for (Titulo tituloListNewTituloToAttach : tituloListNew) {
                tituloListNewTituloToAttach = em.getReference(tituloListNewTituloToAttach.getClass(), tituloListNewTituloToAttach.getId());
                attachedTituloListNew.add(tituloListNewTituloToAttach);
            }
            tituloListNew = attachedTituloListNew;
            usuario.setTituloList(tituloListNew);
            List<Actividad> attachedActividadListNew = new ArrayList<Actividad>();
            for (Actividad actividadListNewActividadToAttach : actividadListNew) {
                actividadListNewActividadToAttach = em.getReference(actividadListNewActividadToAttach.getClass(), actividadListNewActividadToAttach.getId());
                attachedActividadListNew.add(actividadListNewActividadToAttach);
            }
            actividadListNew = attachedActividadListNew;
            usuario.setActividadList(actividadListNew);
            List<ConferencistaActividad> attachedConferencistaActividadListNew = new ArrayList<ConferencistaActividad>();
            for (ConferencistaActividad conferencistaActividadListNewConferencistaActividadToAttach : conferencistaActividadListNew) {
                conferencistaActividadListNewConferencistaActividadToAttach = em.getReference(conferencistaActividadListNewConferencistaActividadToAttach.getClass(), conferencistaActividadListNewConferencistaActividadToAttach.getId());
                attachedConferencistaActividadListNew.add(conferencistaActividadListNewConferencistaActividadToAttach);
            }
            conferencistaActividadListNew = attachedConferencistaActividadListNew;
            usuario.setConferencistaActividadList(conferencistaActividadListNew);
            usuario = em.merge(usuario);
            if (docenteNew != null && !docenteNew.equals(docenteOld)) {
                Usuario oldUsuarioOfDocente = docenteNew.getUsuario();
                if (oldUsuarioOfDocente != null) {
                    oldUsuarioOfDocente.setDocente(null);
                    oldUsuarioOfDocente = em.merge(oldUsuarioOfDocente);
                }
                docenteNew.setUsuario(usuario);
                docenteNew = em.merge(docenteNew);
            }
            if (conferencistaNew != null && !conferencistaNew.equals(conferencistaOld)) {
                Usuario oldUsuarioOfConferencista = conferencistaNew.getUsuario();
                if (oldUsuarioOfConferencista != null) {
                    oldUsuarioOfConferencista.setConferencista(null);
                    oldUsuarioOfConferencista = em.merge(oldUsuarioOfConferencista);
                }
                conferencistaNew.setUsuario(usuario);
                conferencistaNew = em.merge(conferencistaNew);
            }
            if (idRolOld != null && !idRolOld.equals(idRolNew)) {
                idRolOld.getUsuarioList().remove(usuario);
                idRolOld = em.merge(idRolOld);
            }
            if (idRolNew != null && !idRolNew.equals(idRolOld)) {
                idRolNew.getUsuarioList().add(usuario);
                idRolNew = em.merge(idRolNew);
            }
            for (Titulo tituloListNewTitulo : tituloListNew) {
                if (!tituloListOld.contains(tituloListNewTitulo)) {
                    Usuario oldUsuarioDniOfTituloListNewTitulo = tituloListNewTitulo.getUsuarioDni();
                    tituloListNewTitulo.setUsuarioDni(usuario);
                    tituloListNewTitulo = em.merge(tituloListNewTitulo);
                    if (oldUsuarioDniOfTituloListNewTitulo != null && !oldUsuarioDniOfTituloListNewTitulo.equals(usuario)) {
                        oldUsuarioDniOfTituloListNewTitulo.getTituloList().remove(tituloListNewTitulo);
                        oldUsuarioDniOfTituloListNewTitulo = em.merge(oldUsuarioDniOfTituloListNewTitulo);
                    }
                }
            }
            for (Actividad actividadListNewActividad : actividadListNew) {
                if (!actividadListOld.contains(actividadListNewActividad)) {
                    Usuario oldUsuarioDniOfActividadListNewActividad = actividadListNewActividad.getUsuarioDni();
                    actividadListNewActividad.setUsuarioDni(usuario);
                    actividadListNewActividad = em.merge(actividadListNewActividad);
                    if (oldUsuarioDniOfActividadListNewActividad != null && !oldUsuarioDniOfActividadListNewActividad.equals(usuario)) {
                        oldUsuarioDniOfActividadListNewActividad.getActividadList().remove(actividadListNewActividad);
                        oldUsuarioDniOfActividadListNewActividad = em.merge(oldUsuarioDniOfActividadListNewActividad);
                    }
                }
            }
            for (ConferencistaActividad conferencistaActividadListNewConferencistaActividad : conferencistaActividadListNew) {
                if (!conferencistaActividadListOld.contains(conferencistaActividadListNewConferencistaActividad)) {
                    Usuario oldUsuarioDniOfConferencistaActividadListNewConferencistaActividad = conferencistaActividadListNewConferencistaActividad.getUsuarioDni();
                    conferencistaActividadListNewConferencistaActividad.setUsuarioDni(usuario);
                    conferencistaActividadListNewConferencistaActividad = em.merge(conferencistaActividadListNewConferencistaActividad);
                    if (oldUsuarioDniOfConferencistaActividadListNewConferencistaActividad != null && !oldUsuarioDniOfConferencistaActividadListNewConferencistaActividad.equals(usuario)) {
                        oldUsuarioDniOfConferencistaActividadListNewConferencistaActividad.getConferencistaActividadList().remove(conferencistaActividadListNewConferencistaActividad);
                        oldUsuarioDniOfConferencistaActividadListNewConferencistaActividad = em.merge(oldUsuarioDniOfConferencistaActividadListNewConferencistaActividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuario.getDni();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getDni();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Docente docenteOrphanCheck = usuario.getDocente();
            if (docenteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Docente " + docenteOrphanCheck + " in its docente field has a non-nullable usuario field.");
            }
            Conferencista conferencistaOrphanCheck = usuario.getConferencista();
            if (conferencistaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Conferencista " + conferencistaOrphanCheck + " in its conferencista field has a non-nullable usuario field.");
            }
            List<Titulo> tituloListOrphanCheck = usuario.getTituloList();
            for (Titulo tituloListOrphanCheckTitulo : tituloListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Titulo " + tituloListOrphanCheckTitulo + " in its tituloList field has a non-nullable usuarioDni field.");
            }
            List<Actividad> actividadListOrphanCheck = usuario.getActividadList();
            for (Actividad actividadListOrphanCheckActividad : actividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Actividad " + actividadListOrphanCheckActividad + " in its actividadList field has a non-nullable usuarioDni field.");
            }
            List<ConferencistaActividad> conferencistaActividadListOrphanCheck = usuario.getConferencistaActividadList();
            for (ConferencistaActividad conferencistaActividadListOrphanCheckConferencistaActividad : conferencistaActividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the ConferencistaActividad " + conferencistaActividadListOrphanCheckConferencistaActividad + " in its conferencistaActividadList field has a non-nullable usuarioDni field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Rol idRol = usuario.getIdRol();
            if (idRol != null) {
                idRol.getUsuarioList().remove(usuario);
                idRol = em.merge(idRol);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
