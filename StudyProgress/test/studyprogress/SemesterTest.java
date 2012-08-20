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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createSemesterList() {
        StudyProgressManager manager = new StudyProgressManager();
        Student user = new Student("Milla");
        user.addModule("Tietojenkäsittelytieteen perusopinnot", 25.0f);
        user.addCourseToModule(0, new Course("Ohjelmoinnin perusteet", 5.0f, "syksy", 2012, 4));
        user.createSemesterList();
        assertEquals(1, user.getNumberOfSemesters());
    }
}
