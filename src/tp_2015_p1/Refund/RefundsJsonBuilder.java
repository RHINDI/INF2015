
package tp_2015_p1.Refund;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tp_2015_p1.Data.DataExtractor;


public class RefundsJsonBuilder {

    private static JSONObject REFUND_OBJ;
    private final DataExtractor CLAIM_DATA;
    private final List<Float> REFUND_AMOUNTS;
    private final List<String> CLAIM_CARE_NBRS, CLAIM_CARE_DATES;

    public RefundsJsonBuilder(DataExtractor claimData) throws Exception {
        CLAIM_DATA = claimData;
        CLAIM_CARE_NBRS = CLAIM_DATA.getClaimCareNbrs();
        CLAIM_CARE_DATES =CLAIM_DATA.getClaimCareDates();
        REFUND_AMOUNTS = new RefundCalculator(CLAIM_DATA).getRefunds();
        buildJsonRefunds();
    }

    private void buildJsonRefunds() throws Exception {
        REFUND_OBJ = new JSONObject();

        REFUND_OBJ.put("dossier", CLAIM_DATA.getCustomerFileId());
        REFUND_OBJ.put("mois", CLAIM_DATA.getClaimMonth());
        REFUND_OBJ.put("remboursement", CareArrayBuilder());
        REFUND_OBJ.put("total", String.format("%.2f$", RefundTotal()));
    }

    private JSONArray CareArrayBuilder() throws NumberFormatException {
        JSONArray careArray = new JSONArray();
        JSONObject careObj = new JSONObject();
        
        for (int i = 0; i < CLAIM_CARE_NBRS.size(); ++i) {
            careObj.put("soin", Integer.parseInt(CLAIM_CARE_NBRS.get(i)));
            careObj.put("date", CLAIM_CARE_DATES.get(i));
            careObj.put("montant", String.format("%.2f$", REFUND_AMOUNTS.get(i)));
            careArray.add(careObj);

        }
        return careArray;
    }

    private Float RefundTotal() {
        float sum = 0f;
        for (float f : REFUND_AMOUNTS){
            sum += f;
            
        }
       return sum;
    }

    public JSONObject getREFUND_OBJ() {
        return REFUND_OBJ;
    }

}
