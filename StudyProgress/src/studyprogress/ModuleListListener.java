
package studyprogress;

import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;

/**
 *
 * @author ausr
 */
public class ModuleListListener implements ListSelectionListener {
    private StudyProgressManager manager;
    private Student user;
    private StudyGUI gui;
    
    public ModuleListListener(StudyProgressManager manager, Student user, StudyGUI gui) {
        this.manager = manager;
        this.user = user;
        this.gui = gui;
    }
    
    public void valueChanged(ListSelectionEvent e) {
        
    }
}
