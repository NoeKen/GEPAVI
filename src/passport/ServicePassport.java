package passport;

import personne.Personne;
import services.ServicesUtiles;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Service de gestion des passports : création, validation et invalidation, prorogation, etc.
 */
public class ServicePassport {
    private final int DUREEPASSPORT = 10;
    private int index = 1;

    public int getDureePassport() {
        return DUREEPASSPORT;
    }

    /**
     * Methode pour Generer un nouveau passport
     */
    public Personne creerPassport(Personne personne, ArrayList<Passport> passports, LocalDate dateDelivrance, LocalDate dateExpiration) {
        Passport passport = new Passport(ServicesUtiles.GenerateUniqueID(index),personne, dateDelivrance, dateExpiration);
        personne.setPassport(passport);
        passports.add(passport);
        index++;
        System.out.println("passport généré avec succès: " + personne);
        return personne;
    }

    /**
     * Methode prolonger la duree de validite d'un passport
     */
    public Passport prolongerDateExpiration(Passport passport, int prolongement) {
        if (passport.getValide()) {
            passport.setDateExpiration(passport.getDateExpiration().plusYears(prolongement));
            System.out.println("Passport prolongé avec succès jusqu'à la date du : " + passport.getDateExpiration());
            return passport;
        }
        System.out.println("Votre passport n'est plus valide. Veuillez le renouveler");
        return passport;
    }

    /**
     * Methode pout invalider un passport
     */
    public Passport invaliderPassport(Passport passport) {
        passport.setValide(false);
        System.out.println("Passport invalidé. Vous devez également faire invalider votre visa");
        return passport;
    }

    /**Retrouver un passport a partir de son numero*/
    public Passport getPassport (ArrayList<Passport> passports, String numPassport){
        for (Passport passport : passports) {
            if (passport.getNumPassport().equals(numPassport)) {
                System.out.println("Passport appartenant à : " + passport.getPersonne().getPrenom() + " " + passport.getPersonne().getNom());
                return passport;
            }
        }

        return null;
    }

}
