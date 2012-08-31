
package studyprogress;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

/**
 *List listener class for graphical main menu's Module and Course lists.
 * @author ausr
 */
public class ModuleListListener implements ListSelectionListener {
    private StudyProgressManager manager;
    private Student user;
    private StudyGUI gui;
    private JLabel info;
    private JList courses;
    
    public ModuleListListener(StudyProgressManager manager, Student user, StudyGUI gui, JLabel info, JList courses) {
        this.manager = manager;
        this.user = user;
        this.gui = gui;
        this.info = info;
        this.courses = courses;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        int selectedindex  = list.getSelectedIndex();
        courses.setListData(user.moduleCoursesToStringArray(selectedindex));
        
        
    }
}
