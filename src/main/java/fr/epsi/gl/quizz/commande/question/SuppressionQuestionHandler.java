package fr.epsi.gl.quizz.commande.question;

import com.google.common.base.Optional;
import fr.epsi.gl.quizz.commande.HandlerCommande;
import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.question.Question;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 05/05/13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public class SuppressionQuestionHandler implements HandlerCommande<SuppressionQuestionMessage> {

    public Boolean execute(SuppressionQuestionMessage commande){
        if(PeutTrouverLaQuestion(commande)){
            Optional<Question> questionASupprimer = Entrepots.questions().get(commande.id);
            Entrepots.questions().supprime(questionASupprimer.get());
            return true;
        }
        return false;
    }

    private boolean PeutTrouverLaQuestion(SuppressionQuestionMessage commande) {
        return Entrepots.questions().get(commande.id).isPresent();
    }

    public Class<SuppressionQuestionMessage> typeCommande() {
        return SuppressionQuestionMessage.class;
    }
}
