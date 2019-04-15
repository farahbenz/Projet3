package main.java;

import java.util.*;


/**
 * classe qui definit le mode challegeur pour le jeu du plus ou moins.
 */

public class Duel extends Jeu {

    /**
     * Mode duel du Plus ou moins.
     */

    public void plusMoins(String parametre) {

        String modeDeveloppeur = ReadPropertyFile.getValue("modeDeveloppeur");
        Scanner sc = new Scanner(System.in);

        choixCombinaisonOrdinateur();
        combiOrdi = Integer.toString(solutionPossible);
        combinaisonOrdi = combiOrdi.split("");


        if (modeDeveloppeur.equals("true")) {
            System.out.println("(Combinaison Secrète : " + combiOrdi + ")");
        }

        if (parametre.equals("true")) {
            System.out.println("(Combinaison Secrète : " + combiOrdi + ")");
        }

        choixEssais();

        while (true) {
            System.out.println("Utilisateur veuillez entrez la combinaison secrete");


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
        String[] combOrdi = combiOrdi.split("");

        System.out.println("Ordinateur à vous de jouer -> " + combiOrdi); //string

        String reponse;
        System.out.println("Reponse -> ");
        reponse = sc.nextLine();
        String[] reponseDefenseur = reponse.split("");


        /** ********************************************************************************************** */

        do {

            System.out.println("Attaquant à vous de jouer !");

            reponse = "";

            while (true) {
                try {

                    try {
                        choixUtilisateur();
                        solutionDonné2 = Integer.toString(combinaisonSecrete);
                        solution3 = solutionDonné2.split("");
                        length3 = solution3.length;
                    } catch (JeuException e) {
                        System.out.println(e.toString());
                        continue;
                    }
                    break;
                } catch (InputMismatchException ime) {
                    System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez");

                }
            }

            for (int i = 0; i < combinaisonOrdi.length; i++) {

                if (Integer.parseInt(solution3[i]) == Integer.parseInt(combinaisonOrdi[i])) {
                    reponse += "=";
                } else if (Integer.parseInt(solution3[i]) > Integer.parseInt(combinaisonOrdi[i])) {
                    reponse += "-";
                } else if (Integer.parseInt(solution3[i]) < Integer.parseInt(combinaisonOrdi[i])) {
                    reponse += "+";
                } else
                    reponse += "=";
            }

            System.out.println(" -> Reponse : " + reponse);

            if (!reponse.equals(nb)) {

                propositionOrdi = new Integer[nbCase];


                for (int i = 0; i < combOrdi.length; i++) {
                    propositionOrdi[i] = Integer.parseInt(combOrdi[i]);
                }


                for (int i = 0; i < nbCase; i++) {

                    if (reponseDefenseur[i].equals("+")) {
                        propositionOrdi[i]++;

                    } else if (reponseDefenseur[i].equals("-")) {
                        propositionOrdi[i]--;

                    } else if (reponseDefenseur[i].equals("="))
                        propositionOrdi[i] = propositionOrdi[i];
                }


                String result = "";
                for (int i = 0; i < propositionOrdi.length; i++) {
                    result += propositionOrdi[i];
                }
                int resultat = Integer.parseInt(result);
                System.out.println("Proposition ->" + resultat);

                System.out.println("Reponse -> ");
                reponse = sc.nextLine();
                reponseDefenseur= reponse.split("");


                choixEssai++;

            } else
                System.out.println("Bravo !! Vous avez gagnez :)");
            break;

        } while (!reponseDefenseur.equals(nb) && choixEssai <= nbEssaies);

        if (reponseDefenseur.equals(nb)) {
            System.out.println("Bravo Oridnateur !! Vous avez gagnez :)");
        } else
            System.out.println("Oups !! Vous avez perdu :(");
        }

    /**
     * Methode qui permet de relancer le même mode
     * @param parametre
     */
    void rejouer(String parametre) {
        plusMoins(parametre);
    }

}


