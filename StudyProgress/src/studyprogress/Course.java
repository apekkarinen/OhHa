
package studyprogress;

/**
 *
 * @author Antti Pekkarinen
 */

public class Course {
    
    private String name;
    private float creditpoints;
    private int grade;
    
    
    
    public Course(String name, float creditpoints) {
        this.name = name;
        this.creditpoints = creditpoints;
        this.grade = 0;
    }
    
    public Course(String name, float creditpoints, int grade) {
        this.name = name;
        this.creditpoints = creditpoints;
        this.grade = grade;
    }
    
    
    public String getName() {
        return this.name;
    }
    
    public float getCreditPoints() {
        return this.creditpoints;
    }
    
    public int getGrade() {
        return this.grade;
    }
    
}
