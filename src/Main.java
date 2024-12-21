import passport.Passport;
import passport.ServicePassport;
import personne.Personne;
import services.ServicesUtiles;
import visa.ServiceConsulaire;
import visa.Visa;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        boolean skip; // Pour sauter la question de reprise du programme au Menu et sortir du programme
        ArrayList<Personne> personnes = new ArrayList<>();
        ServicePassport servicePassport = new ServicePassport();
        ServiceConsulaire serviceConsulaire = new ServiceConsulaire();
        int personneIndex = 1;
        Scanner sc = new Scanner(System.in);
        int choixMenu = 0;
        boolean repeter = true;

//        Passport passport = new Passport();
//        System.out.println("======= jours  passprt:  "+passport);


        System.out.println("\n\n**************************************************************************************************");
        System.out.println("\n\n            Binvenu sur la GEPAVI (Centre de Gestion des Passports et Visas)\n");
        System.out.println("\n                                    M E N U \n");
        do {
            skip = false;
            System.out.println("Que désirez-vous faire? Entrez le numéro correspondant\n");
            System.out.println("""
                    \t0.Verifier Expiration
                    \t1.Créer une personne\s
                    \t2.Demander un passeport
                    \t3.Demander un visa
                    \t4.Afficher les informations d’une personne
                    \t5.Simuler une situation
                    \t6.Inscription aux processus automatiques
                    NB: Entrer n'importe quelle autre valeur numérique pour quitter le programme""");
            // Capture de la réponse de l'utilisateur et gestion d'exception suite à une entrée de donnée invalide
            choixMenu = ServicesUtiles.IntExceptionManager(sc);

            switch (choixMenu) {
                case 0: {
                    servicePassport.verifierExpirationPassport();
                    break;
                }
                case 1: {
                    creerPersonne(personnes, personneIndex);
                    personneIndex++;
                    break;
                }
                case 2: {
                    demanderPassport(personneIndex, personnes, servicePassport, sc);
                    break;
                }
                case 3: {
                    demanderVisa(servicePassport, serviceConsulaire, sc);
                    break;
                }
                case 4: {
                    afficherPersonne(personnes, sc);
                    break;
                }
                case 5: {
                    int choixsubMenu = 0;
                    System.out.println("\n\n\t\t\t\tMENU de SIMULATION\n");
                    System.out.println("Que voulez-vous faire? Entrez le numéro correspondant");
                    System.out.println("""
                            1.Annuler un passeport\s
                            2.Annuler un visa
                            3.Prolonger la validité du passeport
                            4.Prolonger la validité du visa
                            5.Vérification annuelle
                            6.Retourner au menu principal""");
                    choixsubMenu = ServicesUtiles.IntExceptionManager(sc);
                    switch (choixsubMenu) {
                        case 1: {
                            declarerPerte(servicePassport, sc);
                            break;
                        }
                        case 2: {
                            AnnulerVisa(servicePassport, serviceConsulaire, sc);
                            break;
                        }
                        case 3: {
                            prolongerValiditePassport(servicePassport, sc);
                            break;
                        }
                        case 4: {
                            prolongerValiditeVisa( servicePassport, serviceConsulaire, sc);
                            break;
                        }
                        case 5: {
                            servicePassport.verifierExpirationPassport();
                            break;
                        }
                        case 6: {
                            skip = true;
                            break;
                        }
                        default:
                            System.out.println("Vous avez entré une valeur qui ne correspond pas aux choix proposés");
                            repeter = false;
                    }
                    break;
                }
                case 6: {
                    declancherAutomatisation(personnes, sc);
                    break;
                }
                default: {
                    repeter = false;
                }
            }
            if (repeter && !skip) {
                System.out.println("\nRetourner au menu principal? \n1.Oui \n2.Quitter");
                repeter = ServicesUtiles.IntExceptionManager(sc) == 1;
            } else
                System.out.println("\n\t\tMerci d'avoir utilisé le programme GEPAVI. Au revoir !");
        } while (repeter);
    }

    /**
     * Méthode pour generer une nouvelle personne à partir des données utilisateur
     */
    private static Personne creerPersonne(ArrayList<Personne> personnes, int index) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer le nom de la personne: ");
        String nom = sc.next();
        System.out.println("Entrer le prénom de la personne: ");
        String prenom = sc.next();
        // Générer un ID aléatoire à l'aide de la methode GenerateID() et ajout d'un index pour le rendre unique
        Personne personne = new Personne(ServicesUtiles.GenerateUniqueID(index), nom, prenom);
        personnes.add(personne);
        System.out.println("\nEnregistrement terminé avec succes ");
        System.out.println("    " + personne);

        return personne;
    }

    /**
     * Methode pour faire la demande d'un nouveau passport au service des passports
     */
    private static void demanderPassport(int index, ArrayList<Personne> personnes, ServicePassport servicePassport, Scanner sc) {
        int choix = 0;
        String personRefID;
        Personne personne = null;
        do {
            System.out.println("Quelle est votre situation? Entrez le chiffre correspondant à votre choix \n1.Nouvelle personne\n2.Je possède un numéro de référence ");
            choix = ServicesUtiles.IntExceptionManager(sc);
        } while (choix != 1 && choix != 2);
        if (choix == 1) {
            personne = creerPersonne(personnes, index);
        } else {
            System.out.println("Entrez le numéro de référence (personrefID)");
            personRefID = sc.next();
            // Recherche de la personne dans la liste des personnes existantes à partir du personrefID entré par l'utilisateur
            for (Personne person : personnes) {
                if (person.getRefID().equals(personRefID)) {
                    personne = person;
                }
            }
            if (personne == null) {
                System.out.println("la personne avec le redID: '" + personRefID + "' n'est pas présent dans le système. Veuillez enregistrer la personne");
                return;
            }
        }
        // Génération du nouveau passport
        servicePassport.creerPassport(personne);


    }

    /**
     * Methode pour effectuer une demande de visa au service consulaire
     */
    private static void demanderVisa(ServicePassport servicePassport, ServiceConsulaire serviceConsulaire, Scanner sc) {
        String numPassport;
        Passport monPassport;
        int type = 2;
        System.out.println("Entrer le numero du passport : ");
        numPassport = sc.next();
        //Recherche du passport dans la liste des passports existants à partir du numero de passport entré par l'utilisateur
        monPassport = servicePassport.getPassport(numPassport);
        if (monPassport == null) {
            System.out.println("le passport avec le numéro: '" + numPassport + "' n'a pas été trouvée. veuillez faire une nouvelle demande de passport");
            return;
        }
        System.out.println("""
                Entrer le type de passport :\s
                    1.Etudiant
                    2.Visiteur
                    3.Travailleur
                    4.Resident permanent""");
        type = ServicesUtiles.IntExceptionManager(sc);
        serviceConsulaire.DelivrerVisa(monPassport, type);
    }

    /**
     * Methode pour afficher les details d'informations d'une personne en particulier
     */
    private static void afficherPersonne(ArrayList<Personne> personnes, Scanner sc) {
        Personne personne = null;
        System.out.println("Entrer le numéro de référence de la personne recherchée");
        String refID = sc.next();
        for (Personne person : personnes) {
            if (person.getRefID().equals(refID)) {
                personne = person;
            }
        }
        if (personne == null) {
            System.out.println("Aucune personne trouvée avec ce numéro de référence");
            return;
        }
        System.out.println(personne);
    }

    /**
     * Methode pour declarer la perte d'un passport.
     * On assumera qu'un passport déclaré perdu est rendu invalide
     */
    private static void declarerPerte(ServicePassport servicePassport, Scanner sc) {
        System.out.println("Veuillez entrer le numéro du passport: ");
        String numPassport = sc.next();
        //Recherche d'un passport via la classe ServicePassport
        Passport monPassport = servicePassport.getPassport(numPassport);
        //Vérifier si le passport a été retrouvé dans la liste des passports enrégistrés
        if (monPassport == null) {
            System.out.println("Passport non trouvé dans la base avec ce numéro : " + numPassport);
            return;
        }
        // Invalidation du passport si retrouvé par le service des passport
        servicePassport.invaliderPassport(monPassport);
    }

    /**
     * Methode pour annuler un visa
     */
    private static void AnnulerVisa(ServicePassport servicePassport, ServiceConsulaire serviceConsulaire, Scanner sc) {
        System.out.println("Veuillez entrer le numéro du passport: ");
        String numPassport = sc.next();
        //Recherche d'un passport via la classe ServicePassport
        Passport monPassport = servicePassport.getPassport(numPassport);
        //Vérifier si le passport a été retrouvé dans la liste des passports enrégistrés
        if (monPassport == null) {
            System.out.println("passport.Passport non trouvé dans la base avec ce numéro : " + numPassport);
            return;
        }
        serviceConsulaire.InvaliderVisa(monPassport);
    }

    /**
     * Methode pour prolonger la validite d'un passport
     */
    private static void prolongerValiditePassport(ServicePassport servicePassport, Scanner sc) {
        System.out.println("Veuillez entrer le numéro du passport: ");
        String numPassport = sc.next();
        //Recherche d'un passport via la classe ServicePassport
        Passport monPassport = servicePassport.getPassport(numPassport);
        //Vérifier si le passport a été retrouvé dans la liste des passports enrégistrés
        if (monPassport == null) {
            System.out.println("passport.Passport non trouvé dans la base avec ce numéro : " + numPassport);
            return;
        }
        System.out.println("Entrer le nombre d'années à ajouter au passport");
        int prolongement = ServicesUtiles.IntExceptionManager(sc);
        servicePassport.prolongerDateExpiration(monPassport, prolongement);
    }

    /**
     * Methode pour prolonger la validite d'un visa
     */
    private static void prolongerValiditeVisa(ServicePassport servicePassport, ServiceConsulaire serviceConsulaire, Scanner sc) {
        System.out.println("Veuillez entrer le numéro du passport: ");
        String numPassport = sc.next();
        //Recherche d'un passport via la classe ServicePassport
        Passport monPassport = servicePassport.getPassport(numPassport);
        //Vérifier si le passport a été retrouvé dans la liste des passports enrégistrés
        if (monPassport == null) {
            System.out.println("passport.Passport non trouvé dans la base avec ce numéro : " + numPassport);
            return;
        }
        System.out.println("Entrer le nombre d'années à ajouter au visa");
        int prolongement = ServicesUtiles.IntExceptionManager(sc);
        serviceConsulaire.ProlongerVisa(monPassport, prolongement);
    }

    /**
     * Methode pour Activer la synchronisation automatique sur les comptes
     */
    private static void declancherAutomatisation(ArrayList<Personne> personnes, Scanner sc) {
        try {
            Personne personne = null;
            String refID;
            System.out.println("Entrer le refID de la personne pour qui déclancher l'automatisation: ");
            refID = sc.next();
            for (Personne person : personnes) {
                if (person.getRefID().equals(refID)) {
                    personne = person;
                }
            }
            if (personne == null) {
                System.out.println("Aucune personne trouvée avec ce refID: " + refID);
            } else {
                personne.setSynchronize(true);
                System.out.println("La synchronisation a bien été activée pour le compte de : " + personne.getPrenom() + " " + personne.getNom());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
