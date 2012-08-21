
package studyprogress;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
/**
 *
 * @author ausr
 */

public class MainButtonListener implements ActionListener {
    private StudyProgressManager manager;
    private Student user;

    private StudyGUI gui;
    private JList modules;
    private JList courses;
    private Container summary;
    
    public MainButtonListener(StudyProgressManager manager, Student user, StudyGUI gui, JList modules, JList courses, Container summary) {
        this.manager = manager;
        this.user = user;
        this.gui = gui;
        this.modules = modules;
        this.courses = courses;
        this.summary = summary;
    }
    public void actionPerformed(ActionEvent e) {
        String buttonlabel = ((JButton)e.getSource()).getText();
        int moduleindex = modules.getSelectedIndex();
        int courseindex = courses.getSelectedIndex();
        
        if(buttonlabel.equals("Lisää valmis")) {
            
        }
        else if(buttonlabel.equals("Lisää oma")) {
            gui.displayCreateCustomModule(modules,summary);
        }
        else if(buttonlabel.equals("Poista")) {
            if(moduleindex >= 0) {
                user.deleteModule(moduleindex);
                modules.setListData(user.modulesToStringArray());
                gui.updateSummary(summary.getComponent(0));
            }
        }
        else if(buttonlabel.equals("Lisää valmis kurssi")) {
            
        }
        else if(buttonlabel.equals("Lisää oma kurssi")) {
            gui.displayCreateCustomCourse(courses, modules,summary);
        }
        
        else if(buttonlabel.equals("Poista kurssi")) {
            if(courseindex >= 0 && user.getModuleSize(moduleindex) > 0) {
                user.deleteCourseFromModule(moduleindex, courseindex);
                modules.setListData(user.modulesToStringArray());
                courses.setListData(user.moduleCoursesToStringArray(moduleindex));
                gui.updateSummary(summary.getComponent(0));
            }
        }
        
        else {
            user.writeStudentData();
        }
    }
    
}
