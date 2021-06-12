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
import ufps.edu.co.dto.ActividadAcademica;
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
            ActividadAcademica actividadAcademicaId = convenioActividad.getActividadAcademicaId();
            if (actividadAcademicaId != null) {
                actividadAcademicaId = em.getReference(actividadAcademicaId.getClass(), actividadAcademicaId.getId());
                convenioActividad.setActividadAcademicaId(actividadAcademicaId);
            }
            Convenio convenioId = convenioActividad.getConvenioId();
            if (convenioId != null) {
                convenioId = em.getReference(convenioId.getClass(), convenioId.getId());
                convenioActividad.setConvenioId(convenioId);
            }
            em.persist(convenioActividad);
            if (actividadAcademicaId != null) {
                actividadAcademicaId.getConvenioActividadList().add(convenioActividad);
                actividadAcademicaId = em.merge(actividadAcademicaId);
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
            ActividadAcademica actividadAcademicaIdOld = persistentConvenioActividad.getActividadAcademicaId();
            ActividadAcademica actividadAcademicaIdNew = convenioActividad.getActividadAcademicaId();
            Convenio convenioIdOld = persistentConvenioActividad.getConvenioId();
            Convenio convenioIdNew = convenioActividad.getConvenioId();
            if (actividadAcademicaIdNew != null) {
                actividadAcademicaIdNew = em.getReference(actividadAcademicaIdNew.getClass(), actividadAcademicaIdNew.getId());
                convenioActividad.setActividadAcademicaId(actividadAcademicaIdNew);
            }
            if (convenioIdNew != null) {
                convenioIdNew = em.getReference(convenioIdNew.getClass(), convenioIdNew.getId());
                convenioActividad.setConvenioId(convenioIdNew);
            }
            convenioActividad = em.merge(convenioActividad);
            if (actividadAcademicaIdOld != null && !actividadAcademicaIdOld.equals(actividadAcademicaIdNew)) {
                actividadAcademicaIdOld.getConvenioActividadList().remove(convenioActividad);
                actividadAcademicaIdOld = em.merge(actividadAcademicaIdOld);
            }
            if (actividadAcademicaIdNew != null && !actividadAcademicaIdNew.equals(actividadAcademicaIdOld)) {
                actividadAcademicaIdNew.getConvenioActividadList().add(convenioActividad);
                actividadAcademicaIdNew = em.merge(actividadAcademicaIdNew);
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
            ActividadAcademica actividadAcademicaId = convenioActividad.getActividadAcademicaId();
            if (actividadAcademicaId != null) {
                actividadAcademicaId.getConvenioActividadList().remove(convenioActividad);
                actividadAcademicaId = em.merge(actividadAcademicaId);
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
