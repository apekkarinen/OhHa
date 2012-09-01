
package studyprogress;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *Button listener class for graphical custom Module creation screen.
 * @author ausr
 */
public class CustomModuleListener implements ActionListener {
    private Student user;
    private StudyGUI gui;
    private JTextField name;
    private JTextField credits;
    private JLabel info;
    private JList modules;
    private JFrame source;
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
     * The action performed method.
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
