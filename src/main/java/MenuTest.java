package main.java;

import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    public void AfficheLeMenu() throws Exception {

        Menu affiche = new Menu();
        int affichage = affiche.displayAffichage();
        System.out.println("Le menu affiché est" + affichage);

    }

}