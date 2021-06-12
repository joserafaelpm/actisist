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
import ufps.edu.co.dto.ActividadAcademica;
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
        if (tipoMovilidad.getActividadAcademicaList() == null) {
            tipoMovilidad.setActividadAcademicaList(new ArrayList<ActividadAcademica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ActividadAcademica> attachedActividadAcademicaList = new ArrayList<ActividadAcademica>();
            for (ActividadAcademica actividadAcademicaListActividadAcademicaToAttach : tipoMovilidad.getActividadAcademicaList()) {
                actividadAcademicaListActividadAcademicaToAttach = em.getReference(actividadAcademicaListActividadAcademicaToAttach.getClass(), actividadAcademicaListActividadAcademicaToAttach.getId());
                attachedActividadAcademicaList.add(actividadAcademicaListActividadAcademicaToAttach);
            }
            tipoMovilidad.setActividadAcademicaList(attachedActividadAcademicaList);
            em.persist(tipoMovilidad);
            for (ActividadAcademica actividadAcademicaListActividadAcademica : tipoMovilidad.getActividadAcademicaList()) {
                TipoMovilidad oldTipoMovilidadIdOfActividadAcademicaListActividadAcademica = actividadAcademicaListActividadAcademica.getTipoMovilidadId();
                actividadAcademicaListActividadAcademica.setTipoMovilidadId(tipoMovilidad);
                actividadAcademicaListActividadAcademica = em.merge(actividadAcademicaListActividadAcademica);
                if (oldTipoMovilidadIdOfActividadAcademicaListActividadAcademica != null) {
                    oldTipoMovilidadIdOfActividadAcademicaListActividadAcademica.getActividadAcademicaList().remove(actividadAcademicaListActividadAcademica);
                    oldTipoMovilidadIdOfActividadAcademicaListActividadAcademica = em.merge(oldTipoMovilidadIdOfActividadAcademicaListActividadAcademica);
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
            List<ActividadAcademica> actividadAcademicaListOld = persistentTipoMovilidad.getActividadAcademicaList();
            List<ActividadAcademica> actividadAcademicaListNew = tipoMovilidad.getActividadAcademicaList();
            List<String> illegalOrphanMessages = null;
            for (ActividadAcademica actividadAcademicaListOldActividadAcademica : actividadAcademicaListOld) {
                if (!actividadAcademicaListNew.contains(actividadAcademicaListOldActividadAcademica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ActividadAcademica " + actividadAcademicaListOldActividadAcademica + " since its tipoMovilidadId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ActividadAcademica> attachedActividadAcademicaListNew = new ArrayList<ActividadAcademica>();
            for (ActividadAcademica actividadAcademicaListNewActividadAcademicaToAttach : actividadAcademicaListNew) {
                actividadAcademicaListNewActividadAcademicaToAttach = em.getReference(actividadAcademicaListNewActividadAcademicaToAttach.getClass(), actividadAcademicaListNewActividadAcademicaToAttach.getId());
                attachedActividadAcademicaListNew.add(actividadAcademicaListNewActividadAcademicaToAttach);
            }
            actividadAcademicaListNew = attachedActividadAcademicaListNew;
            tipoMovilidad.setActividadAcademicaList(actividadAcademicaListNew);
            tipoMovilidad = em.merge(tipoMovilidad);
            for (ActividadAcademica actividadAcademicaListNewActividadAcademica : actividadAcademicaListNew) {
                if (!actividadAcademicaListOld.contains(actividadAcademicaListNewActividadAcademica)) {
                    TipoMovilidad oldTipoMovilidadIdOfActividadAcademicaListNewActividadAcademica = actividadAcademicaListNewActividadAcademica.getTipoMovilidadId();
                    actividadAcademicaListNewActividadAcademica.setTipoMovilidadId(tipoMovilidad);
                    actividadAcademicaListNewActividadAcademica = em.merge(actividadAcademicaListNewActividadAcademica);
                    if (oldTipoMovilidadIdOfActividadAcademicaListNewActividadAcademica != null && !oldTipoMovilidadIdOfActividadAcademicaListNewActividadAcademica.equals(tipoMovilidad)) {
                        oldTipoMovilidadIdOfActividadAcademicaListNewActividadAcademica.getActividadAcademicaList().remove(actividadAcademicaListNewActividadAcademica);
                        oldTipoMovilidadIdOfActividadAcademicaListNewActividadAcademica = em.merge(oldTipoMovilidadIdOfActividadAcademicaListNewActividadAcademica);
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
            List<ActividadAcademica> actividadAcademicaListOrphanCheck = tipoMovilidad.getActividadAcademicaList();
            for (ActividadAcademica actividadAcademicaListOrphanCheckActividadAcademica : actividadAcademicaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoMovilidad (" + tipoMovilidad + ") cannot be destroyed since the ActividadAcademica " + actividadAcademicaListOrphanCheckActividadAcademica + " in its actividadAcademicaList field has a non-nullable tipoMovilidadId field.");
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
