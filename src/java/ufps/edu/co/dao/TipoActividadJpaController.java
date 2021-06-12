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
import ufps.edu.co.dto.TipoActividad;

/**
 *
 * @author dunke
 */
public class TipoActividadJpaController implements Serializable {

    public TipoActividadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoActividad tipoActividad) {
        if (tipoActividad.getActividadList() == null) {
            tipoActividad.setActividadList(new ArrayList<Actividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Actividad> attachedActividadList = new ArrayList<Actividad>();
            for (Actividad actividadListActividadToAttach : tipoActividad.getActividadList()) {
                actividadListActividadToAttach = em.getReference(actividadListActividadToAttach.getClass(), actividadListActividadToAttach.getId());
                attachedActividadList.add(actividadListActividadToAttach);
            }
            tipoActividad.setActividadList(attachedActividadList);
            em.persist(tipoActividad);
            for (Actividad actividadListActividad : tipoActividad.getActividadList()) {
                TipoActividad oldTipoActividadIdOfActividadListActividad = actividadListActividad.getTipoActividadId();
                actividadListActividad.setTipoActividadId(tipoActividad);
                actividadListActividad = em.merge(actividadListActividad);
                if (oldTipoActividadIdOfActividadListActividad != null) {
                    oldTipoActividadIdOfActividadListActividad.getActividadList().remove(actividadListActividad);
                    oldTipoActividadIdOfActividadListActividad = em.merge(oldTipoActividadIdOfActividadListActividad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoActividad tipoActividad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoActividad persistentTipoActividad = em.find(TipoActividad.class, tipoActividad.getId());
            List<Actividad> actividadListOld = persistentTipoActividad.getActividadList();
            List<Actividad> actividadListNew = tipoActividad.getActividadList();
            List<String> illegalOrphanMessages = null;
            for (Actividad actividadListOldActividad : actividadListOld) {
                if (!actividadListNew.contains(actividadListOldActividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actividad " + actividadListOldActividad + " since its tipoActividadId field is not nullable.");
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
            tipoActividad.setActividadList(actividadListNew);
            tipoActividad = em.merge(tipoActividad);
            for (Actividad actividadListNewActividad : actividadListNew) {
                if (!actividadListOld.contains(actividadListNewActividad)) {
                    TipoActividad oldTipoActividadIdOfActividadListNewActividad = actividadListNewActividad.getTipoActividadId();
                    actividadListNewActividad.setTipoActividadId(tipoActividad);
                    actividadListNewActividad = em.merge(actividadListNewActividad);
                    if (oldTipoActividadIdOfActividadListNewActividad != null && !oldTipoActividadIdOfActividadListNewActividad.equals(tipoActividad)) {
                        oldTipoActividadIdOfActividadListNewActividad.getActividadList().remove(actividadListNewActividad);
                        oldTipoActividadIdOfActividadListNewActividad = em.merge(oldTipoActividadIdOfActividadListNewActividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoActividad.getId();
                if (findTipoActividad(id) == null) {
                    throw new NonexistentEntityException("The tipoActividad with id " + id + " no longer exists.");
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
            TipoActividad tipoActividad;
            try {
                tipoActividad = em.getReference(TipoActividad.class, id);
                tipoActividad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoActividad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Actividad> actividadListOrphanCheck = tipoActividad.getActividadList();
            for (Actividad actividadListOrphanCheckActividad : actividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoActividad (" + tipoActividad + ") cannot be destroyed since the Actividad " + actividadListOrphanCheckActividad + " in its actividadList field has a non-nullable tipoActividadId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoActividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoActividad> findTipoActividadEntities() {
        return findTipoActividadEntities(true, -1, -1);
    }

    public List<TipoActividad> findTipoActividadEntities(int maxResults, int firstResult) {
        return findTipoActividadEntities(false, maxResults, firstResult);
    }

    private List<TipoActividad> findTipoActividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoActividad.class));
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

    public TipoActividad findTipoActividad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoActividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoActividadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoActividad> rt = cq.from(TipoActividad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
