package View.dialog;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.DBManager;
import View.PanelLivre;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;
import javax.swing.JButton;

public class DialogAddCredit extends JDialog
implements ActionListener,
PropertyChangeListener {

	private int montant = 0;
	private JTextField soldeField;

	/** Creates the reusable dialog. */
	public DialogAddCredit(Frame aFrame,String nom, String prenom, String codePermanent, String solde) {
		super(aFrame, true);

		setTitle("Ajouter du crédit");
		setBounds(500, 400, 350, 250);

		JPanel searchPanel = new JPanel();
        getContentPane().add(searchPanel);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        searchPanel.setLayout(gbl_panel_2);

		JLabel lblCodePermanent = new JLabel("Code permanent : ");
		lblCodePermanent.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblCodePermanent = new GridBagConstraints();
		gbc_lblCodePermanent.anchor = GridBagConstraints.WEST;
		gbc_lblCodePermanent.insets = new Insets(0, 0, 5, 0);
		gbc_lblCodePermanent.gridx = 0;
		gbc_lblCodePermanent.gridy = 1;
        searchPanel.add(lblCodePermanent, gbc_lblCodePermanent);

		JLabel lblNom = new JLabel("Nom : ");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.anchor = GridBagConstraints.WEST;
		gbc_lblNom.insets = new Insets(0, 0, 5, 0);
		gbc_lblNom.gridx = 0;
		gbc_lblNom.gridy = 2;
        searchPanel.add(lblNom, gbc_lblNom);

		JLabel lblPrenom = new JLabel("Prenom : ");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblPrenom = new GridBagConstraints();
		gbc_lblPrenom.anchor = GridBagConstraints.WEST;
		gbc_lblPrenom.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrenom.gridx = 0;
		gbc_lblPrenom.gridy = 3;
        searchPanel.add(lblPrenom, gbc_lblPrenom);

		JLabel lblSoldeActuel = new JLabel("Solde actuel : ");
		lblSoldeActuel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblSoldeActuel = new GridBagConstraints();
		gbc_lblSoldeActuel.anchor = GridBagConstraints.WEST;
		gbc_lblSoldeActuel.insets = new Insets(0, 0, 5, 0);
		gbc_lblSoldeActuel.gridx = 0;
		gbc_lblSoldeActuel.gridy = 4;
        searchPanel.add(lblSoldeActuel, gbc_lblSoldeActuel);

        JLabel lblMontantAjouter = new JLabel("Montant \u00E0 ajouter : ");
		lblMontantAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblMontantAjouter = new GridBagConstraints();
		gbc_lblMontantAjouter.anchor = GridBagConstraints.WEST;
		gbc_lblMontantAjouter.insets = new Insets(0, 0, 5, 0);
		gbc_lblMontantAjouter.gridx = 0;
		gbc_lblMontantAjouter.gridy = 5;
        searchPanel.add(lblMontantAjouter, gbc_lblMontantAjouter);

		//-----------------------------------------------------------------

		JLabel myCodeLabel = new JLabel(codePermanent);
		GridBagConstraints gbc_myCodeLabel = new GridBagConstraints();
		gbc_myCodeLabel.anchor = GridBagConstraints.WEST;
		gbc_myCodeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_myCodeLabel.gridx = 1;
		gbc_myCodeLabel.gridy = 1;
        searchPanel.add(myCodeLabel, gbc_myCodeLabel);

		JLabel myNameLabel = new JLabel(nom);
		GridBagConstraints gbc_myNameLabel = new GridBagConstraints();
		gbc_myNameLabel.anchor = GridBagConstraints.WEST;
		gbc_myNameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_myNameLabel.gridx = 1;
		gbc_myNameLabel.gridy = 2;
        searchPanel.add(myNameLabel, gbc_myNameLabel);

		JLabel myFirstNameLabel = new JLabel(prenom);
		GridBagConstraints gbc_myFirstNameLabel = new GridBagConstraints();
		gbc_myFirstNameLabel.anchor = GridBagConstraints.WEST;
		gbc_myFirstNameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_myFirstNameLabel.gridx = 1;
		gbc_myFirstNameLabel.gridy = 3;
        searchPanel.add(myFirstNameLabel, gbc_myFirstNameLabel);

		JLabel myPayLabel = new JLabel(solde);
		GridBagConstraints gbc_myPayLabel = new GridBagConstraints();
		gbc_myPayLabel.anchor = GridBagConstraints.WEST;
		gbc_myPayLabel.insets = new Insets(0, 0, 5, 0);
		gbc_myPayLabel.gridx = 1;
		gbc_myPayLabel.gridy = 4;
        searchPanel.add(myPayLabel, gbc_myPayLabel);

		soldeField = new JTextField();
		GridBagConstraints gbc_soldeField = new GridBagConstraints();
		gbc_soldeField.anchor = GridBagConstraints.WEST;
		gbc_soldeField.insets = new Insets(0, 0, 5, 0);
		gbc_soldeField.gridx = 1;
		gbc_soldeField.gridy = 5;
        searchPanel.add(soldeField, gbc_soldeField);
		soldeField.setColumns(10);

		JButton btnValider = new JButton("Valider");
		GridBagConstraints gbc_btnValider = new GridBagConstraints();
		gbc_btnValider.anchor = GridBagConstraints.CENTER;
		gbc_btnValider.insets = new Insets(0, 0, 5, 0);
		gbc_btnValider.gridx = 1;
		gbc_btnValider.gridy = 6;
        searchPanel.add(btnValider, gbc_btnValider);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valider(codePermanent);
			}
		});

		JButton button = new JButton("Annuler");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.CENTER;
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 0;
		gbc_button.gridy = 6;
        searchPanel.add(button, gbc_button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAndHide();
			}
		});

		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent ce) {
				soldeField.requestFocusInWindow();
			}
		});

		//Register an event handler that puts the text into the option pane.
		soldeField.addActionListener(this);
	}

	//////////////////////////////////////////////////////////////////////

	private void valider(String codeUtilisateur) {
		if (!soldeField.getText().equals("") ) {
			//we're done; clear and dismiss the dialog
			try {
				montant = (int) Integer.parseInt(soldeField.getText());
				DBManager.connectDataBase();
				System.out.println("solde ajouté : "+montant);
				DBManager.addSoldeTo(montant,codeUtilisateur);
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