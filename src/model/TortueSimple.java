
package model;

import java.util.Observable;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */

public class TortueSimple extends Observable {

    protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
    protected int x = 0, y = 0;
    protected int direction = -90;
    protected int couleur = 0;

    public TortueSimple() {
        reset();
        x = 500/2;
        y = 400/2;
    }

    public void reset() {
        x = 0;
        y = 0;
        direction = -90;
        couleur = 0;
        this.setChanged();
        this.notifyObservers();
    }

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
        this.setChanged();
        this.notifyObservers();
    }

    public void avancer(int dist) {
        int newX = (int) Math.round(x+dist*Math.cos(ratioDegRad * direction));
        int newY = (int) Math.round(y+dist*Math.sin(ratioDegRad * direction));
        x = newX;
        y = newY;
        this.setChanged();
        this.notifyObservers();
    }

    public void droite(int ang) {
        direction = (direction + ang) % 360;
        this.setChanged();
        this.notifyObservers();
    }

    public void gauche(int ang) {
        direction = (direction - ang) % 360;
        this.setChanged();
        this.notifyObservers();
    }

    public void changementCouleur(int n) {
        couleur = n % 12;
        this.setChanged();
        this.notifyObservers();
    }

    public void couleurSuivante() {
        changementCouleur(couleur + 1);
        this.setChanged();
        this.notifyObservers();
    }

    public static double getRatioDegRad() {
        return ratioDegRad;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public int getCouleur() {
        return couleur;
    }
    
    public void setCouleur(int n) {
        this.couleur = n;
        this.setChanged();
        this.notifyObservers();
    }
}
