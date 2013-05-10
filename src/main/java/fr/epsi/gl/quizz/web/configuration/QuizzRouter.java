package fr.epsi.gl.quizz.web.configuration;

import com.google.inject.Injector;
import fr.epsi.gl.quizz.web.resource.AccueilRessource;
import fr.epsi.gl.quizz.web.resource.question.NouvelleQuestionRessource;
import fr.epsi.gl.quizz.web.resource.question.QuestionRessource;
import fr.epsi.gl.quizz.web.resource.question.QuestionsRessource;
import fr.epsi.gl.quizz.web.resource.quizz.NouveauQuizzRessource;
import fr.epsi.gl.quizz.web.resource.quizz.QuizzRessource;
import fr.epsi.gl.quizz.web.resource.quizz.QuizzsRessource;
import fr.epsi.gl.quizz.web.restlet.GuiceFinder;
import org.restlet.Context;
import org.restlet.resource.Finder;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

public class QuizzRouter extends Router {

    public QuizzRouter(Context context, Injector injector) {
        super(context);
        this.injector = injector;
        attacheRoutes();
    }

    private void attacheRoutes() {
        attach("/", AccueilRessource.class);
        attach("/nouvelle-question", NouvelleQuestionRessource.class);
        attach("/questions", QuestionsRessource.class);
        attach("/questions/{id}", QuestionRessource.class);
        attach("/nouveau-quizz", NouveauQuizzRessource.class);
        attach("/quizzs", QuizzsRessource.class);
        attach("/quizzs/{id}", QuizzRessource.class);
    }

    @Override
    public Finder createFinder(Class<? extends ServerResource> resourceClass) {
        return new GuiceFinder(getContext(), resourceClass, injector);
    }

    private Injector injector;
}
