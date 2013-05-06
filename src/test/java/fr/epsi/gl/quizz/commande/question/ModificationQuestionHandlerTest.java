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
 * Date: 06/05/13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
public class ModificationQuestionHandlerTest {
    @Rule
    public AvecEntrepots entrepots = new AvecEntrepots();

    @Test
    public void PeutModifierQuestion() {
        CreationQuestionMessage commande = new CreationQuestionMessage("UberQuestionDeLaMortQuiTue");
        _idQuestion = new CreationQuestionHandler().execute(commande);
        ModificationQuestionMessage message = new ModificationQuestionMessage(_idQuestion, "UberQuestionDeLaMortQuiTueModifié");
        Boolean resultat = new ModificationQuestionHandler().execute(message);

        assertThat(resultat.booleanValue()).isTrue();
        assertThat(Entrepots.questions().get(_idQuestion).get().getLibellé()).isEqualTo("UberQuestionDeLaMortQuiTueModifié");
    }

    private UUID _idQuestion;
}
