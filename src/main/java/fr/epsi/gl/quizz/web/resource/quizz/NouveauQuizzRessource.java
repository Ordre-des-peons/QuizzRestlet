package fr.epsi.gl.quizz.web.resource.quizz;

import fr.epsi.gl.quizz.web.representation.ModeleEtVue;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Created with IntelliJ IDEA.
 * User: aroche
 * Date: 08/05/13
 * Time: 00:51
 * To change this template use File | Settings | File Templates.
 */
public class NouveauQuizzRessource extends ServerResource {
    @Get
    public ModeleEtVue représente() {
        return ModeleEtVue.crée("/quizz/nouveau-quizz");
    }
}
