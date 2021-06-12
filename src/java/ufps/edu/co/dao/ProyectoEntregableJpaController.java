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
import ufps.edu.co.dto.Proyecto;
import ufps.edu.co.dto.ProyectoEntregable;

/**
 *
 * @author dunke
 */
public class ProyectoEntregableJpaController implements Serializable {

    public ProyectoEntregableJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProyectoEntregable proyectoEntregable) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto proyectoId = proyectoEntregable.getProyectoId();
            if (proyectoId != null) {
                proyectoId = em.getReference(proyectoId.getClass(), proyectoId.getId());
                proyectoEntregable.setProyectoId(proyectoId);
            }
            em.persist(proyectoEntregable);
            if (proyectoId != null) {
                proyectoId.getProyectoEntregableList().add(proyectoEntregable);
                proyectoId = em.merge(proyectoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProyectoEntregable proyectoEntregable) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProyectoEntregable persistentProyectoEntregable = em.find(ProyectoEntregable.class, proyectoEntregable.getId());
            Proyecto proyectoIdOld = persistentProyectoEntregable.getProyectoId();
            Proyecto proyectoIdNew = proyectoEntregable.getProyectoId();
            if (proyectoIdNew != null) {
                proyectoIdNew = em.getReference(proyectoIdNew.getClass(), proyectoIdNew.getId());
                proyectoEntregable.setProyectoId(proyectoIdNew);
            }
            proyectoEntregable = em.merge(proyectoEntregable);
            if (proyectoIdOld != null && !proyectoIdOld.equals(proyectoIdNew)) {
                proyectoIdOld.getProyectoEntregableList().remove(proyectoEntregable);
                proyectoIdOld = em.merge(proyectoIdOld);
            }
            if (proyectoIdNew != null && !proyectoIdNew.equals(proyectoIdOld)) {
                proyectoIdNew.getProyectoEntregableList().add(proyectoEntregable);
                proyectoIdNew = em.merge(proyectoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proyectoEntregable.getId();
                if (findProyectoEntregable(id) == null) {
                    throw new NonexistentEntityException("The proyectoEntregable with id " + id + " no longer exists.");
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
            ProyectoEntregable proyectoEntregable;
            try {
                proyectoEntregable = em.getReference(ProyectoEntregable.class, id);
                proyectoEntregable.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyectoEntregable with id " + id + " no longer exists.", enfe);
            }
            Proyecto proyectoId = proyectoEntregable.getProyectoId();
            if (proyectoId != null) {
                proyectoId.getProyectoEntregableList().remove(proyectoEntregable);
                proyectoId = em.merge(proyectoId);
            }
            em.remove(proyectoEntregable);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProyectoEntregable> findProyectoEntregableEntities() {
        return findProyectoEntregableEntities(true, -1, -1);
    }

    public List<ProyectoEntregable> findProyectoEntregableEntities(int maxResults, int firstResult) {
        return findProyectoEntregableEntities(false, maxResults, firstResult);
    }

    private List<ProyectoEntregable> findProyectoEntregableEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProyectoEntregable.class));
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

    public ProyectoEntregable findProyectoEntregable(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProyectoEntregable.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoEntregableCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProyectoEntregable> rt = cq.from(ProyectoEntregable.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
