package tp_2015_p1.Validation;

import tp_2015_p1.Data.DataExtractor;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class ClaimValidator {

    private final String CLAIM_FILE_STRING;
    private final DataExtractor CLAIM_DATA;
    private final String CARE_NBR = "^[1-7]0{2}|3[0-9]{2}|0|150|175$";//matches 0,100,150,175,200,[300..400],500,600,700
    private final String CUSTOMER_FILE_ID = "^[A-E]\\d{6}$";//matche X000000
    private final String CARE_DATE = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[1-2][0-9]|3[0-1])$";//matches  AAAA-MM-JJ
    private final String CLAIM_CARE_DATES = "^\\d{4}-(0[1-9]|1[0-2])$";//matches  AAAA-MM
    private final String CLAIM_AMOUNT = "^\\d*[,.]\\d{2}\\$$";//matches 000.00$, 00.00$, .00$


    public ClaimValidator(String claimFileString) throws Exception {
        CLAIM_FILE_STRING = claimFileString;
        isJSONValide();
        isSize3AndIndexsExist();
        CLAIM_DATA = new DataExtractor(CLAIM_FILE_STRING);
        isCustomerFileIdValide();
        isClaimMonthValide();
        isCareDatesValide();
        isClaimCareNbrsValide();
        isClaimAmountsValide();

    }

    private void isJSONValide() throws Exception {
        try {
            JSONSerializer.toJSON(CLAIM_FILE_STRING);

        } catch (JSONException ex) {
            throw new Exception("la structure du ficher json est invalide.");
        }
    }

    private void isSize3AndIndexsExist() throws Exception {
        JSONObject jsonIndexs = JSONObject.fromObject(CLAIM_FILE_STRING);
        String[] validIndexs = {"dossier", "mois", "reclamations"};

        if (jsonIndexs.size() != validIndexs.length) {
            throw new Exception("les cles fourni ne correspond pas au cles attendu .");
        } else if (CLAIM_FILE_STRING.contains("^\"\\s?\"$")) {//Ajouter commentaire
            throw new Exception("il ya une valeur monquante ");

        }
        for (String s : validIndexs) {

            if (!jsonIndexs.containsKey(s)) {
                throw new Exception("il y a une erreur avec une cle <" + s + "> ou elle n'existe pas.");
            }
        }

    }

    private void isCustomerFileIdValide() throws Exception {

        
        if (!CLAIM_DATA.getCustomerFileId().matches(CUSTOMER_FILE_ID)) {
            throw new Exception("le numero du dossier du client est invalide.");
        }
    }

    private void isClaimMonthValide() throws Exception {

        if (!CLAIM_DATA.getClaimMonth().matches(CLAIM_CARE_DATES)) {
            throw new Exception("le format du mois du reclamation  est invalide.");
        }
    }

    private void isCareDatesValide() throws Exception {

        for (String c : CLAIM_DATA.getClaimCareDates()) {
            if (!(c.matches(CARE_DATE) && CLAIM_DATA.getClaimMonth().equals(c.substring(0, 7)))) {
                throw new Exception("date du soin est on dohore de la date du reclamation.");
            }
        }
    }

    private void isClaimCareNbrsValide() throws Exception {

        for (String c : CLAIM_DATA.getClaimCareNbrs()) {
            if (!c.matches(CARE_NBR)) {
                throw new Exception("le numero du soin  est invalide.");
            }
        }
    }

    private void isClaimAmountsValide() throws Exception {

        for (String c : CLAIM_DATA.getClaimAmounts()) {
            if (!c.matches(CLAIM_AMOUNT)) {
                throw new Exception("le format du montant reclamer est invalide.");
            }
        }

    }

    public DataExtractor getclaimData() {
        return CLAIM_DATA;
    }

}
