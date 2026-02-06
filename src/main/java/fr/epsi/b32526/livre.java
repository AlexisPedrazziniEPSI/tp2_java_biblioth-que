package fr.epsi.b32526;

import jakarta.persistence.*;

@Entity
@Table(name = "livre")
public class livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITRE")
    private String titre;

    @Column(name = "AUTEUR")
    private String auteur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
