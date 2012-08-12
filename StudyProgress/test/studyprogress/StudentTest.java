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
 * @author Antti Pekkarinen
 */
public class StudentTest {
    Student student;
    
    public StudentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       student = new Student("Olli Opiskelija");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorCreatesStudent() {
        assertTrue(student.getName().equals("Olli Opiskelija"));
        
    }
    @Test
    public void createStudentWithNoData() {
        assertEquals(0, student.getNumberOfModules());
        
    }
    
    @Test
    public void addModulesAndGetNumber() {
        
        student.addModule("Perusopinnot", 25.0f);
        student.addModule("Aineopinnot", 25.0f);
        student.addModule("Syventävät opinnot", 25.0f);
        assertEquals(3, student.getNumberOfModules());
    }
    @Test
    public void addAndRemoveCourses() {
        Course course = new Course("Tietorakenteet", 8.0f, "kevät", 2013);
        student.addModule("Perusopinnot", 25.0f);
        student.addModule("Aineopinnot", 25.0f);
        student.addCourseToModule(1, course);
        assertEquals(1,student.getModuleSize(1));
        while(student.getNumberOfModules() > 0) {
            student.deleteModule(0);
        }
          
    }      
    @Test
    public void loginAndTestCourseNumber() {
        Student student2  = new Student("Mikko Mallihenkilö");
        assertEquals(0, student2.getTotalNumberOfCourses());
    }
    
    
}
