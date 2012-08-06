
package studyprogress;

import java.util.ArrayList;

/**
 *
 * @author Antti Pekkarinen
 */
public class Module {
    
    private String name;
    private float totalcreditsneeded;
    private ArrayList<Course> courselist;
    
    public Module(String name, float totalcreditsneeded) {
        this.name = name;
        this.totalcreditsneeded = totalcreditsneeded;
        this.courselist = new ArrayList<Course>();
    }
    
    public void addCourse(Course course) {
        courselist.add(course);
    }
    
    public String getName() {
        return this.name;
    }
    
    public float getTotalCredits() {
        return this.totalcreditsneeded;
    }
    
}
