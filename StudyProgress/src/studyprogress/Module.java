
package studyprogress;

import java.util.ArrayList;

/**
 *
 * @author Antti Pekkarinen
 */
public class Module {
    
    private String name;
    private float totalcreditsrequired;
    private ArrayList<Course> courselist;
    
    public Module(String name, float totalcreditsrequired) {
        this.name = name;
        this.totalcreditsrequired = totalcreditsrequired;
        this.courselist = new ArrayList<Course>();
    }
    
    public void addCourse(Course course) {
        courselist.add(course);
    }
    
    public String getName() {
        return name;
    }
    
    public float getTotalCredits() {
        return totalcreditsrequired;
    }
    public int getNumberOfCourses() {
        return courselist.size();
    }
    
}
