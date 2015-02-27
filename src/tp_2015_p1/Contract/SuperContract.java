/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_2015_p1.Contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tp_2015_p1.Data.DataExtractor;

public class SuperContract implements Contract {

    protected final List<String> CLAIM_CARE_NBRS;
    protected final List<String> CLAIM_AMOUNTS;
    protected final Map<String, Float> CARE_NBRS_MAP_REFUND_P_CENT;
    protected List<Float> MAX_REFUND ;
    
    protected final  String[] careNbr =      {"0", "100", "150", "175", "200", "3[0-9]{2}", "400", "500", "600", "700"};

    protected final  String[] careRefundsA = {".25f", ".35f", "0f", ".50f", ".25f", "0f", "0f", ".25f", ".40f", "0f"};
    protected final  String[] careRefundsB = {".50f|40", ".50f|50", "0f", ".75f", "1f", ".50f", "0f", ".50f|50", "1f", ".70f"};
    protected final  String[] careRefundsC = {".90f", ".95f", ".85f", ".90f", ".90f", ".90f", ".90f", ".90f", ".75f", ".90f"};
    protected final  String[] careRefundsD = {"1f|85", "1f|75", "1f|150", ".95f", "1f|100", "1f", "1f|65", "1f", "1f|100", "1f|90"};
    protected final  String[] careRefundsE = {".15f", ".15f", ".15f", ".25f|20", ".12f", ".60f", ".25f|15", ".30f|20", ".15f", ".22f"};

    public SuperContract(DataExtractor claimData) {
        CLAIM_CARE_NBRS = claimData.getClaimCareNbrs();
        CLAIM_AMOUNTS = claimData.getClaimAmounts();
        CARE_NBRS_MAP_REFUND_P_CENT = new HashMap<>();
        MAX_REFUND = new ArrayList<>();

    }

    @Override
    public  List<Float> refundsCalculation(){

    List<Float> refundArray = new ArrayList<>();

        for (int i = 0; i < CLAIM_CARE_NBRS.size(); ++i) {

            float careAmount = Float.parseFloat(CLAIM_AMOUNTS.get(i).replace("$", ""));
            String careNb = CLAIM_CARE_NBRS.get(i);
            if (MAX_REFUND.get(i) != 0) {
                refundArray.add(Math.min(MAX_REFUND.get(i), careAmount * CARE_NBRS_MAP_REFUND_P_CENT.get(careNb)));

            } else {
                refundArray.add(careAmount * CARE_NBRS_MAP_REFUND_P_CENT.get(careNb));

            }

        }

        return refundArray;
    }
protected void buildMap(String [] careRefund) {

        for (int i = 0; i < CLAIM_CARE_NBRS.size(); ++i) {
            String careN = CLAIM_CARE_NBRS.get(i);

            for (int j = 0; j < careNbr.length; ++j) {
                String careNI = careNbr[j];
                String persRefund = careRefund[j];
                if (careN.matches(careNI)) {
                    if (persRefund.contains("|")) {
                        MAX_REFUND.add(Float.parseFloat(persRefund.replaceAll("\\.?\\d*f\\|", "")));
                        persRefund = persRefund.replaceAll("\\|\\d+", "");
                        CARE_NBRS_MAP_REFUND_P_CENT.put(careN, Float.parseFloat(persRefund));

                    } else {
                        CARE_NBRS_MAP_REFUND_P_CENT.put(careN, Float.parseFloat(persRefund));
                        MAX_REFUND.add(0f);
                    }

                }
            }
        }
    }
    
}
