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
        return personne;
    }

    /**
     * Methode prolonger la duree de validite d'un passport
     */
    public Passport prolongerDateExpiration(Passport passport, int prolongement) {
        passport.setDateExpiration(passport.getDateExpiration().plusYears(prolongement));
        return passport;
    }

    /**
     * Methode pout invalider un passport
     */
    public Passport invaliderPassport(Passport passport) {
        passport.setValide(false);
        System.out.println("passport.Passport invalidé. Vous devez également faire invalider votre visa");
        return passport;
    }

    /**Retrouver un passport a partir de son numero*/
    public Passport getPassport (ArrayList<Passport> passports, String numPassport){
        for (Passport passport : passports) {
            if (passport.getNumPassport().equals(numPassport)) {
                return passport;
            }
        }
        return null;
    }

}
