package fr.epsi.gl.quizz.web.resource.quizz;

import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.Quizz.AjoutQuestionMessage;
import fr.epsi.gl.quizz.requete.question.DetailsQuestion;
import fr.epsi.gl.quizz.requete.question.RechercheQuestions;
import fr.epsi.gl.quizz.requete.quizz.DetailsQuizz;
import fr.epsi.gl.quizz.requete.quizz.RechercheQuizzs;
import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import fr.epsi.gl.quizz.web.resource.RessourceHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.restlet.data.Form;

import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 08/05/13
 * Time: 01:19
 * To change this template use File | Settings | File Templates.
 */
public class QuizzRessourceTest {
    @Before
    public void setUp() throws Exception {
        rechercheQuizz = mock(RechercheQuizzs.class);
        rechercheQuestion = mock(RechercheQuestions.class);
        busCommande = mock(BusCommande.class);
        quizzRessource = new QuizzRessource(rechercheQuizz, busCommande);
    }

    @Test
    public void peutAfficherLeQuizz() {
        DetailsQuizz test = laRechercheRetourneDetailsQuizz();
        initialiseRessource(test);

        ModeleEtVue modeleEtVue = quizzRessource.représente();

        assertThat(modeleEtVue).isNotNull();
        assertThat(modeleEtVue.getTemplate()).isEqualTo("/quizz/quizz");
        assertThat(modeleEtVue.getData()).includes(entry("quizz", test));
    }

    private DetailsQuizz laRechercheRetourneDetailsQuizz() {
        DetailsQuizz test = new DetailsQuizz();
        UUID uuid = UUID.randomUUID();
        test.setId(uuid.toString());
        when(this.rechercheQuizz.detailsDe(uuid)).thenReturn(test);
        return test;
    }

    @Test
    public void peutAjouterUneQuestion() {
        DetailsQuizz detailsQuizz = laRechercheRetourneDetailsQuizz();
        initialiseRessource(detailsQuizz);
        Form formulaire = new Form();
        formulaire.add("idQuestion", "Une question");
        quizzRessource.ajouteQuestion(formulaire);

        ArgumentCaptor<AjoutQuestionMessage> captor = ArgumentCaptor.forClass(AjoutQuestionMessage.class);
        verify(busCommande).envoie(captor.capture());
        AjoutQuestionMessage message = captor.getValue();
        assertThat(message.idQuizz).isEqualTo(UUID.fromString(detailsQuizz.getId()));
    }

    private void initialiseRessource(DetailsQuizz detailsQuizz) {
        RessourceHelper.initialise(quizzRessource).avec("id", detailsQuizz.getId());
    }

    private RechercheQuizzs rechercheQuizz;
    private RechercheQuestions rechercheQuestion;
    private QuizzRessource quizzRessource;
    private BusCommande busCommande;
}
