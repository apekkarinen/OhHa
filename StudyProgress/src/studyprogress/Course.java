
package studyprogress;

/**
 *
 * A class containing the key data describing a single university course.
 * 
 * @author Antti Pekkarinen
 */

public class Course {
    
    private String name;
    private float creditpoints;
    private int grade;
    private String semester;
    private int year;
    
    /**
     * Course class constructor, sets course grade to zero.
     * If you want to create an unfinished course, use this constructor.
     * 
     * @param name Course name.
     * @param creditpoints Credit points awarded for the course.
     * @param semester The semester during which the course is completed (or during which an unfinished course is started).
     * @param year The year during which the course is completed (or during which an unfinished course is started).
     */
    
    
    public Course(String name, float creditpoints, String semester, int year) {
        this.name = name;
        this.creditpoints = creditpoints;
        this.grade = 0;
        this.semester = semester;
        this.year=year;
    }
    
    /**
     * Course class alternative constructor, sets course grade to a given integer value from 0 to 5.
     * @param name Course name.
     * @param creditpoints Credit points awarded for the Course.
     * @param semester The semester during which this Course is completed (or during which an unfinished Course is started).
     * @param year The year during which this Course is completed (or during which an unfinished Course is started).
     * @param grade The grade awarded for this Course. Integer in range [0,5].
     */
    
    public Course(String name, float creditpoints, String semester, int year, int grade) {
        this.name = name;
        this.creditpoints = creditpoints;
        this.semester = semester;
        this.year = year;
        if(grade >= 0 && grade < 6) {
            this.grade = grade;
        }
        else {
            this.grade = 0;
        }
    }
    /**
     * 
     * 
     * @return The name of this Course.
     */
    
    public String getName() {
        return name;
    }
    /**
     * 
     * @return The credit points awarded from this Course.
     */
    
    public float getCreditPoints() {
        return creditpoints;
    }
    
    /**
     * Returns the grade of this course, always in range [0,5].
     * 
     * @return The grade of this Course.
     */
    
    public int getGrade() {
        return grade;
    }
    
    /**
     * 
     * @return The semester during which this Course is completed (or during which an unfinished Course is started).
     */
    
    public String getSemester()  {
        return semester;
    }
    
    /**
     * 
     * @return The year during which this Course is completed (or during which an unfinished Course is started).
     */
    
    public int getYear() {
        return year;
    }
    /**
     * Returns a String representation of this Course-object containing course name,
     * credit points and grade.
     * 
     * @return A String representation of this Course.
     *
     */
    
    public String toString() {
        return name + "(" + creditpoints + " Op.): Arvosana: " + grade;
    }
    
}
