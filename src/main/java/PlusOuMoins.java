package main.java;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe du Jeu plus ou moins avec ses differentes méthodes.
 */
public class PlusOuMoins extends Jeux {
    private static final Logger logger = LogManager.getLogger(PlusOuMoins.class);

    /**
     * Affiche les differents modes possible pour jouer au jeu,
     * lance ensuite la methode pour jouer en fonction du mode choisi,
     * et lance à la fin de la partie le menu de sortie.
     * @throws Exception
     */
    public void selectedMode() throws Exception {

        logger.info("Vous êtes dans le menu pour le choix des differents modes");

        int selectionMode;
        Scanner sc = new Scanner(System.in);
        while(true) {
        System.out.println("Veuillez choisir un mode de jeu ");
        System.out.println("1 - Mode challenger ");
        System.out.println("2 - Mode defenseur ");
        System.out.println("3 - Mode duel ");
        try {
        selectionMode = sc.nextInt();
            while (selectionMode > 3) {
                if (true) {
                    System.out.println("Vous n'avez pas entrez un choix valide, Réessayez");
                    selectionMode = sc.nextInt();
                } else
                    sc.nextLine();
            }
        break;
        } catch (InputMismatchException ime) {
        System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez ");
        }
        }
        displayMode(selectionMode);
        MenuDeSortie();
    }

    /**
     * Selon le choix precedent, cette methode renvoi au bon mode pour jouer.
     * @param Mode
     * @throws IOException, exception propagée
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
     * @throws IOException, exception propagée
     */
    @Override
    public void challenger() throws IOException {
        logger.info("Vous avez choisi le mode challenger");

        choixCombinaisonOrdi();
        choixEssais();

        solutionDonné = Integer.toString(solutionPossible);
        solution = solutionDonné.split("");
        length = solution.length;

        System.out.println("A vous de jouer !");

        while ( !reponse.equals(vt) && choixEssai <= nbEssaies ) {
            reponse = "";

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

          comparaisonDesDeuxCombinaisons();
          System.out.println("Proposition : "+ combinaisonSecrete + " -> Reponse : " + reponse);
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

        while (true) {

            System.out.println("Entrez votre combinaison secrete");
            Scanner reader = new Scanner(System.in);
            choixCombinaisonUtilisateur();
            choixEssais();

            try {
            combinaisonSecrete = reader.nextInt();
            break;
        } catch (InputMismatchException ime){
            System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez ");
        }
        }

        solutionDonné = Integer.toString(combinaisonSecrete);
        solution = solutionDonné.split("");
        length = solution.length;

        System.out.println("A vous de jouer !");

        while (!reponse.equals(vt) && choixEssai <= nbEssaies) {
        reponse = "";

        choixCombinaisonOrdi();

        System.out.println(solutionPossible);

        proposition = Integer.toString(solutionPossible).split("");

        comparaisonDesDeuxCombinaisons();

        System.out.println("Proposition : "+ solutionPossible + " -> Reponse : " + reponse);
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
     * @throws IOException, exception propagée
     */
    @Override
    public void duel() throws IOException {

        logger.info("Vous avez choisi le mode duel");
        Scanner ordi = new Scanner(System.in);

        choixCombinaisonOrdi();
        choixEssais();

        solutionDonné = Integer.toString(solutionPossible);

    while (true){
        Scanner utlisateur = new Scanner(System.in);
        System.out.println("Entrez votre combinaison secrete");
        choixCombinaisonUtilisateur();

        try {
            solutionPossible2 =utlisateur.nextInt();
            break;
        } catch (InputMismatchException ime){
            System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez");
        }

    }
        solutionDonné2 = Integer.toString(solutionPossible2);


        solution = solutionDonné.split("");
        solution2 = solutionDonné2.split("");
        length = solution.length;
        length2 = solution2.length;
        System.out.println("A vous de jouer !");

        do {
            reponse = "";

            int n;
            while (true){
            try {
                n = ordi.nextInt();
                break;
            }catch (InputMismatchException ime){
                System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez");
            }
            }

            proposition = Integer.toString(n).split("");

           comparaisonDesDeuxCombinaisons();

            System.out.println("Proposition : "+ n + " -> Reponse : " + reponse);

            if (!reponse.equals(vt)) {
                reponse2 = "";
                solutionPossible =0;
                choixCombinaisonOrdi();
                System.out.println(solutionPossible);
                proposition2 = Integer.toString(solutionPossible).split("");

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
                System.out.println("Proposition : "+ solutionPossible + " -> Reponse : " + reponse2);
                choixEssai ++;
            }
        }while (!reponse.equals(vt) && !reponse2.equals(vt) && choixEssai <= nbEssaies) ;
        if (reponse.equals(vt)){
            System.out.println("Bravo !! Vous avez gagnez :)");
        }else if (!reponse.equals(vt)){
            System.out.println("Vous avez perdu :(");
        }
    }

    /**
     * Methode qui renvoi la combinaison possible à entrer en fonction du parametre choisi.
     * @throws IOException, exception propagée
     */

    public void choixCombinaisonOrdi() throws IOException {
        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
        String nbCasePlm = mapParameteres.get("nbCasePlm");
        switch (nbCasePlm) {
            case "2":
                solutionPossible = (int) (Math.random() * 99);
                vt = "==";
                break;
            case "3":
                solutionPossible = (int) (Math.random() * 999);
                vt = "===";
                break;
            case "4":
                solutionPossible = (int) (Math.random() * 9999);
                vt = "====";
                break;
            case "5":
                solutionPossible = (int) (Math.random() * 99999);
                vt = "=====";
                break;
            case "6":
                solutionPossible = (int) (Math.random() * 999999);
                vt = "======";
                break;
            case "7":
                solutionPossible = (int) (Math.random() * 9999999);
                vt = "=======";
                break;
            case "8":
                solutionPossible = (int) (Math.random() * 99999999);
                vt = "========";
                break;
            case "9":
                solutionPossible = (int) (Math.random() * 999999999);
                vt = "=========";
                break;
            case "10":
                solutionPossible = (int) (Math.random() * 999999999) + 1000000000;
                vt = "==========";
                break;
        }
    }
    /**
     * Methode qui renvoi la combinaison possible à entrer en fonction du parametre choisi.
     * @throws IOException, exception propagée
     */

    public void choixCombinaisonUtilisateur() throws IOException {
        Map<String, String> mapParameteres = ReadPropertyFile.getListeParametres();
        String nbCasePlm = mapParameteres.get("nbCasePlm");



        switch (nbCasePlm) {
            case "2":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 99);
                vt = "==";
                break;
            case "3":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 999);
                vt = "===";
                break;
            case "4":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 9999);
                vt = "====";
                break;
            case "5":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 99999);
                vt = "=====";
                break;
            case "6":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 999999);
                vt = "======";
                break;
            case "7":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 9999999);
                vt = "=======";
                break;
            case "8":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 99999999);
                vt = "========";
                break;
            case "9":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 999999999);
                vt = "=========";
                break;
            case "10":
                combinaisonSecrete = ThreadLocalRandom.current().nextInt(0, 999999999);
                combinaisonSecrete += 1000000000;
                vt = "==========";
                break;
        }
    }

    /**
     * Methode qui renvoi le nombre d'essai possible en fonction du parametre choisi
     * @throws IOException
     */

    public void choixEssais() throws IOException {
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

    public void comparaisonDesDeuxCombinaisons (){

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
     * Cette methode permet de proposer plusieurs option à la fin d'une partie.
     * @throws Exception
     */
    public void MenuDeSortie () throws Exception {
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


