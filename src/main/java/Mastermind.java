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

    private static final Logger logger = LogManager.getLogger(Mastermind.class);


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

        while(true) {
            Scanner sc = new Scanner(System.in);

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

        choixCombinaisonOrdinateur();
        choixNombreEssai();

        solutionDonné = Integer.toString(solutionPossible);
        solution = solutionDonné.split("");
        length = solution.length;

        System.out.println("A vous de jouer !");

        while (wellPlaced != vtm && choixEssai <= nbEssaies ) {
            while (true) {

                Scanner reader = new Scanner(System.in);
                choixCombinaisonUtilisateur();

                try {
                    combinaisonSecrete = reader.nextInt();
                    break;
                } catch (InputMismatchException ime) {
                    System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez");
                }
            }
            proposition = Integer.toString(combinaisonSecrete).split("");

            resultat();

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
        while (true) {
            System.out.println("Entrez votre combinaison secrete");
            Scanner reader = new Scanner(System.in);
            choixCombinaisonUtilisateur();
            try {
                combinaisonSecrete = reader.nextInt();
                break;
            } catch (InputMismatchException ime){
                System.out.println("La valeur saisie n'est pas une valeur numérique");
            }
        }

        choixNombreEssai();

        solutionDonné =Integer.toString(combinaisonSecrete);

        solution =solutionDonné.split("");
        length =solution.length;

        System.out.println("A vous de jouer !");

        while (wellPlaced != vtm && choixEssai <= nbEssaies) {

            choixCombinaisonOrdinateur();
            proposition = Integer.toString(solutionPossible).split("");

            resultat();

            if (present == 0 && wellPlaced == 1) {
                System.out.println("Proposition : " +  solutionPossible + " -> Reponse : " + wellPlaced + "  bien placé");
            } else if (present == 0 && wellPlaced <= 3) {
                System.out.println("Proposition : " + solutionPossible  + " ->  Reponse : " + wellPlaced + " bien placés");
            } else if (present == 1 && wellPlaced <= 3) {
                System.out.println("Proposition : " + solutionPossible + " -> Reponse : " + present + " présent," + wellPlaced + " bien placés");
            } else if (present <= 3 && wellPlaced <= 3) {
                System.out.println("Proposition : " + solutionPossible  + " -> Reponse : " + present + " présents, " + wellPlaced + " bien placés");
            } else if (present > 1 && wellPlaced == 0) {
                System.out.println("Proposition :" + solutionPossible + " -> Reponse : " + present + " présents");
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
        choixCombinaisonOrdinateur();
        choixNombreEssai();
        solutionDonné = Integer.toString(solutionPossible);

        while (true){
            Scanner utlisateur = new Scanner(System.in);
            System.out.println("Entrez votre combinaison secrete");
            choixCombinaisonUtilisateur();

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

        System.out.println("A vous de jouer !");

        do {
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
            resultat();

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

            choixCombinaisonOrdinateur();
            System.out.println(solutionPossible);

            proposition2 = Integer.toString(solutionPossible).split("");

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
                System.out.println("Proposition : " + solutionPossible + " -> Reponse :  " + wellPlaced + "  bien placé");
            } else if (present == 0 && wellPlaced <= 3) {
                System.out.println("Proposition : " + solutionPossible + " -> Reponse : " + wellPlaced + " bien placés");
            } else if (present == 1 && wellPlaced <= 3) {
                System.out.println("Proposition : " + solutionPossible+ " -> Reponse : " + present + " présent," + wellPlaced + " bien placés");
            } else if (present <= 3 && wellPlaced <= 3) {
                System.out.println("Proposition : " + solutionPossible + " -> Reponse : " + present + " présents, " + wellPlaced + " bien placés");
            } else if (present > 1 && wellPlaced == 0) {
                System.out.println("Proposition :" + solutionPossible + " -> Reponse : " + present + " présents");
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
     * Methode qui renvoi la combinaison possible à entrer en fonction du parametre choisi.
     * @throws IOException, exception propagée
     */

    public void choixCombinaisonOrdinateur () throws IOException {
        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
        String nbCaseMaster = mapParameteres.get("nbCaseMaster");

        switch (nbCaseMaster) {
            case "4":
                solutionPossible = (int) (Math.random() * 9999);
                vtm = 4;
                break;
            case "5":
                solutionPossible = (int) (Math.random() * 99999);
                break;
            case "6":
                solutionPossible = (int) (Math.random() * 999999);
                vtm = 6;
                break;
            case "7":
                solutionPossible = (int) (Math.random() * 9999999);
                vtm = 7;
                break;
            case "8":
                solutionPossible = (int) (Math.random() * 99999999);
                vtm = 8;
                break;
            case "9":
                solutionPossible = (int) (Math.random() * 999999999);
                vtm = 9;
                break;
            case "10":
                solutionPossible = (int) (Math.random() * 999999999 + 1000000000);
                vtm = 10;
                break;

        }
    }
    /**
     * Methode qui renvoi la combinaison possible à entrer en fonction du parametre choisi.
     * @throws IOException, exception propagée
     */

    public void choixCombinaisonUtilisateur () throws IOException {
        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
        String nbCaseMaster = mapParameteres.get("nbCaseMaster");
        switch (nbCaseMaster) {

            case "4":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 9999);
                vtm = 4;
                break;
            case "5":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 99999);
                vtm = 5;
                break;
            case "6":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 999999);
                vtm = 6;
                break;
            case "7":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 9999999);
                vtm = 7;
                break;
            case "8":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 99999999);
                vtm = 8;
                break;
            case "9":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 999999999);
                vtm = 9;
                break;
            case "10":

                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 999999999);
                combinaisonSecrete += 1000000000;
                vtm = 10;
                break;
        }
    }

    /**
     * Methode qui renvoi le nombre d'essai possible en fonction du parametre choisi
     * @throws IOException
     */

    public void choixNombreEssai () throws IOException {
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
     * Methode qui compare la solution soit la combinaison secrete donné et la proposition faite.
     */

    public void resultat (){
        List<String> propositionAsList = Arrays.asList(solution);
        present = 0;
        wellPlaced = 0;
        for (int i = 0; i < length; i++) {

            if (solution[i].equals(proposition[i])) {
                wellPlaced++;
            } else if (propositionAsList.contains(proposition[i]))
                present ++;

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
