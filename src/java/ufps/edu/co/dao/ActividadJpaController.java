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
import ufps.edu.co.dto.TipoActividad;
import ufps.edu.co.dto.Proyecto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.ActividadAnexos;
import ufps.edu.co.dto.ActividadInstitucion;
import ufps.edu.co.dto.InvolucradosActividad;
import ufps.edu.co.dto.ActividadAcademica;

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
        if (actividad.getProyectoList() == null) {
            actividad.setProyectoList(new ArrayList<Proyecto>());
        }
        if (actividad.getActividadAnexosList() == null) {
            actividad.setActividadAnexosList(new ArrayList<ActividadAnexos>());
        }
        if (actividad.getActividadInstitucionList() == null) {
            actividad.setActividadInstitucionList(new ArrayList<ActividadInstitucion>());
        }
        if (actividad.getInvolucradosActividadList() == null) {
            actividad.setInvolucradosActividadList(new ArrayList<InvolucradosActividad>());
        }
        if (actividad.getActividadAcademicaList() == null) {
            actividad.setActividadAcademicaList(new ArrayList<ActividadAcademica>());
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
            TipoActividad tipoActividadId = actividad.getTipoActividadId();
            if (tipoActividadId != null) {
                tipoActividadId = em.getReference(tipoActividadId.getClass(), tipoActividadId.getId());
                actividad.setTipoActividadId(tipoActividadId);
            }
            List<Proyecto> attachedProyectoList = new ArrayList<Proyecto>();
            for (Proyecto proyectoListProyectoToAttach : actividad.getProyectoList()) {
                proyectoListProyectoToAttach = em.getReference(proyectoListProyectoToAttach.getClass(), proyectoListProyectoToAttach.getId());
                attachedProyectoList.add(proyectoListProyectoToAttach);
            }
            actividad.setProyectoList(attachedProyectoList);
            List<ActividadAnexos> attachedActividadAnexosList = new ArrayList<ActividadAnexos>();
            for (ActividadAnexos actividadAnexosListActividadAnexosToAttach : actividad.getActividadAnexosList()) {
                actividadAnexosListActividadAnexosToAttach = em.getReference(actividadAnexosListActividadAnexosToAttach.getClass(), actividadAnexosListActividadAnexosToAttach.getId());
                attachedActividadAnexosList.add(actividadAnexosListActividadAnexosToAttach);
            }
            actividad.setActividadAnexosList(attachedActividadAnexosList);
            List<ActividadInstitucion> attachedActividadInstitucionList = new ArrayList<ActividadInstitucion>();
            for (ActividadInstitucion actividadInstitucionListActividadInstitucionToAttach : actividad.getActividadInstitucionList()) {
                actividadInstitucionListActividadInstitucionToAttach = em.getReference(actividadInstitucionListActividadInstitucionToAttach.getClass(), actividadInstitucionListActividadInstitucionToAttach.getId());
                attachedActividadInstitucionList.add(actividadInstitucionListActividadInstitucionToAttach);
            }
            actividad.setActividadInstitucionList(attachedActividadInstitucionList);
            List<InvolucradosActividad> attachedInvolucradosActividadList = new ArrayList<InvolucradosActividad>();
            for (InvolucradosActividad involucradosActividadListInvolucradosActividadToAttach : actividad.getInvolucradosActividadList()) {
                involucradosActividadListInvolucradosActividadToAttach = em.getReference(involucradosActividadListInvolucradosActividadToAttach.getClass(), involucradosActividadListInvolucradosActividadToAttach.getId());
                attachedInvolucradosActividadList.add(involucradosActividadListInvolucradosActividadToAttach);
            }
            actividad.setInvolucradosActividadList(attachedInvolucradosActividadList);
            List<ActividadAcademica> attachedActividadAcademicaList = new ArrayList<ActividadAcademica>();
            for (ActividadAcademica actividadAcademicaListActividadAcademicaToAttach : actividad.getActividadAcademicaList()) {
                actividadAcademicaListActividadAcademicaToAttach = em.getReference(actividadAcademicaListActividadAcademicaToAttach.getClass(), actividadAcademicaListActividadAcademicaToAttach.getId());
                attachedActividadAcademicaList.add(actividadAcademicaListActividadAcademicaToAttach);
            }
            actividad.setActividadAcademicaList(attachedActividadAcademicaList);
            em.persist(actividad);
            if (usuarioDni != null) {
                usuarioDni.getActividadList().add(actividad);
                usuarioDni = em.merge(usuarioDni);
            }
            if (tipoActividadId != null) {
                tipoActividadId.getActividadList().add(actividad);
                tipoActividadId = em.merge(tipoActividadId);
            }
            for (Proyecto proyectoListProyecto : actividad.getProyectoList()) {
                Actividad oldActividadIdOfProyectoListProyecto = proyectoListProyecto.getActividadId();
                proyectoListProyecto.setActividadId(actividad);
                proyectoListProyecto = em.merge(proyectoListProyecto);
                if (oldActividadIdOfProyectoListProyecto != null) {
                    oldActividadIdOfProyectoListProyecto.getProyectoList().remove(proyectoListProyecto);
                    oldActividadIdOfProyectoListProyecto = em.merge(oldActividadIdOfProyectoListProyecto);
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
            for (ActividadInstitucion actividadInstitucionListActividadInstitucion : actividad.getActividadInstitucionList()) {
                Actividad oldActividadIdOfActividadInstitucionListActividadInstitucion = actividadInstitucionListActividadInstitucion.getActividadId();
                actividadInstitucionListActividadInstitucion.setActividadId(actividad);
                actividadInstitucionListActividadInstitucion = em.merge(actividadInstitucionListActividadInstitucion);
                if (oldActividadIdOfActividadInstitucionListActividadInstitucion != null) {
                    oldActividadIdOfActividadInstitucionListActividadInstitucion.getActividadInstitucionList().remove(actividadInstitucionListActividadInstitucion);
                    oldActividadIdOfActividadInstitucionListActividadInstitucion = em.merge(oldActividadIdOfActividadInstitucionListActividadInstitucion);
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
            for (ActividadAcademica actividadAcademicaListActividadAcademica : actividad.getActividadAcademicaList()) {
                Actividad oldActividadIdOfActividadAcademicaListActividadAcademica = actividadAcademicaListActividadAcademica.getActividadId();
                actividadAcademicaListActividadAcademica.setActividadId(actividad);
                actividadAcademicaListActividadAcademica = em.merge(actividadAcademicaListActividadAcademica);
                if (oldActividadIdOfActividadAcademicaListActividadAcademica != null) {
                    oldActividadIdOfActividadAcademicaListActividadAcademica.getActividadAcademicaList().remove(actividadAcademicaListActividadAcademica);
                    oldActividadIdOfActividadAcademicaListActividadAcademica = em.merge(oldActividadIdOfActividadAcademicaListActividadAcademica);
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
            TipoActividad tipoActividadIdOld = persistentActividad.getTipoActividadId();
            TipoActividad tipoActividadIdNew = actividad.getTipoActividadId();
            List<Proyecto> proyectoListOld = persistentActividad.getProyectoList();
            List<Proyecto> proyectoListNew = actividad.getProyectoList();
            List<ActividadAnexos> actividadAnexosListOld = persistentActividad.getActividadAnexosList();
            List<ActividadAnexos> actividadAnexosListNew = actividad.getActividadAnexosList();
            List<ActividadInstitucion> actividadInstitucionListOld = persistentActividad.getActividadInstitucionList();
            List<ActividadInstitucion> actividadInstitucionListNew = actividad.getActividadInstitucionList();
            List<InvolucradosActividad> involucradosActividadListOld = persistentActividad.getInvolucradosActividadList();
            List<InvolucradosActividad> involucradosActividadListNew = actividad.getInvolucradosActividadList();
            List<ActividadAcademica> actividadAcademicaListOld = persistentActividad.getActividadAcademicaList();
            List<ActividadAcademica> actividadAcademicaListNew = actividad.getActividadAcademicaList();
            List<String> illegalOrphanMessages = null;
            for (Proyecto proyectoListOldProyecto : proyectoListOld) {
                if (!proyectoListNew.contains(proyectoListOldProyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proyecto " + proyectoListOldProyecto + " since its actividadId field is not nullable.");
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
            for (ActividadInstitucion actividadInstitucionListOldActividadInstitucion : actividadInstitucionListOld) {
                if (!actividadInstitucionListNew.contains(actividadInstitucionListOldActividadInstitucion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ActividadInstitucion " + actividadInstitucionListOldActividadInstitucion + " since its actividadId field is not nullable.");
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
            for (ActividadAcademica actividadAcademicaListOldActividadAcademica : actividadAcademicaListOld) {
                if (!actividadAcademicaListNew.contains(actividadAcademicaListOldActividadAcademica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ActividadAcademica " + actividadAcademicaListOldActividadAcademica + " since its actividadId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioDniNew != null) {
                usuarioDniNew = em.getReference(usuarioDniNew.getClass(), usuarioDniNew.getDni());
                actividad.setUsuarioDni(usuarioDniNew);
            }
            if (tipoActividadIdNew != null) {
                tipoActividadIdNew = em.getReference(tipoActividadIdNew.getClass(), tipoActividadIdNew.getId());
                actividad.setTipoActividadId(tipoActividadIdNew);
            }
            List<Proyecto> attachedProyectoListNew = new ArrayList<Proyecto>();
            for (Proyecto proyectoListNewProyectoToAttach : proyectoListNew) {
                proyectoListNewProyectoToAttach = em.getReference(proyectoListNewProyectoToAttach.getClass(), proyectoListNewProyectoToAttach.getId());
                attachedProyectoListNew.add(proyectoListNewProyectoToAttach);
            }
            proyectoListNew = attachedProyectoListNew;
            actividad.setProyectoList(proyectoListNew);
            List<ActividadAnexos> attachedActividadAnexosListNew = new ArrayList<ActividadAnexos>();
            for (ActividadAnexos actividadAnexosListNewActividadAnexosToAttach : actividadAnexosListNew) {
                actividadAnexosListNewActividadAnexosToAttach = em.getReference(actividadAnexosListNewActividadAnexosToAttach.getClass(), actividadAnexosListNewActividadAnexosToAttach.getId());
                attachedActividadAnexosListNew.add(actividadAnexosListNewActividadAnexosToAttach);
            }
            actividadAnexosListNew = attachedActividadAnexosListNew;
            actividad.setActividadAnexosList(actividadAnexosListNew);
            List<ActividadInstitucion> attachedActividadInstitucionListNew = new ArrayList<ActividadInstitucion>();
            for (ActividadInstitucion actividadInstitucionListNewActividadInstitucionToAttach : actividadInstitucionListNew) {
                actividadInstitucionListNewActividadInstitucionToAttach = em.getReference(actividadInstitucionListNewActividadInstitucionToAttach.getClass(), actividadInstitucionListNewActividadInstitucionToAttach.getId());
                attachedActividadInstitucionListNew.add(actividadInstitucionListNewActividadInstitucionToAttach);
            }
            actividadInstitucionListNew = attachedActividadInstitucionListNew;
            actividad.setActividadInstitucionList(actividadInstitucionListNew);
            List<InvolucradosActividad> attachedInvolucradosActividadListNew = new ArrayList<InvolucradosActividad>();
            for (InvolucradosActividad involucradosActividadListNewInvolucradosActividadToAttach : involucradosActividadListNew) {
                involucradosActividadListNewInvolucradosActividadToAttach = em.getReference(involucradosActividadListNewInvolucradosActividadToAttach.getClass(), involucradosActividadListNewInvolucradosActividadToAttach.getId());
                attachedInvolucradosActividadListNew.add(involucradosActividadListNewInvolucradosActividadToAttach);
            }
            involucradosActividadListNew = attachedInvolucradosActividadListNew;
            actividad.setInvolucradosActividadList(involucradosActividadListNew);
            List<ActividadAcademica> attachedActividadAcademicaListNew = new ArrayList<ActividadAcademica>();
            for (ActividadAcademica actividadAcademicaListNewActividadAcademicaToAttach : actividadAcademicaListNew) {
                actividadAcademicaListNewActividadAcademicaToAttach = em.getReference(actividadAcademicaListNewActividadAcademicaToAttach.getClass(), actividadAcademicaListNewActividadAcademicaToAttach.getId());
                attachedActividadAcademicaListNew.add(actividadAcademicaListNewActividadAcademicaToAttach);
            }
            actividadAcademicaListNew = attachedActividadAcademicaListNew;
            actividad.setActividadAcademicaList(actividadAcademicaListNew);
            actividad = em.merge(actividad);
            if (usuarioDniOld != null && !usuarioDniOld.equals(usuarioDniNew)) {
                usuarioDniOld.getActividadList().remove(actividad);
                usuarioDniOld = em.merge(usuarioDniOld);
            }
            if (usuarioDniNew != null && !usuarioDniNew.equals(usuarioDniOld)) {
                usuarioDniNew.getActividadList().add(actividad);
                usuarioDniNew = em.merge(usuarioDniNew);
            }
            if (tipoActividadIdOld != null && !tipoActividadIdOld.equals(tipoActividadIdNew)) {
                tipoActividadIdOld.getActividadList().remove(actividad);
                tipoActividadIdOld = em.merge(tipoActividadIdOld);
            }
            if (tipoActividadIdNew != null && !tipoActividadIdNew.equals(tipoActividadIdOld)) {
                tipoActividadIdNew.getActividadList().add(actividad);
                tipoActividadIdNew = em.merge(tipoActividadIdNew);
            }
            for (Proyecto proyectoListNewProyecto : proyectoListNew) {
                if (!proyectoListOld.contains(proyectoListNewProyecto)) {
                    Actividad oldActividadIdOfProyectoListNewProyecto = proyectoListNewProyecto.getActividadId();
                    proyectoListNewProyecto.setActividadId(actividad);
                    proyectoListNewProyecto = em.merge(proyectoListNewProyecto);
                    if (oldActividadIdOfProyectoListNewProyecto != null && !oldActividadIdOfProyectoListNewProyecto.equals(actividad)) {
                        oldActividadIdOfProyectoListNewProyecto.getProyectoList().remove(proyectoListNewProyecto);
                        oldActividadIdOfProyectoListNewProyecto = em.merge(oldActividadIdOfProyectoListNewProyecto);
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
            for (ActividadInstitucion actividadInstitucionListNewActividadInstitucion : actividadInstitucionListNew) {
                if (!actividadInstitucionListOld.contains(actividadInstitucionListNewActividadInstitucion)) {
                    Actividad oldActividadIdOfActividadInstitucionListNewActividadInstitucion = actividadInstitucionListNewActividadInstitucion.getActividadId();
                    actividadInstitucionListNewActividadInstitucion.setActividadId(actividad);
                    actividadInstitucionListNewActividadInstitucion = em.merge(actividadInstitucionListNewActividadInstitucion);
                    if (oldActividadIdOfActividadInstitucionListNewActividadInstitucion != null && !oldActividadIdOfActividadInstitucionListNewActividadInstitucion.equals(actividad)) {
                        oldActividadIdOfActividadInstitucionListNewActividadInstitucion.getActividadInstitucionList().remove(actividadInstitucionListNewActividadInstitucion);
                        oldActividadIdOfActividadInstitucionListNewActividadInstitucion = em.merge(oldActividadIdOfActividadInstitucionListNewActividadInstitucion);
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
            for (ActividadAcademica actividadAcademicaListNewActividadAcademica : actividadAcademicaListNew) {
                if (!actividadAcademicaListOld.contains(actividadAcademicaListNewActividadAcademica)) {
                    Actividad oldActividadIdOfActividadAcademicaListNewActividadAcademica = actividadAcademicaListNewActividadAcademica.getActividadId();
                    actividadAcademicaListNewActividadAcademica.setActividadId(actividad);
                    actividadAcademicaListNewActividadAcademica = em.merge(actividadAcademicaListNewActividadAcademica);
                    if (oldActividadIdOfActividadAcademicaListNewActividadAcademica != null && !oldActividadIdOfActividadAcademicaListNewActividadAcademica.equals(actividad)) {
                        oldActividadIdOfActividadAcademicaListNewActividadAcademica.getActividadAcademicaList().remove(actividadAcademicaListNewActividadAcademica);
                        oldActividadIdOfActividadAcademicaListNewActividadAcademica = em.merge(oldActividadIdOfActividadAcademicaListNewActividadAcademica);
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
            List<Proyecto> proyectoListOrphanCheck = actividad.getProyectoList();
            for (Proyecto proyectoListOrphanCheckProyecto : proyectoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividad (" + actividad + ") cannot be destroyed since the Proyecto " + proyectoListOrphanCheckProyecto + " in its proyectoList field has a non-nullable actividadId field.");
            }
            List<ActividadAnexos> actividadAnexosListOrphanCheck = actividad.getActividadAnexosList();
            for (ActividadAnexos actividadAnexosListOrphanCheckActividadAnexos : actividadAnexosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividad (" + actividad + ") cannot be destroyed since the ActividadAnexos " + actividadAnexosListOrphanCheckActividadAnexos + " in its actividadAnexosList field has a non-nullable actividadId field.");
            }
            List<ActividadInstitucion> actividadInstitucionListOrphanCheck = actividad.getActividadInstitucionList();
            for (ActividadInstitucion actividadInstitucionListOrphanCheckActividadInstitucion : actividadInstitucionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividad (" + actividad + ") cannot be destroyed since the ActividadInstitucion " + actividadInstitucionListOrphanCheckActividadInstitucion + " in its actividadInstitucionList field has a non-nullable actividadId field.");
            }
            List<InvolucradosActividad> involucradosActividadListOrphanCheck = actividad.getInvolucradosActividadList();
            for (InvolucradosActividad involucradosActividadListOrphanCheckInvolucradosActividad : involucradosActividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividad (" + actividad + ") cannot be destroyed since the InvolucradosActividad " + involucradosActividadListOrphanCheckInvolucradosActividad + " in its involucradosActividadList field has a non-nullable actividadId field.");
            }
            List<ActividadAcademica> actividadAcademicaListOrphanCheck = actividad.getActividadAcademicaList();
            for (ActividadAcademica actividadAcademicaListOrphanCheckActividadAcademica : actividadAcademicaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividad (" + actividad + ") cannot be destroyed since the ActividadAcademica " + actividadAcademicaListOrphanCheckActividadAcademica + " in its actividadAcademicaList field has a non-nullable actividadId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuarioDni = actividad.getUsuarioDni();
            if (usuarioDni != null) {
                usuarioDni.getActividadList().remove(actividad);
                usuarioDni = em.merge(usuarioDni);
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
