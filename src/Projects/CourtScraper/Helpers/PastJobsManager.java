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

    public static String pastJobsFilePath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Configs\\PastJobs.csv";
    public static String configsFolderPath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Configs";

    public static void addJob(String status) throws IOException {
        String appendableString = "";

        appendableString += search.getText() + ",";
        appendableString += date.getText() + ",";
        appendableString += selectedState + ",";
        appendableString += selectedCounty + ",";
        appendableString +=  status + ",";
        appendableString += selectedFlowType + ",";
        appendableString += startTime + ",";
        appendableString += endTime;

        appendToCSV(appendableString, pastJobsFilePath);

        checkForMaxJobs();

    }

    public static void checkForMaxJobs() throws IOException {
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
