
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
    
    public Module(String name, float totalcreditsrequired) {
        this.name = name;
        this.totalcreditsrequired = totalcreditsrequired;
        this.courselist = new ArrayList<Course>();
    }
    
    public void addCourse(Course course) {
        courselist.add(course);
    }
    public Course getCourse(int index) {
        return courselist.get(index);
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
    public float getModuleAverage() {
        int sum = 0;
        int coursesfinished = 0;
        int totalnumberofcourses = courselist.size();
        for (Course course : courselist) {
            if(course.getGrade() > 0) {
                sum = sum + course.getGrade();
                coursesfinished++;
            }
        }
        if(coursesfinished == 0) {
            return 0.0f;
        }
        else {
            return (((float)sum) / ((float)coursesfinished));
        }
    }
    public int getModuleGrade() {
        int grade = (int)(Math.ceil(getModuleAverage() + 0.5) + 0.1);
        
        if(grade > 5) {
            return 5;
        }
        else {
            return grade;
        }
    }
    public void deleteCourse(int index) {
        courselist.remove(index);
    }
    public String toString() {
        int index = 0;
        String modulestring = "";
        for(Course course : courselist) {
            modulestring = modulestring +"      [" + index +"]  "+ course.toString() +"\n";
        }
        return modulestring;
    }
    
}
