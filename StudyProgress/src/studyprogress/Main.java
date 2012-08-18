
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
       StudyCLI cli = new StudyCLI(new StudyProgressManager());
        cli.showLogIn();
    }
}
