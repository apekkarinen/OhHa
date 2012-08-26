
package studyprogress;

import javax.swing.SwingUtilities;

/**
 *  @author Antti Pekkarinen
 */

public class Main {

    /**
     * Main class.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StudyProgressManager manager = new StudyProgressManager();
        if(args != null && args.length > 0 && args[0].equals("g")) {
            StudyGUI gui = new StudyGUI(manager);
            gui.displayLogin();
        }
        else {
            StudyCLI cli = new StudyCLI(manager);
             cli.showLogIn();
        }
    }
}
