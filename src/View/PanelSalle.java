package View;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DAO.DBManager;
import View.dialog.CustomDialog;
import View.dialog.RoomDialog;

import javax.swing.JComboBox;

public class PanelSalle extends JPanel {
	private static JTable table;
    private static DefaultTableModel model;
    private static Object[][] data = new Object[0][0];
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelSalle() {
		setLayout(null);
		//Database
    	populateData(DBManager.getAllRoom(), DBManager.getAllRoom());

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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        updateModel();

        table.setModel(model);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.setBounds(0, 0, 500, 90);

		scrollPane.setViewportView(table);

		/*textField = new JTextField();
		textField.setBounds(490, 187, 155, 22);
		add(textField);
		textField.setColumns(10);*/

		JLabel lblNbDePlaces = new JLabel("Nb de places :");
		lblNbDePlaces.setBounds(486, 187, 89, 16);
		add(lblNbDePlaces);

		JLabel lblVideoprojecteur = new JLabel("Video-projecteur :");
		lblVideoprojecteur.setBounds(486, 233, 105, 16);
		add(lblVideoprojecteur);

		JLabel lblTableau = new JLabel("tableau :");
		lblTableau.setBounds(486, 273, 105, 16);
		add(lblTableau);

		JComboBox<String> comboBoxSize = new JComboBox<>();
		comboBoxSize.setBounds(635, 184, 68, 22);
		comboBoxSize.addItem("");
		comboBoxSize.addItem("1");
		comboBoxSize.addItem("2");
		comboBoxSize.addItem("3");
		comboBoxSize.addItem("4");
		add(comboBoxSize);

		JComboBox<String> comboBoxVideoPro = new JComboBox<>();
		comboBoxVideoPro.setBounds(635, 230, 68, 22);
		comboBoxVideoPro.addItem("");
		comboBoxVideoPro.addItem("Oui");
		comboBoxVideoPro.addItem("Non");
		add(comboBoxVideoPro);

		JComboBox<String> comboBoxTableau = new JComboBox<>();
		comboBoxTableau.setBounds(635, 270, 68, 22);
		comboBoxTableau.addItem("");
		comboBoxTableau.addItem("Oui");
		comboBoxTableau.addItem("Non");
		add(comboBoxTableau);

		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(563, 331, 97, 25);
		add(btnRechercher);
		btnRechercher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ComboTableau : " + comboBoxTableau.getSelectedItem().toString());
				System.out.println("ComboProjecteur : " + comboBoxVideoPro.getSelectedItem().toString());
				populateData(DBManager.searchRoom(comboBoxSize.getSelectedItem().toString(), comboBoxTableau.getSelectedItem().toString(), comboBoxVideoPro.getSelectedItem().toString()), DBManager.searchRoom(comboBoxSize.getSelectedItem().toString(), comboBoxTableau.getSelectedItem().toString(), comboBoxVideoPro.getSelectedItem().toString()));
				updateModel();
				table.setModel(model);
			}
		});

		JButton btnAjouterUnLivre = new JButton("Ajouter une salle");
		btnAjouterUnLivre.setBounds(200, 550, 150, 25);
        add(btnAjouterUnLivre);
        btnAjouterUnLivre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RoomDialog dialog = new RoomDialog(new Frame());
				dialog.setVisible(true);
				populateData(DBManager.getAllRoom(), DBManager.getAllRoom());
				updateModel();
				table.setModel(model);
			}
		});

        JButton btnSupprimerSalle = new JButton("Supprimer une salle");
        btnSupprimerSalle.setBounds(400, 550, 150, 25);
		add(btnSupprimerSalle);
		btnSupprimerSalle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int line = table.getSelectedRow();
				Object obj = table.getModel().getValueAt(line, 0);

				try {
					DBManager.connectDataBase();
					DBManager.deleteRoom(obj.toString());
					DBManager.closeDatabase();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				populateData(DBManager.getAllRoom(), DBManager.getAllRoom());
				updateModel();
				table.setModel(model);
			}
		});
	}

	@SuppressWarnings("serial")
	public void updateModel() {
    	model = new DefaultTableModel(data, new String[] {"Numéro", "Taille", "Tableau", "Projecteur", "Disponibilit\u00E9"}) {
            boolean[] columnEditables = new boolean[] {
            	false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
            	return columnEditables[column];
            }
        };
    }

	public void populateData(ResultSet resultSet, ResultSet count) {
        try {
			int nbSalles = 0;
			while (count.next()) {
				nbSalles++;
			}
			data = new Object[nbSalles][5];

			int i = 0;
			while (resultSet.next()) {
				data[i][0] = resultSet.getString("numero_salle");
				data[i][1] = resultSet.getString("nb_place");
				if(resultSet.getBoolean("have_tableau") == true)
					data[i][2] = "Oui";
				else
					data[i][2] = "Non";
				if(resultSet.getBoolean("have_projecteur") == true)
					data[i][3] = "Oui";
				else
					data[i][3] = "Non";
				if(resultSet.getBoolean("is_disponible") == true)
					data[i][4] = "Disponible";
				else
					data[i][4] = "Non disponible";
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
}
