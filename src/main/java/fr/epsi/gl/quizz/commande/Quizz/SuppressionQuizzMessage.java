package fr.epsi.gl.quizz.commande.Quizz;

import fr.epsi.gl.quizz.commande.Message;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 07/05/13
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class SuppressionQuizzMessage implements Message {

    protected UUID _id;

    public SuppressionQuizzMessage(UUID IdQuizz) {
        _id = IdQuizz;
    }
}
