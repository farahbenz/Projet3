package main.java;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * classe qui definit le mode challegeur pour le jeu du mastermind et plus ou moins.
 */

public class Challengeur extends Jeux {
    /**
     * Mode challenger du Mastermind.
     * @throws IOException, exception propagée
     */

    public void master() throws IOException {

        choixCombinaisonOrdinateur();
        choixEssais();

        solutionDonné = Integer.toString(solutionPossible);
        solution = solutionDonné.split("");
        length = solution.length;

        System.out.println("A vous de jouer !");

        while (wellPlaced != vtm && choixEssai <= nbEssaies ) {
            while (true) {

                Scanner reader = new Scanner(System.in);
                choixCombinaisonUtilisateur();

                try {
                    combinaisonSecrete = reader.nextInt();
                    break;
                } catch (InputMismatchException ime) {
                    System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez");
                }
            }
            proposition = Integer.toString(combinaisonSecrete).split("");

            resultat();

            if (present == 0 && wellPlaced == 1) {
                System.out.println("Proposition : " + combinaisonSecrete + " -> Reponse : " + wellPlaced + "  bien placé");
            } else if (present == 0 && wellPlaced <= 3) {
                System.out.println("Proposition : " + combinaisonSecrete + " -> Reponse : " + wellPlaced + " bien placés");
            } else if (present == 1 && wellPlaced <= 3) {
                System.out.println("Proposition : " + combinaisonSecrete + " -> Reponse : " + present + " présent," + wellPlaced + " bien placés");
            } else if (present <= 3 && wellPlaced <= 3) {
                System.out.println("Proposition : " + combinaisonSecrete + " -> Reponse : " + present + " présents, " + wellPlaced + " bien placés");
            } else if (present > 1 && wellPlaced == 0) {
                System.out.println("Proposition :" + combinaisonSecrete + " -> Reponse : " + present + " présents");
            }

            choixEssai ++;

        }
        if (wellPlaced == vtm && choixEssai <=nbEssaies ) {
            System.out.println("Bravo, vous avez gagnez !!");
        } else
            System.out.println("Vous avez perdu");
    }

    /**
     * Mode challengeur du Plus ou moins.
     * @throws IOException, exception propagée
     */

    public void plusMoins() throws IOException {

        choixCombinaisonOrdinateur();

        choixEssais();
        solutionDonné = Integer.toString(solutionPossible);
        solution = solutionDonné.split("");
        length = solution.length;
        System.out.println("A vous de jouer !");

        while ( !reponse.equals(vt) && choixEssai <= nbEssaies ) {

            reponse = "";

            while (true) {
                Scanner reader = new Scanner(System.in);
                choixCombinaisonUtilisateur();
                try {
                    combinaisonSecrete = reader.nextInt();
                    String combinaisonDonné = Integer.toString(combinaisonSecrete);
                    String[] combinaison = combinaisonDonné.split("");
                    length2 = combinaison.length;

                    while (length != length2) {

                        if (true) {
                            System.out.println("Vous n'avez pas entrez un choix valide, Réessayez");
                            combinaisonSecrete = reader.nextInt();

                        }
                        break;
                    }

                    break;
                } catch (InputMismatchException ime) {
                    System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez");
                }

            }

            proposition = Integer.toString(combinaisonSecrete).split("");
            resultat();
            System.out.println("Proposition : "+ combinaisonSecrete + " -> Reponse : " + reponse);
            choixEssai ++;
        }

        if ( reponse.equals(vt)){
            System.out.println("Bravo !! Vous avez gagnez :)");
        } else if (!reponse.equals(vt)){
            System.out.println("Vous avez perdu :(");
        }
    }
}
