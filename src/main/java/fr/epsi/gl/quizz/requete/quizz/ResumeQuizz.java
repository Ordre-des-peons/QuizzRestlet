package fr.epsi.gl.quizz.requete.quizz;

import org.jongo.marshall.jackson.oid.Id;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 08/05/13
 * Time: 01:08
 * To change this template use File | Settings | File Templates.
 */
public class ResumeQuizz {

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

    public String libellé;
    @Id
    public String id;
}
