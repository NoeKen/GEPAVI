package visa;

import passport.Passport;

import java.time.LocalDate;

public class Visa {
    private String refID;
    private String type;
    private LocalDate dateDelivrance;
    private LocalDate dateExpiration;
    private Boolean valide = true;
    private Passport passport;

    public Visa(String refID, String type, LocalDate dateDelivrance, LocalDate dateExpiration, Passport passport) {
        this.refID = refID;
        this.type = type;
        this.dateDelivrance = dateDelivrance;
        this.dateExpiration = dateExpiration;
        this.passport = passport;
    }

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(LocalDate dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String seeVisa() {
        return "Visa{" +
                ", \n\trefID='" + refID + '\'' +
                ", \n\ttype='" + type + '\'' +
                ", \n\tdateDelivrance=" + dateDelivrance +
                ", \n\tdateExpiration=" + dateExpiration +
                ", \n\tvalide=" + valide +
                ", \n\tnuméro passport=" + passport.getNumPassport() +
                ", \n\tNom personne =" + passport.getPersonne().getNom() + " " + passport.getPersonne().getPrenom() +
                "\n}";
    }

    @Override
    public String toString() {
        return "Visa{" +
                "\n\t\t\trefID='" + refID + '\'' +
                ", \n\t\t\ttype='" + type + '\'' +
                ", \n\t\t\tdateDelivrance=" + dateDelivrance +
                ", \n\t\t\tdateExpiration=" + dateExpiration +
                ", \n\t\t\tvalide=" + valide +
                "\n\t\t}";
    }
}
