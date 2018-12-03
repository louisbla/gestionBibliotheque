package View;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import java.awt.Font;

public class PanelUtilisateurs extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelUtilisateurs() {
		setLayout(null);
		
		JButton buttonAdd = new JButton("Ajouter");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addUser();
			}
		});
		buttonAdd.setBounds(719, 135, 97, 25);
		add(buttonAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 77, 612, 507);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", "", "", null},
			},
			new String[] {
				"id", "Nom", "Pr\u00E9nom", "Statut", "Nb d'emprunt"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(95);
		table.getColumnModel().getColumn(1).setPreferredWidth(92);
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		table.getColumnModel().getColumn(3).setPreferredWidth(89);
		scrollPane.setViewportView(table);
		
		JTextPane txtpnUtilisateurs = new JTextPane();
		txtpnUtilisateurs.setEditable(false);
		txtpnUtilisateurs.setAlignmentX(CENTER_ALIGNMENT);
		txtpnUtilisateurs.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnUtilisateurs.setText("Utilisateurs");
		txtpnUtilisateurs.setBounds(140, 26, 284, 38);
		add(txtpnUtilisateurs);

	}
	
	/////////////////////////////////////////////////////////////////////
	 
	public void addUser() {
		// TO DO
	}
}
