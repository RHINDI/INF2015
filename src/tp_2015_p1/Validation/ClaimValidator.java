
package tp_2015_p1.Validation;

import tp_2015_p1.Data.DataExtractor;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import tp_2015_p1.Exceptions.ClaimExceptions;

public class ClaimValidator extends ClaimExceptions{

    private final String CLAIM_FILE_STRING;
    private static  DataExtractor CLAIM_DATA;
                                //matche les numeros de soin 0,100,150,175,200,[300..400],500,600,700
    private final String CARE_NBR = "^[1-7]0{2}|3[0-9]{2}|0|150|175$";
    private final String CUSTOMER_FILE_ID = "^[A-E]\\d{6}$";//matche X000000
    private final String CARE_DATE = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[1-2][0-9]|3[0-1])$";//matches  AAAA-MM-JJ
    private final String CLAIM_CARE_DATES = "^\\d{4}-(0[1-9]|1[0-2])$";//matches  AAAA-MM
    private final String CLAIM_AMOUNT = "^\\d*[,.]\\d{2}\\$$";//matches 000.00$, 00.00$, .00$
   


    public ClaimValidator(String claimFileString) throws Exception {
        CLAIM_FILE_STRING = claimFileString;
        
        

    }
    public void isJsonStructureValide() throws Exception{
        isJsonValide();
        isSize3AndIndexsExist();
    }
    public void isJsonDataValide() throws Exception{
        CLAIM_DATA = new DataExtractor(CLAIM_FILE_STRING);
        isCustomerFileIdValide();
        isClaimMonthValide();
        isCareDatesValide();
        isDateCareAndClaimEquals();
        isClaimCareNbrsValide();
        isClaimAmountsValide();
    }

    private void isJsonValide() throws Exception {
        try {
            JSONSerializer.toJSON(CLAIM_FILE_STRING);

        } catch (JSONException ex) {
            throw jsonStructureException();
        }
    }

    private void isSize3AndIndexsExist() throws Exception {
        JSONObject jsonIndexs = JSONObject.fromObject(CLAIM_FILE_STRING);
        String[] validIndexs = {"dossier", "mois", "reclamations"};

        if (jsonIndexs.size() != validIndexs.length) {
            throw jsonSizeIndexException(validIndexs.length);
        } 
        
        for (String s : validIndexs) {

            if (!jsonIndexs.containsKey(s)) {
                throw new ClaimExceptions().notKeyException(s);
            }
        }

    }

    private void isCustomerFileIdValide() throws Exception {

        
        if (!CLAIM_DATA.getCustomerFileId().matches(CUSTOMER_FILE_ID)) {
            throw new ClaimExceptions().notFileIdException();
        }
    }

    private void isClaimMonthValide() throws Exception {

        if (!CLAIM_DATA.getClaimMonth().matches(CLAIM_CARE_DATES)) {
            throw new ClaimExceptions().notMonthFormatException();
        }
    }

    private void isCareDatesValide() throws Exception {

        for (String c : CLAIM_DATA.getClaimCareDates()) {
            if (!(c.matches(CARE_DATE) )) {
                throw new ClaimExceptions().careDatesFormatException();
            }
        }
    }
    private void isDateCareAndClaimEquals() throws Exception {

        for (String c : CLAIM_DATA.getClaimCareDates()) {
            if (!(CLAIM_DATA.getClaimMonth().equals(c.substring(0, 7)))) {
                throw new ClaimExceptions().notCareAndClaimDateEqualsException();
            }
        }
    }

    private void isClaimCareNbrsValide() throws Exception {

        for (String c : CLAIM_DATA.getClaimCareNbrs()) {
            if (!c.matches(CARE_NBR)) {
                throw new ClaimExceptions().notClaimCareNbrsException();
            }
        }
    }

    private void isClaimAmountsValide() throws Exception {

        for (String c : CLAIM_DATA.getClaimAmounts()) {
            if (!c.matches(CLAIM_AMOUNT)) {
                throw new ClaimExceptions().notClaimAmountsFormatException();
            }
        }

    }

    public DataExtractor getclaimData() {
        return CLAIM_DATA;
    }

}

