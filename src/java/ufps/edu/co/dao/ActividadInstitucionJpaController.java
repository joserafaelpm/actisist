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
import ufps.edu.co.dto.ActividadInstitucion;
import ufps.edu.co.dto.Institucion;

/**
 *
 * @author dunke
 */
public class ActividadInstitucionJpaController implements Serializable {

    public ActividadInstitucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ActividadInstitucion actividadInstitucion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Institucion institucionId = actividadInstitucion.getInstitucionId();
            if (institucionId != null) {
                institucionId = em.getReference(institucionId.getClass(), institucionId.getId());
                actividadInstitucion.setInstitucionId(institucionId);
            }
            Institucion actividadId = actividadInstitucion.getActividadId();
            if (actividadId != null) {
                actividadId = em.getReference(actividadId.getClass(), actividadId.getId());
                actividadInstitucion.setActividadId(actividadId);
            }
            em.persist(actividadInstitucion);
            if (institucionId != null) {
                institucionId.getActividadInstitucionList().add(actividadInstitucion);
                institucionId = em.merge(institucionId);
            }
            if (actividadId != null) {
                actividadId.getActividadInstitucionList().add(actividadInstitucion);
                actividadId = em.merge(actividadId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActividadInstitucion actividadInstitucion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActividadInstitucion persistentActividadInstitucion = em.find(ActividadInstitucion.class, actividadInstitucion.getId());
            Institucion institucionIdOld = persistentActividadInstitucion.getInstitucionId();
            Institucion institucionIdNew = actividadInstitucion.getInstitucionId();
            Institucion actividadIdOld = persistentActividadInstitucion.getActividadId();
            Institucion actividadIdNew = actividadInstitucion.getActividadId();
            if (institucionIdNew != null) {
                institucionIdNew = em.getReference(institucionIdNew.getClass(), institucionIdNew.getId());
                actividadInstitucion.setInstitucionId(institucionIdNew);
            }
            if (actividadIdNew != null) {
                actividadIdNew = em.getReference(actividadIdNew.getClass(), actividadIdNew.getId());
                actividadInstitucion.setActividadId(actividadIdNew);
            }
            actividadInstitucion = em.merge(actividadInstitucion);
            if (institucionIdOld != null && !institucionIdOld.equals(institucionIdNew)) {
                institucionIdOld.getActividadInstitucionList().remove(actividadInstitucion);
                institucionIdOld = em.merge(institucionIdOld);
            }
            if (institucionIdNew != null && !institucionIdNew.equals(institucionIdOld)) {
                institucionIdNew.getActividadInstitucionList().add(actividadInstitucion);
                institucionIdNew = em.merge(institucionIdNew);
            }
            if (actividadIdOld != null && !actividadIdOld.equals(actividadIdNew)) {
                actividadIdOld.getActividadInstitucionList().remove(actividadInstitucion);
                actividadIdOld = em.merge(actividadIdOld);
            }
            if (actividadIdNew != null && !actividadIdNew.equals(actividadIdOld)) {
                actividadIdNew.getActividadInstitucionList().add(actividadInstitucion);
                actividadIdNew = em.merge(actividadIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actividadInstitucion.getId();
                if (findActividadInstitucion(id) == null) {
                    throw new NonexistentEntityException("The actividadInstitucion with id " + id + " no longer exists.");
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
            ActividadInstitucion actividadInstitucion;
            try {
                actividadInstitucion = em.getReference(ActividadInstitucion.class, id);
                actividadInstitucion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadInstitucion with id " + id + " no longer exists.", enfe);
            }
            Institucion institucionId = actividadInstitucion.getInstitucionId();
            if (institucionId != null) {
                institucionId.getActividadInstitucionList().remove(actividadInstitucion);
                institucionId = em.merge(institucionId);
            }
            Institucion actividadId = actividadInstitucion.getActividadId();
            if (actividadId != null) {
                actividadId.getActividadInstitucionList().remove(actividadInstitucion);
                actividadId = em.merge(actividadId);
            }
            em.remove(actividadInstitucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ActividadInstitucion> findActividadInstitucionEntities() {
        return findActividadInstitucionEntities(true, -1, -1);
    }

    public List<ActividadInstitucion> findActividadInstitucionEntities(int maxResults, int firstResult) {
        return findActividadInstitucionEntities(false, maxResults, firstResult);
    }

    private List<ActividadInstitucion> findActividadInstitucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActividadInstitucion.class));
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

    public ActividadInstitucion findActividadInstitucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActividadInstitucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadInstitucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActividadInstitucion> rt = cq.from(ActividadInstitucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
