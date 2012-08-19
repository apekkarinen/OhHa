
package studyprogress;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
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
    
    public MainButtonListener(StudyProgressManager manager, Student user, StudyGUI gui, JList modules, JList courses) {
        this.manager = manager;
        this.user = user;
        this.gui = gui;
        this.modules = modules;
        this.courses = courses;
    }
    public void actionPerformed(ActionEvent e) {
        String buttonlabel = ((JButton)e.getSource()).getText();
        int moduleindex = modules.getSelectedIndex();
        int courseindex = courses.getSelectedIndex();
        
        if(buttonlabel.equals("Lisää valmis")) {
            
        }
        else if(buttonlabel.equals("Lisää oma")) {
            gui.displayCreateCustomModule(modules);
        }
        else if(buttonlabel.equals("Poista")) {
            if(moduleindex >= 0) {
                user.deleteModule(moduleindex);
                modules.setListData(user.modulesToStringArray());
            }
        }
        else if(buttonlabel.equals("Lisää valmis kurssi")) {
            
        }
        else if(buttonlabel.equals("Lisää oma kurssi")) {
            gui.displayCreateCustomCourse(courses);
        }
        
        else if(buttonlabel.equals("Poista kurssi")) {
            if(courseindex >= 0 && user.getModuleSize(moduleindex) > 0) {
                user.deleteCourseFromModule(moduleindex, courseindex);
                modules.setListData(user.modulesToStringArray());
                courses.setListData(user.moduleCoursesToStringArray(moduleindex));
            }
        }
        
        else {
            user.writeStudentData();
        }
    }
    
}
