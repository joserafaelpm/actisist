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
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.ActividadAnexos;

/**
 *
 * @author dunke
 */
public class ActividadAnexosJpaController implements Serializable {

    public ActividadAnexosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ActividadAnexos actividadAnexos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad actividadId = actividadAnexos.getActividadId();
            if (actividadId != null) {
                actividadId = em.getReference(actividadId.getClass(), actividadId.getId());
                actividadAnexos.setActividadId(actividadId);
            }
            em.persist(actividadAnexos);
            if (actividadId != null) {
                actividadId.getActividadAnexosList().add(actividadAnexos);
                actividadId = em.merge(actividadId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActividadAnexos actividadAnexos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActividadAnexos persistentActividadAnexos = em.find(ActividadAnexos.class, actividadAnexos.getId());
            Actividad actividadIdOld = persistentActividadAnexos.getActividadId();
            Actividad actividadIdNew = actividadAnexos.getActividadId();
            if (actividadIdNew != null) {
                actividadIdNew = em.getReference(actividadIdNew.getClass(), actividadIdNew.getId());
                actividadAnexos.setActividadId(actividadIdNew);
            }
            actividadAnexos = em.merge(actividadAnexos);
            if (actividadIdOld != null && !actividadIdOld.equals(actividadIdNew)) {
                actividadIdOld.getActividadAnexosList().remove(actividadAnexos);
                actividadIdOld = em.merge(actividadIdOld);
            }
            if (actividadIdNew != null && !actividadIdNew.equals(actividadIdOld)) {
                actividadIdNew.getActividadAnexosList().add(actividadAnexos);
                actividadIdNew = em.merge(actividadIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actividadAnexos.getId();
                if (findActividadAnexos(id) == null) {
                    throw new NonexistentEntityException("The actividadAnexos with id " + id + " no longer exists.");
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
            ActividadAnexos actividadAnexos;
            try {
                actividadAnexos = em.getReference(ActividadAnexos.class, id);
                actividadAnexos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadAnexos with id " + id + " no longer exists.", enfe);
            }
            Actividad actividadId = actividadAnexos.getActividadId();
            if (actividadId != null) {
                actividadId.getActividadAnexosList().remove(actividadAnexos);
                actividadId = em.merge(actividadId);
            }
            em.remove(actividadAnexos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ActividadAnexos> findActividadAnexosEntities() {
        return findActividadAnexosEntities(true, -1, -1);
    }

    public List<ActividadAnexos> findActividadAnexosEntities(int maxResults, int firstResult) {
        return findActividadAnexosEntities(false, maxResults, firstResult);
    }

    private List<ActividadAnexos> findActividadAnexosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActividadAnexos.class));
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

    public ActividadAnexos findActividadAnexos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActividadAnexos.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadAnexosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActividadAnexos> rt = cq.from(ActividadAnexos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
