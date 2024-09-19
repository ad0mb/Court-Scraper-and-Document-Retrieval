package CourtScraper.Helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static CourtScraper.DataManagement.CSV.CSVManagement.appendToCSV;
import static CourtScraper.DataManagement.CSV.CSVManagement.deleteLine;
import static CourtScraper.FlowStart.endTime;
import static CourtScraper.FlowStart.startTime;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.*;
import static CourtScraper.Setups.GUI.MainPanelElements.MainInputBoxes.date;
import static CourtScraper.Setups.GUI.MainPanelElements.MainInputBoxes.search;

public class PastJobsManager {

    //this file has the managing features for the Jtable

    public static String pastJobsFilePath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Configs\\PastJobs.csv";
    public static String configsFolderPath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Configs";

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

        appendToCSV(appendableString, pastJobsFilePath);

        checkForMaxJobs();

    }

    private static void checkForMaxJobs() throws IOException {
        List<String> rows = Files.readAllLines(Path.of(pastJobsFilePath));

        if (rows.size() >= 21) {
            for (int i = 0; i<rows.size(); i++) {
                if (rows.get(1).equals(rows.get(i)) || rows.get(i).isEmpty())
                    deleteLine(pastJobsFilePath, configsFolderPath, rows.get(i));
            }
        }
    }

    public static String[][] getJobs() throws IOException {
        List<String> rows = Files.readAllLines(Path.of(pastJobsFilePath));
        String[][] pastJobsPanelList = new String[rows.size()-1][8];

        int counterCount = 0;
        for (int i = rows.size()-1; i > 0; i--) {
            pastJobsPanelList[counterCount] = rows.get(i).split(",");
            counterCount++;
        }

        return pastJobsPanelList;
    }
}
