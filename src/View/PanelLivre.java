package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerManager;
import DAO.DBManager;
import View.dialog.CustomDialog;
import user.Droit;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PanelLivre extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JTable table;
    private static DefaultTableModel model;
    private static Object[][] data = new Object[0][0];
    //private JTextField textField;
    private JTextField titleInput;
    private JTextField authorInput;
    private JTextField isbnInput;
    private JTextField keywordInput;

    @SuppressWarnings("serial")
	public PanelLivre() {
    	//Database
    	populateData(DBManager.getAllBook(), DBManager.getAllBook());

        this.setOpaque(false);
        setLayout(new BorderLayout(0, 0));

        JPanel panel_2 = new JPanel();
        add(panel_2, BorderLayout.EAST);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{60, 116, 0};
        gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);

        JLabel lblRechercherPar = new JLabel("Recherche");
        GridBagConstraints gbc_lblRechercherPar = new GridBagConstraints();
        gbc_lblRechercherPar.insets = new Insets(0, 0, 5, 0);
        gbc_lblRechercherPar.gridx = 1;
        gbc_lblRechercherPar.gridy = 0;
        panel_2.add(lblRechercherPar, gbc_lblRechercherPar);

        JLabel lblNewLabel = new JLabel("Titre");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 2;
        panel_2.add(lblNewLabel, gbc_lblNewLabel);

        titleInput = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.insets = new Insets(0, 0, 5, 0);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 1;
        gbc_textField_1.gridy = 2;
        panel_2.add(titleInput, gbc_textField_1);
        titleInput.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Auteur");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 3;
        panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);

        authorInput = new JTextField();
        GridBagConstraints gbc_textField_2 = new GridBagConstraints();
        gbc_textField_2.insets = new Insets(0, 0, 5, 0);
        gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_2.gridx = 1;
        gbc_textField_2.gridy = 3;
        panel_2.add(authorInput, gbc_textField_2);
        authorInput.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("ISBN");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 4;
        panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);

        isbnInput = new JTextField();
        GridBagConstraints gbc_textField_3 = new GridBagConstraints();
        gbc_textField_3.insets = new Insets(0, 0, 5, 0);
        gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_3.gridx = 1;
        gbc_textField_3.gridy = 4;
        panel_2.add(isbnInput, gbc_textField_3);
        isbnInput.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Type");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 5;
        panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Livre", "Periodique", "Carte", "DVD"}));
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 0);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 5;
        panel_2.add(comboBox, gbc_comboBox);

        JLabel lblNewLabel_4 = new JLabel("Mot-clef");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 6;
        panel_2.add(lblNewLabel_4, gbc_lblNewLabel_4);

        keywordInput = new JTextField();
        GridBagConstraints gbc_textField_5 = new GridBagConstraints();
        gbc_textField_5.insets = new Insets(0, 0, 5, 0);
        gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_5.gridx = 1;
        gbc_textField_5.gridy = 6;
        panel_2.add(keywordInput, gbc_textField_5);
        keywordInput.setColumns(10);

        JButton btnRechercher = new JButton("Rechercher");
        GridBagConstraints gbc_btnRechercher = new GridBagConstraints();
        gbc_btnRechercher.gridx = 1;
        gbc_btnRechercher.gridy = 8;
        panel_2.add(btnRechercher, gbc_btnRechercher);
        btnRechercher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				populateData(DBManager.searchBook(titleInput.getText(), authorInput.getText(), isbnInput.getText(), comboBox.getSelectedItem().toString(), keywordInput.getText()), DBManager.searchBook(titleInput.getText(), authorInput.getText(), isbnInput.getText(), comboBox.getSelectedItem().toString(), keywordInput.getText()));
				updateModel();
				table.setModel(model);
			}
		});

        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel myLbl = new JLabel();
        panel.add(myLbl);
        myLbl.setText("Onglet de gestion des livres");
        myLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        myLbl.setForeground(Color.BLACK);
        myLbl.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        updateModel();

        table.setModel(model);
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

        JButton btnAjouterUnLivre = new JButton("Ajouter une oeuvre");
        panel_1.add(btnAjouterUnLivre);
        btnAjouterUnLivre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CustomDialog dialog = new CustomDialog(new Frame());
				dialog.setVisible(true);
				populateData(DBManager.getAllBook(), DBManager.getAllBook());
				updateModel();
				table.setModel(model);
			}
		});

        JButton btnSupprimerUnLivre = new JButton("Supprimer une oeuvre");
        panel_1.add(btnSupprimerUnLivre);
        btnSupprimerUnLivre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int line = table.getSelectedRow();
				Object obj = table.getModel().getValueAt(line, 0);

				System.out.println("Delete livre id=" + obj);

				try {
					DBManager.connectDataBase();
					DBManager.deleteBook(Integer.parseInt(obj.toString()));
					DBManager.closeDatabase();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				populateData(DBManager.getAllBook(),DBManager.getAllBook());
				updateModel();
				table.setModel(model);
			}
		});

    	//////////////gestion des elements admin///////////////
    	if(ControllerManager.utilisateur.getDroit().equals(Droit.admin)) {
    		btnAjouterUnLivre.setVisible(true);
    		btnSupprimerUnLivre.setVisible(true);
    	}else {
    		btnAjouterUnLivre.setVisible(false);
    		btnSupprimerUnLivre.setVisible(false);
    	}
    }

    @SuppressWarnings("serial")
	public void updateModel() {
    	model = new DefaultTableModel(data, new String[] {"id", "Titre", "Auteur", "ISBN", "Type", "Disponibilit\u00E9"}) {
            boolean[] columnEditables = new boolean[] {
            	false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
            	return columnEditables[column];
            }
        };
    }

    public void populateData(ResultSet resultSet, ResultSet count) {
        try {
			int nbLivres = 0;
			while (count.next()) {
				nbLivres++;
			}
			data = new Object[nbLivres][6];

			int i = 0;
			while (resultSet.next()) {
				data[i][0] = resultSet.getString("id_oeuvre");
				data[i][1] = resultSet.getString("titre");
				data[i][2] = resultSet.getString("auteur");
				data[i][3] = resultSet.getString("isbn");
				data[i][4] = resultSet.getString("type");
				if(resultSet.getBoolean("est_disponible") == true)
					data[i][5] = "Disponible";
				else
					data[i][5] = "Non disponible";
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
