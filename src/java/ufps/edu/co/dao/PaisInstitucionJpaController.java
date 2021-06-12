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
import ufps.edu.co.dto.Institucion;
import ufps.edu.co.dto.Pais;
import ufps.edu.co.dto.PaisInstitucion;

/**
 *
 * @author dunke
 */
public class PaisInstitucionJpaController implements Serializable {

    public PaisInstitucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaisInstitucion paisInstitucion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Institucion institucionId = paisInstitucion.getInstitucionId();
            if (institucionId != null) {
                institucionId = em.getReference(institucionId.getClass(), institucionId.getId());
                paisInstitucion.setInstitucionId(institucionId);
            }
            Pais paisId = paisInstitucion.getPaisId();
            if (paisId != null) {
                paisId = em.getReference(paisId.getClass(), paisId.getId());
                paisInstitucion.setPaisId(paisId);
            }
            em.persist(paisInstitucion);
            if (institucionId != null) {
                institucionId.getPaisInstitucionList().add(paisInstitucion);
                institucionId = em.merge(institucionId);
            }
            if (paisId != null) {
                paisId.getPaisInstitucionList().add(paisInstitucion);
                paisId = em.merge(paisId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaisInstitucion paisInstitucion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaisInstitucion persistentPaisInstitucion = em.find(PaisInstitucion.class, paisInstitucion.getId());
            Institucion institucionIdOld = persistentPaisInstitucion.getInstitucionId();
            Institucion institucionIdNew = paisInstitucion.getInstitucionId();
            Pais paisIdOld = persistentPaisInstitucion.getPaisId();
            Pais paisIdNew = paisInstitucion.getPaisId();
            if (institucionIdNew != null) {
                institucionIdNew = em.getReference(institucionIdNew.getClass(), institucionIdNew.getId());
                paisInstitucion.setInstitucionId(institucionIdNew);
            }
            if (paisIdNew != null) {
                paisIdNew = em.getReference(paisIdNew.getClass(), paisIdNew.getId());
                paisInstitucion.setPaisId(paisIdNew);
            }
            paisInstitucion = em.merge(paisInstitucion);
            if (institucionIdOld != null && !institucionIdOld.equals(institucionIdNew)) {
                institucionIdOld.getPaisInstitucionList().remove(paisInstitucion);
                institucionIdOld = em.merge(institucionIdOld);
            }
            if (institucionIdNew != null && !institucionIdNew.equals(institucionIdOld)) {
                institucionIdNew.getPaisInstitucionList().add(paisInstitucion);
                institucionIdNew = em.merge(institucionIdNew);
            }
            if (paisIdOld != null && !paisIdOld.equals(paisIdNew)) {
                paisIdOld.getPaisInstitucionList().remove(paisInstitucion);
                paisIdOld = em.merge(paisIdOld);
            }
            if (paisIdNew != null && !paisIdNew.equals(paisIdOld)) {
                paisIdNew.getPaisInstitucionList().add(paisInstitucion);
                paisIdNew = em.merge(paisIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paisInstitucion.getId();
                if (findPaisInstitucion(id) == null) {
                    throw new NonexistentEntityException("The paisInstitucion with id " + id + " no longer exists.");
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
            PaisInstitucion paisInstitucion;
            try {
                paisInstitucion = em.getReference(PaisInstitucion.class, id);
                paisInstitucion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paisInstitucion with id " + id + " no longer exists.", enfe);
            }
            Institucion institucionId = paisInstitucion.getInstitucionId();
            if (institucionId != null) {
                institucionId.getPaisInstitucionList().remove(paisInstitucion);
                institucionId = em.merge(institucionId);
            }
            Pais paisId = paisInstitucion.getPaisId();
            if (paisId != null) {
                paisId.getPaisInstitucionList().remove(paisInstitucion);
                paisId = em.merge(paisId);
            }
            em.remove(paisInstitucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PaisInstitucion> findPaisInstitucionEntities() {
        return findPaisInstitucionEntities(true, -1, -1);
    }

    public List<PaisInstitucion> findPaisInstitucionEntities(int maxResults, int firstResult) {
        return findPaisInstitucionEntities(false, maxResults, firstResult);
    }

    private List<PaisInstitucion> findPaisInstitucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaisInstitucion.class));
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

    public PaisInstitucion findPaisInstitucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaisInstitucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaisInstitucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaisInstitucion> rt = cq.from(PaisInstitucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
