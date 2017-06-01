
package factory;

import controller.SimpleLogoController;
import model.Feuille;
import model.TortueAmelioree;
import model.TortueSimple;
import view.SimpleLogoView;
import view.VueFeuille;
import view.VueTortueAmelioree;
import view.VueTortueSimple;

/**
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class TortueAmelioreeFactory implements TortueFactory {

    @Override
    public TortueSimple ajouterNouvelleTortue(SimpleLogoController controller) {
        SimpleLogoView fenetre = controller.getFenetre();
        VueFeuille vueFeuille = fenetre.getFeuille();
        Feuille feuille = controller.getFeuille();
        
        TortueAmelioree tortue = new TortueAmelioree(fenetre.getTortueName());
        tortue.addObserver(vueFeuille); // After loop ?
        VueTortueSimple tView = new VueTortueAmelioree(tortue);
        vueFeuille.ajouterTortues(tView);
        
        for (TortueSimple t : feuille.getTortues()) {
            tortue.ajouterTortue(t);
            
            if (t instanceof TortueAmelioree) {
                ((TortueAmelioree)t).ajouterTortue(tortue);
            }
        }
        
        return tortue;
    }
}
