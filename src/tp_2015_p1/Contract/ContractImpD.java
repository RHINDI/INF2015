package tp_2015_p1.Contract;

import tp_2015_p1.Data.DataExtractor;

public class ContractImpD extends SuperContract {

    public ContractImpD(DataExtractor claimData) {
        super(claimData);
        super.buildArrayList(super.REFUNDS_D);
    }

}
