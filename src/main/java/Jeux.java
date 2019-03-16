package main.java;


import java.io.IOException;
import java.util.Scanner;

public abstract class Jeux {

    protected int combinaisonSecrete;
    protected static int solutionPossible;
    protected static int solutionPossible2;
    protected static String solutionDonné;
    protected static String solutionDonné2;
    protected static String[] solution;
    protected static String[] solution2;
    protected static int length;
    protected static int length2;
    protected static String reponse = "";
    protected static String reponse2 = "";
    protected static String[] proposition;
    protected static String[] proposition2;
    protected int nbEssaies;
    protected String modeDeveloppeur;
    protected int wellPlaced;
    protected int present;
    protected String vt;
    protected int vtm;
    protected int choixEssai = 2;

    public Jeux() {

    }


    public abstract void challenger() throws IOException;

    public abstract void duel() throws Exception;

    public abstract void defenseur () throws IOException;

    /**
     * Methode pour quitter l'application
     */

    protected void exit() {
        System.out.println(" Au revoir ");
        System.exit(0);
    }

    }
