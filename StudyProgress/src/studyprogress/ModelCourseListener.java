
package studyprogress;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 *Button listener class for the graphical model Course creation screen.
 * @author ausr
 */
public class ModelCourseListener implements ActionListener {
    private StudyProgressManager manager;
    private StudyGUI gui;
    private Student user;
    private JFrame source;
    private JList modelcourses;
    private JTextField year;
    private JTextField grade;
    private ButtonGroup semester;
    private JList modules;
    private JList courses;
    private Container summary;
    private int moduleindex;
    /**
     * Class constructor.
     * @param manager The StudyProgressManager using this listener.
     * @param gui The StudyGUI using this listener.
     * @param user The logged-in user.
     * @param source The JFrame from which the action initiated.
     * @param modelcourses The JList containing model Course info.
     * @param year The year text field.
     * @param grade The grade text field.
     * @param semester The semester button group.
     * @param modules The JList containing Module info.
     * @param courses The JList containing Course info.
     * @param summary The summary container.
     * @param moduleindex Index of the Module to add Course to.
     */
    public ModelCourseListener(StudyProgressManager manager,StudyGUI gui,Student user, JFrame source,JList modelcourses,JTextField year,JTextField grade,ButtonGroup semester, JList modules, JList courses, Container summary, int moduleindex) {
    this.manager = manager;
    this.gui = gui;
    this.user = user;
    this.source = source;
    this.modelcourses = modelcourses;
    this.year = year;
    this.grade = grade;
    this.semester = semester;
    this.modules = modules;
    this.courses = courses;
    this.summary = summary;
    this.moduleindex = moduleindex;
    }
    /**
     * Method to run in case an action is registered by the listener.
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonlabel = ((JButton)e.getSource()).getText();
        String yearstring = year.getText();
        String gradestring = grade.getText();
        String semesterstring;
        if(buttonlabel.equals("Lisää kurssi")) {
            try {
              semesterstring = semester.getSelection().getActionCommand();  
            } catch (Exception ex) {
                semesterstring = null;
            }
            
            if(checkData(yearstring,gradestring,semesterstring)) {
                Module module = manager.getModelModule(manager.modelNameListContains(user.getModuleName(moduleindex)));
                Course course = module.getCourse(modelcourses.getSelectedIndex());
                course.setSemester(semesterstring);
                course.setYear(Integer.parseInt(yearstring));
                course.setGrade(Integer.parseInt(gradestring));
                user.addCourseToModule(moduleindex, course);
                modules.setListData(user.modulesToStringArray());
                courses.setListData(user.moduleCoursesToStringArray(moduleindex));
                gui.updateSummary(summary.getComponent(0),summary.getComponent(3));
                modules.setSelectedIndex(moduleindex);
            }
        }
        else {
            source.dispose();
        }
    }
    /**
     * Helper method for checking user input Course data.
     * @param yearstring The year input by user.
     * @param gradestring The grade input by user.
     * @param semesterstring The semester input by user using the semester button group.
     * @return True if data follows the rules for a Course, otherwise false.
     */
    public boolean checkData(String yearstring, String gradestring, String semesterstring) {
        int year = -1;
        int grade = -1;
        try {
            year = Integer.parseInt(yearstring);
            grade = Integer.parseInt(gradestring);
            
        } catch (Exception e) {
            return false;
        }
        if(semesterstring == null) {
            return false;
        }
        if(year >= 0 && grade >= 0 && (semesterstring.equals("syksy") || semesterstring.equals("kevät"))) {
            return true;
        }
        else {
            return false;
        }
    }
}
