package fr.epsi.gl.quizz.commande.Quizz;

import fr.epsi.gl.quizz.commande.Message;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 06/05/13
 * Time: 23:15
 * To change this template use File | Settings | File Templates.
 */
public class CreationQuizzMessage implements Message {
    public CreationQuizzMessage(String libellé) {
        this.libellé = libellé;
    }

    public final String libellé;
}
