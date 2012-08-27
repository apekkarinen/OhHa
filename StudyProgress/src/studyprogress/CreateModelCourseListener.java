
package studyprogress;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 *
 * @author ausr
 */
public class CreateModelCourseListener implements ActionListener {
    private StudyProgressManager manager;
    private StudyGUI gui;
    private Student user;
    private JFrame source;
    private JList models;
    private JTextField year;
    private JTextField grade;
    private ButtonGroup semester;
    
    public CreateModelCourseListener(StudyProgressManager manager,StudyGUI gui,Student user, JFrame source,JList models,JTextField year,JTextField grade,ButtonGroup semester ) {
    this.manager = manager;
    this.gui = gui;
    this.user = user;
    this.source = source;
    this.models = models;
    this.year = year;
    this.grade = grade;
    this.semester = semester;
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
}
