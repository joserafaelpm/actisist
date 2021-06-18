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
import ufps.edu.co.dto.Usuario;
import ufps.edu.co.dto.TipoMovilidad;
import ufps.edu.co.dto.TipoActividad;
import ufps.edu.co.dto.Horario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.ConvenioActividad;
import ufps.edu.co.dto.ActividadAnexos;
import ufps.edu.co.dto.ConferencistaActividad;
import ufps.edu.co.dto.InvolucradosActividad;

/**
 *
 * @author dunke
 */
public class ActividadJpaController implements Serializable {

    public ActividadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actividad actividad) {
        if (actividad.getHorarioList() == null) {
            actividad.setHorarioList(new ArrayList<Horario>());
        }
        if (actividad.getConvenioActividadList() == null) {
            actividad.setConvenioActividadList(new ArrayList<ConvenioActividad>());
        }
        if (actividad.getActividadAnexosList() == null) {
            actividad.setActividadAnexosList(new ArrayList<ActividadAnexos>());
        }
        if (actividad.getConferencistaActividadList() == null) {
            actividad.setConferencistaActividadList(new ArrayList<ConferencistaActividad>());
        }
        if (actividad.getInvolucradosActividadList() == null) {
            actividad.setInvolucradosActividadList(new ArrayList<InvolucradosActividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioDni = actividad.getUsuarioDni();
            if (usuarioDni != null) {
                usuarioDni = em.getReference(usuarioDni.getClass(), usuarioDni.getDni());
                actividad.setUsuarioDni(usuarioDni);
            }
            TipoMovilidad tipoMovilidadId = actividad.getTipoMovilidadId();
            if (tipoMovilidadId != null) {
                tipoMovilidadId = em.getReference(tipoMovilidadId.getClass(), tipoMovilidadId.getId());
                actividad.setTipoMovilidadId(tipoMovilidadId);
            }
            TipoActividad tipoActividadId = actividad.getTipoActividadId();
            if (tipoActividadId != null) {
                tipoActividadId = em.getReference(tipoActividadId.getClass(), tipoActividadId.getId());
                actividad.setTipoActividadId(tipoActividadId);
            }
            List<Horario> attachedHorarioList = new ArrayList<Horario>();
            for (Horario horarioListHorarioToAttach : actividad.getHorarioList()) {
                horarioListHorarioToAttach = em.getReference(horarioListHorarioToAttach.getClass(), horarioListHorarioToAttach.getId());
                attachedHorarioList.add(horarioListHorarioToAttach);
            }
            actividad.setHorarioList(attachedHorarioList);
            List<ConvenioActividad> attachedConvenioActividadList = new ArrayList<ConvenioActividad>();
            for (ConvenioActividad convenioActividadListConvenioActividadToAttach : actividad.getConvenioActividadList()) {
                convenioActividadListConvenioActividadToAttach = em.getReference(convenioActividadListConvenioActividadToAttach.getClass(), convenioActividadListConvenioActividadToAttach.getId());
                attachedConvenioActividadList.add(convenioActividadListConvenioActividadToAttach);
            }
            actividad.setConvenioActividadList(attachedConvenioActividadList);
            List<ActividadAnexos> attachedActividadAnexosList = new ArrayList<ActividadAnexos>();
            for (ActividadAnexos actividadAnexosListActividadAnexosToAttach : actividad.getActividadAnexosList()) {
                actividadAnexosListActividadAnexosToAttach = em.getReference(actividadAnexosListActividadAnexosToAttach.getClass(), actividadAnexosListActividadAnexosToAttach.getId());
                attachedActividadAnexosList.add(actividadAnexosListActividadAnexosToAttach);
            }
            actividad.setActividadAnexosList(attachedActividadAnexosList);
            List<ConferencistaActividad> attachedConferencistaActividadList = new ArrayList<ConferencistaActividad>();
            for (ConferencistaActividad conferencistaActividadListConferencistaActividadToAttach : actividad.getConferencistaActividadList()) {
                conferencistaActividadListConferencistaActividadToAttach = em.getReference(conferencistaActividadListConferencistaActividadToAttach.getClass(), conferencistaActividadListConferencistaActividadToAttach.getId());
                attachedConferencistaActividadList.add(conferencistaActividadListConferencistaActividadToAttach);
            }
            actividad.setConferencistaActividadList(attachedConferencistaActividadList);
            List<InvolucradosActividad> attachedInvolucradosActividadList = new ArrayList<InvolucradosActividad>();
            for (InvolucradosActividad involucradosActividadListInvolucradosActividadToAttach : actividad.getInvolucradosActividadList()) {
                involucradosActividadListInvolucradosActividadToAttach = em.getReference(involucradosActividadListInvolucradosActividadToAttach.getClass(), involucradosActividadListInvolucradosActividadToAttach.getId());
                attachedInvolucradosActividadList.add(involucradosActividadListInvolucradosActividadToAttach);
            }
            actividad.setInvolucradosActividadList(attachedInvolucradosActividadList);
            em.persist(actividad);
            if (usuarioDni != null) {
                usuarioDni.getActividadList().add(actividad);
                usuarioDni = em.merge(usuarioDni);
            }
            if (tipoMovilidadId != null) {
                tipoMovilidadId.getActividadList().add(actividad);
                tipoMovilidadId = em.merge(tipoMovilidadId);
            }
            if (tipoActividadId != null) {
                tipoActividadId.getActividadList().add(actividad);
                tipoActividadId = em.merge(tipoActividadId);
            }
            for (Horario horarioListHorario : actividad.getHorarioList()) {
                Actividad oldActividadIdOfHorarioListHorario = horarioListHorario.getActividadId();
                horarioListHorario.setActividadId(actividad);
                horarioListHorario = em.merge(horarioListHorario);
                if (oldActividadIdOfHorarioListHorario != null) {
                    oldActividadIdOfHorarioListHorario.getHorarioList().remove(horarioListHorario);
                    oldActividadIdOfHorarioListHorario = em.merge(oldActividadIdOfHorarioListHorario);
                }
            }
            for (ConvenioActividad convenioActividadListConvenioActividad : actividad.getConvenioActividadList()) {
                Actividad oldActividadIdOfConvenioActividadListConvenioActividad = convenioActividadListConvenioActividad.getActividadId();
                convenioActividadListConvenioActividad.setActividadId(actividad);
                convenioActividadListConvenioActividad = em.merge(convenioActividadListConvenioActividad);
                if (oldActividadIdOfConvenioActividadListConvenioActividad != null) {
                    oldActividadIdOfConvenioActividadListConvenioActividad.getConvenioActividadList().remove(convenioActividadListConvenioActividad);
                    oldActividadIdOfConvenioActividadListConvenioActividad = em.merge(oldActividadIdOfConvenioActividadListConvenioActividad);
                }
            }
            for (ActividadAnexos actividadAnexosListActividadAnexos : actividad.getActividadAnexosList()) {
                Actividad oldActividadIdOfActividadAnexosListActividadAnexos = actividadAnexosListActividadAnexos.getActividadId();
                actividadAnexosListActividadAnexos.setActividadId(actividad);
                actividadAnexosListActividadAnexos = em.merge(actividadAnexosListActividadAnexos);
                if (oldActividadIdOfActividadAnexosListActividadAnexos != null) {
                    oldActividadIdOfActividadAnexosListActividadAnexos.getActividadAnexosList().remove(actividadAnexosListActividadAnexos);
                    oldActividadIdOfActividadAnexosListActividadAnexos = em.merge(oldActividadIdOfActividadAnexosListActividadAnexos);
                }
            }
            for (ConferencistaActividad conferencistaActividadListConferencistaActividad : actividad.getConferencistaActividadList()) {
                Actividad oldActividadIdOfConferencistaActividadListConferencistaActividad = conferencistaActividadListConferencistaActividad.getActividadId();
                conferencistaActividadListConferencistaActividad.setActividadId(actividad);
                conferencistaActividadListConferencistaActividad = em.merge(conferencistaActividadListConferencistaActividad);
                if (oldActividadIdOfConferencistaActividadListConferencistaActividad != null) {
                    oldActividadIdOfConferencistaActividadListConferencistaActividad.getConferencistaActividadList().remove(conferencistaActividadListConferencistaActividad);
                    oldActividadIdOfConferencistaActividadListConferencistaActividad = em.merge(oldActividadIdOfConferencistaActividadListConferencistaActividad);
                }
            }
            for (InvolucradosActividad involucradosActividadListInvolucradosActividad : actividad.getInvolucradosActividadList()) {
                Actividad oldActividadIdOfInvolucradosActividadListInvolucradosActividad = involucradosActividadListInvolucradosActividad.getActividadId();
                involucradosActividadListInvolucradosActividad.setActividadId(actividad);
                involucradosActividadListInvolucradosActividad = em.merge(involucradosActividadListInvolucradosActividad);
                if (oldActividadIdOfInvolucradosActividadListInvolucradosActividad != null) {
                    oldActividadIdOfInvolucradosActividadListInvolucradosActividad.getInvolucradosActividadList().remove(involucradosActividadListInvolucradosActividad);
                    oldActividadIdOfInvolucradosActividadListInvolucradosActividad = em.merge(oldActividadIdOfInvolucradosActividadListInvolucradosActividad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividad actividad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad persistentActividad = em.find(Actividad.class, actividad.getId());
            Usuario usuarioDniOld = persistentActividad.getUsuarioDni();
            Usuario usuarioDniNew = actividad.getUsuarioDni();
            TipoMovilidad tipoMovilidadIdOld = persistentActividad.getTipoMovilidadId();
            TipoMovilidad tipoMovilidadIdNew = actividad.getTipoMovilidadId();
            TipoActividad tipoActividadIdOld = persistentActividad.getTipoActividadId();
            TipoActividad tipoActividadIdNew = actividad.getTipoActividadId();
            List<Horario> horarioListOld = persistentActividad.getHorarioList();
            List<Horario> horarioListNew = actividad.getHorarioList();
            List<ConvenioActividad> convenioActividadListOld = persistentActividad.getConvenioActividadList();
            List<ConvenioActividad> convenioActividadListNew = actividad.getConvenioActividadList();
            List<ActividadAnexos> actividadAnexosListOld = persistentActividad.getActividadAnexosList();
            List<ActividadAnexos> actividadAnexosListNew = actividad.getActividadAnexosList();
            List<ConferencistaActividad> conferencistaActividadListOld = persistentActividad.getConferencistaActividadList();
            List<ConferencistaActividad> conferencistaActividadListNew = actividad.getConferencistaActividadList();
            List<InvolucradosActividad> involucradosActividadListOld = persistentActividad.getInvolucradosActividadList();
            List<InvolucradosActividad> involucradosActividadListNew = actividad.getInvolucradosActividadList();
            List<String> illegalOrphanMessages = null;
            for (Horario horarioListOldHorario : horarioListOld) {
                if (!horarioListNew.contains(horarioListOldHorario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Horario " + horarioListOldHorario + " since its actividadId field is not nullable.");
                }
            }
            for (ConvenioActividad convenioActividadListOldConvenioActividad : convenioActividadListOld) {
                if (!convenioActividadListNew.contains(convenioActividadListOldConvenioActividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ConvenioActividad " + convenioActividadListOldConvenioActividad + " since its actividadId field is not nullable.");
                }
            }
            for (ActividadAnexos actividadAnexosListOldActividadAnexos : actividadAnexosListOld) {
                if (!actividadAnexosListNew.contains(actividadAnexosListOldActividadAnexos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ActividadAnexos " + actividadAnexosListOldActividadAnexos + " since its actividadId field is not nullable.");
                }
            }
            for (ConferencistaActividad conferencistaActividadListOldConferencistaActividad : conferencistaActividadListOld) {
                if (!conferencistaActividadListNew.contains(conferencistaActividadListOldConferencistaActividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ConferencistaActividad " + conferencistaActividadListOldConferencistaActividad + " since its actividadId field is not nullable.");
                }
            }
            for (InvolucradosActividad involucradosActividadListOldInvolucradosActividad : involucradosActividadListOld) {
                if (!involucradosActividadListNew.contains(involucradosActividadListOldInvolucradosActividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InvolucradosActividad " + involucradosActividadListOldInvolucradosActividad + " since its actividadId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioDniNew != null) {
                usuarioDniNew = em.getReference(usuarioDniNew.getClass(), usuarioDniNew.getDni());
                actividad.setUsuarioDni(usuarioDniNew);
            }
            if (tipoMovilidadIdNew != null) {
                tipoMovilidadIdNew = em.getReference(tipoMovilidadIdNew.getClass(), tipoMovilidadIdNew.getId());
                actividad.setTipoMovilidadId(tipoMovilidadIdNew);
            }
            if (tipoActividadIdNew != null) {
                tipoActividadIdNew = em.getReference(tipoActividadIdNew.getClass(), tipoActividadIdNew.getId());
                actividad.setTipoActividadId(tipoActividadIdNew);
            }
            List<Horario> attachedHorarioListNew = new ArrayList<Horario>();
            for (Horario horarioListNewHorarioToAttach : horarioListNew) {
                horarioListNewHorarioToAttach = em.getReference(horarioListNewHorarioToAttach.getClass(), horarioListNewHorarioToAttach.getId());
                attachedHorarioListNew.add(horarioListNewHorarioToAttach);
            }
            horarioListNew = attachedHorarioListNew;
            actividad.setHorarioList(horarioListNew);
            List<ConvenioActividad> attachedConvenioActividadListNew = new ArrayList<ConvenioActividad>();
            for (ConvenioActividad convenioActividadListNewConvenioActividadToAttach : convenioActividadListNew) {
                convenioActividadListNewConvenioActividadToAttach = em.getReference(convenioActividadListNewConvenioActividadToAttach.getClass(), convenioActividadListNewConvenioActividadToAttach.getId());
                attachedConvenioActividadListNew.add(convenioActividadListNewConvenioActividadToAttach);
            }
            convenioActividadListNew = attachedConvenioActividadListNew;
            actividad.setConvenioActividadList(convenioActividadListNew);
            List<ActividadAnexos> attachedActividadAnexosListNew = new ArrayList<ActividadAnexos>();
            for (ActividadAnexos actividadAnexosListNewActividadAnexosToAttach : actividadAnexosListNew) {
                actividadAnexosListNewActividadAnexosToAttach = em.getReference(actividadAnexosListNewActividadAnexosToAttach.getClass(), actividadAnexosListNewActividadAnexosToAttach.getId());
                attachedActividadAnexosListNew.add(actividadAnexosListNewActividadAnexosToAttach);
            }
            actividadAnexosListNew = attachedActividadAnexosListNew;
            actividad.setActividadAnexosList(actividadAnexosListNew);
            List<ConferencistaActividad> attachedConferencistaActividadListNew = new ArrayList<ConferencistaActividad>();
            for (ConferencistaActividad conferencistaActividadListNewConferencistaActividadToAttach : conferencistaActividadListNew) {
                conferencistaActividadListNewConferencistaActividadToAttach = em.getReference(conferencistaActividadListNewConferencistaActividadToAttach.getClass(), conferencistaActividadListNewConferencistaActividadToAttach.getId());
                attachedConferencistaActividadListNew.add(conferencistaActividadListNewConferencistaActividadToAttach);
            }
            conferencistaActividadListNew = attachedConferencistaActividadListNew;
            actividad.setConferencistaActividadList(conferencistaActividadListNew);
            List<InvolucradosActividad> attachedInvolucradosActividadListNew = new ArrayList<InvolucradosActividad>();
            for (InvolucradosActividad involucradosActividadListNewInvolucradosActividadToAttach : involucradosActividadListNew) {
                involucradosActividadListNewInvolucradosActividadToAttach = em.getReference(involucradosActividadListNewInvolucradosActividadToAttach.getClass(), involucradosActividadListNewInvolucradosActividadToAttach.getId());
                attachedInvolucradosActividadListNew.add(involucradosActividadListNewInvolucradosActividadToAttach);
            }
            involucradosActividadListNew = attachedInvolucradosActividadListNew;
            actividad.setInvolucradosActividadList(involucradosActividadListNew);
            actividad = em.merge(actividad);
            if (usuarioDniOld != null && !usuarioDniOld.equals(usuarioDniNew)) {
                usuarioDniOld.getActividadList().remove(actividad);
                usuarioDniOld = em.merge(usuarioDniOld);
            }
            if (usuarioDniNew != null && !usuarioDniNew.equals(usuarioDniOld)) {
                usuarioDniNew.getActividadList().add(actividad);
                usuarioDniNew = em.merge(usuarioDniNew);
            }
            if (tipoMovilidadIdOld != null && !tipoMovilidadIdOld.equals(tipoMovilidadIdNew)) {
                tipoMovilidadIdOld.getActividadList().remove(actividad);
                tipoMovilidadIdOld = em.merge(tipoMovilidadIdOld);
            }
            if (tipoMovilidadIdNew != null && !tipoMovilidadIdNew.equals(tipoMovilidadIdOld)) {
                tipoMovilidadIdNew.getActividadList().add(actividad);
                tipoMovilidadIdNew = em.merge(tipoMovilidadIdNew);
            }
            if (tipoActividadIdOld != null && !tipoActividadIdOld.equals(tipoActividadIdNew)) {
                tipoActividadIdOld.getActividadList().remove(actividad);
                tipoActividadIdOld = em.merge(tipoActividadIdOld);
            }
            if (tipoActividadIdNew != null && !tipoActividadIdNew.equals(tipoActividadIdOld)) {
                tipoActividadIdNew.getActividadList().add(actividad);
                tipoActividadIdNew = em.merge(tipoActividadIdNew);
            }
            for (Horario horarioListNewHorario : horarioListNew) {
                if (!horarioListOld.contains(horarioListNewHorario)) {
                    Actividad oldActividadIdOfHorarioListNewHorario = horarioListNewHorario.getActividadId();
                    horarioListNewHorario.setActividadId(actividad);
                    horarioListNewHorario = em.merge(horarioListNewHorario);
                    if (oldActividadIdOfHorarioListNewHorario != null && !oldActividadIdOfHorarioListNewHorario.equals(actividad)) {
                        oldActividadIdOfHorarioListNewHorario.getHorarioList().remove(horarioListNewHorario);
                        oldActividadIdOfHorarioListNewHorario = em.merge(oldActividadIdOfHorarioListNewHorario);
                    }
                }
            }
            for (ConvenioActividad convenioActividadListNewConvenioActividad : convenioActividadListNew) {
                if (!convenioActividadListOld.contains(convenioActividadListNewConvenioActividad)) {
                    Actividad oldActividadIdOfConvenioActividadListNewConvenioActividad = convenioActividadListNewConvenioActividad.getActividadId();
                    convenioActividadListNewConvenioActividad.setActividadId(actividad);
                    convenioActividadListNewConvenioActividad = em.merge(convenioActividadListNewConvenioActividad);
                    if (oldActividadIdOfConvenioActividadListNewConvenioActividad != null && !oldActividadIdOfConvenioActividadListNewConvenioActividad.equals(actividad)) {
                        oldActividadIdOfConvenioActividadListNewConvenioActividad.getConvenioActividadList().remove(convenioActividadListNewConvenioActividad);
                        oldActividadIdOfConvenioActividadListNewConvenioActividad = em.merge(oldActividadIdOfConvenioActividadListNewConvenioActividad);
                    }
                }
            }
            for (ActividadAnexos actividadAnexosListNewActividadAnexos : actividadAnexosListNew) {
                if (!actividadAnexosListOld.contains(actividadAnexosListNewActividadAnexos)) {
                    Actividad oldActividadIdOfActividadAnexosListNewActividadAnexos = actividadAnexosListNewActividadAnexos.getActividadId();
                    actividadAnexosListNewActividadAnexos.setActividadId(actividad);
                    actividadAnexosListNewActividadAnexos = em.merge(actividadAnexosListNewActividadAnexos);
                    if (oldActividadIdOfActividadAnexosListNewActividadAnexos != null && !oldActividadIdOfActividadAnexosListNewActividadAnexos.equals(actividad)) {
                        oldActividadIdOfActividadAnexosListNewActividadAnexos.getActividadAnexosList().remove(actividadAnexosListNewActividadAnexos);
                        oldActividadIdOfActividadAnexosListNewActividadAnexos = em.merge(oldActividadIdOfActividadAnexosListNewActividadAnexos);
                    }
                }
            }
            for (ConferencistaActividad conferencistaActividadListNewConferencistaActividad : conferencistaActividadListNew) {
                if (!conferencistaActividadListOld.contains(conferencistaActividadListNewConferencistaActividad)) {
                    Actividad oldActividadIdOfConferencistaActividadListNewConferencistaActividad = conferencistaActividadListNewConferencistaActividad.getActividadId();
                    conferencistaActividadListNewConferencistaActividad.setActividadId(actividad);
                    conferencistaActividadListNewConferencistaActividad = em.merge(conferencistaActividadListNewConferencistaActividad);
                    if (oldActividadIdOfConferencistaActividadListNewConferencistaActividad != null && !oldActividadIdOfConferencistaActividadListNewConferencistaActividad.equals(actividad)) {
                        oldActividadIdOfConferencistaActividadListNewConferencistaActividad.getConferencistaActividadList().remove(conferencistaActividadListNewConferencistaActividad);
                        oldActividadIdOfConferencistaActividadListNewConferencistaActividad = em.merge(oldActividadIdOfConferencistaActividadListNewConferencistaActividad);
                    }
                }
            }
            for (InvolucradosActividad involucradosActividadListNewInvolucradosActividad : involucradosActividadListNew) {
                if (!involucradosActividadListOld.contains(involucradosActividadListNewInvolucradosActividad)) {
                    Actividad oldActividadIdOfInvolucradosActividadListNewInvolucradosActividad = involucradosActividadListNewInvolucradosActividad.getActividadId();
                    involucradosActividadListNewInvolucradosActividad.setActividadId(actividad);
                    involucradosActividadListNewInvolucradosActividad = em.merge(involucradosActividadListNewInvolucradosActividad);
                    if (oldActividadIdOfInvolucradosActividadListNewInvolucradosActividad != null && !oldActividadIdOfInvolucradosActividadListNewInvolucradosActividad.equals(actividad)) {
                        oldActividadIdOfInvolucradosActividadListNewInvolucradosActividad.getInvolucradosActividadList().remove(involucradosActividadListNewInvolucradosActividad);
                        oldActividadIdOfInvolucradosActividadListNewInvolucradosActividad = em.merge(oldActividadIdOfInvolucradosActividadListNewInvolucradosActividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actividad.getId();
                if (findActividad(id) == null) {
                    throw new NonexistentEntityException("The actividad with id " + id + " no longer exists.");
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
            Actividad actividad;
            try {
                actividad = em.getReference(Actividad.class, id);
                actividad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Horario> horarioListOrphanCheck = actividad.getHorarioList();
            for (Horario horarioListOrphanCheckHorario : horarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividad (" + actividad + ") cannot be destroyed since the Horario " + horarioListOrphanCheckHorario + " in its horarioList field has a non-nullable actividadId field.");
            }
            List<ConvenioActividad> convenioActividadListOrphanCheck = actividad.getConvenioActividadList();
            for (ConvenioActividad convenioActividadListOrphanCheckConvenioActividad : convenioActividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividad (" + actividad + ") cannot be destroyed since the ConvenioActividad " + convenioActividadListOrphanCheckConvenioActividad + " in its convenioActividadList field has a non-nullable actividadId field.");
            }
            List<ActividadAnexos> actividadAnexosListOrphanCheck = actividad.getActividadAnexosList();
            for (ActividadAnexos actividadAnexosListOrphanCheckActividadAnexos : actividadAnexosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividad (" + actividad + ") cannot be destroyed since the ActividadAnexos " + actividadAnexosListOrphanCheckActividadAnexos + " in its actividadAnexosList field has a non-nullable actividadId field.");
            }
            List<ConferencistaActividad> conferencistaActividadListOrphanCheck = actividad.getConferencistaActividadList();
            for (ConferencistaActividad conferencistaActividadListOrphanCheckConferencistaActividad : conferencistaActividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividad (" + actividad + ") cannot be destroyed since the ConferencistaActividad " + conferencistaActividadListOrphanCheckConferencistaActividad + " in its conferencistaActividadList field has a non-nullable actividadId field.");
            }
            List<InvolucradosActividad> involucradosActividadListOrphanCheck = actividad.getInvolucradosActividadList();
            for (InvolucradosActividad involucradosActividadListOrphanCheckInvolucradosActividad : involucradosActividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividad (" + actividad + ") cannot be destroyed since the InvolucradosActividad " + involucradosActividadListOrphanCheckInvolucradosActividad + " in its involucradosActividadList field has a non-nullable actividadId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuarioDni = actividad.getUsuarioDni();
            if (usuarioDni != null) {
                usuarioDni.getActividadList().remove(actividad);
                usuarioDni = em.merge(usuarioDni);
            }
            TipoMovilidad tipoMovilidadId = actividad.getTipoMovilidadId();
            if (tipoMovilidadId != null) {
                tipoMovilidadId.getActividadList().remove(actividad);
                tipoMovilidadId = em.merge(tipoMovilidadId);
            }
            TipoActividad tipoActividadId = actividad.getTipoActividadId();
            if (tipoActividadId != null) {
                tipoActividadId.getActividadList().remove(actividad);
                tipoActividadId = em.merge(tipoActividadId);
            }
            em.remove(actividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actividad> findActividadEntities() {
        return findActividadEntities(true, -1, -1);
    }

    public List<Actividad> findActividadEntities(int maxResults, int firstResult) {
        return findActividadEntities(false, maxResults, firstResult);
    }

    private List<Actividad> findActividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actividad.class));
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

    public Actividad findActividad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actividad> rt = cq.from(Actividad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
