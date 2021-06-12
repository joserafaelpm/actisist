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
import ufps.edu.co.dto.PaisInstitucion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.Conferencista;
import ufps.edu.co.dto.Pais;

/**
 *
 * @author dunke
 */
public class PaisJpaController implements Serializable {

    public PaisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pais pais) {
        if (pais.getPaisInstitucionList() == null) {
            pais.setPaisInstitucionList(new ArrayList<PaisInstitucion>());
        }
        if (pais.getConferencistaList() == null) {
            pais.setConferencistaList(new ArrayList<Conferencista>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PaisInstitucion> attachedPaisInstitucionList = new ArrayList<PaisInstitucion>();
            for (PaisInstitucion paisInstitucionListPaisInstitucionToAttach : pais.getPaisInstitucionList()) {
                paisInstitucionListPaisInstitucionToAttach = em.getReference(paisInstitucionListPaisInstitucionToAttach.getClass(), paisInstitucionListPaisInstitucionToAttach.getId());
                attachedPaisInstitucionList.add(paisInstitucionListPaisInstitucionToAttach);
            }
            pais.setPaisInstitucionList(attachedPaisInstitucionList);
            List<Conferencista> attachedConferencistaList = new ArrayList<Conferencista>();
            for (Conferencista conferencistaListConferencistaToAttach : pais.getConferencistaList()) {
                conferencistaListConferencistaToAttach = em.getReference(conferencistaListConferencistaToAttach.getClass(), conferencistaListConferencistaToAttach.getUsuarioDni());
                attachedConferencistaList.add(conferencistaListConferencistaToAttach);
            }
            pais.setConferencistaList(attachedConferencistaList);
            em.persist(pais);
            for (PaisInstitucion paisInstitucionListPaisInstitucion : pais.getPaisInstitucionList()) {
                Pais oldPaisIdOfPaisInstitucionListPaisInstitucion = paisInstitucionListPaisInstitucion.getPaisId();
                paisInstitucionListPaisInstitucion.setPaisId(pais);
                paisInstitucionListPaisInstitucion = em.merge(paisInstitucionListPaisInstitucion);
                if (oldPaisIdOfPaisInstitucionListPaisInstitucion != null) {
                    oldPaisIdOfPaisInstitucionListPaisInstitucion.getPaisInstitucionList().remove(paisInstitucionListPaisInstitucion);
                    oldPaisIdOfPaisInstitucionListPaisInstitucion = em.merge(oldPaisIdOfPaisInstitucionListPaisInstitucion);
                }
            }
            for (Conferencista conferencistaListConferencista : pais.getConferencistaList()) {
                Pais oldPaisOrigenOfConferencistaListConferencista = conferencistaListConferencista.getPaisOrigen();
                conferencistaListConferencista.setPaisOrigen(pais);
                conferencistaListConferencista = em.merge(conferencistaListConferencista);
                if (oldPaisOrigenOfConferencistaListConferencista != null) {
                    oldPaisOrigenOfConferencistaListConferencista.getConferencistaList().remove(conferencistaListConferencista);
                    oldPaisOrigenOfConferencistaListConferencista = em.merge(oldPaisOrigenOfConferencistaListConferencista);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pais pais) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais persistentPais = em.find(Pais.class, pais.getId());
            List<PaisInstitucion> paisInstitucionListOld = persistentPais.getPaisInstitucionList();
            List<PaisInstitucion> paisInstitucionListNew = pais.getPaisInstitucionList();
            List<Conferencista> conferencistaListOld = persistentPais.getConferencistaList();
            List<Conferencista> conferencistaListNew = pais.getConferencistaList();
            List<String> illegalOrphanMessages = null;
            for (PaisInstitucion paisInstitucionListOldPaisInstitucion : paisInstitucionListOld) {
                if (!paisInstitucionListNew.contains(paisInstitucionListOldPaisInstitucion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PaisInstitucion " + paisInstitucionListOldPaisInstitucion + " since its paisId field is not nullable.");
                }
            }
            for (Conferencista conferencistaListOldConferencista : conferencistaListOld) {
                if (!conferencistaListNew.contains(conferencistaListOldConferencista)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Conferencista " + conferencistaListOldConferencista + " since its paisOrigen field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PaisInstitucion> attachedPaisInstitucionListNew = new ArrayList<PaisInstitucion>();
            for (PaisInstitucion paisInstitucionListNewPaisInstitucionToAttach : paisInstitucionListNew) {
                paisInstitucionListNewPaisInstitucionToAttach = em.getReference(paisInstitucionListNewPaisInstitucionToAttach.getClass(), paisInstitucionListNewPaisInstitucionToAttach.getId());
                attachedPaisInstitucionListNew.add(paisInstitucionListNewPaisInstitucionToAttach);
            }
            paisInstitucionListNew = attachedPaisInstitucionListNew;
            pais.setPaisInstitucionList(paisInstitucionListNew);
            List<Conferencista> attachedConferencistaListNew = new ArrayList<Conferencista>();
            for (Conferencista conferencistaListNewConferencistaToAttach : conferencistaListNew) {
                conferencistaListNewConferencistaToAttach = em.getReference(conferencistaListNewConferencistaToAttach.getClass(), conferencistaListNewConferencistaToAttach.getUsuarioDni());
                attachedConferencistaListNew.add(conferencistaListNewConferencistaToAttach);
            }
            conferencistaListNew = attachedConferencistaListNew;
            pais.setConferencistaList(conferencistaListNew);
            pais = em.merge(pais);
            for (PaisInstitucion paisInstitucionListNewPaisInstitucion : paisInstitucionListNew) {
                if (!paisInstitucionListOld.contains(paisInstitucionListNewPaisInstitucion)) {
                    Pais oldPaisIdOfPaisInstitucionListNewPaisInstitucion = paisInstitucionListNewPaisInstitucion.getPaisId();
                    paisInstitucionListNewPaisInstitucion.setPaisId(pais);
                    paisInstitucionListNewPaisInstitucion = em.merge(paisInstitucionListNewPaisInstitucion);
                    if (oldPaisIdOfPaisInstitucionListNewPaisInstitucion != null && !oldPaisIdOfPaisInstitucionListNewPaisInstitucion.equals(pais)) {
                        oldPaisIdOfPaisInstitucionListNewPaisInstitucion.getPaisInstitucionList().remove(paisInstitucionListNewPaisInstitucion);
                        oldPaisIdOfPaisInstitucionListNewPaisInstitucion = em.merge(oldPaisIdOfPaisInstitucionListNewPaisInstitucion);
                    }
                }
            }
            for (Conferencista conferencistaListNewConferencista : conferencistaListNew) {
                if (!conferencistaListOld.contains(conferencistaListNewConferencista)) {
                    Pais oldPaisOrigenOfConferencistaListNewConferencista = conferencistaListNewConferencista.getPaisOrigen();
                    conferencistaListNewConferencista.setPaisOrigen(pais);
                    conferencistaListNewConferencista = em.merge(conferencistaListNewConferencista);
                    if (oldPaisOrigenOfConferencistaListNewConferencista != null && !oldPaisOrigenOfConferencistaListNewConferencista.equals(pais)) {
                        oldPaisOrigenOfConferencistaListNewConferencista.getConferencistaList().remove(conferencistaListNewConferencista);
                        oldPaisOrigenOfConferencistaListNewConferencista = em.merge(oldPaisOrigenOfConferencistaListNewConferencista);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pais.getId();
                if (findPais(id) == null) {
                    throw new NonexistentEntityException("The pais with id " + id + " no longer exists.");
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
            Pais pais;
            try {
                pais = em.getReference(Pais.class, id);
                pais.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pais with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PaisInstitucion> paisInstitucionListOrphanCheck = pais.getPaisInstitucionList();
            for (PaisInstitucion paisInstitucionListOrphanCheckPaisInstitucion : paisInstitucionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pais (" + pais + ") cannot be destroyed since the PaisInstitucion " + paisInstitucionListOrphanCheckPaisInstitucion + " in its paisInstitucionList field has a non-nullable paisId field.");
            }
            List<Conferencista> conferencistaListOrphanCheck = pais.getConferencistaList();
            for (Conferencista conferencistaListOrphanCheckConferencista : conferencistaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pais (" + pais + ") cannot be destroyed since the Conferencista " + conferencistaListOrphanCheckConferencista + " in its conferencistaList field has a non-nullable paisOrigen field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pais> findPaisEntities() {
        return findPaisEntities(true, -1, -1);
    }

    public List<Pais> findPaisEntities(int maxResults, int firstResult) {
        return findPaisEntities(false, maxResults, firstResult);
    }

    private List<Pais> findPaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pais.class));
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

    public Pais findPais(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pais.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pais> rt = cq.from(Pais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
