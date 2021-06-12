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
import ufps.edu.co.dto.Horario;

/**
 *
 * @author dunke
 */
public class HorarioJpaController implements Serializable {

    public HorarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Horario horario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActividadAcademica actividadAcademicaId = horario.getActividadAcademicaId();
            if (actividadAcademicaId != null) {
                actividadAcademicaId = em.getReference(actividadAcademicaId.getClass(), actividadAcademicaId.getId());
                horario.setActividadAcademicaId(actividadAcademicaId);
            }
            em.persist(horario);
            if (actividadAcademicaId != null) {
                actividadAcademicaId.getHorarioList().add(horario);
                actividadAcademicaId = em.merge(actividadAcademicaId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Horario horario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario persistentHorario = em.find(Horario.class, horario.getId());
            ActividadAcademica actividadAcademicaIdOld = persistentHorario.getActividadAcademicaId();
            ActividadAcademica actividadAcademicaIdNew = horario.getActividadAcademicaId();
            if (actividadAcademicaIdNew != null) {
                actividadAcademicaIdNew = em.getReference(actividadAcademicaIdNew.getClass(), actividadAcademicaIdNew.getId());
                horario.setActividadAcademicaId(actividadAcademicaIdNew);
            }
            horario = em.merge(horario);
            if (actividadAcademicaIdOld != null && !actividadAcademicaIdOld.equals(actividadAcademicaIdNew)) {
                actividadAcademicaIdOld.getHorarioList().remove(horario);
                actividadAcademicaIdOld = em.merge(actividadAcademicaIdOld);
            }
            if (actividadAcademicaIdNew != null && !actividadAcademicaIdNew.equals(actividadAcademicaIdOld)) {
                actividadAcademicaIdNew.getHorarioList().add(horario);
                actividadAcademicaIdNew = em.merge(actividadAcademicaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = horario.getId();
                if (findHorario(id) == null) {
                    throw new NonexistentEntityException("The horario with id " + id + " no longer exists.");
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
            Horario horario;
            try {
                horario = em.getReference(Horario.class, id);
                horario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The horario with id " + id + " no longer exists.", enfe);
            }
            ActividadAcademica actividadAcademicaId = horario.getActividadAcademicaId();
            if (actividadAcademicaId != null) {
                actividadAcademicaId.getHorarioList().remove(horario);
                actividadAcademicaId = em.merge(actividadAcademicaId);
            }
            em.remove(horario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Horario> findHorarioEntities() {
        return findHorarioEntities(true, -1, -1);
    }

    public List<Horario> findHorarioEntities(int maxResults, int firstResult) {
        return findHorarioEntities(false, maxResults, firstResult);
    }

    private List<Horario> findHorarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Horario.class));
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

    public Horario findHorario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Horario.class, id);
        } finally {
            em.close();
        }
    }

    public int getHorarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Horario> rt = cq.from(Horario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
