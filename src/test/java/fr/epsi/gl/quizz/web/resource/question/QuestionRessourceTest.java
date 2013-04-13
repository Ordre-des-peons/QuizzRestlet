package fr.epsi.gl.quizz.web.resource.question;

import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.question.AjoutReponseMessage;
import fr.epsi.gl.quizz.requete.question.DetailsQuestion;
import fr.epsi.gl.quizz.requete.question.RechercheQuestions;
import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import fr.epsi.gl.quizz.web.resource.RessourceHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.restlet.data.Form;

import java.util.UUID;

import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.MapAssert.*;
import static org.mockito.Mockito.*;

public class QuestionRessourceTest {

    @Before
    public void setUp() throws Exception {
        recherche = mock(RechercheQuestions.class);
        busCommande = mock(BusCommande.class);
        questionRessource = new QuestionRessource(recherche, busCommande);
    }

    @Test
    public void peutAfficherLaQuestion() {
        DetailsQuestion test = laRechercheRetourne();
        initialiseRessource(test);

        ModeleEtVue modeleEtVue = questionRessource.représente();

        assertThat(modeleEtVue).isNotNull();
        assertThat(modeleEtVue.getTemplate()).isEqualTo("/question/question");
        assertThat(modeleEtVue.getData()).includes(entry("question", test));
    }

    private DetailsQuestion laRechercheRetourne() {
        DetailsQuestion test = new DetailsQuestion();
        UUID uuid = UUID.randomUUID();
        test.setId(uuid.toString());
        when(this.recherche.detailsDe(uuid)).thenReturn(test);
        return test;
    }

    @Test
    public void peutAjouterUneRéponse() {
        DetailsQuestion detailsQuestion = laRechercheRetourne();
        initialiseRessource(detailsQuestion);
        Form formulaire = new Form();
        formulaire.add("libelle", "Une réponse");
        formulaire.add("correcte", "checked");

        questionRessource.ajouteRéponse(formulaire);


        ArgumentCaptor<AjoutReponseMessage> captor = ArgumentCaptor.forClass(AjoutReponseMessage.class);
        verify(busCommande).envoie(captor.capture());
        AjoutReponseMessage message = captor.getValue();
        assertThat(message.idQuestion).isEqualTo(UUID.fromString(detailsQuestion.getId()));
        assertThat(message.correcte).isTrue();
        assertThat(message.libellé).isEqualTo("Une réponse");
    }

    private void initialiseRessource(DetailsQuestion detailsQuestion) {
        RessourceHelper.initialise(questionRessource).avec("id", detailsQuestion.getId());
    }

    private RechercheQuestions recherche;
    private QuestionRessource questionRessource;
    private BusCommande busCommande;
}
