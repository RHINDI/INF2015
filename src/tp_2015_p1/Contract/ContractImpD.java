package tp_2015_p1.Contract;

import tp_2015_p1.Data.DataExtractor;

/**
 *
 * @author hackr
 */
public class ContractImpD extends SuperContract {

    public ContractImpD(DataExtractor claimData) {
        super(claimData);
        super.buildMap(careRefundsD);
        super.refundsCalculation();
    }

}
