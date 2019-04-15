package main.java;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Cette classe sert de Menu pour la selection d'un jeu
 */
public class Menu {
    private static int selectionModePlm;

    /**
     * Création de l'instance Logger en utilisant la méthode getLogger()
     */

    private static final Logger logger = LogManager.getLogger(Menu.class);

    /**
     * Cette methode permet l'affichage du menu principal.
     * @return retourne le jeu selectionné
     */

    public int displayAffichage(String parametre){

        logger.info("Bienvenue dans le menu principale, Vous allez selectionner un jeu");
        int selectionJeu;
        while (true) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez selectionner votre jeu ");
            System.out.println("1 - Jeu du plus ou moins ");

            try {
                selectionJeu = sc.nextInt();
                while (selectionJeu > 1) {
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
     */
    private void displaySelectedAffichage(int choixJeu, String parametre) {

        logger.info("Vous avez été redirigez dans le jeu que vous avez choisi");

        switch (choixJeu) {
            case 1:
                System.out.println("Vous avez choisi le jeu du plus ou moins ");
                plusOuMoins(parametre);
                break;
        }
    }

    /**
     * Methode qui permet d'afficher les differents menu du plus ou moins et de lancer le jeu.
     */

    private void plusOuMoins(String parametre) {


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
                Jeu challengeur = new Challengeur();
                challengeur.plusMoins(parametre);
                break;
            case 2:
                Jeu defenseur = new Defenseur();
                defenseur.plusMoins(parametre);
                break;
            case 3:
                Jeu duel = new Duel();
                duel.plusMoins(parametre);
                break;
        }


        System.out.println("1 - Relancer la même partie ");
        System.out.println("2 - Rejouer au même jeu ");
        System.out.println("2 - Quitter l'application ");

        int selectionMenuFinPlm = sc.nextInt();

        switch (selectionMenuFinPlm){
            case 1 :
                System.out.println("Vous avez choisi de relancer la partie");
                relancePartie(parametre);
            case 2 :
                System.out.println("Vous avez choisi de rejouer");
                plusOuMoins(parametre);
                break;
            case 3:
                exit();
                break;
        }
    }

    private void relancePartie(String parametre) {

        switch (selectionModePlm) {
            case 1:
                Jeu challengeur = new Challengeur();
                challengeur.rejouer(parametre);
                break;
            case 2:
                Jeu defenseur = new Defenseur();
                defenseur.rejouer(parametre);
                break;
            case 3:
                Jeu duel = new Duel();
                duel.rejouer(parametre);
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
