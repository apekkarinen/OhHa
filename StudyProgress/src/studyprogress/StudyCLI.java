
package studyprogress;

/**
 *
 * @author Antti Pekkarinen
 */
import java.util.*;

public class StudyCLI {
    private StudyProgressManager manager;
    private Student user;
    private Scanner userinput;
    
    public StudyCLI(StudyProgressManager manager) {
        this.manager = manager;
        this.user = null;
        this.userinput = new Scanner(System.in, "UTF-8");
    }
    
    
    
}
