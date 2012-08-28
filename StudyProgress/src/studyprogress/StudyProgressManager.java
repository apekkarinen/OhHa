
package studyprogress;


import java.util.*;
import java.io.*;
/**
 *StudyProgressManager is the class containing the basic operations of the
 * StudyProgress-application, such as logging in and creating a user.
 * 
 * @author Antti Pekkarinen
 */
public class StudyProgressManager {
    
    private ArrayList<String> usernamelist;
    private ArrayList<Module> modelmodules;
    private ArrayList<String> modelmodulenames;
    private String userfilepath;
    private String userdirectorypath;
    private File userfile;
    private File userdirectory;
    private File modelsfile;
    
    /**
     * Class constructor. Checks if the user directory exists and if it doesn't,
     * tries to create it. If creating the user directory fails, terminates the program
     * with exit status 1.
     */
 
    public StudyProgressManager() {
        userdirectorypath = "data";
        userfilepath = "data/users.txt";
        userfile = new File(userfilepath);
        userdirectory = new File(userdirectorypath);
        modelsfile = new File("data/models/models.txt");
        modelmodulenames = new ArrayList<String>();
        
        if(!userdirectory.exists()) {
            try {
                 userdirectory.mkdir();
            } catch (Exception e) {
                System.out.println("Käyttäjäkansiota ei voida luoda, tarkista kirjoitusoikeutesi ohjelman suoritushakemistoon!");
                System.exit(1);
            }
        }
        
        
        usernamelist = loadUserListFromFile();
        modelmodules = new ArrayList<Module>();
        loadModelModules();
        
    }
    
    /**
     * Logs in a user by username.
     * @param username Name of the user to log in. If username is not found,
     * returns null.
     * @return Student-object representing the logged-in user.
     */
    
    public Student logInUser(String username) {
        if(usernamelist.contains(username)) {
            return new Student(username);
            
        }
        else {
            return null;
        }
    }
    /**
     * Attempts to create a new user by the name username. If username is
     * reserved to another user or user files cannot be written,
     * returns false. If not, creates the new user and returns true.
     * @param username Name of the new user to create.
     * @return True if a new user was created, otherwise false.
     */
    public boolean createNewUser(String username) {
        File newuserdatadirectory = new File("data/"+username);
        File modulesfile = new File("data/"+username+"/modules.txt");
        if(usernamelist.contains(username)) {
            return false;
        }
        else {
            try {
            usernamelist.add(username);
            writeUserNameList();
            newuserdatadirectory.mkdir();
            
                modulesfile.createNewFile();
            } catch (Exception e) {
                System.out.println("Käyttäjätietojen luominen ei onnistunut!");
                return false;
            }
            
            return true;
        }
    }
    
    /**
     * Returns an array of String representations of all pre-made model study Modules.
     * These representations contain Module names and total credits of the Modules.
     * @return An array of String representations of all model Modules.
     */
    
    public String[] modelModulesToStringArray() {
    String[] empty = {""};
        try {
            int size = modelmodules.size();
            Module module;
            if(size > 0) {
                String[] modules = new String[size];
                for (int i = 0; i < size; i++) {
                    module = modelmodules.get(i);
                    modules[i] = module.getName() + ", laajuus "+module.getTotalCredits()+" op.";
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
     * Returns an array of String representations of all pre-made model Courses of a specified model Module.
     * @param moduleindex List index of the desired model Module. Indices start from 0. 
     * @return An array of String representations of model Courses.
     */
    public String[] moduleCoursesToStringArray(int moduleindex) {
        String[] empty = {""};
        try {
        Module module = modelmodules.get(moduleindex);          

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
     * Checks if a model Module specified by name exists and if it does,
     * returns its index on the model Module list. If the Module does not exist, returns -1.
     * Note that the indices on the list start from 0.
     * @param name Name of the model Module to search for.
     * @return List index of the model Module or -1 if Module not found.
     */
    public int modelNameListContains(String name) {
        return modelmodulenames.indexOf(name);
        
    }
    /**
     * Gets a model Module specified by list index.
     * @param moduleindex List index of the desired Module. Indices start from 0.
     * @return The model Module specified by the index or null if index is out of range.
     */
    public Module getModelModule(int moduleindex) {
        if(moduleindex >= 0) {
            try {
                return modelmodules.get(moduleindex);
            }
            catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    /**
     * Gets the total number of pre-made model Modules
     * distributed with this program.
     * @return Total number of pre-made model Modules.
     */
    public int getNumberOfModelModules() {
        return modelmodules.size();
    }
    /**
     * Deletes a file specified by file path String.
     * If the specified file doesn't exist, does nothing.
     * @param filepath Path of the file to delete.
     */
    public static void deleteFile(String filepath) {
        File file = new File(filepath);
        if(file.exists()) {
            try {
                file.delete();
            } catch (Exception e) {
                System.out.println("Tiedoston poistaminen epäonnistui!");
            }
        }        
    }
    private ArrayList<String> loadUserListFromFile() {
        
        String username;
        ArrayList<String> usernames = new ArrayList<String>();
        
        if(userfile.exists()) {
            try {
                Scanner filescanner = new Scanner(userfile, "UTF-8");
                
                while(filescanner.hasNextLine()) {
                    username = filescanner.nextLine();
                    usernames.add(username);
                }
                
            } catch (Exception e) {
                System.out.println("Virhe ladattaessa käyttäjälistaa!");
            }
        }
        return usernames;
    }
    
    private void writeUserNameList() {
        String username;
        int userlistsize = usernamelist.size();
        
        try {
            PrintWriter writer = new PrintWriter(userfile, "UTF-8");
            for (int i = 0; i < userlistsize; i++) {
                username = usernamelist.get(i);
                writer.println(username);             
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Virhe kirjoitettaessa käyttäjänimitiedostoa!");
        }
    }
    private void loadModelModules() {
        modelmodules.clear();
        modelmodulenames.clear();
        String modelmodulename;
        try {
            Scanner modulescanner = new Scanner(modelsfile, "UTF-8");
            while(modulescanner.hasNextLine()) {
                modelmodulename = modulescanner.nextLine();
                modelmodulenames.add(modelmodulename);
                Module model = Student.loadModule(modelmodulename, "data/models/"+modelmodulename+".txt");
                modelmodules.add(model);
            }
            
        } catch (Exception e) {
            System.out.println("Virhe luettaessa mallikokonaisuuksia!");
        }
    }

}
