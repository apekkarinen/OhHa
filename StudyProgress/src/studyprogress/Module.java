
package studyprogress;

import java.util.ArrayList;

/**
 *A class containing the key data describing an university
 * study module, such as Basic Studies in Computer Science. A study module
 * consists of different courses, so the Module class contains a list of Course-objects.
 *
 * 
 * @author Antti Pekkarinen
 * @see Course
 */
public class Module {
    
    private String name;
    private float totalcreditsrequired;
    private ArrayList<Course> courselist;
    
    /**
     * Class constructor.
     * @param name Name of this Module, usually basic, intermediate or advanced studies of a subject.
     * @param totalcreditsrequired Total credit points needed for completing this Module.
     */
    
    public Module(String name, float totalcreditsrequired) {
        this.name = name;
        if(totalcreditsrequired > 0) {
            this.totalcreditsrequired = totalcreditsrequired;
        }
        else {
            this.totalcreditsrequired = 0.0f;
        }
        this.courselist = new ArrayList<Course>();
    }
    /**
     * Adds a given Course to this Module.
     * @param course The Course to add.
     */
    
    public void addCourse(Course course) {
        courselist.add(course);
    }
    
    /**
     * Search a Course belonging to this Module by index.
     * @param index Index of the Course, integer in range [0,module size - 1].
     * @return The Course specified by index parameter.
     */
     
    public Course getCourse(int index) {
        return courselist.get(index);
    }
    /**
     * 
     * @return The name of this Module.
     */
    
    public String getName() {
        return name;
    }
    /**
     * 
     * @return Total credits needed for completing this module.
     */
    
    public float getTotalCredits() {
        return totalcreditsrequired;
    }
    /**
     * 
     * @return Total number of courses in this Module.
     */
    public int getNumberOfCourses() {
        return courselist.size();
    }
    public float getTotalCreditsCompleted() {
        float sum = 0.0f;
        for(Course course : courselist) {
            sum += course.getCreditPoints();
        }
        return sum;
    }
    /**
     * Calculates the current credit point weighted grade average of this Module. Courses with a grade of 0
     * are treated as unfinished and therefore left out from this calculation.
     * @return Average credit point weighted grade of all finished Courses in this Module.
     */
    public float getModuleAverage() {
        float sum = 0.0f;
        int coursesfinished = 0;
        for (Course course : courselist) {
            if(course.getGrade() > 0) {
                sum = sum + (course.getGrade() * course.getCreditPoints());
                coursesfinished++;
            }
        }
        if(coursesfinished == 0) {
            return 0.0f;
        }
        else if(getTotalCreditsCompleted() < 0.5) {
            return 0.0f;
        }
        else {
            return (sum / getTotalCreditsCompleted());
        }
    }
    /**
     * Calculates the current overall grade of this module. The grade is
     * an integer in range [1,5]. This method uses the following formula for calculating the grade:
     * grade = (int)(Math.ceil(getModuleAverage() - 0.4999) + 0.1)
     * Note that the final grade cannot be greater than five.
     * @return The overall grade of this Module.
     */
    public int getModuleGrade() {
        int grade = (int)(Math.ceil(getModuleAverage() - 0.4999) + 0.1);
        
        if(grade > 5) {
            return 5;
        }
        else {
            return grade;
        }
    }
    /**
     * Deletes a Course specified by index from this Module.
     * @param index Index of the Course to delete, in range [0, module size - 1].
     */
    public void deleteCourse(int index) {
        courselist.remove(index);
    }
    /**
     * Returns a String representation of this Module, in other words 
     * returns a listing of Courses contained in this Module.
     * @return A String representation of this Module.
     */
    public String toString() {
        int index = 0;
        String modulestring = "";
        for(Course course : courselist) {
            modulestring = modulestring +"      [" + index +"]  "+ course.toString() +"\n";
        }
        return modulestring;
    }
    
}
