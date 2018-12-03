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
    private JButton myBtCategorie=new JButton();
    private JButton myBtAuteur=new JButton();
    private JButton myBtLivre=new JButton();
    private JButton myBtUtilisateurs=new JButton();
    private JButton myBtEmprunt=new JButton();
    private JButton myBtReparation=new JButton();

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
        myBtAccueil.setText("Accueil");
        myBtAccueil.setBounds(0, 0, 146, 30);
        myBtAccueil.setEnabled(false);
        
        myBtUtilisateurs.setText("Utilisateur");
        myBtUtilisateurs.setBounds(146, 0, 146, 30);
        myBtUtilisateurs.setEnabled(true);
        
        myBtAccueil.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	allEnabled();
                myBtAccueil.setEnabled(false);
                ca.accueil();
            }
        });
        
        myBtUtilisateurs.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	allEnabled();
                myBtUtilisateurs.setEnabled(false);
                ca.utilisateurs();
            }

        });

        myMenu.add(myBtAccueil);
        myMenu.add(myBtUtilisateurs);
    }

    //////////////////////////////////////////////////////
    
    private void allEnabled() {
    	myBtAccueil.setEnabled(true);
        myBtCategorie.setEnabled(true);
        myBtAuteur.setEnabled(true);
        myBtLivre.setEnabled(true);
        myBtUtilisateurs.setEnabled(true);
        myBtEmprunt.setEnabled(true);
        myBtReparation.setEnabled(true);
    }
    //////////////////////////////////////////////////////

    public JPanel getMenuContainer() {
        return myMenu;
    }

    public JPanel getDesktopContainer() {
        return myDesktop;
    }

}
