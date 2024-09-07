package Projects.Flows.States;

import Projects.FlowStart;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static Projects.DataManagement.CSV.CSVDownloadedAppend.appendToDownloaded;
import static Projects.DataManagement.CSV.CSVManagement.*;
import static Projects.DataManagement.CSV.CSVSearchAppendTemp.tempFilePath;
import static Projects.Flows.States.StateSelect.stateFilter;
import static Projects.Helpers.CheckIfRetrieved.caseRepeatedCheck;
import static Projects.Helpers.FileManagement.tempFileMove;
import static Projects.Helpers.FileManagement.tempFileRename;
import static Projects.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static Projects.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;

public class StateParser extends FlowStart {

    //file location of the temp file that is used to store data
    public static String tempFolderPath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\" + selectedState + "\\" + selectedCounty;

    //docket names and numbers for renaming purposes
    public static List<String> docketNumbers;
    public static List<String> docketNames;


    public static void stateRetrievalFlow() throws IOException, InterruptedException {
        //declares lists for docket names and docket numbers

        //declares list containing lines from temp file.
        List<String> tempLines = Files.readAllLines(Path.of(tempFilePath));
        //parses through the current temp file skipping the first line
        for (int i = 1; i<=tempLines.size()-1; i++) {
            docketNames = new ArrayList<>();
            docketNumbers = new ArrayList<>();

            String[] caseLine = tempLines.get(i).split(",");

            //deletes the line from temp if it found a duplicate and skips retrieval
            if ((caseRepeatedCheck(caseLine[0])) || (caseLine[0].isEmpty())) {
                deleteLine(tempFilePath, tempFolderPath, tempLines.get(i));
                continue;
            }

            //grabs case files
            stateFilter(caseLine[0]);

            //moves case number folder to downloaded folder
            tempFileMove(caseLine[0]);

            //adds line to downloads folder
            appendToDownloaded(tempLines.get(i));

            //deletes line from temp folder
            deleteLine(tempFilePath, tempFolderPath, tempLines.get(i));


        }
    }

    public static void renameFilesBulk() {
        for (int i = 0; i < docketNames.size(); i++) {
            tempFileRename(docketNumbers.get(i), docketNames.get(i));
        }
    }
}
