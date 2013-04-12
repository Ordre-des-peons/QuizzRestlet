package fr.epsi.gl.quizz.web.configuration;

import com.mongodb.MongoClient;
import org.jongo.Jongo;

import javax.inject.Provider;
import java.net.UnknownHostException;

public class JongoProvider implements Provider<Jongo> {
    @Override
    public Jongo get() {
        try {
            return new Jongo(new MongoClient().getDB("quizz"));
        } catch (UnknownHostException e) {
            throw new UnknownError("Impossible de démarrer jongo");
        }
    }
}
