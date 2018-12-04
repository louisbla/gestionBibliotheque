package View;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;

import DAO.DBManager;

import java.beans.*; //property change stuff
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.*;
import java.awt.event.*;
 
/* 1.4 example used by DialogDemo.java. */
class CustomDialog extends JDialog
                   implements ActionListener,
                              PropertyChangeListener {
    private String titleText = null;
    private JTextField titleField;
    private JTextField auteurField;
    private JTextField isbnField;

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
    public CustomDialog(Frame aFrame) {
        super(aFrame, true);
 
        setTitle("Quiz");
        setBounds(500, 400, 450, 300);
 
        titleField = new JTextField(10);
        auteurField = new JTextField(10);
        isbnField = new JTextField(10);
 
        //Create an array of the text and components to be displayed.
        String msgString1 = "Titre du livre : ";
        String msgString2 = "Auteur :";
        String msgString3 = "N° ISBN :";

        Object[] array = {msgString1, titleField, msgString2, auteurField, msgString3, isbnField};
 
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
            	titleField.requestFocusInWindow();
            }
        });
 
        //Register an event handler that puts the text into the option pane.
        titleField.addActionListener(this);
 
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
                    titleText = titleField.getText();
                String ucText = titleText.toUpperCase();
                if (!titleField.getText().equals("") && !auteurField.getText().equals("") && !isbnField.getText().equals("")) {
                    //we're done; clear and dismiss the dialog
                	DBManager.addBook(isbnField.getText(), auteurField.getText(), titleField.getText(), 1);

                	
                    clearAndHide();
                } else {
                    //text was invalid
                	titleField.selectAll();
                    JOptionPane.showMessageDialog(
                                    CustomDialog.this,
                                    "Veuillez entrer toutes les informations",
                                    "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                    titleText = null;
                    titleField.requestFocusInWindow();
                }
            } else { //user closed dialog or clicked cancel
                clearAndHide();
            }
        }
    }
 
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
    	titleField.setText(null);
        setVisible(false);
    }
}