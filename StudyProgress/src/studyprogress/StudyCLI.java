
package studyprogress;


import java.util.*;
import javax.swing.SwingUtilities;
/**
 * The command line interface-class fort StudyProgressManager.
 *
 * @author Antti Pekkarinen
 */
public class StudyCLI {
    private StudyProgressManager manager;
    private Student user;
    private Scanner userinput;
    /**
     * Class constructor.
     * @param manager The StudyProgressManager this StudyCLI uses.
     */
    public StudyCLI(StudyProgressManager manager) {
        this.manager = manager;
        this.user = null;
        this.userinput = new Scanner(System.in, "UTF-8");
    }
    /**
     * Displays the login screen.
     */
    public void showLogIn() {
        int helpindex = 0;
        String selection;
        while(user == null) {
            System.out.println("Tervetuloa opintojen seurantajärjestelmään!");
            System.out.println("Kirjaudu sisään tai luo uusi käyttäjä/poistu (käyttäjänimi/q)");
            selection = userinput.nextLine();

            if (selection.equals("q")) {
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
    /**
     * Displays the main menu.
     */
    private void showMainMenu() {
        String mainselection;
        boolean firstdisplay = true;
        System.out.println();
        printBorder();
        System.out.println("Tervetuloa, "+user.getName()+"!");
        System.out.println();
        while(true) {
            userinput = new Scanner(System.in, "UTF-8");
            firstdisplay = false;
            System.out.println("        //----------Päävalikko/Valitse toiminto:---------->>");
            System.out.println("");
            System.out.println("Poistu ohjelmasta (q) | Tarkastele opintokokonaisuuksia (o)");
            System.out.println("Näytä yhteenveto (y) | Lisää opintokokonaisuus (l)");
            System.out.println("Muokkaa opintokokonaisuuksia (m) | Käynnistä graafinen käyttöliittymä (g)");
            printBorder();
            mainselection = userinput.nextLine();
            if(mainselection.equals("q")) {
                user.writeStudentData();
                break;
            }
            else {
                mainMenuAction(mainselection.toLowerCase());
            }
        }
        
    }
    /**
     * Reacts to different user commands from main menu.
     * @param selection Command given by user in the main menu screen.
     */
    private void mainMenuAction(String selection) {
        
        if(selection.equals("o")) {
            showViewModules();
        }
        else if(selection.equals("y")) {
            showSummary();
        }
        else if(selection.equals("l")) {
            showAddModule();
            
        }
        else if(selection.equals("m")) {
            showEditModule();
        }
        else if(selection.equals("g")) {
            StudyGUI gui = new StudyGUI(manager,user);
            SwingUtilities.invokeLater(gui);
            
        }
        else {
            System.out.println("Tuntematon komento '" +selection+"'");
        }
        
    }
    /**
     * Displays the view Modules-screen.
     */
    private void showViewModules() {
        int selection;
        int numberofmodules = user.getNumberOfModules();
        String input;
        while(true) {
            printBorder();
            System.out.println(user);
            System.out.println("Anna opintokokonaisuuden numero kurssilistausta varten tai q poistuaksesi");
            if(userinput.hasNextInt()) {
                selection = Integer.parseInt(userinput.nextLine());
                if(selection < numberofmodules && selection >= 0) {
                    System.out.println(user.moduleToString(selection));
                    System.out.println("Paina Enter palataksesi edelliseen valikkoon");
                    userinput.nextLine();
                }
                else {
                    System.out.println("Virheellinen kokonaisuuden numero!");
                }
            }
            else {
                input = userinput.nextLine();
                if(input.equals("q")) {
                    break;
                }
                else {
                    System.out.println("Tuntematon valinta!");
                }
            }
            
        }
    }
    /**
     * Displays the summary screen. The summary contains information
     * about the user's studies.
     */
    private void showSummary() {
        printBorder();
        System.out.println();
        System.out.println(user.getSummaryText());
        System.out.println("");
        System.out.println(user);
        System.out.println("Paina enter palataksesi edelliseen valikkoon");
        userinput.nextLine();
    }
    /**
     * Displays the add Module - screen.
     */
    private void showAddModule() {
        while(true) {
            userinput = new Scanner(System.in);
            String name;
            float credits;
            printBorder();
            System.out.println("Uusi opintokokonaisuus: Anna kokonaisuuden nimi tai q poistuaksesi päävalikkoon");
            name = userinput.nextLine();
            
            if(name.equals("q")) {
                break;
            }
            else if(name.equals("")) {
                System.out.println("Kokonaisuuden nimi ei voi olla tyhjä!");
            }
            else {

                System.out.println("Anna kokonaisuuden laajuus opintopisteinä");
                if(userinput.hasNextFloat()) {
                    credits = Float.parseFloat(userinput.nextLine());
                    if(credits > 0) {
                        user.addModule(name, credits);
                        user.writeStudentData();
                        System.out.println("Lisätty kokonaisuus " +name +" ("+credits+"Op)");
                    }
                    else {
                        System.out.println("Virheellinen laajuus, opintopisteitä oltava enemmän kuin 0");
                    }
                }
                else {
                    System.out.println("Virheellinen laajuus, kokonaisuutta ei lisätty.");
                }
            }
        }
    }
    /**
     * Displays the edit module - screen.
     */
    private void showEditModule() {
        int moduleselection = -1;
        int numberofmodules = user.getNumberOfModules();
        int largestmoduleindex;
        String actionselection;
         if(numberofmodules > 0) {
            largestmoduleindex = numberofmodules - 1;
            while (true) {
                while((moduleselection < 0 )|| (moduleselection >= numberofmodules)) {
                System.out.println(user);
                System.out.println("Valitse muokattavan opintokokonaisuuden numero: (0-"+largestmoduleindex+") tai q poistuaksesi");
                if(userinput.hasNextInt()) {
                    moduleselection = Integer.parseInt(userinput.nextLine());
                }
                else if (userinput.nextLine().equals("q")) {
                    break;
                }
                else {
                    System.out.println("Tuntematon valinta!");
                }
                }
                printBorder();
                System.out.println("Haluatko poistaa opintokokonaisuuden (po), lisätä kurssin (l), poistaa kurssin (pk) vai poistua (q)?");
                actionselection = userinput.nextLine();
                if(actionselection.equals("po")) {
                    user.deleteModule(moduleselection);
                    user.writeStudentData();
                }
                else if(actionselection.equals("l")) {
                    showAddCourse(moduleselection);
                }
                else if(actionselection.equals("pk")) {
                    showDeleteCourse(moduleselection);
                }
                else if(actionselection.equals("q")) {
                    break;
                }
                else {
                    System.out.println("Tuntematon valinta "+actionselection);
                }
            }
        }
         else {
             System.out.println("Et ole vielä lisännyt yhtään opintokokonaisuutta!");
         }
    }
    /**
     * Displays the edit Module - screen.
     * @param moduleindex Index of the Module to edit.
     */
    private void showDeleteCourse(int moduleindex) {
        int selection;
        try {
            int modulesize = user.getModuleSize(moduleindex);
            System.out.println(user.moduleToString(moduleindex));
            System.out.println("Minkä kurssin haluat poistaa? (numero 0-"+(modulesize - 1)+")");
            if(userinput.hasNextInt()) {
                selection = Integer.parseInt(userinput.nextLine());
                if(selection >= 0 && selection < modulesize) {
                    user.deleteCourseFromModule(moduleindex, selection);
                    System.out.println("Poistettiin kurssi numero "+selection+".");
                    user.writeStudentData();
                }
                else {
                    System.out.println("Virheellinen valinta, kurssia ei poistettu!");
                }
            }
            else {
                System.out.println("Virheellinen valinta, kurssia ei poistettu!");
            }
        } catch(Exception e) {
            System.out.println("Virhe kurssin poistamisessa!");
        }
        
    }
    /**
     * Displays the add Course to a Module - screen.
     * @param moduleindex Index of the Module to add to.
     */
    private void showAddCourse(int moduleindex) {
        String name = "";
        float credits = -1.0f;
        String semester = "";
        int year = -1;
        int grade = 0;
        userinput = new Scanner(System.in);
        System.out.println("Anna kurssin nimi:");
        name = userinput.nextLine();
        System.out.println("Anna kurssin opintopistelaajuus:");
        if(userinput.hasNextFloat()) {
            credits = Float.parseFloat(userinput.nextLine());
        }
        
        while(!(semester.equals("syksy") || semester.equals("kevät")) ) {
        System.out.println("Anna kurssin lukukausi (s = syksy, k = kevät:");
        semester = userinput.nextLine();
        
        if(semester.equals("s")) {
            semester = "syksy";
        }
        else if(semester.equals("k")) {
            semester = "kevät";
        }
        else {
            System.out.println("Tuntematon valinta '" +semester+"'!");
        }
        
        }
        System.out.println("Anna kurssin lukuvuosi:");
        if(userinput.hasNextInt()) {
            year = Integer.parseInt(userinput.nextLine());
        }
        System.out.println("Anna kurssin arvosana, (0-5, tyhjä rivi = 0):");
        if(userinput.hasNextInt()) {
            grade = Integer.parseInt(userinput.nextLine());
        }
        
        if(!name.equals("") && !semester.equals("") && credits > 0.0f && year > 0 && grade >= 0) {
            user.addCourseToModule(moduleindex, new Course (name, credits, semester, year, grade));
            user.writeStudentData();
        }
        else {
            System.out.println("Virheellisiä arvoja kurssin tiedoissa, luonti epäonnistui!");
        }
    }
    /**
     * Displays the new user creation screen when login is attempted as a user
     * and the username is not found.
     * @param username Username input by the user.
     */
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
    /**
     * Prints a simple border to System.out.
     */
    private void printBorder() {
        System.out.println("*>-----------------------------------------------------------------------<*");
    }

    
   
    
    
    
}
