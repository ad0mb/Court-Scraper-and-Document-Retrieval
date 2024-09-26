/**
 * @author Adam Bouloudene
 * @summary This class contains all the check if retrieved archetype methods. The theme of this class is defined by the methods in it. For example: the first function checks if a case was retrieved before, second function checks if a file was grabbed.
 *
 */

package courtscraper.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static courtscraper.helpers.FolderPaths.DOWNLOADED_TEST_CSV_PATH;
import static courtscraper.helpers.FolderPaths.TEMP_FOLDER_PATH;

public class CheckIfRetrieved {

    public static boolean caseRepeatedCheck(String caseNumber) throws IOException {
        List<String> downloadedList = Files.readAllLines(Path.of(DOWNLOADED_TEST_CSV_PATH));

        // Goes through the downloaded cases list to check skipping the first line
        for (int i = 1; i<=downloadedList.size()-1; i++) {
            String[] row = downloadedList.get(i).split(",");
            if (row[0].equals(caseNumber)) {
                return true;
            }
        }
        return false;
    }

    public static boolean fileDownloadedCheck(String fileName) {
        File tempFolder = new File(TEMP_FOLDER_PATH + "\\" + fileName + ".pdf");

        boolean inFolder = false; // Sets infolder to false for loop

        long startTime = System.currentTimeMillis() + 10000; // Gets current time for timeout sequence
        while (!inFolder && System.currentTimeMillis() < startTime) {
            // If the folder path containing the given parameter name exists it will mark true to end the loop
            if (tempFolder.exists()) {
                inFolder = true;
            }
        }
        return inFolder;
    }
}
