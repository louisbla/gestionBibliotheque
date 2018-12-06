package View;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import DAO.DBManager;
import View.dialog.CustomDialog;
import View.dialog.DialogEmprunt;

public class PanelEmprunt extends JPanel {
	
	private JTable table;
    private static DefaultTableModel model;
    private static Object[][] data = new Object[0][0];

	/**
	 * Create the panel.
	 */
	public PanelEmprunt() {
		updateModel();
		populateData(DBManager.getAllEmprunts(), DBManager.getAllEmprunts());
		
		setLayout(null);
		
		JButton buttonAdd = new JButton("Ajouter");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEmprunt();
			}
		});
		buttonAdd.setBounds(687, 155, 97, 25);
		add(buttonAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 77, 600, 520);
		add(scrollPane);
		
		table = new JTable();
		populateData(DBManager.getAllEmprunts(), DBManager.getAllEmprunts());
		updateModel();
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JTextPane txtpnUtilisateurs = new JTextPane();
		txtpnUtilisateurs.setEditable(false);
		txtpnUtilisateurs.setAlignmentX(CENTER_ALIGNMENT);
		txtpnUtilisateurs.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnUtilisateurs.setText("Emprunts");
		txtpnUtilisateurs.setBounds(192, 26, 284, 38);
		add(txtpnUtilisateurs);

	}
	
    @SuppressWarnings("serial")
	public void updateModel() {
    	model = new DefaultTableModel(data, new String[] {"Date d'emprunt", "Duree (en jours)", "Titre", "ISBN", "Personne", "Code permanent"}) {
            boolean[] columnEditables = new boolean[] {
            	false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
            	return columnEditables[column];
            }
        };
    }
	
	private void populateData(ResultSet resultSet, ResultSet count) {
		try {
			int nbEmprunts = 0;
			while (count.next()) {
				nbEmprunts++;
			}
			data = new Object[nbEmprunts][6];
			System.out.println("Nombre d'emprunts total : " + nbEmprunts);

			int i = 0;
			while (resultSet.next()) {
				data[i][0] = resultSet.getString("date");
				data[i][1] = resultSet.getString("duree");
				data[i][2] = resultSet.getString("titre");
				data[i][3] = resultSet.getString("isbn");
				data[i][4] = resultSet.getString("utilisateur.prenom") + " " + resultSet.getString("utilisateur.nom");
				data[i][5] = resultSet.getString("utilisateur.codePermanent");
				i++;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.closeDatabase();
		}
	}

	/////////////////////////////////////////////////////////////////////
	 
	public void addEmprunt() {
		DialogEmprunt dialog = new DialogEmprunt(new Frame());
		dialog.setVisible(true);

		updateModel();
		populateData(DBManager.getAllEmprunts(), DBManager.getAllEmprunts());
	}
}
