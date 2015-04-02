package tp_2015_p1.Main;

import java.io.FileNotFoundException;
import tp_2015_p1.Validation.ClaimValidator;
import tp_2015_p1.Refund.RefundsJsonBuilder;
import tp_2015_p1.File.FileWriter;
import tp_2015_p1.File.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONObject;
import tp_2015_p1.Data.DataExtractor;
import tp_2015_p1.Statistics.Statistics;

/**
 *
 * @author equipe 23
 */
public class Main {

    protected static String INPUT_FILE = "";
    protected static String OUTPUT_FILE = "";
    private static String CLAIM_FILE_STRING = "";
    private static Statistics stat;
    private static ClaimValidator validate;

    public static void main(String[] args) throws Exception {
        try {
            validateArgs(args);

            CLAIM_FILE_STRING = FileReader.loadFileIntoString(INPUT_FILE, "UTF-8");
            validate = validateData();
            RefundsJsonBuilder refundObj = new RefundsJsonBuilder(validate.getClaimData());
            writeIntoFile(refundObj.getREFUND_OBJ(), OUTPUT_FILE);

            setStatistics(validate.getClaimData());

        } catch (Exception e) {
            stat = new Statistics(new ArrayList<>());

            try {
                stat.readStatisticsFile();
                stat.setNbrClaimRejected(1);
                stat.writeStatisticsFile();

            } catch (FileNotFoundException f) {
                stat.setNbrClaimRejected(1);
                stat.writeStatisticsFile();

            }
            System.out.println("Erreur" + e.getMessage());
            System.exit(0);
        }
    }

    private static void validateArgs(String[] args) throws Exception {

        try {
            if (args.length == 1) {
                stat = new Statistics();
                if (args[0].equalsIgnoreCase("-s")) {
                    stat.readStatisticsFile();
                    stat.printStatisticsFile();
                    System.exit(0);
                } else if (args[0].equalsIgnoreCase("-sr")) {
                    stat.resetStatisticsFile();
                    System.exit(0);
                }
            } else if (args.length == 2) {
                INPUT_FILE = args[0];
                OUTPUT_FILE = args[1];

            } else {
                System.out.println("Erreur,arguments invalides..");
                System.exit(0);
            }
        } catch (FileNotFoundException f) {
            System.out.println("Erreur,le fichier n'exist pas..");
            System.exit(0);
        }
    }

    private static ClaimValidator validateData() throws Exception {
        validate = new ClaimValidator(CLAIM_FILE_STRING);
        validate.isJsonStructureValide();
        validate.isJsonDataValide();
        return validate;
    }

    private static void writeIntoFile(JSONObject obj, String args) throws IOException {
        FileWriter.writeStringIntoFile(obj.toString(2), args, "UTF-8");
    }

    private static void setStatistics(DataExtractor claimData) throws Exception {
        stat = new Statistics(claimData.getClaimString());
        try {
            stat.readStatisticsFile();
            stat.setNbrClaimProcessed(1);
            stat.writeStatisticsFile();

        } catch (FileNotFoundException f) {
            stat.setNbrClaimProcessed(1);
            stat.writeStatisticsFile();

        }
    }

}
