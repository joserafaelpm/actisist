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
import ufps.edu.co.dto.Actividad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dao.exceptions.PreexistingEntityException;
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

    public void create(InvolucradosActividad involucradosActividad) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Actividad actividadOrphanCheck = involucradosActividad.getActividad();
        if (actividadOrphanCheck != null) {
            InvolucradosActividad oldInvolucradosActividadOfActividad = actividadOrphanCheck.getInvolucradosActividad();
            if (oldInvolucradosActividadOfActividad != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Actividad " + actividadOrphanCheck + " already has an item of type InvolucradosActividad whose actividad column cannot be null. Please make another selection for the actividad field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad actividad = involucradosActividad.getActividad();
            if (actividad != null) {
                actividad = em.getReference(actividad.getClass(), actividad.getId());
                involucradosActividad.setActividad(actividad);
            }
            em.persist(involucradosActividad);
            if (actividad != null) {
                actividad.setInvolucradosActividad(involucradosActividad);
                actividad = em.merge(actividad);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInvolucradosActividad(involucradosActividad.getActividadId()) != null) {
                throw new PreexistingEntityException("InvolucradosActividad " + involucradosActividad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InvolucradosActividad involucradosActividad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InvolucradosActividad persistentInvolucradosActividad = em.find(InvolucradosActividad.class, involucradosActividad.getActividadId());
            Actividad actividadOld = persistentInvolucradosActividad.getActividad();
            Actividad actividadNew = involucradosActividad.getActividad();
            List<String> illegalOrphanMessages = null;
            if (actividadNew != null && !actividadNew.equals(actividadOld)) {
                InvolucradosActividad oldInvolucradosActividadOfActividad = actividadNew.getInvolucradosActividad();
                if (oldInvolucradosActividadOfActividad != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Actividad " + actividadNew + " already has an item of type InvolucradosActividad whose actividad column cannot be null. Please make another selection for the actividad field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (actividadNew != null) {
                actividadNew = em.getReference(actividadNew.getClass(), actividadNew.getId());
                involucradosActividad.setActividad(actividadNew);
            }
            involucradosActividad = em.merge(involucradosActividad);
            if (actividadOld != null && !actividadOld.equals(actividadNew)) {
                actividadOld.setInvolucradosActividad(null);
                actividadOld = em.merge(actividadOld);
            }
            if (actividadNew != null && !actividadNew.equals(actividadOld)) {
                actividadNew.setInvolucradosActividad(involucradosActividad);
                actividadNew = em.merge(actividadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = involucradosActividad.getActividadId();
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
                involucradosActividad.getActividadId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The involucradosActividad with id " + id + " no longer exists.", enfe);
            }
            Actividad actividad = involucradosActividad.getActividad();
            if (actividad != null) {
                actividad.setInvolucradosActividad(null);
                actividad = em.merge(actividad);
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
