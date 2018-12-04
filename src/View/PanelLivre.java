package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DAO.DBManager;
import View.dialog.CustomDialog;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class PanelLivre extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public PanelLivre() {
    	
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
				data[i][1] = resultSet.getString("titre");
				data[i][2] = "j";
				data[i][3] = resultSet.getString("auteur");
				data[i][4] = resultSet.getString("isbn");
				if(resultSet.getBoolean("est_disponible") == true)
					data[i][5] = "Disponible";
				else
					data[i][5] = "Non disponible";
				i++;
				
		          String author = resultSet.getString("auteur");
		          String title = resultSet.getString("titre");
		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
    	
    	
        this.setOpaque(false);
        setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
                JLabel myLbl = new JLabel();
                panel.add(myLbl);
                myLbl.setText("Onglet de gestion des livres");
                myLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
                myLbl.setForeground(Color.BLACK);
                myLbl.setHorizontalAlignment(JLabel.CENTER);

        JList mylist = new JList();
        mylist.setBounds(0,60,200,200);
        mylist.setForeground(Color.BLACK);
        
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane);
        
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(
        	data,
        	new String[] {
        		"id", "Titre", "Sous-titre", "Auteur", "ISBN", "Disponibilit\u00E9"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false, false, false, false, false, false
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
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.setBounds(0, 0, 500, 90);
        
        scrollPane.setViewportView(table);
        
        JPanel panel_1 = new JPanel();
        add(panel_1, BorderLayout.SOUTH);
        
        JButton btnAjouterUnLivre = new JButton("Ajouter un livre");
        panel_1.add(btnAjouterUnLivre);
        btnAjouterUnLivre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomDialog dialog = new CustomDialog(new Frame());
				dialog.show();
			}
		});
        
        JButton delete = new JButton();
        delete.setText("Delete");
        
        
        

    }
}
