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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dto.Rol;
import ufps.edu.co.dto.SolicitudRegistro;

/**
 *
 * @author dunke
 */
public class RolJpaController implements Serializable {

    public RolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rol rol) {
        if (rol.getUsuarioList() == null) {
            rol.setUsuarioList(new ArrayList<Usuario>());
        }
        if (rol.getSolicitudRegistroList() == null) {
            rol.setSolicitudRegistroList(new ArrayList<SolicitudRegistro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : rol.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getDni());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            rol.setUsuarioList(attachedUsuarioList);
            List<SolicitudRegistro> attachedSolicitudRegistroList = new ArrayList<SolicitudRegistro>();
            for (SolicitudRegistro solicitudRegistroListSolicitudRegistroToAttach : rol.getSolicitudRegistroList()) {
                solicitudRegistroListSolicitudRegistroToAttach = em.getReference(solicitudRegistroListSolicitudRegistroToAttach.getClass(), solicitudRegistroListSolicitudRegistroToAttach.getToken());
                attachedSolicitudRegistroList.add(solicitudRegistroListSolicitudRegistroToAttach);
            }
            rol.setSolicitudRegistroList(attachedSolicitudRegistroList);
            em.persist(rol);
            for (Usuario usuarioListUsuario : rol.getUsuarioList()) {
                Rol oldIdRolOfUsuarioListUsuario = usuarioListUsuario.getIdRol();
                usuarioListUsuario.setIdRol(rol);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldIdRolOfUsuarioListUsuario != null) {
                    oldIdRolOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldIdRolOfUsuarioListUsuario = em.merge(oldIdRolOfUsuarioListUsuario);
                }
            }
            for (SolicitudRegistro solicitudRegistroListSolicitudRegistro : rol.getSolicitudRegistroList()) {
                Rol oldTypeUsOfSolicitudRegistroListSolicitudRegistro = solicitudRegistroListSolicitudRegistro.getTypeUs();
                solicitudRegistroListSolicitudRegistro.setTypeUs(rol);
                solicitudRegistroListSolicitudRegistro = em.merge(solicitudRegistroListSolicitudRegistro);
                if (oldTypeUsOfSolicitudRegistroListSolicitudRegistro != null) {
                    oldTypeUsOfSolicitudRegistroListSolicitudRegistro.getSolicitudRegistroList().remove(solicitudRegistroListSolicitudRegistro);
                    oldTypeUsOfSolicitudRegistroListSolicitudRegistro = em.merge(oldTypeUsOfSolicitudRegistroListSolicitudRegistro);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rol rol) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getId());
            List<Usuario> usuarioListOld = persistentRol.getUsuarioList();
            List<Usuario> usuarioListNew = rol.getUsuarioList();
            List<SolicitudRegistro> solicitudRegistroListOld = persistentRol.getSolicitudRegistroList();
            List<SolicitudRegistro> solicitudRegistroListNew = rol.getSolicitudRegistroList();
            List<String> illegalOrphanMessages = null;
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuarioListOldUsuario + " since its idRol field is not nullable.");
                }
            }
            for (SolicitudRegistro solicitudRegistroListOldSolicitudRegistro : solicitudRegistroListOld) {
                if (!solicitudRegistroListNew.contains(solicitudRegistroListOldSolicitudRegistro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SolicitudRegistro " + solicitudRegistroListOldSolicitudRegistro + " since its typeUs field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getDni());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            rol.setUsuarioList(usuarioListNew);
            List<SolicitudRegistro> attachedSolicitudRegistroListNew = new ArrayList<SolicitudRegistro>();
            for (SolicitudRegistro solicitudRegistroListNewSolicitudRegistroToAttach : solicitudRegistroListNew) {
                solicitudRegistroListNewSolicitudRegistroToAttach = em.getReference(solicitudRegistroListNewSolicitudRegistroToAttach.getClass(), solicitudRegistroListNewSolicitudRegistroToAttach.getToken());
                attachedSolicitudRegistroListNew.add(solicitudRegistroListNewSolicitudRegistroToAttach);
            }
            solicitudRegistroListNew = attachedSolicitudRegistroListNew;
            rol.setSolicitudRegistroList(solicitudRegistroListNew);
            rol = em.merge(rol);
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Rol oldIdRolOfUsuarioListNewUsuario = usuarioListNewUsuario.getIdRol();
                    usuarioListNewUsuario.setIdRol(rol);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldIdRolOfUsuarioListNewUsuario != null && !oldIdRolOfUsuarioListNewUsuario.equals(rol)) {
                        oldIdRolOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldIdRolOfUsuarioListNewUsuario = em.merge(oldIdRolOfUsuarioListNewUsuario);
                    }
                }
            }
            for (SolicitudRegistro solicitudRegistroListNewSolicitudRegistro : solicitudRegistroListNew) {
                if (!solicitudRegistroListOld.contains(solicitudRegistroListNewSolicitudRegistro)) {
                    Rol oldTypeUsOfSolicitudRegistroListNewSolicitudRegistro = solicitudRegistroListNewSolicitudRegistro.getTypeUs();
                    solicitudRegistroListNewSolicitudRegistro.setTypeUs(rol);
                    solicitudRegistroListNewSolicitudRegistro = em.merge(solicitudRegistroListNewSolicitudRegistro);
                    if (oldTypeUsOfSolicitudRegistroListNewSolicitudRegistro != null && !oldTypeUsOfSolicitudRegistroListNewSolicitudRegistro.equals(rol)) {
                        oldTypeUsOfSolicitudRegistroListNewSolicitudRegistro.getSolicitudRegistroList().remove(solicitudRegistroListNewSolicitudRegistro);
                        oldTypeUsOfSolicitudRegistroListNewSolicitudRegistro = em.merge(oldTypeUsOfSolicitudRegistroListNewSolicitudRegistro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rol.getId();
                if (findRol(id) == null) {
                    throw new NonexistentEntityException("The rol with id " + id + " no longer exists.");
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
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rol with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Usuario> usuarioListOrphanCheck = rol.getUsuarioList();
            for (Usuario usuarioListOrphanCheckUsuario : usuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the Usuario " + usuarioListOrphanCheckUsuario + " in its usuarioList field has a non-nullable idRol field.");
            }
            List<SolicitudRegistro> solicitudRegistroListOrphanCheck = rol.getSolicitudRegistroList();
            for (SolicitudRegistro solicitudRegistroListOrphanCheckSolicitudRegistro : solicitudRegistroListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the SolicitudRegistro " + solicitudRegistroListOrphanCheckSolicitudRegistro + " in its solicitudRegistroList field has a non-nullable typeUs field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rol> findRolEntities() {
        return findRolEntities(true, -1, -1);
    }

    public List<Rol> findRolEntities(int maxResults, int firstResult) {
        return findRolEntities(false, maxResults, firstResult);
    }

    private List<Rol> findRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rol.class));
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

    public Rol findRol(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rol> rt = cq.from(Rol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
