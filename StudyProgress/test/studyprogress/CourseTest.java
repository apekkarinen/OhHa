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
        Course course;
        Course anothercourse;
    
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
        course = new Course("Ohjelmoinnin harjoitustyö", 4, "syksy", 2012);
        anothercourse = new Course("Ohjelmoinnin perusteet", -1.0f, "syksy", -1, -1);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
    public void constructorCourseCredits() {
         
         assertEquals(4, course.getCreditPoints(), 0.001);
     }
     @Test
     public void constructorCourseName() {
         
         assertTrue(course.getName().equals("Ohjelmoinnin harjoitustyö"));
     }
     @Test
     public void constructorDefaultCoursegrade() {
         
         assertEquals(0 , course.getGrade());
     }
     @Test
     public void constructorSetCoursegrade() {
         Course thirdcourse = new Course("Ohjelmoinnin harjoitustyö", 4.0f, "syksy", 2012, 5);
         assertEquals(5 , thirdcourse.getGrade());
     }
     @Test
     public void toStringCompare() {
         
         assertTrue(course.toString().equals("Ohjelmoinnin harjoitustyö (4.0 Op.): Arvosana: 0"));
     }
     @Test
     public void checkNegativeCredits() {
         assertEquals(anothercourse.getCreditPoints(), 0.0f, 0.01);
     }
     @Test
     public void checkNegativeYear() {
         assertEquals(anothercourse.getYear(), 0);
     }
     @Test
     public void checkNegativeGrade() {
         assertEquals(anothercourse.getGrade(), 0);
     }
     @Test
     public void checkGradeTooBig() {
         int biggrade = 6;
         Course fourthcourse = new Course("Ohjelmoinnin jatkokurssi", 4.0f, "syksy", 2012, biggrade);
         assertEquals(fourthcourse.getGrade(), 0);
     }
}
