package CourtScraper.Helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;

public class CheckIfRetrieved {

    public static String downloadedFilePath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\States\\" + selectedState + "\\" + selectedCounty + "\\downloadedtest.csv";

    public static boolean caseRepeatedCheck(String caseNumber) throws IOException {
        List<String> downloadedList = Files.readAllLines(Path.of(downloadedFilePath));

        //goes through the downloaded cases list to check skipping the first line
        for (int i = 1; i<=downloadedList.size()-1; i++) {
            String[] row = downloadedList.get(i).split(",");
            if (row[0].equals(caseNumber)) {
                return true;
            }
        }
        return false;
    }
}
