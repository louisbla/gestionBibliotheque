package View.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//property change stuff
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import DAO.DBManager;
import View.PanelLivre;

/* 1.4 example used by DialogDemo.java. */
public class DialogEmprunt extends JDialog
                   implements ActionListener,
                              PropertyChangeListener {
    private String titleText = null;

    private JComboBox<String> comboUtilisateur = new JComboBox<>();
    private JComboBox<String> comboISBN = new JComboBox<>();

    private PanelLivre dd;

    private String magicWord;
    private JOptionPane optionPane;

    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";

    /**
     * Returns null if the typed string was invalid;
     * otherwise, returns the string as the user entered it.
     */
    public String getValidatedText() {
        return titleText;
    }

    /** Creates the reusable dialog. */
    public DialogEmprunt(Frame aFrame) {
        super(aFrame, true);

        setTitle("Emprunter un livre");
        setBounds(500, 400, 450, 300);
        
        String[] listUsers = DBManager.getAllUserId();
        String[] listISBN = DBManager.getAllISBN();
        

        comboUtilisateur.setModel(new DefaultComboBoxModel<String>(listUsers));
        comboISBN.setModel(new DefaultComboBoxModel<String>(listISBN));
        
        //Create an array of the text and components to be displayed.
        String msgString1 = "Code permanent : ";
        String msgString2 = "N° ISBN :";

        Object[] array = {msgString1, comboUtilisateur, msgString2, comboISBN};

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
                    titleText = comboUtilisateur.getSelectedItem().toString();
                String ucText = titleText.toUpperCase();
                if (!comboUtilisateur.getSelectedItem().toString().equals("") && !comboISBN.getSelectedItem().toString().equals("")) {
                    //we're done; clear and dismiss the dialog
                	try {
						DBManager.connectDataBase();
						DBManager.addEmprunt(comboUtilisateur.getSelectedItem().toString(), comboISBN.getSelectedItem().toString(), 5);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						DBManager.closeDatabase();
					}
                    clearAndHide();
                } else {
                    //text was invalid
                    JOptionPane.showMessageDialog(
                                    DialogEmprunt.this,
                                    "Veuillez entrer toutes les informations",
                                    "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                    titleText = null;
                }
            } else { //user closed dialog or clicked cancel
                clearAndHide();
            }
        }
    }

    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        setVisible(false);
    }
}