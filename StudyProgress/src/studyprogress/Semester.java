
package studyprogress;

/**
 *Semester is a class containing the credit point total and number of Courses finished during a
 * specified Semester.
 * 
 * @author ausr
 */
public class Semester implements Comparable<Semester> {
    /**
     * The year of this Semester.
     * Negative values not allowed.
     */
    private int year;
    /**
     * Time of year of this Semester.
     * Either 0 for fall or 1 for spring.
     */
    private int semester;
    /**
     * Total number of Courses the user has had in this Semester.
     */
    private int numberofcourses;
    /**
     * Total credit points the user has achieved in this Semester.
     */
    private float totalcredits;
    /**
     * Constant for signaling that the time of year for a Semester is fall.
     */
    public static final int FALL = 0;
    /**
     * Constant for signaling that the time of year for a Semester is spring.
     */
    public static final int SPRING = 1;
    
    /**
     * Class constructor, checks for correct parameters.
     * @param year Year in which the Semester takes place, a positive integer.
     * @param semester String specifying whether this Semester is a fall or a spring one. Must be either "syksy" or "kevät". 
     * @param numberofcourses Number of Courses taken during this Semester, unfinished Courses included.
     * @param totalcredits Total credit points awarded from Courses taken during this Semester, unfinished Courses included.
     */
    public Semester(int year, String semester, int numberofcourses, float totalcredits) {
        if(year > 0) {
            this.year = year;
        }
        else {
            this.year = -1;
        }
        if(semester.equals("syksy")) {
            this.semester = FALL;
        }
        else if(semester.equals("kevät")) {
            this.semester = SPRING;
        }
        else {
            this.semester = -1;
        }
        if(numberofcourses >= 0) {
            this.numberofcourses = numberofcourses;
        }
        else {
            this.numberofcourses = 0;
        }
        if(totalcredits < 0) {
            this.totalcredits = 0.0f;
        } 
        else {
            this.totalcredits = totalcredits;
        }
    }
    /**
     * Gets the year in which this Semester takes place.
     * @return Semester year, a positive integer.
     */
    public int getYear() {
        return year;
    }
    /**
     * Returns an integer specifying whether this is a fall or a spring Semester. A value of 0
     * means fall, 1 equals spring.
     * @return Semester.FALL (0) or Semester.SPRING (1) specifying the time of year of this Semester.
     */
    public int getSemester() {
        return semester;
    }
    /**
     * Returns a String specifying the time of year of this Semester. "syksy" for fall or "kevät" for spring.
     * @return String specifying the time of year of this Semester.
     */
    public String getSemesterString() {
        if(this.semester == 0) {
            return "syksy";
        }
        else {
            return "kevät";
        }
    }
    /**
     * Returns the credit point total awarded from Courses taken during this Semester.
     * @return Credit points gained during this semester, a positive float.
     */
    public float getTotalCredits() {
        return totalcredits;
    }
    /**
     * Returns total number of Courses taken during this Semester.
     * @return Total number of Courses taken during this Semester, an integer >= 0.
     */
    public int getNumberOfCourses() {
        return numberofcourses;
    }
    /**
     * Adds a Course to this Semester, in other words adds the credits parameter
     * to this Semester's total credits and increments the number of courses by one.
     * @param credits The credit points of the Course to add, a positive float.
     */
    public void addCourse(float credits) {
        if(credits > 0) {
            this.totalcredits += credits;
            this.numberofcourses++;
        }
    }
    /**
     * Compares two Semesters to each other according to their natural order.
     * @param semester The Semester to compare this Semester to.
     * @return -1 if this Semester is earlier than the parameter, 0 if equal, 1 if later.
     */
    @Override
    public int compareTo(Semester semester) {
        if(this.year < semester.year) {
            return -1;
        }
        else if(this.year > semester.year) {
            return 1;
        }
        else if((this.year == semester.year) && (this.semester < semester.semester)) {
            return 1;
        }
        else if((this.year == semester.year) &&(this.semester > semester.semester)) {
            return -1;
        }
        else {
            return 0;
        }
    }
    
}
