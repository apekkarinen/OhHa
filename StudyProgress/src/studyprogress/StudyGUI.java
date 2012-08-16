
package studyprogress;

/**
 *The Graphical User Interface-class for StudyProgressManager
 * @author ausr
 */
import javax.swing.*;
import java.awt.*;

public class StudyGUI implements Runnable {
    
    private StudyProgressManager manager;
    private Student user;
    
    public StudyGUI(StudyProgressManager manager) {
        this.manager = manager;
        this.user = null;
    }
    
    public StudyGUI(StudyProgressManager manager, Student user) {
        this.manager = manager;
        this.user = user;
    }
    
    public void run() {
        
        if(user == null) {
            displayLogin();
        }
        if(user != null) {
            displayMainMenu(user);
        }
    }
    
    public void displayLogin() {
        JFrame login = new JFrame("Kirjaudu sisään tai luo uusi käyttäjä");
        login.setPreferredSize(new Dimension(300, 150));
        
        Container base = login.getContentPane();
        base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
        
        JTextField userinput = new JTextField();
        userinput.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel helptext = new JLabel("Anna käyttäjänimesi");
        helptext.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Container buttons = new Container();
        buttons.setLayout(new FlowLayout());
        JButton loginbutton = new JButton("Kirjaudu");
        JButton createbutton = new JButton("Luo uusi käyttäjä");
        buttons.add(loginbutton);
        buttons.add(createbutton);
        loginbutton.addActionListener(new LoginButtonListener(this, manager, user, login, userinput, helptext));
        
        base.add(userinput);
        base.add(helptext);
        base.add(buttons);
        
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.pack();
 
        login.setVisible(true);
    }
    
    public void displayMainMenu(Student user) {
        this.user = user;
        JFrame mainmenu = new JFrame("Main Menu: Kirjautunut käyttäjänä "+user.getName());
        mainmenu.setPreferredSize(new Dimension(800,600));
        Container base = mainmenu.getContentPane();
        
        mainmenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainmenu.pack();
        mainmenu.setVisible(true);
    }
    
    
    
}
