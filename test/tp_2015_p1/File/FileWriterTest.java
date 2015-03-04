/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_2015_p1.File;

import java.nio.charset.IllegalCharsetNameException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hackr
 */
public class FileWriterTest {
    
    public FileWriterTest() {
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


    @Test(expected = IllegalCharsetNameException.class)
    public void testWriteStringIntoFileNoEncoding() throws Exception {
        FileWriter.writeStringIntoFile("c'es un test", "refunds.json", "");
       
    }
    
    
}
