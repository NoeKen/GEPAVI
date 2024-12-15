package visa;

import passport.Passport;
import services.ServicesUtiles;

import java.time.LocalDate;

public class ServiceConsulaire {
    private final int VALIDITE = 5;
    private int index;

    /**
     * Methode pour la generation d'un nouveau visa pour une personne
     */
    public Visa DelivrerVisa(Passport passport, int type) {
        LocalDate dateExpiration = LocalDate.now().plusYears(VALIDITE);
        String typeVisa = "";
        Visa monVisa = null;
        switch (type) {
            case 1:
                typeVisa = "Etudiant";
                break;
            case 2:
                typeVisa = "Visiteur";
                break;
            case 3:
                typeVisa = "Travailleur";
                break;
            case 4:
                typeVisa = "Résident Permanent";
                break;
            default: {
                System.out.println("Choix invalide. Vous devez choisir l'une des options proposée");
            }
        }
        // Verification de la validite du passport avant creaction du nouveau visa
        if (passport.getValide()) {
            // Verifier si la date d'expiration du visa n'est pas superieure a celle du passport
            if (dateExpiration.isAfter(passport.getDateExpiration())) {
                //Affichage d'un message si la date d'expiration du visa n'est pas superieure a celle du passport
                System.out.println("La date d'expiration du visa est superieure a celle du passport. celle su passport lui sera automatiquement attribuee");
                dateExpiration = passport.getDateExpiration();
            }
            monVisa = new Visa(ServicesUtiles.GenerateUniqueID(index), typeVisa, LocalDate.now(), dateExpiration, passport);
            index++;
            passport.setVisa(monVisa);
            System.out.println("Félicitation! visa.Visa imposé avec succès: \n" + monVisa.seeVisa());
            return monVisa;
        }
        // Affichage d'un message en cas d'invalidite du passport et sortie de la fonction avec visa null
        System.out.println("Votre passport n'est pas valide. veuillez le renouveler");
        return null;
    }

    /**
     * Methode pour prolonger la durée du visa d'une personne
     */
    public Visa ProlongerVisa(Passport passport, int prolongement) {
        if (passport.getVisa()==null) {
            System.out.println("Aucune demande de visa n'a encore été émise pour ce passport. Nous ne pouvons donc pas prolonger son échéance.");
            return null;
        }
        if (passport.getVisa().getValide()) {
            if (passport.getDateExpiration().isBefore(passport.getVisa().getDateExpiration().plusYears(prolongement))) {
                System.out.println("La nouvelle date d'expiration du visa est superieure a celle du passport. celle su passport lui sera automatiquement attribuee");
                passport.getVisa().setDateExpiration(passport.getDateExpiration());
                return passport.getVisa();
            }
            passport.getVisa().setDateExpiration(passport.getVisa().getDateExpiration().plusYears(prolongement));
        }
        System.out.println("Votre visa n'est plus valide. Veuillez le renouveler");
        return passport.getVisa();
    }

    /**
     * Methode pour invalider un visa
     */
    public Visa InvaliderVisa(Visa visa) {
        visa.setValide(false);
        return visa;
    }

}
