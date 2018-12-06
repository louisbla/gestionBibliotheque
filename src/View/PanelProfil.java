package View;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.FlowLayout;

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

		JButton buttonSolde = new JButton("Ajouter Solde");
		buttonSolde.setBounds(200, 550, 150, 25);
		buttonPanel.add(buttonSolde);

		JButton button = new JButton("Deconnexion");
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
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel titleLabel = new JLabel();
		titleLabel.setBorder(new EmptyBorder(10, 10, 0 ,10));
		titleLabel.setFont(new Font(null, 0, 20));
		titleLabel.setText("PROFIL UTILISATEUR");
		titlePanel.add(titleLabel);
		add(titlePanel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10 ,10));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		//--------------------------------Profil--------------------------------------

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{60, 116, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		JLabel codeLabel = new JLabel("Code permanent : " + ControllerManager.utilisateur.getIdentification());
		GridBagConstraints gbc_codeLabel = new GridBagConstraints();
		gbc_codeLabel.anchor = GridBagConstraints.WEST;
		gbc_codeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_codeLabel.gridx = 0;
		gbc_codeLabel.gridy = 1;
		panel_2.add(codeLabel, gbc_codeLabel);

		JLabel nameLabel = new JLabel("Nom : " + ControllerManager.utilisateur.getNom());
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.WEST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 2;
		panel_2.add(nameLabel, gbc_nameLabel);

		JLabel firstNameLabel = new JLabel("Pr�nom : " + ControllerManager.utilisateur.getPrenom());
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.WEST;
		gbc_firstNameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_firstNameLabel.gridx = 0;
		gbc_firstNameLabel.gridy = 3;
		panel_2.add(firstNameLabel, gbc_firstNameLabel);

		JLabel statusLabel = new JLabel("Statut : " + ControllerManager.utilisateur.getDroit().toString());
		GridBagConstraints gbc_statusLabel = new GridBagConstraints();
		gbc_statusLabel.anchor = GridBagConstraints.WEST;
		gbc_statusLabel.insets = new Insets(0, 0, 5, 0);
		gbc_statusLabel.gridx = 0;
		gbc_statusLabel.gridy = 4;
		panel_2.add(statusLabel, gbc_statusLabel);

		JLabel payLabel = new JLabel("Solde : " + ControllerManager.utilisateur.getSolde());
		GridBagConstraints gbc_payLabel = new GridBagConstraints();
		gbc_payLabel.anchor = GridBagConstraints.WEST;
		gbc_payLabel.insets = new Insets(0, 0, 5, 0);
		gbc_payLabel.gridx = 0;
		gbc_payLabel.gridy = 5;
		panel_2.add(payLabel, gbc_payLabel);
	}
}
