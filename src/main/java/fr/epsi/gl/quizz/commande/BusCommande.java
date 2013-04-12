package fr.epsi.gl.quizz.commande;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import javax.inject.Inject;
import java.util.Map;

public class BusCommande {

    @Inject
    public BusCommande(FournisseurMongoSession fournisseurSession) {
        this.fournisseurSession = fournisseurSession;
    }

    public <U> ListenableFuture<U> envoie(Message message) {
        try {
            return Futures.immediateFuture((U) trouveHandler(message.getClass()).execute(message));
        } finally {
            fournisseurSession.nettoie();
        }
    }

    private HandlerCommande trouveHandler(Class<?> typeCommande) {
       return handlers.get(typeCommande);
    }

    public void enregistreHandler(HandlerCommande handler) {
       handlers.put(handler.typeCommande(), handler);
    }

    private final Map<Class, HandlerCommande> handlers = Maps.newConcurrentMap();
    private FournisseurMongoSession fournisseurSession;
}
