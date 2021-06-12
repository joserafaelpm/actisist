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
import ufps.edu.co.dto.Docente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.TipoDocente;

/**
 *
 * @author dunke
 */
public class TipoDocenteJpaController implements Serializable {

    public TipoDocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocente tipoDocente) {
        if (tipoDocente.getDocenteList() == null) {
            tipoDocente.setDocenteList(new ArrayList<Docente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : tipoDocente.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getUsuarioDni());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            tipoDocente.setDocenteList(attachedDocenteList);
            em.persist(tipoDocente);
            for (Docente docenteListDocente : tipoDocente.getDocenteList()) {
                TipoDocente oldIdTipoDocenteOfDocenteListDocente = docenteListDocente.getIdTipoDocente();
                docenteListDocente.setIdTipoDocente(tipoDocente);
                docenteListDocente = em.merge(docenteListDocente);
                if (oldIdTipoDocenteOfDocenteListDocente != null) {
                    oldIdTipoDocenteOfDocenteListDocente.getDocenteList().remove(docenteListDocente);
                    oldIdTipoDocenteOfDocenteListDocente = em.merge(oldIdTipoDocenteOfDocenteListDocente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocente tipoDocente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocente persistentTipoDocente = em.find(TipoDocente.class, tipoDocente.getId());
            List<Docente> docenteListOld = persistentTipoDocente.getDocenteList();
            List<Docente> docenteListNew = tipoDocente.getDocenteList();
            List<String> illegalOrphanMessages = null;
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docente " + docenteListOldDocente + " since its idTipoDocente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getUsuarioDni());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            tipoDocente.setDocenteList(docenteListNew);
            tipoDocente = em.merge(tipoDocente);
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    TipoDocente oldIdTipoDocenteOfDocenteListNewDocente = docenteListNewDocente.getIdTipoDocente();
                    docenteListNewDocente.setIdTipoDocente(tipoDocente);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                    if (oldIdTipoDocenteOfDocenteListNewDocente != null && !oldIdTipoDocenteOfDocenteListNewDocente.equals(tipoDocente)) {
                        oldIdTipoDocenteOfDocenteListNewDocente.getDocenteList().remove(docenteListNewDocente);
                        oldIdTipoDocenteOfDocenteListNewDocente = em.merge(oldIdTipoDocenteOfDocenteListNewDocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDocente.getId();
                if (findTipoDocente(id) == null) {
                    throw new NonexistentEntityException("The tipoDocente with id " + id + " no longer exists.");
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
            TipoDocente tipoDocente;
            try {
                tipoDocente = em.getReference(TipoDocente.class, id);
                tipoDocente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Docente> docenteListOrphanCheck = tipoDocente.getDocenteList();
            for (Docente docenteListOrphanCheckDocente : docenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoDocente (" + tipoDocente + ") cannot be destroyed since the Docente " + docenteListOrphanCheckDocente + " in its docenteList field has a non-nullable idTipoDocente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoDocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocente> findTipoDocenteEntities() {
        return findTipoDocenteEntities(true, -1, -1);
    }

    public List<TipoDocente> findTipoDocenteEntities(int maxResults, int firstResult) {
        return findTipoDocenteEntities(false, maxResults, firstResult);
    }

    private List<TipoDocente> findTipoDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocente.class));
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

    public TipoDocente findTipoDocente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocente.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDocente> rt = cq.from(TipoDocente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
