
package tp_2015_p1.Data;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class DataExtractor {
    private  final String CUSTOMER_FILE_ID, CLAIM_MONTH;
    private  final  JSONArray CLAIM_ARRAYS;
    private  final  List<String> CLAIM_STRING;
    
    public DataExtractor(String claimString){
        JSONObject claimObject = JSONObject.fromObject(claimString);
        CUSTOMER_FILE_ID = claimObject.getString("dossier").trim();
        CLAIM_MONTH = claimObject.getString("mois").trim();
        CLAIM_ARRAYS = claimObject.getJSONArray("reclamations");

        CLAIM_STRING = new ArrayList<>();
        
        
        fillClaimArrays();
        
    }

    private void fillClaimArrays() {
        for (Object c : CLAIM_ARRAYS) {
            String soin = JSONObject.fromObject(c).getString("soin").trim();
            String date = JSONObject.fromObject(c).getString("date").trim();
            String montant = JSONObject.fromObject(c).getString("montant").trim().replace(",", ".");
            CLAIM_STRING.add(soin +"|"+ date +"|"+ montant);

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

    
    public List<String> getClaimString() {
        return CLAIM_STRING;
    }


}






