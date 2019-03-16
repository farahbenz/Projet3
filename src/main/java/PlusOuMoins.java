package main.java;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe du Jeu plus ou moins avec ses differentes méthodes.
 */

public class PlusOuMoins extends Jeux {

    private static final Logger logger = LogManager.getLogger(Mastermind.class);


    public PlusOuMoins() {
        super();
    }

    /**
     * Affiche les differents modes possible pour jouer au jeu
     * Selection d'un des modes
     * @throws Exception
     */
    public void selectedMode() throws Exception {
        logger.info("Vous êtes dans le menu pour le choix des differents mode");

        int selectionMode;

        Scanner sc = new Scanner(System.in);

        while(true) {

        System.out.println("Veuillez choisir un mode de jeu ");
        System.out.println("1 - Mode challenger ");
        System.out.println("2 - Mode defenseur ");
        System.out.println("3 - Mode duel ");
        try {
        selectionMode = sc.nextInt();
        break;
        } catch (InputMismatchException ime) {
        System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez ");
        }
        finally {
        sc.nextLine();
        }
        }
        displayMode(selectionMode);
        newMenu();

    }

    /**
     * Selon le choix precedent, cette methode renvoi au bon mode pour jouer.
     * @param Mode
     * @throws IOException exception du mode developpeur
     */

    public void displayMode(int Mode) throws Exception {


        switch (Mode) {
            case 1:
                challenger();
                break;
            case 2:
                defenseur();
                break;
            case 3:
                duel();
                break;
        }

    }


    /**
     * * 1er methode : L'ordinateur est le defenseur, il choisi une combinaison secrete.
     * * L'attaquant doit deviner la combinaison secrete choisi par le defenseur.
     * @throws IOException cette exception est en lien avec le mode Developpeur
     */
    @Override
    public void challenger() throws IOException {
        logger.info("Vous avez choisi le mode challenger");

        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
        String nbCasePlm = mapParameteres.get("nbCasePlm");

        Random rand = new Random();
        switch (nbCasePlm) {
            case "2":
                System.out.println("-- Vous devez selectionner une combinaison à 2 chiffres --");
                solutionPossible = rand.nextInt(9);
                solutionPossible += 10;
                vt = "==" ;
                break;
            case "3":
                System.out.println("-- Vous devez selectionner une combinaison à 3 chiffres --");
                solutionPossible = rand.nextInt(89);
                solutionPossible += 100;
                vt = "===";
                break;
            case "4":
                System.out.println("-- Vous devez selectionner une combinaison à 4 chiffres --");
                solutionPossible = rand.nextInt(899);
                solutionPossible += 1000;
                vt = "====";
                break;
            case "5":
                System.out.println("-- Vous devez selectionner une combinaison à 5 chiffres --");
                solutionPossible = rand.nextInt(8999);
                solutionPossible += 10000;
                vt = "=====";
                break;
            case "6":
                System.out.println("-- Vous devez selectionner une combinaison à 6 chiffres --");
                solutionPossible = rand.nextInt(89999);
                solutionPossible += 100000;
                vt = "======";
                break;
            case "7":
                System.out.println("-- Vous devez selectionner une combinaison à 7 chiffres --");
                solutionPossible = rand.nextInt(899999);
                solutionPossible += 1000000;
                vt = "=======";
                break;
            case "8":
                System.out.println("-- Vous devez selectionner une combinaison à 8 chiffres --");
                solutionPossible = rand.nextInt(8999999);
                solutionPossible += 10000000;
                vt = "========";
                break;
            case "9":
                System.out.println("-- Vous devez selectionner une combinaison à 9 chiffres --");
                solutionPossible = rand.nextInt(89999999);
                solutionPossible += 100000000;
                vt = "=========";
                break;
            case "10":
                System.out.println("-- Vous devez selectionner une combinaison à 10 chiffres --");
                solutionPossible = rand.nextInt(899999999);
                solutionPossible += 1000000000;
                vt = "==========";
                break;

        }
        String nbEssai = mapParameteres.get("nbEssai");
        int choixEssai = 2;

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

        solutionDonné = Integer.toString(solutionPossible);

        String modeDeveloppeur = mapParameteres.get("modeDeveloppeur");

        if(modeDeveloppeur.equals("true")) {
            System.out.println("Combinaison choisi par l'ordinateur: "+ solutionDonné);
        }


        solution = solutionDonné.split("");
        length = solution.length;
        reponse = "";
        String[] proposition;

        System.out.println("A vous de jouer !");

       int n;

        while ( !reponse.equals(vt) && choixEssai <= nbEssaies ) {

            reponse = "";

            while (true) {
                Scanner reader = new Scanner(System.in);
                try {
                    n = reader.nextInt();
                    break;
                } catch (InputMismatchException ime) {
                    System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez");
                } finally {
                    reader.nextLine();
                }
            }

            proposition = Integer.toString(n).split("");

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

            System.out.println("Proposition : "+ n + " -> Reponse : " + reponse);
            choixEssai ++;

        }

        if ( reponse.equals(vt)){
            System.out.println("Bravo !! Vous avez gagnez :)");
        } else if (!reponse.equals(vt)){
            System.out.println("Vous avez perdu :(");
        }



    }


