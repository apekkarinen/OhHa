/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studyprogress;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ausr
 */
public class StudyProgressManagerTest {
    StudyProgressManager manager;
    
    public StudyProgressManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        manager = new StudyProgressManager();
        
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void loadUsersAndLogIn() {
        assertNotNull(manager.logInUser("Antti"));
        
    }
    @Test
    public void loadUsersFalseLogin() {
        assertNull(manager.logInUser("Pentti"));
    }
    @Test
    public void createUserAlreadyExists() {
        assertFalse(manager.createNewUser("Erkki"));
    }
    @Test
    public void newUserHasNoModules() {
      boolean success = manager.createNewUser("Liisa");
      Student liisa = manager.logInUser("Liisa");
      assertEquals(0,liisa.getNumberOfModules());
    }
    @Test
    public void createUserAndLogin() {
        manager.createNewUser("Hessu");
        Student hessu = manager.logInUser("Hessu");
        Module module = new Module("Tietojenkäsittelytieteen Perusopinnot", 25.0f);
        Module module2 = new Module("Fysiikan Perusopinnot", 25.0f);
        module.addCourse(new Course("Ohjelmoinnin harjoitustyö", 4.0f, "syksy", 2012, 5));
        module2.addCourse(new Course("Mekaniikka", 8.0f, "syksy", 2012, 5));
        hessu.addModule(module);
        hessu.addModule(module2);
        //hessu.writeStudentData();
        hessu.deleteModule(0);
        hessu.deleteModule(0);
        assertEquals(0,hessu.getNumberOfModules());
        
    }
    @Test
    public void loadModelsAndCheckName() {
        int nameindex = manager.modelNameListContains("Tietojenkäsittelytieteen perusopinnot");
        assertEquals(nameindex, 0);
    }
    @Test
    public void checkNumberOfModels() {
        assertTrue(manager.getNumberOfModelModules() > 0);
    }
    @Test
    public void checkFirstModelName() {
        Module firstmodel = manager.getModelModule(0);
        assertTrue(firstmodel.getName().equals("Tietojenkäsittelytieteen perusopinnot"));
    }


    
        
}
