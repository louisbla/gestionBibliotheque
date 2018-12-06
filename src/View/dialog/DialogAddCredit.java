package View.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.DBManager;
import View.PanelLivre;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JButton;

public class DialogAddCredit extends JDialog
implements ActionListener,
PropertyChangeListener {

	private int montant = 0;
	private JTextField soldeField;

	private JTextPane textCode;
	private JTextPane textNom;
	private JTextPane textPrenom;
	private JTextPane textSolde;

	/** Creates the reusable dialog. */
	public DialogAddCredit(Frame aFrame,String nom, String prenom, String codePermanent, String solde) {
		super(aFrame, true);

		setTitle("Ajouter du solde");
		setBounds(500, 400, 450, 323);
		getContentPane().setLayout(null);

		JLabel lblCrediterSolde = new JLabel("Ajouter du solde");
		lblCrediterSolde.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCrediterSolde.setBounds(130, 13, 179, 30);
		getContentPane().add(lblCrediterSolde);

		JLabel lblCodePermanent = new JLabel("code Permanent :");
		lblCodePermanent.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCodePermanent.setBounds(28, 56, 129, 16);
		getContentPane().add(lblCodePermanent);

		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNom.setBounds(114, 84, 43, 16);
		getContentPane().add(lblNom);

		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrenom.setBounds(91, 113, 66, 16);
		getContentPane().add(lblPrenom);

		JLabel lblSoldeActuel = new JLabel("Solde actuel :");
		lblSoldeActuel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoldeActuel.setBounds(56, 142, 101, 16);
		getContentPane().add(lblSoldeActuel);

		 textCode = new JTextPane();
		textCode.setBackground(UIManager.getColor("Button.background"));
		textCode.setBounds(213, 56, 101, 16);
		textCode.setText(codePermanent);
		getContentPane().add(textCode);

		 textNom = new JTextPane();
		textNom.setBackground(SystemColor.menu);
		textNom.setBounds(208, 84, 101, 16);
		textNom.setText(nom);
		getContentPane().add(textNom);

		 textPrenom = new JTextPane();
		textPrenom.setBackground(SystemColor.menu);
		textPrenom.setBounds(208, 113, 101, 16);
		textPrenom.setText(prenom);
		getContentPane().add(textPrenom);

		 textSolde = new JTextPane();
		textSolde.setBackground(SystemColor.menu);
		textSolde.setBounds(208, 142, 101, 16);
		textSolde.setText(solde);
		getContentPane().add(textSolde);

		soldeField = new JTextField();
		soldeField.setBounds(193, 191, 116, 22);
		getContentPane().add(soldeField);
		soldeField.setColumns(10);

		JLabel lblMontantAjouter = new JLabel("Montant \u00E0 ajouter :");
		lblMontantAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMontantAjouter.setBounds(40, 194, 141, 16);
		getContentPane().add(lblMontantAjouter);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valider(codePermanent);
			}
		});
		btnValider.setBounds(84, 238, 97, 25);
		getContentPane().add(btnValider);

		JButton button = new JButton("Annuler");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAndHide();
			}
		});
		button.setBounds(234, 238, 97, 25);
		getContentPane().add(button);


		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent ce) {
				soldeField.requestFocusInWindow();
			}
		});

		//Register an event handler that puts the text into the option pane.
		soldeField.addActionListener(this);

	}
	
	//////////////////////////////////////////////////////////////////////
	
	private void valider(String codeUtulisateur) {
		if (!soldeField.getText().equals("") ) {
			//we're done; clear and dismiss the dialog
			try {
				montant = (int) Integer.parseInt(soldeField.getText());
				DBManager.connectDataBase();
				System.out.println("solde ajouté : "+montant);
				DBManager.addSoldeTo(montant,codeUtulisateur);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				DBManager.closeDatabase();
			}
			clearAndHide();
		} else {
			//text was invalid
			soldeField.selectAll();
			JOptionPane.showMessageDialog(
					DialogAddCredit.this,
					"Veuillez entrer un montant entier",
					"Erreur",
					JOptionPane.ERROR_MESSAGE);
			montant = 0;
			soldeField.requestFocusInWindow();
		}
	}

	/////////////////////////////////////////////////////////////////////////////

	/** This method reacts to state changes in the option pane. */
	public void propertyChange(PropertyChangeEvent e) {
	}

	/** This method clears the dialog and hides it. */
	public void clearAndHide() {
		soldeField.setText(null);
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}