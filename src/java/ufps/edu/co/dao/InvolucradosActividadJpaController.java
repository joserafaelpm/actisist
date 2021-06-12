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
import ufps.edu.co.dao.exceptions.PreexistingEntityException;
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.InvolucradosActividad;

/**
 *
 * @author dunke
 */
public class InvolucradosActividadJpaController implements Serializable {

    public InvolucradosActividadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InvolucradosActividad involucradosActividad) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad actividadId = involucradosActividad.getActividadId();
            if (actividadId != null) {
                actividadId = em.getReference(actividadId.getClass(), actividadId.getId());
                involucradosActividad.setActividadId(actividadId);
            }
            em.persist(involucradosActividad);
            if (actividadId != null) {
                actividadId.getInvolucradosActividadList().add(involucradosActividad);
                actividadId = em.merge(actividadId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInvolucradosActividad(involucradosActividad.getId()) != null) {
                throw new PreexistingEntityException("InvolucradosActividad " + involucradosActividad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InvolucradosActividad involucradosActividad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InvolucradosActividad persistentInvolucradosActividad = em.find(InvolucradosActividad.class, involucradosActividad.getId());
            Actividad actividadIdOld = persistentInvolucradosActividad.getActividadId();
            Actividad actividadIdNew = involucradosActividad.getActividadId();
            if (actividadIdNew != null) {
                actividadIdNew = em.getReference(actividadIdNew.getClass(), actividadIdNew.getId());
                involucradosActividad.setActividadId(actividadIdNew);
            }
            involucradosActividad = em.merge(involucradosActividad);
            if (actividadIdOld != null && !actividadIdOld.equals(actividadIdNew)) {
                actividadIdOld.getInvolucradosActividadList().remove(involucradosActividad);
                actividadIdOld = em.merge(actividadIdOld);
            }
            if (actividadIdNew != null && !actividadIdNew.equals(actividadIdOld)) {
                actividadIdNew.getInvolucradosActividadList().add(involucradosActividad);
                actividadIdNew = em.merge(actividadIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = involucradosActividad.getId();
                if (findInvolucradosActividad(id) == null) {
                    throw new NonexistentEntityException("The involucradosActividad with id " + id + " no longer exists.");
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
            InvolucradosActividad involucradosActividad;
            try {
                involucradosActividad = em.getReference(InvolucradosActividad.class, id);
                involucradosActividad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The involucradosActividad with id " + id + " no longer exists.", enfe);
            }
            Actividad actividadId = involucradosActividad.getActividadId();
            if (actividadId != null) {
                actividadId.getInvolucradosActividadList().remove(involucradosActividad);
                actividadId = em.merge(actividadId);
            }
            em.remove(involucradosActividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InvolucradosActividad> findInvolucradosActividadEntities() {
        return findInvolucradosActividadEntities(true, -1, -1);
    }

    public List<InvolucradosActividad> findInvolucradosActividadEntities(int maxResults, int firstResult) {
        return findInvolucradosActividadEntities(false, maxResults, firstResult);
    }

    private List<InvolucradosActividad> findInvolucradosActividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InvolucradosActividad.class));
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

    public InvolucradosActividad findInvolucradosActividad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InvolucradosActividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvolucradosActividadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InvolucradosActividad> rt = cq.from(InvolucradosActividad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
