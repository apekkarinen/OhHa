
package studyprogress;
import java.util.*;
import java.io.*;
/**
 *The class representing an university student, the user of this software.
 * Every university course belongs to a module, so the Student class contains a list of
 * Module-objects.
 * 
 * @see Module
 * 
 * @author Antti Pekkarinen
 */



public class Student {
    
    private String name;
    private ArrayList<Module> modulelist;
    private ArrayList<Semester> semesterlist;
    private File studentdirectory;
    private File modulesfile;
    
    /**
     * Class constructor.
     * @param name The name of this Student.
     */
    
    public Student(String name) {
        this.name = name;
        this.modulelist = new ArrayList<Module>();
        studentdirectory = new File("data/"+name);
        modulesfile = new File("data/"+name+"/modules.txt");
        if(!modulesfile.exists()) {
            studentdirectory.mkdir();
            try {
                modulesfile.createNewFile();
            } catch (Exception e) {
                System.out.println("Opiskelijakansion luominen epäonnistui!");
            }
            
        }
        loadStudentData();
    }
    
    /**
     * 
     * @return The name of this Student.
     */
    
    public String getName() {
        return name;
    }
    
    /**
     * Constructs a Module with specified parameters and adds it to this Student's module list.
     * @param name Name of the Module to add.
     * @param totalcredits Total credit points needed for completing the Module.
     */
    
    public void addModule(String name, float totalcredits) {
        Module module = new Module(name,totalcredits);
        modulelist.add(module);
    }
    /**
     * Adds a specified Module directly to this Student's Module list.
     * Saves Student data to disk after adding the Module.
     * @param module The Module to add to this Student's Module list.
     */
     public void addModule(Module module) {
        modulelist.add(module);
    }
     
     /**
      * Deletes a Module based on list index from this Student's Module list.
      * Saves Student data to disk after deletion.
      * @param index List index of the Module to delete. Range: Integer in [0, number of Modules -1]
      */
     
    public void deleteModule(int index) {
        String modulename = modulelist.get(index).getName();
        String modulepath = "data/"+name+"/"+modulename+".txt";
        StudyProgressManager.deleteFile(modulepath);
        modulelist.remove(index);     
    }
    
    /**
     * Adds a Course to the specified Module of this Student. Module specified by list index.
     * Saves Student data to after adding the Course.
     * @param moduleindex List index of the module to add the Course to. Range: Integer in [0, number of Modules -1]
     * @param course The Course to add.
     */
    
    public void addCourseToModule(int moduleindex, Course course) {
        modulelist.get(moduleindex).addCourse(course);
    }
    /**
     *
     * @return Number of Modules belonging to this Student.
     */
    
    public int getNumberOfModules() {
        return modulelist.size();
    }
    /**
     * Returns the total number of Courses belonging to all Modules of this Student,
     * including all unfinished Courses.
     * @return Total number of this Student's Courses.
     */
    
    public int getTotalNumberOfCourses() {
        int sum = 0;
        for (Module module : modulelist) {
           sum = sum + module.getNumberOfCourses();
        }
        return sum;
    }
    /**
     * Loads Student data from disk. Loaded data includes all Modules
     * and Courses of this Student.
     */
 
    public void loadStudentData() {
        String modulename;
        this.modulelist.clear();
        try {
            Scanner scanner = new Scanner(modulesfile, "UTF-8");
            while(scanner.hasNextLine()) {
                modulename = scanner.nextLine();
                addModule(loadModule(modulename, "data/"+name+"/"+modulename+".txt"));
            }
        } catch (Exception e) {
            System.out.println("Virhe ladattaessa opiskelijatietoja!");
        }
        
        
    }
    
    /**
     * Writes Student data to disk. Written data includes all Modules
     * and Courses of this Student.
     */
    
    public void writeStudentData() {
        try {
            StudyProgressManager.deleteFile("data/"+name+"/modules.txt");
            PrintWriter namewriter = new PrintWriter(modulesfile, "UTF-8");
            for (Module module : modulelist) {
                namewriter.println(module.getName());
                writeModule(module);
            }
            namewriter.close();
            
        } catch (Exception e) {
            System.out.println("Virhe opiskelijatietojen tallentamisessa!");
        }
        
    }
    
    /**
     * Deletes a Course specified by list index from a Module also specified by list index.
     * @param moduleindex Index of the Module to delete from. Range: Integer in [0, number of Modules -1]
     * @param courseindex Index of the Course to delete. Range: Integer in [0, number of Courses in Module -1]
     */
    
    public void deleteCourseFromModule(int moduleindex, int courseindex) {
        modulelist.get(moduleindex).deleteCourse(courseindex);

    }
    /**
     * Gets the number of Courses in the Module specified by list index.
     * @param moduleindex The index of the specified Module.  Range: Integer in [0, number of Modules -1]
     * @return Number of Courses in the specified Module.
     */
    public int getModuleSize(int moduleindex) {
        return modulelist.get(moduleindex).getNumberOfCourses();
    }
    /**
     * Returns a String representation of the specified Module of this Student.
     * This representation includes all Courses of the Module.
     * @param moduleindex List index of the Module. Range: Integer in [0, number of Modules -1]
     * @return A String representation of the specified Module.
     */
    
    
    public String moduleToString(int moduleindex) {
        Module module = modulelist.get(moduleindex);
        return module.getName() + " (keskiarvo "+module.getModuleAverage()+", arvosana "+module.getModuleGrade()+")\n" +module.toString();
    }
    
