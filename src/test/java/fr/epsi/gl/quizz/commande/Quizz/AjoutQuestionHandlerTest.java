package fr.epsi.gl.quizz.commande.Quizz;

import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.Quizz.FabriqueQuizz;
import fr.epsi.gl.quizz.domaine.question.FabriqueQuestion;
import fr.epsi.gl.quizz.domaine.question.Question;
import fr.epsi.gl.quizz.domaine.Quizz.Quizz;
import fr.epsi.gl.quizz.persistance.fake.AvecEntrepots;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 07/05/13
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */
public class AjoutQuestionHandlerTest {
    @Rule
    public AvecEntrepots entrepots = new AvecEntrepots();

    @Test
    public void peutAjouterUneRéponse() {
        Quizz LeQuizz = unQuizz();
        Question LaQuestion = uneQuestion();
        AjoutQuestionMessage message = new AjoutQuestionMessage(LeQuizz.getId(),LaQuestion.getId());

        new AjoutQuestionHandler().execute(message);

        assertThat(LeQuizz.getQuestions()).hasSize(1);
        Question question = LeQuizz.getQuestions().get(0);
        assertThat(question.getLibellé()).isEqualTo("une question");
    }

    private Quizz unQuizz() {
        Quizz quizz = new FabriqueQuizz().nouvelle("Un Quizz");
        Entrepots.quizz().ajoute(quizz);
        return quizz;
    }

    private Question uneQuestion() {
        Question question = new FabriqueQuestion().nouvelle("une question");
        Entrepots.questions().ajoute(question);
        return question;
    }
}
