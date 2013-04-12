package fr.epsi.gl.quizz.web.resource.question;

import com.google.inject.Inject;
import fr.epsi.gl.quizz.requete.question.DetailsQuestion;
import fr.epsi.gl.quizz.requete.question.RechercheQuestions;
import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.UUID;

public class QuestionRessource extends ServerResource
{

    @Inject
    public QuestionRessource(RechercheQuestions recherche) {
        this.recherche = recherche;
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

    private DetailsQuestion question;
    private RechercheQuestions recherche;
}
