
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
        createbutton.addActionListener(new LoginButtonListener(this, manager, user, login, userinput, helptext));
        
        base.add(userinput);
        base.add(helptext);
        base.add(buttons);
        
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.pack();
 
        login.setVisible(true);
    }
    
    public void displayMainMenu(Student user) {
        this.user = user;
        JFrame mainmenu = new JFrame("Main Menu: Kirjautunut käyttäjänä "+this.user.getName());
        mainmenu.setPreferredSize(new Dimension(800,600));
        Container base = mainmenu.getContentPane();
        
        Container summary = new Container();
        summary.setLayout(new BoxLayout(summary, BoxLayout.Y_AXIS));
        
        Container modules = new Container();
        modules.setLayout(new BoxLayout(modules, BoxLayout.Y_AXIS));
        JList modulelist = new JList(user.modulesToStringArray());
        modulelist.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scroller = new JScrollPane(modulelist);
        scroller.setPreferredSize(new Dimension(160, 80));
        
        Container modulebuttons = new Container();
        modulebuttons.setLayout(new FlowLayout());
        JButton add = new JButton("Lisää valmis");
        JButton addcustom = new JButton("Lisää oma");
        JButton edit = new JButton("Muokkaa");
        JButton delete = new JButton("Poista");
        
        modulebuttons.add(add);
        modulebuttons.add(addcustom);
        modulebuttons.add(edit);
        modulebuttons.add(delete);
        
        Container moduledata = new Container();
        JLabel moduletext = new JLabel("Placeholder for module data");
        moduledata.setLayout(new BoxLayout(moduledata,BoxLayout.Y_AXIS));
        
        moduledata.add(moduletext);
        modules.add(scroller);
        modules.add(modulebuttons);
        modules.add(moduledata);
        
        JLabel summarytext = new JLabel("Placeholder for summary");
        summary.add(summarytext);
        
        base.add(summary, BorderLayout.LINE_START);
        base.add(modules,BorderLayout.LINE_END);

        
        mainmenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainmenu.pack();
        mainmenu.setVisible(true);
    }
    private void addMainMenuModuleListeners(JButton add, JButton addcustom, JButton edit, JButton delete, JList Modulelist) {
        
    }
    
   
    
    
    
}
