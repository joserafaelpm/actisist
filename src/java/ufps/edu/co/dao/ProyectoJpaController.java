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
import ufps.edu.co.dto.Contrato;
import ufps.edu.co.dto.ProyectoEntregable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.Proyecto;

/**
 *
 * @author dunke
 */
public class ProyectoJpaController implements Serializable {

    public ProyectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proyecto proyecto) {
        if (proyecto.getProyectoEntregableList() == null) {
            proyecto.setProyectoEntregableList(new ArrayList<ProyectoEntregable>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad actividadId = proyecto.getActividadId();
            if (actividadId != null) {
                actividadId = em.getReference(actividadId.getClass(), actividadId.getId());
                proyecto.setActividadId(actividadId);
            }
            Contrato contratoId = proyecto.getContratoId();
            if (contratoId != null) {
                contratoId = em.getReference(contratoId.getClass(), contratoId.getId());
                proyecto.setContratoId(contratoId);
            }
            List<ProyectoEntregable> attachedProyectoEntregableList = new ArrayList<ProyectoEntregable>();
            for (ProyectoEntregable proyectoEntregableListProyectoEntregableToAttach : proyecto.getProyectoEntregableList()) {
                proyectoEntregableListProyectoEntregableToAttach = em.getReference(proyectoEntregableListProyectoEntregableToAttach.getClass(), proyectoEntregableListProyectoEntregableToAttach.getId());
                attachedProyectoEntregableList.add(proyectoEntregableListProyectoEntregableToAttach);
            }
            proyecto.setProyectoEntregableList(attachedProyectoEntregableList);
            em.persist(proyecto);
            if (actividadId != null) {
                actividadId.getProyectoList().add(proyecto);
                actividadId = em.merge(actividadId);
            }
            if (contratoId != null) {
                contratoId.getProyectoList().add(proyecto);
                contratoId = em.merge(contratoId);
            }
            for (ProyectoEntregable proyectoEntregableListProyectoEntregable : proyecto.getProyectoEntregableList()) {
                Proyecto oldProyectoIdOfProyectoEntregableListProyectoEntregable = proyectoEntregableListProyectoEntregable.getProyectoId();
                proyectoEntregableListProyectoEntregable.setProyectoId(proyecto);
                proyectoEntregableListProyectoEntregable = em.merge(proyectoEntregableListProyectoEntregable);
                if (oldProyectoIdOfProyectoEntregableListProyectoEntregable != null) {
                    oldProyectoIdOfProyectoEntregableListProyectoEntregable.getProyectoEntregableList().remove(proyectoEntregableListProyectoEntregable);
                    oldProyectoIdOfProyectoEntregableListProyectoEntregable = em.merge(oldProyectoIdOfProyectoEntregableListProyectoEntregable);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proyecto proyecto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto persistentProyecto = em.find(Proyecto.class, proyecto.getId());
            Actividad actividadIdOld = persistentProyecto.getActividadId();
            Actividad actividadIdNew = proyecto.getActividadId();
            Contrato contratoIdOld = persistentProyecto.getContratoId();
            Contrato contratoIdNew = proyecto.getContratoId();
            List<ProyectoEntregable> proyectoEntregableListOld = persistentProyecto.getProyectoEntregableList();
            List<ProyectoEntregable> proyectoEntregableListNew = proyecto.getProyectoEntregableList();
            List<String> illegalOrphanMessages = null;
            for (ProyectoEntregable proyectoEntregableListOldProyectoEntregable : proyectoEntregableListOld) {
                if (!proyectoEntregableListNew.contains(proyectoEntregableListOldProyectoEntregable)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProyectoEntregable " + proyectoEntregableListOldProyectoEntregable + " since its proyectoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (actividadIdNew != null) {
                actividadIdNew = em.getReference(actividadIdNew.getClass(), actividadIdNew.getId());
                proyecto.setActividadId(actividadIdNew);
            }
            if (contratoIdNew != null) {
                contratoIdNew = em.getReference(contratoIdNew.getClass(), contratoIdNew.getId());
                proyecto.setContratoId(contratoIdNew);
            }
            List<ProyectoEntregable> attachedProyectoEntregableListNew = new ArrayList<ProyectoEntregable>();
            for (ProyectoEntregable proyectoEntregableListNewProyectoEntregableToAttach : proyectoEntregableListNew) {
                proyectoEntregableListNewProyectoEntregableToAttach = em.getReference(proyectoEntregableListNewProyectoEntregableToAttach.getClass(), proyectoEntregableListNewProyectoEntregableToAttach.getId());
                attachedProyectoEntregableListNew.add(proyectoEntregableListNewProyectoEntregableToAttach);
            }
            proyectoEntregableListNew = attachedProyectoEntregableListNew;
            proyecto.setProyectoEntregableList(proyectoEntregableListNew);
            proyecto = em.merge(proyecto);
            if (actividadIdOld != null && !actividadIdOld.equals(actividadIdNew)) {
                actividadIdOld.getProyectoList().remove(proyecto);
                actividadIdOld = em.merge(actividadIdOld);
            }
            if (actividadIdNew != null && !actividadIdNew.equals(actividadIdOld)) {
                actividadIdNew.getProyectoList().add(proyecto);
                actividadIdNew = em.merge(actividadIdNew);
            }
            if (contratoIdOld != null && !contratoIdOld.equals(contratoIdNew)) {
                contratoIdOld.getProyectoList().remove(proyecto);
                contratoIdOld = em.merge(contratoIdOld);
            }
            if (contratoIdNew != null && !contratoIdNew.equals(contratoIdOld)) {
                contratoIdNew.getProyectoList().add(proyecto);
                contratoIdNew = em.merge(contratoIdNew);
            }
            for (ProyectoEntregable proyectoEntregableListNewProyectoEntregable : proyectoEntregableListNew) {
                if (!proyectoEntregableListOld.contains(proyectoEntregableListNewProyectoEntregable)) {
                    Proyecto oldProyectoIdOfProyectoEntregableListNewProyectoEntregable = proyectoEntregableListNewProyectoEntregable.getProyectoId();
                    proyectoEntregableListNewProyectoEntregable.setProyectoId(proyecto);
                    proyectoEntregableListNewProyectoEntregable = em.merge(proyectoEntregableListNewProyectoEntregable);
                    if (oldProyectoIdOfProyectoEntregableListNewProyectoEntregable != null && !oldProyectoIdOfProyectoEntregableListNewProyectoEntregable.equals(proyecto)) {
                        oldProyectoIdOfProyectoEntregableListNewProyectoEntregable.getProyectoEntregableList().remove(proyectoEntregableListNewProyectoEntregable);
                        oldProyectoIdOfProyectoEntregableListNewProyectoEntregable = em.merge(oldProyectoIdOfProyectoEntregableListNewProyectoEntregable);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proyecto.getId();
                if (findProyecto(id) == null) {
                    throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.");
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
            Proyecto proyecto;
            try {
                proyecto = em.getReference(Proyecto.class, id);
                proyecto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProyectoEntregable> proyectoEntregableListOrphanCheck = proyecto.getProyectoEntregableList();
            for (ProyectoEntregable proyectoEntregableListOrphanCheckProyectoEntregable : proyectoEntregableListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proyecto (" + proyecto + ") cannot be destroyed since the ProyectoEntregable " + proyectoEntregableListOrphanCheckProyectoEntregable + " in its proyectoEntregableList field has a non-nullable proyectoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Actividad actividadId = proyecto.getActividadId();
            if (actividadId != null) {
                actividadId.getProyectoList().remove(proyecto);
                actividadId = em.merge(actividadId);
            }
            Contrato contratoId = proyecto.getContratoId();
            if (contratoId != null) {
                contratoId.getProyectoList().remove(proyecto);
                contratoId = em.merge(contratoId);
            }
            em.remove(proyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proyecto> findProyectoEntities() {
        return findProyectoEntities(true, -1, -1);
    }

    public List<Proyecto> findProyectoEntities(int maxResults, int firstResult) {
        return findProyectoEntities(false, maxResults, firstResult);
    }

    private List<Proyecto> findProyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proyecto.class));
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

    public Proyecto findProyecto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proyecto> rt = cq.from(Proyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
