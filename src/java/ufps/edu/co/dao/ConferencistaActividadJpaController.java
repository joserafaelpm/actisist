/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.Usuario;
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.ConferencistaActividad;

/**
 *
 * @author dunke
 */
public class ConferencistaActividadJpaController implements Serializable {

    public ConferencistaActividadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConferencistaActividad conferencistaActividad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioDni = conferencistaActividad.getUsuarioDni();
            if (usuarioDni != null) {
                usuarioDni = em.getReference(usuarioDni.getClass(), usuarioDni.getDni());
                conferencistaActividad.setUsuarioDni(usuarioDni);
            }
            Actividad actividadId = conferencistaActividad.getActividadId();
            if (actividadId != null) {
                actividadId = em.getReference(actividadId.getClass(), actividadId.getId());
                conferencistaActividad.setActividadId(actividadId);
            }
            em.persist(conferencistaActividad);
            if (usuarioDni != null) {
                usuarioDni.getConferencistaActividadList().add(conferencistaActividad);
                usuarioDni = em.merge(usuarioDni);
            }
            if (actividadId != null) {
                actividadId.getConferencistaActividadList().add(conferencistaActividad);
                actividadId = em.merge(actividadId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConferencistaActividad conferencistaActividad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConferencistaActividad persistentConferencistaActividad = em.find(ConferencistaActividad.class, conferencistaActividad.getId());
            Usuario usuarioDniOld = persistentConferencistaActividad.getUsuarioDni();
            Usuario usuarioDniNew = conferencistaActividad.getUsuarioDni();
            Actividad actividadIdOld = persistentConferencistaActividad.getActividadId();
            Actividad actividadIdNew = conferencistaActividad.getActividadId();
            if (usuarioDniNew != null) {
                usuarioDniNew = em.getReference(usuarioDniNew.getClass(), usuarioDniNew.getDni());
                conferencistaActividad.setUsuarioDni(usuarioDniNew);
            }
            if (actividadIdNew != null) {
                actividadIdNew = em.getReference(actividadIdNew.getClass(), actividadIdNew.getId());
                conferencistaActividad.setActividadId(actividadIdNew);
            }
            conferencistaActividad = em.merge(conferencistaActividad);
            if (usuarioDniOld != null && !usuarioDniOld.equals(usuarioDniNew)) {
                usuarioDniOld.getConferencistaActividadList().remove(conferencistaActividad);
                usuarioDniOld = em.merge(usuarioDniOld);
            }
            if (usuarioDniNew != null && !usuarioDniNew.equals(usuarioDniOld)) {
                usuarioDniNew.getConferencistaActividadList().add(conferencistaActividad);
                usuarioDniNew = em.merge(usuarioDniNew);
            }
            if (actividadIdOld != null && !actividadIdOld.equals(actividadIdNew)) {
                actividadIdOld.getConferencistaActividadList().remove(conferencistaActividad);
                actividadIdOld = em.merge(actividadIdOld);
            }
            if (actividadIdNew != null && !actividadIdNew.equals(actividadIdOld)) {
                actividadIdNew.getConferencistaActividadList().add(conferencistaActividad);
                actividadIdNew = em.merge(actividadIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = conferencistaActividad.getId();
                if (findConferencistaActividad(id) == null) {
                    throw new NonexistentEntityException("The conferencistaActividad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConferencistaActividad conferencistaActividad;
            try {
                conferencistaActividad = em.getReference(ConferencistaActividad.class, id);
                conferencistaActividad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conferencistaActividad with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioDni = conferencistaActividad.getUsuarioDni();
            if (usuarioDni != null) {
                usuarioDni.getConferencistaActividadList().remove(conferencistaActividad);
                usuarioDni = em.merge(usuarioDni);
            }
            Actividad actividadId = conferencistaActividad.getActividadId();
            if (actividadId != null) {
                actividadId.getConferencistaActividadList().remove(conferencistaActividad);
                actividadId = em.merge(actividadId);
            }
            em.remove(conferencistaActividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConferencistaActividad> findConferencistaActividadEntities() {
        return findConferencistaActividadEntities(true, -1, -1);
    }

    public List<ConferencistaActividad> findConferencistaActividadEntities(int maxResults, int firstResult) {
        return findConferencistaActividadEntities(false, maxResults, firstResult);
    }

    private List<ConferencistaActividad> findConferencistaActividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConferencistaActividad.class));
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

    public ConferencistaActividad findConferencistaActividad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConferencistaActividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getConferencistaActividadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConferencistaActividad> rt = cq.from(ConferencistaActividad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
