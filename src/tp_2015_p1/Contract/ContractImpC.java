package tp_2015_p1.Contract;

import tp_2015_p1.Data.DataExtractor;

public class ContractImpC extends SuperContract {

    public ContractImpC(DataExtractor claimData) {
        super(claimData);
        super.buildMap(careRefundsC);
        super.refundsCalculation();
    }

   

}
