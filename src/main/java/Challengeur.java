package main.java;


import java.util.*;

/**
 * classe qui definit le mode challegeur pour le jeu du mastermind et plus ou moins.
 */

public class Challengeur extends Jeux {

    /**
     * Fait appel au fichier config.properties pour utiliser les parametres choisi
     */

    String modeDeveloppeur = ReadPropertyFile.getValue("modeDeveloppeur");

    /**
     * Mode challengeur du Plus ou moins.
     */

    void plusMoins(String parametre){

        choixCombinaisonOrdinateur();
        combiOrdi = Integer.toString(solutionPossible);
        combinaisonOrdi = combiOrdi.split("");

        choixEssais();

        if (modeDeveloppeur.equals("true")) {
            System.out.println("(Combinaison Secrète : " + combiOrdi + ")");
        }

        if (parametre.equals("true")) {
            System.out.println("(Combinaison Secrète : " + combiOrdi + ")");
        }


        System.out.println("Attaquant à vous de jouer !");


        while (!reponse.equals(nb) && choixEssai <= nbEssaies) {

            reponse ="";

            try {
                choixUtilisateur();
            } catch (JeuxException e) {
                System.out.println(e.toString());
                continue;
            }


            for (int i = 0; i < nbCase; i++) {

                if (Integer.parseInt(propositionUtil[i]) == Integer.parseInt(combinaisonOrdi[i])) {
                    reponse += "=";
                } else if (Integer.parseInt(propositionUtil[i]) > Integer.parseInt(combinaisonOrdi[i])) {
                    reponse += "-";
                } else if (Integer.parseInt(propositionUtil[i]) < Integer.parseInt(combinaisonOrdi[i])) {
                    reponse += "+";
                } else
                    reponse += "=";
            }

            System.out.println("Reponse -> " + reponse);
            choixEssai++;
        }

        if (reponse.equals(nb)) {
            System.out.println("Bravo à l'attaquant!! Vous avez gagnez :)");
        } else
            System.out.println("Vous avez perdu :(");

    }


    /**
     * Mode challenger du Mastermind.
     */

    void master(String parametre){

        choixCombinaisonOrdinateur();
        combiOrdi = Integer.toString(solutionPossible);
        combinaisonOrdi = combiOrdi.split("");

        choixEssais();

        if (modeDeveloppeur.equals("true")) {
            System.out.println("(Combinaison Secrète : " + combiOrdi + ")");
        }

        if (parametre.equals("true")) {
            System.out.println("(Combinaison Secrète : " + combiOrdi + ")");
        }

        System.out.println("Attaquant à vous de jouer !");

        while (wellPlaced != nbCase && choixEssai <= nbEssaies) {

            wellPlaced = 0;
            present = 0;

            try {
                choixUtilisateur();
            } catch (JeuxException e) {
                System.out.println(e.toString());
                continue;
            }

            List<String> propositionAsList = Arrays.asList(combinaisonOrdi);

            for (int i = 0; i < nbCase; i++) {
                if (propositionUtil[i].equals(combinaisonOrdi[i])) {
                    wellPlaced++;

                } else if (propositionAsList.contains(propositionUtil[i])) {
                    present++;
                }
            }

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

            choixEssai++;

        }

        if (wellPlaced == nbCase) {
            System.out.println("Bravo à l'attaquant, vous avez gagnez !!");
        } else
            System.out.println("Oups, vous avez perdu");
    }

}