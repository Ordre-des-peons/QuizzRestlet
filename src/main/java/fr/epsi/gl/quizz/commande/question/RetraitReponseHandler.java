package fr.epsi.gl.quizz.commande.question;

import com.google.common.base.Optional;
import fr.epsi.gl.quizz.commande.HandlerCommande;
import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.question.Question;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 10/05/13
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 */
public class RetraitReponseHandler implements HandlerCommande<RetraitReponseMessage> {

    @Override
    public Boolean execute(RetraitReponseMessage commande){
        if(Entrepots.questions().get(commande.idQuestion).isPresent()){
            Optional<Question> questionASupprimer = Entrepots.questions().get(commande.idQuestion);
            questionASupprimer.get().retireRéponse(commande.libelléReponse);
            return true;
        }
        return false;
    }

    @Override
    public Class<RetraitReponseMessage> typeCommande() {
        return RetraitReponseMessage.class;

    }
}
