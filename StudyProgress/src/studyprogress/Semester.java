
package studyprogress;

/**
 *
 * @author ausr
 */
public class Semester implements Comparable<Semester> {
    private int year;
    private int semester;
    private int numberofcourses;
    private float totalcredits;
    public static final int FALL = 0;
    public static final int SPRING = 1;
    
    
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
    public int getYear() {
        return year;
    }
    public int getSemester() {
        return semester;
    }
    public float getTotalCredits() {
        return totalcredits;
    }
    public int getNumberOfCourses() {
        return numberofcourses;
    }
    public void addCourse(float credits) {
        if(credits > 0) {
            this.totalcredits += credits;
            this.numberofcourses++;
        }
    }
    public int compareTo(Semester semester) {
        if(this.year < semester.year) {
            return -1;
        }
        else if(this.year > semester.year) {
            return 1;
        }
        else if((this.year == semester.year) && (this.semester < semester.semester)) {
            return -1;
        }
        else if((this.year == semester.year) &&(this.semester > semester.semester)) {
            return 1;
        }
        else {
            return 0;
        }
    }
    
}
