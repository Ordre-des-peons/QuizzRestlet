package fr.epsi.gl.quizz.commande.question;

import fr.epsi.gl.quizz.commande.Message;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 10/05/13
 * Time: 13:02
 * To change this template use File | Settings | File Templates.
 */
public class RetraitReponseMessage implements Message {
    protected UUID idQuestion;
    protected String libelléReponse;

    public RetraitReponseMessage(UUID id, String libellé){
        idQuestion = id;
        libelléReponse = libellé;
    }
}
