
package studyprogress;
import java.util.*;
import java.io.*;

/**
 *Student is the class representing an university student, the user of this software.
 * Every university course belongs to a module, so the Student class contains a list of
 * Module-objects.
 * 
 * @see Module
 * 
 * @author Antti Pekkarinen
 */



public class Student {
    
    /**
     * The name of this Student.
     * Is never null.
     */
    private String name;
    /**
     * List of all Modules of this Student.
     * Is never null.
     */
    private ArrayList<Module> modulelist;
    /**
     * List of all Semesters of this Student.
     * Is never null.
     */
    private ArrayList<Semester> semesterlist;
    /**
     * This Student's directory on disk.
     * Is never null.
     */
    private File studentdirectory;
    /*
     * The Module file of this Student.
     * Is never null.
     */
    private File modulesfile;
    
    /**
     * Class constructor.
     * @param name The name of this Student.
     */
    
    public Student(String name) {
        this.name = name;
        this.semesterlist = new ArrayList<Semester>();
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
        createSemesterList();
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
        createSemesterList();
    }
    /**
     * Adds a specified Module directly to this Student's Module list.
     * Saves Student data to disk after adding the Module.
     * @param module The Module to add to this Student's Module list.
     */
     public void addModule(Module module) {
        modulelist.add(module);
        createSemesterList();
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
        createSemesterList();
    }
    /**
     * Gets the average of credit points earned in a Semester. Returns 0.0f if no Semesters
     * have been registered for this Student.
     * @return The average of credit points earned per Semester, a float >= 0.
     */
    public float getAverageCreditsPerSemester() {
        float sum = getTotalCreditsCompleted();
        int numberofsemesters = this.getNumberOfSemesters();
        if(numberofsemesters != 0) {
            return (sum /(float) numberofsemesters);
        }
        else {
            return 0.0f;
        }
        
    }
    
    /**
     * Adds a Course to the specified Module of this Student. Module specified by list index.
     * Saves Student data to after adding the Course.
     * @param moduleindex List index of the module to add the Course to. Range: Integer in [0, number of Modules -1]
     * @param course The Course to add.
     */
    
