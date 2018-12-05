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

public class DialogUser extends JDialog implements ActionListener, PropertyChangeListener {

	private String titleText = null;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField codePerm;
    private JTextField passwordField;

    private JComboBox<String> comboBox = new JComboBox<>();

    private PanelLivre dd;

    private String magicWord;
    private JOptionPane optionPane;

    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";
    
    public String getValidatedText() {
        return titleText;
    }

    /** Creates the reusable dialog. */
    public DialogUser(Frame aFrame) {
        super(aFrame, true);
        
        setBounds(500, 400, 450, 350);

        nomField = new JTextField(10);
        prenomField = new JTextField(10);
        codePerm = new JTextField(10);
        passwordField = new JTextField(10);

        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"admin", "professeur", "etudiant"}));

        //Create an array of the text and components to be displayed.
        String msgString1 = "Nom : ";
        String msgString2 = "Prenom :";
        String msgString3 = "Code Permanent :";
        String msgString4 = "Mot de Passe";
        String msgString5 = "statut :";

        Object[] array = {msgString1, nomField, msgString2, prenomField, msgString3, codePerm,msgString4, passwordField, msgString5, comboBox};

        //Create an array specifying the number of dialog buttons
        //and their text.
        Object[] options = {btnString1, btnString2};

        //Create the JOptionPane.
        optionPane = new JOptionPane(array,
                                    JOptionPane.QUESTION_MESSAGE,
                                    JOptionPane.YES_NO_OPTION,
                                    null,
                                    options,
                                    options[0]);

        //Make this dialog display it.
        setContentPane(optionPane);

        //Handle window closing correctly.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                    optionPane.setValue(new Integer(
                                        JOptionPane.CLOSED_OPTION));
            }
        });

        //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
            	nomField.requestFocusInWindow();
            }
        });

        //Register an event handler that puts the text into the option pane.
        nomField.addActionListener(this);

        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
    }

    /** This method handles events for the text field. */
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
    }

    /** This method reacts to state changes in the option pane. */
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (isVisible()
         && (e.getSource() == optionPane)
         && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
             JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                //ignore reset
                return;
            }

            //Reset the JOptionPane's value.
            //If you don't do this, then if the user
            //presses the same button next time, no
            //property change event will be fired.
            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);

            if (btnString1.equals(value)) {
                    titleText = nomField.getText();
                String ucText = titleText.toUpperCase();
                if (!nomField.getText().equals("") && !prenomField.getText().equals("") && !codePerm.getText().equals("")) {
                    //we're done; clear and dismiss the dialog
                	try {
						DBManager.connectDataBase();
						DBManager.addUser(codePerm.getText(), prenomField.getText(), nomField.getText(), comboBox.getSelectedItem().toString(), passwordField.getText(), 0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						DBManager.closeDatabase();
					}
                    clearAndHide();
                } else {
                    //text was invalid
                	nomField.selectAll();
                    JOptionPane.showMessageDialog(
                                    DialogUser.this,
                                    "Veuillez entrer toutes les informations",
                                    "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                    titleText = null;
                    nomField.requestFocusInWindow();
                }
            } else { //user closed dialog or clicked cancel
                clearAndHide();
            }
        }
    }

    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
    	nomField.setText(null);
        setVisible(false);
    }
}
