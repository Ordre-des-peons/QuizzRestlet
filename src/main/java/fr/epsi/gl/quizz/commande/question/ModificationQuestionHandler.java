package fr.epsi.gl.quizz.commande.question;

import com.google.common.base.Optional;
import fr.epsi.gl.quizz.commande.HandlerCommande;
import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.question.Question;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 06/05/13
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */
public class ModificationQuestionHandler implements HandlerCommande<ModificationQuestionMessage> {

    @Override
    public Boolean execute(ModificationQuestionMessage commande) {
        if(Entrepots.questions().get(commande._id).isPresent()){
            Optional<Question> questionAModifier = Entrepots.questions().get(commande._id);
            questionAModifier.get().setLibellé(commande._libellé);
            return true;
        }
        return false;
    }

    @Override
    public Class<ModificationQuestionMessage> typeCommande() {
        return ModificationQuestionMessage.class;
    }
}
