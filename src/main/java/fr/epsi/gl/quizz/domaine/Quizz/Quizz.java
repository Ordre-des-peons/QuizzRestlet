package fr.epsi.gl.quizz.domaine.Quizz;

import com.google.common.collect.Lists;
import fr.epsi.gl.quizz.domaine.Aggregat;
import fr.epsi.gl.quizz.domaine.question.Question;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 06/05/13
 * Time: 23:24
 * To change this template use File | Settings | File Templates.
 */
public class Quizz implements Aggregat {
    @SuppressWarnings("UnusedDeclaration")
    protected Quizz() {
    }

    Quizz(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getLibellé() {
        return libellé;
    }

    public void setLibellé(String libellé) {
        this.libellé = libellé;
    }

    public void ajouteQuestion(Question UneQuestion) {
        questions.add(UneQuestion);
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    private UUID id;
    private String libellé;
    private List<Question> questions = Lists.newArrayList();
}
