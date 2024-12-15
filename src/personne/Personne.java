package personne;

import passport.Passport;

public class Personne {
    private String refID;
    private String nom;
    private String prenom;
    private Passport passport;

    //Définition du constructeur personne.Personne
//    public personne.Personne(String nom, String prenom, passport.Passport passport) {
//        this.nom = nom;
//        this.prenom = prenom;
//        this.passport = passport;
//    }

    public Personne(String ID, String nom, String prenom) {
        this.refID = ID;
        this.nom = nom;
        this.prenom = prenom;
    }

    // Définition des getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "personne.Personne{" +
                "\n\trefID='" + refID + '\'' +
                ", \n\tnom='" + nom + '\'' +
                ", \n\tprenom='" + prenom + '\'' +
                ", \t" + passport +
                "\n}";
    }
}