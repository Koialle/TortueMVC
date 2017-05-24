package model;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class TortueBalle extends TortueSimple {
    protected TortueSimple tortueSuivie;

    public TortueSimple getTortueSuivie() {
        return tortueSuivie;
    }

    public void setTortueSuivie(TortueSimple suivie) {
        this.tortueSuivie = suivie;
    }
}