    /**
     * Returns a String array of short String representations of all the Modules belonging to this Student.
     * These representations do not include Course names.
     * @return A String array of short String representations of this Student's Modules.
     */
    
    public String[] modulesToStringArray() {
        int size = modulelist.size();
        Module module;
        if(size > 0) {
            String[] modules = new String[size];
            for (int i = 0; i < size; i++) {
                module = modulelist.get(i);
                modules[i] = module.getName() + " (keskiarvo "+module.getModuleAverage()+", arvosana "+module.getModuleGrade()+")";
            }
            return modules;
        }
        else {
            return null;
        }
    }
    public String[] moduleCoursesToStringArray(int moduleindex) {
        try {
        Module module = modulelist.get(moduleindex);          

            int size = module.getNumberOfCourses();
            if(size >0) {
               String[] returnarray = new String[size];
                for (int i = 0; i < size; i++) {
                   returnarray[i] = module.getCourse(i).toString();
                }
                return returnarray; 
            }
            else {
                String[] empty = {"Ei kursseja"};
                return empty;

            }
        } catch(Exception e) {
            String[] empty = {"Ei kursseja"};
            return empty;
        }
    }
    
    /**
     * Returns a String representation of this Student including short summaries of all Modules.
     * This summary does not include Course names.
     * @return A String representation of this Student.
     */
    
    public String toString() {
        int index = 0;
        String studentstring = "Käyttäjä "+name+", Opintokokonaisuudet: \n  numero    nimi\n";
        for(Module module : modulelist) {
            studentstring = studentstring +"    ["+index +"] " + module.getName() + " (keskiarvo "+module.getModuleAverage()+", arvosana "+module.getModuleGrade()+")\n";
            index++;
        }
        return studentstring;
    }
    
    private Module loadModule(String name, String filepath) {
        File modulefile = new File(filepath);
        float modulecredits;
        String coursename;
        float coursecredits;
        int grade;
        String semester;
        int year;
        Module module;
        
        if(modulefile.exists()) {
            try {
                Scanner scanner = new Scanner(modulefile, "UTF-8");
                modulecredits = Float.parseFloat(scanner.nextLine());
                module = new Module(name, modulecredits);

                while(scanner.hasNextLine()) {
                        coursename = scanner.nextLine();
                        coursecredits = Float.parseFloat(scanner.nextLine());
                        semester = scanner.nextLine();
                        year = Integer.parseInt(scanner.nextLine());
                        grade = Integer.parseInt(scanner.nextLine());
                        module.addCourse(new Course(coursename,coursecredits,semester,year,grade));
                    }
                return module;

            } catch (Exception e) {
                System.out.println("Virhe luettaessa opintokokonaisuuksia!");
                return null;
            }
        }
        return null;
    }
    
    private void writeModule(Module module) {
        File modulefile = new File("data/"+name+"/"+module.getName()+".txt");
        int numberofcourses = module.getNumberOfCourses();
        Course course;
        try {
            PrintWriter writer = new PrintWriter(modulefile, "UTF-8");
            writer.println(module.getTotalCredits());
            for (int i = 0; i < numberofcourses ; i++) {
                course = module.getCourse(i);
                writer.println(course.getName());
                writer.println(course.getCreditPoints());
                writer.println(course.getSemester());
                writer.println(course.getYear());
                writer.println(course.getGrade());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Virhe opiskelijatietojen tallennuksessa!");
        }
        
    }
    private void createSemesterList() {
        int year;
        String semesterstring;
        int semester;
        int semesterindex;
        for(Module module : modulelist) {
            int size = module.getNumberOfCourses();
            for (int i = 0; i < size; i++) {
                Course course = module.getCourse(i);
                year = course.getYear();
                semesterstring = course.getSemester();
                semesterindex = semesterListContains(year, semesterstring);
                
                if(semesterindex >= 0) {
                    semesterlist.get(semesterindex).addCourse(course.getCreditPoints());
                }
                else {
                    semesterlist.add(new Semester(year, semesterstring, 1, course.getCreditPoints()));
                }
            }
        }
    }
    private int semesterListContains(int year, String semesterstring) {
        int listyear;
        int listsemester;
        int semester;
        int index = 0;
        
        if(semesterstring.equals("syksy")) {
            semester = Semester.FALL;
        }
        else if(semesterstring.equals("kevät")) {
            semester = Semester.SPRING;
        }
        else {
            semester = -1;
        }
        
        for(Semester testsemester : semesterlist) {
            listyear = testsemester.getYear();
            listsemester = testsemester.getSemester();
            if(listyear == year && listsemester == semester) {
                return index;
            }
            index++;
        }
        return -1;
             
    }
    
}
