package courtscraper.helpers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static courtscraper.StartGUI.driver;
import static courtscraper.datamanagement.csv.CSVManagement.appendToCSV;
import static courtscraper.datamanagement.csv.CSVManagement.deleteLine;
import static courtscraper.helpers.FolderPaths.*;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.*;
import static courtscraper.setups.gui.mainpanelelements.MainInputBoxes.date;
import static courtscraper.setups.gui.mainpanelelements.MainInputBoxes.search;

public class Logger {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private static String startTime;
    private static String endTime;
    private String runStatus;


    public Logger() {
        startTime = dateFormat.format(new Date());
        this.runStatus = "Finished";
    }

    public void logError(Exception e) throws IOException {
        endTime = dateFormat.format(new Date());
        this.runStatus = "Failed";
        //specific folder name format (new format because folders cant accept other form
        String folderName = new SimpleDateFormat("dd-MM-yyyy HH.MM.SS").format(new Date());

        //create crash folder
        File crashFolder = new File(CRASH_LOGS_FOLDER_PATH + "\\" + folderName);
        crashFolder.mkdir();

        //create info text file
        File crashInfoFile = new File(CRASH_LOGS_FOLDER_PATH + "\\" + folderName + "\\info.txt");
        crashInfoFile.createNewFile();

        //create html file
        File crashHtmlFile = new File(CRASH_LOGS_FOLDER_PATH + "\\" + folderName + "\\crashpage.html");
        crashHtmlFile.createNewFile();

        //write error to info txt
        FileWriter infoWriter = new FileWriter(crashInfoFile, true);
        infoWriter.write("Start Time: " + startTime + "\nEnd Time: " + endTime + "\nSearch: " + search.getText() + "\nSelected State: " + selectedStateMain + "\nSelected County: " + selectedCountyMain + "\nSelected Flow Type: " + selectedFlowType + "\n " + "\n");
        e.printStackTrace(new PrintWriter(infoWriter));
        infoWriter.flush();
        infoWriter.close();

        //write html source to html file
        FileWriter crashWriter = new FileWriter(crashHtmlFile);
        crashWriter.write(driver.getPageSource());
        crashWriter.flush();
        crashWriter.close();

    }

    public void logJob() throws IOException {
        String appendableString = "";

        //crafts the csv line to add to pastJobslist
        //adds search terms
        appendableString += search.getText() + ",";
        //adds date range
        appendableString += date.getText() + ",";
        //adds selected state
        appendableString += selectedStateMain + ",";
        //adds selected county
        appendableString += selectedCountyMain + ",";
        //adds status (failed or finsihed, this is decided in the FlowStart)
        appendableString +=  runStatus + ",";
        //adds selected flow type
        appendableString += selectedFlowType + ",";
        //adds start date and time
        appendableString += startTime + ",";
        //adds finished date and time
        appendableString += endTime;

        appendToCSV(appendableString, PAST_JOBS_CSV_PATH);
        appendToCSV(appendableString, ALL_JOBS_CSV_PATH);

        checkForMaxJobs();

    }

    private void checkForMaxJobs() throws IOException {
        List<String> rows = Files.readAllLines(Path.of(PAST_JOBS_CSV_PATH));

        if (rows.size() >= 21) {
            for (int i = 0; i<rows.size(); i++) {
                if (rows.get(1).equals(rows.get(i)) || rows.get(i).isEmpty())
                    deleteLine(PAST_JOBS_CSV_PATH, CONFIGS_FOLDER_PATH, rows.get(i));
            }
        }
    }
}
