
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
    
    /**
     * The StudyProgressManager using this listener.
     * Is never null.
     */
    private StudyProgressManager manager;
    /**
     * The logged-in user.
     * Is never null.
     */
    private Student user;
    /**
     * The StudyGUI using this listener.
     * Is never null.
     */
    private StudyGUI gui;
    /**
     * The info text label.
     * Is never null.
     */
    private JLabel info;
    /**
     * The JList containing Course info.
     * Is never null.
     */
    private JList courses;
    
    /**
     * Class constructor.
     * @param manager The StudyProgressManager using this listener.
     * @param user The logged-in user.
     * @param gui The StudyGUI using this listener.
     * @param info The info text label.
     * @param courses The JList containing Course info.
     */
    public ModuleListListener(StudyProgressManager manager, Student user, StudyGUI gui, JLabel info, JList courses) {
        this.manager = manager;
        this.user = user;
        this.gui = gui;
        this.info = info;
        this.courses = courses;
    }
    /**
     * Changes the Course list data if the Module list selection changes.
     * @param e The list selection event.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        int selectedindex  = list.getSelectedIndex();
        courses.setListData(user.moduleCoursesToStringArray(selectedindex));
        
        
    }
}
