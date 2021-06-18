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
import ufps.edu.co.dto.TipoMovilidad;

/**
 *
 * @author dunke
 */
public class TipoMovilidadJpaController implements Serializable {

    public TipoMovilidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoMovilidad tipoMovilidad) {
        if (tipoMovilidad.getActividadList() == null) {
            tipoMovilidad.setActividadList(new ArrayList<Actividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Actividad> attachedActividadList = new ArrayList<Actividad>();
            for (Actividad actividadListActividadToAttach : tipoMovilidad.getActividadList()) {
                actividadListActividadToAttach = em.getReference(actividadListActividadToAttach.getClass(), actividadListActividadToAttach.getId());
                attachedActividadList.add(actividadListActividadToAttach);
            }
            tipoMovilidad.setActividadList(attachedActividadList);
            em.persist(tipoMovilidad);
            for (Actividad actividadListActividad : tipoMovilidad.getActividadList()) {
                TipoMovilidad oldTipoMovilidadIdOfActividadListActividad = actividadListActividad.getTipoMovilidadId();
                actividadListActividad.setTipoMovilidadId(tipoMovilidad);
                actividadListActividad = em.merge(actividadListActividad);
                if (oldTipoMovilidadIdOfActividadListActividad != null) {
                    oldTipoMovilidadIdOfActividadListActividad.getActividadList().remove(actividadListActividad);
                    oldTipoMovilidadIdOfActividadListActividad = em.merge(oldTipoMovilidadIdOfActividadListActividad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoMovilidad tipoMovilidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMovilidad persistentTipoMovilidad = em.find(TipoMovilidad.class, tipoMovilidad.getId());
            List<Actividad> actividadListOld = persistentTipoMovilidad.getActividadList();
            List<Actividad> actividadListNew = tipoMovilidad.getActividadList();
            List<String> illegalOrphanMessages = null;
            for (Actividad actividadListOldActividad : actividadListOld) {
                if (!actividadListNew.contains(actividadListOldActividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actividad " + actividadListOldActividad + " since its tipoMovilidadId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Actividad> attachedActividadListNew = new ArrayList<Actividad>();
            for (Actividad actividadListNewActividadToAttach : actividadListNew) {
                actividadListNewActividadToAttach = em.getReference(actividadListNewActividadToAttach.getClass(), actividadListNewActividadToAttach.getId());
                attachedActividadListNew.add(actividadListNewActividadToAttach);
            }
            actividadListNew = attachedActividadListNew;
            tipoMovilidad.setActividadList(actividadListNew);
            tipoMovilidad = em.merge(tipoMovilidad);
            for (Actividad actividadListNewActividad : actividadListNew) {
                if (!actividadListOld.contains(actividadListNewActividad)) {
                    TipoMovilidad oldTipoMovilidadIdOfActividadListNewActividad = actividadListNewActividad.getTipoMovilidadId();
                    actividadListNewActividad.setTipoMovilidadId(tipoMovilidad);
                    actividadListNewActividad = em.merge(actividadListNewActividad);
                    if (oldTipoMovilidadIdOfActividadListNewActividad != null && !oldTipoMovilidadIdOfActividadListNewActividad.equals(tipoMovilidad)) {
                        oldTipoMovilidadIdOfActividadListNewActividad.getActividadList().remove(actividadListNewActividad);
                        oldTipoMovilidadIdOfActividadListNewActividad = em.merge(oldTipoMovilidadIdOfActividadListNewActividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoMovilidad.getId();
                if (findTipoMovilidad(id) == null) {
                    throw new NonexistentEntityException("The tipoMovilidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMovilidad tipoMovilidad;
            try {
                tipoMovilidad = em.getReference(TipoMovilidad.class, id);
                tipoMovilidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoMovilidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Actividad> actividadListOrphanCheck = tipoMovilidad.getActividadList();
            for (Actividad actividadListOrphanCheckActividad : actividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoMovilidad (" + tipoMovilidad + ") cannot be destroyed since the Actividad " + actividadListOrphanCheckActividad + " in its actividadList field has a non-nullable tipoMovilidadId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoMovilidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoMovilidad> findTipoMovilidadEntities() {
        return findTipoMovilidadEntities(true, -1, -1);
    }

    public List<TipoMovilidad> findTipoMovilidadEntities(int maxResults, int firstResult) {
        return findTipoMovilidadEntities(false, maxResults, firstResult);
    }

    private List<TipoMovilidad> findTipoMovilidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoMovilidad.class));
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

    public TipoMovilidad findTipoMovilidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoMovilidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoMovilidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoMovilidad> rt = cq.from(TipoMovilidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
