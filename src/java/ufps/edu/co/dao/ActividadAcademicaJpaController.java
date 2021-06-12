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
import ufps.edu.co.dto.TipoMovilidad;
import ufps.edu.co.dto.Horario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.ActividadAcademica;
import ufps.edu.co.dto.ConvenioActividad;
import ufps.edu.co.dto.ConferencistaActividad;

/**
 *
 * @author dunke
 */
public class ActividadAcademicaJpaController implements Serializable {

    public ActividadAcademicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ActividadAcademica actividadAcademica) {
        if (actividadAcademica.getHorarioList() == null) {
            actividadAcademica.setHorarioList(new ArrayList<Horario>());
        }
        if (actividadAcademica.getConvenioActividadList() == null) {
            actividadAcademica.setConvenioActividadList(new ArrayList<ConvenioActividad>());
        }
        if (actividadAcademica.getConferencistaActividadList() == null) {
            actividadAcademica.setConferencistaActividadList(new ArrayList<ConferencistaActividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad actividadId = actividadAcademica.getActividadId();
            if (actividadId != null) {
                actividadId = em.getReference(actividadId.getClass(), actividadId.getId());
                actividadAcademica.setActividadId(actividadId);
            }
            TipoMovilidad tipoMovilidadId = actividadAcademica.getTipoMovilidadId();
            if (tipoMovilidadId != null) {
                tipoMovilidadId = em.getReference(tipoMovilidadId.getClass(), tipoMovilidadId.getId());
                actividadAcademica.setTipoMovilidadId(tipoMovilidadId);
            }
            List<Horario> attachedHorarioList = new ArrayList<Horario>();
            for (Horario horarioListHorarioToAttach : actividadAcademica.getHorarioList()) {
                horarioListHorarioToAttach = em.getReference(horarioListHorarioToAttach.getClass(), horarioListHorarioToAttach.getId());
                attachedHorarioList.add(horarioListHorarioToAttach);
            }
            actividadAcademica.setHorarioList(attachedHorarioList);
            List<ConvenioActividad> attachedConvenioActividadList = new ArrayList<ConvenioActividad>();
            for (ConvenioActividad convenioActividadListConvenioActividadToAttach : actividadAcademica.getConvenioActividadList()) {
                convenioActividadListConvenioActividadToAttach = em.getReference(convenioActividadListConvenioActividadToAttach.getClass(), convenioActividadListConvenioActividadToAttach.getId());
                attachedConvenioActividadList.add(convenioActividadListConvenioActividadToAttach);
            }
            actividadAcademica.setConvenioActividadList(attachedConvenioActividadList);
            List<ConferencistaActividad> attachedConferencistaActividadList = new ArrayList<ConferencistaActividad>();
            for (ConferencistaActividad conferencistaActividadListConferencistaActividadToAttach : actividadAcademica.getConferencistaActividadList()) {
                conferencistaActividadListConferencistaActividadToAttach = em.getReference(conferencistaActividadListConferencistaActividadToAttach.getClass(), conferencistaActividadListConferencistaActividadToAttach.getId());
                attachedConferencistaActividadList.add(conferencistaActividadListConferencistaActividadToAttach);
            }
            actividadAcademica.setConferencistaActividadList(attachedConferencistaActividadList);
            em.persist(actividadAcademica);
            if (actividadId != null) {
                actividadId.getActividadAcademicaList().add(actividadAcademica);
                actividadId = em.merge(actividadId);
            }
            if (tipoMovilidadId != null) {
                tipoMovilidadId.getActividadAcademicaList().add(actividadAcademica);
                tipoMovilidadId = em.merge(tipoMovilidadId);
            }
            for (Horario horarioListHorario : actividadAcademica.getHorarioList()) {
                ActividadAcademica oldActividadAcademicaIdOfHorarioListHorario = horarioListHorario.getActividadAcademicaId();
                horarioListHorario.setActividadAcademicaId(actividadAcademica);
                horarioListHorario = em.merge(horarioListHorario);
                if (oldActividadAcademicaIdOfHorarioListHorario != null) {
                    oldActividadAcademicaIdOfHorarioListHorario.getHorarioList().remove(horarioListHorario);
                    oldActividadAcademicaIdOfHorarioListHorario = em.merge(oldActividadAcademicaIdOfHorarioListHorario);
                }
            }
            for (ConvenioActividad convenioActividadListConvenioActividad : actividadAcademica.getConvenioActividadList()) {
                ActividadAcademica oldActividadAcademicaIdOfConvenioActividadListConvenioActividad = convenioActividadListConvenioActividad.getActividadAcademicaId();
                convenioActividadListConvenioActividad.setActividadAcademicaId(actividadAcademica);
                convenioActividadListConvenioActividad = em.merge(convenioActividadListConvenioActividad);
                if (oldActividadAcademicaIdOfConvenioActividadListConvenioActividad != null) {
                    oldActividadAcademicaIdOfConvenioActividadListConvenioActividad.getConvenioActividadList().remove(convenioActividadListConvenioActividad);
                    oldActividadAcademicaIdOfConvenioActividadListConvenioActividad = em.merge(oldActividadAcademicaIdOfConvenioActividadListConvenioActividad);
                }
            }
            for (ConferencistaActividad conferencistaActividadListConferencistaActividad : actividadAcademica.getConferencistaActividadList()) {
                ActividadAcademica oldActividadAcademicaIdOfConferencistaActividadListConferencistaActividad = conferencistaActividadListConferencistaActividad.getActividadAcademicaId();
                conferencistaActividadListConferencistaActividad.setActividadAcademicaId(actividadAcademica);
                conferencistaActividadListConferencistaActividad = em.merge(conferencistaActividadListConferencistaActividad);
                if (oldActividadAcademicaIdOfConferencistaActividadListConferencistaActividad != null) {
                    oldActividadAcademicaIdOfConferencistaActividadListConferencistaActividad.getConferencistaActividadList().remove(conferencistaActividadListConferencistaActividad);
                    oldActividadAcademicaIdOfConferencistaActividadListConferencistaActividad = em.merge(oldActividadAcademicaIdOfConferencistaActividadListConferencistaActividad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActividadAcademica actividadAcademica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActividadAcademica persistentActividadAcademica = em.find(ActividadAcademica.class, actividadAcademica.getId());
            Actividad actividadIdOld = persistentActividadAcademica.getActividadId();
            Actividad actividadIdNew = actividadAcademica.getActividadId();
            TipoMovilidad tipoMovilidadIdOld = persistentActividadAcademica.getTipoMovilidadId();
            TipoMovilidad tipoMovilidadIdNew = actividadAcademica.getTipoMovilidadId();
            List<Horario> horarioListOld = persistentActividadAcademica.getHorarioList();
            List<Horario> horarioListNew = actividadAcademica.getHorarioList();
            List<ConvenioActividad> convenioActividadListOld = persistentActividadAcademica.getConvenioActividadList();
            List<ConvenioActividad> convenioActividadListNew = actividadAcademica.getConvenioActividadList();
            List<ConferencistaActividad> conferencistaActividadListOld = persistentActividadAcademica.getConferencistaActividadList();
            List<ConferencistaActividad> conferencistaActividadListNew = actividadAcademica.getConferencistaActividadList();
            List<String> illegalOrphanMessages = null;
            for (Horario horarioListOldHorario : horarioListOld) {
                if (!horarioListNew.contains(horarioListOldHorario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Horario " + horarioListOldHorario + " since its actividadAcademicaId field is not nullable.");
                }
            }
            for (ConvenioActividad convenioActividadListOldConvenioActividad : convenioActividadListOld) {
                if (!convenioActividadListNew.contains(convenioActividadListOldConvenioActividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ConvenioActividad " + convenioActividadListOldConvenioActividad + " since its actividadAcademicaId field is not nullable.");
                }
            }
            for (ConferencistaActividad conferencistaActividadListOldConferencistaActividad : conferencistaActividadListOld) {
                if (!conferencistaActividadListNew.contains(conferencistaActividadListOldConferencistaActividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ConferencistaActividad " + conferencistaActividadListOldConferencistaActividad + " since its actividadAcademicaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (actividadIdNew != null) {
                actividadIdNew = em.getReference(actividadIdNew.getClass(), actividadIdNew.getId());
                actividadAcademica.setActividadId(actividadIdNew);
            }
            if (tipoMovilidadIdNew != null) {
                tipoMovilidadIdNew = em.getReference(tipoMovilidadIdNew.getClass(), tipoMovilidadIdNew.getId());
                actividadAcademica.setTipoMovilidadId(tipoMovilidadIdNew);
            }
            List<Horario> attachedHorarioListNew = new ArrayList<Horario>();
            for (Horario horarioListNewHorarioToAttach : horarioListNew) {
                horarioListNewHorarioToAttach = em.getReference(horarioListNewHorarioToAttach.getClass(), horarioListNewHorarioToAttach.getId());
                attachedHorarioListNew.add(horarioListNewHorarioToAttach);
            }
            horarioListNew = attachedHorarioListNew;
            actividadAcademica.setHorarioList(horarioListNew);
            List<ConvenioActividad> attachedConvenioActividadListNew = new ArrayList<ConvenioActividad>();
            for (ConvenioActividad convenioActividadListNewConvenioActividadToAttach : convenioActividadListNew) {
                convenioActividadListNewConvenioActividadToAttach = em.getReference(convenioActividadListNewConvenioActividadToAttach.getClass(), convenioActividadListNewConvenioActividadToAttach.getId());
                attachedConvenioActividadListNew.add(convenioActividadListNewConvenioActividadToAttach);
            }
            convenioActividadListNew = attachedConvenioActividadListNew;
            actividadAcademica.setConvenioActividadList(convenioActividadListNew);
            List<ConferencistaActividad> attachedConferencistaActividadListNew = new ArrayList<ConferencistaActividad>();
            for (ConferencistaActividad conferencistaActividadListNewConferencistaActividadToAttach : conferencistaActividadListNew) {
                conferencistaActividadListNewConferencistaActividadToAttach = em.getReference(conferencistaActividadListNewConferencistaActividadToAttach.getClass(), conferencistaActividadListNewConferencistaActividadToAttach.getId());
                attachedConferencistaActividadListNew.add(conferencistaActividadListNewConferencistaActividadToAttach);
            }
            conferencistaActividadListNew = attachedConferencistaActividadListNew;
            actividadAcademica.setConferencistaActividadList(conferencistaActividadListNew);
            actividadAcademica = em.merge(actividadAcademica);
            if (actividadIdOld != null && !actividadIdOld.equals(actividadIdNew)) {
                actividadIdOld.getActividadAcademicaList().remove(actividadAcademica);
                actividadIdOld = em.merge(actividadIdOld);
            }
            if (actividadIdNew != null && !actividadIdNew.equals(actividadIdOld)) {
                actividadIdNew.getActividadAcademicaList().add(actividadAcademica);
                actividadIdNew = em.merge(actividadIdNew);
            }
            if (tipoMovilidadIdOld != null && !tipoMovilidadIdOld.equals(tipoMovilidadIdNew)) {
                tipoMovilidadIdOld.getActividadAcademicaList().remove(actividadAcademica);
                tipoMovilidadIdOld = em.merge(tipoMovilidadIdOld);
            }
            if (tipoMovilidadIdNew != null && !tipoMovilidadIdNew.equals(tipoMovilidadIdOld)) {
                tipoMovilidadIdNew.getActividadAcademicaList().add(actividadAcademica);
                tipoMovilidadIdNew = em.merge(tipoMovilidadIdNew);
            }
            for (Horario horarioListNewHorario : horarioListNew) {
                if (!horarioListOld.contains(horarioListNewHorario)) {
                    ActividadAcademica oldActividadAcademicaIdOfHorarioListNewHorario = horarioListNewHorario.getActividadAcademicaId();
                    horarioListNewHorario.setActividadAcademicaId(actividadAcademica);
                    horarioListNewHorario = em.merge(horarioListNewHorario);
                    if (oldActividadAcademicaIdOfHorarioListNewHorario != null && !oldActividadAcademicaIdOfHorarioListNewHorario.equals(actividadAcademica)) {
                        oldActividadAcademicaIdOfHorarioListNewHorario.getHorarioList().remove(horarioListNewHorario);
                        oldActividadAcademicaIdOfHorarioListNewHorario = em.merge(oldActividadAcademicaIdOfHorarioListNewHorario);
                    }
                }
            }
            for (ConvenioActividad convenioActividadListNewConvenioActividad : convenioActividadListNew) {
                if (!convenioActividadListOld.contains(convenioActividadListNewConvenioActividad)) {
                    ActividadAcademica oldActividadAcademicaIdOfConvenioActividadListNewConvenioActividad = convenioActividadListNewConvenioActividad.getActividadAcademicaId();
                    convenioActividadListNewConvenioActividad.setActividadAcademicaId(actividadAcademica);
                    convenioActividadListNewConvenioActividad = em.merge(convenioActividadListNewConvenioActividad);
                    if (oldActividadAcademicaIdOfConvenioActividadListNewConvenioActividad != null && !oldActividadAcademicaIdOfConvenioActividadListNewConvenioActividad.equals(actividadAcademica)) {
                        oldActividadAcademicaIdOfConvenioActividadListNewConvenioActividad.getConvenioActividadList().remove(convenioActividadListNewConvenioActividad);
                        oldActividadAcademicaIdOfConvenioActividadListNewConvenioActividad = em.merge(oldActividadAcademicaIdOfConvenioActividadListNewConvenioActividad);
                    }
                }
            }
            for (ConferencistaActividad conferencistaActividadListNewConferencistaActividad : conferencistaActividadListNew) {
                if (!conferencistaActividadListOld.contains(conferencistaActividadListNewConferencistaActividad)) {
                    ActividadAcademica oldActividadAcademicaIdOfConferencistaActividadListNewConferencistaActividad = conferencistaActividadListNewConferencistaActividad.getActividadAcademicaId();
                    conferencistaActividadListNewConferencistaActividad.setActividadAcademicaId(actividadAcademica);
                    conferencistaActividadListNewConferencistaActividad = em.merge(conferencistaActividadListNewConferencistaActividad);
                    if (oldActividadAcademicaIdOfConferencistaActividadListNewConferencistaActividad != null && !oldActividadAcademicaIdOfConferencistaActividadListNewConferencistaActividad.equals(actividadAcademica)) {
                        oldActividadAcademicaIdOfConferencistaActividadListNewConferencistaActividad.getConferencistaActividadList().remove(conferencistaActividadListNewConferencistaActividad);
                        oldActividadAcademicaIdOfConferencistaActividadListNewConferencistaActividad = em.merge(oldActividadAcademicaIdOfConferencistaActividadListNewConferencistaActividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actividadAcademica.getId();
                if (findActividadAcademica(id) == null) {
                    throw new NonexistentEntityException("The actividadAcademica with id " + id + " no longer exists.");
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
            ActividadAcademica actividadAcademica;
            try {
                actividadAcademica = em.getReference(ActividadAcademica.class, id);
                actividadAcademica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadAcademica with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Horario> horarioListOrphanCheck = actividadAcademica.getHorarioList();
            for (Horario horarioListOrphanCheckHorario : horarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ActividadAcademica (" + actividadAcademica + ") cannot be destroyed since the Horario " + horarioListOrphanCheckHorario + " in its horarioList field has a non-nullable actividadAcademicaId field.");
            }
            List<ConvenioActividad> convenioActividadListOrphanCheck = actividadAcademica.getConvenioActividadList();
            for (ConvenioActividad convenioActividadListOrphanCheckConvenioActividad : convenioActividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ActividadAcademica (" + actividadAcademica + ") cannot be destroyed since the ConvenioActividad " + convenioActividadListOrphanCheckConvenioActividad + " in its convenioActividadList field has a non-nullable actividadAcademicaId field.");
            }
            List<ConferencistaActividad> conferencistaActividadListOrphanCheck = actividadAcademica.getConferencistaActividadList();
            for (ConferencistaActividad conferencistaActividadListOrphanCheckConferencistaActividad : conferencistaActividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ActividadAcademica (" + actividadAcademica + ") cannot be destroyed since the ConferencistaActividad " + conferencistaActividadListOrphanCheckConferencistaActividad + " in its conferencistaActividadList field has a non-nullable actividadAcademicaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Actividad actividadId = actividadAcademica.getActividadId();
            if (actividadId != null) {
                actividadId.getActividadAcademicaList().remove(actividadAcademica);
                actividadId = em.merge(actividadId);
            }
            TipoMovilidad tipoMovilidadId = actividadAcademica.getTipoMovilidadId();
            if (tipoMovilidadId != null) {
                tipoMovilidadId.getActividadAcademicaList().remove(actividadAcademica);
                tipoMovilidadId = em.merge(tipoMovilidadId);
            }
            em.remove(actividadAcademica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ActividadAcademica> findActividadAcademicaEntities() {
        return findActividadAcademicaEntities(true, -1, -1);
    }

    public List<ActividadAcademica> findActividadAcademicaEntities(int maxResults, int firstResult) {
        return findActividadAcademicaEntities(false, maxResults, firstResult);
    }

    private List<ActividadAcademica> findActividadAcademicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActividadAcademica.class));
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

    public ActividadAcademica findActividadAcademica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActividadAcademica.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadAcademicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActividadAcademica> rt = cq.from(ActividadAcademica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
