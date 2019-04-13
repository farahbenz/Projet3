package main.java;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Cette classe contient les variables et methodes communes aux classes des differents modes.
 */
public  abstract class Jeux {

    protected int combinaisonSecrete;
    protected int solutionPossible;
    protected String [] combinaisonOrdi;
    protected String solutionDonné2;
    protected String reponse = "";
    protected String combiOrdi;
    protected String[] solution3;
    protected Integer[] propositionOrdi;
    protected int length2;
    protected int length3;
    protected int nbEssaies;
    protected int wellPlaced;
    protected int present;
    protected int choixEssai = 5;
    protected int nbCase;
    protected String nb;
    String[] propositionUtil;


    /**
     * Cette methode permet de recuperer le parametre choisi pour le nombre d'essai
     */

    protected void choixEssais() {

        String nbEssai = ReadPropertyFile.getValue("nbEssai");

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
     * Cette methode represente la combinaison que l'ordinateur peut entrer en fonction du nombre de case choisi
     * dans les parametres.
     */
    protected int choixCombinaisonOrdinateur() {

        nbCase = parseInt(ReadPropertyFile.getValue("nbCase"));
        int minimum = (int) Math.pow(10, nbCase - 1); // minimum value avec 2 nbcase est 10 (10^1)
        int maximum = (int) Math.pow(10, nbCase) - 1; // maximum value avec 2 nbcase est 99 (10^2 - 1)
        Random random = new Random();
        solutionPossible = minimum + random.nextInt((maximum - minimum) + 1);

        switch (nbCase) {
            case 2:
                nb = "==";
                break;
            case 3:
                nb = "===";
                break;
            case 4:
                nb = "====";
                break;
            case 5:
                nb = "=====";
                break;
            case 6:
                nb = "======";
                break;
            case 7:
                nb = "=======";
                break;
            case 8:
                nb = "========";
                break;
            case 9:
                nb = "=========";
                break;
            case 10:
                nb = "==========";
                break;
        }

        return solutionPossible ;

    }

    /**
     * Cette methode represente la combinaison que l'utisateur peut entrer en fonction du nombre de case choisi
     * dans les parametres.
     */

    public void choixUtilisateur() throws JeuxException  {
        int nbreChiffres = parseInt(ReadPropertyFile.getValue("nbCase"));
        Scanner reader = new Scanner(System.in);
        combinaisonSecrete = reader.nextInt();
        int tailleNbre = Integer.toString(combinaisonSecrete).length();
        if ( nbreChiffres != tailleNbre){
            throw new JeuxException("Une combinaison à  " + nbreChiffres + " chiffres est attendue");
        }

        this.propositionUtil = Integer.toString(combinaisonSecrete).split("");
    }


    /**
     * @param parametre pour le mode developpeur
     **/

    void plusMoins(String parametre) {

    }

    /**
     * @param parametre pour le mode developpeur
     */
    void master(String parametre) {

    }

}