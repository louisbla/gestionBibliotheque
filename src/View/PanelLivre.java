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
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DAO.DBManager;
import View.dialog.CustomDialog;

public class PanelLivre extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JTable table;
    private static DefaultTableModel model;
    private static Object[][] data = new Object[0][0];

    @SuppressWarnings("serial")
	public PanelLivre() {
    	//Database
    	populateData(DBManager.getAllBook(), DBManager.getAllBook());

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

        JButton btnAjouterUnLivre = new JButton("Ajouter un livre");
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

    	InputMap im = table.getInputMap(JTable.WHEN_FOCUSED);
    	ActionMap am = table.getActionMap();
    	im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
    	am.put("delete", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int line = table.getSelectedRow();
				Object obj = table.getModel().getValueAt(line, 0);

				System.out.println("Delete livre id=" + obj);

				try {
					DBManager.connectDataBase();
					DBManager.deleteBook(Integer.parseInt(obj.toString()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					DBManager.closeDatabase();
				}

				populateData(DBManager.getAllBook(),DBManager.getAllBook());
				updateModel();
				table.setModel(model);
			}
    	});

    	Scanner sc = new Scanner(System.in);
    	System.out.println("Auteur : ");
    	String auteur = sc.nextLine();
    	System.out.println("Titre : ");
    	String titre = sc.nextLine();
    	System.out.println("Titre entré :" + titre + ":");
    	System.out.println("Auteur entré :" + auteur + ":");
    	String isbn = "";
    	String id = "";

    	populateData(DBManager.searchBook(id, titre, auteur, isbn), DBManager.searchBook(id, titre, auteur, isbn));
    	updateModel();
		table.setModel(model);
    }

    @SuppressWarnings("serial")
	public void updateModel() {
    	model = new DefaultTableModel(data, new String[] {"id", "Titre", "Sous-titre", "Auteur", "ISBN", "Disponibilit\u00E9"}) {
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
