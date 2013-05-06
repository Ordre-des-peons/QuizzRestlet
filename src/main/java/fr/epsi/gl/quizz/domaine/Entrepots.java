package fr.epsi.gl.quizz.domaine;

import fr.epsi.gl.quizz.domaine.Quizz.EntrepotQuizz;
import fr.epsi.gl.quizz.domaine.question.EntrepotQuestions;

public abstract class Entrepots {

    public static EntrepotQuestions questions() {
        return instance.entrepotQuestions();
    }

    public static EntrepotQuizz quizz() {
        return instance.entrepotQuizz();
    }

    protected abstract EntrepotQuestions entrepotQuestions();
    protected abstract EntrepotQuizz entrepotQuizz();
    public static void setInstance(Entrepots instance) {
        Entrepots.instance = instance;
    }

    private static Entrepots instance;
}
