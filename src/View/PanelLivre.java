package View;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DAO.DBManager;

public class PanelLivre extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public PanelLivre() {
    	
    	
        this.setOpaque(false);
        this.setLayout(null);

        JLabel myLbl = new JLabel();
        myLbl.setBounds(0, 0, 800, 60);
        myLbl.setText("Onglet de gestion des livres");
        myLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        myLbl.setForeground(Color.BLACK);
        myLbl.setHorizontalAlignment(JLabel.CENTER);

        this.add(myLbl);

        JList mylist = new JList();
        mylist.setBounds(0,60,200,200);
        mylist.setForeground(Color.BLACK);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 55, 800,400);
        add(scrollPane);
        
        JButton delete = new JButton();
        delete.setText("Delete");
        
        //Database
        
        Object[][] data = new Object[0][0];
        

        try {
			Statement statement = DBManager.connectDataBase().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM livre");
			int nbLivres = 0;
			if(resultSet.next()) {
        		nbLivres = resultSet.getInt(1);
        	}
			data = new Object[nbLivres][6];
        	
			statement = DBManager.connectDataBase().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM livre");
			int i = 0;
			while (resultSet.next()) {
				data[i][0] = resultSet.getString("id_livre");
				data[i][1] = resultSet.getString("auteur");
				data[i][2] = "j";
				data[i][3] = resultSet.getString("titre");
				data[i][4] = resultSet.getString("isbn");
				if(resultSet.getString("est_disponible") == "1")
					data[i][5] = "Disponible";
				else
					data[i][5] = "Non disponible";
				i++;
				
		          String author = resultSet.getString("auteur");
		          String title = resultSet.getString("titre");
		          System.out.println("Auteur : " + author);
		          System.out.println("Titre : " + title);
		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
        
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(
        	data,
        	new String[] {
        		"id", "Titre", "Sous-titre", "Auteur", "ISBN", "Disponibilité"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false, false, false, false, true, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(31);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.setBounds(0, 0, 500, 90);
        
        scrollPane.setViewportView(table);        
        

    }
}
