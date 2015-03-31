package tp_2015_p1.Statistics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import tp_2015_p1.Data.DataExtractor;
import tp_2015_p1.File.FileReader;
import tp_2015_p1.File.FileWriter;

public class Statistics1 {

    private final String TOT_NBR_CLAIM_PROCESSED = "Le nombre de réclamations traitées : ";
    private final String TOT_NBR_CLAIM_REJECTED = "Le nombre de réclamations rejetées : ";
    private final String NBR_CARS_CLAMED = "le nombre de soins déclares pour : ";
    private static final String cares_name[] = {
        "0|Massothérapie", "100|Ostéopathie", "150|Kinésithérapie", "175|Médecin généraliste privé",
        "200|Psychologie individuelle", "3[0-9]{2}|Soins dentaires", "400|Naturopathie, acuponcture",
        "500|Chiropratie", "600|Physiothérapie", "700|Orthophonie, ergothérapie"};

    private static String jsonStatistics;
    private static int NBR_CLAIM_PROCESSED;
    private static int NBR_CLAIM_REJECTED;
    private static JSONArray CARES_IN_STAT;
    private static HashMap CARE_NBRE;
    private static List<String> CLAIM_STRING;

    public Statistics1(DataExtractor claimData) throws Exception {
        CLAIM_STRING = claimData.getClaimString();
    }

    public Statistics1() throws Exception {

    }

    public void readStatisticsFile() throws IOException {
        CARES_IN_STAT = new JSONArray();
        jsonStatistics = FileReader.loadFileIntoString("statistics.json", "UTF-8");
        JSONObject obj = (JSONObject) JSONSerializer.toJSON(jsonStatistics);
        NBR_CLAIM_PROCESSED = obj.getInt(TOT_NBR_CLAIM_PROCESSED);
        NBR_CLAIM_REJECTED = obj.getInt(TOT_NBR_CLAIM_REJECTED);
        CARES_IN_STAT = obj.getJSONArray(NBR_CARS_CLAMED);

        CARE_NBRE = new HashMap();
        for (String s : CLAIM_STRING) {
                String nbrCare = s.replaceAll("\\|.+", "");
                String nameCare = careName(nbrCare);
            for (Object c :CARES_IN_STAT) {

                if (JSONObject.fromObject(c).containsKey(nameCare + " : ")) {
                    String soin = JSONObject.fromObject(c).getString(nameCare + " : ").trim();
                    CARE_NBRE.put(nbrCare, soin);
                    break;
                }
            }
        }
    }

    public void setNbrClaimProcessed(int nbrClaimProcessed) {
        NBR_CLAIM_PROCESSED += nbrClaimProcessed;
    }

    private JSONArray buildStatJson() {
        JSONArray obj = new JSONArray();
        JSONObject totCares = new JSONObject();

        for (int i = 0; !CLAIM_STRING.isEmpty(); ++i) {
            String careNb = CLAIM_STRING.get(0).replaceAll("\\|.+$", "");
            totCares.put(careName(careNb) + " : ", count(careNb));
            obj.add(totCares);
            totCares.clear();
        }

        return obj;
    }


    private String careName(String careNb) {
        for (String n : cares_name) {
            if (careNb.replaceAll("\\|.+", "").matches(n)) {
                return n.replaceAll(".+\\|", "");
            }
        }
        return "";
    }

    private int count(String care) {
        int count = 0;

        for (Iterator<String> iterator = CLAIM_STRING.iterator(); iterator.hasNext();) {
            String string = iterator.next().replaceAll("\\|.+$", "");
            if (string.matches(care)) {
                count++;
                iterator.remove();
            }
        }
        return count;
    }

    //
    ///
    //
    //
    //
    //
    //
    //
    public void writeStatisticsFile() throws IOException {
    }

public void printStatisticsFile() throws IOException {
//        readStatisticsFile();
//        JSONObject statisticsObject = JSONObject.fromObject(jsonStatistics);
//        System.out.print(""
//                + totnbr_claim_processed + statisticsObject.getString(totnbr_claim_processed) + "\n"
//                + totnbr_claim_rejected + statisticsObject.getString(totnbr_claim_rejected) + "\n"
//                + nbr_care_Clamed + "\n"
//        );
//        JSONArray statisticsCares = statisticsObject.getJSONArray(nbr_care_Clamed);
//
//        for (Object careName : statisticsCares) {
//            String g = careName.toString();
//            System.out.println("    " + g.replaceAll("[{\"]|[\":\\d}]", "") + " : " + careName.toString().replaceAll("\\D", ""));
//        }

    }

    public void resetStatisticsFile() throws IOException {
    }

}
