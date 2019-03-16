package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Classe du Jeu mastermind avec ses differentes méthodes.
 */

public class Mastermind extends Jeux {

    private static final Logger logger = LogManager.getLogger(PlusOuMoins.class);


    public Mastermind() {
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
     * @throws IOException
     */

    public void displayMode(int Mode) throws IOException {

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
     * 1er methode : L'ordinateur est le defenseur, il choisi une combinaison secrete.
     * L'attaquant doit deviner la combinaison secrete choisi par le defenseur.
     * @throws IOException generé pour le mode developpeur.
     */
    @Override
    public void challenger() throws IOException {
        logger.info("Vous avez choisi le mode challenger");

        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
        String nbCaseMaster = mapParameteres.get("nbCaseMaster");

        Random rand = new Random();

        switch (nbCaseMaster) {
            case "4":
                System.out.println("-- Vous devez selectionner une combinaison à 4 chiffres --");
                solutionPossible = rand.nextInt(899);
                solutionPossible += 1000;
                vtm = 4;
                break;
            case "5":
                System.out.println("-- Vous devez selectionner une combinaison à 5 chiffres --");
                solutionPossible = rand.nextInt(8999);
                solutionPossible += 10000;
                vtm = 5;
                break;
            case "6":
                System.out.println("-- Vous devez selectionner une combinaison à 6 chiffres --");
                solutionPossible = rand.nextInt(89999);
                solutionPossible += 100000;
                vtm = 6;
                break;
            case "7":
                System.out.println("-- Vous devez selectionner une combinaison à 7 chiffres --");
                solutionPossible = rand.nextInt(899999);
                solutionPossible += 1000000;
                vtm = 7;
                break;
            case "8":
                System.out.println("-- Vous devez selectionner une combinaison à 8 chiffres --");
                solutionPossible = rand.nextInt(8999999);
                solutionPossible += 10000000;
                vtm = 8;
                break;
            case "9":
                System.out.println("-- Vous devez selectionner une combinaison à 9 chiffres --");
                solutionPossible = rand.nextInt(89999999);
                solutionPossible += 100000000;
                vtm = 9;
                break;
            case "10":
                System.out.println("-- Vous devez selectionner une combinaison à 10 chiffres --");
                solutionPossible = rand.nextInt(899999999);
                solutionPossible += 1000000000;
                vtm = 10;
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

        if (modeDeveloppeur.equals("true")) {
            System.out.println("( Combinaison secrete: " + solutionDonné + " )");
        }


        solution = solutionDonné.split("");
        length = solution.length;

        String[] proposition;


        List<String> propositionAsList = Arrays.asList(solution);

        System.out.println("A vous de jouer !");

        while (wellPlaced != vtm && choixEssai <= nbEssaies ) {

            int n;

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
            present = 0;
            wellPlaced = 0;
            for (int i = 0; i < length; i++) {

                if (solution[i].equals(proposition[i])) {
                    wellPlaced++;
                } else if (propositionAsList.contains(proposition[i]))
                    present ++;

            }
            if (present == 0 && wellPlaced == 1) {
                System.out.println("Proposition : " + n + " -> Reponse : " + wellPlaced + "  bien placé");
            } else if (present == 0 && wellPlaced <= 3) {
                System.out.println("Proposition : " + n + " -> Reponse : " + wellPlaced + " bien placés");
            } else if (present == 1 && wellPlaced <= 3) {
                System.out.println("Proposition : " + n + " -> Reponse : " + present + " présent," + wellPlaced + " bien placés");
            } else if (present <= 3 && wellPlaced <= 3) {
                System.out.println("Proposition : " + n + " -> Reponse : " + present + " présents, " + wellPlaced + " bien placés");
            } else if (present > 1 && wellPlaced == 0) {
                System.out.println("Proposition :" + n + " -> Reponse : " + present + " présents");
            }

            choixEssai ++;

        }
        if (wellPlaced == vtm && choixEssai <=nbEssaies ) {
            System.out.println("Bravo, vous avez gagnez !!");
        } else
            System.out.println("Vous avez perdu");
    }

    /**
     * 1er methode : Le defenseur choisi une combinaison secrete.
     * L'attaquant est l'ordinateur et doit deviner la combinaison secrete choisi par le defenseur.
     */

            @Override
            public void defenseur () throws IOException {
                logger.info("Vous avez choisi le mode defenseur");
                Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
                String nbCaseMaster = mapParameteres.get("nbCaseMaster");
                while (true) {

                    System.out.println("Entrez votre combinaison secrete");
                    Scanner reader = new Scanner(System.in);

                    switch (nbCaseMaster) {

                        case "4":
                            System.out.println("-- Vous devez selectionner une combinaison à 4 chiffres --");
                            combinaisonSecrete = ThreadLocalRandom.current().nextInt(899);
                            combinaisonSecrete += 1000;
                            vtm = 4;
                            break;
                        case "5":
                            System.out.println("-- Vous devez selectionner une combinaison à 5 chiffres --");
                            combinaisonSecrete = ThreadLocalRandom.current().nextInt(8999);
                            combinaisonSecrete += 10000;
                            vtm = 5;
                            break;
                        case "6":
                            System.out.println("-- Vous devez selectionner une combinaison à 6 chiffres --");
                            combinaisonSecrete = ThreadLocalRandom.current().nextInt(89999);
                            combinaisonSecrete += 100000;
                            vtm = 6;
                            break;
                        case "7":
                            System.out.println("-- Vous devez selectionner une combinaison à 7 chiffres --");
                            combinaisonSecrete = ThreadLocalRandom.current().nextInt(899999);
                            combinaisonSecrete += 1000000;
                            vtm = 7;
                            break;
                        case "8":
                            System.out.println("-- Vous devez selectionner une combinaison à 8 chiffres --");
                            combinaisonSecrete = ThreadLocalRandom.current().nextInt(8999999);
                            combinaisonSecrete += 10000000;
                            vtm = 8;
                            break;
                        case "9":
                            System.out.println("-- Vous devez selectionner une combinaison à 9 chiffres --");
                            combinaisonSecrete = ThreadLocalRandom.current().nextInt(89999999);
                            combinaisonSecrete += 100000000;
                            vtm = 9;
                            break;
                        case "10":
                            System.out.println("-- Vous devez selectionner une combinaison à 10 chiffres --");
                            combinaisonSecrete = ThreadLocalRandom.current().nextInt(899999999);
                            combinaisonSecrete += 1000000000;
                            vtm = 10;
                            break;
                    }

                    try {
                        combinaisonSecrete = reader.nextInt();
                        break;
                    } catch (InputMismatchException ime){
                        System.out.println("La valeur saisie n'est pas une valeur numérique");
                    }
                    finally {
                        reader.nextLine();
                    }
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
                solutionDonné =Integer.toString(combinaisonSecrete);

                solution =solutionDonné.split("");
                length =solution.length;

                String[] proposition;

                List<String> propositionAsList = Arrays.asList(solution);

                System.out.println("A vous de jouer !");

                while (wellPlaced != vtm && choixEssai <= nbEssaies) {
                    int n =0;
                    Random rand = new Random();

                    switch (nbCaseMaster) {
                        case "4":
                            n = rand.nextInt(899);
                            n += 1000;
                            vtm = 4;
                            break;
                        case "5":
                            n = rand.nextInt(8999);
                            n += 10000;
                            vtm = 5;
                            break;
                        case "6":
                            n = rand.nextInt(89999);
                            n += 100000;
                            vtm = 6;
                            break;
                        case "7":
                            n = rand.nextInt(899999);
                            n += 1000000;
                            vtm = 7;
                            break;
                        case "8":
                            n = rand.nextInt(8999999);
                            n += 10000000;
                            vtm = 8;
                            break;
                        case "9":
                            n = rand.nextInt(89999999);
                            n += 100000000;
                            vtm = 9;
                            break;
                        case "10":
                            n = rand.nextInt(899999999);
                            n += 1000000000;
                            vtm = 10;
                            break;

                    }


                    proposition = Integer.toString(n).split("");
                    present = 0;
                    wellPlaced = 0;
                    for (int i = 0; i < length; i++) {

                        if (solution[i].equals(proposition[i])) {
                            wellPlaced++;
                        } else if (propositionAsList.contains(proposition[i]))
                            present++;

                    }
                    if (present == 0 && wellPlaced == 1) {
                        System.out.println("Proposition : " +  n + " -> Reponse : " + wellPlaced + "  bien placé");
                    } else if (present == 0 && wellPlaced <= 3) {
                        System.out.println("Proposition : " + n  + " ->  Reponse : " + wellPlaced + " bien placés");
                    } else if (present == 1 && wellPlaced <= 3) {
                        System.out.println("Proposition : " + n + " -> Reponse : " + present + " présent," + wellPlaced + " bien placés");
                    } else if (present <= 3 && wellPlaced <= 3) {
                        System.out.println("Proposition : " + n  + " -> Reponse : " + present + " présents, " + wellPlaced + " bien placés");
                    } else if (present > 1 && wellPlaced == 0) {
                        System.out.println("Proposition :" + n + " -> Reponse : " + present + " présents");
                    }

                    choixEssai++;

                }
                if (wellPlaced == vtm && choixEssai <= nbEssaies) {
                    System.out.println("Bravo, vous avez gagnez !!");
                } else
                    System.out.println("Vous avez perdu");
            }

    /**
     * L'ordinateur et l'utilisateur joue tour à tour,
     * le premier à trouver la combinaison secrète de l'autre a gagné
     * @throws
     */
    @Override
    public void duel() throws IOException {
        logger.info("Vous avez choisi le mode duel");
        Scanner ordi = new Scanner(System.in);
        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();

        String nbCaseMaster = mapParameteres.get("nbCaseMaster");

        Random rand = new Random();

        switch (nbCaseMaster) {
            case "4":
                System.out.println("-- Vous devez selectionner une combinaison à 4 chiffres --");
                solutionPossible = rand.nextInt(899);
                solutionPossible += 1000;
                vtm = 4;
                break;
            case "5":
                System.out.println("-- Vous devez selectionner une combinaison à 5 chiffres --");
                solutionPossible = rand.nextInt(8999);
                solutionPossible += 10000;
                vtm = 5;
                break;
            case "6":
                System.out.println("-- Vous devez selectionner une combinaison à 6 chiffres --");
                solutionPossible = rand.nextInt(89999);
                solutionPossible += 100000;
                vtm = 6;
                break;
            case "7":
                System.out.println("-- Vous devez selectionner une combinaison à 7 chiffres --");
                solutionPossible = rand.nextInt(899999);
                solutionPossible += 1000000;
                vtm = 7;
                break;
            case "8":
                System.out.println("-- Vous devez selectionner une combinaison à 8 chiffres --");
                solutionPossible = rand.nextInt(8999999);
                solutionPossible += 10000000;
                vtm = 8;
                break;
            case "9":
                System.out.println("-- Vous devez selectionner une combinaison à 9 chiffres --");
                solutionPossible = rand.nextInt(89999999);
                solutionPossible += 100000000;
                vtm = 9;
                break;
            case "10":
                System.out.println("-- Vous devez selectionner une combinaison à 10 chiffres --");
                solutionPossible = rand.nextInt(899999999);
                solutionPossible += 1000000000;
                vtm = 10;
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

        if (modeDeveloppeur.equals("true")) {
            System.out.println("Combinaison choisi par l'ordinateur " + solutionDonné);
        }

        while (true){
            Scanner utlisateur = new Scanner(System.in);
            System.out.println("Entrez votre combinaison secrete");
            switch (nbCaseMaster) {

                case "4":
                    System.out.println("-- Vous devez selectionner une combinaison à 4 chiffres --");
                    solutionPossible2 = ThreadLocalRandom.current().nextInt(899);
                    solutionPossible2 += 1000;
                    vtm = 4;
                    break;
                case "5":
                    System.out.println("-- Vous devez selectionner une combinaison à 5 chiffres --");
                    solutionPossible2 = ThreadLocalRandom.current().nextInt(8999);
                    solutionPossible2 += 10000;
                    vtm = 5;
                    break;
                case "6":
                    System.out.println("-- Vous devez selectionner une combinaison à 6 chiffres --");
                    solutionPossible2 = ThreadLocalRandom.current().nextInt(89999);
                    solutionPossible2 += 100000;
                    vtm = 6;
                    break;
                case "7":
                    System.out.println("-- Vous devez selectionner une combinaison à 7 chiffres --");
                    solutionPossible2 = ThreadLocalRandom.current().nextInt(899999);
                    solutionPossible2 += 1000000;
                    vtm = 7;
                    break;
                case "8":
                    System.out.println("-- Vous devez selectionner une combinaison à 8 chiffres --");
                    solutionPossible2 = ThreadLocalRandom.current().nextInt(8999999);
                    solutionPossible2 += 10000000;
                    vtm = 8;
                    break;
                case "9":
                    System.out.println("-- Vous devez selectionner une combinaison à 9 chiffres --");
                    solutionPossible2 = ThreadLocalRandom.current().nextInt(89999999);
                    solutionPossible2 += 100000000;
                    vtm = 9;
                    break;
                case "10":
                    System.out.println("-- Vous devez selectionner une combinaison à 10 chiffres --");
                    solutionPossible2 = ThreadLocalRandom.current().nextInt(899999999);
                    solutionPossible2 += 1000000000;
                    vtm = 10;
                    break;

            }

            try {
                solutionPossible2 =utlisateur.nextInt();
                break;
            } catch (InputMismatchException ime){
                System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez ");
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
        String[] proposition;
        String[] proposition2;

        System.out.println("A vous de jouer !");

        do {

            // choix joueur
            int n;
            while (true){
                try {
                    n = ordi.nextInt();
                    break;
                }catch (InputMismatchException ime){
                    System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez ");
                }
                finally {
                    ordi.nextLine();
                }
            }

            proposition = Integer.toString(n).split("");
            List<String> propositionAsList = Arrays.asList(solution);
            present = 0;
            wellPlaced = 0;
            for (int i = 0; i < length; i++) {

                if (solution[i].equals(proposition[i])) {
                    wellPlaced++;
                } else if (propositionAsList.contains(proposition[i]))
                    present++;

            }

            if (present == 0 && wellPlaced == 1) {
                System.out.println("Proposition : " + n + " -> Reponse : " + wellPlaced + "  bien placé");
            } else if (present == 0 && wellPlaced <= 3) {
                System.out.println("Proposition : " + n + " -> Reponse : " + wellPlaced + " bien placés");
            } else if (present == 1 && wellPlaced <= 3) {
                System.out.println("Proposition : " + n + " -> Reponse : " + present + " présent," + wellPlaced + " bien placés");
            } else if (present <= 3 && wellPlaced <= 3) {
                System.out.println("Proposition : " + n + " -> Reponse : " + present + " présents, " + wellPlaced + " bien placés");
            } else if (present > 1 && wellPlaced == 0) {
                System.out.println("Proposition :" + n + " -> Reponse : " + present + " présents");
            }

            if (wellPlaced != vtm) {

            // choix ordinateur

                int r =0;

                switch (nbCaseMaster) {

                    case "4":
                        r = rand.nextInt(899);
                        r += 1000;
                        vtm = 4;
                        break;
                    case "5":
                        r = rand.nextInt(8999);
                        r += 10000;
                        vtm = 5;
                        break;
                    case "6":
                        r = rand.nextInt(89999);
                        r += 100000;
                        vtm = 6;
                        break;
                    case "7":
                        r = rand.nextInt(899999);
                        r += 1000000;
                        vtm = 7;
                        break;
                    case "8":
                        r = rand.nextInt(8999999);
                        r += 10000000;
                        vtm = 8;
                        break;
                    case "9":
                        r = rand.nextInt(89999999);
                        r += 100000000;
                        vtm = 9;
                        break;
                    case "10":
                        r = rand.nextInt(899999999);
                        r += 1000000000;
                        vtm = 10;
                        break;

                }
            System.out.println(r);

            proposition2 = Integer.toString(r).split("");
            List<String> proposition2AsList = Arrays.asList(solution2);
            present = 0;
            wellPlaced = 0;
            for (int i = 0; i < length; i++) {

                if (solution2[i].equals(proposition2[i])) {
                    wellPlaced++;
                } else if (proposition2AsList.contains(proposition2[i]))
                    present++;

            }
            if (present == 0 && wellPlaced == 1) {
                System.out.println("Proposition : " + r + " -> Reponse :  " + wellPlaced + "  bien placé");
            } else if (present == 0 && wellPlaced <= 3) {
                System.out.println("Proposition : " + r + " -> Reponse : " + wellPlaced + " bien placés");
            } else if (present == 1 && wellPlaced <= 3) {
                System.out.println("Proposition : " + r + " -> Reponse : " + present + " présent," + wellPlaced + " bien placés");
            } else if (present <= 3 && wellPlaced <= 3) {
                System.out.println("Proposition : " + r + " -> Reponse : " + present + " présents, " + wellPlaced + " bien placés");
            } else if (present > 1 && wellPlaced == 0) {
                System.out.println("Proposition :" + r + " -> Reponse : " + present + " présents");
            }
            choixEssai++;
        }

    }   while (wellPlaced != vtm && choixEssai <= nbEssaies) ;
        if (wellPlaced == vtm) {
            System.out.println("Vous avez gagnez :)");
        } else
            System.out.println("Vous avez perdu :(");


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

                switch (selection) {
                    case 1:
                        System.out.println("Vous avez choisi de rejouer au meme jeu");
                        Mastermind mst = new Mastermind();
                        mst.selectedMode();
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
