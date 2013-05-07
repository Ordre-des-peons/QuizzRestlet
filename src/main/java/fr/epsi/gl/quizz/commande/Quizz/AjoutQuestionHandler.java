package fr.epsi.gl.quizz.commande.Quizz;

import com.google.common.base.Optional;
import fr.epsi.gl.quizz.commande.HandlerCommande;
import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.Quizz.Quizz;
import fr.epsi.gl.quizz.domaine.question.Question;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 07/05/13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class AjoutQuestionHandler implements HandlerCommande<AjoutQuestionMessage> {
    @Override
    public Object execute(AjoutQuestionMessage commande) {
        Optional<Quizz> quizz = Entrepots.quizz().get(commande.idQuizz);
        Optional<Question> question = Entrepots.questions().get(commande.idQuestion);
        checkState(quizz.isPresent(), "Quizz inconnue");
        checkState(question.isPresent(), "Question inconnue");
        quizz.get().ajouteQuestion(question.get());
        return null;
    }

    @Override
    public Class<AjoutQuestionMessage> typeCommande() {
        return AjoutQuestionMessage.class;
    }
}
