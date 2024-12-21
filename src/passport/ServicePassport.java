package passport;

import personne.Personne;
import services.ServicesUtiles;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        try {
            Passport passport = new Passport(ServicesUtiles.GenerateUniqueID(index), personne, dateDelivrance, dateExpiration);
            personne.setPassport(passport);
            passports.add(passport);
            index++;
            System.out.println("passport généré avec succès: " + personne);
            return personne;
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de la création du passport");
            throw new RuntimeException(e);
        }
    }

    /**
     * Methode prolonger la duree de validite d'un passport
     */
    public void prolongerDateExpiration(Passport passport, int prolongement) {
        try {
            if (passport.getValide()) {
                passport.setDateExpiration(passport.getDateExpiration().plusYears(prolongement));
                System.out.println("Passport prolongé avec succès jusqu'à la date du : " + passport.getDateExpiration());
                return;
            }
            System.out.println("Votre passport n'est plus valide. Veuillez le renouveler");
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors du prolongement de la durée du passport");
            throw new RuntimeException(e);
        }
    }

    /**
     * Methode pout invalider un passport
     */
    public void invaliderPassport(Passport passport) {
        try {
            passport.setValide(false);
            System.out.println("Passport invalidé. Vous devez également faire invalider votre visa");
            // Verifier si la synchronisation est activée pour cet utilisateur et invalider automatiquement le visa
            if (passport.getPersonne().isSynchronize()) {
                passport.getVisa().setValide(false);
                System.out.println("Votre visa a également été automatiquement invalidé");
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'invalidation du passport ");
        }
    }

    /**
     * Retrouver un passport à partir de son numéro
     */
    public Passport getPassport(ArrayList<Passport> passports, String numPassport) {
        for (Passport passport : passports) {
            if (passport.getNumPassport().equals(numPassport)) {
                System.out.println("Passport appartenant à : " + passport.getPersonne().getPrenom() + " " + passport.getPersonne().getNom());
                return passport;
            }
        }

        return null;
    }

    public ArrayList<Passport> verifierExpirationPassport(ArrayList<Passport> passports) {
        ArrayList<Passport> validitePassports = new ArrayList<>();
        ArrayList<Passport> invalidePassports = new ArrayList<>();
        for (Passport passport : passports) {
            // Calculer la différence en jours
            long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), passport.getDateExpiration());
            System.out.println("Nombre de jours restants: " + daysBetween);
            if (passport.getValide()) {
                validitePassports.add(passport);
            }
            if (!passport.getValide()) {
                if (passport.getPersonne().isSynchronize()) {
                    passport.setValide(true);
                }
                invalidePassports.add(passport);
            }
        }
        return validitePassports;
    }
}
