package fr.epsi.gl.quizz.web.resource.question;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.question.AjoutReponseMessage;
import fr.epsi.gl.quizz.requete.question.DetailsQuestion;
import fr.epsi.gl.quizz.requete.question.RechercheQuestions;
import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.UUID;

public class QuestionRessource extends ServerResource
{

    @Inject
    public QuestionRessource(RechercheQuestions recherche, BusCommande bus) {
        this.recherche = recherche;
        this.bus = bus;
    }

    @Override
    protected void doInit() throws ResourceException {
        UUID id = UUID.fromString(getRequestAttributes().get("id").toString());
        question = recherche.detailsDe(id);
    }

    @Get
    public ModeleEtVue représente() {
        return ModeleEtVue.crée("/question/question").avec("question", question);
    }

    @Put
    public void ajouteRéponse(Form formulaire) {
        boolean correcte = !Strings.isNullOrEmpty(formulaire.getFirstValue("correcte"));
        AjoutReponseMessage message = new AjoutReponseMessage(UUID.fromString(question.getId()), formulaire.getFirstValue("libelle"), correcte);
        bus.envoie(message);
    }

    private DetailsQuestion question;
    private RechercheQuestions recherche;
    private BusCommande bus;
}
