package courtscraper.helpers;

import java.io.File;

import static courtscraper.helpers.FolderPaths.*;

public class FileManagement {

    //this file contains all the methods that allow you to move rename and modify files/folders

    //folder paths

    public static void tempFileMove(String caseNumber) throws InterruptedException {
        File oldFolder = new File(TEMP_FOLDER_PATH);

        //moves and renames
        oldFolder.renameTo(new File(DOWNLOADS_FOLDER_PATH + "\\" + caseNumber));
        new File(COURTLINK_SCRAPER_PATH + "\\Temp").mkdir();

        Thread.sleep(500);

    }

    public static void tempFileRename(String originalName, String newName) {
        File oldNamedFolder = new File(TEMP_FOLDER_PATH + "\\" + originalName + ".pdf");

        oldNamedFolder.renameTo(new File(TEMP_FOLDER_PATH + "\\" + newName + ".pdf"));
    }
}
