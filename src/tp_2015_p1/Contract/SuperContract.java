package tp_2015_p1.Contract;

import java.util.ArrayList;
import java.util.List;
import tp_2015_p1.Data.DataExtractor;
import tp_2015_p1.Data.JsonData;
import tp_2015_p1.Refund.Dollar;

public class SuperContract extends JsonData implements Contract {

    protected final List<String> CLAIM_STRING;
    protected final List<String> CARE_NBRS_PRESENT;
    protected List<Float> MAX_REFUND;

    
    public SuperContract(DataExtractor claimData) {

        CLAIM_STRING = claimData.getClaimString();
        CARE_NBRS_PRESENT = new ArrayList<>();
        MAX_REFUND = new ArrayList<>();

    }

    @Override
    public List<Float> refundsCalculation() {

        List<Float> refundArray = new ArrayList<>();
        Dollar dollar = new Dollar();

        for (int i = 0; i < CLAIM_STRING.size(); ++i) {

            String careAmount = CLAIM_STRING.get(i).replaceAll(".+\\|", "").replace("$", "");
            refundArray.add(dollar.getReturnedAmount(CARE_NBRS_PRESENT.get(i),careAmount));
        }

        return refundArray;
    }

    protected void buildMap(String[] careRefund) {

        for (int i = 0; i < CLAIM_STRING.size(); ++i) {
            String careN = CLAIM_STRING.get(i).replaceAll("\\|.+$", "");

            for (int j = 0; j < ALL_CARE_NBR.length; ++j) {
                if (careN.matches(ALL_CARE_NBR[j])) {
                    CARE_NBRS_PRESENT.add(careN +"|" + careRefund[j]);
                    break;

                }
            }
        }

    }
}
