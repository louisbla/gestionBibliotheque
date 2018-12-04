package View;

import DAO.DBManager;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAccueil extends JPanel {

	private Statement statement = null;
	private ResultSet resultSet = null;

    private static final long serialVersionUID = 1L;

    public PanelAccueil() {
        this.setOpaque(false);
        this.setLayout(null);

        JLabel myLbl = new JLabel();
        myLbl.setBounds(0, 315, 998, 60);
        myLbl.setText("Bienvenue dans le gestionnaire de bibliotheque");
        myLbl.setFont(new Font("Times New Roman", Font.BOLD, 40));
        myLbl.setForeground(Color.BLACK);
        myLbl.setHorizontalAlignment(JLabel.CENTER);


        this.add(myLbl);
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
