package tp_2015_p1.Main;

import java.io.FileNotFoundException;
import tp_2015_p1.Validation.ClaimValidator;
import tp_2015_p1.Refund.RefundsJsonBuilder;
import tp_2015_p1.File.FileWriter;
import tp_2015_p1.File.FileReader;
import java.io.IOException;
import net.sf.json.JSONObject;
import tp_2015_p1.Exceptions.ClaimExceptions;

/**
 *
 * @author equipe 23
 */
public class Main {

    protected static String INPUT_FILE = "";
    protected static String OUTPUT_FILE = "";
    private static String CLAIM_FILE_STRING = "";

    public static void main(String[] args) throws Exception {
        try {
            validateArgs(args);

            CLAIM_FILE_STRING = FileReader.loadFileIntoString(INPUT_FILE, "UTF-8");
            ClaimValidator validate = validateData();
            RefundsJsonBuilder refundObj = new RefundsJsonBuilder(validate.getclaimData());

            writeIntoFile(refundObj.getREFUND_OBJ(), OUTPUT_FILE);
           
        } catch (Exception e) {
            JSONObject errorObj = new JSONObject();
            errorObj.put("Erreur", e.getMessage());

            writeIntoFile(errorObj, OUTPUT_FILE);
        }
    }


    private static void validateArgs(String[] args) throws Exception {
        if (args.length == 2) {
            INPUT_FILE = args[0];
            OUTPUT_FILE = args[1];

        } else {
            System.out.println("Erreur,arguments invalides..");
            System.exit(0);
        }
    }

    private static ClaimValidator validateData() throws Exception {
        ClaimValidator validate = new ClaimValidator(CLAIM_FILE_STRING);
        validate.isJsonStructureValide();
        validate.isJsonDataValide();
        return validate;
    }
    
    private static void writeIntoFile(JSONObject obj, String args) throws IOException {
        FileWriter.writeStringIntoFile(obj.toString(2), args, "UTF-8");
    }

}
