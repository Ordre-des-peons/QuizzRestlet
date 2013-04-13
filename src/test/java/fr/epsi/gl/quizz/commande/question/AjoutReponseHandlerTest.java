package fr.epsi.gl.quizz.commande.question;

import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.question.FabriqueQuestion;
import fr.epsi.gl.quizz.domaine.question.Question;
import fr.epsi.gl.quizz.domaine.question.Reponse;
import fr.epsi.gl.quizz.persistance.fake.AvecEntrepots;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class AjoutReponseHandlerTest {

    @Rule
    public AvecEntrepots entrepots = new AvecEntrepots();

    @Test
    public void peutAjouterUneRéponse() {
        Question question = uneQuestion();
        AjoutReponseMessage message = new AjoutReponseMessage(question.getId(), "une réponse", true);

        new AjoutReponseHandler().execute(message);

        assertThat(question.getRéponses()).hasSize(1);
        Reponse reponse = question.getRéponses().get(0);
        assertThat(reponse.isCorrecte()).isTrue();
        assertThat(reponse.getLibellé()).isEqualTo("une réponse");
    }

    private Question uneQuestion() {
        Question question = new FabriqueQuestion().nouvelle("test");
        Entrepots.questions().ajoute(question);
        return question;
    }
}
