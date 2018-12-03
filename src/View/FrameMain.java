package View;

import Controller.ControllerManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameMain extends JFrame {

    private static final long serialVersionUID = 1L;

    private ControllerManager ca;

    private JPanel myMenu;
    private JPanel myDesktop;
    private JPanel panelUtilisateurs;

    private JButton myBtAccueil=new JButton();
    private JButton myBtLivre=new JButton();
    private JButton myBtUtilisateurs=new JButton();
    private JButton myBtEmprunt=new JButton();
    
    private final int ongletLargeur = 146;
    private final int ongletHauteur = 30;

    //////////////////////////////////////////////////////

    public FrameMain(ControllerManager controller) {
        this.ca=controller;

        this.setTitle("Gestionnaire de Bibliotheque");
        this.setSize(1024, 768);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel myGlobalPane = (JPanel) this.getContentPane();
        myGlobalPane.setBackground(Color.GRAY);
        myGlobalPane.setLayout(null);

        myMenu = new JPanel();
        myMenu.setOpaque(false);
        myMenu.setLayout(null);
        myMenu.setBounds(0, 0, 1018, 30);

        myGlobalPane.add(myMenu);

        this.myDesktop = new JPanel();
        myDesktop.setOpaque(false);
        myDesktop.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        myDesktop.setLayout(new BorderLayout());
        myDesktop.setBounds(10, 40, 998, 690);

        myGlobalPane.add(myDesktop);

        initMenu();
    }

    //////////////////////////////////////////////////////

    private void initMenu(){
    	allEnabled();
    	
        myBtAccueil.setText("Accueil");
        myBtAccueil.setBounds(0, 0, ongletLargeur, ongletHauteur);
        myBtAccueil.setEnabled(false);
        myBtAccueil.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	allEnabled();
                myBtAccueil.setEnabled(false);
                myBtCategorie.setEnabled(true);
                myBtAuteur.setEnabled(true);
                myBtLivre.setEnabled(true);
                myBtAdherent.setEnabled(true);
                myBtEmprunt.setEnabled(true);
                myBtReparation.setEnabled(true);

                ca.clear();
                
                ca.accueil();
            }
        });
        
        myBtUtilisateurs.setText("Utilisateur");
        myBtUtilisateurs.setBounds(ongletLargeur*2, 0, ongletLargeur, ongletHauteur);
        myBtUtilisateurs.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	allEnabled();
                myBtUtilisateurs.setEnabled(false);
                ca.clear();
                ca.utilisateurs();
            }

        });

        myBtEmprunt.setText("Emprunts");
        myBtEmprunt.setBounds(ongletLargeur*3, 0, ongletLargeur, ongletHauteur);
        myBtEmprunt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	allEnabled();
                myBtEmprunt.setEnabled(false);
                ca.clear();
                ca.emprunts();
            }

        });
        
        myMenu.add(myBtAccueil);
        myMenu.add(myBtUtilisateurs);
        myMenu.add(myBtEmprunt);
        
        myBtLivre.setText("Livres");
        myBtLivre.setBounds(147, 0, 146, 30);
        myBtLivre.setEnabled(true);
        myBtLivre.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                myBtAccueil.setEnabled(true);
                myBtCategorie.setEnabled(true);
                myBtAuteur.setEnabled(true);
                myBtLivre.setEnabled(false);
                myBtAdherent.setEnabled(true);
                myBtEmprunt.setEnabled(true);
                myBtReparation.setEnabled(true);

                ca.clear();
                
                ca.livre();
            }

        });

        myMenu.add(myBtLivre);
    }

    //////////////////////////////////////////////////////
    
    private void allEnabled() {
    	myBtAccueil.setEnabled(true);
        myBtLivre.setEnabled(true);
        myBtUtilisateurs.setEnabled(true);
        myBtEmprunt.setEnabled(true);
    }
    
    //////////////////////////////////////////////////////

    public JPanel getMenuContainer() {
        return myMenu;
    }

    public JPanel getDesktopContainer() {
        return myDesktop;
    }

}
