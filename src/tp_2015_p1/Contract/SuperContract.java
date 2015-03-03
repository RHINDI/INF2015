package tp_2015_p1.Contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tp_2015_p1.Data.DataExtractor;
import tp_2015_p1.Data.JsonData;

public class SuperContract extends JsonData implements Contract {

    protected final List<String> CLAIM_CARE_NBRS;
    protected final List<String> CLAIM_AMOUNTS;
    protected final Map<String, Float> CARE_NBRS_MAP_REFUND_P_CENT;
    protected List<Float> MAX_REFUND;

    
    public SuperContract(DataExtractor claimData) {
        CLAIM_CARE_NBRS = claimData.getClaimCareNbrs();
        CLAIM_AMOUNTS = claimData.getClaimAmounts();
        CARE_NBRS_MAP_REFUND_P_CENT = new HashMap<>();
        MAX_REFUND = new ArrayList<>();

    }

    @Override
    public List<Float> refundsCalculation() {

        List<Float> refundArray = new ArrayList<>();

        for (int i = 0; i < CLAIM_CARE_NBRS.size(); ++i) {

            float careAmount = Float.parseFloat(CLAIM_AMOUNTS.get(i).replace("$", ""));
            String careNb = CLAIM_CARE_NBRS.get(i);
            float percentRefund = CARE_NBRS_MAP_REFUND_P_CENT.get(careNb);
            if (MAX_REFUND.get(i) != 0) {
                refundArray.add(Math.min(MAX_REFUND.get(i), careAmount * percentRefund));

            } else {
                refundArray.add(careAmount * percentRefund);

            }

        }

        return refundArray;
    }

    protected void buildMap(String[] careRefund) {

        for (int i = 0; i < CLAIM_CARE_NBRS.size(); ++i) {
            String careN = CLAIM_CARE_NBRS.get(i);

            for (int j = 0; j < ALL_CARE_NBR.length; ++j) {
                if (careN.matches(ALL_CARE_NBR[j])) {
                    addInMapAndMaxRefund(careRefund[j], careN);
                    break;

                }
            }
        }

    }

    private void addInMapAndMaxRefund(String percentRefund, String careN)  {
        String  maxRefund = "\\.?\\d*f\\|";//match le pourcentage de remboursement
        String  percent = "\\|\\d+";// match le maximume
        
        if (percentRefund.contains("|")) {
            MAX_REFUND.add(Float.parseFloat(percentRefund.replaceAll(maxRefund, "")));
            percentRefund = percentRefund.replaceAll(percent, "");
            CARE_NBRS_MAP_REFUND_P_CENT.put(careN, Float.parseFloat(percentRefund));
            
        } else {
            CARE_NBRS_MAP_REFUND_P_CENT.put(careN, Float.parseFloat(percentRefund));
            MAX_REFUND.add(0f);
        }
        
    }
}
