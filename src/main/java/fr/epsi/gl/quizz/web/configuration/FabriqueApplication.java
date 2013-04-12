package fr.epsi.gl.quizz.web.configuration;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.HandlerCommande;
import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.web.QuizzApplication;
import org.reflections.Reflections;
import org.restlet.Context;

import java.util.Set;

public class FabriqueApplication {

    public QuizzApplication configure() {
        Injector injector = Guice.createInjector(Stage.PRODUCTION, new GuiceProductionModule());
        Entrepots.setInstance(injector.getInstance(Entrepots.class));
        congigureBusCommande(injector);
        return new QuizzApplication(new Context(), injector);
    }

    private void congigureBusCommande(Injector injector) {
        BusCommande busCommande = injector.getInstance(BusCommande.class);
        Set<Class<? extends HandlerCommande>> handlers = new Reflections("fr.epsi.gl.quizz.commande").getSubTypesOf(HandlerCommande.class);
        for (Class<? extends HandlerCommande> handler : handlers) {
            busCommande.enregistreHandler(injector.getInstance(handler));
        }
    }

}