    /**
     * 1er methode : Le defenseur choisi une combinaison secrete.
     * L'attaquant est l'ordinateur et doit deviner la combinaison secrete choisi par le defenseur.
     */
    @Override
    public void defenseur() throws IOException {
        logger.info("Vous avez choisi le mode defenseur");
        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
        String nbCasePlm = mapParameteres.get("nbCasePlm");
        while (true) {

            System.out.println("Entrez votre combinaison secrete");
            Scanner reader = new Scanner(System.in);


            switch (nbCasePlm) {
                case "2":
                    System.out.println("-- Vous devez selectionner une combinaison à 2 chiffres --");
                    combinaisonSecrete = ThreadLocalRandom.current().nextInt(9);
                    combinaisonSecrete += 10;
                    vt = "==" ;
                    break;
                case "3":
                    System.out.println("-- Vous devez selectionner une combinaison à 3 chiffres --");
                    combinaisonSecrete = ThreadLocalRandom.current().nextInt(89);
                    combinaisonSecrete += 100;
                    vt = "===";
                    break;
                case "4":
                    System.out.println("-- Vous devez selectionner une combinaison à 4 chiffres --");
                    combinaisonSecrete = ThreadLocalRandom.current().nextInt(899);
                    combinaisonSecrete += 1000;
                    vt = "====";
                    break;
                case "5":
                    System.out.println("-- Vous devez selectionner une combinaison à 5 chiffres --");
                    combinaisonSecrete = ThreadLocalRandom.current().nextInt(8999);
                    combinaisonSecrete += 10000;
                    vt = "=====";
                    break;
                case "6":
                    System.out.println("-- Vous devez selectionner une combinaison à 6 chiffres --");
                    combinaisonSecrete = ThreadLocalRandom.current().nextInt(89999);
                    combinaisonSecrete += 100000;
                    vt = "======";
                    break;
                case "7":
                    System.out.println("-- Vous devez selectionner une combinaison à 7 chiffres --");
                    combinaisonSecrete = ThreadLocalRandom.current().nextInt(899999);
                    combinaisonSecrete += 1000000;
                    vt = "=======";
                    break;
                case "8":
                    System.out.println("-- Vous devez selectionner une combinaison à 8 chiffres --");
                    combinaisonSecrete = ThreadLocalRandom.current().nextInt(8999999);
                    combinaisonSecrete += 10000000;
                    vt = "========";
                    break;
                case "9":
                    System.out.println("-- Vous devez selectionner une combinaison à 9 chiffres --");
                    combinaisonSecrete = ThreadLocalRandom.current().nextInt(89999999);
                    combinaisonSecrete += 100000000;
                    vt = "=========";
                    break;
                case "10":
                    System.out.println("-- Vous devez selectionner une combinaison à 10 chiffres --");
                    combinaisonSecrete = ThreadLocalRandom.current().nextInt(899999999);
                    combinaisonSecrete += 1000000000;
                    vt = "==========";
                    break;
            }

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

            try {
            combinaisonSecrete = reader.nextInt();
            break;
        } catch (InputMismatchException ime){
            System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez ");
        }
        finally {
             reader.nextLine();
        }
        }

    solutionDonné = Integer.toString(combinaisonSecrete);

    solution = solutionDonné.split("");
    length = solution.length;
    reponse = "";
    String[] proposition;
    System.out.println("A vous de jouer !");

        while (!reponse.equals(vt) && choixEssai <= nbEssaies) {
        reponse = "";
        int n =0;
            Random rand = new Random();

            switch (nbCasePlm) {
                case "2":
                   n = rand.nextInt(9);
                   n += 10;
                    vt = "==" ;
                    break;
                case "3":
                    n = rand.nextInt(89);
                    n += 100;
                    vt = "===";
                    break;
                case "4":
                    n = rand.nextInt(899);
                    n += 1000;
                    vt = "====";
                    break;
                case "5":
                    n = rand.nextInt(8999);
                    n += 10000;
                    vt = "=====";
                    break;
                case "6":
                    n = rand.nextInt(89999);
                    n += 100000;
                    vt = "======";
                    break;
                case "7":
                    n = rand.nextInt(899999);
                    n += 1000000;
                    vt = "=======";
                    break;
                case "8":
                    n = rand.nextInt(8999999);
                    n += 10000000;
                    vt = "========";
                    break;
                case "9":
                    n = rand.nextInt(89999999);
                    n += 100000000;
                    vt = "=========";
                    break;
                case "10":
                    n = rand.nextInt(899999999);
                    n += 1000000000;
                    vt = "==========";
                    break;

            }

        System.out.println(n);

        proposition = Integer.toString(n).split("");


        for (int i = 0; i < length; i++) {

            if (Integer.parseInt(solution[i]) == Integer.parseInt(proposition[i])) {
                reponse += "=";
            } else if ( Integer.parseInt(proposition[i]) > Integer.parseInt(solution[i])) {
                reponse += "-";
            } else if ( Integer.parseInt(proposition[i]) < Integer.parseInt(solution[i])) {
                reponse += "+";
            } else
                reponse += "=";
        }

        System.out.println("Proposition : "+ n + " -> Reponse : " + reponse);
        choixEssai ++;
    }

        if (reponse.equals(vt)){
        System.out.println("Bravo !! Vous avez gagnez :)");
    } else if (!reponse.equals(vt)){
        System.out.println("Vous avez perdu :(");
    }

}

