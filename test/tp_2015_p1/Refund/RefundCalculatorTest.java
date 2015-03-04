/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_2015_p1.Refund;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tp_2015_p1.Data.DataExtractor;

/**
 *
 * @author hackr
 */
public class RefundCalculatorTest {

    private static RefundCalculator refund;
    private static DataExtractor   data;
    

    public RefundCalculatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        String jsonString = ""
                + "{ \"dossier\": \"H100323\","
                + "\"mois\": \"2015-01\","
                + "\"reclamations\": [{"
                + "\"soin\": 350,"
                + "\"date\":\"2015-01-11 \","
                + "\"montant\": \"75.00$\"},]} ";
        data = new DataExtractor(jsonString);
        refund = new RefundCalculator(data);
    }

    @AfterClass
    public static void tearDownClass() {
        refund = null;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(expected = ClassNotFoundException.class)
    public void testGetRefunds() throws Exception {
       refund.getRefunds();
    }

}
