
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void constructorName() {
        Module module = new Module("Perusopinnot", 25.0f);
        assertTrue(module.getName().equals("Perusopinnot"));
    }
    @Test
    public void constructorCreditPoints() {
        Module module = new Module("Perusopinnot", 25.0f);
        assertEquals(25.0f, module.getTotalCredits(), 0.001);
    }
    
    
    
}
