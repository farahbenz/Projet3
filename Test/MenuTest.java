import main.java.JeuxException;
import main.java.Menu;
import org.junit.Test;


import java.io.IOException;

import static junit.framework.TestCase.fail;


public class MenuTest {

@Test
       public void AfficheLeMenu(String parametre) throws IOException, JeuxException {

           Menu affiche = new Menu();
           int affichage = (int) affiche.displayAffichage(parametre);
           System.out.println("Le menu affiché est" + affichage);
           fail("Erreur d'affichage");

      }
}

