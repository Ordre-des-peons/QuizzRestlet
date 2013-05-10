package fr.epsi.gl.quizz.web.resource.quizz;

import com.google.inject.Inject;
import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.Quizz.AjoutQuestionMessage;
import fr.epsi.gl.quizz.commande.Quizz.SuppressionQuizzMessage;
import fr.epsi.gl.quizz.requete.quizz.DetailsQuizz;
import fr.epsi.gl.quizz.requete.quizz.RechercheQuizzs;
import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import org.restlet.data.Form;
import org.restlet.resource.*;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 08/05/13
 * Time: 00:53
 * To change this template use File | Settings | File Templates.
 */
public class QuizzRessource extends ServerResource {

    @Inject
    public QuizzRessource(RechercheQuizzs RechercheQuizz, BusCommande bus) {
        this.RechercheQuizz = RechercheQuizz;;
        this.bus = bus;
    }

    @Override
    protected void doInit() throws ResourceException {
        UUID idQuizz = UUID.fromString(getRequestAttributes().get("id").toString());
        quizz = RechercheQuizz.detailsDe(idQuizz);
    }

    @Get
    public ModeleEtVue représente() {
        return ModeleEtVue.crée("/quizz/quizz").avec("quizz", quizz);
    }

    @Put
    public void ajouteQuestion(Form formulaire) {
        AjoutQuestionMessage message = new AjoutQuestionMessage(UUID.fromString(quizz.getId()),UUID.randomUUID());
        bus.envoie(message);
    }

    @Delete
    public void supprimerQuestion() {
        SuppressionQuizzMessage message = new SuppressionQuizzMessage(UUID.fromString(quizz.getId()));
        bus.envoie(message);
    }

    private DetailsQuizz quizz;
    private RechercheQuizzs RechercheQuizz;;
    private BusCommande bus;
}