    /**
     * L'ordinateur et l'utilisateur joue tour à tour,
     * le premier à trouver la combinaison secrète de l'autre a gagné
     * @throws
     */
    @Override
    public void duel() throws Exception {

        logger.info("Vous avez choisi le mode duel");

        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
        String nbCasePlm = mapParameteres.get("nbCasePlm");

        Scanner ordi = new Scanner(System.in);
        Random rand = new Random();

        switch (nbCasePlm) {
            case "2":
                System.out.println("-- Vous devez selectionner une combinaison à 2 chiffres --");
                solutionPossible = rand.nextInt(9);
                solutionPossible += 10;
                vt = "==" ;
                break;
            case "3":
                System.out.println("-- Vous devez selectionner une combinaison à 3 chiffres --");
                solutionPossible = rand.nextInt(89);
                solutionPossible += 100;
                vt = "===";
                break;
            case "4":
                System.out.println("-- Vous devez selectionner une combinaison à 4 chiffres --");
                solutionPossible = rand.nextInt(899);
                solutionPossible += 1000;
                vt = "====";
                break;
            case "5":
                System.out.println("Vous devez selectionner une combinaison à 5 chiffres");
                solutionPossible = rand.nextInt(8999);
                solutionPossible += 10000;
                vt = "=====";
                break;
            case "6":
                System.out.println("-- Vous devez selectionner une combinaison à 6 chiffres --");
                solutionPossible = rand.nextInt(89999);
                solutionPossible += 100000;
                vt = "======";
                break;
            case "7":
                System.out.println("-- Vous devez selectionner une combinaison à 7 chiffres --");
                solutionPossible = rand.nextInt(899999);
                solutionPossible += 1000000;
                vt = "=======";
                break;
            case "8":
                System.out.println("-- Vous devez selectionner une combinaison à 8 chiffres --");
                solutionPossible = rand.nextInt(8999999);
                solutionPossible += 10000000;
                vt = "========";
                break;
            case "9":
                System.out.println("-- Vous devez selectionner une combinaison à 9 chiffres --");
                solutionPossible = rand.nextInt(89999999);
                solutionPossible += 100000000;
                vt = "=========";
                break;
            case "10":
                System.out.println("-- Vous devez selectionner une combinaison à 10 chiffres --");
                solutionPossible = rand.nextInt(899999999);
                solutionPossible += 1000000000;
                vt = "==========";
                break;

        }

        String nbEssai = mapParameteres.get("nbEssai");

        switch (nbEssai) {
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

        solutionDonné = Integer.toString(solutionPossible);

        String modeDeveloppeur = mapParameteres.get("modeDeveloppeur");

        if(modeDeveloppeur.equals("true")) {
            System.out.println("Combinaison choisi par l'ordinateur " + solutionDonné);
        }

    while (true){
        Scanner utlisateur = new Scanner(System.in);
        System.out.println("Entrez votre combinaison secrete");

        switch (nbCasePlm) {
            case "2":
                System.out.println("-- Vous devez selectionner une combinaison à 2 chiffres --");
                solutionPossible2 = ThreadLocalRandom.current().nextInt(9);
                solutionPossible2 += 10;
                vt = "==" ;
                break;
            case "3":
                System.out.println("-- Vous devez selectionner une combinaison à 3 chiffres --");
                solutionPossible2 = ThreadLocalRandom.current().nextInt(89);
                solutionPossible2 += 100;
                vt = "===";
                break;
            case "4":
                System.out.println("-- Vous devez selectionner une combinaison à 4 chiffres --");
                solutionPossible2 = ThreadLocalRandom.current().nextInt(899);
                solutionPossible2 += 1000;
                vt = "====";
                break;
            case "5":
                System.out.println("-- Vous devez selectionner une combinaison à 5 chiffres --");
                solutionPossible2 = ThreadLocalRandom.current().nextInt(8999);
                solutionPossible2 += 10000;
                vt = "=====";
                break;
            case "6":
                System.out.println("-- Vous devez selectionner une combinaison à 6 chiffres --");
                solutionPossible2 = ThreadLocalRandom.current().nextInt(89999);
                solutionPossible2 += 100000;
                vt = "======";
                break;
            case "7":
                System.out.println("-- Vous devez selectionner une combinaison à 7 chiffres --");
                solutionPossible2 = ThreadLocalRandom.current().nextInt(899999);
                solutionPossible2 += 1000000;
                vt = "=======";
                break;
            case "8":
                System.out.println("-- Vous devez selectionner une combinaison à 8 chiffres --");
                solutionPossible2 = ThreadLocalRandom.current().nextInt(8999999);
                solutionPossible2 += 10000000;
                vt = "========";
                break;
            case "9":
                System.out.println("-- Vous devez selectionner une combinaison à 9 chiffres --");
                solutionPossible2 = ThreadLocalRandom.current().nextInt(89999999);
                solutionPossible2 += 100000000;
                vt = "=========";
                break;
            case "10":
                System.out.println("-- Vous devez selectionner une combinaison à 10 chiffres --");
                solutionPossible2 = ThreadLocalRandom.current().nextInt(899999999);
                solutionPossible2 += 1000000000;
                vt = "==========";
                break;

        }

        try {
            solutionPossible2 =utlisateur.nextInt();
            break;
        } catch (InputMismatchException ime){
            System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez");
        }
        finally {
            utlisateur.nextLine();
        }
    }
        solutionDonné2 = Integer.toString(solutionPossible2);


        solution = solutionDonné.split("");
        solution2 = solutionDonné2.split("");
        length = solution.length;
        length2 = solution2.length;
        reponse = "";
        String[] proposition;
        reponse2 = "";
        String[] proposition2;
        System.out.println("A vous de jouer !");

        do {

            // choix joueur

            reponse = "";
            int n;
            while (true){
            try {
                n = ordi.nextInt();
                break;
            }catch (InputMismatchException ime){
                System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez");
            }
            finally {
                ordi.nextLine();
            }
            }

            proposition = Integer.toString(n).split("");

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

            System.out.println("Proposition : "+ n + " -> Reponse : " + reponse);

            if (!reponse.equals(vt)) {

                // choix ordinateur
                reponse2 = "";

                int r =0;

                switch (nbCasePlm) {
                    case "2":
                        r = rand.nextInt(9);
                        r += 10;
                        vt = "==" ;
                        break;
                    case "3":
                        r = rand.nextInt(89);
                        r += 100;
                        vt = "===";
                        break;
                    case "4":
                        r = rand.nextInt(899);
                        r += 1000;
                        vt = "====";
                        break;
                    case "5":
                        r = rand.nextInt(8999);
                        r += 10000;
                        vt = "=====";
                        break;
                    case "6":
                        r = rand.nextInt(89999);
                        r += 100000;
                        vt = "======";
                        break;
                    case "7":
                        r = rand.nextInt(899999);
                        r += 1000000;
                        vt = "=======";
                        break;
                    case "8":
                        r = rand.nextInt(8999999);
                        r += 10000000;
                        vt = "========";
                        break;
                    case "9":
                        r = rand.nextInt(89999999);
                        r += 100000000;
                        vt = "=========";
                        break;
                    case "10":
                        r = rand.nextInt(899999999);
                        r += 1000000000;
                        vt = "==========";
                        break;

                }

                System.out.println(r);

                proposition2 = Integer.toString(r).split("");

                for (int i = 0; i < length2; i++) {

                    if (Integer.parseInt(solution2[i]) == Integer.parseInt(proposition2[i])) {
                        reponse2 += "=";
                    } else if (Integer.parseInt(proposition2[i]) > Integer.parseInt(solution2[i])) {
                        reponse2 += "-";
                    } else if (Integer.parseInt(proposition2[i]) < Integer.parseInt(solution2[i])) {
                        reponse2 += "+";
                    } else
                        reponse2 += "=";
                }

                System.out.println("Proposition : "+ r + " -> Reponse : " + reponse2);
                choixEssai ++;
            }

        }while (!reponse.equals(vt) && !reponse2.equals(vt) && choixEssai <= nbEssaies) ;
        if (reponse.equals(vt)){
            System.out.println("Bravo !! Vous avez gagnez :)");
        } else if (!reponse.equals(vt)){
            System.out.println("Vous avez perdu :(");
        }
    }



    /**
     * Cette methode permet de proposer plusieurs option à la fin d'une partie.
     * @throws Exception
     */
    public void newMenu () throws Exception {
        logger.info(" Votre partie est terminée vous avez le choix entre plusieurs options ");
        int selection;

        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Rejouer au même jeu");
        System.out.println("2 - Lancer un autre jeu ");
        System.out.println("3 - Quitter l'application ");

        selection = sc.nextInt();

        switch (selection){
            case 1 :
                System.out.println("Vous avez choisi de rejouer au meme jeu");
                selectedMode();
                break;

            case 2:
                System.out.println("Vous avez choisi de lancer un autre jeu");
                Menu newMenu = new Menu();
                newMenu.displayAffichage();
                break;

            case 3:
                exit();
                break;

        }

    }
}


