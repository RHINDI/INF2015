
package tp_2015_p1.Validation;

import net.sf.json.JSONException;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


public class ClaimValidatorTest {
    

    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Before
    public void setUp() throws Exception {
     
    }
    
    @After
    public void tearDown() {
    }


    @Test (expected = JSONException.class)
    public void testJsonExceptionThrowed() throws Exception {
          String jsonString = ""
                  + "{ \"dossier\": \"A100323\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\": [{"
                  + "\"soin\": 350,"
                  + "\"date\":\"2015-01-11 \","
                  + "\"montant\": \"75.00$\"},} ";
        ClaimValidator validate = new ClaimValidator(jsonString);
        validate.isJsonStructureValide();  
    }
    
    @Test 
    public void testExceptionThrowed() throws Exception {
          String jsonString = 
                  "{ \"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        //thrown.expect(Exception.class);
        thrown.expectMessage(equalTo("Erreur: le nomber des cles attendu doit etre 3 ."));
        
        validate.isJsonStructureValide(); 
       
    }
    
    @Test 
    public void testNotKeyException() throws Exception {
          String jsonString = 
                  "{ \"dossiier\": \"A100323\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: erreur avec la cle ou elle n'existe pas < dossier >"));
        
        validate.isJsonStructureValide(); 
       
    }
    @Test 
    public void testNotTypeContractException() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"H100323\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: le numero du dossier doit etre un carataire de A a E suivi de 6 chiffre."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotTypeContractException2() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"a100323\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: le numero du dossier doit etre un carataire de A a E suivi de 6 chiffre."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotNumberFileIdException() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A1003230\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: le numero du dossier doit etre un carataire de A a E suivi de 6 chiffre."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotNumberFileIdException2() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A10032\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: le numero du dossier doit etre un carataire de A a E suivi de 6 chiffre."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotNumberFileIdException3() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"AA10032\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: le numero du dossier doit etre un carataire de A a E suivi de 6 chiffre."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotMonthFormatException() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-01-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: la date du reclamation doit respecter la forme AAAA-MM."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotMonthFormatException2() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"01-2015\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: la date du reclamation doit respecter la forme AAAA-MM."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotMonthFormatException3() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-13\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: la date du reclamation doit respecter la forme AAAA-MM."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotMonthFormatException4() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-mai\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: la date du reclamation doit respecter la forme AAAA-MM."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotcareDatesFormatException() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"11-01-2015 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: la date du soin doit respecter la forme AAAA-MM-JJ."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotcareDatesFormatException2() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-13-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: la date du soin doit respecter la forme AAAA-MM-JJ."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotcareDatesFormatException3() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-mai-11 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: la date du soin doit respecter la forme AAAA-MM-JJ."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotcareDatesFormatException4() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-01-32 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: la date du soin doit respecter la forme AAAA-MM-JJ."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotCareAndClaimDateEqualsException() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 350,"
                  + "\"date\": \"2015-02-01 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur:la date du soin est on dohore de la date du reclamation."));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotClaimCareNbrsException() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 460,"
                  + "\"date\": \"2015-01-01 \","
                  + "\"montant\": \"75.00$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur: les numeros de soin valide sont [ 0,100,150,175,200,[300..400],500,600,700]"));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotClaimAmountsFormatException() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 400,"
                  + "\"date\": \"2015-01-01 \","
                  + "\"montant\": \"75.00\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur:le montant doit respecter la forme ..000.00$ ou ..000,00$"));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotClaimAmountsFormatException2() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 400,"
                  + "\"date\": \"2015-01-01 \","
                  + "\"montant\": \"75.0$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur:le montant doit respecter la forme ..000.00$ ou ..000,00$"));
        
        validate.isJsonDataValide(); 
       
    }
    @Test 
    public void testNotClaimAmountsFormatException3() throws Exception {
          String jsonString = 
                  "{ \"dossier\": \"A100320\","
                  + "\"mois\": \"2015-01\","
                  + "\"reclamations\":[{"
                  + "\"soin\": 400,"
                  + "\"date\": \"2015-01-01 \","
                  + "\"montant\": \"75$\"}]} ";
          
        ClaimValidator validate = new ClaimValidator(jsonString);
        thrown.expectMessage(equalTo("Erreur:le montant doit respecter la forme ..000.00$ ou ..000,00$"));
        
        validate.isJsonDataValide(); 
       
    }
    
    
}
