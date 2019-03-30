package main.java;

import java.io.IOException;
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
     * Cette methode permet l'affichage du menu principal.
     * @return retourne le jeu selectionné
     * @throws IOException exception propagée
     */
    public int displayAffichage() throws IOException {

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
                    }
                }
                break;
            } catch (InputMismatchException ime){
                System.out.println("La valeur saisie n'est pas une valeur numérique");
            }
        }
        displaySelectedAffichage(selectionJeu);

        return selectionJeu;
    }

    /**
     * Permet la redirection vers le jeu choisi
     * @param choixJeu
     * @throws IOException
     */
    private static void displaySelectedAffichage(int choixJeu) throws IOException {

        logger.info("Vous avez été redirigez dans le jeu que vous avez choisi");

        switch (choixJeu) {
            case 1:
                System.out.println("Vous avez choisi le jeu du plus ou moins ");
                plusOuMoins();


                break;
            case 2:
                System.out.println("Vous avez choisi le jeu du Mastermind ");
                mastermind();
                break;
        }
    }

    /**
     * Methode qui permet d'afficher les differents menu du mastermind et de lancer le jeu.
     * @throws IOException exception propagée
     */
    private static void mastermind() throws IOException {

        int selectionMode;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Veuillez choisir un mode de jeu ");
            System.out.println("1 - Mode challenger ");
            System.out.println("2 - Mode defenseur ");
            System.out.println("3 - Mode duel ");
            try {
                selectionMode = sc.nextInt();
                while (selectionMode > 3) {
                    if (true) {
                        System.out.println("Vous n'avez pas entrez un choix valide, Réessayez");
                        selectionMode = sc.nextInt();
                    }
                }
                break;
            } catch (InputMismatchException ime){
                System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez ");
            }
        }

        switch (selectionMode){
            case 1:
                Challengeur challengeur = new Challengeur();
                challengeur.master();
                break;
            case 2:
                Defenseur defenseur = new Defenseur();
                defenseur.master();
                break;
            case 3:
                Duel duel = new Duel();
                duel.master();
                break;
        }
        int selection;

        System.out.println("1 - Rejouer au même jeu");
        System.out.println("2 - Lancer un autre jeu ");
        System.out.println("3 - Quitter l'application ");

        selection = sc.nextInt();

        switch (selection){
            case 1 :
                System.out.println("Vous avez choisi de rejouer au meme jeu");
                mastermind();
                break;
            case 2:
                System.out.println("Vous avez choisi de lancer un autre jeu");
                displaySelectedAffichage(selection);
                break;
            case 3:
                exit();
                break;

        }

    }
    /**
     * Methode qui permet d'afficher les differents menu du plus ou moins et de lancer le jeu.
     * @throws IOException exception propagée
     */

    private static void plusOuMoins() throws IOException {

        int selectionMode;
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Veuillez choisir un mode de jeu ");
            System.out.println("1 - Mode challenger ");
            System.out.println("2 - Mode defenseur ");
            System.out.println("3 - Mode duel ");
            try {
                selectionMode = sc.nextInt();
                while (selectionMode > 3) {
                    if (true) {
                        System.out.println("Vous n'avez pas entrez un choix valide, Réessayez");
                        selectionMode = sc.nextInt();
                    }
                }
                break;
            } catch (InputMismatchException ime) {
                System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez ");
            }
        }

        switch (selectionMode) {
            case 1:
                Challengeur challengeur = new Challengeur();
                challengeur.plusMoins();
                break;
            case 2:
                Defenseur defenseur = new Defenseur();
                defenseur.plusMoins();

                break;
            case 3:
                Duel duel = new Duel();
                duel.plusMoins();
                break;
        }

        int selection;

        System.out.println("1 - Rejouer au même jeu");
        System.out.println("2 - Lancer un autre jeu ");
        System.out.println("3 - Quitter l'application ");

        selection = sc.nextInt();

        switch (selection){
            case 1 :
                System.out.println("Vous avez choisi de rejouer au meme jeu");
                plusOuMoins();
                break;
            case 2:
                System.out.println("Vous avez choisi de lancer un autre jeu");
                displaySelectedAffichage(selection);
                break;
            case 3:
                exit();
                break;

        }


    }

    /**
     * Methode exit qui permet de sortir de l'application
     */

    private static void exit() {
        System.out.println(" Au revoir ");
        System.exit(0);
    }

}
