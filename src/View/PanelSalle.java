package View;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class PanelSalle extends JPanel {
	private JTable table;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelSalle() {
		setLayout(null);
		
		JLabel lblReservationDesSalles = new JLabel("Reservation des salles");
		lblReservationDesSalles.setBounds(302, 13, 200, 50);
		lblReservationDesSalles.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblReservationDesSalles.setForeground(Color.BLACK);
		lblReservationDesSalles.setHorizontalAlignment(JLabel.CENTER);
		add(lblReservationDesSalles);
		
		JLabel lblSallesDisponibles = new JLabel("Salles disponibles :");
		lblSallesDisponibles.setBounds(134, 111, 200, 33);
		lblSallesDisponibles.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSallesDisponibles.setForeground(Color.BLACK);
		lblSallesDisponibles.setHorizontalAlignment(JLabel.CENTER);
		add(lblSallesDisponibles);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 159, 411, 344);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnReserverSalle = new JButton("Reserver Salle");
		btnReserverSalle.setBounds(47, 546, 133, 25);
		add(btnReserverSalle);
		
		textField = new JTextField();
		textField.setBounds(192, 547, 155, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNbDePlaces = new JLabel("Nb de places :");
		lblNbDePlaces.setBounds(486, 187, 89, 16);
		add(lblNbDePlaces);
		
		JLabel lblVideoprojecteur = new JLabel("Video-projecteur :");
		lblVideoprojecteur.setBounds(486, 233, 105, 16);
		add(lblVideoprojecteur);
		
		JLabel lblTableau = new JLabel("tableau :");
		lblTableau.setBounds(486, 273, 105, 16);
		add(lblTableau);
		
		JComboBox comboBoxVideoPro = new JComboBox();
		comboBoxVideoPro.setBounds(635, 230, 68, 22);
		comboBoxVideoPro.addItem("---");
		comboBoxVideoPro.addItem("oui");
		comboBoxVideoPro.addItem("non");
		add(comboBoxVideoPro);
		
		JComboBox comboBoxTableau = new JComboBox();
		comboBoxTableau.setBounds(635, 270, 68, 22);
		comboBoxTableau.addItem("---");
		comboBoxTableau.addItem("oui");
		comboBoxTableau.addItem("non");
		add(comboBoxTableau);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(563, 331, 97, 25);
		add(btnRechercher);

	}
}
