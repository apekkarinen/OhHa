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
    public void addModulesAndGetNumber() {
        Module module1 = new Module("Perusopinnot", 25.0f);
        Module module2 = new Module("Aineopinnot", 25.0f);
        Module module3 = new Module("Syventävät opinnot", 25.0f);
        student.addModule(module1);
        student.addModule(module2);
        student.addModule(module3);
        assertEquals(3, student.getNumberOfModules());
    }
    
}
