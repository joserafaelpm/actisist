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
import ufps.edu.co.dto.Conferencista;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.TipoConferencista;

/**
 *
 * @author dunke
 */
public class TipoConferencistaJpaController implements Serializable {

    public TipoConferencistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoConferencista tipoConferencista) {
        if (tipoConferencista.getConferencistaList() == null) {
            tipoConferencista.setConferencistaList(new ArrayList<Conferencista>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Conferencista> attachedConferencistaList = new ArrayList<Conferencista>();
            for (Conferencista conferencistaListConferencistaToAttach : tipoConferencista.getConferencistaList()) {
                conferencistaListConferencistaToAttach = em.getReference(conferencistaListConferencistaToAttach.getClass(), conferencistaListConferencistaToAttach.getUsuarioDni());
                attachedConferencistaList.add(conferencistaListConferencistaToAttach);
            }
            tipoConferencista.setConferencistaList(attachedConferencistaList);
            em.persist(tipoConferencista);
            for (Conferencista conferencistaListConferencista : tipoConferencista.getConferencistaList()) {
                TipoConferencista oldTipoConferencistaIdOfConferencistaListConferencista = conferencistaListConferencista.getTipoConferencistaId();
                conferencistaListConferencista.setTipoConferencistaId(tipoConferencista);
                conferencistaListConferencista = em.merge(conferencistaListConferencista);
                if (oldTipoConferencistaIdOfConferencistaListConferencista != null) {
                    oldTipoConferencistaIdOfConferencistaListConferencista.getConferencistaList().remove(conferencistaListConferencista);
                    oldTipoConferencistaIdOfConferencistaListConferencista = em.merge(oldTipoConferencistaIdOfConferencistaListConferencista);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoConferencista tipoConferencista) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoConferencista persistentTipoConferencista = em.find(TipoConferencista.class, tipoConferencista.getId());
            List<Conferencista> conferencistaListOld = persistentTipoConferencista.getConferencistaList();
            List<Conferencista> conferencistaListNew = tipoConferencista.getConferencistaList();
            List<String> illegalOrphanMessages = null;
            for (Conferencista conferencistaListOldConferencista : conferencistaListOld) {
                if (!conferencistaListNew.contains(conferencistaListOldConferencista)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Conferencista " + conferencistaListOldConferencista + " since its tipoConferencistaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Conferencista> attachedConferencistaListNew = new ArrayList<Conferencista>();
            for (Conferencista conferencistaListNewConferencistaToAttach : conferencistaListNew) {
                conferencistaListNewConferencistaToAttach = em.getReference(conferencistaListNewConferencistaToAttach.getClass(), conferencistaListNewConferencistaToAttach.getUsuarioDni());
                attachedConferencistaListNew.add(conferencistaListNewConferencistaToAttach);
            }
            conferencistaListNew = attachedConferencistaListNew;
            tipoConferencista.setConferencistaList(conferencistaListNew);
            tipoConferencista = em.merge(tipoConferencista);
            for (Conferencista conferencistaListNewConferencista : conferencistaListNew) {
                if (!conferencistaListOld.contains(conferencistaListNewConferencista)) {
                    TipoConferencista oldTipoConferencistaIdOfConferencistaListNewConferencista = conferencistaListNewConferencista.getTipoConferencistaId();
                    conferencistaListNewConferencista.setTipoConferencistaId(tipoConferencista);
                    conferencistaListNewConferencista = em.merge(conferencistaListNewConferencista);
                    if (oldTipoConferencistaIdOfConferencistaListNewConferencista != null && !oldTipoConferencistaIdOfConferencistaListNewConferencista.equals(tipoConferencista)) {
                        oldTipoConferencistaIdOfConferencistaListNewConferencista.getConferencistaList().remove(conferencistaListNewConferencista);
                        oldTipoConferencistaIdOfConferencistaListNewConferencista = em.merge(oldTipoConferencistaIdOfConferencistaListNewConferencista);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoConferencista.getId();
                if (findTipoConferencista(id) == null) {
                    throw new NonexistentEntityException("The tipoConferencista with id " + id + " no longer exists.");
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
            TipoConferencista tipoConferencista;
            try {
                tipoConferencista = em.getReference(TipoConferencista.class, id);
                tipoConferencista.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoConferencista with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Conferencista> conferencistaListOrphanCheck = tipoConferencista.getConferencistaList();
            for (Conferencista conferencistaListOrphanCheckConferencista : conferencistaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoConferencista (" + tipoConferencista + ") cannot be destroyed since the Conferencista " + conferencistaListOrphanCheckConferencista + " in its conferencistaList field has a non-nullable tipoConferencistaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoConferencista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoConferencista> findTipoConferencistaEntities() {
        return findTipoConferencistaEntities(true, -1, -1);
    }

    public List<TipoConferencista> findTipoConferencistaEntities(int maxResults, int firstResult) {
        return findTipoConferencistaEntities(false, maxResults, firstResult);
    }

    private List<TipoConferencista> findTipoConferencistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoConferencista.class));
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

    public TipoConferencista findTipoConferencista(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoConferencista.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoConferencistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoConferencista> rt = cq.from(TipoConferencista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
