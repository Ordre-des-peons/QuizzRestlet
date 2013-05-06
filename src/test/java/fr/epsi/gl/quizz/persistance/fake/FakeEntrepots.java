package fr.epsi.gl.quizz.persistance.fake;

import fr.epsi.gl.quizz.domaine.Entrepots;
import fr.epsi.gl.quizz.domaine.Quizz.EntrepotQuizz;
import fr.epsi.gl.quizz.domaine.question.EntrepotQuestions;

public class FakeEntrepots extends Entrepots{
    @Override
    protected EntrepotQuestions entrepotQuestions() {
        return entrepotQuestions;
    }

    @Override
    protected EntrepotQuizz entrepotQuizz() {
        return entrepotQuizz;
    }

    private EntrepotQuestions entrepotQuestions = new FakeEntrepotQuestions();
    private EntrepotQuizz entrepotQuizz = new FakeEntrepotQuizz();
}
