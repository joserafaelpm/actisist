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
import ufps.edu.co.dto.Convenio;
import ufps.edu.co.dto.ConvenioActividad;

/**
 *
 * @author dunke
 */
public class ConvenioActividadJpaController implements Serializable {

    public ConvenioActividadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConvenioActividad convenioActividad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad actividadId = convenioActividad.getActividadId();
            if (actividadId != null) {
                actividadId = em.getReference(actividadId.getClass(), actividadId.getId());
                convenioActividad.setActividadId(actividadId);
            }
            Convenio convenioId = convenioActividad.getConvenioId();
            if (convenioId != null) {
                convenioId = em.getReference(convenioId.getClass(), convenioId.getId());
                convenioActividad.setConvenioId(convenioId);
            }
            em.persist(convenioActividad);
            if (actividadId != null) {
                actividadId.getConvenioActividadList().add(convenioActividad);
                actividadId = em.merge(actividadId);
            }
            if (convenioId != null) {
                convenioId.getConvenioActividadList().add(convenioActividad);
                convenioId = em.merge(convenioId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConvenioActividad convenioActividad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConvenioActividad persistentConvenioActividad = em.find(ConvenioActividad.class, convenioActividad.getId());
            Actividad actividadIdOld = persistentConvenioActividad.getActividadId();
            Actividad actividadIdNew = convenioActividad.getActividadId();
            Convenio convenioIdOld = persistentConvenioActividad.getConvenioId();
            Convenio convenioIdNew = convenioActividad.getConvenioId();
            if (actividadIdNew != null) {
                actividadIdNew = em.getReference(actividadIdNew.getClass(), actividadIdNew.getId());
                convenioActividad.setActividadId(actividadIdNew);
            }
            if (convenioIdNew != null) {
                convenioIdNew = em.getReference(convenioIdNew.getClass(), convenioIdNew.getId());
                convenioActividad.setConvenioId(convenioIdNew);
            }
            convenioActividad = em.merge(convenioActividad);
            if (actividadIdOld != null && !actividadIdOld.equals(actividadIdNew)) {
                actividadIdOld.getConvenioActividadList().remove(convenioActividad);
                actividadIdOld = em.merge(actividadIdOld);
            }
            if (actividadIdNew != null && !actividadIdNew.equals(actividadIdOld)) {
                actividadIdNew.getConvenioActividadList().add(convenioActividad);
                actividadIdNew = em.merge(actividadIdNew);
            }
            if (convenioIdOld != null && !convenioIdOld.equals(convenioIdNew)) {
                convenioIdOld.getConvenioActividadList().remove(convenioActividad);
                convenioIdOld = em.merge(convenioIdOld);
            }
            if (convenioIdNew != null && !convenioIdNew.equals(convenioIdOld)) {
                convenioIdNew.getConvenioActividadList().add(convenioActividad);
                convenioIdNew = em.merge(convenioIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = convenioActividad.getId();
                if (findConvenioActividad(id) == null) {
                    throw new NonexistentEntityException("The convenioActividad with id " + id + " no longer exists.");
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
            ConvenioActividad convenioActividad;
            try {
                convenioActividad = em.getReference(ConvenioActividad.class, id);
                convenioActividad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The convenioActividad with id " + id + " no longer exists.", enfe);
            }
            Actividad actividadId = convenioActividad.getActividadId();
            if (actividadId != null) {
                actividadId.getConvenioActividadList().remove(convenioActividad);
                actividadId = em.merge(actividadId);
            }
            Convenio convenioId = convenioActividad.getConvenioId();
            if (convenioId != null) {
                convenioId.getConvenioActividadList().remove(convenioActividad);
                convenioId = em.merge(convenioId);
            }
            em.remove(convenioActividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConvenioActividad> findConvenioActividadEntities() {
        return findConvenioActividadEntities(true, -1, -1);
    }

    public List<ConvenioActividad> findConvenioActividadEntities(int maxResults, int firstResult) {
        return findConvenioActividadEntities(false, maxResults, firstResult);
    }

    private List<ConvenioActividad> findConvenioActividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConvenioActividad.class));
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

    public ConvenioActividad findConvenioActividad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConvenioActividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getConvenioActividadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConvenioActividad> rt = cq.from(ConvenioActividad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
