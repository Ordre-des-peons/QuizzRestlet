package fr.epsi.gl.quizz.web.resource.quizz;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.Quizz.CreationQuizzMessage;
import fr.epsi.gl.quizz.requete.quizz.RechercheQuizzs;
import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 08/05/13
 * Time: 00:53
 * To change this template use File | Settings | File Templates.
 */
public class QuizzsRessource extends ServerResource{
    @Inject
    public QuizzsRessource(BusCommande busCommande, RechercheQuizzs recherche) {
        this.busCommande = busCommande;
        this.recherche = recherche;
    }

    @Get
    public ModeleEtVue represente() {
        return ModeleEtVue.crée("/quizz/quizzs").avec("quizzs", recherche.toutes());
    }

    @Post
    public void crée(Form formulaire) {
        CreationQuizzMessage commande = new CreationQuizzMessage(formulaire.getFirstValue("libelle"));
        ListenableFuture<UUID> idQuizz = busCommande.envoie(commande);
        setStatus(Status.SUCCESS_ACCEPTED);
        setLocationRef("/quizzs/" + Futures.getUnchecked(idQuizz));
    }

    private BusCommande busCommande;
    private RechercheQuizzs recherche;
}
