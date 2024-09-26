/**
 * @author Adam Bouloudene
 * @summary This class is the loop that selects a case number to be searched and retrieved. This is the same flow for any case and will adapt to any case using the StateSelect class. This class ensures no duplicates are allowed to be searched aswell.
 *
 * Methods:
 * stateRetrievalFlow: Loops through all case numbers in the selected state and counties temp.csv. Will sort through duplicates to prevent issues.
 * renameFilesBulk: This is a specific method designed to bulk rename files. It is used for flows that cannot rename documents live and must do so using specific document identifiers. It utilizes 2 lists with matching index names and documents to rename the folders.
 *
 * @todo Explore moving bulk rename to FileManagement.
 */

package courtscraper.flows.states;

import courtscraper.FlowStart;
import courtscraper.helpers.CheckIfRetrieved;
import courtscraper.helpers.FileManagement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static courtscraper.datamanagement.csv.CSVManagement.appendToCSV;
import static courtscraper.datamanagement.csv.CSVManagement.deleteLine;
import static courtscraper.helpers.FileManagement.tempFileRename;
import static courtscraper.helpers.FolderPaths.*;


public class StateParser extends FlowStart {

    // Docket names and numbers for renaming purposes
    protected static List<String> docketNumbers;
    protected static List<String> docketNames;


    public static void stateRetrievalFlow() throws IOException, InterruptedException {
        List<String> tempLines = Files.readAllLines(Path.of(TEMP_CSV_PATH));

        // Parses through the current temp file skipping the first line
        for (int i = 1; i<=tempLines.size()-1; i++) {
            docketNames = new ArrayList<>();
            docketNumbers = new ArrayList<>();

            String[] caseLine = tempLines.get(i).split(",");

            //deletes the line from temp if it found a duplicate and skips retrieval
            if ((CheckIfRetrieved.caseRepeatedCheck(caseLine[0])) || (caseLine[0].isEmpty())) {
                deleteLine(TEMP_CSV_PATH, COUNTY_FOLDER_PATH, tempLines.get(i));
                continue;
            }

            StateSelect.stateFilter(caseLine[0]); // Grabs case files

            FileManagement.tempFileMove(caseLine[0]); // Moves case number folder to downloaded folder

            appendToCSV(tempLines.get(i), DOWNLOADED_TEST_CSV_PATH); // Adds line to downloads folder

            deleteLine(TEMP_CSV_PATH, COUNTY_FOLDER_PATH, tempLines.get(i)); // Deletes line from temp folder


        }
    }

    public static void renameFilesBulk() {
        for (int i = 0; i < docketNames.size(); i++) {
            tempFileRename(docketNumbers.get(i), docketNames.get(i));
        }
    }
}
