
package studyprogress;

/**
 *
 * @author Antti Pekkarinen
 */
import java.util.*;
import java.io.*;

public class StudyProgressManager {
    
    private ArrayList<String> usernamelist;
    private Student user;
    private String userfilepath;
    private String userdirectorypath;
    private File userfile;
    private File userdirectory;
    private Scanner filescanner;
 
    public StudyProgressManager() {
        userdirectorypath = "data";
        userfilepath = "data/users.txt";
        userfile = new File(userfilepath);
        userdirectory = new File(userdirectorypath);
        usernamelist = loadUserListFromFile();      
    }
    
    private ArrayList<String> loadUserListFromFile() {
        
        String username;
        ArrayList<String> usernames = new ArrayList<String>();
        
        if(userfile.exists()) {
            try {
                filescanner = new Scanner(userfile, "UTF-8");
                
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
    
    public boolean logInUser(String username) {
        if(usernamelist.contains(username)) {
            return true;
        }
        else {
            return false;
        }
    }

}
