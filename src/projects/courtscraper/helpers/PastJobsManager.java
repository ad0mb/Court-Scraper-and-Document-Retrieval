package courtscraper.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static courtscraper.datamanagement.csv.CSVManagement.appendToCSV;
import static courtscraper.datamanagement.csv.CSVManagement.deleteLine;
import static courtscraper.FlowStart.endTime;
import static courtscraper.FlowStart.startTime;
import static courtscraper.helpers.FolderPaths.CONFIGS_FOLDER_PATH;
import static courtscraper.helpers.FolderPaths.PAST_JOBS_CSV_PATH;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.*;
import static courtscraper.setups.gui.mainpanelelements.MainInputBoxes.date;
import static courtscraper.setups.gui.mainpanelelements.MainInputBoxes.search;

public class PastJobsManager {

    //this file has the managing features for the Jtable

    public static void addJob(String status) throws IOException {
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
        appendableString +=  status + ",";
        //adds selected flow type
        appendableString += selectedFlowType + ",";
        //adds start date and time
        appendableString += startTime + ",";
        //adds finished date and time
        appendableString += endTime;

        appendToCSV(appendableString, PAST_JOBS_CSV_PATH);

        checkForMaxJobs();

    }

    private static void checkForMaxJobs() throws IOException {
        List<String> rows = Files.readAllLines(Path.of(PAST_JOBS_CSV_PATH));

        if (rows.size() >= 21) {
            for (int i = 0; i<rows.size(); i++) {
                if (rows.get(1).equals(rows.get(i)) || rows.get(i).isEmpty())
                    deleteLine(PAST_JOBS_CSV_PATH, CONFIGS_FOLDER_PATH, rows.get(i));
            }
        }
    }

    public static String[][] getJobs() throws IOException {
        List<String> rows = Files.readAllLines(Path.of(PAST_JOBS_CSV_PATH));
        String[][] pastJobsPanelList = new String[rows.size()-1][8];

        int counterCount = 0;
        for (int i = rows.size()-1; i > 0; i--) {
            pastJobsPanelList[counterCount] = rows.get(i).split(",");
            counterCount++;
        }

        return pastJobsPanelList;
    }
}
