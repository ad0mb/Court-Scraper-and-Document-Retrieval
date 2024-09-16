package CourtScraper.Helpers;

import java.io.File;

import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;

public class FileManagement {

    //this file contains all the methods that allow you to move rename and modify files/folders

    //folder paths
    private static String downloadedFolderPath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\States\\" + selectedState + "\\" + selectedCounty + "\\Downloaded";
    public static String tempDownloadsFolderPath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Temp";
    private static String tempFolderLocationPath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper";

    public static void tempFileMove(String caseNumber) throws InterruptedException {
        File oldFolder = new File(tempDownloadsFolderPath);

        //moves and renames
        oldFolder.renameTo(new File(downloadedFolderPath + "\\" + caseNumber));
        new File(tempFolderLocationPath + "\\Temp").mkdir();

        Thread.sleep(500);

    }

    public static void tempFileRename(String originalName, String newName) {
        File oldNamedFolder = new File(tempDownloadsFolderPath + "\\" + originalName + ".pdf");

        oldNamedFolder.renameTo(new File(tempDownloadsFolderPath + "\\" + newName + ".pdf"));
    }
}
