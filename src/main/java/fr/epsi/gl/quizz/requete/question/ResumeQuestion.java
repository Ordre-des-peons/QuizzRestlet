package fr.epsi.gl.quizz.requete.question;

import org.jongo.marshall.jackson.oid.Id;

public class ResumeQuestion {

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
