package fr.epsi.gl.quizz.commande;

import fr.epsi.gl.quizz.commande.question.CreationQuestionMessage;
import org.junit.Test;

import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BusCommandeTest {

    @Test
    public void peutEnregistrerUnHandler() {
        BusCommande busCommande = new BusCommande(mock(FournisseurMongoSession.class));
        CreationQuestionCommandeHandlerCommande handler = new CreationQuestionCommandeHandlerCommande();
        busCommande.enregistreHandler(handler);
        CreationQuestionMessage commande = new CreationQuestionMessage("test");

        busCommande.envoie(commande);

        assertThat(handler.dernièreCommande).isEqualTo(commande);
    }

    private static class CreationQuestionCommandeHandlerCommande implements HandlerCommande<CreationQuestionMessage> {

        @Override
        public UUID execute(CreationQuestionMessage commande) {
            this.dernièreCommande = commande;
            return UUID.randomUUID();
        }

        @Override
        public Class<CreationQuestionMessage> typeCommande() {
            return CreationQuestionMessage.class;
        }

        private CreationQuestionMessage dernièreCommande;
    }
}
