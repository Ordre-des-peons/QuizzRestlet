package fr.epsi.gl.quizz.commande.question;

import fr.epsi.gl.quizz.commande.Message;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 06/05/13
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public class ModificationQuestionMessage implements Message {
    protected UUID _id;
    protected String _libellé;

    public ModificationQuestionMessage(UUID IdQuestion, String NouveauLibellé) {
        _id = IdQuestion;
        _libellé = NouveauLibellé;
    }
}
