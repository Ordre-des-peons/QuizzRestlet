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
 * Date: 07/05/13
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
public class SuppressionQuizzHandlerTest {

    @Rule
    public AvecEntrepots entrepots = new AvecEntrepots();

    @Test
    public void peutSupprimerQuizz() {
        CreationQuizzMessage commandeCreer = new CreationQuizzMessage("UberQuizzTest");
        _idQuizz = new CreationQuizzHandler().execute(commandeCreer);
        SuppressionQuizzMessage commandeSuppr = new SuppressionQuizzMessage(_idQuizz);

        Boolean résultat = new SuppressionQuizzHandler().execute(commandeSuppr);

        assertThat(résultat.booleanValue()).isTrue();
        assertThat(Entrepots.quizz().get(_idQuizz).isPresent()).isFalse();
    }

    private UUID _idQuizz;
}
