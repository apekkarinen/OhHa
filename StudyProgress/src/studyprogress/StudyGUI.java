
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
        JFrame mainmenu = new JFrame("Päävalikko: Kirjautunut käyttäjänä "+this.user.getName());
        mainmenu.setPreferredSize(new Dimension(900,600));
        Container base = mainmenu.getContentPane();
        
        Container summary = drawSummary(this, user);
        
        Container modules = new Container();
        modules.setLayout(new BoxLayout(modules, BoxLayout.Y_AXIS));
        
        JLabel modulelisttext = createCenteredLabel("Lisää, poista ja muokkaa opintokokonaisuuksia",0,0);
        JLabel moduleinfo = createCenteredLabel("Kokonaisuuden kurssit",0,0);
        
        JList modulelist = createList(user.modulesToStringArray());
        JScrollPane modulescroller = new JScrollPane(modulelist);
        modulescroller.setPreferredSize(new Dimension(160, 40));
        
        JList courselist = createList(user.moduleCoursesToStringArray(modulelist.getSelectedIndex()));
        ModuleListListener modulelistener = new ModuleListListener(manager, user, this, moduleinfo, courselist);
        modulelist.addListSelectionListener(modulelistener);
        JScrollPane coursescroller = new JScrollPane(courselist);
        coursescroller.setPreferredSize(new Dimension(160, 120));
        
        Container modulebuttons = new Container();
        MainButtonListener buttonlistener = new MainButtonListener(manager, user, this, modulelist, courselist, summary);
        modulebuttons.setLayout(new FlowLayout());
        JButton add = new JButton("Lisää valmis");
        JButton addcustom = new JButton("Lisää oma");
        JButton delete = new JButton("Poista");
        
        
        Container coursebuttons = new Container();
        coursebuttons.setLayout(new FlowLayout());
        JButton addcourse = new JButton("Lisää valmis kurssi");
        JButton addcustomcourse = new JButton("Lisää oma kurssi");
        JButton deletecourse = new JButton("Poista kurssi");
        addMainButtonListeners(buttonlistener, add, addcustom, delete, addcourse, addcustomcourse, deletecourse);
        
        modulebuttons.add(add);
        modulebuttons.add(addcustom);
        modulebuttons.add(delete);
        coursebuttons.add(addcourse);
        coursebuttons.add(addcustomcourse);
        coursebuttons.add(deletecourse);
        
        modules.add(modulelisttext);
        modules.add(modulescroller);
        modules.add(modulebuttons);
        modules.add(moduleinfo);
        modules.add(coursescroller);
        modules.add(coursebuttons);

        JButton save = new JButton("Tallenna");
        save.addActionListener(new MainButtonListener(manager, user, this, modulelist, courselist, summary));
        modules.add(save);
        base.add(summary, BorderLayout.LINE_START);
        base.add(modules,BorderLayout.LINE_END);


        
        mainmenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainmenu.pack();
        mainmenu.setVisible(true);
    }
    private void addMainButtonListeners(MainButtonListener buttonlistener, JButton add, JButton addcustom, JButton delete, JButton addcourse, JButton addcustomcourse, JButton deletecourse) {
        delete.addActionListener(buttonlistener);
        add.addActionListener(buttonlistener);
        addcustom.addActionListener(buttonlistener);
        addcourse.addActionListener(buttonlistener);
        addcustomcourse.addActionListener(buttonlistener);
        deletecourse.addActionListener(buttonlistener);
    }
    
    public void displayCreateCustomModule(JList modules, Container summary) {
        JFrame createframe = new JFrame("Luo oma opintokokonaisuus");
        createframe.setPreferredSize(new Dimension(520,160));
        Container base = createframe.getContentPane();
        base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
        JLabel info = createCenteredLabel("Syötä kokonaisuuden tiedot:",0,0);
        
        JTextField name = createCenteredTextField(160,25);
        JTextField credits = createCenteredTextField(40,25);
        
        JLabel nameinfo = createCenteredLabel("Kokonaisuuden nimi",0,0);
        JLabel creditsinfo = createCenteredLabel("Kokonaisuuden opintopistelaajuus",0,0);
        
        JButton create = new JButton("Luo kokonaisuus");
        create.setAlignmentX(Component.CENTER_ALIGNMENT);
        create.addActionListener(new CreateCustomModuleListener(createframe, user, this, name, credits, info, modules, summary));
        base.add(info);
        base.add(nameinfo);
        base.add(name);
        base.add(creditsinfo);
        base.add(credits);
        base.add(create);
        createframe.pack();
        createframe.setVisible(true);
    }
    public void displayCreateCustomCourse(JList courses, JList modules, Container summary) {
        JFrame createframe = new JFrame("Luo oma kurssi");
        createframe.setPreferredSize(new Dimension(440,300));
        Container base = createframe.getContentPane();
        base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
        
        JLabel info = createCenteredLabel("Syötä kurssin tiedot:\n ",0,0);
        
        JLabel nameinfo = createCenteredLabel("Nimi",0,0);
        JTextField name = createCenteredTextField(160, 25);
        JLabel creditinfo = createCenteredLabel("Opintopistelaajuus",0,0);
        JTextField credits = createCenteredTextField(40, 25);
        JLabel yearinfo = createCenteredLabel("Vuosi",0,0);
        JTextField year = createCenteredTextField(40, 25);
        JLabel gradeinfo = createCenteredLabel("Arvosana",0,0);
        JTextField grade = createCenteredTextField(40, 25);
        JLabel semesterinfo = createCenteredLabel("Lukukausi",0,0);
        
        Container radiobuttons = new Container();
        radiobuttons.setLayout(new FlowLayout());
        JRadioButton fall = new JRadioButton("syksy");
        fall.setActionCommand("syksy");
        JRadioButton spring = new JRadioButton("kevät");
        spring.setActionCommand("kevät");
        ButtonGroup semester = new ButtonGroup();
        JLabel alert = createCenteredLabel("",0,0);
        Container buttons = new Container();
        buttons.setLayout(new FlowLayout());
        JButton add = new JButton("Luo kurssi");
        add.addActionListener(new CreateCustomCourseListener(createframe, user, this, modules, courses, name, credits, year, grade, semester,summary ));
        JButton cancel = new JButton("Peruuta");
        cancel.addActionListener(new CreateCustomCourseListener(createframe, user, this, modules, courses, name, credits, year, grade, semester,summary ));
        
        buttons.add(add);
        buttons.add(cancel);
        semester.add(fall);
        semester.add(spring);
        radiobuttons.add(fall);
        radiobuttons.add(spring);
        addComponentsToCourseBase(base, info, nameinfo, creditinfo, yearinfo, gradeinfo, semesterinfo, alert, name, credits, year, grade, radiobuttons, buttons);
        createframe.pack();
        createframe.setVisible(true);

    }
    private void addComponentsToCourseBase(Container base, JLabel info, JLabel nameinfo, JLabel creditinfo, JLabel yearinfo, JLabel gradeinfo, JLabel semesterinfo, JLabel alert, JTextField name, JTextField credits, JTextField year, JTextField grade, Container radiobuttons, Container buttons) {
        base.add(info);
        base.add(nameinfo);
        base.add(name);
        base.add(creditinfo);
        base.add(credits);
        base.add(yearinfo);
        base.add(year);
        base.add(gradeinfo);
        base.add(grade);
        base.add(semesterinfo);
        base.add(radiobuttons);
        base.add(alert);
        base.add(buttons);
    }
    public Container drawSummary(StudyGUI gui, Student user) {
        String[] columnnames = {"lukukausi", "kurssien määrä","opintopisteet"};
        Container summary = new Container();

        summary.setLayout(new BoxLayout(summary, BoxLayout.Y_AXIS));
        
        
        JTextArea summarytext = new JTextArea(user.getSummaryText());
        summarytext.setMaximumSize(new Dimension(350,250));
        Component component = Box.createVerticalStrut(20);
        
        JLabel semesterinfo = createCenteredLabel("Lukukausien tiedot",0,0);
        JTable semestertable = new JTable(new SemesterTableModel(user.createSemesterArray(), columnnames));
        semestertable.setPreferredScrollableViewportSize(new Dimension(350,200));
        JScrollPane semesterscroller = new JScrollPane(semestertable);
        semesterscroller.setMaximumSize(new Dimension(350,200));
        summary.add(summarytext);
        summary.add(component);
        summary.add(semesterinfo);
        summary.add(semesterscroller);
        return summary;
}
    public void updateSummary(Component text, Component scroller) {
        ((JTextArea)text).setText(user.getSummaryText());
        ((SemesterTableModel)((JTable)((JViewport)((JScrollPane)scroller).getComponent(0)).getView()).getModel()).setData(user.createSemesterArray());
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
        private JTextField createCenteredTextField(int maxwidth, int maxheight) {
           JTextField field =  new JTextField();
           field.setAlignmentX(Component.CENTER_ALIGNMENT);
           if(maxwidth > 0 && maxheight > 0) {
            field.setMaximumSize(new Dimension(maxwidth, maxheight));
            return field;
           }
           else {
               field.setMaximumSize(new Dimension(160, 25));
               return field;
           }
        }
        private JLabel createCenteredLabel(String text, int maxwidth, int maxheight) {
           JLabel label = new JLabel(text);
           label.setAlignmentX(Component.CENTER_ALIGNMENT);
           if(maxheight > 0 && maxwidth > 0) {
               label.setMaximumSize(new Dimension(maxwidth, maxheight));
           }
           return label;
        }

}
   
    
    
    

