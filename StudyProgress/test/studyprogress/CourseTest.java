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
public class CourseTest {
    
    public CourseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
    public void constructorCourseCredits() {
         Course course = new Course("Ohjelmoinnin harjoitustyö", 4);
         assertEquals(4, course.getCreditPoints(), 0.001);
     }
     @Test
     public void constructorCourseName() {
         Course course = new Course("Ohjelmoinnin harjoitustyö", 4);
         assertTrue(course.getName().equals("Ohjelmoinnin harjoitustyö"));
     }
     @Test
     public void constructorDefaultCoursegrade() {
         Course course = new Course("Ohjelmoinnin harjoitustyö", 4);
         assertEquals(0 , course.getGrade());
     }
     @Test
     public void constructorSetCoursegrade() {
         Course course = new Course("Ohjelmoinnin harjoitustyö", 4, 5);
         assertEquals(5 , course.getGrade());
     }
}
