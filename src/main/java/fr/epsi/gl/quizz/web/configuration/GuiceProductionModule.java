package fr.epsi.gl.quizz.web.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import fr.epsi.gl.quizz.commande.BusCommande;
import fr.epsi.gl.quizz.commande.FournisseurMongoSession;
import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.persistance.mongo.EntrepotsMongo;
import fr.epsi.gl.quizz.persistance.mongo.FournisseurMongoSessionParThread;
import org.jongo.Jongo;
import org.mongolink.MongoSessionManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.inject.Singleton;

public class GuiceProductionModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(MongoSessionManager.class).toProvider(MongoSessionManagerProvider.class).in(Singleton.class);
        bind(FournisseurMongoSession.class).to(FournisseurMongoSessionParThread.class).in(Singleton.class);
        bind(Jongo.class).toProvider(JongoProvider.class).in(Singleton.class);
        bind(Entrepots.class).to(EntrepotsMongo.class);
        bind(BusCommande.class).in(Singleton.class);
    }


    @Provides
    public TemplateEngine templateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("templates");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
}
