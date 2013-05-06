package fr.epsi.gl.quizz.persistance.fake;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import fr.epsi.gl.quizz.domaine.Quizz.EntrepotQuizz;
import fr.epsi.gl.quizz.domaine.Quizz.Quizz;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 06/05/13
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class FakeEntrepotQuizz implements EntrepotQuizz {
    @Override
    public Optional<Quizz> get(Object id) {
        for (Quizz quizz : DesQuizz) {
            if(quizz.getId().equals(id)) {
                return Optional.of(quizz);
            }
        }
        return Optional.absent();
    }

    @Override
    public Quizz ajoute(Quizz aggregat) {
        DesQuizz.add(aggregat);
        return aggregat;
    }

    @Override
    public void supprime(Quizz aggregat) {
        DesQuizz.remove(aggregat);
    }

    private List<Quizz> DesQuizz = Lists.newArrayList();
}
