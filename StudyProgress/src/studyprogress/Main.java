
package studyprogress;

import javax.swing.SwingUtilities;

/**
 * Main class.
 *  @author Antti Pekkarinen
 */

public class Main {

    /**
     * Main method.
     * @param args The command line arguments.
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
