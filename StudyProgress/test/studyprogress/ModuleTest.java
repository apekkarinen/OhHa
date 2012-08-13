
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
public class ModuleTest {
    Module module;

    public ModuleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        module = new Module("Perusopinnot", 25.0f);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void constructorName() {
        
        assertTrue(module.getName().equals("Perusopinnot"));
    }
    @Test
    public void constructorCreditPoints() {
        
        assertEquals(25.0f, module.getTotalCredits(), 0.001);
    }
    @Test
    public void addCourseAndGetNumber() {
        Course course = new Course("Ohjelmoinnin harjoitustyö", 4, "syksy", 2012);
        Course anothercourse = new Course("Ohjelmistojen mallintaminen", 4, "syksy", 2012);
        module.addCourse(course);
        module.addCourse(anothercourse);
        
        assertEquals(2, module.getNumberOfCourses());
        
    }
    @Test
    public void testAverageWithZeroCourses() {
        assertEquals(0, module.getModuleAverage(), 0.001);
    }
    @Test
    public void testAverage() {
        Course course = new Course("Ohjelmoinnin harjoitustyö", 4, "syksy", 2012, 5);
        Course anothercourse = new Course("Ohjelmistojen mallintaminen", 4, "syksy", 2012, 4);
        module.addCourse(course);
        module.addCourse(anothercourse);
        assertEquals(4.5f, module.getModuleAverage(), 0.001);
    }
    
    
    
}
