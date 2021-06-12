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
import ufps.edu.co.dto.Rol;
import ufps.edu.co.dto.SolicitudRegistro;

/**
 *
 * @author dunke
 */
public class SolicitudRegistroJpaController implements Serializable {

    public SolicitudRegistroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SolicitudRegistro solicitudRegistro) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol typeUs = solicitudRegistro.getTypeUs();
            if (typeUs != null) {
                typeUs = em.getReference(typeUs.getClass(), typeUs.getId());
                solicitudRegistro.setTypeUs(typeUs);
            }
            em.persist(solicitudRegistro);
            if (typeUs != null) {
                typeUs.getSolicitudRegistroList().add(solicitudRegistro);
                typeUs = em.merge(typeUs);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSolicitudRegistro(solicitudRegistro.getToken()) != null) {
                throw new PreexistingEntityException("SolicitudRegistro " + solicitudRegistro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SolicitudRegistro solicitudRegistro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SolicitudRegistro persistentSolicitudRegistro = em.find(SolicitudRegistro.class, solicitudRegistro.getToken());
            Rol typeUsOld = persistentSolicitudRegistro.getTypeUs();
            Rol typeUsNew = solicitudRegistro.getTypeUs();
            if (typeUsNew != null) {
                typeUsNew = em.getReference(typeUsNew.getClass(), typeUsNew.getId());
                solicitudRegistro.setTypeUs(typeUsNew);
            }
            solicitudRegistro = em.merge(solicitudRegistro);
            if (typeUsOld != null && !typeUsOld.equals(typeUsNew)) {
                typeUsOld.getSolicitudRegistroList().remove(solicitudRegistro);
                typeUsOld = em.merge(typeUsOld);
            }
            if (typeUsNew != null && !typeUsNew.equals(typeUsOld)) {
                typeUsNew.getSolicitudRegistroList().add(solicitudRegistro);
                typeUsNew = em.merge(typeUsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = solicitudRegistro.getToken();
                if (findSolicitudRegistro(id) == null) {
                    throw new NonexistentEntityException("The solicitudRegistro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SolicitudRegistro solicitudRegistro;
            try {
                solicitudRegistro = em.getReference(SolicitudRegistro.class, id);
                solicitudRegistro.getToken();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitudRegistro with id " + id + " no longer exists.", enfe);
            }
            Rol typeUs = solicitudRegistro.getTypeUs();
            if (typeUs != null) {
                typeUs.getSolicitudRegistroList().remove(solicitudRegistro);
                typeUs = em.merge(typeUs);
            }
            em.remove(solicitudRegistro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SolicitudRegistro> findSolicitudRegistroEntities() {
        return findSolicitudRegistroEntities(true, -1, -1);
    }

    public List<SolicitudRegistro> findSolicitudRegistroEntities(int maxResults, int firstResult) {
        return findSolicitudRegistroEntities(false, maxResults, firstResult);
    }

    private List<SolicitudRegistro> findSolicitudRegistroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SolicitudRegistro.class));
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

    public SolicitudRegistro findSolicitudRegistro(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SolicitudRegistro.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudRegistroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SolicitudRegistro> rt = cq.from(SolicitudRegistro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
