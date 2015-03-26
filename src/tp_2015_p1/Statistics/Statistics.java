package tp_2015_p1.Statistics;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tp_2015_p1.Data.DataExtractor;
import tp_2015_p1.File.FileReader;
import tp_2015_p1.File.FileWriter;

public class Statistics {

    private static int nbr_claim_processed;
    private static int nbr_claim_rejected;
    private static int nbr_care_processed;
    private final String totnbr_claim_processed = "Le nombre total de réclamations traitées : ";
    private final String totnbr_claim_rejected = "Le nombre total de réclamations rejetées : ";
    private final String nbr_care_Clamed = "le nombre de soins déclares pour :";
    private static String jsonStatistics;

    private static final String cares_name[] = {
        "0|Massothérapie", "100|Ostéopathie", "150|Kinésithérapie","175|Médecin généraliste privé", 
        "200|Psychologie individuelle","3[0-9]{2}|Soins dentaires", "400|Naturopathie, acuponcture", 
        "500|Chiropratie","600|Physiothérapie", "700|Orthophonie, ergothérapie"};
    
    private static List<String> CLAIM_STRING;

    public Statistics(DataExtractor claimData) throws Exception {
        CLAIM_STRING = claimData.getClaimString();
    }

    public Statistics() throws Exception {

    }

    public void resetStatisticsFile() throws IOException {
        JSONObject statisticsToWrite = new JSONObject();

        statisticsToWrite.put(totnbr_claim_processed, 0);
        statisticsToWrite.put(totnbr_claim_rejected, 0);
        statisticsToWrite.put(nbr_care_Clamed, 0);
        FileWriter.writeStringIntoFile(statisticsToWrite.toString(2), "statistics.json", "UTF-8");

    }

    public void readStatisticsFile() throws IOException {

        jsonStatistics = FileReader.loadFileIntoString("statistics.json", "UTF-8");
    }

    public void writeStatisticsFile() throws IOException {
        JSONObject statisticsToWrite = new JSONObject();

        statisticsToWrite.put(totnbr_claim_processed, CLAIM_STRING.size());
        statisticsToWrite.put(totnbr_claim_rejected, nbr_claim_rejected);
        statisticsToWrite.put(nbr_care_Clamed, buildStatJson());
        FileWriter.writeStringIntoFile(statisticsToWrite.toString(2), "statistics.json", "UTF-8");
    }

    private JSONArray buildStatJson() {
        JSONArray obj = new JSONArray();
        JSONObject totCares = new JSONObject();

        while (!CLAIM_STRING.isEmpty()) {
            String careNb = CLAIM_STRING.get(0).replaceAll("\\|.+$", "");
            totCares.put(careName(careNb) + " : ", count(careNb));
            obj.add(totCares);
            totCares.clear();
        }

        return obj;
    }

    private String careName(String careName) {
        for (String n : cares_name) {
            if (careName.replaceAll("|.+", "").matches(n)) {
                return n.replaceAll("\\d+\\|", "");
            }
        }
        return "";
    }

    private int count(String care) {
        int count = 0;

        for (Iterator<String> iterator = CLAIM_STRING.iterator(); iterator.hasNext();) {
            String string = iterator.next();
            if (string.matches(care)) {
                count++;
                iterator.remove();
            }
        }

        return count;
    }

    public void printStatisticsFile() throws IOException {
        readStatisticsFile();
        JSONObject statisticsObject = JSONObject.fromObject(jsonStatistics);
        System.out.print(""
                + totnbr_claim_processed + statisticsObject.getString(totnbr_claim_processed) + "\n"
                + totnbr_claim_rejected + statisticsObject.getString(totnbr_claim_rejected)   + "\n"
                + nbr_care_Clamed + "\n"
        );
        JSONArray statisticsCares = statisticsObject.getJSONArray(nbr_care_Clamed);

        for (Object careName : statisticsCares) {
            String g = careName.toString();
            System.out.println("    " + g.replaceAll("[{\"]|[\":\\d}]", "") + " : "+careName.toString().replaceAll("\\D", ""));
        }

    }

}
