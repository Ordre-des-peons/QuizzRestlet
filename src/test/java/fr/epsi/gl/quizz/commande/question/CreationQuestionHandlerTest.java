package fr.epsi.gl.quizz.commande.question;

import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.persistance.fake.AvecEntrepots;
import org.junit.Rule;
import org.junit.Test;

import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;

public class CreationQuestionHandlerTest {


    @Rule
    public AvecEntrepots entrepots = new AvecEntrepots();

    @Test
    public void peutCréerQuestion() {
        CreationQuestionMessage commande = new CreationQuestionMessage("Ceci est une question");

        UUID idQuestion = new CreationQuestionHandler().execute(commande);

        assertThat(idQuestion).isNotNull();
        assertThat(Entrepots.questions().get(idQuestion)).isNotNull();
        assertThat(Entrepots.questions().get(idQuestion).get().getLibellé()).isEqualTo("Ceci est une question");
    }
}
