
package studyprogress;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author ausr
 */
public class CreateCustomModuleListener implements ActionListener {
    private Student user;
    private StudyGUI gui;
    private JTextField name;
    private JTextField credits;
    private JLabel info;
    private JList modules;
    private JFrame source;
    public CreateCustomModuleListener(JFrame source, Student user, StudyGUI gui, JTextField name, JTextField credits, JLabel info, JList modules) {
        this.user = user;
        this.gui = gui;
        this.name = name;
        this.credits = credits;
        this.info = info;
        this.modules = modules;
        this.source = source;
    }
    public void actionPerformed(ActionEvent e) {
        String namestring = name.getText();
        String creditstring = credits.getText();
        if(!checkModuleData(namestring, creditstring)) {
            info.setText("Virheellinen nimi tai opintopistelaajuus! Kokonaisuutta ei luotu.");
        }
        else {
            user.addModule(namestring, Float.parseFloat(creditstring));
            modules.setListData(user.modulesToStringArray());
            source.dispose();
        }
    }
    private boolean checkModuleData(String name, String creditstring) {
        float credits = -1.0f;
        try {
            credits = Float.parseFloat(creditstring);
        } catch (Exception e) {
            return false;
        }
        if(credits > 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
