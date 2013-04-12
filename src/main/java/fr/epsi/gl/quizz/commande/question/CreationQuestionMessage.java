package fr.epsi.gl.quizz.commande.question;

import fr.epsi.gl.quizz.commande.Message;

public class CreationQuestionMessage implements Message {

    public CreationQuestionMessage(String libellé) {
        this.libellé = libellé;
    }

    public final String libellé;
}
