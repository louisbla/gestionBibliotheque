package View;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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
		setLayout(new BorderLayout());

		//Database
    	populateData(DBManager.getAllRoom(), DBManager.getAllRoom());

    	JPanel titlePanel = new JPanel();
    	titlePanel.setBorder(new EmptyBorder(10, 10, 0, 10));
		add(titlePanel, BorderLayout.NORTH);

		JLabel lblReservationDesSalles = new JLabel("RESERVATION DES SALLES");
		lblReservationDesSalles.setBounds(302, 13, 200, 50);
		lblReservationDesSalles.setFont(new Font(null, 0, 20));
		lblReservationDesSalles.setForeground(Color.BLACK);
		lblReservationDesSalles.setHorizontalAlignment(JLabel.CENTER);
		titlePanel.add(lblReservationDesSalles);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(scrollPane, BorderLayout.CENTER);

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

		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(searchPanel, BorderLayout.EAST);

		GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{60, 116, 0};
        gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        searchPanel.setLayout(gbl_panel_2);

        JLabel lblRechercherPar = new JLabel("Recherche");
        GridBagConstraints gbc_lblRechercherPar = new GridBagConstraints();
        gbc_lblRechercherPar.insets = new Insets(0, 0, 5, 0);
        gbc_lblRechercherPar.gridx = 1;
        gbc_lblRechercherPar.gridy = 0;
        searchPanel.add(lblRechercherPar, gbc_lblRechercherPar);

        JLabel sizeLabel = new JLabel("Nombre de places :");
        sizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_sizeLabel = new GridBagConstraints();
        gbc_sizeLabel.anchor = GridBagConstraints.EAST;
        gbc_sizeLabel.insets = new Insets(0, 0, 5, 5);
        gbc_sizeLabel.gridx = 0;
        gbc_sizeLabel.gridy = 2;
        searchPanel.add(sizeLabel, gbc_sizeLabel);

        JLabel projectorLabel = new JLabel("Video-projecteur :");
        projectorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_projectorLabel = new GridBagConstraints();
        gbc_projectorLabel.anchor = GridBagConstraints.EAST;
        gbc_projectorLabel.insets = new Insets(0, 0, 5, 5);
        gbc_projectorLabel.gridx = 0;
        gbc_projectorLabel.gridy = 3;
        searchPanel.add(projectorLabel, gbc_projectorLabel);

        JLabel tableLabel = new JLabel("Tableau :");
        tableLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_tableLabel = new GridBagConstraints();
        gbc_tableLabel.anchor = GridBagConstraints.EAST;
        gbc_tableLabel.insets = new Insets(0, 0, 5, 5);
        gbc_tableLabel.gridx = 0;
        gbc_tableLabel.gridy = 4;
        searchPanel.add(tableLabel, gbc_tableLabel);

		JComboBox<String> comboBoxSize = new JComboBox<>();
		comboBoxSize.setModel(new DefaultComboBoxModel<String>(new String[] {"", "1", "2", "3", "4"}));
		GridBagConstraints gbc_comboBoxSize = new GridBagConstraints();
		gbc_comboBoxSize.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSize.gridx = 1;
		gbc_comboBoxSize.gridy = 2;
        searchPanel.add(comboBoxSize, gbc_comboBoxSize);

		JComboBox<String> comboBoxVideoPro = new JComboBox<>();
		comboBoxVideoPro.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Oui", "Non"}));
		GridBagConstraints gbc_comboBoxVideoPro = new GridBagConstraints();
		gbc_comboBoxVideoPro.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxVideoPro.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVideoPro.gridx = 1;
		gbc_comboBoxVideoPro.gridy = 3;
        searchPanel.add(comboBoxVideoPro, gbc_comboBoxVideoPro);

		JComboBox<String> comboBoxTableau = new JComboBox<>();
		comboBoxTableau.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Oui", "Non"}));
		GridBagConstraints gbc_comboBoxTableau = new GridBagConstraints();
		gbc_comboBoxTableau.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxTableau.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTableau.gridx = 1;
		gbc_comboBoxTableau.gridy = 4;
        searchPanel.add(comboBoxTableau, gbc_comboBoxTableau);

		JButton btnRechercher = new JButton("Rechercher");
		GridBagConstraints gbc_btnRechercher = new GridBagConstraints();
        gbc_btnRechercher.gridx = 1;
        gbc_btnRechercher.gridy = 5;
        searchPanel.add(btnRechercher, gbc_btnRechercher);
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

		JPanel adminPanel = new JPanel();
		adminPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(adminPanel, BorderLayout.SOUTH);

		JButton btnAjouterUnLivre = new JButton("Ajouter une salle");
		btnAjouterUnLivre.setBounds(200, 550, 150, 25);
		adminPanel.add(btnAjouterUnLivre);
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
        adminPanel.add(btnSupprimerSalle);
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
