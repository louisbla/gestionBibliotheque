package View;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class PanelEmpruntUtilisateur extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelEmpruntUtilisateur() {
		setLayout(null);
		
		
		this.setOpaque(false);
        setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

                JLabel myLbl = new JLabel();
                panel.add(myLbl);
                myLbl.setText("Mes Emprunts");
                myLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
                myLbl.setForeground(Color.BLACK);
                myLbl.setHorizontalAlignment(JLabel.CENTER);
                
                

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //updateModel();

        /*table.setModel(model);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(31);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.setBounds(0, 0, 500, 90);*/

        scrollPane.setViewportView(table);

	}
}
