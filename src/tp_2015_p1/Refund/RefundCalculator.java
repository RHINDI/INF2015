package tp_2015_p1.Refund;

import java.lang.reflect.Method;
import java.util.List;
import tp_2015_p1.Data.DataExtractor;

public class RefundCalculator {

    private final DataExtractor CLAIM_DATA;

    RefundCalculator(DataExtractor claimData) {
        CLAIM_DATA = claimData;

    }

    public List<Float> getRefunds() throws Exception {
        char contractType =  CLAIM_DATA.getCustomerFileId().charAt(0);
        String fullClassName = "tp_2015_p1.Contract.ContractImp" + contractType;
        Class contractImpX = Class.forName(fullClassName);
        Object instanceContract = contractImpX.getConstructor(DataExtractor.class).newInstance(CLAIM_DATA);
        Method method = contractImpX.getMethod("refundsCalculation");

        return (List<Float>) method.invoke(instanceContract);

    }
}
