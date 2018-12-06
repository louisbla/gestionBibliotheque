package View;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.BorderLayout;
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
		setLayout(new BorderLayout());
		this.cm = controller;

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(buttonPanel, BorderLayout.SOUTH);

		Button buttonSolde = new Button("Ajouter Solde");
		buttonSolde.setBounds(200, 550, 150, 25);
		buttonPanel.add(buttonSolde);

		Button button = new Button("Deconnexion");
		button.setBounds(400, 550, 150, 25);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cm.logout();
				controller.clear();
				controller.profil();
			}
		});
		buttonPanel.add(button);

		JPanel titlePanel = new JPanel();

		JLabel titleLabel = new JLabel();
		titleLabel.setBorder(new EmptyBorder(10, 10, 10 ,10));
		titleLabel.setFont(new Font(null, 0, 20));
		titleLabel.setText("PROFIL UTILISATEUR");
		titlePanel.add(titleLabel);
		add(titlePanel, )

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10 ,10));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		//--------------------------------Identifiant--------------------------------------

		JTextPane txtpnIdentifiant = new JTextPane();
		txtpnIdentifiant.setBounds(32, 42, 72, 22);
		panel.add(txtpnIdentifiant);
		txtpnIdentifiant.setText("Identifiant :");

		textFieldIdentifiant = new JTextField();
		textFieldIdentifiant.setEditable(false);
		textFieldIdentifiant.setBounds(197, 42, 116, 22);
		textFieldIdentifiant.setText(ControllerManager.utilisateur.getIdentification());
		panel.add(textFieldIdentifiant);
		textFieldIdentifiant.setColumns(10);

		//--------------------------------Nom--------------------------------------

		JTextPane txtpnNom = new JTextPane();
		txtpnNom.setText("Nom :");
		txtpnNom.setBounds(32, 95, 72, 22);
		panel.add(txtpnNom);

		textFieldNom = new JTextField();
		textFieldNom.setEditable(false);
		textFieldNom.setBounds(197, 95, 116, 22);
		textFieldNom.setText(ControllerManager.utilisateur.getNom());
		panel.add(textFieldNom);
		textFieldNom.setColumns(10);

		//--------------------------------Prénom--------------------------------------

		JTextPane txtpnPrnom = new JTextPane();
		txtpnPrnom.setText("Pr\u00E9nom :");
		txtpnPrnom.setBounds(32, 130, 72, 22);
		panel.add(txtpnPrnom);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setEditable(false);
		textFieldPrenom.setBounds(197, 130, 116, 22);
		textFieldPrenom.setText(ControllerManager.utilisateur.getPrenom());
		panel.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		//--------------------------------Statut--------------------------------------

		JTextPane txtpnStatut = new JTextPane();
		txtpnStatut.setText("Statut :");
		txtpnStatut.setBounds(32, 203, 99, 22);
		panel.add(txtpnStatut);

		textFieldStatut = new JTextField();
		textFieldStatut.setEditable(false);
		textFieldStatut.setBounds(197, 203, 116, 22);
		textFieldStatut.setText(ControllerManager.utilisateur.getDroit().toString());
		panel.add(textFieldStatut);
		textFieldStatut.setColumns(10);

		//--------------------------------Solde--------------------------------------

		JTextPane txtpnSolde = new JTextPane();
		txtpnSolde.setText("Solde :");
		txtpnSolde.setBounds(32, 252, 72, 22);
		panel.add(txtpnSolde);

		textFieldSolde = new JTextField();
		textFieldSolde.setEditable(false);
		textFieldSolde.setBounds(197, 252, 116, 22);
		textFieldSolde.setText(Float.toString(ControllerManager.utilisateur.getSolde()));
		panel.add(textFieldSolde);
		textFieldSolde.setColumns(10);
	}
}
