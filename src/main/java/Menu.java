package main.java;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Cette classe sert de Menu pour la selection d'un jeu
 */
public class Menu {

    private static final Logger logger = LogManager.getLogger(Menu.class);

    /**
     * Methode pour le choix des jeux proposés
     * @throws Exception propagée par le mode developpeur
     */
    public int displayAffichage() throws Exception {
        /**
         * Cette methode retourne le choix selectionné
         */
        logger.info("Bienvenue dans le menu principale, Vous allez selectionner un jeu");

        int selectionJeu;

        while (true) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez selectionner votre jeu ");
            System.out.println("1 - Jeu du plus ou moins ");
            System.out.println("2 - Mastermind ");

            try {
                selectionJeu = sc.nextInt();
                    while (selectionJeu > 2) {
                        if (true) {
                            System.out.println("Vous n'avez pas entrez un choix valide, Réessayez");
                            selectionJeu = sc.nextInt();
                        } else
                            sc.nextLine();
                    }
                break; // pour sortir de la boucle.
            } catch (InputMismatchException ime){
                System.out.println("La valeur saisie n'est pas une valeur numérique");
            }
        }
        displaySelectedAffichage(selectionJeu);

        return selectionJeu;
    }

    /**
     * Permet, selon la selection precedente, d'entrer dans le jeu selectionné
     * @param choixJeu
     * @throws Exception propagée pour le mode developpeur
     */
    public static void displaySelectedAffichage(int choixJeu) throws Exception {

        logger.info("Vous avez été redirigez dans le jeu que vous avez choisi");

        Jeux jeux;

        switch (choixJeu) {
            case 1:
                System.out.println("Vous avez choisi le jeu du plus ou moins ");
                jeux = new PlusOuMoins();
                jeux.selectedMode();

                break;
            case 2:
                System.out.println("Vous avez choisi le jeu du Mastermind ");
                jeux = new Mastermind();
                jeux.selectedMode();
                break;
        }
    }
}
