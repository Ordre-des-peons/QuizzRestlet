package fr.epsi.gl.quizz.commande.question;

import fr.epsi.gl.quizz.commande.Message;

import java.util.UUID;

public class AjoutReponseMessage implements Message {

    public AjoutReponseMessage(UUID idQuestion, String libellé, boolean correcte) {
        this.idQuestion = idQuestion;
        this.correcte = correcte;
        this.libellé = libellé;
    }

    public final UUID idQuestion;
    public final boolean correcte;
    public final String libellé;
}
