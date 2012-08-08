
package studyprogress;

/**
 *
 * @author Antti Pekkarinen
 */

import java.util.ArrayList;

public class Student {
    
    private String name;
    private ArrayList<Module> modulelist;
    
    public Student(String name) {
        this.name = name;
        this.modulelist = new ArrayList<Module>();
    }
    
    public String getName() {
        return name;
    }
    
    public void addModule(String name, float totalcredits) {
        Module module = new Module(name,totalcredits);
        modulelist.add(module);
    }
    
    public int getNumberOfModules() {
        return modulelist.size();
    }
    
    public void loadStudentData() {
        
    }
    
    public void writeStudentData() {
        
    }
    
    private Module loadModule(String name, String filepath) {
        
    }
    
}
