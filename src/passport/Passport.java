package passport;

import personne.Personne;
import visa.Visa;

import java.security.SecureRandom;
import java.time.LocalDate;

public class Passport {
    private String numPassport;
    private LocalDate dateDelivrance;
    private LocalDate dateExpiration;
    private String lieuDelivrance = "Montréal";
    private Boolean valide = true;
    private Visa visa;
    private Personne personne;

    public Passport(
            String numPassport,
            Personne personne,
            LocalDate dateDelivrance,
            LocalDate dateExpiration) {
        this.numPassport = numPassport;
        this.personne = personne;
        this.dateDelivrance = dateDelivrance;
        this.dateExpiration = dateExpiration;
    }

    //public Passport(String numPassport, Personne personne, LocalDate dateDelivrance, LocalDate dateExpiration) {
    //}

    public String getNumPassport() {
        return numPassport;
    }

    public LocalDate getDateDelivrance() {
        return dateDelivrance;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public String getLieuDelivrance() {
        return lieuDelivrance;
    }

    public Boolean getValide() {
        return valide;
    }

    public Visa getVisa() {
        return visa;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setDateDelivrance(LocalDate dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public void setLieuDelivrance(String lieuDelivrance) {
        this.lieuDelivrance = lieuDelivrance;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public void setVisa(Visa visa) {
        this.visa = visa;
    }

    public String passportToString() {
        return "\nPassprt: {"
                + "\n\tnumPassport= " + numPassport
                + "\n\tdateDelivrance= " + dateDelivrance
                + "\n\tdateExpiration= " + dateExpiration
                + "\n\tlieuDelivrance= " + lieuDelivrance
                + "\n\tvalide= " + valide
                + "\n\tvisa= " + visa
                + "\n\tpersonne= " + personne.getNom()+" "+personne.getPrenom()
                + "\n}";
    }
    @Override
    public String toString() {
        return "{" +
                "\n\t\tnumPassport='" + numPassport + '\'' +
                ", \n\t\tdateDelivrance=" + dateDelivrance +
                ", \n\t\tdateExpiration=" + dateExpiration +
                ", \n\t\tlieuDelivrance='" + lieuDelivrance + '\'' +
                ", \n\t\tvalide=" + valide +
                ", \n\t\tvisa=" + visa +
                "\n\t}";
    }
}
