
package view;

import java.awt.Graphics;
import model.TortueAmelioree;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class VueTortueAmelioree extends VueTortueSimple {
    
    public VueTortueAmelioree(TortueAmelioree tortue) {
        super(tortue);
    }

    @Override
    public void dessinerTortue(Graphics graphics) {
        super.dessinerTortue(graphics);
        
        if (graphics == null) return;
        
        graphics.drawString(((TortueAmelioree)tortue).getNom(), tortue.getX() + 10, tortue.getY() + 10);
    }
}
