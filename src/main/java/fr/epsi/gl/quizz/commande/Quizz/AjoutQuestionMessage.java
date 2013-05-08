package fr.epsi.gl.quizz.commande.Quizz;

import fr.epsi.gl.quizz.commande.Message;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 07/05/13
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
public class AjoutQuestionMessage implements Message {
    public AjoutQuestionMessage(UUID idQuizz,UUID idQuestion) {
        this.idQuizz = idQuizz;
        this.idQuestion = idQuestion;
    }

    public final UUID idQuizz;
    public final UUID idQuestion;
}
