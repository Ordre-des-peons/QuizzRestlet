package fr.epsi.gl.quizz.domaine.question;

import com.google.common.collect.Lists;
import fr.epsi.gl.quizz.domaine.Aggregat;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Question implements Aggregat{

    @SuppressWarnings("UnusedDeclaration")
    protected Question() {
    }

    Question(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getLibellé() {
        return libellé;
    }

    public void setLibellé(String libellé) {
        this.libellé = libellé;
    }

    public Reponse ajouteRéponseFausse(String libellé) {
        Reponse réponse = Reponse.fausse(libellé);
        réponses.add(réponse);
        return réponse;
    }

    public List<Reponse> getRéponses() {
        return Collections.unmodifiableList(réponses);
    }

    public Reponse ajouteRéponseJuste(String libellé) {
        Reponse réponse = Reponse.juste(libellé);
        réponses.add(réponse);
        return réponse;
    }

    private UUID id;
    private String libellé;
    private List<Reponse> réponses = Lists.newArrayList();


}
