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

    /**
     * Création de l'instance Logger en utilisant la méthode getLogger()
     */

    private static final Logger logger = LogManager.getLogger(Menu.class);

    /**
     * Cette methode permet l'affichage du menu principal.
     * @return retourne le jeu selectionné
     * @throws IOException exception propagée
     */

    public int displayAffichage(String parametre) throws IOException, JeuxException {

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
        displaySelectedAffichage(selectionJeu, parametre);
        return selectionJeu;
    }

    /**
     * Permet la redirection vers le jeu choisi
     * @param choixJeu
     * @throws IOException
     */
    private static void displaySelectedAffichage(int choixJeu, String parametre) throws IOException, JeuxException {

        logger.info("Vous avez été redirigez dans le jeu que vous avez choisi");

        switch (choixJeu) {
            case 1:
                System.out.println("Vous avez choisi le jeu du plus ou moins ");
                plusOuMoins(parametre);
                break;
            case 2:
                System.out.println("Vous avez choisi le jeu du Mastermind ");
                mastermind(parametre);
                break;
        }
    }

    /**
     * Methode qui permet d'afficher les differents menu du mastermind et de lancer le jeu.
     * @throws IOException exception propagée
     */
    private static void mastermind(String parametre) throws IOException, JeuxException {

        int selectionModeMaster;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Veuillez choisir un mode de jeu ");
            System.out.println("1 - Mode challenger ");
            try {
                selectionModeMaster = sc.nextInt();
                while (selectionModeMaster > 2) {
                    if (true) {
                        System.out.println("Vous n'avez pas entrez un choix valide, Réessayez");
                        selectionModeMaster = sc.nextInt();
                    }
                }
                break;
            } catch (InputMismatchException ime){
                System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez ");
            }
        }

        switch (selectionModeMaster){
            case 1:
                Jeux challengeur = new Challengeur();
                challengeur.master(parametre);
                break;
        }

        /**
         * Affichage du menu de sortie de jeu qui propose plusieurs choix.
         */

        System.out.println("1 - Rejouer au même jeu");
        System.out.println("2 - Lancer un autre jeu ");
        System.out.println("3 - Quitter l'application ");
        int selectionMenuFinMaster = sc.nextInt();

        /**
         * En fonction du choix fait, vous serez redirigez vers celui ci.
         */

        switch (selectionMenuFinMaster){
            case 1 :
                System.out.println("Vous avez choisi de rejouer au meme jeu");
                mastermind(parametre);
                break;
            case 2:
                System.out.println("Vous avez choisi de lancer un autre jeu");
                displaySelectedAffichage(selectionMenuFinMaster, parametre);
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

    private static void plusOuMoins(String parametre) throws IOException, JeuxException {

        int selectionModePlm;
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Veuillez choisir un mode de jeu ");
            System.out.println("1 - Mode challenger ");
            System.out.println("2 - Mode defenseur ");
            System.out.println("3 - Mode duel ");
            try {
                selectionModePlm = sc.nextInt();
                while (selectionModePlm > 3) {
                    if (true) {
                        System.out.println("Vous n'avez pas entrez un choix valide, Réessayez");
                        selectionModePlm = sc.nextInt();
                    }
                }
                break;
            } catch (InputMismatchException ime) {
                System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez ");
            }
        }

        switch (selectionModePlm) {
            case 1:
                Jeux challengeur = new Challengeur();
                challengeur.plusMoins(parametre);
                break;
            case 2:
                Jeux defenseur = new Defenseur();
                defenseur.plusMoins(parametre);

                break;
            case 3:
                Jeux duel = new Duel();
                duel.plusMoins(parametre);
                break;
        }

        System.out.println("1 - Rejouer au même jeu");
        System.out.println("2 - Lancer un autre jeu ");
        System.out.println("3 - Quitter l'application ");

        int selectionMenuFinPlm = sc.nextInt();

        switch (selectionMenuFinPlm){
            case 1 :
                System.out.println("Vous avez choisi de rejouer au meme jeu");
                plusOuMoins(parametre);
                break;
            case 2:
                System.out.println("Vous avez choisi de lancer un autre jeu");
                displaySelectedAffichage(selectionMenuFinPlm, parametre);
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
