package fr.epsi.gl.quizz.web.resource.quizz;

import com.google.inject.Inject;
import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.Quizz.AjoutQuestionMessage;
import fr.epsi.gl.quizz.requete.question.DetailsQuestion;
import fr.epsi.gl.quizz.requete.question.RechercheQuestions;
import fr.epsi.gl.quizz.requete.quizz.DetailsQuizz;
import fr.epsi.gl.quizz.requete.quizz.RechercheQuizzs;
import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

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

    private DetailsQuizz quizz;
    private DetailsQuestion question;
    private RechercheQuizzs RechercheQuizz;
    private RechercheQuestions RechercheQuestion;
    private BusCommande bus;
}
