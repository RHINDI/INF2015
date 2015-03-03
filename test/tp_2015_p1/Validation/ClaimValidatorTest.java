
package tp_2015_p1.Validation;

import java.io.IOException;
import net.sf.json.JSONException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tp_2015_p1.Data.DataExtractor;
import tp_2015_p1.File.FileReader;


public class ClaimValidatorTest {
    
    public ClaimValidatorTest() {
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
    public void testIsJsonValide() {
        System.out.println("isJsonValide");
        String jsonString = "{ \"dossier\": \"A100323\",\"mois\": \"2015-01\",\"reclamations\": [{\"soin\": 350,\"date\":"
                + " \"2015-01-11 \",\"montant\": \"75.00$\"},} ";
    }
    @Test
    public void testGetclaimData() {
        System.out.println("getclaimData");
        ClaimValidator instance = null;
        DataExtractor expResult = null;
        DataExtractor result = instance.getclaimData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
