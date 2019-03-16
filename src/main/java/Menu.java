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

        logger.info("Bienvenue dans le menu principale, Vous allez selectionner un jeu");

        int selectionJeu;

        Scanner sc = new Scanner(System.in);

        while (true){

            System.out.println("Veuillez selectionner votre jeu ");
            System.out.println("1 - Jeu du plus ou moins ");
            System.out.println("2 - Mastermind ");
            try {
                selectionJeu = sc.nextInt();
                break;
            } catch (InputMismatchException ime){
                System.out.println("La valeur saisie n'est pas une valeur numérique");
            }
            finally {
                sc.nextLine();
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


        switch (choixJeu) {
            case 1:
                System.out.println("Vous avez choisi le jeu du plus ou moins ");
                PlusOuMoins plm = new PlusOuMoins();
                plm.selectedMode();

                break;
            case 2:
                System.out.println("Vous avez choisi le jeu du Mastermind ");
                Mastermind mst = new Mastermind();
                mst.selectedMode();
                break;
        }

    }

}
