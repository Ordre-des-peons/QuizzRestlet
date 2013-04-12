package fr.epsi.gl.quizz.web.resource.question;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.question.CreationQuestionMessage;
import fr.epsi.gl.quizz.requete.question.RechercheQuestions;
import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.inject.Inject;
import java.util.UUID;

public class QuestionsRessource extends ServerResource{

    @Inject
    public QuestionsRessource(BusCommande busCommande, RechercheQuestions recherche) {
        this.busCommande = busCommande;
        this.recherche = recherche;
    }

    @Get
    public ModeleEtVue represente() {
        return ModeleEtVue.crée("/question/questions").avec("questions", recherche.toutes());
    }

    @Post
    public void crée(Form formulaire) {
        CreationQuestionMessage commande = new CreationQuestionMessage(formulaire.getFirstValue("libelle"));
        ListenableFuture<UUID> idQuestion = busCommande.envoie(commande);
        setStatus(Status.SUCCESS_ACCEPTED);
        setLocationRef("/questions/" + Futures.getUnchecked(idQuestion));
    }

    private BusCommande busCommande;
    private RechercheQuestions recherche;
}
