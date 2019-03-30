package main.java;

public class Main {


    /**
     * Instanciation de la classe Menu pour faire appel à la methode qui affichera le menu principale
     */
    public static void main(String[] args) throws Exception {

        String parametre = args[0];
        System.out.println(parametre);

        Menu menu = new Menu();
        menu.displayAffichage();
    }
}

