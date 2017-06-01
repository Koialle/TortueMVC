
package factory;

import controller.SimpleLogoController;
import model.TortueSimple;

/**
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public interface TortueFactory {
    public TortueSimple ajouterNouvelleTortue(SimpleLogoController controller);
}
