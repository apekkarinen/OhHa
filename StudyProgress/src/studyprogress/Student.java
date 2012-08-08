
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
    
    
    public Student(String name) {
        this.name = name;
        this.modulelist = new ArrayList<Module>();
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
        File studentdirectory = new File("data/"+name);
        File modulesfile = new File("data/"+name+"/modules.txt");
        String modulename;
        try {
            Scanner scanner = new Scanner(modulesfile, "UTF-8");
            while(scanner.hasNextLine()) {
                modulename = scanner.nextLine();
                addModule(loadModule(modulename, "data/"+name+"/"+modulename));
            }
        } catch (Exception e) {
            System.out.println("Virhe ladattaessa opiskelijatietoja!");
        }
        
        
    }
    
    public void writeStudentData() {
        
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
        
    }
    
}
