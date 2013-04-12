package fr.epsi.gl.quizz.web.resource.question;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.question.CreationQuestionMessage;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.inject.Inject;
import java.util.UUID;

public class QuestionsRessource extends ServerResource{

    @Inject
    public QuestionsRessource(BusCommande busCommande) {
        this.busCommande = busCommande;
    }

    @Post
    public void crée(Form formulaire) {
        CreationQuestionMessage commande = new CreationQuestionMessage(formulaire.getFirstValue("libelle"));
        ListenableFuture<UUID> idQuestion = busCommande.envoie(commande);
        setStatus(Status.SUCCESS_ACCEPTED);
        setLocationRef("/questions/" + Futures.getUnchecked(idQuestion));
    }

    private BusCommande busCommande;
}
