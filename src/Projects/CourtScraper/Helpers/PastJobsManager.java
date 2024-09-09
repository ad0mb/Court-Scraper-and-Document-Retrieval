package CourtScraper.Helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static CourtScraper.DataManagement.CSV.CSVManagement.appendToCSV;
import static CourtScraper.DataManagement.CSV.CSVManagement.deleteLine;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;
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
        appendableString +=  status;

        appendToCSV(appendableString, pastJobsFilePath);

        checkForMaxJobs();

    }

    public static void checkForMaxJobs() throws IOException {
        //File pastJobsFile = new File(pastJobsFilePath);

        List<String> rows = Files.readAllLines(Path.of(pastJobsFilePath));

        if (rows.size() >= 21) {
            deleteLine(pastJobsFilePath, configsFolderPath, rows.getLast());
        }
    }

    public static void getJob() {

    }
}
