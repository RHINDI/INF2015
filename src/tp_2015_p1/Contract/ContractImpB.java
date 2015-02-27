package tp_2015_p1.Contract;

import tp_2015_p1.Data.DataExtractor;

public class ContractImpB extends SuperContract {

    public ContractImpB(DataExtractor claimData) {
        super(claimData);
        super.buildMap(careRefundsB);
        super.refundsCalculation();
    }

    

}
