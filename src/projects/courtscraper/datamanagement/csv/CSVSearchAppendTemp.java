/**
 * @author Adam Bouloudene
 * @summary This method serves the purpose of appending the case numbers from a Courtlink scrape to the temp folder. It appends the case number, date filed and other relevant information.
 *
 * @todo Use string builder to build the appendableString.
 * @todo Use CSVManagement().appendToCSV() method to append string to csv.
 */

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

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Sets format for date and time
    private static Date date; // The current date is declared here

    public static void courtLinkAppendToTemp(List<String> toBeAppended) throws IOException {
        date = new Date();

        String appendableString = "";

        FileWriter csvFile = new FileWriter(TEMP_CSV_PATH, true);

        // Adds case number and date of case filed from the Courtlink site
        for (int i = toBeAppended.size()-1; i>=0; i--) {
            appendableString += toBeAppended.get(i).replace("\"", "") + ",";
        }


        appendableString += processedInputs[0] + ","; // Adds keywords used in search

        appendableString += processedInputs[1] + ","; // Adds attorney terms used in search

        appendableString += dateFormat.format(date) + ","; // Adds current date and time from which the document was grabbed from

        appendableString += selectedStateMain + ","; // Adds selected state

        appendableString += selectedCountyMain + ","; // Added selected county

        appendableString += "" + ","; // Adds documents selected


        csvFile.append("\n");
        csvFile.append(appendableString.substring(0, appendableString.length() - 1));

        csvFile.flush();
        csvFile.close();
    }
}
