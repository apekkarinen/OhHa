
package studyprogress;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 *Button listener class for the graphical model Module creation screen.
 * @author ausr
 */
public class CreateModelModuleListener implements ActionListener {
    private StudyProgressManager manager;
    private StudyGUI gui;
    private Student user;
    private JFrame source;
    private JList models;
    private JList modules;
    private JList courses;
    private Container summary;

    
    
    public CreateModelModuleListener (StudyProgressManager manager,StudyGUI gui,Student user, JFrame source,JList models,JList modules, JList courses, Container summary) {
    this.manager = manager;
    this.gui = gui;
    this.user = user;
    this.source = source;
    this.models = models;
    this.modules = modules;
    this.courses = courses;
    this.summary = summary;

    }
    
    public void actionPerformed(ActionEvent e) {
        String buttonlabel = ((JButton)e.getSource()).getText();
        if(buttonlabel.equals("Lisää kokonaisuus")) {
            user.addModule(Module.EmptyModuleCopy(manager.getModelModule(models.getSelectedIndex())));
            modules.setListData(user.modulesToStringArray());
            gui.updateSummary(summary.getComponent(0),summary.getComponent(3));
            modules.setSelectedIndex(0);
        }
        else {
            source.dispose();
        }
    }
}