    public void addCourseToModule(int moduleindex, Course course) {
        modulelist.get(moduleindex).addCourse(course);
        createSemesterList();
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
     * Gets total credit points completed by this Student. Unfinished Courses (grade of 0)
     * are excluded from this total.
     * @return Total credit points completed by this Student, a float >= 0.0f.
     */
    public float getTotalCreditsCompleted() {
        float sum = 0.0f;
        for(Module module : modulelist) {
            sum += module.getTotalCreditsCompleted();
        }
        return sum;
    }
    /**
     * Gets total credits still needed for this Student to complete all registered Modules.
     * In other words, credit points still needed for this Student to graduate. Returns
     * 0.0f if this Student already has more credit points than required for graduating.
     * @return Total credit points still needed to complete all this Student's Modules.
     */
    public float getTotalCreditsRemaining() {
        float required = 0.0f;
        float completed = getTotalCreditsCompleted();
        for(Module module : modulelist) {
            required += module.getTotalCredits();
        }
        if(completed > required) {
            return 0.0f;
        }
        else {
            return required - completed;
        }
    }
    /**
     * Returns the number of semesters it will take for this Student to graduate
     * provided that the Student maintains current pace in their studies.
     * Returns -1.0f if pace is 0.01 or less or if the Student has under 0.1 credits to go.
     * @return The number of semesters still left for graduation with current studying pace.
     */
    
    public float semestersToGo() {
        float remaining = getTotalCreditsRemaining();
        float pace = getAverageCreditsPerSemester();
        if(pace > 0.01 && remaining > 0.1) {
            return remaining / pace;
        }
        else {
            return -1.0f;
        }
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
        createSemesterList();

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
     * Returns the name of a specified Module of this Student.
     * @param moduleindex List index of the Module. The indices start from 0.
     * @return Name of the Module specified by parameter moduleindex.
     */
    public String getModuleName(int moduleindex) {
        return modulelist.get(moduleindex).getName();
    }
    /**
     * Returns the number of Semesters during which this Student has studied. Unfinished Courses are
     * also included in this total.
     * @return Number of Semesters during which this Student has studied.
     */
    public int getNumberOfSemesters() {
        return semesterlist.size();
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
        String[] empty = {""};
        try {
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
                return empty;
            }
        } catch (Exception e) {
            return empty;
        }
    }
    /**
     * Return an array of String representations of the Courses of a single specified Module of this Student.
     * @param moduleindex List index of the desired Module. The indices start from 0.
     * @return Array of String representations of all Courses in the specified Module.
     */
    public String[] moduleCoursesToStringArray(int moduleindex) {
        String[] empty = {""};
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
                return empty;

            }
        } catch(Exception e) {
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
    /**
     * Returns a short but informative summary of this Student's studies so far including
     * total number of Modules, Courses and Semesters, credits still needed for graduation and
     * credit average per semester.
     * Also gives an estimate of how many semesters it will take for this Student to graduate.
     * 
     * @return Summary of this Student's studies so far.
     */
    public String getSummaryText() {
        int semestersleft = (int)Math.ceil(this.semestersToGo());
        float pace = this.getAverageCreditsPerSemester();
        String tip = "";
        String graduationestimate;
        String username = "Yhteenveto: Käyttäjä "+name;
        String modulesummary = "Sinulla on yhteensä "+this.getNumberOfModules()+" opintokokonaisuutta.";
        String coursesummary = "Sinulla on yhteensä "+this.getTotalNumberOfCourses()+" kurssia.";
        String semestersummary = "Olet opiskellut "+this.getNumberOfSemesters()+" lukukautta.";
        String creditsleftsummary = "Sinun on vielä kerättävä "+this.getTotalCreditsRemaining()+ " op valmistuaksesi.";
        String pacesummary = "Olet kerännyt keskimäärin "+pace+ " op per lukukausi.";
        if (semestersleft >= 0) {
            graduationestimate = "Nykyisellä opiskelutahdillasi valmistut "+semestersleft +" lukukaudessa";
        }
        else {
            graduationestimate = "Et ole vielä opiskellut yhtään kurssia";
        }
        if(pace > 0.0f && pace < 20.0f) {
            tip = "Opiskelutahtisi on erittäin hidas \n (< 20 op/lukukausi), kerää itsesi!";
        }
        if(pace > 20.0f && pace < 30.0f) {
            tip = "Opiskelutahtisi on hidas \n (< 30 op/lukukausi), nopeuta tahtia jatkossa.";
        }
        if(pace > 30.0f && pace < 35.0f) {
            tip = "Opiskelutahtisi on normaali\n (30-35 op/lukukausi), olet aikataulussa!";
        }
        if(pace > 35.0f) {
            tip = "Opiskelutahtisi on erinomainen\n (> 35 op/lukukausi), hyvää työtä!";
        }
        
        return "\n"+username+"\n\n"+modulesummary+"\n"+coursesummary+"\n"+semestersummary+"\n\n"+creditsleftsummary+"\n"+pacesummary+"\n"+graduationestimate+"\n\n"+tip;
    }
    
    /**
     * Loads a Module from the specified text file. This means the Module including all its Courses.
     * This method takes the filename parameter as is, in other words it does not append the .txt file extension.
     * @param name Name of the Module to load.
     * @param filepath Location of the Module to load.
     * @return The loaded Module.
     */
    public static Module loadModule(String name, String filepath) {
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
        else {
            System.out.println("Kokonaisuustiedostoa ei löydy!");
            return null;
        }

    }
    /**
     * Creates the Semester list for this Student by parsing through the
     * Module list and making a record of the semesters of all Courses. The resulting Semester
     * list is sorted from oldest to newest.
     */
    
    public void createSemesterList() {
        semesterlist.clear();
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
        Collections.sort(semesterlist);
    }
    /**
     * Returns an array of String representations of Semesters, including year
     * and time of year of the Semester.
     * @return An array of String representations of Semesters.
     */
    public String[][] createSemesterArray() {
        int numberofsemesters = this.getNumberOfSemesters();
        String[][] semesters = new String[numberofsemesters][3];
        for (int i = 0; i < numberofsemesters; i++) {
            Semester semester = semesterlist.get(i);
            semesters[i][0] = semester.getSemesterString() +" "+semester.getYear();
            semesters[i][1] = ""+semester.getNumberOfCourses();
            semesters[i][2] = ""+semester.getTotalCredits();
        }
        return semesters;
    }
    /**
     * Writes the data of a given Module to disk.
     * @param module The Module to write.
     */
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
    /**
     * Checks whether the semester list contains a specified semester and
     * if it does, returns the list index of the semester. If it doesn't, returns -1.
     * @param year Year of the semester.
     * @param semesterstring Time of year of the semester.
     * @return List index of the semester if found on the list or -1 if not found.
     */
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
            if((listyear == year) && (listsemester == semester)) {
                return index;
            }
            index++;
        }
        return -1;
             
    }
    
}
