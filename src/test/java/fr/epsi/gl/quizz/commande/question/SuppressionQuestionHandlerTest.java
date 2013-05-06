package fr.epsi.gl.quizz.commande.question;

import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.persistance.fake.AvecEntrepots;
import org.junit.Test;
import org.junit.Rule;

import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 05/05/13
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public class SuppressionQuestionHandlerTest {

    @Rule
    public AvecEntrepots entrepots = new AvecEntrepots();

    @Test
    public void peutSupprimerQuestion() {
        CreationQuestionMessage commandeCreer = new CreationQuestionMessage("UberQuestionTest");
        _idQuestion = new CreationQuestionHandler().execute(commandeCreer);
        SuppressionQuestionMessage commandeSuppr = new SuppressionQuestionMessage(_idQuestion);

        Boolean résultat = new SuppressionQuestionHandler().execute(commandeSuppr);

        assertThat(résultat.booleanValue()).isTrue();
        assertThat(Entrepots.questions().get(_idQuestion).isPresent()).isFalse();
    }

    private UUID _idQuestion;

}
