package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Feuille;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class VueFeuille extends JPanel implements Observer
{
    protected List<VueTortueSimple> tortues; // la liste des sous-vues
	
    public VueFeuille() {
        setBackground(Color.white);
        setSize(new Dimension(600,400));
        setPreferredSize(new Dimension(600,400));
        
        tortues = new ArrayList();
    }

    public void montrerTortues(Graphics g) {
        for (VueTortueSimple t : tortues) {
            t.dessinerTortue(g);
        }
    }

    public void ajouterTortues(VueTortueSimple o) {
        tortues.add(o);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Color c = g.getColor();
        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0,0,dim.width, dim.height);
        g.setColor(c);

        montrerTortues(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        // Test reset
        if (o instanceof Feuille && ((Feuille)o).getTortues().isEmpty()) {
            tortues.clear();
        }

        this.repaint();
    }
}
