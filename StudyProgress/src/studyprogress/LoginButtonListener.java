
package studyprogress;


import javax.swing.*;
import java.awt.event.*;
/**
 *Button listener class for graphical login screen.
 * @author ausr
 */
public class LoginButtonListener implements ActionListener {
    /**
     * The StudyProgressManager using this listener.
     * Is never null.
     */
    private StudyProgressManager manager;
    /**
     * The logged-in user.
     * Can be null, in which case the user has not yet logged in.
     * The methods of this class will work regardless of this variable being either null
     * or not null.
     */
    private Student user;
    /**
     * The JFrame from which the action initiated.
     * Is never null.
     */
    private JFrame source;
    /**
     * Text field for user-input username.
     * Is never null.
     */
    private JTextField userinput;
    /**
     * The help text label.
     * Is never null.
     */
    private JLabel helptext;
    /**
     * The StudyGUI using this listener.
     * Is never null.
     */
    private StudyGUI gui;
    /**
     * Class constructor.
     * @param gui The StudyGUI using this listener.
     * @param manager The StudyProgressManager using this listener.
     * @param user The logged-in user.
     * @param source JFrame from which the action initiated.
     * @param userinput User input text field.
     * @param helptext Help text JLabel.
     */
    public LoginButtonListener(StudyGUI gui, StudyProgressManager manager, Student user, JFrame source, JTextField userinput, JLabel helptext) {
        this.gui = gui;
        this.manager = manager;
        this.user = user;
        this.userinput = userinput;
        this.helptext = helptext;
        this.source = source;
    }
    /**
     * This method runs in case an action is registered by the listener.
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userinput.getText();
        if(!username.equals("")) {
            if(((JButton)e.getSource()).getText().equals("Kirjaudu")) {
                user = manager.logInUser(username);
                if(user == null) {
                    helptext.setText("Käyttäjää ei löytynyt!");
                }
                else {
                    source.dispose();
                    gui.displayMainMenu(user);
                }
            }

            else {
                manager.createNewUser(username);
                helptext.setText("Käyttäjä "+username+ " luotu onnistuneesti. Kirjaudu sisään");
            }
         }
    }
}
