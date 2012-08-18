
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
    
    public MainButtonListener(StudyProgressManager manager, Student user, StudyGUI gui, JList modules) {
        this.manager = manager;
        this.user = user;
        this.gui = gui;
        this.modules = modules;
    }
    public void actionPerformed(ActionEvent e) {
        String buttonlabel = ((JButton)e.getSource()).getText();
        int index = gui.getSelectedModule();
        
        if(buttonlabel.equals("Lisää valmis")) {
            
        }
        else if(buttonlabel.equals("Lisää oma")) {
            
        }
        else if(buttonlabel.equals("Poista")) {
            if(index >= 0) {
                user.deleteModule(index);
                modules.setListData(user.modulesToStringArray());
            }
        }
        else {
            
        }
    }
    
}
