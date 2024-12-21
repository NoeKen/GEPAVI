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
    ArrayList<Passport> passports = new ArrayList<>();

    public int getDureePassport() {
        return DUREEPASSPORT;
    }

    /**
     * Methode pour Generer un nouveau passport
     */
    public void creerPassport(Personne personne) {
        try {
            Passport passport = new Passport(ServicesUtiles.GenerateUniqueID(index), personne, LocalDate.now(), LocalDate.now().plusYears(DUREEPASSPORT));
            personne.setPassport(passport);
            passports.add(passport);
            index++;
            System.out.println("passport généré avec succès: " + personne);
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

                /* Vérifier si la synchronisation est activée pour cette personne et possède bien un visa pour prolonger sa durée
                 jusqu'à la nouvelle date d'expiration du passport */
                if (passport.getPersonne().isSynchronize() && passport.getVisa() != null) {
                    passport.getVisa().setDateExpiration(passport.getDateExpiration());
                    System.out.println("Votre visa a également été prolongé jusqu'à la même date du passport");
                }
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
            //Invalidation du passport en définissant son attribut setValide à false
            passport.setValide(false);
            // Verifier si la synchronisation est activée pour cet utilisateur et invalider automatiquement le visa
            if (passport.getPersonne().isSynchronize()) {
                passport.getVisa().setValide(false);
                System.out.println("Passport invalidé. Votre visa a également été automatiquement invalidé");
            }else
                System.out.println("Passport invalidé. Vous devez également faire invalider votre visa");
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'invalidation du passport ");
        }
    }

    /**
     * Retrouver un passport à partir de son numéro
     */
    public Passport getPassport(String numPassport) {
        for (Passport passport : passports) {
            if (passport.getNumPassport().equals(numPassport)) {
                System.out.println("Passport appartenant à : " + passport.getPersonne().getPrenom() + " " + passport.getPersonne().getNom());
                return passport;
            }
        }

        return null;
    }

    /**
     * Vérifie la validité des passports et affiche ceux valides et ceux invalides
     */
    public void verifierExpirationPassport() {
        // Verifier si la liste est vide
        if (!passports.isEmpty()) {
            // Affichage des passports invalides
            System.out.println("\n\n\tPassports invalides: ");
            for (Passport passport : passports) {
                //Verification des passports invalides
                if (!passport.getValide()) {
                    System.out.println(passport.passportToString());
                }
            }
            // Affichage des passports valides
            System.out.println("\n\tPassports valides: ");
            for (Passport passport : passports) {
                //Verification des passports valides
                if (passport.getValide()) {
                    System.out.println(passport.passportToString());
                }
            }
        } else
            System.out.println("\nDésolé,aucune donnée trouvée");
    }
}
