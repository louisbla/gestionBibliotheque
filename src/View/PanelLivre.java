package View;

import DAO.DBManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

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
        
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"1", "Harry Potter", "A l'ecole des sorcies", "J K Rowling", "54454548", delete},
        		{null, null, null, null, null, null},
        		{null, "", null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        	},
        	new String[] {
        		"id", "Titre", "Sous-titre", "Auteur", "ISBN", "Editer"
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
