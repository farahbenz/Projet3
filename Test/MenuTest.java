import main.java.Menu;
import org.junit.Test;

import static junit.framework.TestCase.fail;


public class MenuTest {

@Test
       public void AfficheLeMenu(String parametre) {

           Menu affiche = new Menu();
           int affichage = affiche.displayAffichage(parametre);
           System.out.println("Le menu affiché est" + affichage);
           fail("Erreur d'affichage");

      }
}

