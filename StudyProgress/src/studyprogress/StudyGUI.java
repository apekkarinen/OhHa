
package studyprogress;


import javax.swing.*;
import java.awt.*;
/**
 *The Graphical User Interface-class for StudyProgressManager
 * @author ausr
 */
public class StudyGUI implements Runnable {
    
    private StudyProgressManager manager;
    private Student user;
    
    /**
     *Class constructor, sets user field to null. 
     * @param manager The StudyProgressManager this StudyGUI uses.
     */
    
    public StudyGUI(StudyProgressManager manager) {
        this.manager = manager;
        this.user = null;
    }
    /**
     * Alternate class constructor, specifies the user field.
     * @param manager The StudyProgressManager this StudyGUI uses.
     * @param user The logged-in user Student.
     */
    
    public StudyGUI(StudyProgressManager manager, Student user) {
        this.manager = manager;
        this.user = user;
    }
    /**
     * Run-method from interface runnable. If user has not yet logged in, shows the
     * login screen. Otherwise shows the main menu.
     */
    @Override
    public void run() {
        
        if(user == null) {
            displayLogin();
        }
        if(user != null) {
            displayMainMenu(user);
        }
    }
    /**
     * Displays the graphical login screen.
     */
    public void displayLogin() {
        JFrame login = new JFrame("Kirjaudu sisään tai luo uusi käyttäjä");
        login.setPreferredSize(new Dimension(300, 150));
        
        Container base = login.getContentPane();
        base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
        
        Component box  = Box.createVerticalStrut(25);
        JTextField userinput = new JTextField();
        userinput.setAlignmentX(Component.CENTER_ALIGNMENT);
        userinput.setMaximumSize(new Dimension(160,25));
        JLabel helptext = new JLabel("Anna käyttäjänimesi");
        helptext.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Container buttons = createContainer(new FlowLayout());
        JButton loginbutton = new JButton("Kirjaudu");
        JButton createbutton = new JButton("Luo uusi käyttäjä");
        buttons.add(loginbutton);
        buttons.add(createbutton);
        loginbutton.addActionListener(new LoginButtonListener(this, manager, user, login, userinput, helptext));
        createbutton.addActionListener(new LoginButtonListener(this, manager, user, login, userinput, helptext));
        
        base.add(box);
        base.add(userinput);
        base.add(helptext);
        base.add(buttons);
        
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.pack();
 
        login.setVisible(true);
    }
    /**
     * Displays the graphical main menu screen.
     * @param user Logged-in user.
     */
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
        modulescroller.setPreferredSize(new Dimension(160, 80));
        
        JList courselist = createList(user.moduleCoursesToStringArray(modulelist.getSelectedIndex()));
        ModuleListListener modulelistener = new ModuleListListener(manager, user, this, moduleinfo, courselist);
        modulelist.addListSelectionListener(modulelistener);
        JScrollPane coursescroller = new JScrollPane(courselist);
        coursescroller.setPreferredSize(new Dimension(160, 120));
        
        Container modulebuttons = createContainer(new FlowLayout());
        MainButtonListener buttonlistener = new MainButtonListener(manager, user, this, mainmenu, modulelist, courselist, summary);
        JButton add = new JButton("Lisää valmis");
        JButton addcustom = new JButton("Lisää oma");
        JButton delete = new JButton("Poista");
        
        
        Container coursebuttons = createContainer(new FlowLayout());
        Container menubuttons = createContainer(new FlowLayout());
        Component modulesbox = Box.createVerticalStrut(40);
        Component coursesbox = Box.createVerticalStrut(40);
        JButton addcourse = new JButton("Lisää valmis kurssi");
        JButton addcustomcourse = new JButton("Lisää oma kurssi");
        JButton deletecourse = new JButton("Poista kurssi");
        JButton save = new JButton("Tallenna");
        JButton quit = new JButton("Lopeta");
        addMainButtonListeners(buttonlistener, add, addcustom, delete, addcourse, addcustomcourse, deletecourse, save, quit);
        
        modulebuttons.add(add);
        modulebuttons.add(addcustom);
        modulebuttons.add(delete);
        coursebuttons.add(addcourse);
        coursebuttons.add(addcustomcourse);
        coursebuttons.add(deletecourse);
        menubuttons.add(save);
        menubuttons.add(quit);
        
        modules.add(modulesbox);
        modules.add(modulelisttext);
        modules.add(modulescroller);
        modules.add(modulebuttons);
        modules.add(coursesbox);
        modules.add(moduleinfo);
        modules.add(coursescroller);
        modules.add(coursebuttons);
        modules.add(menubuttons);



