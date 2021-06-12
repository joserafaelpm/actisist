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
import ufps.edu.co.dto.Institucion;
import ufps.edu.co.dto.TipoConvenio;
import ufps.edu.co.dto.ConvenioActividad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.Convenio;

/**
 *
 * @author dunke
 */
public class ConvenioJpaController implements Serializable {

    public ConvenioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Convenio convenio) {
        if (convenio.getConvenioActividadList() == null) {
            convenio.setConvenioActividadList(new ArrayList<ConvenioActividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Institucion empresa = convenio.getEmpresa();
            if (empresa != null) {
                empresa = em.getReference(empresa.getClass(), empresa.getId());
                convenio.setEmpresa(empresa);
            }
            TipoConvenio tipoConvenio = convenio.getTipoConvenio();
            if (tipoConvenio != null) {
                tipoConvenio = em.getReference(tipoConvenio.getClass(), tipoConvenio.getId());
                convenio.setTipoConvenio(tipoConvenio);
            }
            List<ConvenioActividad> attachedConvenioActividadList = new ArrayList<ConvenioActividad>();
            for (ConvenioActividad convenioActividadListConvenioActividadToAttach : convenio.getConvenioActividadList()) {
                convenioActividadListConvenioActividadToAttach = em.getReference(convenioActividadListConvenioActividadToAttach.getClass(), convenioActividadListConvenioActividadToAttach.getId());
                attachedConvenioActividadList.add(convenioActividadListConvenioActividadToAttach);
            }
            convenio.setConvenioActividadList(attachedConvenioActividadList);
            em.persist(convenio);
            if (empresa != null) {
                empresa.getConvenioList().add(convenio);
                empresa = em.merge(empresa);
            }
            if (tipoConvenio != null) {
                tipoConvenio.getConvenioList().add(convenio);
                tipoConvenio = em.merge(tipoConvenio);
            }
            for (ConvenioActividad convenioActividadListConvenioActividad : convenio.getConvenioActividadList()) {
                Convenio oldConvenioIdOfConvenioActividadListConvenioActividad = convenioActividadListConvenioActividad.getConvenioId();
                convenioActividadListConvenioActividad.setConvenioId(convenio);
                convenioActividadListConvenioActividad = em.merge(convenioActividadListConvenioActividad);
                if (oldConvenioIdOfConvenioActividadListConvenioActividad != null) {
                    oldConvenioIdOfConvenioActividadListConvenioActividad.getConvenioActividadList().remove(convenioActividadListConvenioActividad);
                    oldConvenioIdOfConvenioActividadListConvenioActividad = em.merge(oldConvenioIdOfConvenioActividadListConvenioActividad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Convenio convenio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Convenio persistentConvenio = em.find(Convenio.class, convenio.getId());
            Institucion empresaOld = persistentConvenio.getEmpresa();
            Institucion empresaNew = convenio.getEmpresa();
            TipoConvenio tipoConvenioOld = persistentConvenio.getTipoConvenio();
            TipoConvenio tipoConvenioNew = convenio.getTipoConvenio();
            List<ConvenioActividad> convenioActividadListOld = persistentConvenio.getConvenioActividadList();
            List<ConvenioActividad> convenioActividadListNew = convenio.getConvenioActividadList();
            List<String> illegalOrphanMessages = null;
            for (ConvenioActividad convenioActividadListOldConvenioActividad : convenioActividadListOld) {
                if (!convenioActividadListNew.contains(convenioActividadListOldConvenioActividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ConvenioActividad " + convenioActividadListOldConvenioActividad + " since its convenioId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (empresaNew != null) {
                empresaNew = em.getReference(empresaNew.getClass(), empresaNew.getId());
                convenio.setEmpresa(empresaNew);
            }
            if (tipoConvenioNew != null) {
                tipoConvenioNew = em.getReference(tipoConvenioNew.getClass(), tipoConvenioNew.getId());
                convenio.setTipoConvenio(tipoConvenioNew);
            }
            List<ConvenioActividad> attachedConvenioActividadListNew = new ArrayList<ConvenioActividad>();
            for (ConvenioActividad convenioActividadListNewConvenioActividadToAttach : convenioActividadListNew) {
                convenioActividadListNewConvenioActividadToAttach = em.getReference(convenioActividadListNewConvenioActividadToAttach.getClass(), convenioActividadListNewConvenioActividadToAttach.getId());
                attachedConvenioActividadListNew.add(convenioActividadListNewConvenioActividadToAttach);
            }
            convenioActividadListNew = attachedConvenioActividadListNew;
            convenio.setConvenioActividadList(convenioActividadListNew);
            convenio = em.merge(convenio);
            if (empresaOld != null && !empresaOld.equals(empresaNew)) {
                empresaOld.getConvenioList().remove(convenio);
                empresaOld = em.merge(empresaOld);
            }
            if (empresaNew != null && !empresaNew.equals(empresaOld)) {
                empresaNew.getConvenioList().add(convenio);
                empresaNew = em.merge(empresaNew);
            }
            if (tipoConvenioOld != null && !tipoConvenioOld.equals(tipoConvenioNew)) {
                tipoConvenioOld.getConvenioList().remove(convenio);
                tipoConvenioOld = em.merge(tipoConvenioOld);
            }
            if (tipoConvenioNew != null && !tipoConvenioNew.equals(tipoConvenioOld)) {
                tipoConvenioNew.getConvenioList().add(convenio);
                tipoConvenioNew = em.merge(tipoConvenioNew);
            }
            for (ConvenioActividad convenioActividadListNewConvenioActividad : convenioActividadListNew) {
                if (!convenioActividadListOld.contains(convenioActividadListNewConvenioActividad)) {
                    Convenio oldConvenioIdOfConvenioActividadListNewConvenioActividad = convenioActividadListNewConvenioActividad.getConvenioId();
                    convenioActividadListNewConvenioActividad.setConvenioId(convenio);
                    convenioActividadListNewConvenioActividad = em.merge(convenioActividadListNewConvenioActividad);
                    if (oldConvenioIdOfConvenioActividadListNewConvenioActividad != null && !oldConvenioIdOfConvenioActividadListNewConvenioActividad.equals(convenio)) {
                        oldConvenioIdOfConvenioActividadListNewConvenioActividad.getConvenioActividadList().remove(convenioActividadListNewConvenioActividad);
                        oldConvenioIdOfConvenioActividadListNewConvenioActividad = em.merge(oldConvenioIdOfConvenioActividadListNewConvenioActividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = convenio.getId();
                if (findConvenio(id) == null) {
                    throw new NonexistentEntityException("The convenio with id " + id + " no longer exists.");
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
            Convenio convenio;
            try {
                convenio = em.getReference(Convenio.class, id);
                convenio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The convenio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ConvenioActividad> convenioActividadListOrphanCheck = convenio.getConvenioActividadList();
            for (ConvenioActividad convenioActividadListOrphanCheckConvenioActividad : convenioActividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Convenio (" + convenio + ") cannot be destroyed since the ConvenioActividad " + convenioActividadListOrphanCheckConvenioActividad + " in its convenioActividadList field has a non-nullable convenioId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Institucion empresa = convenio.getEmpresa();
            if (empresa != null) {
                empresa.getConvenioList().remove(convenio);
                empresa = em.merge(empresa);
            }
            TipoConvenio tipoConvenio = convenio.getTipoConvenio();
            if (tipoConvenio != null) {
                tipoConvenio.getConvenioList().remove(convenio);
                tipoConvenio = em.merge(tipoConvenio);
            }
            em.remove(convenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Convenio> findConvenioEntities() {
        return findConvenioEntities(true, -1, -1);
    }

    public List<Convenio> findConvenioEntities(int maxResults, int firstResult) {
        return findConvenioEntities(false, maxResults, firstResult);
    }

    private List<Convenio> findConvenioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Convenio.class));
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

    public Convenio findConvenio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Convenio.class, id);
        } finally {
            em.close();
        }
    }

    public int getConvenioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Convenio> rt = cq.from(Convenio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
