package fr.epsi.b32526;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "emprunt")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DATE_DEBUT")
    private LocalDateTime dateDebut;

    @Column(name = "DATE_FIN")
    private LocalDateTime dateFin;

    @Column(name = "DELAI")
    private int delai;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;

    @ManyToMany
    @JoinTable(name = "COMPO",
            joinColumns = @JoinColumn(name = "ID_EMP"),
            inverseJoinColumns = @JoinColumn(name = "ID_LIV")
    )
    private Set<livre> livres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public int getDelai() {
        return delai;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<livre> livres) {
        this.livres = livres;
    }
}
