
package studyprogress;

/**
 *
 * @author Antti Pekkarinen
 */

public class Course {
    
    private String name;
    private float creditpoints;
    private int grade;
    private String semester;
    private int year;
    
    
    
    public Course(String name, float creditpoints, String semester, int year) {
        this.name = name;
        this.creditpoints = creditpoints;
        this.grade = 0;
        this.semester = semester;
        this.year=year;
    }
    
    public Course(String name, float creditpoints, String semester, int year, int grade) {
        this.name = name;
        this.creditpoints = creditpoints;
        this.semester = semester;
        this.year = year;
        this.grade = grade;
        
    }
    
    
    public String getName() {
        return name;
    }
    
    public float getCreditPoints() {
        return creditpoints;
    }
    
    public int getGrade() {
        return grade;
    }
    public String getSemester()  {
        return semester;
    }
    
    public int getYear() {
        return year;
    }
    
    public String toString() {
        return name + "(" + creditpoints + " Op.): Arvosana: " + grade;
    }
    
}
