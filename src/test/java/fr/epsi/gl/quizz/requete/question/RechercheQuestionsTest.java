package fr.epsi.gl.quizz.requete.question;

import com.foursquare.fongo.Fongo;
import org.jongo.Jongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.fest.assertions.Assertions.*;

public class RechercheQuestionsTest {

    @Before
    public void setUp() throws Exception {
        fongo = new Fongo("test");
        jongo = new Jongo(fongo.getDB("quizz"));
    }

    @After
    public void tearDown() throws Exception {
        fongo.dropDatabase("quizz");
    }

    @Test
    public void peutRécupérerToutesLesQuestions() {
        jongo.getCollection("question").insert("{libellé: 'test'}");
        RechercheQuestions recherche = new RechercheQuestions(jongo);

        List<ResumeQuestion> questions = recherche.toutes();

        assertThat(questions).hasSize(1);
        assertThat(questions.get(0).getLibellé()).isEqualTo("test");
    }

    @Test
    public void peutRécupérerUneQuestion() {
        UUID id = UUID.randomUUID();
        jongo.getCollection("question").insert("{_id: #, libellé : 'test', réponses : [{correcte: false, libellé:'réponse'}]}", id);
        RechercheQuestions recherche = new RechercheQuestions(jongo);

        DetailsQuestion details = recherche.detailsDe(id);

        assertThat(details).isNotNull();
        assertThat(details.getLibellé()).isEqualTo("test");
        assertThat(details.getRéponses()).hasSize(1);
    }

    private Jongo jongo;
    private Fongo fongo;
}
