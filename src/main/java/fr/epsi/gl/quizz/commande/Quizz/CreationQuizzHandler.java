package fr.epsi.gl.quizz.commande.Quizz;

import fr.epsi.gl.quizz.commande.HandlerCommande;
import fr.epsi.gl.quizz.commande.question.CreationQuestionMessage;
import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.Quizz.FabriqueQuizz;
import fr.epsi.gl.quizz.domaine.Quizz.Quizz;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 06/05/13
 * Time: 23:17
 * To change this template use File | Settings | File Templates.
 */
public class CreationQuizzHandler implements HandlerCommande<CreationQuizzMessage> {

    @Override
    public UUID execute(CreationQuizzMessage commande) {
        Quizz quizz = new FabriqueQuizz().nouvelle(commande.libellé);
        Entrepots.quizz().ajoute(quizz);
        return quizz.getId();
    }

    @Override
    public Class<CreationQuizzMessage> typeCommande() {
        return CreationQuizzMessage.class;
    }
}
