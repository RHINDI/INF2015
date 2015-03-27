
package tp_2015_p1.Refund;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tp_2015_p1.Data.DataExtractor;


public class RefundsJsonBuilder {

    private static JSONObject REFUND_OBJ;
    private final DataExtractor CLAIM_DATA;
    private final List<String> REFUND_AMOUNTS,CLAIM_STRING;

    public RefundsJsonBuilder(DataExtractor claimData) throws Exception {
        CLAIM_DATA = claimData;
        CLAIM_STRING = CLAIM_DATA.getClaimString();
        REFUND_AMOUNTS = new RefundCalculator(CLAIM_DATA).getRefunds();
        buildJsonRefunds();
    }

    private void buildJsonRefunds() throws Exception {
        REFUND_OBJ = new JSONObject();

        REFUND_OBJ.put("dossier", CLAIM_DATA.getCustomerFileId());
        REFUND_OBJ.put("mois", CLAIM_DATA.getClaimMonth());
        REFUND_OBJ.put("remboursement", CareArrayBuilder());
        REFUND_OBJ.put("total", RefundTotal().replaceAll("(\\d{2})$",".$1") +"$");
    }

    private JSONArray CareArrayBuilder() throws NumberFormatException {
        JSONArray careArray = new JSONArray();
        JSONObject careObj = new JSONObject();
        
        for (int i = 0; i < CLAIM_STRING.size(); ++i) {
            careObj.put("soin", Integer.parseInt(CLAIM_STRING.get(i).replaceAll("\\|.+$", "")));
            careObj.put("date", CLAIM_STRING.get(i).replaceAll("\\|\\d+\\..+", "").replaceAll(".+\\|", ""));
            careObj.put("montant", REFUND_AMOUNTS.get(i).replaceAll("(\\d{2})$",".$1") +"$");
            careArray.add(careObj);

        }
        return careArray;
    }

    private String RefundTotal() {
        int sum = 0;
        for (String f : REFUND_AMOUNTS){
            int k = Integer.parseInt(f);
            sum += k;
            
        }
       return Integer.toString(sum);
    }

    public JSONObject getREFUND_OBJ() {
        return REFUND_OBJ;
    }

}
