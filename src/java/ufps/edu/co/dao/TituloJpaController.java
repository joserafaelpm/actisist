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
import ufps.edu.co.dto.Titulo;
import ufps.edu.co.dto.Usuario;

/**
 *
 * @author dunke
 */
public class TituloJpaController implements Serializable {

    public TituloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Titulo titulo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioDni = titulo.getUsuarioDni();
            if (usuarioDni != null) {
                usuarioDni = em.getReference(usuarioDni.getClass(), usuarioDni.getDni());
                titulo.setUsuarioDni(usuarioDni);
            }
            em.persist(titulo);
            if (usuarioDni != null) {
                usuarioDni.getTituloList().add(titulo);
                usuarioDni = em.merge(usuarioDni);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Titulo titulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Titulo persistentTitulo = em.find(Titulo.class, titulo.getId());
            Usuario usuarioDniOld = persistentTitulo.getUsuarioDni();
            Usuario usuarioDniNew = titulo.getUsuarioDni();
            if (usuarioDniNew != null) {
                usuarioDniNew = em.getReference(usuarioDniNew.getClass(), usuarioDniNew.getDni());
                titulo.setUsuarioDni(usuarioDniNew);
            }
            titulo = em.merge(titulo);
            if (usuarioDniOld != null && !usuarioDniOld.equals(usuarioDniNew)) {
                usuarioDniOld.getTituloList().remove(titulo);
                usuarioDniOld = em.merge(usuarioDniOld);
            }
            if (usuarioDniNew != null && !usuarioDniNew.equals(usuarioDniOld)) {
                usuarioDniNew.getTituloList().add(titulo);
                usuarioDniNew = em.merge(usuarioDniNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = titulo.getId();
                if (findTitulo(id) == null) {
                    throw new NonexistentEntityException("The titulo with id " + id + " no longer exists.");
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
            Titulo titulo;
            try {
                titulo = em.getReference(Titulo.class, id);
                titulo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The titulo with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioDni = titulo.getUsuarioDni();
            if (usuarioDni != null) {
                usuarioDni.getTituloList().remove(titulo);
                usuarioDni = em.merge(usuarioDni);
            }
            em.remove(titulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Titulo> findTituloEntities() {
        return findTituloEntities(true, -1, -1);
    }

    public List<Titulo> findTituloEntities(int maxResults, int firstResult) {
        return findTituloEntities(false, maxResults, firstResult);
    }

    private List<Titulo> findTituloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Titulo.class));
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

    public Titulo findTitulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Titulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTituloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Titulo> rt = cq.from(Titulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
