package tp_2015_p1;

import tp_2015_p1.Validation.ClaimValidator;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class validateClaimTest {

    private static final String inputFile = "inputFile.json";
    ClaimValidator validate;

    public validateClaimTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        validate = new ClaimValidator(inputFile);
    }

    @After
    public void tearDown() {
        validate = null;
    }

    @Test
    public void testIsCustomerIdValide() {
        System.out.println("isCustomerIdValide");
        assertTrue(validate.isCustomerFileIdValide());

    }

    @Test
    public void testIsClaimMonthValide() {
        System.out.println("isClaimMonthValide");
        assertTrue(validate.isClaimMonthValide());

    }

    @Test
    public void testIsCareDatesValide() {
        System.out.println("isCareDatesValide");
        assertTrue(validate.isCareDatesValide());

    }

    @Test
    public void testIsClaimCareNbrsValide() {
        System.out.println("isClaimCareNbrsValide");

    }

    @Test
    public void testGetCustomerId() {
        System.out.println("getCustomerId");

    }

    @Test
    public void testGetContractId() {
        System.out.println("getContractId");

    }

    @Test
    public void testGetClaimMonth() {
        System.out.println("getClaimMonth");

    }

    @Test
    public void testGetClaimsArray() {
        System.out.println("getClaimsArray");

    }

    @Test
    public void testGetCareDates() {
        System.out.println("getCareDates");

    }

    @Test
    public void testGetClaimCareNbrs() {
        System.out.println("getClaimCareNbrs");

    }

    @Test
    public void testGetClaimAmounts() {
        System.out.println("getClaimAmounts");

    }

}
