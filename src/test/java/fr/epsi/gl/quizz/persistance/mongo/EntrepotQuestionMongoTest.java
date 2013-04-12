package fr.epsi.gl.quizz.persistance.mongo;

import fr.epsi.gl.quizz.commande.FournisseurMongoSession;
import fr.epsi.gl.quizz.domaine.question.FabriqueQuestion;
import fr.epsi.gl.quizz.domaine.question.Question;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mongolink.MongoSession;
import org.mongolink.test.MongolinkRule;

import static org.fest.assertions.Assertions.*;

public class EntrepotQuestionMongoTest {

    @Rule
    public MongolinkRule mongolinkRule = MongolinkRule.withPackage("fr.epsi.gl.quizz.persistance.mongo.mapping");

    @Before
    public void setUp()  {
        entrepot = new EntrepotQuestionMongo(new FournisseurMongoSession() {
            @Override
            public MongoSession get() {
                return mongolinkRule.getCurrentSession();
            }

            @Override
            public void nettoie() {

            }
        });
    }

    @Test
    public void peutAjouter() {
        Question question = new FabriqueQuestion().nouvelle("Les nains portent-ils des chaussettes ?");

        entrepot.ajoute(question);
        mongolinkRule.cleanSession();

        Question questionTrouvée = entrepot.get(question.getId()).orNull();
        assertThat(questionTrouvée).isNotNull();
        assertThat(questionTrouvée.getLibellé()).isEqualTo("Les nains portent-ils des chaussettes ?");
    }

    @Test
    public void peutPersisterReponses() {
        Question question = new FabriqueQuestion().nouvelle("test");
        question.ajouteRéponseJuste("ok");

        entrepot.ajoute(question);
        mongolinkRule.cleanSession();

        Question questionTrouvée = entrepot.get(question.getId()).get();

        assertThat(questionTrouvée.getRéponses()).hasSize(1);
        assertThat(questionTrouvée.getRéponses().get(0).getLibellé()).isEqualTo("ok");
        assertThat(questionTrouvée.getRéponses().get(0).isCorrecte()).isTrue();
    }

    @Test
    @Ignore
    public void peutSupprimer() {
        Question question = new FabriqueQuestion().nouvelle("Les nains portent-ils des chaussettes ?");
        entrepot.ajoute(question);

        entrepot.supprime(question);
        mongolinkRule.cleanSession();

        assertThat(entrepot.get(question.getId()).isPresent()).isFalse();
    }

    private EntrepotQuestionMongo entrepot;
}
