package fr.epsi.gl.quizz.commande.question;

import com.google.common.base.Optional;
import fr.epsi.gl.quizz.commande.HandlerCommande;
import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.question.Question;

import static com.google.common.base.Preconditions.*;

public class AjoutReponseHandler implements HandlerCommande<AjoutReponseMessage> {

    @Override
    public Object execute(AjoutReponseMessage commande) {
        Optional<Question> question = Entrepots.questions().get(commande.idQuestion);
        checkState(question.isPresent(), "Question inconnue");
        if(commande.correcte) {
            question.get().ajouteRéponseJuste(commande.libellé);
        } else {
            question.get().ajouteRéponseFausse(commande.libellé);
        }
        return null;
    }

    @Override
    public Class<AjoutReponseMessage> typeCommande() {
        return AjoutReponseMessage.class;
    }
}
