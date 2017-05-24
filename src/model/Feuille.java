
package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class Feuille extends Observable {
    
    private ArrayList<TortueSimple> tortues; // la liste des tortues enregistrees
    private TortueSimple tortueCourante;
	
    public Feuille() {
        tortues = new ArrayList();
        tortueCourante = new TortueSimple();
        ajouterTortue(tortueCourante);
    }
    
    public Feuille(TortueSimple courante) {
        tortues = new ArrayList();
        this.tortueCourante = courante;
        ajouterTortue(courante);
    }
    
    public TortueSimple getTortue(int x, int y){
        for (TortueSimple t : tortues) {
            if ((x - 15 < t.getX()) && (t.getX() < x + 15) && (y - 15 < t.getY()) &&(t.getY() < y + 15)){
                return t;
            }
        }
        return tortueCourante;
    }

    public void ajouterTortue(TortueSimple o) {
        if (!tortues.contains(o)) {
            tortues.add(o);
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void effacer() {
        tortues.clear();
        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<TortueSimple> getTortues() {
        return tortues;
    }

    public TortueSimple getTortueCourante() {
        return tortueCourante;
    }

    public void setTortueCourante(TortueSimple courante) {
        this.tortueCourante = courante;
    }
}
