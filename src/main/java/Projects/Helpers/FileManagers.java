package Projects.Helpers;

import java.io.File;

import static Projects.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static Projects.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;

public class FileManagers {

    //folder paths
    private static String downloadedFolder = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\" + selectedState + "\\" + selectedCounty + "\\Downloaded";
    private static String tempFolder = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Temp";
    private static String tempFolderLocation = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper";

    public static void caseFileFlow(String caseNumber) {
        File oldFolder = new File(tempFolder);

        //moves and renames
        oldFolder.renameTo(new File(downloadedFolder + "\\" + caseNumber));

        new File(tempFolderLocation + "\\Temp").mkdir();



    }
}
