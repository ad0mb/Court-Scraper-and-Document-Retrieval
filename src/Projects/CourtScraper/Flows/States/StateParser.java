package CourtScraper.Flows.States;


import CourtScraper.DataManagement.CSV.CSVDownloadedAppend;
import CourtScraper.FlowStart;
import CourtScraper.Helpers.CheckIfRetrieved;
import CourtScraper.Helpers.FileManagement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static CourtScraper.DataManagement.CSV.CSVManagement.deleteLine;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;


public class StateParser extends FlowStart {

    //file location of the temp file that is used to store data
    public static String tempFolderPath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\States\\" + selectedState + "\\" + selectedCounty;

    //docket names and numbers for renaming purposes
    public static List<String> docketNumbers;
    public static List<String> docketNames;


    public static void stateRetrievalFlow() throws IOException, InterruptedException {
        //declares lists for docket names and docket numbers

        //declares list containing lines from temp file.
        List<String> tempLines = Files.readAllLines(Path.of(CourtScraper.DataManagement.CSV.CSVSearchAppendTemp.tempFilePath));
        //parses through the current temp file skipping the first line
        for (int i = 1; i<=tempLines.size()-1; i++) {
            docketNames = new ArrayList<>();
            docketNumbers = new ArrayList<>();

            String[] caseLine = tempLines.get(i).split(",");

            //deletes the line from temp if it found a duplicate and skips retrieval
            if ((CheckIfRetrieved.caseRepeatedCheck(caseLine[0])) || (caseLine[0].isEmpty())) {
                deleteLine(CourtScraper.DataManagement.CSV.CSVSearchAppendTemp.tempFilePath, tempFolderPath, tempLines.get(i));
                continue;
            }

            //grabs case files
            StateSelect.stateFilter(caseLine[0]);

            //moves case number folder to downloaded folder
            FileManagement.tempFileMove(caseLine[0]);

            //adds line to downloads folder
            CSVDownloadedAppend.appendToDownloaded(tempLines.get(i));

            //deletes line from temp folder
            deleteLine(CourtScraper.DataManagement.CSV.CSVSearchAppendTemp.tempFilePath, tempFolderPath, tempLines.get(i));


        }
    }

    public static void renameFilesBulk() {
        for (int i = 0; i < docketNames.size(); i++) {
            FileManagement.tempFileRename(docketNumbers.get(i), docketNames.get(i));
        }
    }
}
