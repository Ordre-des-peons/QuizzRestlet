package fr.epsi.gl.quizz.commande.Quizz;

import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.persistance.fake.AvecEntrepots;
import org.junit.Rule;
import org.junit.Test;

import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 06/05/13
 * Time: 23:13
 * To change this template use File | Settings | File Templates.
 */
public class CreationQuizzHandlerTest {
    @Rule
    public AvecEntrepots entrepots = new AvecEntrepots();

    @Test
    public void peutCréerUnQuizz() {
        CreationQuizzMessage commande = new CreationQuizzMessage("UberMegaQuizzDeLaMort");

        UUID idQuizz = new CreationQuizzHandler().execute(commande);

        assertThat(idQuizz).isNotNull();
        assertThat(Entrepots.quizz().get(idQuizz)).isNotNull();
        assertThat(Entrepots.quizz().get(idQuizz).get().getLibellé()).isEqualTo("UberMegaQuizzDeLaMort");
    }
}
