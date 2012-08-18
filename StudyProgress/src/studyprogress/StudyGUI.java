
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
    private int selectedmoduleindex;
    
    public StudyGUI(StudyProgressManager manager) {
        this.manager = manager;
        this.user = null;
        this.selectedmoduleindex = -1;
    }
    
    public StudyGUI(StudyProgressManager manager, Student user) {
        this.manager = manager;
        this.user = user;
        this.selectedmoduleindex = -1;
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
        JFrame mainmenu = new JFrame("Päävalikko: Kirjautunut käyttäjänä "+this.user.getName());
        mainmenu.setPreferredSize(new Dimension(800,600));
        Container base = mainmenu.getContentPane();
        
        Container summary = new Container();
        summary.setLayout(new BoxLayout(summary, BoxLayout.Y_AXIS));
        
        Container modules = new Container();
        modules.setLayout(new BoxLayout(modules, BoxLayout.Y_AXIS));
        
        JLabel modulelisttext = new JLabel("Lisää, poista ja muokkaa opintokokonaisuuksia");
        modulelisttext.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel moduleinfo = new JLabel("Kokonaisuuden kurssit");
        moduleinfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JList modulelist = createList(user.modulesToStringArray());
        JScrollPane modulescroller = new JScrollPane(modulelist);
        modulescroller.setPreferredSize(new Dimension(160, 40));
        
        JList courselist = createList(user.moduleCoursesToStringArray(this.getSelectedModule()));
        ModuleListListener modulelistener = new ModuleListListener(manager, user, this, moduleinfo, courselist);
        modulelist.addListSelectionListener(modulelistener);
        JScrollPane coursescroller = new JScrollPane(courselist);
        coursescroller.setPreferredSize(new Dimension(160, 120));
        
        Container modulebuttons = new Container();
        MainButtonListener buttonlistener = new MainButtonListener(manager, user, this, modulelist);
        modulebuttons.setLayout(new FlowLayout());
        JButton add = new JButton("Lisää valmis");
        JButton addcustom = new JButton("Lisää oma");
        JButton delete = new JButton("Poista");
        addModuleButtonListeners(buttonlistener, add, addcustom, delete);
        
        modulebuttons.add(add);
        modulebuttons.add(addcustom);
        modulebuttons.add(delete);
        
        modules.add(modulelisttext);
        modules.add(modulescroller);
        modules.add(modulebuttons);
        modules.add(moduleinfo);
        modules.add(coursescroller);
        
        JLabel summarytext = new JLabel("Placeholder for summary");
        summary.add(summarytext);
        
        base.add(summary, BorderLayout.LINE_START);
        base.add(modules,BorderLayout.LINE_END);

        
        mainmenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainmenu.pack();
        mainmenu.setVisible(true);
    }
    private void addModuleButtonListeners(MainButtonListener buttonlistener, JButton add, JButton addcustom, JButton delete) {
        delete.addActionListener(buttonlistener);
        add.addActionListener(buttonlistener);
        addcustom.addActionListener(buttonlistener);
    }
   
    public void setSelectedModule(int index) {
        if (index >= 0) {
            selectedmoduleindex = index;   
        }
    }
    
    public int getSelectedModule() {
        return selectedmoduleindex;
    }
    
    private JList createList(Object[] data) {
        if(data != null) {
            JList returnlist = new JList(data);
            returnlist.setLayoutOrientation(JList.VERTICAL);
            returnlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            return returnlist;
        }
        else {
            return new JList();
        }
        
        
    }
    
   
    
    
    
}
