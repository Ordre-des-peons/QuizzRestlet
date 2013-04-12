package fr.epsi.gl.quizz.web.resource.question;

import com.google.common.util.concurrent.Futures;
import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.Message;
import fr.epsi.gl.quizz.commande.question.CreationQuestionMessage;
import fr.epsi.gl.quizz.web.resource.RessourceHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.restlet.data.Form;
import org.restlet.data.Reference;
import org.restlet.data.Status;

import java.util.UUID;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

public class QuestionsRessourceTest {

    @Before
    public void setUp() throws Exception {
        bus = mock(BusCommande.class);
        when(bus.envoie(any(Message.class))).thenReturn(Futures.<Object>immediateFuture(UUID.randomUUID()));
        ressource = new QuestionsRessource(bus);
        RessourceHelper.initialise(ressource);
    }

    @Test
    public void peutCréerUneQuestion() {
        Form formulaire = new Form();
        formulaire.add("libelle", "Ceci est une question");

        ressource.crée(formulaire);

        ArgumentCaptor<CreationQuestionMessage> capteur = ArgumentCaptor.forClass(CreationQuestionMessage.class);
        verify(bus).envoie(capteur.capture());
        CreationQuestionMessage commande = capteur.getValue();
        assertThat(commande.libellé).isEqualTo("Ceci est une question");
    }

    @Test
    public void peutRediriger() {
        UUID idQuestion = UUID.randomUUID();
        when(bus.envoie(any(CreationQuestionMessage.class))).thenReturn(Futures.<Object>immediateFuture(idQuestion));

        ressource.crée(new Form());

        assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
        assertThat(ressource.getLocationRef()).isEqualTo(new Reference("http://localhost/questions/" + idQuestion));
    }

    private BusCommande bus;
    private QuestionsRessource ressource;
}
