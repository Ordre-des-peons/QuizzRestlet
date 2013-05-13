package fr.epsi.gl.quizz.commande.question;

import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.question.FabriqueQuestion;
import fr.epsi.gl.quizz.domaine.question.Question;
import fr.epsi.gl.quizz.domaine.question.Reponse;
import fr.epsi.gl.quizz.persistance.fake.AvecEntrepots;
import org.junit.Rule;
import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;
/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 10/05/13
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class RetraitReponseHandlerTest {
    @Rule
    public AvecEntrepots entrepots = new AvecEntrepots();

    @Test
    public void peutSupprimerUneRéponse(){
        _uneQuestion = new FabriqueQuestion().nouvelle("test");
        Entrepots.questions().ajoute(_uneQuestion);

        AjoutReponseMessage messageAjoutReponse = new AjoutReponseMessage(_uneQuestion.getId(), "une réponse", true);
        new AjoutReponseHandler().execute(messageAjoutReponse);
        _uneRéponse = _uneQuestion.getRéponses().get(0);
        RetraitReponseMessage messageRetraitReponse = new RetraitReponseMessage(_uneQuestion.getId(), _uneRéponse.getLibellé());
        Boolean résultat = new RetraitReponseHandler().execute(messageRetraitReponse);

        assertThat(résultat.booleanValue()).isTrue();
        assertThat(_uneQuestion.getRéponses().contains(_uneRéponse)).isFalse();
    }

    private Question _uneQuestion;
    private Reponse _uneRéponse;
}
