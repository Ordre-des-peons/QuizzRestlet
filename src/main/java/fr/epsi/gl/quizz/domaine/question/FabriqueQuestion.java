package fr.epsi.gl.quizz.domaine.question;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class FabriqueQuestion {

    public Question nouvelle(String libellé) {
        checkNotNull(libellé);
        Question question = new Question(UUID.randomUUID());
        question.setLibellé(libellé);
        return question;
    }
}
