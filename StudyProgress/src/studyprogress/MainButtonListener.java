
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
    private JFrame source;
    private StudyGUI gui;
    
    public MainButtonListener(StudyProgressManager manager, Student user, JFrame source,StudyGUI gui) {
        this.manager = manager;
        this.user = user;
        this.source = source;
        this.gui = gui;
    }
    public void actionPerformed(ActionEvent e) {
        String buttonlabel = ((JButton)e.getSource()).getText();
        if(buttonlabel.equals("Lisää valmis")) {
            
        }
        else if(buttonlabel.equals("Lisää oma")) {
            
        }
        else if(buttonlabel.equals("Muokkaa")) {
            
        }
        else if(buttonlabel.equals("Poista")) {
            
        }
        else {
            
        }
    }
    
}
