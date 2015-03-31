package tp_2015_p1.Refund;

import org.apache.commons.lang.StringUtils;

public class Dollar {

    private static final String[] CARE_MONTH_MAX = {"100|25000", "175|20000", "200|25000", "500|15000", "600|30000"};

    public String getReturnedAmount(String careClaimed, int careAmount) {
        int amountReturned ;
        String maxRefundRegx = ".+\\|";//match le pourcentage de remboursement
        String percentRegx = "(\\d{1,}\\|)|(\\|\\d*)";// match le maximume
        String careNbr = careClaimed.replaceAll("\\|.+$", "");
        int occurance = StringUtils.countMatches(careClaimed, "|");
        
        if (occurance == 1) {
            float percent = Float.parseFloat(careClaimed.replaceAll("^.+\\|", ""));
            amountReturned = (int) (careAmount * percent);

        } else {

            int max = Integer.parseInt(careClaimed.replaceAll(maxRefundRegx, ""));
            float a = Float.parseFloat(careClaimed.replaceAll(percentRegx, ""));
            amountReturned = (int)(Math.min(max, careAmount * a));
        }
            if (careNbr.matches("[1256]0{2}|175")) {
                amountReturned = checkMounthMax(careNbr, amountReturned);
            }
            return Integer.toString(amountReturned);
    }

    private int checkMounthMax(String careNbr, int amountReturned) {
        int maxMounth;

        for (int i = 0; i < CARE_MONTH_MAX.length; ++i) {
            String c = CARE_MONTH_MAX[i];
            
            if (careNbr.matches(c.replaceAll("\\|.+", ""))) {
                maxMounth = Integer.parseInt(c.replaceAll(".+\\|", ""));
                
                if (maxMounth <= amountReturned) {
                    CARE_MONTH_MAX[i] = careNbr + "|" + 0;
                    return maxMounth;
                } else {
                    maxMounth -= amountReturned;
                    CARE_MONTH_MAX[i] = careNbr + "|" + Integer.toString(maxMounth);
                    break;
                }
            }
        }

        return amountReturned;
    }

}
