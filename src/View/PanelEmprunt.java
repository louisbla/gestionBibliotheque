package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class PanelEmprunt extends JPanel {
	
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelEmprunt() {
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", "", "", null},
			},
			new String[] {
				"id", "date debut", "date de fin", "Oeuvre", "Personne"
			}
		));
		scrollPane.setViewportView(table);
		
		JTextPane txtpnUtilisateurs = new JTextPane();
		txtpnUtilisateurs.setEditable(false);
		txtpnUtilisateurs.setAlignmentX(CENTER_ALIGNMENT);
		txtpnUtilisateurs.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnUtilisateurs.setText("Emprunts");
		txtpnUtilisateurs.setBounds(192, 26, 284, 38);
		add(txtpnUtilisateurs);

	}
	
	/////////////////////////////////////////////////////////////////////
	 
	public void addEmprunt() {
		// TO DO
	}
}
