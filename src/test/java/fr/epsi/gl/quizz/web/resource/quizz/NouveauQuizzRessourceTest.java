package fr.epsi.gl.quizz.web.resource.quizz;

import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import fr.epsi.gl.quizz.web.resource.question.NouvelleQuestionRessource;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 08/05/13
 * Time: 01:16
 * To change this template use File | Settings | File Templates.
 */
public class NouveauQuizzRessourceTest {
    @Test
    public void peutAfficherLeFormulaire() {
        NouveauQuizzRessource ressource = new NouveauQuizzRessource();

        ModeleEtVue modeleEtVue = ressource.représente();

        assertThat(modeleEtVue).isNotNull();
        assertThat((modeleEtVue.getTemplate())).isEqualTo("/quizz/nouveau-quizz");
    }
}
