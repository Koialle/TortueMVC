
package factory;

import controller.SimpleLogoController;
import model.TortueSimple;
import view.VueFeuille;
import view.VueTortueSimple;

/**
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class TortueSimpleFactory implements TortueFactory {

    @Override
    public TortueSimple ajouterNouvelleTortue(SimpleLogoController controller) {
        VueFeuille vueFeuille = controller.getFenetre().getFeuille();
        TortueSimple tortue = new TortueSimple();
        tortue.addObserver(vueFeuille);        
        VueTortueSimple tView = new VueTortueSimple(tortue);
        vueFeuille.ajouterTortues(tView);
        
        return tortue;
    }
}
