
package studyprogress;

/**
 *Student is the class representing an university student, the user of this software.
 * Every university course belongs to a module, so the Student class contains a list of
 * Module-objects.
 * 
 * @see Module
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
    
    public String getName() {
        return name;
    }
    
    public void addModule(String name, float totalcredits) {
        Module module = new Module(name,totalcredits);
        modulelist.add(module);
    }
     public void addModule(Module module) {
        modulelist.add(module);
        writeStudentData();
        
    }
    public void deleteModule(int index) {
        String modulename = modulelist.get(index).getName();
        String modulepath = "data/"+name+"/"+modulename+".txt";
        StudyProgressManager.deleteFile(modulepath);
        modulelist.remove(index);
        writeStudentData();
        
    }
    
    public void addCourseToModule(int moduleindex, Course course) {
        modulelist.get(moduleindex).addCourse(course);
        writeStudentData();
    }
    
    public int getNumberOfModules() {
        return modulelist.size();
    }
    public int getTotalNumberOfCourses() {
        int sum = 0;
        for (Module module : modulelist) {
           sum = sum + module.getNumberOfCourses();
        }
        return sum;
    }
    
    private void loadStudentData() {
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
    public void deleteCourseFromModule(int moduleindex, int courseindex) {
        modulelist.get(moduleindex).deleteCourse(courseindex);

    }
    public int getModuleSize(int moduleindex) {
        return modulelist.get(moduleindex).getNumberOfCourses();
    }
    
    public String moduleToString(int moduleindex) {
        Module module = modulelist.get(moduleindex);
        return module.getName() + " (keskiarvo "+module.getModuleAverage()+", arvosana "+module.getModuleGrade()+")\n" +module.toString();
    }
    
    public String toString() {
        int index = 0;
        String studentstring = "Käyttäjä "+name+", Opintokokonaisuudet: \n  numero    nimi\n";
        for(Module module : modulelist) {
            studentstring = studentstring +"    ["+index +"] " + module.getName() + " (keskiarvo "+module.getModuleAverage()+", arvosana "+module.getModuleGrade()+")";
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
    
}
