package fr.epsi.gl.quizz.commande.question;

import fr.epsi.gl.quizz.commande.Message;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 05/05/13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public class SuppressionQuestionMessage implements Message {
    protected UUID id;

    public SuppressionQuestionMessage(UUID idQuestion) {
        id = idQuestion;
    }
}
