package fr.epsi.b32526;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        livrerepository.init();
        ajoutlivre();
        System.out.println(updatelivre(5,"Du plaisir dans la cuisine", "Jean Dupont"));
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

    public static void close() {
        livrerepository.close();
    }
}
