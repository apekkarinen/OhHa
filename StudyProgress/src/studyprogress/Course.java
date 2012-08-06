
package studyprogress;

/**
 *
 * @author ausr
 */

public class Course {
    
    private String name;
    private float creditpoints;
    
    
    
    public Course(String name, float creditpoints) {
        this.name = name;
        this.creditpoints = creditpoints;
    }
    
    public String getName() {
        return this.name;
    }
    
    public float getCreditPoints() {
        return this.creditpoints;
    }
    
}
