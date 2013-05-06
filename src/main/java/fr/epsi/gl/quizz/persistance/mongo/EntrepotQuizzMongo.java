package fr.epsi.gl.quizz.persistance.mongo;

import com.google.common.base.Optional;
import fr.epsi.gl.quizz.commande.FournisseurMongoSession;
import fr.epsi.gl.quizz.domaine.Quizz.EntrepotQuizz;
import fr.epsi.gl.quizz.domaine.Quizz.Quizz;
import org.mongolink.MongoSession;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 06/05/13
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
public class EntrepotQuizzMongo implements EntrepotQuizz {
    public EntrepotQuizzMongo(FournisseurMongoSession fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Override
    public Optional<Quizz> get(Object id) {
        MongoSession session = fournisseur.get();
        return Optional.fromNullable(session.get(id, Quizz.class));
    }

    @Override
    public Quizz ajoute(Quizz aggregat) {
        fournisseur.get().save(aggregat);
        return aggregat;
    }

    @Override
    public void supprime(Quizz aggregat) {
        fournisseur.get().delete(aggregat);
    }

    private FournisseurMongoSession fournisseur;
}
