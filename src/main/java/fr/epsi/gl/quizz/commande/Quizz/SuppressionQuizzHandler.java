package fr.epsi.gl.quizz.commande.Quizz;

import com.google.common.base.Optional;
import fr.epsi.gl.quizz.commande.HandlerCommande;
import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.Quizz.Quizz;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 07/05/13
 * Time: 21:14
 * To change this template use File | Settings | File Templates.
 */
public class SuppressionQuizzHandler implements HandlerCommande<SuppressionQuizzMessage> {

    public Boolean execute(SuppressionQuizzMessage commande){
        if(PeutTrouverLeQuizz(commande)){
            Optional<Quizz> quizzASupprimer = Entrepots.quizz().get(commande._id);
            Entrepots.quizz().supprime(quizzASupprimer.get());
            return true;
        }
        return false;
    }

    private boolean PeutTrouverLeQuizz(SuppressionQuizzMessage commande) {
        return Entrepots.quizz().get(commande._id).isPresent();
    }

    public Class<SuppressionQuizzMessage> typeCommande() {
        return SuppressionQuizzMessage.class;
    }
}
