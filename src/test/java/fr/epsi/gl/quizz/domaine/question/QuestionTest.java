package fr.epsi.gl.quizz.domaine.question;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class QuestionTest {

    @Test
    public void peutDonnerUnIdentifiant() {
        Question question = uneQuestion();

        assertThat(question).isNotNull();
        assertThat(question.getId()).isNotNull();
    }

    @Test
    public void peutDonnerUnLibellé() {
        Question question = new FabriqueQuestion().nouvelle("Les nains portent-ils des chaussettes ?");

        assertThat(question.getLibellé()).isEqualTo("Les nains portent-ils des chaussettes ?");
    }

    @Test
    public void peutAjouterRéponseFausse() {
        Question question = uneQuestion();

        Reponse réponse = question.ajouteRéponseFausse("Non");

        assertThat(réponse).isNotNull();
        assertThat(réponse.getLibellé()).isEqualTo("Non");
        assertThat(réponse.isCorrecte()).isFalse();
        assertThat(question.getRéponses()).contains(réponse);
    }

    @Test
    public void peutAjouterRéponseJuste() {
        Question question = uneQuestion();

        Reponse réponse = question.ajouteRéponseJuste("Oui");

        assertThat(réponse).isNotNull();
        assertThat(réponse.getLibellé()).isEqualTo("Oui");
        assertThat(réponse.isCorrecte()).isTrue();
        assertThat(question.getRéponses()).contains(réponse);
    }

    private Question uneQuestion() {
        return new FabriqueQuestion().nouvelle("toto");
    }
}
