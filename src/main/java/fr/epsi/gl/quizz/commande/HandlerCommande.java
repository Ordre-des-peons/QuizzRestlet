package fr.epsi.gl.quizz.commande;


public interface HandlerCommande<T extends Message> {

    public Object execute(T commande);

    public Class<T> typeCommande();
}
