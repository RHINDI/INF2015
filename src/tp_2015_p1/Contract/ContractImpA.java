package tp_2015_p1.Contract;

import tp_2015_p1.Data.DataExtractor;

public class ContractImpA extends SuperContract {
   
    public ContractImpA(DataExtractor claimData) {
        super(claimData);
        super.buildArrayList(super.REFUNDS_A);

    }   

}

