package passport;

import personne.Personne;
import visa.Visa;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    public Passport(){
        this.numPassport = "2";
        this.dateDelivrance = LocalDate.now();
        this.dateExpiration = LocalDate.now().plus(5, ChronoUnit.DAYS);
        this.valide = true;
        invaliderPassport();
    }

    private long invaliderPassport() {
        // Calcul de la différence en jours totaux
        long totalDays = ChronoUnit.DAYS.between(dateDelivrance, dateExpiration);
        System.out.println("\n\n\t\t\t\nTotal de days de passport: " + totalDays+"\n\n\n");
        if (totalDays <= 7 ){
            valide = false;
        }
        return totalDays;
    }

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

    // Methode toString formatée autrement pour y inclure la personne de référence
    public String passportToString() {
        return "Passprt: {"
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
                "\n\t\tJours restants: "+ invaliderPassport()+
                "\n\t}";
    }
}
