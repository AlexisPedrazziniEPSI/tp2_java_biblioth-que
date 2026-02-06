package fr.epsi.b32526;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class livrerepository {
    private static EntityManagerFactory emf;

    public static void init() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("pu");
        }
    }

    public static void close() {
        if (emf != null) {
            emf.close();
            emf = null;
        }
    }

    private static EntityManager newEm() {
        if (emf == null) {
            throw new IllegalStateException("livrerepository.init() must be called before using the repository.");
        }
        return emf.createEntityManager();
    }

    public static livre findbyid(int id) {
        try (EntityManager em = newEm()) {
            return em.find(livre.class, id);
        }
    }

    public static livre[] findAlllivres() {
        try (EntityManager em = newEm()) {
            return em.createQuery("from livre", livre.class)
                    .getResultList()
                    .toArray(new livre[0]);
        }
    }

    public static void addlivre(livre l) {
        EntityManager em = newEm();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(l);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static boolean deletelivre(int id) {
        EntityManager em = newEm();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            livre l = em.find(livre.class, id);
            if (l == null) {
                tx.commit();
                return false;
            }
            em.remove(l);
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static boolean updatelivre(int id, String titre, String auteur) {
        EntityManager em = newEm();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            livre l = em.find(livre.class, id);
            if (l == null) {
                tx.commit();
                return false;
            }
            l.setTitre(titre);
            l.setAuteur(auteur);
            em.merge(l);
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}