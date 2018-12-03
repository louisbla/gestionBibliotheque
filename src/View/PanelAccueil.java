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
        myLbl.setText("Bienvenue dans le gestionnaire de biblioth�que");
        myLbl.setFont(new Font("Times New Roman", Font.BOLD, 60));
        myLbl.setForeground(Color.BLACK);
        myLbl.setHorizontalAlignment(JLabel.CENTER);

       
        this.add(myLbl);
    }

    //R�cup�re les strings dans le resultSet
    private void writeBooks(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
        	String author = resultSet.getString("auteur");
        	String title = resultSet.getString("titre");
        	System.out.println("Auteur : " + author);
        	System.out.println("Titre : " + title);
        }
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
