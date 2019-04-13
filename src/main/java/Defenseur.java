package main.java;

import java.util.*;

/**
 * Classe qui definit le mode defenseur pour le jeu du plus ou moins.
 */

public class Defenseur extends Jeu {

    Scanner sc = new Scanner(System.in);

    /**
     * Mode defenseur du Plus ou moins.
     */
    public void plusMoins(String parametre) {

        while (true) {

            System.out.println("Defenseur veuillez entrez votre combinaison secrete:");


                try {
                    choixUtilisateur();
                } catch (JeuException e) {
                    System.out.println(e.toString());
                    continue;
                }
                break;
            }


        choixCombinaisonOrdinateur();
        combiOrdi = Integer.toString(solutionPossible);
        combinaisonOrdi = combiOrdi.split("");


        System.out.println("Proposition ordinateur -> " + combiOrdi);


        propositionOrdi = new Integer[nbCase];


        for (int i = 0; i < combinaisonOrdi.length; i++) {
            propositionOrdi[i] = Integer.parseInt(combinaisonOrdi[i]);
        }

        choixEssais();
        boolean gagner = false;
        while (!gagner && choixEssai <= nbEssaies) {

            reponse = "";

            System.out.println("Reponse -> ");
            reponse = sc.nextLine();
            String[] reponseDefenseur = reponse.split("");
            length2 = reponseDefenseur.length;

            while (true) {
                try {
                    if (length2 != nbCase) {
                        throw new JeuException();
                    }
                    break;
                } catch (JeuException e) {
                    System.out.println("Veuillez réessayer, vous n'avez pas entrez un choix valide");
                    sc.nextLine();
                } finally {
                    break;
                }
            }

            if (!reponse.equals(nb)) {
                for (int i = 0; i < nbCase; i++) {

                    if (reponseDefenseur[i].equals("+")) {
                        propositionOrdi[i]++;

                    } else if (reponseDefenseur[i].equals("-")) {
                        propositionOrdi[i]--;

                    } else if (reponseDefenseur[i].equals(("=")))
                        propositionOrdi[i] = propositionOrdi[i];
                }

                /**
                 * on introduit la nouvelle proposition dans un nouveau tableau pour ensuite l'afficher
                 */

                String result = "";
                for (int i = 0; i < propositionOrdi.length; i++) {
                    result += propositionOrdi[i];
                }
                int resultat = Integer.parseInt(result);
                System.out.println(resultat);

                choixEssai++;

            } else
                gagner = true;

        }

        if (reponse.equals(nb)) {
            System.out.println("Bravo à l'attaquant, vous avez gagnez !!");
        } else
            System.out.println("Oups, vous avez perdu");
        }



}
