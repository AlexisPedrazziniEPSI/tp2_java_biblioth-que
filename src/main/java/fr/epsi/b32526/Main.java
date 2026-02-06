package fr.epsi.b32526;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        livrerepository.init();
        ajoutlivre();
        System.out.println(updatelivre(5,"Du plaisir dans la cuisine", "Jean Dupont"));
        
        testJpql();
        
        close();
    }

    static void ajoutlivre(){
        for (int i = 0; i < 5; i++) {
            livre l = new livre();
            l.setTitre("Titre"+i);
            l.setAuteur("Auteur"+i);
            livrerepository.addlivre(l);
        }
    }

    static boolean updatelivre(int id, String titre, String auteur) {
        return livrerepository.updatelivre(id, titre, auteur);
    }

    static void testJpql() {
        EntityManager em = livrerepository.newEm();
        try {
            TypedQuery<livre> queryTitre = em.createQuery("select l from livre l where l.titre = :titre", livre.class);
            queryTitre.setParameter("titre", "Titre1");
            List<livre> livresTitre = queryTitre.getResultList();
            for (livre l : livresTitre) {
                System.out.println("Livre trouvé : " + l.getTitre() + " par " + l.getAuteur());
            }

            TypedQuery<livre> queryAuteur = em.createQuery("select l from livre l where l.auteur = :auteur", livre.class);
            queryAuteur.setParameter("auteur", "Auteur2");
            List<livre> livresAuteur = queryAuteur.getResultList();
            for (livre l : livresAuteur) {
                System.out.println("Livre trouvé : " + l.getTitre() + " par " + l.getAuteur());
            }
        } finally {
            em.close();
        }
    }

    public static void close() {
        livrerepository.close();
    }
}
