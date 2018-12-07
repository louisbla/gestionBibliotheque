package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.ControllerManager;

public class PanelProfilVisiteur extends JPanel {

	private ControllerManager cm;

	private JTextField txtIdentifiant;
	private JTextField txtMotDePasse;

	public PanelProfilVisiteur(ControllerManager controller) {
		setLayout(new BorderLayout());
		this.cm = controller;

		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
		add(statusPanel, BorderLayout.NORTH);
		statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel statusLabel = new JLabel("Vous \u00EAtes connecte en tant que visiteur");
		statusPanel.add(statusLabel);
		statusLabel.setFont(new Font(null, 0, 20));
		statusLabel.setForeground(Color.BLACK);
		statusLabel.setHorizontalAlignment(JLabel.CENTER);

		//-------------------------Login----------------------------------------

		JPanel loginPanel = new JPanel();
		add(loginPanel, BorderLayout.CENTER);

		GridBagLayout gbl_panel_2 = new GridBagLayout();
        loginPanel.setLayout(gbl_panel_2);

		txtIdentifiant = new JTextField();
		GridBagConstraints gbc_txtIdentifiant = new GridBagConstraints();
		gbc_txtIdentifiant.anchor = GridBagConstraints.CENTER;
		gbc_txtIdentifiant.insets = new Insets(0, 0, 5, 0);
		gbc_txtIdentifiant.gridx = 0;
		gbc_txtIdentifiant.gridy = 1;
		loginPanel.add(txtIdentifiant, gbc_txtIdentifiant);
		txtIdentifiant.setColumns(10);

		txtMotDePasse = new JTextField();
		GridBagConstraints gbc_txtMotDePasse = new GridBagConstraints();
		gbc_txtMotDePasse.anchor = GridBagConstraints.CENTER;
		gbc_txtMotDePasse.insets = new Insets(0, 0, 5, 0);
		gbc_txtMotDePasse.gridx = 0;
		gbc_txtMotDePasse.gridy = 2;
		loginPanel.add(txtMotDePasse, gbc_txtMotDePasse);
		txtMotDePasse.setColumns(10);

		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.CENTER;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 3;
		loginPanel.add(btnLogin, gbc_btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cm.login(txtIdentifiant.getText(), txtMotDePasse.getText());
				controller.clear();
				controller.profil();
			}
		});
	}

}
