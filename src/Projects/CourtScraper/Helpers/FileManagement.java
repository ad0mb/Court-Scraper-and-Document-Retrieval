package CourtScraper.Helpers;

import java.io.File;

import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;

public class FileManagement {

    //folder paths
    private static String downloadedFolderPath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\States\\" + selectedState + "\\" + selectedCounty + "\\Downloaded";
    private static String tempFolderPath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Temp";
    private static String tempFolderLocationPath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper";

    public static void tempFileMove(String caseNumber) throws InterruptedException {
        File oldFolder = new File(tempFolderPath);

        //moves and renames
        oldFolder.renameTo(new File(downloadedFolderPath + "\\" + caseNumber));
        new File(tempFolderLocationPath + "\\Temp").mkdir();

        Thread.sleep(500);

    }

    public static void tempFileRename(String originalName, String newName) {
        File oldNamedFolder = new File(tempFolderPath + "\\" + originalName + ".pdf");

        oldNamedFolder.renameTo(new File(tempFolderPath + "\\" + newName + ".pdf"));
    }
}