/**
 * @author Adam Bouloudene
 * @summary This file manages and organizes the data that can be seen in the Past Jobs panel on the interface. It will also update jobs, trim the list to ensure it does not get too long.
 *
 */

package courtscraper.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static courtscraper.datamanagement.csv.CSVManagement.appendToCSV;
import static courtscraper.datamanagement.csv.CSVManagement.deleteLine;
import static courtscraper.helpers.FolderPaths.*;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.*;
import static courtscraper.setups.gui.mainpanelelements.MainInputBoxes.date;
import static courtscraper.setups.gui.mainpanelelements.MainInputBoxes.search;

public class JobsManager {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private String startTime;
    private String endTime;

    public JobsManager() {
        this.startTime = runLogger.updateInfo("startTime", dateFormat.format(new Date()));
    }

    public void addJob(String runStatus) throws IOException {
        endTime = runLogger.updateInfo("endTime", dateFormat.format(new Date()));
        String appendableString = "";

        //crafts the csv line to add to pastJobslist
        //adds search terms
        appendableString += search.getText() + ",";
        //adds date range
        appendableString += date.getText() + ",";
        //adds selected state
        appendableString += selectedStateMain + ",";
        //adds selected county
        appendableString += selectedCountyMain + ",";
        //adds status (failed or finsihed, this is decided in the FlowStart)
        appendableString +=  runStatus + ",";
        //adds selected flow type
        appendableString += selectedFlowType + ",";
        //adds start date and time
        appendableString += startTime + ",";
        //adds finished date and time
        appendableString += endTime;

        appendToCSV(appendableString, PAST_JOBS_CSV_PATH);
        appendToCSV(appendableString, ALL_JOBS_CSV_PATH);

        checkForMaxJobs();
    }

    private void checkForMaxJobs() throws IOException {
        List<String> rows = Files.readAllLines(Path.of(PAST_JOBS_CSV_PATH));

        if (rows.size() >= 21) {
            for (int i = 0; i<rows.size(); i++) {
                if (rows.get(1).equals(rows.get(i)) || rows.get(i).isEmpty())
                    deleteLine(PAST_JOBS_CSV_PATH, PAST_JOBS_FOLDER_PATH, rows.get(i));
            }
        }
    }
}
