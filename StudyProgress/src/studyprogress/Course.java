
package studyprogress;

/**
 *
 * Course is the class containing the key data describing a single university course.
 * 
 * @author Antti Pekkarinen
 */

public class Course {
    /**
     * Name of this Course.
     */
    private String name;
    /**
     * Credit points of this Course. Negative values
     * are not allowed.
     */
    private float creditpoints;
    /**
     * Grade of the Course.
     * Negative values are not allowed.
     */
    private int grade;
    /**
     * Time of year of this Course.
     * Possible values are "syksy" and "kevät".
     */
    private String semester;
    /**
     * Year of this Course.
     * Negative values are not allowed.
     */
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
        if(creditpoints >= 0 && year >= 0) {
            this.creditpoints = creditpoints;
            this.year = year;
        }
        else {
            this.creditpoints = 0;
            this.year = 0;
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
     * Sets the time of year value of this Course to either fall or spring.
     * @param semester The time of year of this Course as String. Either "syksy" for fall or "kevät" for spring semester.
     */

    public void setSemester(String semester) {
        if(semester.equals("syksy") || semester.equals("kevät")) {
            this.semester = semester;
        }
    }
    /**
     * Sets the year of this Course.
     * @param year The year of this Course, a positive integer.
     */
    public void setYear(int year) {
        if(year >= 0) {
            this.year = year;
        }
    }
    /**
     * Sets the grade of this Course. Courses with a grade of zero (0) are treated as
     * unfinished or failed.
     * @param grade The grade of this Course, an integer in range [0,5].
     */
    public void setGrade(int grade) {
        if(grade >= 0 && grade <= 5) {
            this.grade = grade;
        }
    }
        /**
     * Returns a String representation of this Course-object containing course name,
     * credit points and grade.
     * 
     * @return A String representation of this Course.
     *
     */
    public String toString() {
        return name + " (" + creditpoints + " Op.): Arvosana: " + grade;
    }
    
}
