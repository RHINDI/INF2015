package tp_2015_p1.Contract;

import tp_2015_p1.Data.DataExtractor;

public class ContractImpE extends SuperContract {


    public ContractImpE(DataExtractor claimData) {
        super(claimData);
        super.buildMap(careRefundsE);
        super.refundsCalculation();
    }

}