        base.add(summary, BorderLayout.LINE_START);
        base.add(modules,BorderLayout.LINE_END);


        
        mainmenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainmenu.pack();
        mainmenu.setVisible(true);
    }
    /**
     * Helper method for adding button listeners to all buttons of the main menu.
     * @param buttonlistener The button listener to add.
     * @param add JButton for adding a model Module.
     * @param addcustom JButton for adding a custom Module.
     * @param delete JButton for deleting a Module.
     * @param addcourse JButton for adding a model course. 
     * @param addcustomcourse JButton for adding a custom Course.
     * @param deletecourse JButton for deleting a Course.
     * @param save JButton for saving all changes to disk.
     * @param quit JButton for quitting the program.
     */
    private void addMainButtonListeners(MainButtonListener buttonlistener, JButton add, JButton addcustom, JButton delete, JButton addcourse, JButton addcustomcourse, JButton deletecourse, JButton save, JButton quit) {
        delete.addActionListener(buttonlistener);
        add.addActionListener(buttonlistener);
        addcustom.addActionListener(buttonlistener);
        addcourse.addActionListener(buttonlistener);
        addcustomcourse.addActionListener(buttonlistener);
        deletecourse.addActionListener(buttonlistener);
        save.addActionListener(buttonlistener);
        quit.addActionListener(buttonlistener);
    }
    /**
     * Displays the graphical create custom Module -screen.
     * @param modules JList displaying the Module info.
     * @param summary JTextField containing the summary text.
     */
    public void displayCreateCustomModule(JList modules, Container summary) {
        JFrame createframe = new JFrame("Luo oma opintokokonaisuus");
        createframe.setPreferredSize(new Dimension(520,160));
        Container base = createframe.getContentPane();
        base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
        JLabel info = createCenteredLabel("Syötä kokonaisuuden tiedot:",0,0);
        Component box = Box.createVerticalStrut(25);
        
        JTextField name = createCenteredTextField(160,25);
        JTextField credits = createCenteredTextField(40,25);
        
        JLabel nameinfo = createCenteredLabel("Kokonaisuuden nimi",0,0);
        JLabel creditsinfo = createCenteredLabel("Kokonaisuuden opintopistelaajuus",0,0);
        
        JButton create = new JButton("Luo kokonaisuus");
        create.setAlignmentX(Component.CENTER_ALIGNMENT);
        create.addActionListener(new CustomModuleListener(createframe, user, this, name, credits, info, modules, summary));
        base.add(info);
        base.add(box);
        base.add(nameinfo);
        base.add(name);
        base.add(creditsinfo);
        base.add(credits);
        base.add(create);
        createframe.pack();
        createframe.setVisible(true);
    }
    /**
     * Displays the graphical create custom Course -screen.
     * @param courses JList displaying the Course data.
     * @param modules JList displaying the Module data.
     * @param summary JTextField displaying the summary text.
     */
    public void displayCreateCustomCourse(JList courses, JList modules, Container summary) {
        JFrame createframe = new JFrame("Luo oma kurssi");
        createframe.setPreferredSize(new Dimension(440,300));
        Container base = createframe.getContentPane();
        base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
        
        JLabel info = createCenteredLabel("Syötä kurssin tiedot:\n ",0,0);
        Component box = Box.createVerticalStrut(25);
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
        Container buttons = new Container();
        buttons.setLayout(new FlowLayout());
        JButton add = new JButton("Luo kurssi");
        add.addActionListener(new CustomCourseListener(createframe, user, this, modules, courses, name, credits, year, grade, semester,summary ));
        JButton cancel = new JButton("Peruuta");
        cancel.addActionListener(new CustomCourseListener(createframe, user, this, modules, courses, name, credits, year, grade, semester,summary ));
        
        buttons.add(add);
        buttons.add(cancel);
        semester.add(fall);
        semester.add(spring);
        radiobuttons.add(fall);
        radiobuttons.add(spring);
        addComponentsToCourseBase(base, info, box, nameinfo, creditinfo, yearinfo, gradeinfo, semesterinfo, name, credits, year, grade, radiobuttons, buttons);
        createframe.pack();
        createframe.setVisible(true);

    }
    /**
     * Helper method for adding all of the Components to create custom Course - screen's base container. 
     * @param base The base Container to add all Components to.
     * @param info Info text.
     * @param box Invisible box used to separate the info text from other Components.
     * @param nameinfo Name info text.
     * @param creditinfo Credit info text.
     * @param yearinfo Year info text.
     * @param gradeinfo Grade info text.
     * @param semesterinfo Semester info text.
     * @param name Name field.
     * @param credits Credits field.
     * @param year Year field.
     * @param grade Grade field.
     * @param radiobuttons Radiobuttons-Container.
     * @param buttons Buttons-Container.
     */
    private void addComponentsToCourseBase(Container base, JLabel info, Component box, JLabel nameinfo, JLabel creditinfo, JLabel yearinfo, JLabel gradeinfo, JLabel semesterinfo, JTextField name, JTextField credits, JTextField year, JTextField grade, Container radiobuttons, Container buttons) {
        base.add(info);
        base.add(box);
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
        base.add(buttons);
    }
    /**
     * Creates the summary Container for main menu screen.
     * @param gui StudyGUI for which to draw.
     * @param user Logged-in user.
     * @return The summary Container.
     */
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
    /**
     * Updates the contents of the summary Container.
     * @param text The summary text field to update.
     * @param scroller The semester summary scroller to update.
     */
    public void updateSummary(Component text, Component scroller) {
        ((JTextArea)text).setText(user.getSummaryText());
        ((SemesterTableModel)((JTable)((JViewport)((JScrollPane)scroller).getComponent(0)).getView()).getModel()).setData(user.createSemesterArray());
    }
    /**
     * Displays the graphical add model Module - screen.
     * @param modules The JList containing Module info.
     * @param courses The JList containing Course info.
     * @param summary The summary Container.
     */
    public void displayAddModelModule(JList modules, JList courses, Container summary) {
        JFrame addmodel = new JFrame("Lisää kokonaisuus");
        
        Container base = addmodel.getContentPane();
        base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
        
        JList modellist = createList(manager.modelModulesToStringArray());
        JScrollPane modelscroller = new JScrollPane(modellist);
        modelscroller.setPreferredSize(new Dimension(460, 80));
        Container buttons = createContainer(new FlowLayout());
        JButton add = new JButton("Lisää kokonaisuus");
        JButton back = new JButton("Takaisin");
        ModelModuleListener listener = new ModelModuleListener(manager, this, user, addmodel, modellist, modules, courses, summary);
        add.addActionListener(listener);
        back.addActionListener(listener);
        buttons.add(add);
        buttons.add(back);
        base.add(modelscroller);
        base.add(buttons);
        addmodel.pack();
        addmodel.setVisible(true);

    }
    /**
     * Displays the graphical add model Course - screen.
     * @param moduleindex Index of the Module to add the Course to.
     * @param modules The JList containing Module info.
     * @param courses The JList containing Course info.
     * @param summary The summary Container.
     */
    public void displayAddModelCourse(int moduleindex,JList modules, JList courses, Container summary) {
        JFrame addcourse = new JFrame("Lisää kurssi");
        Container base = addcourse.getContentPane();
        base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
        JLabel yearinfo = createCenteredLabel("vuosi", 100, 25);
        JTextField yearinput = createCenteredTextField(100,25);
        JLabel gradeinfo = createCenteredLabel("arvosana", 100, 25);
        JTextField gradeinput = createCenteredTextField(100,25);       
        Container radiobuttons = createContainer(new FlowLayout());
        JRadioButton fall = new JRadioButton("syksy");
        fall.setActionCommand("syksy");
        JRadioButton spring = new JRadioButton("kevät");
        spring.setActionCommand("kevät");
        ButtonGroup semester = new ButtonGroup();
        semester.add(fall);
        semester.add(spring);
        radiobuttons.add(fall);
        radiobuttons.add(spring);
        
        JList courselist = createList(manager.moduleCoursesToStringArray(manager.modelNameListContains(user.getModuleName(moduleindex))));
        JScrollPane coursescroller = new JScrollPane(courselist);
        coursescroller.setPreferredSize(new Dimension(420, 160));
        Container buttons = createContainer(new FlowLayout());
        JButton add = new JButton("Lisää kurssi");
        JButton back = new JButton("Takaisin");
        add.addActionListener(new ModelCourseListener(manager, this, user, addcourse, courselist, yearinput, gradeinput, semester, modules, courses, summary, moduleindex));
        back.addActionListener(new ModelCourseListener(manager, this, user, addcourse, courselist, yearinput, gradeinput, semester, modules, courses, summary, moduleindex));
        buttons.add(add);
        buttons.add(back);
        base.add(coursescroller);
        base.add(yearinfo);
        base.add(yearinput);
        base.add(gradeinfo);
        base.add(gradeinput);
        base.add(radiobuttons);
        base.add(buttons);
        addcourse.pack();
        addcourse.setVisible(true);
    }
    /**
     * Helper method to create a JList with vertical layout
     * and single selection.
     * @param data The contents of the list in an array.
     * @return The JList containing the data from the parameter array.
     */
    private JList createList(Object[] data) {
        if(data != null) {
            JList returnlist = new JList(data);
            returnlist.setLayoutOrientation(JList.VERTICAL);
            returnlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            return returnlist;
        }
        else {
            System.out.println("List data is null!");
            return new JList();
        }
    }
    /**
     * Helper method for creating a centered JTextField
     * @param maxwidth Maximum width of the text field.
     * @param maxheight Maximum height of the text field. 
     * @return A Centered JTextfield.
     */
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
        /**
         * Helper method for creating a centered JLabel.
         * @param text Text contents of the JLabel to create.
         * @param maxwidth Maximum width of the JLabel.
         * @param maxheight Maximum height of the JLabel.
         * @return A centered JLabel.
         */
        private JLabel createCenteredLabel(String text, int maxwidth, int maxheight) {
           JLabel label = new JLabel(text);
           label.setAlignmentX(Component.CENTER_ALIGNMENT);
           if(maxheight > 0 && maxwidth > 0) {
               label.setMaximumSize(new Dimension(maxwidth, maxheight));
           }
           return label;
        }
        /**
         * A helper method for creating a Container with a given layout.
         * @param mgr The layout manager for the Container.
         * @return The new Container.
         */
        private Container createContainer(LayoutManager mgr) {
            Container returncontainer = new Container();
            returncontainer.setLayout(mgr);
            return returncontainer;
        }

}
   
    
    
    

