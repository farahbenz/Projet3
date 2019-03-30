package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Defenseur extends Jeux{
    /**
     * Mode defenseur du Plus ou moins.
     * @throws IOException, exception propagée
     */
    public void plusMoins() throws IOException {

        Scanner reader = new Scanner(System.in);
        Random rand = new Random();

        while (true) {
            System.out.println("Veuillez entrez la combinaison secrete");
            choixCombinaisonUtilisateur();
            try {
                combinaisonSecrete = reader.nextInt();
                solutionDonné = Integer.toString(combinaisonSecrete);
                solution = solutionDonné.split("");
                length = solution.length;

                while (length != nbCase) {
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

        Integer[] solution1 = new Integer[nbCase];

        for (int i = 0; i < length; i++) {
            solution1[i] = Integer.parseInt(solution[i]);
        }

        Boolean pasEncoreTrouvé = true;

        choixEssais();

        Integer[] choixOrdi = new Integer[nbCase];
        ArrayList<Integer[]> ordi_choix = new ArrayList<Integer[]>();


        while (pasEncoreTrouvé && !reponse.equals(nbCase) && choixEssai <= nbEssaies) {


            pasEncoreTrouvé = false;

            for (int i = 0; i < solution1.length; i++) {
                if (choixOrdi[i] != solution1[i]) {

                    pasEncoreTrouvé = true;

                    if (choixOrdi[i] == null) {
                        choixOrdi[i] = 1;
                    } else if (choixOrdi[i] > solution1[i]) {
                        choixOrdi[i] -= rand.nextInt(choixOrdi[i]);
                        reponse += "-";
                    } else if (choixOrdi[i] < solution1[i]) {
                        choixOrdi[i] += rand.nextInt(solution1[i]);
                        reponse += "+";
                    }
                } else {
                    reponse += "=";
                }
            }

            System.out.println(" proposition : " + choixOrdi + " reponse : "+ reponse );
            reponse = "";
            ordi_choix.add(choixOrdi);
            choixEssai++;
        }

        if ( reponse.equals("====")) {
            System.out.println("Bravo !! Vous avez gagnez :)");
        } else
            System.out.println("Vous avez perdu :(");
    }


    /**
     * Mode defenseur du Mastermind.
     * @throws IOException, exception propagée
     */
    public void master() throws IOException {


        while (true) {
            System.out.println("Entrez votre combinaison secrete");
            Scanner reader = new Scanner(System.in);
            choixCombinaisonUtilisateur();
            try {
                combinaisonSecrete = reader.nextInt();
                break;
            } catch (InputMismatchException ime){
                System.out.println("La valeur saisie n'est pas une valeur numérique");
            }
        }

        choixEssais();

        solutionDonné =Integer.toString(combinaisonSecrete);

        solution =solutionDonné.split("");
        length =solution.length;

        System.out.println("A vous de jouer !");

        while (wellPlaced != vtm && choixEssai <= nbEssaies) {

            choixCombinaisonOrdinateur();
            proposition = Integer.toString(solutionPossible).split("");

            resultat();

            if (present == 0 && wellPlaced == 1) {
                System.out.println("Proposition : " +  solutionPossible + " -> Reponse : " + wellPlaced + "  bien placé");
            } else if (present == 0 && wellPlaced <= 3) {
                System.out.println("Proposition : " + solutionPossible  + " ->  Reponse : " + wellPlaced + " bien placés");
            } else if (present == 1 && wellPlaced <= 3) {
                System.out.println("Proposition : " + solutionPossible + " -> Reponse : " + present + " présent," + wellPlaced + " bien placés");
            } else if (present <= 3 && wellPlaced <= 3) {
                System.out.println("Proposition : " + solutionPossible  + " -> Reponse : " + present + " présents, " + wellPlaced + " bien placés");
            } else if (present > 1 && wellPlaced == 0) {
                System.out.println("Proposition :" + solutionPossible + " -> Reponse : " + present + " présents");
            }

            choixEssai++;
        }

        if (wellPlaced == vtm && choixEssai <= nbEssaies) {
            System.out.println("Bravo, vous avez gagnez !!");
        } else
            System.out.println("Vous avez perdu");
    }
}
