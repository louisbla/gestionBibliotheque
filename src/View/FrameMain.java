package View;

import Controller.ControllerManager;
import user.Droit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class FrameMain extends JFrame {

	private static final long serialVersionUID = 1L;

	private ControllerManager ca;

	private JPanel myMenu;
	private JPanel myDesktop;

	private JButton myBtAccueil=new JButton();
	private JButton myBtLivre=new JButton();
	private JButton myBtUtilisateurs=new JButton();
	private JButton myBtEmprunt=new JButton();
	private JButton myBtProfil = new JButton();
	private JButton myBtSalle = new JButton();

	private final int ongletLargeur = 146;
	private final int ongletHauteur = 30;

	//////////////////////////////////////////////////////

	public FrameMain(ControllerManager controller) {
		this.ca=controller;

		this.setTitle("Gestionnaire de Bibliotheque");
		this.setSize(1024, 768);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel myGlobalPane = (JPanel) this.getContentPane();
		myGlobalPane.setBackground(Color.GRAY);
		getContentPane().setLayout(new BorderLayout(0, 0));

		myMenu = new JPanel();
		myMenu.setOpaque(false);

		myGlobalPane.add(myMenu, BorderLayout.NORTH);

		this.myDesktop = new JPanel();
		myDesktop.setOpaque(false);
		myDesktop.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
		myDesktop.setLayout(new BorderLayout());

		myGlobalPane.add(myDesktop);

		initMenu();
	}

	//////////////////////////////////////////////////////

	private void initMenu(){
		allEnabled();

		myBtAccueil.setText("ACCUEIL");
		myBtAccueil.setEnabled(false);
		myBtAccueil.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				allEnabled();
				myBtAccueil.setEnabled(false);

				ca.clear();
				ca.accueil();
			}
		});

		myBtUtilisateurs.setText("UTILISATEURS");
		myBtUtilisateurs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				allEnabled();
				myBtUtilisateurs.setEnabled(false);
				ca.clear();
				ca.utilisateurs();
			}

		});

		myBtSalle.setText("SALLES");
		myBtSalle.setEnabled(true);
		myBtSalle.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				allEnabled();
				myBtSalle.setEnabled(false);

				ca.clear();
				ca.salle();
			}

		});
		myMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		myMenu.add(myBtAccueil);
		
		
				myBtLivre.setText("OEUVRES");
				myBtLivre.setEnabled(true);
				myBtLivre.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						allEnabled();
						myBtLivre.setEnabled(false);

						ca.clear();
						ca.livre();
					}

				});
				myMenu.add(myBtLivre);
		
				myBtEmprunt.setText("EMPRUNTS");
				myBtEmprunt.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						allEnabled();
						myBtEmprunt.setEnabled(false);
						ca.clear();
						ca.emprunts();
					}

				});
				myMenu.add(myBtEmprunt);
		
				myBtProfil.setText("MON COMPTE");
				myBtProfil.setEnabled(true);
				myBtProfil.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						allEnabled();
						myBtProfil.setEnabled(false);

						ca.clear();
						ca.profil();
					}

				});
				myMenu.add(myBtProfil);
		myMenu.add(myBtUtilisateurs);
		myMenu.add(myBtSalle);

		refreshOnglet();
	}

		/* gestion des droits */
	public void refreshOnglet() {
		switch (ca.utilisateur.getDroit()) {
		case admin:
			myBtUtilisateurs.setVisible(true);
			myBtSalle.setVisible(true);
			break;

		case professeur:
			myBtUtilisateurs.setVisible(false);
			myBtSalle.setVisible(true);
			break;

		default:
			myBtUtilisateurs.setVisible(false);
			myBtSalle.setVisible(false);
			break;
		}
	}

	//////////////////////////////////////////////////////

	private void allEnabled() {
		myBtAccueil.setEnabled(true);
		myBtLivre.setEnabled(true);
		myBtUtilisateurs.setEnabled(true);
		myBtEmprunt.setEnabled(true);
		myBtProfil.setEnabled(true);
		myBtSalle.setEnabled(true);
	}


	//////////////////////////////////////////////////////

	public JPanel getMenuContainer() {
		return myMenu;
	}

	public JPanel getDesktopContainer() {
		return myDesktop;
	}

}
