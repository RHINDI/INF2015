package tp_2015_p1.Main;

import tp_2015_p1.Validation.ClaimValidator;
import tp_2015_p1.Refund.RefundsJsonBuilder;
import tp_2015_p1.File.FileWriter;
import tp_2015_p1.File.FileReader;
import java.io.IOException;
import net.sf.json.JSONObject;

/**
 *
 * @author equipe 23
 */
public class Main {

    private static String INPUT_FILE = "";
    private static String OUTPUT_FILE = "";
    private static String CLAIM_FILE_STRING = "";

    public static void main(String[] args) throws Exception {
        try {
            validateArgs(args);
            
            CLAIM_FILE_STRING = FileReader.loadFileIntoString(INPUT_FILE, "UTF-8");
            ClaimValidator validate = new ClaimValidator(CLAIM_FILE_STRING);
            RefundsJsonBuilder refundObj = new RefundsJsonBuilder(validate.getclaimData());

            writeIntoFile(refundObj.getREFUND_OBJ(), OUTPUT_FILE);

        
        } catch (Exception e) {
            JSONObject errorObj = new JSONObject();
            errorObj.put("Erreur", e.getMessage());

            writeIntoFile(errorObj, OUTPUT_FILE);
        }
    }

    private static void validateArgs(String[] args) {
        if (args.length == 2) {
            INPUT_FILE = args[0];
            OUTPUT_FILE = args[1];

        } else {
            System.out.println("Erreur,arguments invalides..");
            System.exit(0);

        }
    }


    private static void writeIntoFile(JSONObject obj, String args) throws IOException {
        FileWriter.writeStringIntoFile(obj.toString(2), args, "UTF-8");
    }

}
