
package tp_2015_p1.Data;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class DataExtractor {
    private  final String CUSTOMER_FILE_ID;
    private  final  String CLAIM_MONTH;
    private  final  JSONArray CLAIM_ARRAYS;
    private  final  List<String> CLAIM_CARE_DATES;
    private  final  List<String> CLAIM_CARE_NBRS;
    private  final  List<String> CLAIM_AMOUNTS;
    
    public DataExtractor(String claimString){
        JSONObject claimObject = JSONObject.fromObject(claimString);
        CUSTOMER_FILE_ID = claimObject.getString("dossier").trim();
        CLAIM_MONTH = claimObject.getString("mois").trim();
        CLAIM_ARRAYS = claimObject.getJSONArray("reclamations");
        CLAIM_AMOUNTS = new ArrayList<>();
        CLAIM_CARE_DATES = new ArrayList<>();
        CLAIM_CARE_NBRS = new ArrayList<>();
        
        
        for (Object c : CLAIM_ARRAYS) {
            CLAIM_CARE_NBRS.add(JSONObject.fromObject(c).getString("soin").trim());
            CLAIM_CARE_DATES.add(JSONObject.fromObject(c).getString("date").trim());
            CLAIM_AMOUNTS.add(JSONObject.fromObject(c).getString("montant").trim().replace(",", "."));
        }
        
    }

       
    public String getCustomerFileId() {
        return CUSTOMER_FILE_ID;
    }


    public String getClaimMonth() {
        return CLAIM_MONTH;
    }

    public JSONArray getClaimsArray() {
        return CLAIM_ARRAYS;
    }

    public List<String> getClaimCareDates() {
        return CLAIM_CARE_DATES;
    }

    public List<String> getClaimCareNbrs() {
        return CLAIM_CARE_NBRS;
    }

    public List<String> getClaimAmounts() {
        return CLAIM_AMOUNTS;
    }


}






