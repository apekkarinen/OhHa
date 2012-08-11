
package studyprogress;

/**
 *
 * @author Antti Pekkarinen
 */
import java.util.*;

public class StudyCLI {
    private StudyProgressManager manager;
    private Student user;
    private Scanner userinput;
    
    public StudyCLI(StudyProgressManager manager) {
        this.manager = manager;
        this.user = null;
        this.userinput = new Scanner(System.in, "UTF-8");
    }
    
    public void showLogIn() {
        int helpindex = 0;
        String selection;
        while(user == null) {
            System.out.println("Tervetuloa opintojen seurantajärjestelmään!");
            System.out.println("Kirjaudu sisään/luo uusi käyttäjä/näytä ohje/poistu (käyttäjänimi/h/q)");
            selection = userinput.nextLine();
            
            if(selection.equals("h")) {
                showHelp(helpindex);
            }
            else if (selection.equals("q")) {
                break;
            }
            else {
                user = manager.logInUser(selection);
                if(user == null) {
                    promptForNewUserCreation(selection);
                }
                else {
                   showMainMenu(); 
                }
            }
        }
    }
    
    private void showMainMenu() {
        String selection;
        System.out.println("Tervetuloa, "+user.getName()+"!");
        System.out.println("Sinulla on yhteensä "+user.getNumberOfModules()+ " opintokokonaisuutta.");
        System.out.println("Sinulla on yhteensä "+user.getTotalNumberOfCourses() +" kurssia." );
        while(true) {
            System.out.println("Päävalikko/Valitse toiminto:");
            System.out.println("Poistu ohjelmasta/q");
            selection = userinput.nextLine();
            if(selection.equals("q")) {
                user.writeStudentData();
                break;
        }
        }
        
    }
    private void promptForNewUserCreation(String username) {
        String selection;
        System.out.println("Käyttäjää nimeltä "+username+" ei löydy, haluatko luoda uuden käyttäjän tällä nimellä? (k/e)");
        selection = userinput.nextLine();
        
        if(selection.equals("k")) {
            System.out.println("Luodaan uusi käyttäjä '"+username+"'.");
            manager.createNewUser(username);
            System.out.println("Käyttäjä "+username+" luotu.");
        }
        else if (selection.equals("e")) {
            System.out.println("Valitsit 'e', Käyttäjää ei luotu.");
        }
        else {
            System.out.println("Tuntematon valinta, käyttäjää ei luotu.");
        }
        
    }
    private void showHelp(int index) {
        
    }
    
   
    
    
    
}
