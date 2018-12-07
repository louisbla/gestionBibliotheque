package View;

import DAO.DBManager;
import user.Droit;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ControllerManager;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PanelAccueil extends JPanel {

	private Statement statement = null;
	private ResultSet resultSet = null;

	private JTextField txtIdentifiant;
	private JTextField txtMotDePasse;
	private ControllerManager cm;

    private static final long serialVersionUID = 1L;
    private JPanel panelLogin;

    public PanelAccueil(ControllerManager controller) {
        this.cm = controller;
        setLayout(new BorderLayout());

        JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
		add(statusPanel, BorderLayout.NORTH);
		statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel statusLabel = new JLabel("Bienvenue dans le gestionnaire de bibliotheque");
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
		gbc_txtIdentifiant.gridx = 1;
		gbc_txtIdentifiant.gridy = 1;
		loginPanel.add(txtIdentifiant, gbc_txtIdentifiant);
		txtIdentifiant.setColumns(10);

		txtMotDePasse = new JTextField();
		GridBagConstraints gbc_txtMotDePasse = new GridBagConstraints();
		gbc_txtMotDePasse.anchor = GridBagConstraints.CENTER;
		gbc_txtMotDePasse.insets = new Insets(0, 0, 5, 0);
		gbc_txtMotDePasse.gridx = 1;
		gbc_txtMotDePasse.gridy = 2;
		loginPanel.add(txtMotDePasse, gbc_txtMotDePasse);
		txtMotDePasse.setColumns(10);

		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.CENTER;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 3;
		loginPanel.add(btnLogin, gbc_btnLogin);

		JLabel codeLabel = new JLabel("Code permanent : ");
		GridBagConstraints gbc_codeLabel = new GridBagConstraints();
		gbc_codeLabel.anchor = GridBagConstraints.WEST;
		gbc_codeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_codeLabel.gridx = 0;
		gbc_codeLabel.gridy = 1;
		loginPanel.add(codeLabel, gbc_codeLabel);

		JLabel passwordLabel = new JLabel("Mot de passe : ");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 2;
		loginPanel.add(passwordLabel, gbc_passwordLabel);

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cm.login(txtIdentifiant.getText(), txtMotDePasse.getText());
				cm.clear();
				cm.profil();
			}
		});

		if(controller.utilisateur.getDroit().equals(Droit.visiteur)) loginPanel.setVisible(true);
        else loginPanel.setVisible(false);
    }
}
