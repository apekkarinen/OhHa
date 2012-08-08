
package studyprogress;

/**
 *
 * @author Antti Pekkarinen
 */

import java.util.*;
import java.io.*;

public class Student {
    
    private String name;
    private ArrayList<Module> modulelist;
    private File studentdirectory;
    private File modulesfile;
    
    
    public Student(String name) {
        this.name = name;
        this.modulelist = new ArrayList<Module>();
        studentdirectory = new File("data/"+name);
        modulesfile = new File("data/"+name+"/modules.txt");
        loadStudentData();
    }
    
    public String getName() {
        return name;
    }
    
    public void addModule(String name, float totalcredits) {
        Module module = new Module(name,totalcredits);
        modulelist.add(module);
    }
    
    public void addModule(Module module) {
        modulelist.add(module);
    }
    
    public int getNumberOfModules() {
        return modulelist.size();
    }
    
    private void loadStudentData() {
        String modulename;
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
    
    public void writeStudentData() {
        try {
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
    
    private Module loadModule(String name, String filepath) {
        File modulefile = new File(filepath);
        float modulecredits;
        String coursename;
        float coursecredits;
        int grade;
        String semester;
        int year;
        Module module;
        
        try {
            Scanner scanner = new Scanner(modulefile, "UTF-8");
            modulecredits = Float.parseFloat(scanner.nextLine());
            module = new Module(name, modulecredits);
            
            while(scanner.hasNextLine()) {
                    coursename = scanner.nextLine();
                    coursecredits = Float.parseFloat(scanner.nextLine());
                    grade = Integer.parseInt(scanner.nextLine());
                    semester = scanner.nextLine();
                    year = Integer.parseInt(scanner.nextLine());
                    module.addCourse(new Course(coursename,coursecredits,semester,year,grade));
                }
            return module;
            
        } catch (Exception e) {
            System.out.println("Virhe luettaessa opintokokonaisuuksia!");
            return null;
        }
        
        
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
            System.out.println("Virhe opiskelijatietojen tallennukessa!");
        }
        
    }
    
}
