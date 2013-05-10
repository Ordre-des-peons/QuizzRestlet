package fr.epsi.gl.quizz.web.resource.quizz;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.Message;
import fr.epsi.gl.quizz.commande.Quizz.CreationQuizzMessage;
import fr.epsi.gl.quizz.commande.question.CreationQuestionMessage;
import fr.epsi.gl.quizz.requete.question.RechercheQuestions;
import fr.epsi.gl.quizz.requete.question.ResumeQuestion;
import fr.epsi.gl.quizz.requete.quizz.RechercheQuizzs;
import fr.epsi.gl.quizz.requete.quizz.ResumeQuizz;
import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import fr.epsi.gl.quizz.web.resource.RessourceHelper;
import fr.epsi.gl.quizz.web.resource.question.QuestionsRessource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.restlet.data.Form;
import org.restlet.data.Reference;
import org.restlet.data.Status;

import java.util.List;
import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 10/05/13
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
public class QuizzsRessourceTest {
    @Before
    public void setUp() throws Exception {
        bus = mock(BusCommande.class);
        when(bus.envoie(any(Message.class))).thenReturn(Futures.<Object>immediateFuture(UUID.randomUUID()));
        recherche = mock(RechercheQuizzs.class);
        ressource = new QuizzsRessource(bus, recherche);
        RessourceHelper.initialise(ressource);
    }

    @Test
    public void peutCréerUnQuizz() {
        Form formulaire = new Form();
        formulaire.add("libelle", "Ceci est un quizz");

        ressource.crée(formulaire);

        ArgumentCaptor<CreationQuizzMessage> capteur = ArgumentCaptor.forClass(CreationQuizzMessage.class);
        verify(bus).envoie(capteur.capture());
        CreationQuizzMessage commande = capteur.getValue();
        assertThat(commande.libellé).isEqualTo("Ceci est un quizz");
    }

    @Test
    public void peutRediriger() {
        UUID idQuizz = UUID.randomUUID();
        when(bus.envoie(any(CreationQuizzMessage.class))).thenReturn(Futures.<Object>immediateFuture(idQuizz));

        ressource.crée(new Form());

        assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
        assertThat(ressource.getLocationRef()).isEqualTo(new Reference("http://localhost/quizzs/" + idQuizz));
    }

    @Test
    public void peutAfficherToutesLesQuestions() {
        List<ResumeQuizz> quizzs = Lists.newArrayList();
        when(recherche.toutes()).thenReturn(quizzs);

        ModeleEtVue represente = ressource.represente();

        assertThat(represente.getTemplate()).isEqualTo("/quizz/quizzs");
        assertThat(represente.getData()).includes(entry("quizzs", quizzs));

    }
    private BusCommande bus;
    private QuizzsRessource ressource;
    private RechercheQuizzs recherche;
}
