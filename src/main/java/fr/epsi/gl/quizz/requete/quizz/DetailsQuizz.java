package fr.epsi.gl.quizz.requete.quizz;

import com.google.common.collect.Lists;
import fr.epsi.gl.quizz.requete.question.DetailsQuestion;
import org.jongo.marshall.jackson.oid.Id;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 08/05/13
 * Time: 01:00
 * To change this template use File | Settings | File Templates.
 */
public class DetailsQuizz {

    public String getLibellé() {
        return libellé;
    }

    public void setLibellé(String libellé) {
        this.libellé = libellé;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DetailsQuestion> getRéponses() {
        return questions;
    }

    public void setQuestions(List<DetailsQuestion> questions) {
        this.questions = questions;
    }

    private String libellé;
    @Id
    private String id;
    private List<DetailsQuestion> questions = Lists.newArrayList();
}
