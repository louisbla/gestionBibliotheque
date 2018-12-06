package View.dialog;

import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import DAO.DBManager;
import View.PanelLivre;

import java.beans.*; //property change stuff
import java.awt.*;
import java.awt.event.*;

/* 1.4 example used by DialogDemo.java. */
public class RoomDialog extends JDialog
                   implements ActionListener,
                              PropertyChangeListener {
    private String titleText = null;
    private JTextField nameField;
    private JComboBox<String> comboBoxSize = new JComboBox<>();
    private JComboBox<String> comboBoxTable = new JComboBox<>();
    private JComboBox<String> comboBoxProjector = new JComboBox<>();

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
    public RoomDialog(Frame aFrame) {
        super(aFrame, true);

        setTitle("Quiz");
        setBounds(500, 400, 450, 300);

        nameField = new JTextField(10);

        comboBoxSize.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4"}));
        comboBoxTable.setModel(new DefaultComboBoxModel<String>(new String[] {"Non", "Oui"}));
        comboBoxProjector.setModel(new DefaultComboBoxModel<String>(new String[] {"Non", "Oui"}));

        //Create an array of the text and components to be displayed.
        String msgString1 = "Numéro de salle :";
        String msgString2 = "Taille :";
        String msgString3 = "Tableau :";
        String msgString4 = "Projecteur :";

        Object[] array = {msgString1, nameField, msgString2, comboBoxSize, msgString3, comboBoxTable, msgString4, comboBoxProjector};

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
            	nameField.requestFocusInWindow();
            }
        });

        //Register an event handler that puts the text into the option pane.
        nameField.addActionListener(this);

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
                    titleText = nameField.getText();
                String ucText = titleText.toUpperCase();
                if (!nameField.getText().equals("")) {
                    //we're done; clear and dismiss the dialog
                	try {
						DBManager.connectDataBase();
						DBManager.addRoom(nameField.getText(), comboBoxSize.getSelectedIndex() + 1, comboBoxTable.getSelectedIndex(), comboBoxProjector.getSelectedIndex(), 1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						DBManager.closeDatabase();
					}
                    clearAndHide();
                } else {
                    //text was invalid
                	nameField.selectAll();
                    JOptionPane.showMessageDialog(
                    		RoomDialog.this,
                                    "Veuillez entrer toutes les informations",
                                    "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                    titleText = null;
                    nameField.requestFocusInWindow();
                }
            } else { //user closed dialog or clicked cancel
                clearAndHide();
            }
        }
    }

    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
    	nameField.setText(null);
        setVisible(false);
    }
}