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

    //this file is the job manager, it will organize the jobs that appear on the jobs GUI interface. It will also append to all jobs history and trim jobs from the last 20.

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

        appendableString += search.getText() + ","; //adds search terms
        appendableString += date.getText() + ","; //adds date range
        appendableString += selectedStateMain + ","; //adds selected state
        appendableString += selectedCountyMain + ","; //adds selected county
        appendableString +=  runStatus + ","; //adds status (failed or finsihed, this is decided in the FlowStart)
        appendableString += selectedFlowType + ","; //adds selected flow type
        appendableString += startTime + ","; //adds start date and time
        appendableString += endTime; //adds finished date and time

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
