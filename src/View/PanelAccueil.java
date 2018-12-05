package View;

import DAO.DBManager;

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
    private JPanel panel;

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
        
        panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        				SpringLayout sl_panel = new SpringLayout();
        				panel.setLayout(sl_panel);
        				
        				txtIdentifiant = new JTextField();
        				sl_panel.putConstraint(SpringLayout.NORTH, txtIdentifiant, -236, SpringLayout.SOUTH, panel);
        				sl_panel.putConstraint(SpringLayout.WEST, txtIdentifiant, 130, SpringLayout.WEST, panel);
        				sl_panel.putConstraint(SpringLayout.SOUTH, txtIdentifiant, -214, SpringLayout.SOUTH, panel);
        				panel.add(txtIdentifiant);
        				txtIdentifiant.setText("BLAL19019408");
        				txtIdentifiant.setColumns(10);
        		
        				txtMotDePasse = new JTextField();
        				sl_panel.putConstraint(SpringLayout.NORTH, txtMotDePasse, -236, SpringLayout.SOUTH, panel);
        				sl_panel.putConstraint(SpringLayout.WEST, txtMotDePasse, 18, SpringLayout.EAST, txtIdentifiant);
        				sl_panel.putConstraint(SpringLayout.SOUTH, txtMotDePasse, -214, SpringLayout.SOUTH, panel);
        				panel.add(txtMotDePasse);
        				txtMotDePasse.setText("azerty");
        				txtMotDePasse.setColumns(10);
        		
        				JButton btnLogin = new JButton("login");
        				sl_panel.putConstraint(SpringLayout.NORTH, btnLogin, -237, SpringLayout.SOUTH, panel);
        				sl_panel.putConstraint(SpringLayout.WEST, btnLogin, 21, SpringLayout.EAST, txtMotDePasse);
        				sl_panel.putConstraint(SpringLayout.SOUTH, btnLogin, -212, SpringLayout.SOUTH, panel);
        				panel.add(btnLogin);
        				btnLogin.addActionListener(new ActionListener() {
        					@Override
        					public void actionPerformed(ActionEvent e) {
        						cm.login(txtIdentifiant.getText(), txtMotDePasse.getText());
        						cm.clear();
        						cm.profil();
        					}
        				});
        
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
