package fr.epsi.gl.quizz.web.resource.question;

import fr.epsi.gl.quizz.requete.question.DetailsQuestion;
import fr.epsi.gl.quizz.requete.question.RechercheQuestions;
import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import fr.epsi.gl.quizz.web.resource.RessourceHelper;
import org.junit.Test;

import java.util.UUID;

import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.MapAssert.*;
import static org.mockito.Mockito.*;

public class QuestionRessourceTest {

    @Test
    public void peutAfficherLaQuestion() {
        DetailsQuestion test = new DetailsQuestion();
        UUID uuid = UUID.randomUUID();
        test.setId(uuid.toString());
        RechercheQuestions recherche = mock(RechercheQuestions.class);
        when(recherche.detailsDe(uuid)).thenReturn(test);
        QuestionRessource ressource = new QuestionRessource(recherche);
        RessourceHelper.initialise(ressource).avec("id", test.getId());

        ModeleEtVue modeleEtVue = ressource.représente();

        assertThat(modeleEtVue).isNotNull();
        assertThat(modeleEtVue.getTemplate()).isEqualTo("/question/question");
        assertThat(modeleEtVue.getData()).includes(entry("question", test));
    }
}
