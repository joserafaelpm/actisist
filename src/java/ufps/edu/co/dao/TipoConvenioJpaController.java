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
import ufps.edu.co.dto.Convenio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.TipoConvenio;

/**
 *
 * @author dunke
 */
public class TipoConvenioJpaController implements Serializable {

    public TipoConvenioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoConvenio tipoConvenio) {
        if (tipoConvenio.getConvenioList() == null) {
            tipoConvenio.setConvenioList(new ArrayList<Convenio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Convenio> attachedConvenioList = new ArrayList<Convenio>();
            for (Convenio convenioListConvenioToAttach : tipoConvenio.getConvenioList()) {
                convenioListConvenioToAttach = em.getReference(convenioListConvenioToAttach.getClass(), convenioListConvenioToAttach.getId());
                attachedConvenioList.add(convenioListConvenioToAttach);
            }
            tipoConvenio.setConvenioList(attachedConvenioList);
            em.persist(tipoConvenio);
            for (Convenio convenioListConvenio : tipoConvenio.getConvenioList()) {
                TipoConvenio oldTipoConvenioOfConvenioListConvenio = convenioListConvenio.getTipoConvenio();
                convenioListConvenio.setTipoConvenio(tipoConvenio);
                convenioListConvenio = em.merge(convenioListConvenio);
                if (oldTipoConvenioOfConvenioListConvenio != null) {
                    oldTipoConvenioOfConvenioListConvenio.getConvenioList().remove(convenioListConvenio);
                    oldTipoConvenioOfConvenioListConvenio = em.merge(oldTipoConvenioOfConvenioListConvenio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoConvenio tipoConvenio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoConvenio persistentTipoConvenio = em.find(TipoConvenio.class, tipoConvenio.getId());
            List<Convenio> convenioListOld = persistentTipoConvenio.getConvenioList();
            List<Convenio> convenioListNew = tipoConvenio.getConvenioList();
            List<String> illegalOrphanMessages = null;
            for (Convenio convenioListOldConvenio : convenioListOld) {
                if (!convenioListNew.contains(convenioListOldConvenio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Convenio " + convenioListOldConvenio + " since its tipoConvenio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Convenio> attachedConvenioListNew = new ArrayList<Convenio>();
            for (Convenio convenioListNewConvenioToAttach : convenioListNew) {
                convenioListNewConvenioToAttach = em.getReference(convenioListNewConvenioToAttach.getClass(), convenioListNewConvenioToAttach.getId());
                attachedConvenioListNew.add(convenioListNewConvenioToAttach);
            }
            convenioListNew = attachedConvenioListNew;
            tipoConvenio.setConvenioList(convenioListNew);
            tipoConvenio = em.merge(tipoConvenio);
            for (Convenio convenioListNewConvenio : convenioListNew) {
                if (!convenioListOld.contains(convenioListNewConvenio)) {
                    TipoConvenio oldTipoConvenioOfConvenioListNewConvenio = convenioListNewConvenio.getTipoConvenio();
                    convenioListNewConvenio.setTipoConvenio(tipoConvenio);
                    convenioListNewConvenio = em.merge(convenioListNewConvenio);
                    if (oldTipoConvenioOfConvenioListNewConvenio != null && !oldTipoConvenioOfConvenioListNewConvenio.equals(tipoConvenio)) {
                        oldTipoConvenioOfConvenioListNewConvenio.getConvenioList().remove(convenioListNewConvenio);
                        oldTipoConvenioOfConvenioListNewConvenio = em.merge(oldTipoConvenioOfConvenioListNewConvenio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoConvenio.getId();
                if (findTipoConvenio(id) == null) {
                    throw new NonexistentEntityException("The tipoConvenio with id " + id + " no longer exists.");
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
            TipoConvenio tipoConvenio;
            try {
                tipoConvenio = em.getReference(TipoConvenio.class, id);
                tipoConvenio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoConvenio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Convenio> convenioListOrphanCheck = tipoConvenio.getConvenioList();
            for (Convenio convenioListOrphanCheckConvenio : convenioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoConvenio (" + tipoConvenio + ") cannot be destroyed since the Convenio " + convenioListOrphanCheckConvenio + " in its convenioList field has a non-nullable tipoConvenio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoConvenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoConvenio> findTipoConvenioEntities() {
        return findTipoConvenioEntities(true, -1, -1);
    }

    public List<TipoConvenio> findTipoConvenioEntities(int maxResults, int firstResult) {
        return findTipoConvenioEntities(false, maxResults, firstResult);
    }

    private List<TipoConvenio> findTipoConvenioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoConvenio.class));
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

    public TipoConvenio findTipoConvenio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoConvenio.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoConvenioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoConvenio> rt = cq.from(TipoConvenio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
