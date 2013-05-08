package fr.epsi.gl.quizz.requete.question;

import com.foursquare.fongo.Fongo;
import fr.epsi.gl.quizz.requete.quizz.RechercheQuizzs;
import fr.epsi.gl.quizz.requete.quizz.ResumeQuizz;
import org.jongo.Jongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 08/05/13
 * Time: 01:02
 * To change this template use File | Settings | File Templates.
 */
public class RechercheQuizzTest {
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
    public void peutRécupérerTousLesQuizz() {
        jongo.getCollection("quizz").insert("{libellé: 'test'}");
        RechercheQuizzs recherche = new RechercheQuizzs(jongo);

        List<ResumeQuizz> quizzs = recherche.toutes();

        assertThat(quizzs).hasSize(1);
        assertThat(quizzs.get(0).getLibellé()).isEqualTo("test");
    }

    private Jongo jongo;
    private Fongo fongo;
}
