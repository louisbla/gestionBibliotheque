package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerManager;
import DAO.DBManager;

public class PanelEmpruntUtilisateur extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelEmpruntUtilisateur() {
		setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.setBorder(new EmptyBorder(10, 10, 0, 10));

        JLabel myLbl = new JLabel();
        panel.add(myLbl);
        myLbl.setText("MES EMPRUNTS");
        myLbl.setFont(new Font(null, 0, 20));
        myLbl.setForeground(Color.BLACK);
        myLbl.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        int NbEmprunts = DBManager.getNbEmpruntsOfUser(ControllerManager.utilisateur.getIdentification());
        System.out.println("nb emprunts : " + NbEmprunts);
        Object[][] data = new Object[NbEmprunts][5];


        try {
        	ResultSet resultSet = DBManager.getEmpruntsOfUser(ControllerManager.utilisateur.getIdentification());
        	int i=0;
			while(resultSet.next()) {
				data[i][0] = resultSet.getString("titre");
				data[i][1] = resultSet.getString("auteur");
				data[i][2] = resultSet.getString("isbn");
				data[i][3] = resultSet.getString("date");
				data[i][4] = resultSet.getString("duree");
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        table = new JTable();
        table.setModel(new DefaultTableModel(
        	data,
        	new String[] {
        		"Titre", "Auteur", "ISBN", "Date d'emprunt", "Duree(jour)"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false, false, false, false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(118);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(94);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(3).setPreferredWidth(106);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setMaxWidth(100);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(table);
	}
}
