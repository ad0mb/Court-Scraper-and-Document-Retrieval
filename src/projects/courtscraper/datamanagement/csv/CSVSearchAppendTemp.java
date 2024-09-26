package courtscraper.datamanagement.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static courtscraper.flows.courtlink.courtlinksearchconfig.CourtlinkSearchConfigMain.processedInputs;
import static courtscraper.helpers.FolderPaths.TEMP_CSV_PATH;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedCountyMain;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedStateMain;


public class CSVSearchAppendTemp {

    //declarations of current date and time
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static Date date;

    public static void courtLinkAppendToTemp(List<String> toBeAppended) throws IOException {
        date = new Date();

        String appendableString = "";

        FileWriter csvFile = new FileWriter(TEMP_CSV_PATH, true);

        //adds case number and date of case filed from the courtlink site
        for (int i = toBeAppended.size()-1; i>=0; i--) {
            appendableString += toBeAppended.get(i).replace("\"", "") + ",";
        }

        appendableString += processedInputs[0] + ","; //adds keywords used in search
        appendableString += processedInputs[1] + ","; //adds attorney terms used in search
        appendableString += dateFormat.format(date) + ","; //adds current date and time from which the document was grabbed from
        appendableString += selectedStateMain + ","; //adds selected state
        appendableString += selectedCountyMain + ","; //added selected county
        appendableString += "" + ","; //adds documents selected


        csvFile.append("\n");
        csvFile.append(appendableString.substring(0, appendableString.length() - 1));

        csvFile.flush();
        csvFile.close();
    }
}
