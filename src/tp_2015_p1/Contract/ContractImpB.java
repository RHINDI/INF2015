package tp_2015_p1.Contract;

import tp_2015_p1.Data.DataExtractor;

public class ContractImpB extends SuperContract {

    public ContractImpB(DataExtractor claimData) {
        super(claimData);
        buildMap();
        super.refundsCalculation();
    }

    private void buildMap() {
        for (int i = 0; i < CLAIM_CARE_NBRS.size(); ++i) {
            String careN = CLAIM_CARE_NBRS.get(i);

            for (int j = 0; j < careNbr.length; ++j) {
                String careNI = careNbr[j];
                String persRefund = careRefundsB[j];
                if (careN.matches(careNI)) {
                    if (persRefund.contains("|")) {
                        MAX_REFUND.add(Float.parseFloat(persRefund.replaceAll("\\.?\\d*f\\|", "")));
                        persRefund = persRefund.replaceAll("\\|\\d+", "");
                        CARE_NBRS_MAP_REFUND_P_CENT.put(careN, Float.parseFloat(persRefund));

                    } else {
                        CARE_NBRS_MAP_REFUND_P_CENT.put(careN, Float.parseFloat(persRefund));
                        MAX_REFUND.add(0f);
                    }
                    break;
                }
            }
        }
    }

}
