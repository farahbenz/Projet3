package main.java;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Cette classe contient les variables et methodes communes aux classes des differents modes.
 */
public  abstract class Jeu {

    protected int combinaisonSecrete;
    protected int solutionPossible;
    protected int length2;
    protected int length3;
    protected int nbEssaies;
    protected int choixEssai = 1;
    protected int nbCase;
    protected String [] combinaisonOrdi;
    protected String[] propositionUtil;
    protected String solutionDonne;
    protected String reponse = "";
    protected String nb;
    protected String combiOrdi;
    protected String[] solution3;
    protected Integer[] propositionOrdi;


    /**
     * Cette methode permet de recuperer le parametre choisi pour le nombre d'essai
     */

    protected void choixEssais() {
        nbEssaies = parseInt(ReadPropertyFile.getValue("nbEssai"));
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

        for( int i = 0; i <  nbCase; i ++){
            nb = "=";
            nb += "=";
        }

        return solutionPossible ;
    }

    /**
     * Cette methode represente la combinaison que l'utisateur peut entrer en fonction du nombre de case choisi
     * dans les parametres.
     */

    public void choixUtilisateur() throws JeuException {
        int nbreChiffres = parseInt(ReadPropertyFile.getValue("nbCase"));
        Scanner reader = new Scanner(System.in);
        combinaisonSecrete = reader.nextInt();
        int tailleNbre = Integer.toString(combinaisonSecrete).length();
        if ( nbreChiffres != tailleNbre){
            throw new JeuException("Une combinaison à  " + nbreChiffres + " chiffres est attendue");
        }

        this.propositionUtil = Integer.toString(combinaisonSecrete).split("");
    }


    /**
     * Methode plusMoins abstraite.
     * @param parametre pour le mode developpeur
     **/

    abstract void plusMoins(String parametre);


    /**
     * Methode qui permet de relancer le meme mode.
     * @param parametre
     */

    abstract void rejouer(String parametre);


}

