package courtscraper.flows.states;

import courtscraper.FlowStart;
import courtscraper.exceptions.BlockedDocumentException;
import courtscraper.helpers.CheckIfRetrieved;
import courtscraper.helpers.FileManagement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static courtscraper.datamanagement.csv.CSVManagement.appendToCSV;
import static courtscraper.datamanagement.csv.CSVManagement.deleteLine;
import static courtscraper.helpers.FileManagement.tempFileRename;
import static courtscraper.helpers.FolderPaths.*;


public class StateParser extends FlowStart {

    //this is the main parser flow which will parse through all gathered case numbers in temp.csv

    //docket names and numbers for renaming purposes
    protected static List<String> docketNumbers;
    protected static List<String> docketNames;


    public static void stateRetrievalFlow() throws IOException, InterruptedException {
        //declares list containing lines from temp file.
        List<String> tempLines = Files.readAllLines(Path.of(TEMP_CSV_PATH));

        //parses through the current temp file skipping the first line
        for (int i = 1; i<=tempLines.size()-1; i++) {
            docketNames = new ArrayList<>();
            docketNumbers = new ArrayList<>();

            String[] caseLine = tempLines.get(i).split(",");

            //deletes the line from temp if it found a duplicate and skips retrieval
            if ((CheckIfRetrieved.caseRepeatedCheck(caseLine[0])) || (caseLine[0].isEmpty())) {
                deleteLine(TEMP_CSV_PATH, COUNTY_FOLDER_PATH, tempLines.get(i));
                continue;
            }

            //catches blocked documents error, Example: Confidential document on harris county
            try {
                StateSelect.stateFilter(caseLine[0]); //grabs case files
            } catch (BlockedDocumentException e) {
                deleteLine(TEMP_CSV_PATH, COUNTY_FOLDER_PATH, tempLines.get(i));
                continue;
            }

            FileManagement.tempFileMove(caseLine[0]); //moves case number folder to downloaded folder

            appendToCSV(tempLines.get(i), DOWNLOADED_TEST_CSV_PATH); //adds line to downloads folder

            deleteLine(TEMP_CSV_PATH, COUNTY_FOLDER_PATH, tempLines.get(i)); //deletes line from temp folder


        }
    }

    public static void renameFilesBulk() {
        for (int i = 0; i < docketNames.size(); i++) {
            tempFileRename(docketNumbers.get(i), docketNames.get(i));
        }
    }
}
