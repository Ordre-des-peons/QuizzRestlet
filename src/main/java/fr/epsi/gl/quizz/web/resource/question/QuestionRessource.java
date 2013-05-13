package fr.epsi.gl.quizz.web.resource.question;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.question.AjoutReponseMessage;
import fr.epsi.gl.quizz.commande.question.ModificationQuestionMessage;
import fr.epsi.gl.quizz.commande.question.RetraitReponseMessage;
import fr.epsi.gl.quizz.commande.question.SuppressionQuestionMessage;
import fr.epsi.gl.quizz.requete.question.DetailsQuestion;
import fr.epsi.gl.quizz.requete.question.RechercheQuestions;
import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import org.restlet.data.Form;
import org.restlet.resource.*;

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

    @Post
    public void modifie(Form formulaire){
        String nouveauLibellé = formulaire.getFirstValue("nouveau-libelle");
        UUID id = UUID.fromString(question.getId());

        ModificationQuestionMessage message = new ModificationQuestionMessage(id, nouveauLibellé);
        bus.envoie(message);
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

    @Delete
    public void supprimerLaRéponse(String LibReponse){
        if(LibReponse == null)
            supprimerQuestion();
        else
            supprimerRéponse(LibReponse);
    }

    private void supprimerQuestion() {
        SuppressionQuestionMessage message = new SuppressionQuestionMessage(UUID.fromString(question.getId()));
        bus.envoie(message);
    }

    private void supprimerRéponse(String reponse) {
        RetraitReponseMessage message = new RetraitReponseMessage(UUID.fromString(question.getId()), reponse);
        bus.envoie(message);
    }

    private DetailsQuestion question;
    private RechercheQuestions recherche;
    private BusCommande bus;
}
