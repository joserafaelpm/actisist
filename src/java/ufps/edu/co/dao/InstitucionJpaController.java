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
import ufps.edu.co.dto.Conferencista;
import ufps.edu.co.dto.PaisInstitucion;
import ufps.edu.co.dto.ActividadInstitucion;
import ufps.edu.co.dto.Institucion;

/**
 *
 * @author dunke
 */
public class InstitucionJpaController implements Serializable {

    public InstitucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Institucion institucion) {
        if (institucion.getConvenioList() == null) {
            institucion.setConvenioList(new ArrayList<Convenio>());
        }
        if (institucion.getConferencistaList() == null) {
            institucion.setConferencistaList(new ArrayList<Conferencista>());
        }
        if (institucion.getPaisInstitucionList() == null) {
            institucion.setPaisInstitucionList(new ArrayList<PaisInstitucion>());
        }
        if (institucion.getActividadInstitucionList() == null) {
            institucion.setActividadInstitucionList(new ArrayList<ActividadInstitucion>());
        }
        if (institucion.getActividadInstitucionList1() == null) {
            institucion.setActividadInstitucionList1(new ArrayList<ActividadInstitucion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Convenio> attachedConvenioList = new ArrayList<Convenio>();
            for (Convenio convenioListConvenioToAttach : institucion.getConvenioList()) {
                convenioListConvenioToAttach = em.getReference(convenioListConvenioToAttach.getClass(), convenioListConvenioToAttach.getId());
                attachedConvenioList.add(convenioListConvenioToAttach);
            }
            institucion.setConvenioList(attachedConvenioList);
            List<Conferencista> attachedConferencistaList = new ArrayList<Conferencista>();
            for (Conferencista conferencistaListConferencistaToAttach : institucion.getConferencistaList()) {
                conferencistaListConferencistaToAttach = em.getReference(conferencistaListConferencistaToAttach.getClass(), conferencistaListConferencistaToAttach.getUsuarioDni());
                attachedConferencistaList.add(conferencistaListConferencistaToAttach);
            }
            institucion.setConferencistaList(attachedConferencistaList);
            List<PaisInstitucion> attachedPaisInstitucionList = new ArrayList<PaisInstitucion>();
            for (PaisInstitucion paisInstitucionListPaisInstitucionToAttach : institucion.getPaisInstitucionList()) {
                paisInstitucionListPaisInstitucionToAttach = em.getReference(paisInstitucionListPaisInstitucionToAttach.getClass(), paisInstitucionListPaisInstitucionToAttach.getId());
                attachedPaisInstitucionList.add(paisInstitucionListPaisInstitucionToAttach);
            }
            institucion.setPaisInstitucionList(attachedPaisInstitucionList);
            List<ActividadInstitucion> attachedActividadInstitucionList = new ArrayList<ActividadInstitucion>();
            for (ActividadInstitucion actividadInstitucionListActividadInstitucionToAttach : institucion.getActividadInstitucionList()) {
                actividadInstitucionListActividadInstitucionToAttach = em.getReference(actividadInstitucionListActividadInstitucionToAttach.getClass(), actividadInstitucionListActividadInstitucionToAttach.getId());
                attachedActividadInstitucionList.add(actividadInstitucionListActividadInstitucionToAttach);
            }
            institucion.setActividadInstitucionList(attachedActividadInstitucionList);
            List<ActividadInstitucion> attachedActividadInstitucionList1 = new ArrayList<ActividadInstitucion>();
            for (ActividadInstitucion actividadInstitucionList1ActividadInstitucionToAttach : institucion.getActividadInstitucionList1()) {
                actividadInstitucionList1ActividadInstitucionToAttach = em.getReference(actividadInstitucionList1ActividadInstitucionToAttach.getClass(), actividadInstitucionList1ActividadInstitucionToAttach.getId());
                attachedActividadInstitucionList1.add(actividadInstitucionList1ActividadInstitucionToAttach);
            }
            institucion.setActividadInstitucionList1(attachedActividadInstitucionList1);
            em.persist(institucion);
            for (Convenio convenioListConvenio : institucion.getConvenioList()) {
                Institucion oldEmpresaOfConvenioListConvenio = convenioListConvenio.getEmpresa();
                convenioListConvenio.setEmpresa(institucion);
                convenioListConvenio = em.merge(convenioListConvenio);
                if (oldEmpresaOfConvenioListConvenio != null) {
                    oldEmpresaOfConvenioListConvenio.getConvenioList().remove(convenioListConvenio);
                    oldEmpresaOfConvenioListConvenio = em.merge(oldEmpresaOfConvenioListConvenio);
                }
            }
            for (Conferencista conferencistaListConferencista : institucion.getConferencistaList()) {
                Institucion oldInstitucionIdOfConferencistaListConferencista = conferencistaListConferencista.getInstitucionId();
                conferencistaListConferencista.setInstitucionId(institucion);
                conferencistaListConferencista = em.merge(conferencistaListConferencista);
                if (oldInstitucionIdOfConferencistaListConferencista != null) {
                    oldInstitucionIdOfConferencistaListConferencista.getConferencistaList().remove(conferencistaListConferencista);
                    oldInstitucionIdOfConferencistaListConferencista = em.merge(oldInstitucionIdOfConferencistaListConferencista);
                }
            }
            for (PaisInstitucion paisInstitucionListPaisInstitucion : institucion.getPaisInstitucionList()) {
                Institucion oldInstitucionIdOfPaisInstitucionListPaisInstitucion = paisInstitucionListPaisInstitucion.getInstitucionId();
                paisInstitucionListPaisInstitucion.setInstitucionId(institucion);
                paisInstitucionListPaisInstitucion = em.merge(paisInstitucionListPaisInstitucion);
                if (oldInstitucionIdOfPaisInstitucionListPaisInstitucion != null) {
                    oldInstitucionIdOfPaisInstitucionListPaisInstitucion.getPaisInstitucionList().remove(paisInstitucionListPaisInstitucion);
                    oldInstitucionIdOfPaisInstitucionListPaisInstitucion = em.merge(oldInstitucionIdOfPaisInstitucionListPaisInstitucion);
                }
            }
            for (ActividadInstitucion actividadInstitucionListActividadInstitucion : institucion.getActividadInstitucionList()) {
                Institucion oldInstitucionIdOfActividadInstitucionListActividadInstitucion = actividadInstitucionListActividadInstitucion.getInstitucionId();
                actividadInstitucionListActividadInstitucion.setInstitucionId(institucion);
                actividadInstitucionListActividadInstitucion = em.merge(actividadInstitucionListActividadInstitucion);
                if (oldInstitucionIdOfActividadInstitucionListActividadInstitucion != null) {
                    oldInstitucionIdOfActividadInstitucionListActividadInstitucion.getActividadInstitucionList().remove(actividadInstitucionListActividadInstitucion);
                    oldInstitucionIdOfActividadInstitucionListActividadInstitucion = em.merge(oldInstitucionIdOfActividadInstitucionListActividadInstitucion);
                }
            }
            for (ActividadInstitucion actividadInstitucionList1ActividadInstitucion : institucion.getActividadInstitucionList1()) {
                Institucion oldActividadIdOfActividadInstitucionList1ActividadInstitucion = actividadInstitucionList1ActividadInstitucion.getActividadId();
                actividadInstitucionList1ActividadInstitucion.setActividadId(institucion);
                actividadInstitucionList1ActividadInstitucion = em.merge(actividadInstitucionList1ActividadInstitucion);
                if (oldActividadIdOfActividadInstitucionList1ActividadInstitucion != null) {
                    oldActividadIdOfActividadInstitucionList1ActividadInstitucion.getActividadInstitucionList1().remove(actividadInstitucionList1ActividadInstitucion);
                    oldActividadIdOfActividadInstitucionList1ActividadInstitucion = em.merge(oldActividadIdOfActividadInstitucionList1ActividadInstitucion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Institucion institucion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Institucion persistentInstitucion = em.find(Institucion.class, institucion.getId());
            List<Convenio> convenioListOld = persistentInstitucion.getConvenioList();
            List<Convenio> convenioListNew = institucion.getConvenioList();
            List<Conferencista> conferencistaListOld = persistentInstitucion.getConferencistaList();
            List<Conferencista> conferencistaListNew = institucion.getConferencistaList();
            List<PaisInstitucion> paisInstitucionListOld = persistentInstitucion.getPaisInstitucionList();
            List<PaisInstitucion> paisInstitucionListNew = institucion.getPaisInstitucionList();
            List<ActividadInstitucion> actividadInstitucionListOld = persistentInstitucion.getActividadInstitucionList();
            List<ActividadInstitucion> actividadInstitucionListNew = institucion.getActividadInstitucionList();
            List<ActividadInstitucion> actividadInstitucionList1Old = persistentInstitucion.getActividadInstitucionList1();
            List<ActividadInstitucion> actividadInstitucionList1New = institucion.getActividadInstitucionList1();
            List<String> illegalOrphanMessages = null;
            for (Conferencista conferencistaListOldConferencista : conferencistaListOld) {
                if (!conferencistaListNew.contains(conferencistaListOldConferencista)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Conferencista " + conferencistaListOldConferencista + " since its institucionId field is not nullable.");
                }
            }
            for (PaisInstitucion paisInstitucionListOldPaisInstitucion : paisInstitucionListOld) {
                if (!paisInstitucionListNew.contains(paisInstitucionListOldPaisInstitucion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PaisInstitucion " + paisInstitucionListOldPaisInstitucion + " since its institucionId field is not nullable.");
                }
            }
            for (ActividadInstitucion actividadInstitucionListOldActividadInstitucion : actividadInstitucionListOld) {
                if (!actividadInstitucionListNew.contains(actividadInstitucionListOldActividadInstitucion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ActividadInstitucion " + actividadInstitucionListOldActividadInstitucion + " since its institucionId field is not nullable.");
                }
            }
            for (ActividadInstitucion actividadInstitucionList1OldActividadInstitucion : actividadInstitucionList1Old) {
                if (!actividadInstitucionList1New.contains(actividadInstitucionList1OldActividadInstitucion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ActividadInstitucion " + actividadInstitucionList1OldActividadInstitucion + " since its actividadId field is not nullable.");
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
            institucion.setConvenioList(convenioListNew);
            List<Conferencista> attachedConferencistaListNew = new ArrayList<Conferencista>();
            for (Conferencista conferencistaListNewConferencistaToAttach : conferencistaListNew) {
                conferencistaListNewConferencistaToAttach = em.getReference(conferencistaListNewConferencistaToAttach.getClass(), conferencistaListNewConferencistaToAttach.getUsuarioDni());
                attachedConferencistaListNew.add(conferencistaListNewConferencistaToAttach);
            }
            conferencistaListNew = attachedConferencistaListNew;
            institucion.setConferencistaList(conferencistaListNew);
            List<PaisInstitucion> attachedPaisInstitucionListNew = new ArrayList<PaisInstitucion>();
            for (PaisInstitucion paisInstitucionListNewPaisInstitucionToAttach : paisInstitucionListNew) {
                paisInstitucionListNewPaisInstitucionToAttach = em.getReference(paisInstitucionListNewPaisInstitucionToAttach.getClass(), paisInstitucionListNewPaisInstitucionToAttach.getId());
                attachedPaisInstitucionListNew.add(paisInstitucionListNewPaisInstitucionToAttach);
            }
            paisInstitucionListNew = attachedPaisInstitucionListNew;
            institucion.setPaisInstitucionList(paisInstitucionListNew);
            List<ActividadInstitucion> attachedActividadInstitucionListNew = new ArrayList<ActividadInstitucion>();
            for (ActividadInstitucion actividadInstitucionListNewActividadInstitucionToAttach : actividadInstitucionListNew) {
                actividadInstitucionListNewActividadInstitucionToAttach = em.getReference(actividadInstitucionListNewActividadInstitucionToAttach.getClass(), actividadInstitucionListNewActividadInstitucionToAttach.getId());
                attachedActividadInstitucionListNew.add(actividadInstitucionListNewActividadInstitucionToAttach);
            }
            actividadInstitucionListNew = attachedActividadInstitucionListNew;
            institucion.setActividadInstitucionList(actividadInstitucionListNew);
            List<ActividadInstitucion> attachedActividadInstitucionList1New = new ArrayList<ActividadInstitucion>();
            for (ActividadInstitucion actividadInstitucionList1NewActividadInstitucionToAttach : actividadInstitucionList1New) {
                actividadInstitucionList1NewActividadInstitucionToAttach = em.getReference(actividadInstitucionList1NewActividadInstitucionToAttach.getClass(), actividadInstitucionList1NewActividadInstitucionToAttach.getId());
                attachedActividadInstitucionList1New.add(actividadInstitucionList1NewActividadInstitucionToAttach);
            }
            actividadInstitucionList1New = attachedActividadInstitucionList1New;
            institucion.setActividadInstitucionList1(actividadInstitucionList1New);
            institucion = em.merge(institucion);
            for (Convenio convenioListOldConvenio : convenioListOld) {
                if (!convenioListNew.contains(convenioListOldConvenio)) {
                    convenioListOldConvenio.setEmpresa(null);
                    convenioListOldConvenio = em.merge(convenioListOldConvenio);
                }
            }
            for (Convenio convenioListNewConvenio : convenioListNew) {
                if (!convenioListOld.contains(convenioListNewConvenio)) {
                    Institucion oldEmpresaOfConvenioListNewConvenio = convenioListNewConvenio.getEmpresa();
                    convenioListNewConvenio.setEmpresa(institucion);
                    convenioListNewConvenio = em.merge(convenioListNewConvenio);
                    if (oldEmpresaOfConvenioListNewConvenio != null && !oldEmpresaOfConvenioListNewConvenio.equals(institucion)) {
                        oldEmpresaOfConvenioListNewConvenio.getConvenioList().remove(convenioListNewConvenio);
                        oldEmpresaOfConvenioListNewConvenio = em.merge(oldEmpresaOfConvenioListNewConvenio);
                    }
                }
            }
            for (Conferencista conferencistaListNewConferencista : conferencistaListNew) {
                if (!conferencistaListOld.contains(conferencistaListNewConferencista)) {
                    Institucion oldInstitucionIdOfConferencistaListNewConferencista = conferencistaListNewConferencista.getInstitucionId();
                    conferencistaListNewConferencista.setInstitucionId(institucion);
                    conferencistaListNewConferencista = em.merge(conferencistaListNewConferencista);
                    if (oldInstitucionIdOfConferencistaListNewConferencista != null && !oldInstitucionIdOfConferencistaListNewConferencista.equals(institucion)) {
                        oldInstitucionIdOfConferencistaListNewConferencista.getConferencistaList().remove(conferencistaListNewConferencista);
                        oldInstitucionIdOfConferencistaListNewConferencista = em.merge(oldInstitucionIdOfConferencistaListNewConferencista);
                    }
                }
            }
            for (PaisInstitucion paisInstitucionListNewPaisInstitucion : paisInstitucionListNew) {
                if (!paisInstitucionListOld.contains(paisInstitucionListNewPaisInstitucion)) {
                    Institucion oldInstitucionIdOfPaisInstitucionListNewPaisInstitucion = paisInstitucionListNewPaisInstitucion.getInstitucionId();
                    paisInstitucionListNewPaisInstitucion.setInstitucionId(institucion);
                    paisInstitucionListNewPaisInstitucion = em.merge(paisInstitucionListNewPaisInstitucion);
                    if (oldInstitucionIdOfPaisInstitucionListNewPaisInstitucion != null && !oldInstitucionIdOfPaisInstitucionListNewPaisInstitucion.equals(institucion)) {
                        oldInstitucionIdOfPaisInstitucionListNewPaisInstitucion.getPaisInstitucionList().remove(paisInstitucionListNewPaisInstitucion);
                        oldInstitucionIdOfPaisInstitucionListNewPaisInstitucion = em.merge(oldInstitucionIdOfPaisInstitucionListNewPaisInstitucion);
                    }
                }
            }
            for (ActividadInstitucion actividadInstitucionListNewActividadInstitucion : actividadInstitucionListNew) {
                if (!actividadInstitucionListOld.contains(actividadInstitucionListNewActividadInstitucion)) {
                    Institucion oldInstitucionIdOfActividadInstitucionListNewActividadInstitucion = actividadInstitucionListNewActividadInstitucion.getInstitucionId();
                    actividadInstitucionListNewActividadInstitucion.setInstitucionId(institucion);
                    actividadInstitucionListNewActividadInstitucion = em.merge(actividadInstitucionListNewActividadInstitucion);
                    if (oldInstitucionIdOfActividadInstitucionListNewActividadInstitucion != null && !oldInstitucionIdOfActividadInstitucionListNewActividadInstitucion.equals(institucion)) {
                        oldInstitucionIdOfActividadInstitucionListNewActividadInstitucion.getActividadInstitucionList().remove(actividadInstitucionListNewActividadInstitucion);
                        oldInstitucionIdOfActividadInstitucionListNewActividadInstitucion = em.merge(oldInstitucionIdOfActividadInstitucionListNewActividadInstitucion);
                    }
                }
            }
            for (ActividadInstitucion actividadInstitucionList1NewActividadInstitucion : actividadInstitucionList1New) {
                if (!actividadInstitucionList1Old.contains(actividadInstitucionList1NewActividadInstitucion)) {
                    Institucion oldActividadIdOfActividadInstitucionList1NewActividadInstitucion = actividadInstitucionList1NewActividadInstitucion.getActividadId();
                    actividadInstitucionList1NewActividadInstitucion.setActividadId(institucion);
                    actividadInstitucionList1NewActividadInstitucion = em.merge(actividadInstitucionList1NewActividadInstitucion);
                    if (oldActividadIdOfActividadInstitucionList1NewActividadInstitucion != null && !oldActividadIdOfActividadInstitucionList1NewActividadInstitucion.equals(institucion)) {
                        oldActividadIdOfActividadInstitucionList1NewActividadInstitucion.getActividadInstitucionList1().remove(actividadInstitucionList1NewActividadInstitucion);
                        oldActividadIdOfActividadInstitucionList1NewActividadInstitucion = em.merge(oldActividadIdOfActividadInstitucionList1NewActividadInstitucion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = institucion.getId();
                if (findInstitucion(id) == null) {
                    throw new NonexistentEntityException("The institucion with id " + id + " no longer exists.");
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
            Institucion institucion;
            try {
                institucion = em.getReference(Institucion.class, id);
                institucion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The institucion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Conferencista> conferencistaListOrphanCheck = institucion.getConferencistaList();
            for (Conferencista conferencistaListOrphanCheckConferencista : conferencistaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Institucion (" + institucion + ") cannot be destroyed since the Conferencista " + conferencistaListOrphanCheckConferencista + " in its conferencistaList field has a non-nullable institucionId field.");
            }
            List<PaisInstitucion> paisInstitucionListOrphanCheck = institucion.getPaisInstitucionList();
            for (PaisInstitucion paisInstitucionListOrphanCheckPaisInstitucion : paisInstitucionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Institucion (" + institucion + ") cannot be destroyed since the PaisInstitucion " + paisInstitucionListOrphanCheckPaisInstitucion + " in its paisInstitucionList field has a non-nullable institucionId field.");
            }
            List<ActividadInstitucion> actividadInstitucionListOrphanCheck = institucion.getActividadInstitucionList();
            for (ActividadInstitucion actividadInstitucionListOrphanCheckActividadInstitucion : actividadInstitucionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Institucion (" + institucion + ") cannot be destroyed since the ActividadInstitucion " + actividadInstitucionListOrphanCheckActividadInstitucion + " in its actividadInstitucionList field has a non-nullable institucionId field.");
            }
            List<ActividadInstitucion> actividadInstitucionList1OrphanCheck = institucion.getActividadInstitucionList1();
            for (ActividadInstitucion actividadInstitucionList1OrphanCheckActividadInstitucion : actividadInstitucionList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Institucion (" + institucion + ") cannot be destroyed since the ActividadInstitucion " + actividadInstitucionList1OrphanCheckActividadInstitucion + " in its actividadInstitucionList1 field has a non-nullable actividadId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Convenio> convenioList = institucion.getConvenioList();
            for (Convenio convenioListConvenio : convenioList) {
                convenioListConvenio.setEmpresa(null);
                convenioListConvenio = em.merge(convenioListConvenio);
            }
            em.remove(institucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Institucion> findInstitucionEntities() {
        return findInstitucionEntities(true, -1, -1);
    }

    public List<Institucion> findInstitucionEntities(int maxResults, int firstResult) {
        return findInstitucionEntities(false, maxResults, firstResult);
    }

    private List<Institucion> findInstitucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Institucion.class));
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

    public Institucion findInstitucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Institucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstitucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Institucion> rt = cq.from(Institucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
