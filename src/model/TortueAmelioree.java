package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */

public class TortueAmelioree extends TortueSimple {
    protected String nom;
    protected List<TortueSimple> tortuesConnues;
    
    public TortueAmelioree(String nom) {
        this.nom = nom;
        this.tortuesConnues = new ArrayList();
    }

    public TortueAmelioree() {
        this("Inconnue");
    }

    public String getNom() {
        return nom;
    }
    
    public void ajouterTortue(TortueSimple tortue) {
        this.tortuesConnues.add(tortue);
    }
    
    public void enleverTortue(TortueSimple tortue) {
        this.tortuesConnues.remove(tortue);
    }
    
    public void reinitialiserTortues() {
        this.tortuesConnues.clear();
    }

    public List<TortueSimple> getTortuesConnues() {
        return tortuesConnues;
    }

    public double getDistanceEuclidienne(TortueSimple tortue) {
        return Math.sqrt(Math.pow(this.getX() - tortue.getX(), 2) + Math.pow(this.getY() - tortue.getY(), 2));
    }
    
    @Override
    public void avancer(int dist) {
        super.avancer(dist);
        for (TortueSimple tortue : tortuesConnues) {
            if (tortue instanceof TortueAmelioree && this.getDistanceEuclidienne(tortue) < 15) {
                System.out.println("> " + nom + " : Salut " + ((TortueAmelioree)tortue).getNom());
                tortue.avancer(dist);
            }
        }
    }
}
