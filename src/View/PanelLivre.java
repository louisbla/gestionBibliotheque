package View;

import DAO.DBManager;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelLivre extends JPanel {

    private static final long serialVersionUID = 1L;

    public PanelLivre() {
    	
    	
        this.setOpaque(false);
        this.setLayout(null);

        JLabel myLbl = new JLabel();
        myLbl.setBounds(0, 315, 998, 60);
        myLbl.setText("Onglet de gestion des livres");
        myLbl.setFont(new Font("Times New Roman", Font.BOLD, 60));
        myLbl.setForeground(Color.BLACK);
        myLbl.setHorizontalAlignment(JLabel.CENTER);


        this.add(myLbl);

    }

}
