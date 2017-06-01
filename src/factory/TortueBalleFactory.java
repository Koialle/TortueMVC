
package factory;

import controller.SimpleLogoController;
import model.TortueBalle;
import model.TortueSimple;
import view.VueFeuille;
import view.VueTortueBalle;

/**
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class TortueBalleFactory implements TortueFactory {

    @Override
    public TortueSimple ajouterNouvelleTortue(SimpleLogoController controller) {
        VueFeuille vueFeuille = controller.getFenetre().getFeuille();
        TortueBalle tortue = new TortueBalle();
        tortue.addObserver(vueFeuille);        
        VueTortueBalle tView = new VueTortueBalle(tortue);
        vueFeuille.ajouterTortues(tView);
        
        return tortue;
    }
}
