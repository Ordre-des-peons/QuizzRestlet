package fr.epsi.gl.quizz.requete.quizz;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.jongo.Jongo;

import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 08/05/13
 * Time: 01:06
 * To change this template use File | Settings | File Templates.
 */
public class RechercheQuizzs {

    @Inject
    public RechercheQuizzs(Jongo jongo) {
        this.jongo = jongo;
    }

    public List<ResumeQuizz> toutes() {
        return Lists.newArrayList(jongo.getCollection("quizz").find().projection("{_id:1, libellé : 1}").as(ResumeQuizz.class));
    }

    public DetailsQuizz detailsDe(UUID id) {
        return jongo.getCollection("quizz").findOne("{_id:#}", id).as(DetailsQuizz.class);
    }


    private Jongo jongo;
}
