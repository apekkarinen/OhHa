
package studyprogress;


import javax.swing.*;
import java.awt.event.*;
/**
 *Button listener class for graphical login screen.
 * @author ausr
 */
public class LoginButtonListener implements ActionListener {
    private StudyProgressManager manager;
    private Student user;
    private JFrame source;
    private JTextField userinput;
    private JLabel helptext;
    private StudyGUI gui;
    
    public LoginButtonListener(StudyGUI gui, StudyProgressManager manager, Student user, JFrame source, JTextField userinput, JLabel helptext) {
        this.gui = gui;
        this.manager = manager;
        this.user = user;
        this.userinput = userinput;
        this.helptext = helptext;
        this.source = source;
    }
    public void actionPerformed(ActionEvent e) {
        String username = userinput.getText();
        
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
