package tp_2015_p1.Data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DataExtractorTest {

    private static DataExtractor data;

    public DataExtractorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        String jsonString = ""
                + "{ \"dossier\": \"A100323\","
                + "\"mois\": \"2015-01\","
                + "\"reclamations\": [{"
                + "\"soin\": 350,"
                + "\"date\":\"2015-01-11 \","
                + "\"montant\": \"75.00$\"},]} ";
        data = new DataExtractor(jsonString);
    }

    @AfterClass
    public static void tearDownClass() {
        data = null;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    

    @Test
    public void testGetCustomerFileId() {
        assertEquals(data.getCustomerFileId(), "A100323");

    }

    @Test
    public void testGetClaimMonth() {
        assertEquals(data.getClaimMonth(), "2015-01");
    }

    @Test
    public void testGetClaimsArray() {
        assertEquals(data.getClaimsArray().size(), 1);
    }

    @Test
    public void testGetClaimCareDates() {
        assertEquals(data.getClaimString().size(), 1);
        assertEquals(data.getClaimString().get(0).replaceAll("\\|\\d+\\..+", "").replaceAll(".+\\|", ""), "2015-01-11");
    }

    @Test
    public void testGetClaimCareNbrs() {
        assertEquals(data.getClaimString().size(), 1);
        assertEquals(data.getClaimString().get(0).replaceAll("\\|.+$", ""), "350");

    }

    @Test
    public void testGetClaimAmounts() {
        assertEquals(data.getClaimString().size(), 1);
        assertEquals(data.getClaimString().get(0).replaceAll(".+\\|", ""), "75.00$");
    }

}
