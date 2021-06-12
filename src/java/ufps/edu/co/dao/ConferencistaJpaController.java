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
import ufps.edu.co.dto.Pais;
import ufps.edu.co.dto.Institucion;
import ufps.edu.co.dto.Usuario;
import ufps.edu.co.dto.TipoConferencista;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dao.exceptions.IllegalOrphanException;
import ufps.edu.co.dao.exceptions.NonexistentEntityException;
import ufps.edu.co.dao.exceptions.PreexistingEntityException;
import ufps.edu.co.dto.Conferencista;

/**
 *
 * @author dunke
 */
public class ConferencistaJpaController implements Serializable {

    public ConferencistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conferencista conferencista) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Usuario usuarioOrphanCheck = conferencista.getUsuario();
        if (usuarioOrphanCheck != null) {
            Conferencista oldConferencistaOfUsuario = usuarioOrphanCheck.getConferencista();
            if (oldConferencistaOfUsuario != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Usuario " + usuarioOrphanCheck + " already has an item of type Conferencista whose usuario column cannot be null. Please make another selection for the usuario field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais paisOrigen = conferencista.getPaisOrigen();
            if (paisOrigen != null) {
                paisOrigen = em.getReference(paisOrigen.getClass(), paisOrigen.getId());
                conferencista.setPaisOrigen(paisOrigen);
            }
            Institucion institucionId = conferencista.getInstitucionId();
            if (institucionId != null) {
                institucionId = em.getReference(institucionId.getClass(), institucionId.getId());
                conferencista.setInstitucionId(institucionId);
            }
            Usuario usuario = conferencista.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getDni());
                conferencista.setUsuario(usuario);
            }
            TipoConferencista tipoConferencistaId = conferencista.getTipoConferencistaId();
            if (tipoConferencistaId != null) {
                tipoConferencistaId = em.getReference(tipoConferencistaId.getClass(), tipoConferencistaId.getId());
                conferencista.setTipoConferencistaId(tipoConferencistaId);
            }
            em.persist(conferencista);
            if (paisOrigen != null) {
                paisOrigen.getConferencistaList().add(conferencista);
                paisOrigen = em.merge(paisOrigen);
            }
            if (institucionId != null) {
                institucionId.getConferencistaList().add(conferencista);
                institucionId = em.merge(institucionId);
            }
            if (usuario != null) {
                usuario.setConferencista(conferencista);
                usuario = em.merge(usuario);
            }
            if (tipoConferencistaId != null) {
                tipoConferencistaId.getConferencistaList().add(conferencista);
                tipoConferencistaId = em.merge(tipoConferencistaId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConferencista(conferencista.getUsuarioDni()) != null) {
                throw new PreexistingEntityException("Conferencista " + conferencista + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Conferencista conferencista) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conferencista persistentConferencista = em.find(Conferencista.class, conferencista.getUsuarioDni());
            Pais paisOrigenOld = persistentConferencista.getPaisOrigen();
            Pais paisOrigenNew = conferencista.getPaisOrigen();
            Institucion institucionIdOld = persistentConferencista.getInstitucionId();
            Institucion institucionIdNew = conferencista.getInstitucionId();
            Usuario usuarioOld = persistentConferencista.getUsuario();
            Usuario usuarioNew = conferencista.getUsuario();
            TipoConferencista tipoConferencistaIdOld = persistentConferencista.getTipoConferencistaId();
            TipoConferencista tipoConferencistaIdNew = conferencista.getTipoConferencistaId();
            List<String> illegalOrphanMessages = null;
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Conferencista oldConferencistaOfUsuario = usuarioNew.getConferencista();
                if (oldConferencistaOfUsuario != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Usuario " + usuarioNew + " already has an item of type Conferencista whose usuario column cannot be null. Please make another selection for the usuario field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (paisOrigenNew != null) {
                paisOrigenNew = em.getReference(paisOrigenNew.getClass(), paisOrigenNew.getId());
                conferencista.setPaisOrigen(paisOrigenNew);
            }
            if (institucionIdNew != null) {
                institucionIdNew = em.getReference(institucionIdNew.getClass(), institucionIdNew.getId());
                conferencista.setInstitucionId(institucionIdNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getDni());
                conferencista.setUsuario(usuarioNew);
            }
            if (tipoConferencistaIdNew != null) {
                tipoConferencistaIdNew = em.getReference(tipoConferencistaIdNew.getClass(), tipoConferencistaIdNew.getId());
                conferencista.setTipoConferencistaId(tipoConferencistaIdNew);
            }
            conferencista = em.merge(conferencista);
            if (paisOrigenOld != null && !paisOrigenOld.equals(paisOrigenNew)) {
                paisOrigenOld.getConferencistaList().remove(conferencista);
                paisOrigenOld = em.merge(paisOrigenOld);
            }
            if (paisOrigenNew != null && !paisOrigenNew.equals(paisOrigenOld)) {
                paisOrigenNew.getConferencistaList().add(conferencista);
                paisOrigenNew = em.merge(paisOrigenNew);
            }
            if (institucionIdOld != null && !institucionIdOld.equals(institucionIdNew)) {
                institucionIdOld.getConferencistaList().remove(conferencista);
                institucionIdOld = em.merge(institucionIdOld);
            }
            if (institucionIdNew != null && !institucionIdNew.equals(institucionIdOld)) {
                institucionIdNew.getConferencistaList().add(conferencista);
                institucionIdNew = em.merge(institucionIdNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setConferencista(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.setConferencista(conferencista);
                usuarioNew = em.merge(usuarioNew);
            }
            if (tipoConferencistaIdOld != null && !tipoConferencistaIdOld.equals(tipoConferencistaIdNew)) {
                tipoConferencistaIdOld.getConferencistaList().remove(conferencista);
                tipoConferencistaIdOld = em.merge(tipoConferencistaIdOld);
            }
            if (tipoConferencistaIdNew != null && !tipoConferencistaIdNew.equals(tipoConferencistaIdOld)) {
                tipoConferencistaIdNew.getConferencistaList().add(conferencista);
                tipoConferencistaIdNew = em.merge(tipoConferencistaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = conferencista.getUsuarioDni();
                if (findConferencista(id) == null) {
                    throw new NonexistentEntityException("The conferencista with id " + id + " no longer exists.");
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
            Conferencista conferencista;
            try {
                conferencista = em.getReference(Conferencista.class, id);
                conferencista.getUsuarioDni();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conferencista with id " + id + " no longer exists.", enfe);
            }
            Pais paisOrigen = conferencista.getPaisOrigen();
            if (paisOrigen != null) {
                paisOrigen.getConferencistaList().remove(conferencista);
                paisOrigen = em.merge(paisOrigen);
            }
            Institucion institucionId = conferencista.getInstitucionId();
            if (institucionId != null) {
                institucionId.getConferencistaList().remove(conferencista);
                institucionId = em.merge(institucionId);
            }
            Usuario usuario = conferencista.getUsuario();
            if (usuario != null) {
                usuario.setConferencista(null);
                usuario = em.merge(usuario);
            }
            TipoConferencista tipoConferencistaId = conferencista.getTipoConferencistaId();
            if (tipoConferencistaId != null) {
                tipoConferencistaId.getConferencistaList().remove(conferencista);
                tipoConferencistaId = em.merge(tipoConferencistaId);
            }
            em.remove(conferencista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conferencista> findConferencistaEntities() {
        return findConferencistaEntities(true, -1, -1);
    }

    public List<Conferencista> findConferencistaEntities(int maxResults, int firstResult) {
        return findConferencistaEntities(false, maxResults, firstResult);
    }

    private List<Conferencista> findConferencistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conferencista.class));
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

    public Conferencista findConferencista(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Conferencista.class, id);
        } finally {
            em.close();
        }
    }

    public int getConferencistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conferencista> rt = cq.from(Conferencista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
