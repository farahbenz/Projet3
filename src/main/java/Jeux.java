package main.java;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Cette classe contient les variables et methodes communes aux classes des differents modes.
 */
public  abstract class Jeux {

    protected static int combinaisonSecrete;
    protected static int solutionPossible;
    protected static int solutionPossible2;
    protected static String solutionDonné;
    protected static String solutionDonné2;
    protected static String solutionDonné3;
    protected static String[] solution;
    protected static String[] solution2;
    protected static String[] solution3;
    protected static int length;
    protected static int length2;
    protected static int length3;
    protected static String reponse = "";
    protected static String reponse2 = "";
    protected static String[] proposition;
    protected static String[] proposition2;
    protected int nbEssaies;
    protected int wellPlaced;
    protected int present;
    protected String vt;
    protected int vtm;
    protected int choixEssai = 1;
    protected int nbCase;

    /**
     * Cette methode permet la comparaison entre la solution donnée par le defenseur
     * et la proposition faites par l'attaquant.
     */

    protected void resultat (){

        for (int i = 0; i < length; i++) {

            if (Integer.parseInt(solution[i]) == Integer.parseInt(proposition[i])) {
                reponse += "=";
            } else if (Integer.parseInt(proposition[i]) > Integer.parseInt(solution[i])) {
                reponse += "-";
            } else if (Integer.parseInt(proposition[i]) < Integer.parseInt(solution[i])) {
                reponse += "+";
            } else
                reponse += "=";
        }
    }

    /**
     * Cette methode permet de recuperer le parametre choisi pour le nombre d'essai
     * @throws IOException, exception générée par les parametres.
     */

    protected void choixEssais() throws IOException {
        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
        String nbEssai = mapParameteres.get("nbEssai");
        switch (nbEssai){
            case "1":
                nbEssaies = 1;
            case "2":
                nbEssaies = 2;
            case "3":
                nbEssaies = 3;
            case "4":
                nbEssaies = 4;
            case "5":
                nbEssaies = 5;
            case "6":
                nbEssaies = 6;
        }
    }

    /**
     * Cette methode represente la combinaison que l'utilisateur peut entrer en fonction du nombre de case choisi
     * dans les parametres.
     * @throws IOException générée par les parametres
     */
    protected void choixCombinaisonUtilisateur() throws IOException {

        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
        String nbCasePlm = mapParameteres.get("nbCasePlm");
        switch (nbCasePlm) {
            case "2":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(10, 99);
                vt = "==";
                nbCase = 2;
                break;
            case "3":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(100, 999);
                vt = "===";
                nbCase = 3;
                break;
            case "4":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(1000, 9999);
                vt = "====";
                vtm = 4;
                nbCase =4;
                break;
            case "5":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(10000, 99999);
                vt = "=====";
                vtm = 5;
                nbCase = 5;
                break;
            case "6":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(100000, 999999);
                vt = "======";
                vtm = 6;
                nbCase = 6;
                break;
            case "7":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(1000000, 9999999);
                vt = "=======";
                vtm = 7;
                nbCase = 7;
                break;
            case "8":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(10000000, 99999999);
                vt = "========";
                vtm = 8;
                nbCase = 8;
                break;
            case "9":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(100000000, 999999999);
                vt = "=========";
                vtm = 9;
                nbCase = 9;
                break;
            case "10":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(1000000000, 999999999);
                combinaisonSecrete += 1000000000;
                vt = "==========";
                vtm = 10;
                nbCase = 10;
                break;
        }
    }

    /**
     * Cette methode represente la combinaison que l'ordinateur peut entrer en fonction du nombre de case choisi
     * dans les parametres.
     * @throws IOException générée par les parametres
     */
    protected void choixCombinaisonOrdinateur() throws IOException {
        Random rand   = new Random();

        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
        String nbCasePlm = mapParameteres.get("nbCasePlm");
        switch (nbCasePlm) {
            case "2":
                solutionPossible = (int) (Math.random() * 99) + 10;
                vt = "==";
                break;
            case "3":
                solutionPossible = (int) (Math.random() * 999) + 100;
                vt = "===";
                break;
            case "4":
                solutionPossible = rand.nextInt(9999);
                vt = "====";
                nbCase = 4;
                break;
            case "5":
                solutionPossible = (int) (Math.random() * 99999) + 10000;
                vt = "=====";
                break;
            case "6":
                solutionPossible = (int) (Math.random() * 999999) + 100000;
                vt = "======";
                break;
            case "7":
                solutionPossible = (int) (Math.random() * 9999999) + 1000000;
                vt = "=======";
                break;
            case "8":
                solutionPossible = (int) (Math.random() * 99999999) + 10000000;
                vt = "========";
                break;
            case "9":
                solutionPossible = (int) (Math.random() * 999999999) + 100000000;
                vt = "=========";
                break;
            case "10":
                solutionPossible = (int) (Math.random() * 999999999) + 1000000000;
                vt = "==========";
                break;
        }

    }

}