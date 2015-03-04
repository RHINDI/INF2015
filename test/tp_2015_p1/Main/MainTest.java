package tp_2015_p1.Main;

import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {//extends Main{

    public MainTest() {
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
    public void testMainArgsGood() throws Exception {
        String[] args = {"inputfile.json","refunds.json"};
        Main.main(args);
        assertEquals(Main.INPUT_FILE, args[0]);
        assertEquals(Main.OUTPUT_FILE, args[1]);
    }
    //@Test(expected = FileNotFoundException.class)
    public void testMainArgs() throws Exception {
        String[] args = {"inputfile.json"};
        Main.main(args);
    }
    //@Test(expected = FileNotFoundException.class)
    public void testMainArgs2() throws Exception {
        String[] args = {"inputfile.json","refunds.json","nofile.json"};
        Main.main(args);
    }
    //@Test(expected = FileNotFoundException.class)
    public void testMainArgs3() throws Exception {
        String[] args = {};
        Main.main(args);
    }
    

}
