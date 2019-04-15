package main.java;

/**
 * classe qui definit le mode challegeur pour le jeu du mastermind et plus ou moins.
 */

public class Challengeur extends Jeu {

    /**
     * Fait appel au fichier config.properties pour utiliser les parametres choisi
     */

    String modeDeveloppeur = ReadPropertyFile.getValue("modeDeveloppeur");

    /**
     * Mode challengeur du Plus ou moins.
     */

    public void plusMoins(String parametre){

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
            } catch (JeuException e) {
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
     * Methode qui permet de relancer le même mode
     * @param parametre
     */

    void rejouer(String parametre){
        plusMoins(parametre);
    }

}