/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_2015_p1.File;

import java.io.FileNotFoundException;
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
public class FileReaderTest {
    
    public FileReaderTest() {
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


    @Test (expected = FileNotFoundException.class)
    public void testLoadFileIntoStringNoFile() throws Exception {
        FileReader.loadFileIntoString("noFile.json", "UTF-8");
    }
    @Test (expected = IllegalCharsetNameException.class)
    public void testLoadFileIntoStringNoEncoding() throws Exception {
        FileReader.loadFileIntoString("inputfile.json", "");
    }
    
}
