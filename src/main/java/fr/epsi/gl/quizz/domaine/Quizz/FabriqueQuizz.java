package fr.epsi.gl.quizz.domaine.Quizz;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 06/05/13
 * Time: 23:24
 * To change this template use File | Settings | File Templates.
 */

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class FabriqueQuizz {

    public Quizz nouvelle(String libellé) {
        checkNotNull(libellé);
        Quizz quizz = new Quizz(UUID.randomUUID());
        quizz.setLibellé(libellé);
        return quizz;
    }
}
