
package view;

import controller.SimpleLogoController;
import factory.TortueAmelioreeFactory;
import factory.TortueBalleFactory;
import factory.TortueSimpleFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class SimpleLogoView extends JFrame implements ActionListener {
    /**
     * - La vue peut faire des requêtes d'état sur le modèle, signifie qu'elle peut avoir une dépendance vers le modèle. Slide 4/10 rappel MVC
     * - Chaque vue est associée à un controlleur, et chaque controleur est associé à une vue (écouteur d'évènements)
     * - Chaque controleur connait le modèle
     * - Le modèle ne connait ni la vue, ni le controlleur
     * Exemple MVC : http://baptiste-wicht.developpez.com/tutoriels/conception/mvc/
     */
    private final SimpleLogoController controller;

    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);

    private JTextField champDegree;
    private JTextField champNom;
    private JComboBox listeCouleurs;
    private JComboBox listeTortues;
    
    private VueFeuille feuille;

    public JTextField getChampDegree() {
        return champDegree;
    }
    
    public String getTortueName() {
        return champNom.getText();
    }

    public JComboBox getListeCouleurs() {
        return listeCouleurs;
    }

    public VueFeuille getFeuille() {
        return feuille;
    }

    public SimpleLogoView(SimpleLogoController controller, VueFeuille feuille) {
        super("un logo tout simple");
        this.controller = controller;
        this.feuille = feuille;
        
        this.initialisationLogo();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }

    public void initialisationLogo() {
        getContentPane().setLayout(new BorderLayout(10,10));

        // Boutons
        JToolBar toolBar = new JToolBar();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel,"North");

        ajouterBouton(toolBar,"Effacer","Nouveau dessin","/icons/index.png");

        toolBar.add(Box.createRigidArea(HGAP));
        champDegree = new JTextField("45",5);
        champNom = new JTextField("Nom",10);
        toolBar.add(champDegree);
        ajouterBouton(toolBar, "Avancer", "Avancer 50", null);
        ajouterBouton(toolBar, "Gauche", "Gauche 45", null);
        ajouterBouton(toolBar, "Droite", "Droite 45", null);
        
        toolBar.add(champNom);
        String[] colorStrings = {"noir", "bleu", "cyan","gris fonce","rouge",
                                "vert", "gris clair", "magenta", "orange",
                                "gris", "rose", "jaune"};
        String[] typeTortues = {"Tortue classique","Tortue améliorée","Tortue balle"};

        // Create the combo box
        toolBar.add(Box.createRigidArea(HGAP));
        JLabel colorLabel = new JLabel("   Couleur: ");
        toolBar.add(colorLabel);
        listeCouleurs = new JComboBox(colorStrings);
        listeTortues = new JComboBox(typeTortues);
        toolBar.add(listeCouleurs);
        toolBar.add(listeTortues);        
        ajouterBouton(toolBar, "Ajouter", "Ajouter tortue", null);

        listeCouleurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                int n = cb.getSelectedIndex();
                controller.changerCouleur(n);
            }
        });

        // Menus
        JMenuBar menubar=new JMenuBar();
        setJMenuBar(menubar);	// on installe le menu bar
        JMenu menuFile=new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        ajouterObjetMenu(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
        ajouterObjetMenu(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        JMenu menuCommandes=new JMenu("Commandes"); // on installe le premier menu
        menubar.add(menuCommandes);
        ajouterObjetMenu(menuCommandes, "Avancer", "Avancer", -1);
        ajouterObjetMenu(menuCommandes, "Gauche", "Gauche", -1);
        ajouterObjetMenu(menuCommandes, "Droite", "Droite", -1);
        ajouterObjetMenu(menuCommandes, "Ajouter", "Ajouter une tortue", -1);

        JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
        menubar.add(menuHelp);
        ajouterObjetMenu(menuHelp, "Aide", "Help", -1);
        ajouterObjetMenu(menuHelp, "A propos", "About", -1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // la feuille de dessin
        getContentPane().add(feuille, "Center");

        pack();
        setVisible(true);
    }

    /** la gestion des actions des boutons
     * @param e */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String c = e.getActionCommand();

        // actions des boutons du haut
        if (c.equals("Avancer")) {
            try {
                int v = Integer.parseInt(champDegree.getText());
                controller.avancer(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + champDegree.getText());
            }
        } else if (c.equals("Droite")) {
            try {
                int v = Integer.parseInt(champDegree.getText());
                controller.droite(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + champDegree.getText());
            }
        } else if (c.equals("Gauche")) {
            try {
                int v = Integer.parseInt(champDegree.getText());
                controller.gauche(v);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + champDegree.getText());
            }
        } else if (c.equals("Ajouter")) {
            int n = listeCouleurs.getSelectedIndex();
            int typeTortue = listeTortues.getSelectedIndex();
            switch (typeTortue) {
                case 0 :
                    controller.ajouterTortue(new TortueSimpleFactory());
                    break;
                case 1 :
                    controller.ajouterTortue(new TortueAmelioreeFactory());
                    break;
                case 2 : 
                    controller.ajouterTortue(new TortueBalleFactory());
                    break;
                
            }
            controller.changerCouleur(n);
        } else if (c.equals("Effacer")) {
            this.effacer();
        } else if (c.equals("Quitter")) {
            controller.quitter();
        }

        feuille.repaint();
    }

    // efface tout et reinitialise la feuille
    public void effacer() {
        controller.reinitialiserFeuille();
        // Replace la tortue au centre
        Dimension size = feuille.getSize();
        controller.changerPosition(size.width/2, size.height/2);
    }

    //utilitaires pour installer des boutons et des menus
    public void ajouterBouton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton)p.add(new JButton(name));
        } else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon (u);
                b = (JButton) p.add(new JButton(im));
            } else {
                b = (JButton) p.add(new JButton(name));
            }
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0,0,0,0));
        b.addActionListener(this);
    }

    public void ajouterObjetMenu(JMenu m, String label, String command, int key) {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(this);
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE) {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            } else {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
            }
        }
    }

    public void ajouterTortue(VueTortueSimple tortue) {
        feuille.ajouterTortues(tortue);
    }

    public void ajouterFeuilleDessin(VueFeuille feuilleView) {
        feuille = feuilleView;
        getContentPane().add(feuille,"Center");
    }
}
