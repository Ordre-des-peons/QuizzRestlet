package fr.epsi.gl.quizz.commande.question;

import fr.epsi.gl.quizz.commande.HandlerCommande;
import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.question.FabriqueQuestion;
import fr.epsi.gl.quizz.domaine.question.Question;

import java.util.UUID;

@SuppressWarnings("UnusedDeclaration")
public class CreationQuestionHandler implements HandlerCommande<CreationQuestionMessage> {

    @Override
    public UUID execute(CreationQuestionMessage commande) {
        Question question = new FabriqueQuestion().nouvelle(commande.libellé);
        Entrepots.questions().ajoute(question);
        return question.getId();
    }

    @Override
    public Class<CreationQuestionMessage> typeCommande() {
        return CreationQuestionMessage.class;
    }


}
