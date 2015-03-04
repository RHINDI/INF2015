package tp_2015_p1.Exceptions;

import net.sf.json.JSONException;

public abstract class ClaimValidatorExceptions extends Exception{

    abstract JSONException jsonStructureException();

    abstract Exception jsonSizeIndexException(int nbIndexs);

    abstract Exception notKeyException(String key);

    abstract Exception notFileIdException();

    abstract Exception notMonthFormatException();

    abstract Exception careDatesFormatException();

    abstract Exception notCareAndClaimDateEqualsException();

    abstract Exception notClaimCareNbrsException();

    abstract Exception notClaimAmountsFormatException();

}
