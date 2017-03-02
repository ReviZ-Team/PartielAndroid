package fr.com.listepesonnalisee;

/**
 * Created by Uchuujin on 02/03/2017.
 */

public class MonObjet {
    private String nom;
    private String prenom;

    public MonObjet(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
