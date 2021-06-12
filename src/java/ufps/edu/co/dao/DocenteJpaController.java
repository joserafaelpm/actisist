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
import ufps.edu.co.dto.TipoDocente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dao.exceptions.PreexistingEntityException;
import ufps.edu.co.dto.Docente;

/**
 *
 * @author dunke
 */
public class DocenteJpaController implements Serializable {

    public DocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docente docente) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Usuario usuarioOrphanCheck = docente.getUsuario();
        if (usuarioOrphanCheck != null) {
            Docente oldDocenteOfUsuario = usuarioOrphanCheck.getDocente();
            if (oldDocenteOfUsuario != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Usuario " + usuarioOrphanCheck + " already has an item of type Docente whose usuario column cannot be null. Please make another selection for the usuario field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = docente.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getDni());
                docente.setUsuario(usuario);
            }
            TipoDocente idTipoDocente = docente.getIdTipoDocente();
            if (idTipoDocente != null) {
                idTipoDocente = em.getReference(idTipoDocente.getClass(), idTipoDocente.getId());
                docente.setIdTipoDocente(idTipoDocente);
            }
            em.persist(docente);
            if (usuario != null) {
                usuario.setDocente(docente);
                usuario = em.merge(usuario);
            }
            if (idTipoDocente != null) {
                idTipoDocente.getDocenteList().add(docente);
                idTipoDocente = em.merge(idTipoDocente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocente(docente.getUsuarioDni()) != null) {
                throw new PreexistingEntityException("Docente " + docente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docente docente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente persistentDocente = em.find(Docente.class, docente.getUsuarioDni());
            Usuario usuarioOld = persistentDocente.getUsuario();
            Usuario usuarioNew = docente.getUsuario();
            TipoDocente idTipoDocenteOld = persistentDocente.getIdTipoDocente();
            TipoDocente idTipoDocenteNew = docente.getIdTipoDocente();
            List<String> illegalOrphanMessages = null;
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Docente oldDocenteOfUsuario = usuarioNew.getDocente();
                if (oldDocenteOfUsuario != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Usuario " + usuarioNew + " already has an item of type Docente whose usuario column cannot be null. Please make another selection for the usuario field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getDni());
                docente.setUsuario(usuarioNew);
            }
            if (idTipoDocenteNew != null) {
                idTipoDocenteNew = em.getReference(idTipoDocenteNew.getClass(), idTipoDocenteNew.getId());
                docente.setIdTipoDocente(idTipoDocenteNew);
            }
            docente = em.merge(docente);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setDocente(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.setDocente(docente);
                usuarioNew = em.merge(usuarioNew);
            }
            if (idTipoDocenteOld != null && !idTipoDocenteOld.equals(idTipoDocenteNew)) {
                idTipoDocenteOld.getDocenteList().remove(docente);
                idTipoDocenteOld = em.merge(idTipoDocenteOld);
            }
            if (idTipoDocenteNew != null && !idTipoDocenteNew.equals(idTipoDocenteOld)) {
                idTipoDocenteNew.getDocenteList().add(docente);
                idTipoDocenteNew = em.merge(idTipoDocenteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = docente.getUsuarioDni();
                if (findDocente(id) == null) {
                    throw new NonexistentEntityException("The docente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente docente;
            try {
                docente = em.getReference(Docente.class, id);
                docente.getUsuarioDni();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docente with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = docente.getUsuario();
            if (usuario != null) {
                usuario.setDocente(null);
                usuario = em.merge(usuario);
            }
            TipoDocente idTipoDocente = docente.getIdTipoDocente();
            if (idTipoDocente != null) {
                idTipoDocente.getDocenteList().remove(docente);
                idTipoDocente = em.merge(idTipoDocente);
            }
            em.remove(docente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docente> findDocenteEntities() {
        return findDocenteEntities(true, -1, -1);
    }

    public List<Docente> findDocenteEntities(int maxResults, int firstResult) {
        return findDocenteEntities(false, maxResults, firstResult);
    }

    private List<Docente> findDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docente.class));
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

    public Docente findDocente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docente> rt = cq.from(Docente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
