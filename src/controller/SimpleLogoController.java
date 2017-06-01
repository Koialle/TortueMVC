
package controller;

import factory.TortueFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import model.Feuille;
import model.TortueSimple;
import view.VueFeuille;
import view.SimpleLogoView;
import view.VueTortueSimple;

/**
 * Class Main = Controlleur
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class SimpleLogoController implements MouseListener {

    private static SimpleLogoView fenetre = null;
    private Feuille feuille = null;
    private VueFeuille vueFeuille = null;
    private VueTortueSimple vueTortueCourante = null;

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                SimpleLogoController controller = new SimpleLogoController();
            }
        });
    }
    
    public SimpleLogoController() {
        // Modèle
        TortueSimple courante = new TortueSimple();
        feuille = new Feuille(courante);

        // Views
        vueFeuille = new VueFeuille();
        vueFeuille.addMouseListener(this);
        vueTortueCourante = new VueTortueSimple(courante);
        vueFeuille.ajouterTortues(vueTortueCourante);

        // Add listeners
        feuille.addObserver(vueFeuille);
        courante.addObserver(vueFeuille);
        
        fenetre = new SimpleLogoView(this, vueFeuille);
    }

    public void reinitialiserTortueCourante()
    {
        getCourante().reset();
    }

    public void changerCouleur(int n) {
        getCourante().setCouleur(n);
    }
    
    public void changerPosition(int x, int y) {
        getCourante().setPosition(x, y);
    }
    
    public void quitter() {
        System.exit(0);
    }
    
    public void avancer(int v) {
        getCourante().avancer(v);
    }
    
    public void droite(int v) {
        getCourante().droite(v);
    }
    
    public void gauche(int v) {
        getCourante().gauche(v);
    }

    public void reinitialiserFeuille() {
        feuille.effacer();
    }
    
    public SimpleLogoView getFenetre() {
        return fenetre;
    }
    
    public Feuille getFeuille() {
        return feuille;
    }
    
    // Temporary while no tortueController
    protected TortueSimple getCourante()
    {
        return feuille.getTortueCourante();
    }
    
    protected void setCourante(TortueSimple tortue)
    {
        feuille.ajouterTortue(tortue);
        feuille.setTortueCourante(tortue);
    }
    
    public void ajouterTortue(TortueFactory factory)
    {
        TortueSimple tortue = factory.ajouterNouvelleTortue(this);
        setCourante(tortue);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        TortueSimple tortue = feuille.getTortue(x, y);
        feuille.setTortueCourante(tortue);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
