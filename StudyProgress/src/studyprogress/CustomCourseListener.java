/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studyprogress;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 *Button listener class for graphical custom Course creation screen.
 * @author ausr
 */
public class CustomCourseListener implements ActionListener {
    /**
     * The logged-in user. Cannot be null.
     */
    private Student user;
    /**
     * The StudyGUI using this listener.
     * Is never null.
     */
    private StudyGUI gui;
    /**
     * JList containing Module info.
     * Is never null.
     */
    private JList modules;
    /**
     * JList containing Course info.
     * Is never null.
     */
    private JList courses;
    /**
     * Course name text field.
     * Is never null.
     */
    private JTextField name;
    /**
     * Course credits text field.
     * Is never null.
     */
    private JTextField credits;
    /**
     * Course year text field.
     * Is never null.
     */
    private JTextField year;
    /**
     * Course grade text field.
     */
    private JTextField grade;
    /**
     * Course semester button group.
     * Is never null.
     */
    private ButtonGroup semester;
    /**
     * The JFrame from which the action initiated.
     * Is never null.
     */
    private JFrame source;
    /**
     * The summary container.
     * Is never null.
     */
    private Container summary;
    /**
     * Class constructor.
     * @param source The JFrame from which the action initiated.
     * @param user The logged-in user.
     * @param gui The StudyGUI using this listener.
     * @param modules JList containing Module info.
     * @param courses JList containing Course info.
     * @param name Name text field.
     * @param credits Credits text field.
     * @param year Year text field.
     * @param grade Grade text field.
     * @param semester Semester text field.
     * @param summary Summary Container.
     */
    public CustomCourseListener(JFrame source, Student user, StudyGUI gui, JList modules, JList courses, JTextField name, JTextField credits, JTextField year, JTextField grade, ButtonGroup semester, Container summary) {
        this.user = user;
        this.gui = gui;
        this.modules = modules;
        this.courses = courses;
        this.name =name;
        this.credits = credits;
        this.year = year;
        this.grade = grade;
        this.semester = semester;
        this.source = source;
        this.summary = summary;
    }
    /**
     * This method runs in case an action is registered by the listener.
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonlabel = ((JButton)e.getSource()).getText();
        int moduleindex = modules.getSelectedIndex();
        int courseindex = courses.getSelectedIndex();
        String namestring = name.getText();
        String creditstring = credits.getText();
        String yearstring = year.getText();
        String gradestring = grade.getText();
        String semesterstring;
        try {
            semesterstring = ((ButtonModel)semester.getSelection()).getActionCommand();
        } catch (Exception notselected) {
            semesterstring = null;
        }
        
        if(buttonlabel.equals("Luo kurssi")) {
            if(checkCourseData(creditstring, yearstring, gradestring, semesterstring)) {
                float credits = Float.parseFloat(creditstring);
                int year = Integer.parseInt(yearstring);
                int grade = Integer.parseInt(gradestring);
                try {
                    user.addCourseToModule(moduleindex, new Course(namestring, credits, semesterstring, year, grade ));
                    modules.setListData(user.modulesToStringArray());
                    courses.setListData(user.moduleCoursesToStringArray(moduleindex));
                    gui.updateSummary(summary.getComponent(0),summary.getComponent(3));
                    modules.setSelectedIndex(moduleindex);
                } catch (Exception ex) {
                    System.out.println("Virhe kurssin luomisessa");
                }
                source.dispose();
                
            }
            
        }
        else if (buttonlabel.equals("Peruuta")) {
            source.dispose();
        }
    }
    /**
     * Helper method for checking Course data.
     * @param credits Course credits.
     * @param year Course year.
     * @param grade Course grade.
     * @param semester Course semester.
     * @return True if Course data follows all rules, otherwise false.
     */
    private boolean checkCourseData(String credits, String year, String grade, String semester) {
        float parsedcredits = -1.0f;
        int parsedyear = -1;
        int parsedgrade = -1;
        try {
            parsedyear = Integer.parseInt(year);
            parsedgrade = Integer.parseInt(grade);
            parsedcredits = Float.parseFloat(credits);
        } catch (Exception e) {
            return false;
        }        
        if(semester == null) {
            return false;
        }
        if(!(semester.equals("syksy") || semester.equals("kevät"))) {
            return false;
        }
        if(parsedcredits < 0.0f || parsedyear < 0 || parsedgrade < 0) {
        return false;
        }
        return true;
    }
}
