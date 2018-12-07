package View.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import DAO.DBManager;

public class BookDetailDialog extends JDialog {

	private JPanel messagePane;
	private JLabel resume;

	public BookDetailDialog(Frame aFrame, JTable table, int row) {
        super(aFrame, true);
        setTitle("Détail de l'oeuvre");
        setBounds(500, 400, 450, 300);

        messagePane = new JPanel();
        resume = new JLabel();
        messagePane.add(resume);
        //add(resume);
        getContentPane().add(messagePane);

        resume.setFont(new Font("Times New Roman", Font.BOLD, 20));
        resume.setForeground(Color.BLACK);
        resume.setHorizontalAlignment(JLabel.CENTER);

        Object obj = table.getModel().getValueAt(row, 0);

		try {
			DBManager.connectDataBase();
			resume.setText(DBManager.getResume(Integer.parseInt(obj.toString())));
			DBManager.closeDatabase();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
