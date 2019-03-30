package main.java;

import java.io.IOException;
import java.util.*;


public class Duel extends Jeux{

    /**
     * Mode Duel du plus ou moins
     * @throws IOException, exception propagée
     */
    public void plusMoins() throws IOException {

        Scanner ordi = new Scanner(System.in);
        Random rand = new Random();

        choixCombinaisonOrdinateur();

        choixEssais();

        solutionDonné = Integer.toString(solutionPossible); // la solution choisi par l'ordi devient String
        solution = solutionDonné.split("");

        Scanner reader = new Scanner(System.in);

        while (true) {
            System.out.println("Veuillez entrez la combinaison secrete");
            choixCombinaisonUtilisateur();
            try {
                combinaisonSecrete = reader.nextInt();
                solutionDonné2 = Integer.toString(combinaisonSecrete);
                solution2 = solutionDonné2.split("");
                length = solution2.length;

                while (length != nbCase) {
                    if (true) {
                        System.out.println("Vous n'avez pas entrez un choix valide, Réessayez");
                        combinaisonSecrete = reader.nextInt();
                    }
                    break;
                }

                break;
            } catch (InputMismatchException ime) {
                System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez");
            }

        }

        System.out.println("A vous de jouer !");

        do {
            reponse = "";

            int n;
            while (true){
                try {
                    n = ordi.nextInt();
                    solutionDonné3 = Integer.toString(n);
                    solution3 = solutionDonné3.split("");
                    length3 = solution3.length;
                    while (length3 != nbCase) {
                        if (true) {
                            System.out.println("Vous n'avez pas entrez un choix valide, Réessayez");
                            n =ordi.nextInt();
                        }
                        break;
                    }
                    break;
                }catch (InputMismatchException ime){
                    System.out.println("!!La valeur saisie n'est pas une valeur numérique!! Réessayez");
                }
            }

            proposition = Integer.toString(n).split("");

            resultat();

            System.out.println("Proposition : "+ n + " -> Reponse : " + reponse);

            if (!reponse.equals(vt)) {

                Integer[] solution1 = new Integer[nbCase];

                for (int i = 0; i < solution2.length; i++) {
                    solution1[i] = Integer.parseInt(solution2[i]);
                }

                Integer[] choixOrdi = new Integer[nbCase];

                ArrayList<Integer[]> ordi_choix = new ArrayList<Integer[]>();

                for (int i = 0; i < solution1.length; i++) {
                    if (choixOrdi[i] != solution1[i]) {


                        if (choixOrdi[i] == null) {
                            choixOrdi[i] = 1;
                        } else if (choixOrdi[i] > solution1[i]) {
                            choixOrdi[i] -= rand.nextInt(choixOrdi[i]);
                            reponse2 += "-";
                        } else if (choixOrdi[i] < solution1[i]) {
                            choixOrdi[i] += rand.nextInt(solution1[i]);
                            reponse2 += "+";
                        }
                    } else {
                        reponse2 += "=";
                    }
                }

                System.out.println(" Reponse : " + reponse2 );
                reponse2 = "";
                ordi_choix.add(choixOrdi);
                choixEssai++;
            }

        }while (!reponse.equals(vt) && choixEssai <= nbEssaies) ;
        if (reponse.equals(vt)){
            System.out.println("Bravo !! Vous avez gagnez :)");
        }else if (!reponse.equals(vt)){
            System.out.println("Vous avez perdu :(");
        }

    }

    /**
     * Mode Duel du mastermind
     * @throws IOException
     */
    public void master() throws IOException {
        Scanner ordi = new Scanner(System.in);
        choixCombinaisonOrdinateur();
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
}
