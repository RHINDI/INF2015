package tp_2015_p1.Statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import tp_2015_p1.File.FileReader;
import tp_2015_p1.File.FileWriter;

public class Statistics {

    private final String TOT_NBR_CLAIM_PROCESSED = "Le nombre de réclamations traitées : ";
    private final String TOT_NBR_CLAIM_REJECTED = "Le nombre de réclamations rejetées : ";
    private final String NBR_CARS_CLAMED = "Le nombre de soins déclares pour : ";
    private static final String cares_name[] = {
        "0|Massothérapie", "100|Ostéopathie", "150|Kinésithérapie", "175|Médecin généraliste privé",
        "200|Psychologie individuelle", "3\\d{2}|Soins dentaires", "400|Naturopathie, acuponcture",
        "500|Chiropratie", "600|Physiothérapie", "700|Orthophonie, ergothérapie"};

    private static String jsonStatistics;
    private static int NBR_CLAIM_PROCESSED;
    private static int NBR_CLAIM_REJECTED;
    private static JSONArray CARES_IN_STAT;
    private static List<String> CARE_NBRE;
    private static List<String> CLAIM_STRING;

    public Statistics(List<String> claimString) throws Exception {
        CARE_NBRE = new ArrayList<>();
        CLAIM_STRING = new ArrayList<>();
        if (!claimString.isEmpty()) {
            for (String c : claimString) {
                CLAIM_STRING.add(c.replaceAll("\\|.+$", ""));
            }
        }
    }

    public Statistics() throws Exception {

    }

    public void resetStatisticsFile() throws IOException {
        JSONObject statisticsToWrite = new JSONObject();
        JSONArray careArray = new JSONArray();
        NBR_CLAIM_PROCESSED = 0;
        NBR_CLAIM_REJECTED = 0;
        statisticsToWrite.put(TOT_NBR_CLAIM_PROCESSED, NBR_CLAIM_PROCESSED);
        statisticsToWrite.put(TOT_NBR_CLAIM_REJECTED, NBR_CLAIM_REJECTED);
        statisticsToWrite.put(NBR_CARS_CLAMED, careArray);
        FileWriter.writeStringIntoFile(statisticsToWrite.toString(2), "statistics.json", "UTF-8");
    }

    public void readStatisticsFile() throws Exception {

        CARES_IN_STAT = new JSONArray();
        jsonStatistics = FileReader.loadFileIntoString("statistics.json", "UTF-8");
        JSONObject obj = (JSONObject) JSONSerializer.toJSON(jsonStatistics);
        NBR_CLAIM_PROCESSED = obj.getInt(TOT_NBR_CLAIM_PROCESSED);
        NBR_CLAIM_REJECTED = obj.getInt(TOT_NBR_CLAIM_REJECTED);
        CARES_IN_STAT = obj.getJSONArray(NBR_CARS_CLAMED);

        CARE_NBRE = new ArrayList<>();
        for (String s : cares_name) {
            String nbrCare = s.replaceAll("\\|.+", "");
            if (nbrCare.equals("3\\d{2}")) {
                nbrCare = "300";
            }
            String nameCare = s.replaceAll(".+\\|", "");
            for (Object c : CARES_IN_STAT) {

                if (JSONObject.fromObject(c).containsKey(nameCare + " : ")) {
                    String soin = JSONObject.fromObject(c).getString(nameCare + " : ").trim();
                    CARE_NBRE.add(nbrCare + "|" + soin);
                    break;
                }
            }
        }

    }

    public void writeStatisticsFile() throws IOException {
        JSONObject statisticsToWrite = new JSONObject();

        statisticsToWrite.put(TOT_NBR_CLAIM_PROCESSED, NBR_CLAIM_PROCESSED);
        statisticsToWrite.put(TOT_NBR_CLAIM_REJECTED, NBR_CLAIM_REJECTED);
        statisticsToWrite.put(NBR_CARS_CLAMED, buildStatJson());
        FileWriter.writeStringIntoFile(statisticsToWrite.toString(2), "statistics.json", "UTF-8");
    }

    private JSONArray buildStatJson() {
        JSONArray obj = new JSONArray();
        JSONObject totCares = new JSONObject();

        if (!CARE_NBRE.isEmpty()) {
            while (!CLAIM_STRING.isEmpty()) {
                boolean isadd = false;
                String careNb = CLAIM_STRING.get(0).replaceAll("\\|.+$", "");

                for (int j = 0; j < CARE_NBRE.size(); ++j) {
                    String careNb1 = CARE_NBRE.get(j).replaceAll("\\|.+", "");

                    if (careNb1.equals(careNb)) {
                        int conte = count(careNb);
                        int conte1 = Integer.parseInt(CARE_NBRE.get(j).replaceAll(".+\\|", ""));
                        conte = conte + conte1;
                        CARE_NBRE.set(j, careNb + "|" + conte);
                        isadd = true;
                        break;
                    }
                }
                if (!isadd) {
                    CARE_NBRE.add(careNb + "|" + count(careNb));
                }
            }

            for (String c : CARE_NBRE) {
                String careNb = c.replaceAll("\\|.+", "");
                totCares.put(careName(careNb) + " : ", Integer.parseInt(c.replaceAll(".+\\|", "")));
                obj.add(totCares);
                totCares.clear();
            }
        } else {

            while (!CLAIM_STRING.isEmpty()) {
                String careNb = CLAIM_STRING.get(0).replaceAll("\\|.+", "");
                totCares.put(careName(careNb) + " : ", count(careNb));
                obj.add(totCares);
                totCares.clear();
            }
        }

        return obj;
    }

    public void printStatisticsFile() throws Exception {
        readStatisticsFile();
        System.out.println(
                TOT_NBR_CLAIM_PROCESSED + "" + NBR_CLAIM_PROCESSED + "\n"
                + TOT_NBR_CLAIM_REJECTED + "" + NBR_CLAIM_REJECTED + "\n"
                + NBR_CARS_CLAMED + "");
        for (String c : CARE_NBRE) {
            String careNb = c.replaceAll("\\|.+", "");
            System.out.println(
                    "        " + careName(careNb) + "   : " +
                    Integer.parseInt(c.replaceAll(".+\\|", "")));
        }

    }

    private String careName(String careNb) {
        for (String n : cares_name) {
            if (careNb.replaceAll("\\|.+", "").matches(n)) {
                return n.replaceAll(".+\\|", "");
            }
        }
        return "";
    }

    private int count(String nbrCare) {
        int count = 0;

        for (Iterator<String> iterator = CLAIM_STRING.iterator(); iterator.hasNext();) {
            String careNbr = iterator.next();

            if (nbrCare.matches("3\\d{2}") && careNbr.matches("3\\d{2}")) {
                count++;
                iterator.remove();

            } else if (careNbr.matches(nbrCare)) {
                count++;
                iterator.remove();
            }
        }
        return count;
    }

    public void setNbrClaimProcessed(int nbrClaimProcessed) {
        NBR_CLAIM_PROCESSED += nbrClaimProcessed;
    }

    public void setNbrClaimRejected(int nbrClaimRejected) {
        NBR_CLAIM_REJECTED += nbrClaimRejected;
    }

}
