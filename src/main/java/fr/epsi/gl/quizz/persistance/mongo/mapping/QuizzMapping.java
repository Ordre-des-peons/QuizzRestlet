package fr.epsi.gl.quizz.persistance.mongo.mapping;

import fr.epsi.gl.quizz.domaine.Quizz.Quizz;
import org.mongolink.domain.mapper.AggregateMap;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 10/05/13
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnusedDeclaration")
public class QuizzMapping extends AggregateMap<Quizz> {

    public QuizzMapping() {
        super(Quizz.class);
    }

    @Override
    protected void map() {
        id(element().getId()).natural();
        property(element().getLibellé());
        collection(element().getQuestions());
    }
}
