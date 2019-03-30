import main.java.Menu;
import org.junit.Test;


import java.io.IOException;

import static junit.framework.TestCase.fail;


public class MenuTest {



    @Test
        public void AfficheLeMenu() throws IOException {

            Menu affiche = new Menu();
            int affichage = affiche.displayAffichage();
            System.out.println("Le menu affiché est" + affichage);
            fail("Erreur d'affichage");
        }
    }

