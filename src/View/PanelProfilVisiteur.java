package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ControllerManager;

public class PanelProfilVisiteur extends JPanel {

	private ControllerManager cm;
	
	private JTextField txtIdentifiant;
	private JTextField txtMotDePasse;
	
	public PanelProfilVisiteur(ControllerManager controller) {
		setLayout(null);
		this.cm = controller;
		
		JPanel panelVisiteur = new JPanel();
		panelVisiteur.setBounds(12, 13, 898, 603);
		add(panelVisiteur);
		panelVisiteur.setLayout(null);

		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(251, 99, 364, 43);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setText("Vous \u00EAtes connecte en tant que " + ControllerManager.utilisateur.getDroit());
		panelVisiteur.add(lblNewLabel);

		txtIdentifiant = new JTextField();
		txtIdentifiant.setBounds(251, 203, 116, 22);
		txtIdentifiant.setText("BLAL19019408");
		panelVisiteur.add(txtIdentifiant);
		txtIdentifiant.setColumns(10);

		txtMotDePasse = new JTextField();
		txtMotDePasse.setBounds(251, 254, 116, 22);
		txtMotDePasse.setText("azerty");
		panelVisiteur.add(txtMotDePasse);
		txtMotDePasse.setColumns(10);

		JButton btnLogin = new JButton("login");
		btnLogin.setBounds(251, 307, 97, 25);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cm.login(txtIdentifiant.getText(), txtMotDePasse.getText());
				controller.clear();
				controller.profil();
			}
		});
		panelVisiteur.add(btnLogin);
	}

}
