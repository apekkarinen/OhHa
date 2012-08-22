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
public class SemesterTest {
    StudyProgressManager manager;
    Student user;
    public SemesterTest() {
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
        user = new Student("Milla");
        user.addModule("Tietojenkäsittelytieteen perusopinnot", 25.0f);
        user.addCourseToModule(0, new Course("Ohjelmoinnin perusteet", 5.0f, "syksy", 2012, 4));
        user.addModule("Tietojenkäsittelytieteen aineopinnot", 60.0f);
        user.addCourseToModule(1, new Course("Tietorakenteet", 8.0f, "kevät", 2013, 5));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void numberOfSemesters() {

        assertEquals(2, user.getNumberOfSemesters());
    }
    @Test
    public void creditsGained() {
        assertEquals(13.0f, user.getTotalCreditsCompleted(), 0.001);
    }
}
