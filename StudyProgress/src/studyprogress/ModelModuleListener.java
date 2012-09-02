
package studyprogress;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 *Button listener class for the graphical model Module creation screen.
 * @author ausr
 */
public class ModelModuleListener implements ActionListener {
    /**
     * The StudyProgressManager using this listener.
     * Is never null.
     */
    private StudyProgressManager manager;
    /**
     * The StudyGUI using this listener.
     * Is never null.
     */
    private StudyGUI gui;
    /**
     * The logged-in user.
     * Is never null.
     */
    private Student user;
    /**
     * The JFrame from which the action initiated.
     * Is never null.
     */
    private JFrame source;
    /**
     * The JList containing model Module info.
     * Is never null.
     */
    private JList models;
    /**
     * The JList containing Module info.
     * Is never null.
     */
    private JList modules;
    /**
     * The JList containing Course info.
     * Is never null.
     */
    private JList courses;
    /**
     * The summary Container.
     */
    private Container summary;

    
    /**
     * Class constructor.
     * @param manager The StudyProgressManager using this listener.
     * @param gui The StudyGUI using this listener.
     * @param user The logged-in user.
     * @param source The JFrame from which the action initiated.
     * @param models The JList containing the model Module info.
     * @param modules The JList containing the Module info.
     * @param courses The JList containing the Course info.
     * @param summary The summary container.
     */
    public ModelModuleListener (StudyProgressManager manager,StudyGUI gui,Student user, JFrame source,JList models,JList modules, JList courses, Container summary) {
    this.manager = manager;
    this.gui = gui;
    this.user = user;
    this.source = source;
    this.models = models;
    this.modules = modules;
    this.courses = courses;
    this.summary = summary;

    }
    /**
     * This method runs in case an action is registered by the listener.
     * @param e The action event.
     */
    @Override
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
