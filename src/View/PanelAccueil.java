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

public class PanelAccueil extends JPanel {

	private Statement statement = null;
	private ResultSet resultSet = null;
	
	private JTextField txtIdentifiant;
	private JTextField txtMotDePasse;
	private ControllerManager cm;

    private static final long serialVersionUID = 1L;
    private JPanel panelLogin;

    public PanelAccueil(ControllerManager controller) {
        this.setOpaque(false);
        this.cm = controller;
        setLayout(new BorderLayout(0, 0));

        JLabel myLbl = new JLabel();
        myLbl.setText("Bienvenue dans le gestionnaire de bibliotheque");
        myLbl.setFont(new Font("Times New Roman", Font.BOLD, 40));
        myLbl.setForeground(Color.BLACK);
        myLbl.setHorizontalAlignment(JLabel.CENTER);

        this.add(myLbl, BorderLayout.NORTH);
        
        panelLogin = new JPanel();
        add(panelLogin, BorderLayout.CENTER);
        				panelLogin.setLayout(null);
        				
        				JPanel panel = new JPanel();
        				panel.setBounds(228, 159, 255, 160);
        				panelLogin.add(panel);
        				panel.setLayout(null);
        				
        						JButton btnLogin = new JButton("login");
        						btnLogin.setBounds(70, 113, 116, 25);
        						panel.add(btnLogin);
        						
        						txtMotDePasse = new JTextField();
        						txtMotDePasse.setBounds(123, 63, 116, 22);
        						panel.add(txtMotDePasse);
        						txtMotDePasse.setColumns(10);
        								
        						txtIdentifiant = new JTextField();
        						txtIdentifiant.setBounds(123, 13, 116, 22);
        						panel.add(txtIdentifiant);
        						txtIdentifiant.setColumns(10);
        								
        						JLabel lblLogin = new JLabel("login :");
        						lblLogin.setBounds(37, 13, 56, 22);
        						panel.add(lblLogin);
        						lblLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
        								
        						JLabel lblPassword = new JLabel("password :");
        						lblPassword.setBounds(12, 64, 81, 19);
        						panel.add(lblPassword);
        						lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        				btnLogin.addActionListener(new ActionListener() {
        					@Override
        					public void actionPerformed(ActionEvent e) {
        						cm.login(txtIdentifiant.getText(), txtMotDePasse.getText());
        						cm.clear();
        						cm.profil();
        					}
        				});
        				
        				if(controller.utilisateur.getDroit().equals(Droit.visiteur)) panelLogin.setVisible(true);
        				else panelLogin.setVisible(false);
        
    }

  //R�cup�re les strings dans le resultSet
    private void writeUsers(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
        	String code = resultSet.getString("codePermanent");
        	String firstname = resultSet.getString("prenom");
        	String name = resultSet.getString("nom");
        	int pay = resultSet.getInt("solde");
        	System.out.println("Code permanent : " + code);
        	System.out.println("Pr�nom : " + firstname);
        	System.out.println("Nom : " + name);
        	System.out.println("Solde : " + pay);
        }
    }
}
