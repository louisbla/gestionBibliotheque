package View;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.Button;

import Controller.ControllerManager;
import DAO.DBManager;
import user.Droit;
import javax.swing.JLabel;

public class PanelProfil extends JPanel {
	
	private ControllerManager cm;
	
	private JTextField textFieldIdentifiant;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldStatut;
	private JTextField textFieldSolde;

	/**
	 * Create the panel.
	 */
	public PanelProfil(ControllerManager controller) {
		setLayout(null);
		this.cm = controller;
		

		JPanel panelUtilisateur = new JPanel();
		panelUtilisateur.setBounds(12, 13, 898, 603);
		add(panelUtilisateur);
		panelUtilisateur.setLayout(null);

		Button buttonSolde = new Button("Ajouter Solde");
		buttonSolde.setBounds(564, 135, 105, 24);
		panelUtilisateur.add(buttonSolde);

		Button button = new Button("Deconnexion");
		button.setBounds(564, 254, 105, 24);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cm.logout();
				controller.clear();
				controller.profil();
			}
		});
		panelUtilisateur.add(button);

		JTextPane txtpnProfilUtilisateur = new JTextPane();
		txtpnProfilUtilisateur.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtpnProfilUtilisateur.setText("Profil Utilisateur");
		txtpnProfilUtilisateur.setBounds(169, 28, 227, 51);
		panelUtilisateur.add(txtpnProfilUtilisateur);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(62, 110, 440, 303);
		panelUtilisateur.add(panel);
		panel.setLayout(null);

		JTextPane txtpnIdentifiant = new JTextPane();
		txtpnIdentifiant.setBounds(32, 42, 72, 22);
		panel.add(txtpnIdentifiant);
		txtpnIdentifiant.setText("Identifiant :");

		JTextPane txtpnNom = new JTextPane();
		txtpnNom.setText("Nom :");
		txtpnNom.setBounds(32, 95, 72, 22);
		panel.add(txtpnNom);

		JTextPane txtpnPrnom = new JTextPane();
		txtpnPrnom.setText("Pr\u00E9nom :");
		txtpnPrnom.setBounds(32, 130, 72, 22);
		panel.add(txtpnPrnom);

		JTextPane txtpnStatut = new JTextPane();
		txtpnStatut.setText("Statut :");
		txtpnStatut.setBounds(32, 203, 99, 22);
		panel.add(txtpnStatut);

		JTextPane txtpnSolde = new JTextPane();
		txtpnSolde.setText("Solde :");
		txtpnSolde.setBounds(32, 252, 72, 22);
		panel.add(txtpnSolde);

		textFieldIdentifiant = new JTextField();
		textFieldIdentifiant.setEditable(false);
		textFieldIdentifiant.setBounds(197, 42, 116, 22);
		textFieldIdentifiant.setText(ControllerManager.utilisateur.getIdentification());
		panel.add(textFieldIdentifiant);
		textFieldIdentifiant.setColumns(10);

		textFieldNom = new JTextField();
		textFieldNom.setEditable(false);
		textFieldNom.setBounds(197, 95, 116, 22);
		textFieldNom.setText(ControllerManager.utilisateur.getNom());
		panel.add(textFieldNom);
		textFieldNom.setColumns(10);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setEditable(false);
		textFieldPrenom.setBounds(197, 130, 116, 22);
		textFieldPrenom.setText(ControllerManager.utilisateur.getPrenom());
		panel.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		textFieldStatut = new JTextField();
		textFieldStatut.setEditable(false);
		textFieldStatut.setBounds(197, 203, 116, 22);
		textFieldStatut.setText(ControllerManager.utilisateur.getDroit().toString());
		panel.add(textFieldStatut);
		textFieldStatut.setColumns(10);

		textFieldSolde = new JTextField();
		textFieldSolde.setEditable(false);
		textFieldSolde.setBounds(197, 252, 116, 22);
		textFieldSolde.setText(Float.toString(ControllerManager.utilisateur.getSolde()));
		panel.add(textFieldSolde);
		textFieldSolde.setColumns(10);

	}
}
