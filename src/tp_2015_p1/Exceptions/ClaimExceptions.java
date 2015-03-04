package tp_2015_p1.Exceptions;

import java.io.FileNotFoundException;
import net.sf.json.JSONException;

public class ClaimExceptions extends ClaimValidatorExceptions {

    private static final String JSON_STRUCTURE_ERR_MSG = "la structure du ficher json est invalide.";
    private static final String JSON_INDEX_ERR_MSG = "Erreur: le nomber des cles attendu doit etre ";
    private static final String JSON_KEY_ERR_MSG = "Erreur: erreur avec la cle ou elle n'existe pas < ";
    private static final String FILE_ID_ERR_MSG = "Erreur: le numero du dossier doit etre un carataire de A a E suivi de 6 chiffre.";
    private static final String MONTH_FORMAT_ERR_MSG = "Erreur: la date du reclamation doit respecter la forme AAAA-MM.";
    private static final String CARE_CLAIM_DATES_ERR_MSG = "Erreur:la date du soin est on dohore de la date du reclamation.";
    private static final String CARE_NBR_ERR_MSG = "Erreur: les numeros de soin valide sont [ 0,100,150,175,200,[300..400],500,600,700]";
    private static final String AMOUNTS_FORMAT_ERR_MSG = "Erreur:le montant doit respecter la forme ..000.00$ ou ..000,00$";
    private static final String CARE_DATES_FORMAT_ERR_MSG = "Erreur: la date du soin doit respecter la forme AAAA-MM-JJ.";
    private static final String MAIN_ARGS_ERR_MSG = "Erreur,arguments invalides..";

    
    public ClaimExceptions(){
        
    }
    

    @Override
    public JSONException jsonStructureException() {
        return new JSONException(JSON_STRUCTURE_ERR_MSG);
    }

    @Override
    public Exception jsonSizeIndexException(int nbIndexs) {
        return new Exception(JSON_INDEX_ERR_MSG + nbIndexs + " .");
    }

    @Override
    public Exception notKeyException(String key) {
        return new Exception(JSON_KEY_ERR_MSG + key + " >");
    }

    @Override
    public Exception notFileIdException() {
        return new Exception(FILE_ID_ERR_MSG);

    }

    @Override
    public Exception notMonthFormatException() {
        return new Exception(MONTH_FORMAT_ERR_MSG);

    }

    @Override
    public Exception careDatesFormatException() {
        return new Exception(CARE_DATES_FORMAT_ERR_MSG);

    }

    @Override
    public Exception notCareAndClaimDateEqualsException() {
        return new Exception(CARE_CLAIM_DATES_ERR_MSG);

    }

    @Override
    public Exception notClaimCareNbrsException() {
        return new Exception(CARE_NBR_ERR_MSG);

    }

    @Override
    public Exception notClaimAmountsFormatException() {
        return new Exception(AMOUNTS_FORMAT_ERR_MSG);

    }

}
