
package studyprogress;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *Button listener class for graphical custom Module creation screen.
 * @author ausr
 */
public class CustomModuleListener implements ActionListener {
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
     * The Module name text field.
     * Is never null.
     */
    private JTextField name;
    /**
     * The Module credits text field.
     * Is never null.
     */
    private JTextField credits;
    /**
     * Info text label.
     * Is never null.
     */
    private JLabel info;
    /**
     * The JList containing Module info.
     * Is never null.
     */
    private JList modules;
    /**
     * The JFrame from which the action initiated.
     * Is never null.
     */
    private JFrame source;
    /**
     * The summary container.
     * Is never null.
     */
    private Container summary;
    /**
     * Class constructor.
     * @param source The JFrame from which the action initiated.
     * @param user The logged-in user.
     * @param gui The StudyGUI using this listener.
     * @param name The name text field.
     * @param credits The credits text field.
     * @param info The info text label.
     * @param modules The JList containing Module info.
     * @param summary The summary Container.
     */
    public CustomModuleListener(JFrame source, Student user, StudyGUI gui, JTextField name, JTextField credits, JLabel info, JList modules, Container summary) {
        this.user = user;
        this.gui = gui;
        this.name = name;
        this.credits = credits;
        this.info = info;
        this.modules = modules;
        this.source = source;
        this.summary = summary;
    }
    /**
     * This method runs in case an action is registered by the listener.
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String namestring = name.getText();
        String creditstring = credits.getText();
        if(!checkModuleData(namestring, creditstring)) {
            info.setText("Virheellinen nimi tai opintopistelaajuus! Kokonaisuutta ei luotu.");
        }
        else {
            user.addModule(namestring, Float.parseFloat(creditstring));
            modules.setListData(user.modulesToStringArray());
            gui.updateSummary(summary.getComponent(0),summary.getComponent(3));
            modules.setSelectedIndex(0);
            source.dispose();
        }
    }
    /**
     * Helper method for checking Module data.
     * @param name Name of the Module input by the user.
     * @param creditstring Credit points input by the user.
     * @return True if data follows the rules, otherwise false.
     */
    private boolean checkModuleData(String name, String creditstring) {
        float credits = -1.0f;
        try {
            credits = Float.parseFloat(creditstring);
        } catch (Exception e) {
            return false;
        }
        if(credits > 0 && !name.equals("")) {
            return true;
        }
        else {
            return false;
        }
    }
}
