package fr.epsi.b32526;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TestBibliotheque {
    static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        try {
            Emprunt emprunt = em.find(Emprunt.class, 1);
            if (emprunt != null) {
                System.out.println("Emprunt du " + emprunt.getDateDebut() + " par " + emprunt.getClient().getNom());
                System.out.println("Livres empruntés :");
                for (livre l : emprunt.getLivres()) {
                    System.out.println("- " + l.getTitre() + " (" + l.getAuteur() + ")");
                }
            } else {
                System.out.println("Emprunt non trouvé.");
            }

            TypedQuery<Emprunt> query = em.createQuery("select e from Emprunt e where e.client.id = :clientId", Emprunt.class);
            query.setParameter("clientId", 1);
            List<Emprunt> empruntsClient = query.getResultList();
            
            for (Emprunt e : empruntsClient) {
                System.out.println("Emprunt ID: " + e.getId() + ", Date début: " + e.getDateDebut());
                System.out.println("  Livres:");
                for (livre l : e.getLivres()) {
                    System.out.println("  - " + l.getTitre());
                }
            }

        } finally {
            em.close();
            emf.close();
        }
    }
}
