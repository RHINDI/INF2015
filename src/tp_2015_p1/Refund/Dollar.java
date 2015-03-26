package tp_2015_p1.Refund;

import org.apache.commons.lang.StringUtils;

public class Dollar {

    private static String[] CARE_MONTH_MAX = {"100|250", "175|200", "200|250", "500|150", "600|300"};

    public Float getReturnedAmount(String careClaimed, String careAmount) {
        int occurance = StringUtils.countMatches(careClaimed, "|");
        String maxRefund = "\\.?\\d*f\\|";//match le pourcentage de remboursement
        String percent = "\\|\\d+";// match le maximume

        if (occurance == 1) {
            float a = Float.parseFloat(careClaimed.replaceAll("^.+\\|", ""));
            
            return Float.parseFloat(careAmount) * a;

        }else{
            
            float max = Float.parseFloat(careClaimed.replaceAll(".+\\|", ""));
            float a = Float.parseFloat(careClaimed.replaceAll("\\|\\d+", "").replaceAll("^.+\\|", ""));
            
            return Math.min(max,Float.parseFloat(careAmount) * a);
        }

        
    }

}
