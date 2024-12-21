package services;

import java.security.SecureRandom;
import java.util.Scanner;

public final class ServicesUtiles {
    // Constructeur privé pour empêcher l'instanciation
    private ServicesUtiles() {
        throw new UnsupportedOperationException("Cette classe ne peut pas être instanciée.");
    }

    /**
     * Methode pour gener une cle d'ID unique
     */
    public static String GenerateUniqueID(int index) {
        String lettres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String chiffres = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder numero = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            // Ajouter une lettre
            numero.append(lettres.charAt(random.nextInt(lettres.length())));

            // Ajouter un chiffre
            numero.append(chiffres.charAt(random.nextInt(chiffres.length())));
        }
        return numero.toString() + index;
    }

    /**
     * Gestion d'exception pour entree invalide sur type Int attendu
     */
    public static int IntExceptionManager(Scanner sc) {
        int choix = 0;
        boolean valid;
        do {
            try {
                choix = sc.nextInt();
                valid = true;
            } catch (Exception e) {
                valid = false;
                sc.nextLine(); // Consommer l'entrée incorrecte conservée dans le buffer pour éviter la boucle infinie
                System.out.println("Entrée non valide. Veuillez entrer le chiffre correspondant à votre choix");
            }
        } while (!valid);
        return choix;
    }


}
