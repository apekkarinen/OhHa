/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studyprogress;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 *
 * @author ausr
 */
public class CreateCustomCourseListener implements ActionListener {
    private Student user;
    private StudyGUI gui;
    private JList modules;
    private JList courses;
    private JTextField name;
    private JTextField credits;
    private JTextField year;
    private JTextField grade;
    private ButtonGroup semester;
    private JFrame source;
    private Container summary;
    
    public CreateCustomCourseListener(JFrame source, Student user, StudyGUI gui, JList modules, JList courses, JTextField name, JTextField credits, JTextField year, JTextField grade, ButtonGroup semester, Container summary) {
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
    
    public void actionPerformed(ActionEvent e) {
        String buttonlabel = ((JButton)e.getSource()).getText();
        int moduleindex = modules.getSelectedIndex();
        int courseindex = courses.getSelectedIndex();
        String namestring = name.getText();
        String creditstring = credits.getText();
        String yearstring = year.getText();
        String gradestring = grade.getText();
        String semesterstring = ((ButtonModel)semester.getSelection()).getActionCommand();
        
        if(buttonlabel.equals("Luo kurssi")) {
            if(checkCourseData(creditstring, yearstring, gradestring, semesterstring)) {
                float credits = Float.parseFloat(creditstring);
                int year = Integer.parseInt(yearstring);
                int grade = Integer.parseInt(gradestring);
                user.addCourseToModule(moduleindex, new Course(namestring, credits, semesterstring, year, grade ));
                courses.setListData(user.moduleCoursesToStringArray(moduleindex));
                modules.setListData(user.modulesToStringArray());
                gui.updateSummaryComponents(summary.getComponents());
                source.dispose();
                
            }
            
        }
        else if (buttonlabel.equals("Peruuta")) {
            source.dispose();
        }
    }
    private boolean checkCourseData(String credits, String year, String grade, String semester) {
        try {
            Integer.parseInt(year);
            Integer.parseInt(grade);
            Float.parseFloat(credits);
        } catch (Exception e) {
            return false;
        }
        if(!(semester.equals("syksy") || semester.equals("kevät"))) {
            return false;
        }
        return true;
    }
}
