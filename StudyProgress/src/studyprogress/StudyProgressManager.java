
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


 
    public StudyProgressManager() {
        userdirectorypath = "data";
        userfilepath = "data/users.txt";
        userfile = new File(userfilepath);
        userdirectory = new File(userdirectorypath);
        
        if(!userDirectoryExists()) {
            userdirectory.mkdir();
        }
        
        usernamelist = loadUserListFromFile();      
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
    
    public boolean logInUser(String username) {
        if(usernamelist.contains(username)) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean createNewUser(String username) {
        if(usernamelist.contains(username)) {
            return false;
        }
        else {
            usernamelist.add(username);
            writeUserNameList();
            return true;
        }
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
            System.out.println("Virhe kirjoitettaessa käyttänimitiedostoa!");
        }
    }
    private boolean userDirectoryExists() {
        return userdirectory.exists();
    }

}
