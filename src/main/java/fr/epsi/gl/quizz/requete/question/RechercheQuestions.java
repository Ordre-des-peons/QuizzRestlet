package fr.epsi.gl.quizz.requete.question;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.jongo.Jongo;

import java.util.List;
import java.util.UUID;

public class RechercheQuestions {

    @Inject
    public RechercheQuestions(Jongo jongo) {
        this.jongo = jongo;
    }

    public List<ResumeQuestion> toutes() {
        return Lists.newArrayList(jongo.getCollection("question").find().projection("{_id:1, libellé : 1}").as(ResumeQuestion.class));
    }

    public DetailsQuestion detailsDe(UUID id) {
        return jongo.getCollection("question").findOne("{_id:#}", id).as(DetailsQuestion.class);
    }


    private Jongo jongo;
}
